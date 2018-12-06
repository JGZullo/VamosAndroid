package br.com.vamos.vamos.Services;

import br.com.vamos.vamos.Passageiro;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PassageiroService {

    @GET("/students/find/email?value={email}/")
    Call<Passageiro> buscarPassageiro(@Path("email")String email);
}
