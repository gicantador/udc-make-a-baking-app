package com.pgcn.udcmakeabaking;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.media.session.MediaButtonReceiver;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.pgcn.udcmakeabaking.model.Step;
import com.pgcn.udcmakeabaking.util.ImageUtils;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StepDetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class StepDetailFragment extends Fragment implements ExoPlayer.EventListener {
    private static final String TAG = StepDetailFragment.class.getSimpleName();
    private static MediaSessionCompat mMediaSession;
    @BindView(R.id.bt_nextstep)
    @Nullable
    Button mBtNext;
    @BindView(R.id.bt_prevstep)
    @Nullable
    Button mBtPrev;
    @BindView(R.id.tv_step_order)
    @Nullable
    TextView mStepOrder;
    @BindView(R.id.tv_step_description)
    @Nullable
    TextView mStepDescription;
    @BindView(R.id.tv_step_smalldescription)
    @Nullable
    TextView mStepShortDescription;
    @BindView(R.id.vv_videoView)
    @Nullable
    SimpleExoPlayerView mPlayerView;
    private SimpleExoPlayer mExoPlayer;
    private PlaybackStateCompat.Builder mStateBuilder;
    private Step mStep;
    private Integer mStepListSize;
    private Integer mStepPosition;
    private OnFragmentInteractionListener mListener;
    private ConstraintLayout.LayoutParams paramsNotFullscreen;

    public StepDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d(TAG, "*** onCreateView StepDetailFragment *****");

        View view = inflater.inflate(R.layout.fragment_step_detail, container, false);

        ButterKnife.bind(this, view);

        // Load the question mark as the background image until the user answers the question.


        if (null != savedInstanceState) {
            mStep = savedInstanceState.getParcelable(Step.KEY_STEP);
            mStepListSize = savedInstanceState.getInt(Step.KEY_STEP_LIST_SIZE);
            mStepPosition = savedInstanceState.getInt((Step.KEY_STEP_POSITION));

        } else {
            Bundle bundle = this.getArguments();
            if (bundle != null) {
                mStep = bundle.getParcelable(Step.KEY_STEP);
                mStepListSize = bundle.getInt(Step.KEY_STEP_LIST_SIZE);
                mStepPosition = bundle.getInt(Step.KEY_STEP_POSITION);

            }
        }

        if (null != mStepDescription) {
            mStepDescription.setText(mStep.getDescription());
            mStepShortDescription.setText(mStep.getShortDescription());
            String text = String.format(getString(R.string.lb_step_order), mStepPosition, mStepListSize);
            mStepOrder.setText(text);
            iniciaBotoes();
        }
        iniciaVideo();
        return view;

    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    private void iniciaBotoes() {

        Log.d(TAG, "iniciaBotoes() posicao[" + mStepPosition + "] tamanho lista[" + mStepListSize + "]");

        if (mStepPosition.compareTo(mStepListSize) != (-1)) {
            mBtNext.setVisibility(View.INVISIBLE);
        } else
            mBtNext.setVisibility(View.VISIBLE);
        if (mStepPosition.equals(0)) {
            mBtPrev.setVisibility(View.INVISIBLE);
        } else mBtPrev.setVisibility(View.VISIBLE);


        mBtNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d(TAG, "Clique NEXT, abrir step " + (mStepPosition + 1));
                //   releasePlayer();
                mPlayerView.setVisibility(View.INVISIBLE);
                //       getActivity().de
                mListener.onFragmentInteraction(mStepPosition + 1);
            }
        });


        mBtPrev.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d(TAG, "Clique PREV, abrir step " + (mStepPosition - 1));
                //  releasePlayer();
                mPlayerView.setVisibility(View.INVISIBLE);
                mListener.onFragmentInteraction(mStepPosition - 1);
            }
        });
    }

    private void iniciaVideo() {
        Bitmap placeholder;
        if (null != mStep.getThumbnailURL() && !mStep.getThumbnailURL().isEmpty()) {
            try {
                placeholder = ImageUtils.getBitmapFromURL(mStep.getThumbnailURL());
            } catch (Exception e) {
                e.printStackTrace();
                placeholder = BitmapFactory.decodeResource(getResources(), R.drawable.placeholder);
            }
        } else {
            placeholder = BitmapFactory.decodeResource(getResources(), R.drawable.placeholder);
        }

        //    if (null != mStep.getVideoURL() && !mStep.getVideoURL().isEmpty()) {
        mPlayerView.setVisibility(View.VISIBLE);
        mPlayerView.setDefaultArtwork(placeholder);
        try {
            // Initialize the Media Session.
            initializeMediaSession();
            // Initialize the player.
            initializePlayer(Uri.parse(Uri.decode(mStep.getVideoURL())));
        } catch (Exception e) {
            Log.e(TAG, "erro ao carregar video", e);
            //e.printStackTrace();
        }
    }

    /**
     * Initializes the Media Session to be enabled with media buttons, transport controls, callbacks
     * and media controller.
     */
    private void initializeMediaSession() {

        // Create a MediaSessionCompat.
        mMediaSession = new MediaSessionCompat(getActivity(), TAG);

        // Enable callbacks from MediaButtons and TransportControls.
        mMediaSession.setFlags(
                MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS |
                        MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS);

        // Do not let MediaButtons restart the player when the app is not visible.
        mMediaSession.setMediaButtonReceiver(null);

        // Set an initial PlaybackState with ACTION_PLAY, so media buttons can start the player.
        mStateBuilder = new PlaybackStateCompat.Builder()
                .setActions(
                        PlaybackStateCompat.ACTION_PLAY |
                                PlaybackStateCompat.ACTION_PAUSE |
                                PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS |
                                PlaybackStateCompat.ACTION_PLAY_PAUSE);

        mMediaSession.setPlaybackState(mStateBuilder.build());


        // MySessionCallback has methods that handle callbacks from a media controller.
        mMediaSession.setCallback(new MySessionCallback());

        // Start the Media Session since the activity is active.
        mMediaSession.setActive(true);

    }

    /**
     * Initialize ExoPlayer.
     *
     * @param mediaUri The URI of the sample to play.
     */
    private void initializePlayer(Uri mediaUri) {
        if (mExoPlayer == null) {
            // Create an instance of the ExoPlayer.
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(getActivity(), trackSelector, loadControl);
            mPlayerView.setPlayer(mExoPlayer);
            // Set the ExoPlayer.EventListener to this activity.
            mExoPlayer.addListener(this);
            // Prepare the MediaSource.
            String userAgent = Util.getUserAgent(getActivity(), "Makeabaking");
            MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(
                    getActivity(), userAgent), new DefaultExtractorsFactory(), null, null);
            mExoPlayer.prepare(mediaSource);
            mExoPlayer.setPlayWhenReady(true);
        }
    }

    /**
     * Release ExoPlayer.
     */
    private void releasePlayer() {
//        mNotificationManager.cancelAll();
        mExoPlayer.stop();
        mExoPlayer.release();
        mExoPlayer = null;
    }

    /**
     * Release the player when the activity is destroyed.
     */
    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy()");

        super.onDestroy();
        releasePlayer();
    }

    @Override
    public void onAttach(Context context) {
        Log.d(TAG, "onAttach()");

        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        Log.d(TAG, "onDetach()");

        super.onDetach();
        mListener = null;
    }

    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {
        Log.d(TAG, "onLoadingChanged(boolean[ " + isLoading + "]");

    }

    /**
     * Method that is called when the ExoPlayer state changes. Used to update the MediaSession
     * PlayBackState to keep in sync.
     * @param playWhenReady true if ExoPlayer is playing, false if it's paused.
     * @param playbackState int describing the state of ExoPlayer. Can be STATE_READY, STATE_IDLE,
     *                      STATE_BUFFERING, or STATE_ENDED.
     */
    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        if((playbackState == ExoPlayer.STATE_READY) && playWhenReady){
            mStateBuilder.setState(PlaybackStateCompat.STATE_PLAYING,
                    mExoPlayer.getCurrentPosition(), 1f);
        } else if((playbackState == ExoPlayer.STATE_READY)){
            mStateBuilder.setState(PlaybackStateCompat.STATE_PAUSED,
                    mExoPlayer.getCurrentPosition(), 1f);
        }
        mMediaSession.setPlaybackState(mStateBuilder.build());

    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {

    }

    @Override
    public void onPositionDiscontinuity() {

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(Step.KEY_STEP, mStep);
        outState.putInt(Step.KEY_STEP_LIST_SIZE, mStepListSize);
        outState.putInt(Step.KEY_STEP_POSITION, mStepPosition);
        super.onSaveInstanceState(outState);

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);

        void onFragmentInteraction(int position);

        void onFragmentInteraction(Step step);
    }

    public interface OnListFragmentInteractionListener {
    }

    /**
     * Broadcast Receiver registered to receive the MEDIA_BUTTON intent coming from clients.
     */
    public static class MediaReceiver extends BroadcastReceiver {

        public MediaReceiver() {
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            MediaButtonReceiver.handleIntent(mMediaSession, intent);
        }
    }

    /**
     * Media Session Callbacks, where all external clients control the player.
     */
    private class MySessionCallback extends MediaSessionCompat.Callback {
        @Override
        public void onPlay() {
            mExoPlayer.setPlayWhenReady(true);
        }

        @Override
        public void onPause() {
            mExoPlayer.setPlayWhenReady(false);
        }

        @Override
        public void onSkipToPrevious() {
            mExoPlayer.seekTo(0);
        }
    }

//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//
//        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) //To fullscreen
//        {
//            paramsNotFullscreen = (ConstraintLayout.LayoutParams) mPlayerView.getLayoutParams();
//            ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(paramsNotFullscreen);
//            params.setMargins(0, 0, 0, 0);
//            params.height = ViewGroup.LayoutParams.MATCH_PARENT;
//            params.width = ViewGroup.LayoutParams.MATCH_PARENT;
//            mPlayerView.setLayoutParams(params);
//
//        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
//            mPlayerView.setLayoutParams(paramsNotFullscreen);
//        }
//
//    }
}
