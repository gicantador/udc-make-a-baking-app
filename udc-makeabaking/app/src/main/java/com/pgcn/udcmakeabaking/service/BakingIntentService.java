package com.pgcn.udcmakeabaking.service;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.pgcn.udcmakeabaking.BakingAppWidgetProvider;
import com.pgcn.udcmakeabaking.model.Baking;
import com.pgcn.udcmakeabaking.model.Ingredient;
import com.pixplicity.easyprefs.library.Prefs;

import org.apache.commons.lang3.StringUtils;

import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Giselle on 14/03/2018.
 */

public class BakingIntentService extends IntentService {
    public static final String ACTION_UPDATE_BAKING_WIDGETS = "com.pgcn.udcmakeabaking.action.update_baking_widgets";
    private static final String TAG = BakingIntentService.class.getSimpleName();

    public BakingIntentService() {
        super("BakingIntentService");
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public BakingIntentService(String name) {
        super(name);
    }

    /**
     * Starts this service to perform UpdatePlantWidgets action with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    public static void startActionUpdateBakingWidgets(Context context) {
        Intent intent = new Intent(context, BakingIntentService.class);
        intent.setAction(ACTION_UPDATE_BAKING_WIDGETS);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(TAG, "onHandleIntent");

        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_UPDATE_BAKING_WIDGETS.equals(action)) {
                handleActionUpdateBakingWidgets();
            }
        }
    }

    /**
     * Handle action UpdatePlantWidgets in the provided background thread
     */
    private void handleActionUpdateBakingWidgets() {

        Log.d(TAG, "handleActionUpdateBakingWidgets");

        Context ctx = getApplicationContext();
        // Initialize the Prefs class
        new Prefs.Builder()
                .setContext(ctx)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(Baking.PREFERENCE_NAME)
                .setUseDefaultSharedPreference(true)
                .build();

        String bakingName = Prefs.getString(Baking.KEY_BAKING_NAME, StringUtils.EMPTY);
        String bakingImage = Prefs.getString(Baking.KEY_BAKING_IMAGE, StringUtils.EMPTY);
        Set<String> listaTextoIngredientes = new TreeSet<String>();
        listaTextoIngredientes = Prefs.getOrderedStringSet(Ingredient.KEY_INGREDIENT_LIST,
                listaTextoIngredientes);

        Baking baking = new Baking();
        baking.setName(bakingName);
        baking.setImage(bakingImage);
        Log.d(TAG, "bakingName: " + bakingName);


        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, BakingAppWidgetProvider.class));
        //Now update all widgets
        BakingAppWidgetProvider.updateBakingWidgets(this, appWidgetManager, baking,
                listaTextoIngredientes,
                appWidgetIds);

    }

}
