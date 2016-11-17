package com.example.guilhermesborz.consumoautomovel.Modelo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.guilhermesborz.consumoautomovel.Modelo.CadastroActivity;
import com.example.guilhermesborz.consumoautomovel.Modelo.ListaActivity;
import com.example.guilhermesborz.consumoautomovel.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
