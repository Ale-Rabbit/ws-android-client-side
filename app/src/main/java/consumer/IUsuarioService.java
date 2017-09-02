package consumer;

import pojo.Usuario;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by mauriciocantu on 31/08/17.
 */

public interface IUsuarioService {

    static final String URL_BASE = "http://localhost:8080/compubras";

    // Realiza uma requisição do tipo POST com a URI /usuario/login/senha
    // Esse método é utilizado no UsuarioConsumer
    @POST("/usuario/{login}/{senha}")
    Call<Usuario> postAutentica(@Path("login") String login, @Path("senha") String senha);



}