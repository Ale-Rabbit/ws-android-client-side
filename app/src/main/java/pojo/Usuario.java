package pojo;

import android.content.Intent;

import java.io.Serializable;

/**
 * Created by mauriciocantu on 27/07/17.
 */

public class Usuario implements Serializable{

    private String nome, login, senha;
    private Integer id;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.getNome() + " : " + this.getLogin() + " : " + this.getId();
    }
}
