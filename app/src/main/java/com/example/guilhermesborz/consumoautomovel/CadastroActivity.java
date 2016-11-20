package com.example.guilhermesborz.consumoautomovel;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.view.View;

import com.example.guilhermesborz.consumoautomovel.DAO.AbastecimentoDAO;
import com.example.guilhermesborz.consumoautomovel.Modelo.Abastecimento;

public class CadastroActivity extends Activity {

    private TextView txtData;
    private int ano, mes, dia;
    private EditText edQuilometragem;
    private EditText edLitros;
    //private TextView tvRecebeData;
    private Spinner spinner;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        txtData = (TextView) findViewById(R.id.tvRecebeData);
        edQuilometragem = (EditText)findViewById(R.id.edQuilometragem);
        edLitros = (EditText) findViewById(R.id.edAbastecidos);
        //txtData = (TextView) findViewById(R.id.tvRecebeData);
        spinner = (Spinner) findViewById(R.id.spinner);


        final Calendar cal = Calendar.getInstance();
        ano = cal.get(Calendar.YEAR);
        mes = cal.get(Calendar.MONTH);
        dia = cal.get(Calendar.DAY_OF_MONTH);

        AtualizarData();

        //SPINNER
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

    }

    public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener    {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState)
        {
            final Calendar calendario = Calendar.getInstance();
            ano = calendario.get(Calendar.YEAR);
            mes = calendario.get(Calendar.MONTH);
            dia = calendario.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, ano, mes, dia);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int day)
        {
            ano = year;
            mes = month;
            dia = day;
            AtualizarData();
            //MensagemData();
        }

        @Override
        public int show(FragmentTransaction transaction, String tag)
        {
            return super.show(transaction, tag);
        }

    }
    private void AtualizarData()
    {
        txtData.setText(new StringBuilder().append(dia).append("/").append(mes + 1).append("/").append(ano).append(" "));
    }

    /*private void MensagemData()
    {
        Toast.makeText(this, new StringBuilder().append("Data: ").append(txtData.getText()),  Toast.LENGTH_SHORT).show();
    }*/

    public void MostrarData(View v)
    {
        DialogFragment ClasseData = new  DatePickerFragment();
        ClasseData.show(getFragmentManager(),  "datepicker");
    }

    public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {


        public void onItemSelected(AdapterView<?> parent, View view,
                                   int pos, long id) {
            // An item was selected. You can retrieve the selected item using
            // parent.getItemAtPosition(pos)
        }

        public void onNothingSelected(AdapterView<?> parent) {
            // Another interface callback
        }
    }
    public boolean validaQuilometragem(EditText edQuilometragemTeste){

        if(AbastecimentoDAO.obterLista().size()>0){
            String quilometragemString = edQuilometragemTeste.getText().toString();
            Float quilometragemFloat = Float.parseFloat(quilometragemString);

            int ultimoAbastecimentoCadastrado = (AbastecimentoDAO.obterLista().size()-1);

            if(AbastecimentoDAO.obterLista().get(ultimoAbastecimentoCadastrado).getQuilometragemAtual()> quilometragemFloat){
                return false;
            }else
                return true;
        }
      return true;
    }
    public boolean validaDataRecebida() throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        Date dataRecebida = sdf.parse(txtData.getText().toString());
        Date dataAtual = new Date();

        if(dataRecebida.after(dataAtual)){
            return false;
        }
        if(AbastecimentoDAO.obterLista().size()>0){

            int ultimoAbastecimento = AbastecimentoDAO.obterLista().size()-1;
            String stringDataLista = AbastecimentoDAO.obterLista().get(ultimoAbastecimento).getDataAbastecimento();
            Date dataUltimoAbastecimento = sdf.parse(stringDataLista);

            if(dataRecebida.before(dataUltimoAbastecimento)){
                return false;
            }
        }
            return true;
    }

    public void clicaConfirmar(View view) throws ParseException {

        if(edQuilometragem.getText().toString().isEmpty()|| edLitros.getText().toString().isEmpty()){
            Toast.makeText(CadastroActivity.this.getApplicationContext(), "Insira Valor", Toast.LENGTH_SHORT).show();

        }else if(!validaQuilometragem(edQuilometragem)){
            Toast.makeText(CadastroActivity.this.getApplicationContext(), "Uma quilometragem maior ja foi digitada", Toast.LENGTH_SHORT).show();
        }else if(!validaDataRecebida()){
            Toast.makeText(CadastroActivity.this.getApplicationContext(), "Data Digitada é Inválida", Toast.LENGTH_SHORT).show();
        }
        else{

            String valorQuilometragem = edQuilometragem.getText().toString();
            String valorLitros = edLitros.getText().toString();
            String valorData = txtData.getText().toString();
            int seletorSpinner = spinner.getSelectedItemPosition();

            Float quilometragem = Float.parseFloat(valorQuilometragem);
            Float litros = Float.parseFloat(valorLitros);

            Abastecimento ab = new Abastecimento();

            ab.setLitrosAbastecidos(litros);
            ab.setQuilometragemAtual(quilometragem);
            ab.setDataAbastecimento(valorData);

            if(seletorSpinner == 0){
                ab.setPostoEscolhido("Texaco");
            }else if(seletorSpinner == 1){
                ab.setPostoEscolhido("Shell");
            }else if(seletorSpinner == 2){
                ab.setPostoEscolhido("Petrobras");
            }else {
                ab.setPostoEscolhido("Ipiranga");
            }

            Intent abridor = new Intent(this,MainActivity.class);
            abridor.putExtra("Abastecimento",ab);
            AbastecimentoDAO.salvar(ab);
            abridor.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(abridor);
            finish();
        }

    }
}
