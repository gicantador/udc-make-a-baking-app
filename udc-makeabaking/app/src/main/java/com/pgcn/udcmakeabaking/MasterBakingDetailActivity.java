package com.pgcn.udcmakeabaking;


import android.content.ContextWrapper;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.pgcn.udcmakeabaking.model.Baking;
import com.pgcn.udcmakeabaking.model.Ingredient;
import com.pgcn.udcmakeabaking.model.Step;
import com.pgcn.udcmakeabaking.util.LayoutUtil;
import com.pixplicity.easyprefs.library.Prefs;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class MasterBakingDetailActivity extends AppCompatActivity implements IngredientFragment.OnListFragmentInteractionListener, StepFragment.OnSelectStepFragmentInteractionListener, StepDetailFragment.OnFragmentInteractionListener {

    private static final String TAG = MasterBakingDetailActivity.class.getSimpleName();
    private ArrayList<Step> mStepList;
    private ArrayList<Ingredient> mIngredientList;
    private Baking mBaking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate");

        setContentView(R.layout.activity_master_baking_detail);

        Intent intentThatStartedThisActivity = getIntent();

        if (intentThatStartedThisActivity != null) {
            Bundle bundle;
            FragmentManager fragmentManager = getSupportFragmentManager();

            if (intentThatStartedThisActivity.hasExtra(Baking.KEY_BAKING)) {
                mBaking = intentThatStartedThisActivity.getParcelableExtra(Baking.KEY_BAKING);
            }

            if (intentThatStartedThisActivity.hasExtra(Step.KEY_STEP_LIST)) {
                mStepList = intentThatStartedThisActivity
                        .getParcelableArrayListExtra(Step.KEY_STEP_LIST);
                bundle = new Bundle();
                bundle.putParcelableArrayList(Step.KEY_STEP_LIST, mStepList);
                StepFragment stepFragment = new StepFragment();
                stepFragment.setArguments(bundle);
                FragmentTransaction fts = fragmentManager.beginTransaction();
                fts.add(R.id.f_mock_steps, stepFragment);
                fts.commit();
            }
            if (intentThatStartedThisActivity.hasExtra(Ingredient.KEY_INGREDIENT_LIST)) {
                mIngredientList = intentThatStartedThisActivity
                        .getParcelableArrayListExtra(Ingredient.KEY_INGREDIENT_LIST);
                IngredientFragment ingredientFragment = new IngredientFragment();
                bundle = new Bundle();
                bundle.putParcelableArrayList(Ingredient.KEY_INGREDIENT_LIST, mIngredientList);
                ingredientFragment.setArguments(bundle);
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.add(R.id.f_mock_ingredient, ingredientFragment);
                ft.commit();
            }


        }
        // Initialize the Prefs class
        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(Baking.PREFERENCE_NAME)
                .setUseDefaultSharedPreference(true)
                .build();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (R.id.action_saveforwidget == id) {
            salvarNoPreferences();
        }

        return super.onOptionsItemSelected(item);
    }

    private void salvarNoPreferences() {
        if (mBaking != null) {
            Prefs.putString(Baking.KEY_BAKING_NAME, mBaking.getName());
            Prefs.putString(Baking.KEY_BAKING_IMAGE, mBaking.getImage());
            Set<String> listaTextoIngredientes = new TreeSet<String>();
            for (Ingredient ingrediente : mIngredientList) {
                listaTextoIngredientes.add(ingredienteParaTexto(ingrediente));
            }
            Prefs.putOrderedStringSet(Ingredient.KEY_INGREDIENT_LIST, listaTextoIngredientes);
        }

    }

    private String ingredienteParaTexto(Ingredient ingrediente) {
        return ingrediente.getQuantity() + StringUtils.SPACE + ingrediente.getMeasure() + StringUtils.SPACE + getString(R.string.lb_of)
                + StringUtils.SPACE + ingrediente.getIngredient();
    }

    @Override
    public void onListFragmentInteraction(Ingredient ingrediente) {
        Log.d(TAG, "onListFragmentInteraction ingrediente ");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(Step.KEY_STEP_LIST, mStepList);
        outState.putParcelableArrayList(Ingredient.KEY_INGREDIENT, mIngredientList);
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onSelectStep(Step step, int adapterPosition) {
        if (LayoutUtil.isTablet(this)) {
            montaFragmentStepDetail(step, adapterPosition);
        } else {
            Intent intent = new Intent(this, StepDetailActivity.class);
            Bundle bundle = new Bundle();

            bundle.putParcelable(Step.KEY_STEP, step);
            bundle.putParcelableArrayList(Step.KEY_STEP_LIST, mStepList);
            bundle.putInt(Step.KEY_STEP_POSITION, adapterPosition);

            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

    void montaFragmentStepDetail(Step step, int position) {
        Log.d(TAG, "montaFragmentStepDetail()");

        Bundle bundle = new Bundle();
        bundle.putInt(Step.KEY_STEP_LIST_SIZE, mStepList.size());
        bundle.putInt(Step.KEY_STEP_POSITION, position);
        bundle.putParcelable(Step.KEY_STEP, step);

        StepDetailFragment stepDetailFragment = new StepDetailFragment();
        stepDetailFragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fts = fragmentManager.beginTransaction();
        fts.replace(R.id.f_tablet_mock_stepdetail, stepDetailFragment);

        fts.commit();

    }


    @Override
    public void onFragmentInteraction(Uri uri) {
        Log.d(TAG, " onFragmentInteraction(Uri uri)()" + uri);
    }

    @Override
    public void onFragmentInteraction(int position) {
        Log.d(TAG, "onFragmentInteraction(int position()" + position);
    }

    @Override
    public void onFragmentInteraction(Step step) {
        Log.d(TAG, "onFragmentInteraction(Step step)()" + step);
    }
}
