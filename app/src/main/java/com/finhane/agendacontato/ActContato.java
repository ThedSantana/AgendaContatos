package com.finhane.agendacontato;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.finhane.agendacontato.database.Database;
import com.finhane.agendacontato.service.ContatoService;


public class ActContato extends AppCompatActivity implements View.OnClickListener{

    private ImageButton btnAdicionar;
    private EditText txtPesquisa;
    private ListView listContatos;
    private Database dataBase;
    private SQLiteDatabase conn;
    private ArrayAdapter<String> arrContatos;
    private ContatoService contatoService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_contato);

        txtPesquisa = (EditText)findViewById(R.id.txtPesquisa);
        btnAdicionar = (ImageButton)findViewById(R.id.btnAdicionar);
        listContatos = (ListView)findViewById(R.id.listContatos);

        btnAdicionar.setOnClickListener(this);

        dataBase = new Database(this);

        try {
            conn = dataBase.getReadableDatabase();
        }catch (SQLException exc){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Erro ao criar a Base de dados: " + exc.getMessage());
            dlg.setNeutralButton("OK", null);
            dlg.show();
            System.exit(0);
        }
        contatoService = new ContatoService(conn);
//        contatoService.testeInserirContatos();
        arrContatos = contatoService.listContatos(this);
        listContatos.setAdapter(arrContatos);
    }


    @Override
    public void onClick(View v) {
        Intent it = new Intent(this, ActAdicionaContato.class);
        startActivity(it);
    }
}
