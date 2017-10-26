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

public class TelaAction extends AppCompatActivity {

    private EditText etNome, etLogin, etSenha;
    private Button btAction, btDelete;
    private Usuario usuario;
    private UsuarioConsumer uConsumer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_cadastrar);

        inicializaComps();

        switch(getIntent().getStringExtra("action")){
            case "editar":
                btAction.setText("Editar");
                btDelete.setVisibility(View.VISIBLE);
                usuario = (Usuario) getIntent().getSerializableExtra("usuario");
                etNome.setText(usuario.getNome());
                etLogin.setText(usuario.getLogin());
                etSenha.setText(usuario.getSenha());

                this.btAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setUserData();
                        uConsumer.putAtualizar(usuario).enqueue(new Callback<Usuario>() {
                            @Override
                            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                                System.out.println(response.message());
                                System.out.println(response.code());
                                System.out.println(response.body());
                                System.out.println(call.request().url());
                                if(response.isSuccessful()){
                                    Toast.makeText(getApplicationContext(), "Usuario atualizado!", Toast.LENGTH_LONG).show();
                                    /*Intent itTelaLogado = new Intent(getApplicationContext(), TelaLogado.class);
                                    itTelaLogado.putExtra("usuario", usuario);
                                    startActivity(itTelaLogado);*/
                                    finish();
                                }else{
                                    Toast.makeText(getApplicationContext(), "DEU RUIM", Toast.LENGTH_LONG).show();
                                    finish();
                                }

                            }

                            @Override
                            public void onFailure(Call<Usuario> call, Throwable t) {
                                Toast.makeText(getApplicationContext(), "DEU RUIM", Toast.LENGTH_LONG).show();
                                finish();
                            }
                        });
                    }
                });

                this.btDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        uConsumer.deleteUser(usuario.getId()).enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if(response.isSuccessful()){
                                    Toast.makeText(getApplicationContext(), "Usuario excluido", Toast.LENGTH_LONG).show();
                                    Intent itTelaLogin = new Intent(getApplicationContext(), TelaLogin.class);
                                    startActivity(itTelaLogin);
                                    finish();
                                }else{
                                    Toast.makeText(getApplicationContext(), "DEU RUIM", Toast.LENGTH_LONG).show();
                                    finish();
                                }
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                Toast.makeText(getApplicationContext(), "DEU RUIM", Toast.LENGTH_LONG).show();
                                finish();
                            }
                        });
                    }
                });

                break;

            case "cadastrar":
                btAction.setText("cadastrar");

                this.btAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        usuario = new Usuario();
                        setUserData();

                        uConsumer.postCadastrar(usuario).enqueue(new Callback<Usuario>() {
                            @Override
                            public void onResponse(Call<Usuario> call, Response<Usuario> response) {

                                if (response.isSuccessful()){
                                    usuario = response.body();
                                    System.out.println(response.code());
                                    System.out.println(response.body());
                                    System.out.println(response.message());
                                    Intent itTelaLogado = new Intent(getApplicationContext(), TelaLogado.class);
                                    itTelaLogado.putExtra("usuario", usuario);
                                    startActivity(itTelaLogado);
                                    finish();
                                }else{
                                    System.out.println(response.code());
                                    System.out.println(response.message());
                                    Toast.makeText(getApplicationContext(), "Falha ao cadastrar", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }

                            @Override
                            public void onFailure(Call<Usuario> call, Throwable t) {
                                System.out.println(t.getMessage());
                                Toast.makeText(getApplicationContext(), "Falha ao cadastrar", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
                    }
                });
                break;
        }


    }

    private void inicializaComps(){
        this.etNome = (EditText) findViewById(R.id.et_nome_c);
        this.etLogin = (EditText) findViewById(R.id.et_login_c);
        this.etSenha = (EditText) findViewById(R.id.et_senha_c);
        this.btAction = (Button) findViewById(R.id.bt_cadastrar_c);
        this.btDelete = (Button) findViewById(R.id.bt_deletar);
        this.uConsumer = new UsuarioConsumer();
    }

    private void setUserData(){
        usuario.setNome(etNome.getText().toString());
        usuario.setLogin(etLogin.getText().toString());
        usuario.setSenha(etSenha.getText().toString());
    }
}
