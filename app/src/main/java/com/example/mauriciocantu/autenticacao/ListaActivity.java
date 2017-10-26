package com.example.mauriciocantu.autenticacao;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import consumer.UsuarioConsumer;
import pojo.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaActivity extends ListActivity {

    private List<Usuario> listaUsuarios;
    private UsuarioConsumer usuarioConsumer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inicializaComps();
    }

    private void criarLista(){
        usuarioConsumer.buscarTodos().enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {

                if(response.isSuccessful()){
                    listaUsuarios = response.body();
                    ArrayAdapter<Usuario> adapterUsuario = new ArrayAdapter<Usuario>(getApplicationContext(), android.R.layout.simple_list_item_1, listaUsuarios);
                    setListAdapter(adapterUsuario);
                }

            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    private void inicializaComps(){
        usuarioConsumer = new UsuarioConsumer();
        criarLista();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent itTelaAction = new Intent(getApplicationContext(), TelaAction.class);
        itTelaAction.putExtra("usuario", listaUsuarios.get(position));
        itTelaAction.putExtra("action","editar");
        startActivity(itTelaAction);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // recria a lista de usuarios
        criarLista();
    }


}
