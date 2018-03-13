package com.pgcn.udcmakeabaking;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.pgcn.udcmakeabaking.model.Step;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class StepDetailActivity extends AppCompatActivity implements StepDetailFragment.OnFragmentInteractionListener {
    private static final String TAG = StepDetailActivity.class.getSimpleName();


    private ArrayList<Step> mStepList;
    private Integer mStepPosition;
    private Step mStep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);
        Log.d(TAG, "onCreate");

        Intent intentThatStartedThisActivity = getIntent();
        ButterKnife.bind(this);
        if (savedInstanceState != null) {
            mStep = savedInstanceState.getParcelable(Step.KEY_STEP);
            mStepList = savedInstanceState.getParcelableArrayList(Step.KEY_STEP_LIST);
            mStepPosition = savedInstanceState.getInt((Step.KEY_STEP_POSITION));

        } else if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra(Step.KEY_STEP)) {
                mStep = intentThatStartedThisActivity.getParcelableExtra(Step.KEY_STEP);
            }

            if (intentThatStartedThisActivity.hasExtra(Step.KEY_STEP_LIST)) {
                mStepList = intentThatStartedThisActivity
                        .getParcelableArrayListExtra(Step.KEY_STEP_LIST);

            }
            mStepPosition = intentThatStartedThisActivity.getIntExtra(Step
                    .KEY_STEP_POSITION, 0);

        }

        //  mStepList = montaOrdensLista(theSte22p);
        Log.d(TAG, "Lista de steps recebida " + mStepList.size());
        Log.d(TAG, "Step recebido " + mStep.toString());


        montaFragment(mStep, true);
    }


    private void montaFragment(Step theStep, Boolean inicio) {
        Log.d(TAG, "montaFragment ");

        Bundle bundle = new Bundle();
        bundle.putInt(Step.KEY_STEP_LIST_SIZE, mStepList.size());
        bundle.putInt(Step.KEY_STEP_POSITION, mStepPosition);
        bundle.putParcelable(Step.KEY_STEP, theStep);

        StepDetailFragment stepDetailFragment = new StepDetailFragment();
        stepDetailFragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fts = fragmentManager.beginTransaction();
        if (inicio) {
            fts.replace(R.id.f_mock_stepdetail, stepDetailFragment);
        } else
            fts.replace(R.id.f_mock_stepdetail, stepDetailFragment);
        fts.commit();

    }



    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onFragmentInteraction(int position) {
        Step stepToShow = null;

        if (position > (-1) && position < mStepList.size()) {
            stepToShow = mStepList.get(position);
            mStepPosition = position;
        }

        if (stepToShow != null) {
            montaFragment(stepToShow, false);
        }
    }

    @Override
    public void onFragmentInteraction(Step step) {

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState");
        outState.putParcelable(Step.KEY_STEP, mStep);
        outState.putParcelableArrayList(Step.KEY_STEP_LIST, mStepList);
        outState.putInt(Step.KEY_STEP_POSITION, mStepPosition);

        super.onSaveInstanceState(outState);
    }



}
