package br.senai.sp.cotia.jogodavelhaapp.fragment;

import android.location.GnssAntennaInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import br.senai.sp.cotia.jogodavelhaapp.R;
import br.senai.sp.cotia.jogodavelhaapp.databinding.FragmentJogoBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentJogo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentJogo extends Fragment {
    //declarando para acessar os elementos no Layout

    private FragmentJogoBinding binding;

    //vetor para agrupar botoes
    private Button[] botoes;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //instanciando o binding
        binding = FragmentJogoBinding.inflate(inflater, container, false);

        //instanciando vetor
        botoes = new Button[9];
        //agrupando os botoes no vetor
        botoes[0]= binding.bt00;
        botoes[1]= binding.bt01;
        botoes[2]= binding.bt02;
        botoes[3]= binding.bt10;
        botoes[4]= binding.bt11;
        botoes[5]= binding.bt12;
        botoes[6]= binding.bt20;
        botoes[7]= binding.bt21;
        botoes[8]= binding.bt22;

        //associa o listener aos botoes
        for(Button bt: botoes)
            bt.setOnClickListener(listenerBotoes);



        return binding.getRoot();

    }
    //metodo que associa um evento aos botoes pressionados, ou seja reconhece os botões
    private View.OnClickListener listenerBotoes = btPressionado ->{
        //pega o nome do botao
        String nomeBotao = getContext().getResources().getResourceName(btPressionado.getId());

        //estrai os dois ultimos caracteres do nome do botao
        String posicao = nomeBotao.substring(nomeBotao.length()-2);

        //extrai a posição em linha e coluna
        int linha = Character.getNumericValue(posicao.charAt(0));
        int coluna = Character.getNumericValue(posicao.charAt(0));
        
    };

}