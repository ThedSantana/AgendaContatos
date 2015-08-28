package com.finhane.agendacontato.model;

import java.util.Date;

/**
 * Created by felipe on 8/28/15.
 */
public class Contato {
    private long id;
    private String nome, telefone, tipotelefone, email, tipoemail, endereco, tipoendereco, tipodataespeciais, grupos;
    private Date datasespeciais;

    public Contato(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTipotelefone() {
        return tipotelefone;
    }

    public void setTipotelefone(String tipotelefone) {
        this.tipotelefone = tipotelefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipoemail() {
        return tipoemail;
    }

    public void setTipoemail(String tipoemail) {
        this.tipoemail = tipoemail;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTipoendereco() {
        return tipoendereco;
    }

    public void setTipoendereco(String tipoendereco) {
        this.tipoendereco = tipoendereco;
    }

    public String getTipodataespeciais() {
        return tipodataespeciais;
    }

    public void setTipodataespeciais(String tipodataespeciais) {
        this.tipodataespeciais = tipodataespeciais;
    }

    public String getGrupos() {
        return grupos;
    }

    public void setGrupos(String grupos) {
        this.grupos = grupos;
    }

    public Date getDatasespeciais() {
        return datasespeciais;
    }

    public void setDatasespeciais(Date datasespeciais) {
        this.datasespeciais = datasespeciais;
    }
}
