package consumer;

import java.util.List;

import pojo.Usuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by mauriciocantu on 31/08/17.
 */

public interface IUsuarioService {

    // Trocar para o IP do notebook. O celular e o note tem que estar na mesma rede.
    String URL_BASE = "http://192.168.241.113:8080/";

    // Realiza uma requisição do tipo POST com a URI /usuario/login/senha
    // Esse método é utilizado no UsuarioConsumer
    @POST("usuario/{login}/{senha}")
    Call<Usuario> postAutentica(@Path("login") String login, @Path("senha") String senha);

    @POST("usuario/")
    Call<Usuario> postCadastrar(@Body Usuario u);

    @PUT("usuario/")
    Call<Usuario> putAtualizar(@Body Usuario u);

    @DELETE("usuario/{id}")
    Call<Void> deleteUser(@Path("id") Integer id);

    @GET("usuario/")
    Call<List<Usuario>> buscarTodos();

    @GET("usuario/{id}")
    Call<Usuario> getUsuario(@Path("id") Integer id);


}
