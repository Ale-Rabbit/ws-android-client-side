package consumer;

import android.content.Intent;

import java.util.List;

import pojo.Usuario;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Path;

/**
 * Created by mauriciocantu on 31/08/17.
 */

public class UsuarioConsumer {

    private IUsuarioService usuarioService;
    private Retrofit retrofit;

    public UsuarioConsumer(){
        this.retrofit = new Retrofit.
                Builder().
                baseUrl(IUsuarioService.URL_BASE).
                addConverterFactory(GsonConverterFactory.create()).
                build();

        this.usuarioService = retrofit.create(IUsuarioService.class);

    }

    public Call<Usuario> postAutentica(String login, String senha){
        return this.usuarioService.postAutentica(login, senha);
    }

    public Call<Usuario> postCadastrar(Usuario u){
        return this.usuarioService.postCadastrar(u);
    }

    public Call<Usuario> putAtualizar(Usuario u){
        return this.usuarioService.putAtualizar(u);
    }

    public Call<Void> deleteUser(Integer id){
        return this.usuarioService.deleteUser(id);
    }

    public Call<List<Usuario>> buscarTodos(){
        return this.usuarioService.buscarTodos();
    }

    public Call<Usuario> getUser(Integer id) {
        return this.usuarioService.getUsuario(id);
    }

}

