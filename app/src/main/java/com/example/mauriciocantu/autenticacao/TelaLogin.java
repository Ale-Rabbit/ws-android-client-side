package com.example.mauriciocantu.autenticacao;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import consumer.UsuarioConsumer;
import pojo.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TelaLogin extends AppCompatActivity {

    private EditText etLogin, etSenha;
    private Button btLogin;
    private Usuario usuario;
    private TextView tvCriarConta;
    private SharedPreferences spLogin;
    private SharedPreferences.Editor editor;
    public static final String NOME_ARQUIVO = "preferences";
    private UsuarioConsumer usuarioConsumer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_login);

        inicializaComponentes();

        if (verificaLogin()){
            Intent itLogar = new Intent(getApplicationContext(), TelaLogado.class);
            startActivity(itLogar);
            finish();
        }

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                usuario.setLogin(etLogin.getText().toString());
                usuario.setSenha(etSenha.getText().toString());
                validaLogin(usuario.getLogin(), usuario.getSenha());

            }
        });

        tvCriarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itTelaCadastro = new Intent(TelaLogin.this, TelaCadastro.class);
                startActivity(itTelaCadastro);
                finish();
            }
        });

    }

    private boolean verificaLogin(){
        boolean logou = false;
        String login = spLogin.getString("login", null);
        if (login!=null){
            logou = true;
        }
        return logou;
    }

    private void inicializaComponentes(){
        this.etLogin = (EditText) findViewById(R.id.et_login);
        this.etSenha = (EditText) findViewById(R.id.et_senha);
        this.btLogin = (Button) findViewById(R.id.bt_logar);
        this.usuario = new Usuario();
        this.spLogin = getApplicationContext().getSharedPreferences(NOME_ARQUIVO, MODE_APPEND);
        this.editor = spLogin.edit();
        this.tvCriarConta = (TextView) findViewById(R.id.tv_criar);
        this.usuarioConsumer = new UsuarioConsumer();
    }

    private Usuario validaLogin(String login, String senha){

        this.usuarioConsumer.postAutentica(login, senha).enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.isSuccessful()){
                    editor.putString("login", usuario.getLogin());
                    editor.commit();
                    Intent itTelaLogado = new Intent(getApplicationContext(), TelaLogado.class);
                    itTelaLogado.putExtra("usuario", usuario);
                    startActivity(itTelaLogado);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {

            }
        });

        return usuario;
    }


}
