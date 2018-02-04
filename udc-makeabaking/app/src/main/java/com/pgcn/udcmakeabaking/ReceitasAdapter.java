package com.pgcn.udcmakeabaking;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pgcn.udcmakeabaking.model.Baking;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 */
public class ReceitasAdapter extends RecyclerView.Adapter<ReceitasAdapter.ReceitasViewHolder> {
    private static final String TAG = ReceitasAdapter.class.getSimpleName();
    private final ReceitasAdapterOnClickHandler mClickHandler;
    private ArrayList<Baking> mBakingList;

    public ReceitasAdapter(ArrayList<Baking> bakingList, ReceitasAdapterOnClickHandler clickHandler) {

        mBakingList = bakingList;
        mClickHandler = clickHandler;
    }

    @Override
    public ReceitasViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        Log.d(TAG, "onCreateViewHolder");
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.baking_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem, viewGroup, false);

        return new ReceitasViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ReceitasViewHolder holder, int position) {
        Baking baking = mBakingList.get(position);
        if (baking != null) {
            Log.d(TAG, "receita: " + baking.getName());
            holder.bakingName.setText(baking.getName());
            Log.d(TAG, "imagem: " + baking.getImage());
            String imagePath = baking.getImage();
            if (imagePath != null && !imagePath.isEmpty()) {
                Picasso.with(holder.bakingImage.getContext()).load(imagePath).placeholder(R
                        .drawable.placeholder_empty).into(holder.bakingImage);
            }
        }
    }

    @Override
    public int getItemCount() {
        if (mBakingList != null) {
            return mBakingList.size();
        }
        return 0;
    }

    public void setBakingData() {
        mBakingList = null;
        notifyDataSetChanged();
    }

    public interface ReceitasAdapterOnClickHandler {
        void onClick(Baking baking, int adapterPosition);
    }

    /**
     * Cache of the children views for a list item.
     */
    class ReceitasViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.iv_baking_img)
        ImageView bakingImage;

        @BindView(R.id.tv_baking_title)
        TextView bakingName;

        public ReceitasViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClick");
            int adapterPosition = getAdapterPosition();
            Baking bakingSelected = mBakingList.get(adapterPosition);
            if (bakingSelected != null) {
                Log.d(TAG, "Receita selecionado : posicao[" + adapterPosition + "] title[" +
                        bakingSelected.getName() + "]");
                mClickHandler.onClick(bakingSelected, adapterPosition);
            }
        }
    }
}
