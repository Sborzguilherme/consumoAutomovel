package com.example.guilhermesborz.consumoautomovel.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guilhermesborz.consumoautomovel.Modelo.Abastecimento;
import com.example.guilhermesborz.consumoautomovel.R;

import java.util.ArrayList;


public class AbastecimentoAdapter extends RecyclerView.Adapter {

    private ArrayList<Abastecimento> listaAbastecimento;
    private Context context;

    public AbastecimentoAdapter(Context c, ArrayList<Abastecimento> p){
        this.listaAbastecimento = p;
        this.context = c;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itens_lista,parent,false);
        AbastecimentoViewHolder retorno = new AbastecimentoViewHolder(view);
        return retorno;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AbastecimentoViewHolder elemento = (AbastecimentoViewHolder) holder;
        Abastecimento ab = listaAbastecimento.get(position);
        elemento.seAtualiza(ab);

    }

    @Override
    public int getItemCount() {
        return  this.listaAbastecimento.size();
    }
    public class AbastecimentoViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivIconePosto;
        private TextView tvDataLista;
        private TextView tvQuilometragemLista;
        private TextView tvLitrosLista;

        public AbastecimentoViewHolder(View itemView) {
            super(itemView);

            this.ivIconePosto = (ImageView) itemView.findViewById(R.id.ivIconePosto);
            this.tvDataLista = (TextView) itemView.findViewById(R.id.tvDataLista);
            this.tvQuilometragemLista = (TextView) itemView.findViewById(R.id.tvQuilometragemLista);
            this.tvLitrosLista= (TextView) itemView.findViewById(R.id.tvLitrosLista);

        }

        public void seAtualiza(Abastecimento elementoParaMostrar){
            tvDataLista.setText(elementoParaMostrar.getDataAbastecimento());
            tvLitrosLista.setText("Litros: "+String.valueOf(elementoParaMostrar.getLitrosAbastecidos()));
            tvQuilometragemLista.setText("Km: "+String.valueOf(elementoParaMostrar.getQuilometragemAtual()));

            if(elementoParaMostrar.getPostoEscolhido().equals("Texaco")){
                ivIconePosto.setImageResource(R.drawable.posto1);
            }else if(elementoParaMostrar.getPostoEscolhido().equals("Shell")){
                ivIconePosto.setImageResource(R.drawable.posto2);
            }else if(elementoParaMostrar.getPostoEscolhido().equals("Petrobras")){
                ivIconePosto.setImageResource(R.drawable.posto3);
            }else
                ivIconePosto.setImageResource(R.drawable.posto4);


        }
    }
}
