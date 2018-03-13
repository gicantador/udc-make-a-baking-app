package com.pgcn.udcmakeabaking;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import com.pgcn.udcmakeabaking.model.Baking;
import com.pgcn.udcmakeabaking.model.Ingredient;
import com.pgcn.udcmakeabaking.model.Step;
import com.pgcn.udcmakeabaking.service.AsyncTaskDelegate;
import com.pgcn.udcmakeabaking.util.BakingJSONResourceReader;
import com.pgcn.udcmakeabaking.util.BakingUtils;
import com.pgcn.udcmakeabaking.util.NetworkUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BakingTimeActivity extends AppCompatActivity implements AsyncTaskDelegate,
        ReceitasAdapter.ReceitasAdapterOnClickHandler {
    private static final String TAG = BakingTimeActivity.class.getSimpleName();
    @BindView(R.id.rv_receitas)
    RecyclerView mRecyclerViewReceitas;
    @BindView(R.id.pb_loading_indicator)
    ProgressBar mProgressBar;
    private ArrayList<Baking> mBakingArrayList;

    //  @BindView(R.id.toolbar)
    //   Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate");

        setContentView(R.layout.baking_time_activity_main);
        ButterKnife.bind(this);

        //    setSupportActionBar(mToolbar);


        if (null != savedInstanceState) {
            mBakingArrayList = savedInstanceState.getParcelableArrayList(Baking.KEY_BAKING);
        }
        if (null == mBakingArrayList || mBakingArrayList.isEmpty()) {
            try {
                recuperaListaReceitas();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, numberOfColumns());
        ReceitasAdapter mReceitasAdapter = new ReceitasAdapter(mBakingArrayList, this);
        mRecyclerViewReceitas.setLayoutManager(mLayoutManager);
        mRecyclerViewReceitas.setHasFixedSize(false);
        mRecyclerViewReceitas.setAdapter(mReceitasAdapter);

    }


    private int numberOfColumns() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int widthDivider = 600;
        int width = displayMetrics.widthPixels;
        int nColumns = width / widthDivider;
        Log.d(TAG, "nro colunas " + nColumns);
        if (nColumns < 1) return 1;
        return nColumns;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void processFinish(Object output) {

        Log.d(TAG, "processFinish()");

        mBakingArrayList = (ArrayList<Baking>) output;
        if (null != mBakingArrayList && !mBakingArrayList.isEmpty()) {
            for (int i = 0; i < mBakingArrayList.size(); i++) {
                Log.d(TAG, " bakingArrayList " + mBakingArrayList);
            }
        }

    }

    private void recuperaListaReceitas() throws IOException {

        boolean isOnline = NetworkUtils.isOnline((ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE));
        mBakingArrayList = (ArrayList<Baking>) recuperaListaInterna();

    }

    @Override
    public void preExecute() {

        Log.d(TAG, "preExecute()");

    }

    private List<Baking> recuperaListaInterna() {

        // Load our JSON file.
        BakingJSONResourceReader reader = new BakingJSONResourceReader(getResources(), R.raw.baking);
        Log.d(TAG, " reader " + reader.toString());
        List<Baking> bakingArrayList = reader.constructUsingGson();
        if (null != bakingArrayList && !bakingArrayList.isEmpty()) {
            Log.d(TAG, " bakingArrayList size" + bakingArrayList.size());
            for (int i = 0; i < bakingArrayList.size(); i++) {
                Baking baking = bakingArrayList.get(i);
                Log.d(TAG, " bakingArrayList " + baking.getName());
            }
        }
        return bakingArrayList;

    }

    @Override
    public void onClick(Baking baking, int adapterPosition) {
        Log.d(TAG, " onClick position: " + adapterPosition);
        Log.d(TAG, " baking " + baking.getName());

        // clicou na receita, abrir receita detalhada
        // no celular apenas lista de ingredientes e lista de steps
        // na tablet lista de ingredientes e steps expandindo para video dos stepps ao lado.


        Intent intent = new Intent(this, MasterBakingDetailActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(Step.KEY_STEP_LIST, BakingUtils.ordenaStepsPorId(baking
                .getSteps()));
        bundle.putParcelableArrayList(Ingredient.KEY_INGREDIENT_LIST, baking.getIngredients());
        Log.d(TAG, " indo no intent: " + baking.getSteps().size() + " steps");
        Log.d(TAG, " indo no intent: " + baking.getIngredients().size()+ " ingredientes");

        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(Baking.KEY_BAKING, mBakingArrayList);
        super.onSaveInstanceState(outState);
    }
}
