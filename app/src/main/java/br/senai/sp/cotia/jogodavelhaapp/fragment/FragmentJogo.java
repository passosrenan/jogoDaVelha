package br.senai.sp.cotia.jogodavelhaapp.fragment;

import android.graphics.Color;
import android.location.GnssAntennaInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

import br.senai.sp.cotia.jogodavelhaapp.R;
import br.senai.sp.cotia.jogodavelhaapp.databinding.FragmentJogoBinding;
import br.senai.sp.cotia.jogodavelhaapp.util.PrefUtil;

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

    //variaveis do placar
    private int placarJ1=0, placarJ2=0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //habilita o menu neste fragent
        setHasOptionsMenu(true);
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
        simj1 = PrefUtil.getSimboloJ1(getContext());
        simj2 = PrefUtil.getSimboloJ1(getContext());

        binding.textView.setText(getResources().getString(R.string.jogador1,simj1));
        binding.textView3.setText(getResources().getString(R.string.jogador2,simj2));

        //sorteia quem inicia o jogo
        sorteia();


        return binding.getRoot();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        //verifica qual botao foi clicado no menu

        switch (item.getItemId()){
            case R.id.menu_resetar:
                placarJ2=0;
                placarJ1=0;
                limpaCampos();
                atualizaPlacar();
                break;
            case R.id.menu_pref:
                NavHostFragment.findNavController(FragmentJogo.this).navigate(R.id.action_fragmentJogo_to_fragmentPreferences);
        }

        return true;
    }

    private void sorteia() {
        //nao precisa do igual a true porque o método está implícito
        if (random.nextBoolean()) {
            simboolo = simj1;
        } else {
            simboolo = simj2;
        }

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);

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

    private void atualizaPlacar(){
        binding.textView2.setText(placarJ2+"");
        binding.textView4.setText(placarJ1+"");
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

            if(simboolo.equals(simj1)){
                placarJ1++;
            }else{
                placarJ2++;

            }
            atualizaPlacar();


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