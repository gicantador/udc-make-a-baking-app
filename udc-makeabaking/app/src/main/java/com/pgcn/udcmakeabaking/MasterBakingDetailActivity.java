package com.pgcn.udcmakeabaking;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.pgcn.udcmakeabaking.model.Baking;
import com.pgcn.udcmakeabaking.model.Ingredient;

import java.util.ArrayList;

public class MasterBakingDetailActivity extends AppCompatActivity implements IngredientFragment.OnListFragmentInteractionListener {
    private static final String TAG = MasterBakingDetailActivity.class.getSimpleName();


    private Baking mBaking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Log.d(TAG, "onCreate");

        setContentView(R.layout.master_baking_detail_activity);

        Intent intentThatStartedThisActivity = getIntent();

        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra(Ingredient.KEY_INGREDIENT)) {

                ArrayList<Ingredient> ingredientsList = intentThatStartedThisActivity
                        .getParcelableArrayListExtra(Ingredient.KEY_INGREDIENT);

                //    mBaking = intentThatStartedThisActivity.getParcelableExtra(Baking.KEY_BAKING);

                //   Log.d(TAG, "Baking para exibir: " + mBaking.getName());

                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(Ingredient.KEY_INGREDIENT, ingredientsList);


                FragmentManager fragmentManager = getSupportFragmentManager();

                // Creating a new head fragment
                IngredientFragment ingredientFragment = new IngredientFragment();
                //ingredientFragment.setmIngredientList(ingredientsList);

                ingredientFragment.setArguments(bundle);


                // Add the fragment to its container using a transaction
                //       fragmentManager.beginTransaction().add(R.id.f_ingredient_list_fragment,
                ///               ingredientFragment).commit();

                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.add(R.id.f_mock_ingredient, ingredientFragment);
                ft.commit();

          /*      IngredientFragment fr = new IngredientFragment();
                fr.setmIngredientList(mBaking.getIngredients());
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.f_ingredient_list_fragment, fr);
                fragmentTransaction.commit();*/
            }


        }


    }

    @Override
    public void onListFragmentInteraction(Ingredient ingrediente) {
        Log.d(TAG, "onListFragmentInteraction ");
    }
}
