package com.example.mauriciocantu.autenticacao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import consumer.UsuarioConsumer;
import pojo.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TelaCadastro extends AppCompatActivity {

    private EditText etNome, etLogin, etSenha;
    private Button btCadastrar;
    private Usuario usuario;
    private UsuarioConsumer uConsumer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_cadastrar);

        inicializaComps();

        this.btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario = new Usuario();
                usuario.setNome(etNome.getText().toString());
                usuario.setLogin(etLogin.getText().toString());
                usuario.setSenha(etSenha.getText().toString());

                uConsumer.postCadastrar(usuario).enqueue(new Callback<Usuario>() {
                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {

                        if (response.isSuccessful()){
                            Intent itTelaLogado = new Intent();
                            itTelaLogado.putExtra("usuario", usuario);
                            startActivity(itTelaLogado);
                            finish();
                        }

                    }

                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Falha ao cadastrar", Toast.LENGTH_SHORT).show();
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
    }
}
