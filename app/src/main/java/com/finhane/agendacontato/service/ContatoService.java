package com.finhane.agendacontato.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import com.finhane.agendacontato.model.Contato;

/**
 * Created by felipe on 8/27/15.
 */
public class ContatoService {

    private SQLiteDatabase conn;

    public ContatoService(SQLiteDatabase conn){
        this.conn = conn;
    }

    public void inserirContato(Contato contato){
        ContentValues values = new ContentValues();
        values.put("NOME", contato.getNome());
        values.put("TELEFONE", contato.getTelefone());
        values.put("TIPOTELEFONE", contato.getTipotelefone());
        values.put("EMAIL", contato.getEmail());
        values.put("TIPOEMAIL", contato.getTipoemail());
        values.put("ENDERECO", contato.getEndereco());
        values.put("TIPOENDERECO", contato.getTipoendereco());
        values.put("DATASESPECIAIS", contato.getDatasespeciais().getTime());
        values.put("TIPODATASESPECIAIS", contato.getTipodataespeciais());
        values.put("GRUPOS", contato.getGrupos());
        conn.insertOrThrow("CONTATO", null, values);
    }

    /**
     * Método para inserir dados automaticamente
     */
    public void testeInserirContatos(){
        for (int i = 0; i <= 10; i++) {
            ContentValues values = new ContentValues();
            values.put("TELEFONE", i+100000);
            conn.insertOrThrow("CONTATO", null , values);
        }


    }

    public ArrayAdapter<String> listContatos(Context context){
        ArrayAdapter<String> arrRetornoContatos;
        arrRetornoContatos = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1);

        // conn.rawQuery(); #PASSAR COMANDO SQL
        Cursor cursor = conn.query("CONTATO",null,null,null,null,null,null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0){
            do {
                arrRetornoContatos.add(cursor.getString(2));
            }while (cursor.moveToNext());
        }
        return arrRetornoContatos;
    }
}
