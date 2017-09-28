package com.example.mauriciocantu.autenticacao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import consumer.UsuarioConsumer;
import pojo.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mauriciocantu on 21/09/17.
 */

public class TelaEditar extends Activity {

    private EditText etNome, etLogin, etSenha;
    private Button btCadastrar;
    private Usuario usuario;
    private UsuarioConsumer uConsumer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_cadastrar);
        inicializaComps();
        btCadastrar.setText("editar");
        etNome.setText(usuario.getNome());
        etLogin.setText(usuario.getLogin());
        etSenha.setText(usuario.getSenha());

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario.setNome(etNome.getText().toString());
                usuario.setLogin(etLogin.getText().toString());
                usuario.setSenha(etSenha.getText().toString());

                uConsumer.putAtualizar(usuario).enqueue(new Callback<Usuario>() {
                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Usuario atualizado!", Toast.LENGTH_LONG).show();
                            Intent itTelaLogado = new Intent(TelaEditar.this, TelaLogado.class);
                            itTelaLogado.putExtra("usuario", usuario);
                            startActivity(itTelaLogado);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "DEU RUIM", Toast.LENGTH_LONG).show();
                    }
                });

            }
        });


    }

    private void inicializaComps(){
        this.etNome = (EditText) findViewById(R.id.et_nome_c);
        this.etLogin = (EditText) findViewById(R.id.et_login_c);
        this.etSenha = (EditText) findViewById(R.id.et_senha_c);
        this.btCadastrar = (Button) findViewById(R.id.bt_cadastrar_c);
        this.uConsumer = new UsuarioConsumer();
        this.usuario = (Usuario) getIntent().getSerializableExtra("usuario");
    }
}
