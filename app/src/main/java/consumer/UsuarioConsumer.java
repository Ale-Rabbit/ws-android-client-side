package consumer;

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

}
