package com.pgcn.udcmakeabaking;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pgcn.udcmakeabaking.IngredientFragment.OnListFragmentInteractionListener;
import com.pgcn.udcmakeabaking.model.Ingredient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Ingredient} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {

    private static final String TAG = IngredientAdapter.class.getSimpleName();

    private final List<Ingredient> mIngredientList;


    public IngredientAdapter(List<Ingredient> items) {
        mIngredientList = items;
    }

    @Override
    public IngredientViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        //    Log.d(TAG, "onCreateViewHolder");
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.adapter_ingredient, viewGroup, false);

        return new IngredientViewHolder(view);

    }

    @Override
    public void onBindViewHolder(IngredientViewHolder holder, int position) {
        if (null != mIngredientList && !mIngredientList.isEmpty()) {
            Ingredient ingredientItem = mIngredientList.get(position);
            if (null != ingredientItem) {
                holder.mMeasure.setText(ingredientItem.getMeasure());
                holder.mQuantity.setText(ingredientItem.getQuantity());
                holder.mName.setText(ingredientItem.getIngredient());
            }
        }

    }

    @Override
    public int getItemCount() {
        if (null != mIngredientList)
            return mIngredientList.size();
        else return 0;
    }

    public class IngredientViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_ingredient_quantity)
        public TextView mQuantity;

        @BindView(R.id.tv_ingredient_measure)
        public TextView mMeasure;

        @BindView(R.id.tv_ingredient_name)
        public TextView mName;


        // --Commented out by Inspection (04/02/2018 01:04):public Ingredient mItem;

        public IngredientViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }



    }
}
