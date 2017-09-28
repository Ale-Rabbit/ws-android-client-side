package consumer;

import pojo.Usuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by mauriciocantu on 31/08/17.
 */

public interface IUsuarioService {

    static final String URL_BASE = "http://192.168.241.54:8080/compubras";

    // Realiza uma requisição do tipo POST com a URI /usuario/login/senha
    // Esse método é utilizado no UsuarioConsumer
    @POST("/usuario/{login}/{senha}")
    Call<Usuario> postAutentica(@Path("login") String login, @Path("senha") String senha);

    @POST("/usuario/")
    Call<Usuario> postCadastrar(@Body Usuario u);

    @PUT("/usuario/")
    Call<Usuario> putAtualizar(@Body Usuario u);


}
