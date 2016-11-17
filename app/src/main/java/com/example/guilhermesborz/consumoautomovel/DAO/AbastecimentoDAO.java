package com.example.guilhermesborz.consumoautomovel.DAO;

import com.example.guilhermesborz.consumoautomovel.Modelo.Abastecimento;

import java.util.ArrayList;

/**
 * Created by Micro Solution on 17/11/2016.
 */

public class AbastecimentoDAO {

    private static ArrayList<Abastecimento> listaAbastecimento;

    private static void inicializarLista(){
        if(AbastecimentoDAO.listaAbastecimento == null){
            AbastecimentoDAO.listaAbastecimento = new ArrayList<>();
        }
    }
    public static void salvar(Abastecimento abastecimento){
        inicializarLista();
        listaAbastecimento.add(abastecimento);

    }

    public static ArrayList<Abastecimento> obterLista(){
        inicializarLista();
        return listaAbastecimento;
    }
}
