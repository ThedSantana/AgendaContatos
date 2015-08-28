package com.finhane.agendacontato;

import android.app.AlertDialog;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

import com.finhane.agendacontato.database.Database;
import com.finhane.agendacontato.model.Contato;
import com.finhane.agendacontato.service.ContatoService;

import java.util.Date;

public class ActAdicionaContato extends AppCompatActivity {

    private EditText txtNome, txtTelefone, txtEmail, txtEndereco, txtDataEspecial, txtGrupo;
    private Spinner spinnerTipoTelefone, spinnerTipoEmail, spinnerTipoEndereco, spinnerTipoDataEspecial;
    private ArrayAdapter<String> arrTipoTelefone, arrTipoEmail, arrTipoEndereco, arrTipoDataEspecial;

    private Database dataBase;
    private SQLiteDatabase conn;
    private ContatoService contatoService;

    private Contato contato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_adiciona_contato);

        txtNome = (EditText)findViewById(R.id.txtNome);
        txtTelefone = (EditText)findViewById(R.id.txtTelefone);
        spinnerTipoTelefone = (Spinner)findViewById(R.id.spinnerTipoTelefone);
        txtEmail = (EditText)findViewById(R.id.txtEmail);
        spinnerTipoEmail = (Spinner)findViewById(R.id.spinnerTipoEmail);
        txtEndereco = (EditText)findViewById(R.id.txtEndereco);
        spinnerTipoEndereco = (Spinner)findViewById(R.id.spinnerTipoEndereco);
        txtDataEspecial = (EditText)findViewById(R.id.txtDataEspecial);
        spinnerTipoDataEspecial = (Spinner)findViewById(R.id.spinnerTipoDataEspecial);
        txtGrupo = (EditText)findViewById(R.id.txtGrupo);

        arrTipoTelefone = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item);
        arrTipoTelefone.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        arrTipoEmail = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item);
        arrTipoEmail.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        arrTipoEndereco = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item);
        arrTipoEndereco.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        arrTipoDataEspecial = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item);
        arrTipoDataEspecial.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinnerTipoTelefone.setAdapter(arrTipoTelefone);
        spinnerTipoEmail.setAdapter(arrTipoEmail);
        spinnerTipoEndereco.setAdapter(arrTipoEndereco);
        spinnerTipoDataEspecial.setAdapter(arrTipoDataEspecial);

        arrTipoTelefone.add("Celular");
        arrTipoTelefone.add("Casa");
        arrTipoTelefone.add("Trabalho");
        arrTipoTelefone.add("Principal");
        arrTipoTelefone.add("Fax Trabalho");
        arrTipoTelefone.add("Fax Casa");
        arrTipoTelefone.add("Pager");
        arrTipoTelefone.add("Outros");

        arrTipoEmail.add("Pessoal");
        arrTipoEmail.add("Trabalho");
        arrTipoEmail.add("Outros");

        arrTipoEndereco.add("Pessoal");
        arrTipoEndereco.add("Trabalho");
        arrTipoEndereco.add("Outros");

        arrTipoDataEspecial.add("Aniversário");
        arrTipoDataEspecial.add("Data Comemorativa");
        arrTipoDataEspecial.add("Outros");

        dataBase = new Database(this);
        try {
            conn = dataBase.getWritableDatabase();
        }catch (SQLException exc){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Erro ao criar a Base de dados: " + exc.getMessage());
            dlg.setNeutralButton("OK", null);
            dlg.show();
            System.exit(0);
        }
        contatoService = new ContatoService(conn);
//        contato = new Contato();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_act_adiciona_contato, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuSalvar:
                if(null == contato){
                    inserir();
                }else{

                }
                finish();
                break;
            case R.id.menuExcluir:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void inserir(){
        try{
            contato = new Contato();
            contato.setNome(txtNome.getText().toString());

            contato.setTelefone(txtTelefone.getText().toString());
            contato.setTipotelefone(spinnerTipoTelefone.getSelectedItem().toString());

            contato.setEmail(txtEmail.getText().toString());
            contato.setTipoemail(spinnerTipoEmail.getSelectedItem().toString());

            contato.setEndereco(txtEndereco.getText().toString());
            contato.setTipoendereco(spinnerTipoEndereco.getSelectedItem().toString());

            Date date = new Date();
            contato.setDatasespeciais(date);
            contato.setTipodataespeciais(spinnerTipoDataEspecial.getSelectedItem().toString());

            contato.setGrupos(txtGrupo.getText().toString());

            contatoService.inserirContato(contato);
        }catch (Exception ex){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Erro ao inserir os dados: " + ex.getMessage());
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }


    }
}
