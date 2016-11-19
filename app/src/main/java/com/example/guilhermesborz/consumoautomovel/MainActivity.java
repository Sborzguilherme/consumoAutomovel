package com.example.guilhermesborz.consumoautomovel;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.guilhermesborz.consumoautomovel.DAO.AbastecimentoDAO;
import com.example.guilhermesborz.consumoautomovel.Modelo.Abastecimento;

public class MainActivity extends Activity {

    private TextView tvMostraResultadoConsumo;
    private TextView tvQuilometragemInicial;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvMostraResultadoConsumo = (TextView)findViewById(R.id.tvMostraResultadoConsumo);

        if(AbastecimentoDAO.obterLista().size()<1){
            tvMostraResultadoConsumo.setText("Sem Histórico de Abastecimento");
        }else if(AbastecimentoDAO.obterLista().size()<2){
            tvMostraResultadoConsumo.setText("Cadastre mais um abastecimento");
        }else{

            int i = AbastecimentoDAO.obterLista().size()-1;

            double quilometragem = AbastecimentoDAO.obterLista().get(i).getQuilometragemAtual() - AbastecimentoDAO.obterLista().get(i-1).getQuilometragemAtual();
            double litros = AbastecimentoDAO.obterLista().get(i-1).getLitrosAbastecidos();


            double resultado = (quilometragem/litros);

           String valor = String.format("%.2f",resultado);

            tvMostraResultadoConsumo.setText(valor+" Km/L");

             }
        }

    public void clicaAdicionarAbastecimento(View view){
        Intent abridor = new Intent(this,CadastroActivity.class);
        startActivity(abridor);

    }
    public void clicaVisualizarLista(View view){
        Intent abridor = new Intent(this,ListaActivity.class);
        startActivity(abridor);

    }

}
