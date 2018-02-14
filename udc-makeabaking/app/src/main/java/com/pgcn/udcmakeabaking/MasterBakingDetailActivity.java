package com.pgcn.udcmakeabaking;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.pgcn.udcmakeabaking.model.Ingredient;
import com.pgcn.udcmakeabaking.model.Step;

import java.util.ArrayList;

public class MasterBakingDetailActivity extends AppCompatActivity implements IngredientFragment.OnListFragmentInteractionListener, StepFragment.OnListFragmentInteractionListener {
    private static final String TAG = MasterBakingDetailActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Log.d(TAG, "onCreate");

        setContentView(R.layout.activity_master_baking_detail);

        Intent intentThatStartedThisActivity = getIntent();

        if (intentThatStartedThisActivity != null) {
            Bundle bundle;
            FragmentManager fragmentManager = getSupportFragmentManager();


            if (intentThatStartedThisActivity.hasExtra(Step.KEY_STEP)) {
                ArrayList<Step> stepList = intentThatStartedThisActivity
                        .getParcelableArrayListExtra(Step.KEY_STEP);
                bundle = new Bundle();
                bundle.putParcelableArrayList(Step.KEY_STEP, stepList);
                StepFragment stepFragment = new StepFragment();
                stepFragment.setArguments(bundle);
                FragmentTransaction fts = fragmentManager.beginTransaction();
                fts.add(R.id.f_mock_steps, stepFragment);
                fts.commit();
            }
            if (intentThatStartedThisActivity.hasExtra(Ingredient.KEY_INGREDIENT)) {
                ArrayList<Ingredient> ingredientsList = intentThatStartedThisActivity
                        .getParcelableArrayListExtra(Ingredient.KEY_INGREDIENT);
                IngredientFragment ingredientFragment = new IngredientFragment();
                bundle = new Bundle();
                bundle.putParcelableArrayList(Ingredient.KEY_INGREDIENT, ingredientsList);
                ingredientFragment.setArguments(bundle);
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.add(R.id.f_mock_ingredient, ingredientFragment);
                ft.commit();
            }
        }

    }

    @Override
    public void onListFragmentInteraction(Ingredient ingrediente) {
        Log.d(TAG, "onListFragmentInteraction ingrediente ");
    }

    @Override
    public void onListFragmentInteraction(Step step) {
        Log.d(TAG, "onListFragmentInteraction step ");

    }
}
