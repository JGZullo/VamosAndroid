package br.com.vamos.vamos.Services;

import br.com.vamos.vamos.Domain.Credencial;
import br.com.vamos.vamos.Domain.LoginResponse;
import br.com.vamos.vamos.Domain.Passageiro;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PassageiroService {

    @GET("/students/email")
    Call<Passageiro> buscarPassageiro(@Query("value") String email);

    @POST("/login")
    Call<LoginResponse> login(@Body Credencial credencial);
}
