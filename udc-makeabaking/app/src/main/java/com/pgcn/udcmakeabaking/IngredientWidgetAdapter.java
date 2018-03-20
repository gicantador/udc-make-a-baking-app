package com.pgcn.udcmakeabaking;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pgcn.udcmakeabaking.model.Ingredient;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Giselle on 16/03/2018.
 */

public class IngredientWidgetAdapter extends RecyclerView.Adapter<IngredientWidgetAdapter.IngredientViewHolder> {
    private static final String TAG = IngredientWidgetAdapter.class.getSimpleName();

    private final List<Ingredient> mIngredientList;

    public IngredientWidgetAdapter(List<Ingredient> items) {
        mIngredientList = items;
    }

    @Override
    public IngredientWidgetAdapter.IngredientViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        Log.d(TAG, "onCreateViewHolder");
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.baking_app_widget_ingredientlist, viewGroup, false);

        return new IngredientWidgetAdapter.IngredientViewHolder(view);

    }

    @Override
    public void onBindViewHolder(IngredientWidgetAdapter.IngredientViewHolder holder, int position) {
        if (null != mIngredientList && !mIngredientList.isEmpty()) {
            Ingredient ingredientItem = mIngredientList.get(position);
            if (null != ingredientItem) {
                //      holder.mMeasure.setText(ingredientItem.getMeasure());
                //        holder.mQuantity.setText(ingredientItem.getQuantity());
                //        holder.mName.setText(ingredientItem.getIngredient());
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

        /*   @BindView(R.id.  appwidget_tv_ingredient_quantity)
           public TextView mQuantity;

           @BindView(R.id.appwidget_tv_ingredient_measure)
           public TextView mMeasure;

           @BindView(R.id.appwidget_tv_ingredient_name)
           public TextView mName;
   */
        public IngredientViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }


}
