package br.senai.sp.cotia.jogodavelhaapp.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import br.senai.sp.cotia.jogodavelhaapp.R;
import br.senai.sp.cotia.jogodavelhaapp.databinding.FragmentInicioBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentInicio#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentInicio extends Fragment {

    private FragmentInicioBinding binding;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentInicioBinding.inflate(inflater, container, false);
        binding.btInicio.setOnClickListener(v -> {
            NavHostFragment.findNavController(FragmentInicio.this).navigate(R.id.action_fragmentInicio_to_fragmentJogo);

            binding.textNome1.getText();



        });
        return binding.getRoot();
    }



    /*
    public static void getNomeJ1(String jogador1, Context context) {
        SharedPreferences shared = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = shared.edit();
        editor.putString("nome_jog_1", jogador1);
        editor.commit();
    }

    public static void getNomeJ2(String jogador2, Context context) {
        SharedPreferences shared = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = shared.edit();
        editor.putString("nome_jog_2", jogador2);
        editor.commit();
    }

     */


    @Override
    public void onStart() {
        super.onStart();
        //sumindo com a tolbar
        //pega uma referencia do tipo appCompactActivity
        AppCompatActivity myActivity = (AppCompatActivity) getActivity();

        myActivity.getSupportActionBar().hide();
    }
}