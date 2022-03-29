package br.senai.sp.cotia.jogodavelhaapp.fragment;

import android.graphics.Color;
import android.location.GnssAntennaInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

import br.senai.sp.cotia.jogodavelhaapp.R;
import br.senai.sp.cotia.jogodavelhaapp.databinding.FragmentJogoBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentJogo}#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentJogo extends Fragment {
    //declarando para acessar os elementos no Layout

    private FragmentJogoBinding binding;

    //vetor para agrupar botoes
    private Button[] botoes;

    //variável que representa o tabuleiro

    private String[][] tabuleiro;

    //varivel para os simbolos
    private String simj1, simj2, simboolo;

    //variavel Random para sortear quem começa
    private Random random;

    //variável para contar o numero de jogadas
    private int numJogadas = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //instanciando o binding
        binding = FragmentJogoBinding.inflate(inflater, container, false);

        //instanciando vetor
        botoes = new Button[9];
        //agrupando os botoes no vetor
        botoes[0] = binding.bt00;
        botoes[1] = binding.bt01;
        botoes[2] = binding.bt02;
        botoes[3] = binding.bt10;
        botoes[4] = binding.bt11;
        botoes[5] = binding.bt12;
        botoes[6] = binding.bt20;
        botoes[7] = binding.bt21;
        botoes[8] = binding.bt22;

        //associa o listener aos botoes
        for (Button bt : botoes)
            bt.setOnClickListener(listenerBotoes);

        //inicializa o tabuleiro
        tabuleiro = new String[3][3];

        //preencher tabuleiro com ""
        for (String[] vetor : tabuleiro) {
            Arrays.fill(vetor, "");
        }

        //instancia o ramdom;
        random = new Random();

        //define os símbolos dos jogadores
        simj1 = "x";
        simj2 = "O";

        //sorteia quem inicia o jogo
        sorteia();


        return binding.getRoot();

    }

    private void sorteia() {
        //nao precisa do igual a true porque o método está implícito
        if (random.nextBoolean()) {
            simboolo = simj1;
        } else {
            simboolo = simj2;
        }

    }

    private void atualizaVez() {
        //verifica de quem é a vez e pinta o placar do jogoador em questao
        if (simboolo.equals(simj1)) {
            binding.LinearJ1.setBackgroundColor(getResources().getColor(R.color.backu));
            binding.LinearJ2.setBackgroundColor(getResources().getColor(R.color.white));
        } else {
            binding.LinearJ1.setBackgroundColor(getResources().getColor(R.color.white));
            binding.LinearJ2.setBackgroundColor(getResources().getColor(R.color.backu));
        }
    }

    private boolean venceu() {


        //verifica se venceu nas linhas
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[i][0].equals(simboolo)
                    && tabuleiro[i][1].equals(simboolo)
                    && tabuleiro[i][2].equals(simboolo)) {
                return true;
            }
        }
        //verifica se venceu nas colunas
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[0][i].equals(simboolo)
                    && tabuleiro[1][i].equals(simboolo)
                    && tabuleiro[2][i].equals(simboolo)) {
                return true;
            }
        }
        //verifica se venceu nas diagonais
        if (tabuleiro[0][0].equals(simboolo)
                && tabuleiro[1][1].equals(simboolo)
                && tabuleiro[2][2].equals(simboolo)) {
            return true;
        }
        if (tabuleiro[0][2].equals(simboolo)
                && tabuleiro[1][1].equals(simboolo)
                && tabuleiro[2][0].equals(simboolo)) {
            return true;
        }

        return false;

    }
    private void limpaCampos(){
        for(String[] vetor: tabuleiro){
            Arrays.fill(vetor, "");
        }


        for(Button bt:botoes){
            bt.setBackgroundColor(getResources().getColor(R.color.purple_200));
            bt.setText("");
            bt.setClickable(true);

        }
        sorteia();
        atualizaVez();
        numJogadas=0;


    }



    //metodo que associa um evento aos botoes pressionados, ou seja reconhece os botões
    private View.OnClickListener listenerBotoes = btPressionado -> {
        //incrementa as jogadas
        numJogadas++;

        //pega o nome do botao
        String nomeBotao = getContext().getResources().getResourceName(btPressionado.getId());

        //estrai os dois ultimos caracteres do nome do botao
        String posicao = nomeBotao.substring(nomeBotao.length() - 2);

        //extrai a posição em linha e coluna
        int linha = Character.getNumericValue(posicao.charAt(0));
        int coluna = Character.getNumericValue(posicao.charAt(1));

        //marca no tabuleiro o simbolo que foi jogado
        tabuleiro[linha][coluna] = simboolo;


        //casting view pra button
        Button botao = (Button) btPressionado;

        //troca o texto do botao clicado
        botao.setText(simboolo);


        //desabilitar o botao
        botao.setClickable(false);
        botao.setBackgroundColor(getResources().getColor(R.color.back));

        //verifica se venceu
        if (numJogadas >= 5 && venceu()) {
            //exibe um Toast informando que o jogador venceu
            Toast.makeText(getContext(),R.string.vencedor, Toast.LENGTH_LONG).show();
            limpaCampos();
        }else if(numJogadas == 9){
            Toast.makeText(getContext(), R.string.velha, Toast.LENGTH_LONG).show();
            limpaCampos();
        }else{
            //inverter a vez
            simboolo = simboolo == simj1 ? simj2 : simj1;

            atualizaVez();
        }






    };

}