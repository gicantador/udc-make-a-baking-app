package com.pgcn.udcmakeabaking;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

import com.pgcn.udcmakeabaking.model.Step;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StepDetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class StepDetailFragment extends Fragment {

    private static final String TAG = StepDetailFragment.class.getSimpleName();

    private Step mStep;

    private OnFragmentInteractionListener mListener;

    @BindView(R.id.tv_step_description)
    TextView mStepDescription;

    @BindView(R.id.vv_videoView)
    VideoView mStepVideo;

    private ArrayList<Step> mStepList;

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
        View view = inflater.inflate(R.layout.fragment_step_detail, container, false);
        ButterKnife.bind(this, view);


        if (null != savedInstanceState) {
            mStep = savedInstanceState.getParcelable(Step.KEY_STEP);
        } else {
            Bundle bundle = this.getArguments();
            if (bundle != null) {
                mStep = bundle.getParcelable(Step.KEY_STEP);
            }
        }
        if (null != mStep) {
            mStepDescription.setText(mStep.getDescription());
            mStepVideo.setVideoURI(Uri.parse(mStep.getVideoURL()));
        }
        return view;

    }

    @Override
    public void onAttach(Context context) {
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
        super.onDetach();
        mListener = null;
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
    }

    public interface OnListFragmentInteractionListener {
    }
}
