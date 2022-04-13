package br.senai.sp.cotia.jogodavelhaapp.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PrefUtil {

    public static void salvarSimboloJog1(String simbolo, Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("sim_jog_1", simbolo);
        editor.commit();
    }

    public static String getSimboloJ1(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("simb_jog_1", "x");
    }

    public static String getSimboloJ2(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("simb_jog_2", "x");
    }

    public static String getNomeJ1(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("nome_jog_1", "jogador 1");
    }
    public static String getNomeJ2(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("nome_jog_2", " jogador 2");
    }

}
