package com.example.mauriciocantu.autenticacao;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import pojo.Usuario;

import static com.example.mauriciocantu.autenticacao.TelaLogin.NOME_ARQUIVO;

public class TelaLogado extends AppCompatActivity {

    private TextView tvLogado;
    private Button btDeslogar, btEditar;
    private Usuario usuario;
    private SharedPreferences spLogin;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_logado);
        inicializaComponentes();

        tvLogado.setText("Bem vindo, " + usuario.getNome());

        if(getIntent().getSerializableExtra("usuario") != null){
            usuario = (Usuario) getIntent().getSerializableExtra("usuario");
            tvLogado.setText(usuario.getLogin());
        }else{
            tvLogado.setText(spLogin.getString("login", "guest"));
        }

        this.btDeslogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.clear();
                editor.commit();
                Intent itTelaLogin = new Intent(getApplicationContext(), TelaLogin.class);
                startActivity(itTelaLogin);
                finish();
            }
        });

        this.btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itTelaEditar = new Intent(TelaLogado.this, TelaEditar.class);
                itTelaEditar.putExtra("usuario", usuario);
                startActivity(itTelaEditar);
            }
        });
    }

    private void inicializaComponentes(){
        this.tvLogado = (TextView) findViewById(R.id.tv_logado);
        this.btDeslogar = (Button) findViewById(R.id.bt_deslogar);
        this.btEditar = (Button) findViewById(R.id.bt_editar);
        this.usuario = (Usuario) getIntent().getSerializableExtra("usuario");
        this.spLogin = getApplicationContext().getSharedPreferences(NOME_ARQUIVO, MODE_APPEND);
        this.editor = spLogin.edit();
    }
}
