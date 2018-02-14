package com.pgcn.udcmakeabaking;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.pgcn.udcmakeabaking.model.Step;

public class StepDetailActivity extends AppCompatActivity implements StepDetailFragment.OnFragmentInteractionListener {
    private static final String TAG = StepDetailActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);
        Log.d(TAG, "onCreate");

        Intent intentThatStartedThisActivity = getIntent();
        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra(Step.KEY_STEP)) {
                Step theStep = intentThatStartedThisActivity.getParcelableExtra(Step.KEY_STEP);
                Log.d(TAG, "Step recebido " + theStep.toString());


                Bundle bundle = new Bundle();
                bundle.putParcelable(Step.KEY_STEP, theStep);
                StepDetailFragment stepDetailFragment = new StepDetailFragment();
                stepDetailFragment.setArguments(bundle);
                FragmentManager fragmentManager = getSupportFragmentManager();

                FragmentTransaction fts = fragmentManager.beginTransaction();
                fts.add(R.id.f_mock_stepdetail, stepDetailFragment);
                fts.commit();

            }
        }
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
