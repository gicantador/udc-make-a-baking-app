package com.pgcn.udcmakeabaking;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pgcn.udcmakeabaking.model.Step;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Giselle on 04/02/2018.
 */

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepViewHolder> {

    private static final String TAG = StepAdapter.class.getSimpleName();

    private final List<Step> mSteptList;


    public StepAdapter(ArrayList<Step> items, StepAdapterOnClickHandler click) {
        mSteptList = items;
        mClickHandler = click;
    }

    @Override
    public StepViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.adapter_steps_short, viewGroup, false);

        return new StepViewHolder(view);

    }

    @Override
    public void onBindViewHolder(StepViewHolder holder, int position) {
        if (null != mSteptList && !mSteptList.isEmpty()) {
            Step stepItem = mSteptList.get(position);
            if (null != stepItem) {
                holder.mStepShortDescription.setText(stepItem.getShortDescription());
            }
        }

    }

    @Override
    public int getItemCount() {
        return mSteptList.size();
    }

    public class StepViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tv_step_shortDescription)
        public TextView mStepShortDescription;

        public StepViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClick");
            int adapterPosition = getAdapterPosition();
            Step stepSelected = mSteptList.get(adapterPosition);
            if (stepSelected != null) {
                Log.d(TAG, "Step Posicionado selecionado : posicao[" + adapterPosition + "] " +
                        "title[" + stepSelected.getShortDescription() + "]");
                mClickHandler.onClick(stepSelected, adapterPosition);
            }
        }
    }

    private final StepAdapterOnClickHandler mClickHandler;

    public interface StepAdapterOnClickHandler {
        void onClick(Step step, int adapterPosition);
    }
}
