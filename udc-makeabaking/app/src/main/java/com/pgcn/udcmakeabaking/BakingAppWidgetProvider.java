package com.pgcn.udcmakeabaking;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.pgcn.udcmakeabaking.model.Baking;
import com.pgcn.udcmakeabaking.service.BakingIntentService;

import org.apache.commons.lang3.StringUtils;

import java.util.Set;

/**
 * Implementation of App Widget functionality.
 */
public class BakingAppWidgetProvider extends AppWidgetProvider {


 /*   static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                Baking baking, int appWidgetId) {

        Intent intent = new Intent(context, BakingTimeActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_app_widget);
        views.removeAllViews(R.id.appwidget_lv_ingredient);
        views.setTextViewText(R.id.appwidget_appname, baking.getName());

        for (Ingredient ingredient : baking.getIngredients()) {
            RemoteViews rvIngredient = new RemoteViews(context.getPackageName(),
                    R.layout.baking_app_widget_ingredientlistitem);
            rvIngredient.setTextViewText(R.id.appwidget_lv_ingredient,
                    String.valueOf(ingredient.getQuantity()) +
                            String.valueOf(ingredient.getMeasure()) + " " + ingredient.getIngredient());
            views.addView(R.id.appwidget_lv_ingredient, rvIngredient);
        }

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }
*/

    public static void updateBakingWidgets(Context context, AppWidgetManager appWidgetManager, Baking
            baking, Set<String> listaTextoIngredientes, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, baking, listaTextoIngredientes, appWidgetId);
        }
    }

    private static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, Baking baking, Set<String> listaTextoIngredientes, int appWidgetId) {
        Intent intent = new Intent(context, BakingTimeActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_app_widget);
        views.setTextViewText(R.id.appwidget_appname, baking.getName());

        StringBuilder lista = new StringBuilder();
        for (String ingredient : listaTextoIngredientes) {
            lista.append(ingredient + StringUtils.CR + StringUtils.LF);
        }
        views.setTextViewText(R.id.appwidget_listaingredientes, lista.toString());

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        //Start the intent service update widget action, the service takes care of updating the widgets UI
        BakingIntentService.startActionUpdateBakingWidgets(context);
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
    }

    @Override
    public void onEnabled(Context context) {
    }

    @Override
    public void onDisabled(Context context) {
    }


}

