package com.pgcn.udcmakeabaking.util;

import com.pgcn.udcmakeabaking.model.Step;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Giselle on 20/02/2018.
 */

public class BakingUtils {

    public static ArrayList<Step> ordenaStepsPorId(ArrayList<Step> listaSteps) {
        // ordena a lista pela id dos steps
        Collections.sort(listaSteps, new Comparator<Step>() {
            @Override
            public int compare(Step stp1, Step stp2) {
                // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                return stp1.getId() > stp2.getId() ? 1 : (stp1.getId() < stp2.getId()) ? -1 : 0;
            }

        });
        return listaSteps;
    }
}
