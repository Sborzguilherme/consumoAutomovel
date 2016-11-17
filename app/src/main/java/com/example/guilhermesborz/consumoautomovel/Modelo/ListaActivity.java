package com.example.guilhermesborz.consumoautomovel.Modelo;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.GridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import com.example.guilhermesborz.consumoautomovel.Adapter.AbastecimentoAdapter;
import com.example.guilhermesborz.consumoautomovel.DAO.AbastecimentoDAO;
import com.example.guilhermesborz.consumoautomovel.R;

import java.util.ArrayList;

public class ListaActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        ArrayList<Abastecimento> lista = AbastecimentoDAO.obterLista();

        RecyclerView rvListaAbastecimento = (RecyclerView) findViewById(R.id.rvListaAbastecimento);

        RecyclerView.LayoutManager formaApresentacao = new LinearLayoutManager(this.getApplicationContext(),LinearLayoutManager.VERTICAL,false);

        rvListaAbastecimento.setLayoutManager(formaApresentacao);
        AbastecimentoAdapter adaptador = new AbastecimentoAdapter(this.getApplicationContext(), lista);
        rvListaAbastecimento.setAdapter(adaptador);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_all, menu);
        return true;
    }



}

