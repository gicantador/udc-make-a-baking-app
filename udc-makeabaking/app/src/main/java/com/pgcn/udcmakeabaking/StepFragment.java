package com.pgcn.udcmakeabaking;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pgcn.udcmakeabaking.model.Step;

import java.util.ArrayList;

/**
 * Created by Giselle on 04/02/2018.
 */

public class StepFragment extends Fragment implements StepAdapter.StepAdapterOnClickHandler {
    private static final String TAG = StepFragment.class.getSimpleName();

    private ArrayList<Step> mStepList;

    public StepFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_steps_list, container, false);
        if (null != savedInstanceState) {
            mStepList = savedInstanceState.getParcelableArrayList(Step.KEY_STEP);

        } else {
            Bundle bundle = this.getArguments();
            if (bundle != null) {
                mStepList = bundle.getParcelableArrayList(Step.KEY_STEP);
            }

        }
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new StepAdapter(mStepList, this));
        }
        return view;

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            //  mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onClick(Step step, int adapterPosition) {
        Log.d(TAG, " onClick position: " + adapterPosition);
        Log.d(TAG, " step " + step.getShortDescription());

        // clicou no step, abrir step detalhado
        // no celular apresentar step detalhado e video
        // na tablet lista de steps e video grande ao lado


        Intent intent = new Intent(getActivity(), StepDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(Step.KEY_STEP, step);

        bundle.putParcelableArrayList(Step.KEY_STEP_LIST, mStepList);

        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(Step step);
    }


}
