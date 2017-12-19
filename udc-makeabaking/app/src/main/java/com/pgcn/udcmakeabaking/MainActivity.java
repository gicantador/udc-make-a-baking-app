package com.pgcn.udcmakeabaking;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.pgcn.udcmakeabaking.model.Baking;
import com.pgcn.udcmakeabaking.service.AsyncTaskDelegate;
import com.pgcn.udcmakeabaking.util.BakingJSONResourceReader;
import com.pgcn.udcmakeabaking.util.NetworkUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AsyncTaskDelegate {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        try {
            listarBaking();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

        ArrayList<Baking> bakingArrayList = (ArrayList<Baking>) output;
        if (null != bakingArrayList && !bakingArrayList.isEmpty()) {
            for (int i = 0; i <= bakingArrayList.size(); i++) {
                Log.d(TAG, " bakingArrayList " + bakingArrayList);
            }
        }

    }

    @Override
    public void preExecute() {
        boolean isOnline = NetworkUtils.isOnline((ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE));

        if (isOnline) {
            BakingJSONResourceReader reader = new BakingJSONResourceReader(getResources(), R.raw
                    .baking);
        }

    }

    private void listarBaking() throws IOException {

        // Load our JSON file.
        BakingJSONResourceReader reader = new BakingJSONResourceReader(getResources(), R.raw.baking);
        Log.d(TAG, " reader " + reader.toString());
        List<Baking> bakingArrayList = (List<Baking>) reader.constructUsingGson();
        if (null != bakingArrayList && !bakingArrayList.isEmpty()) {
            Log.d(TAG, " bakingArrayList size" + bakingArrayList.size());
            for (int i = 0; i <= bakingArrayList.size(); i++) {
                Baking baking = bakingArrayList.get(i);
                Log.d(TAG, " bakingArrayList " + baking.getName());
            }
        }


/*
        InputStream is = getResources().openRawResource(R.raw.baking);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            is.close();
        }

        String jsonString = writer.toString();

        new BakingService(this).execute(jsonString);*/

    }
}
