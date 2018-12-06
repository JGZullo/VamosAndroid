package br.com.vamos.vamos.Services;

import br.com.vamos.vamos.Motorista;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MotoristaService {

    @GET("/drivers/find/email?value={email}")
    Call<Motorista> buscarMotorista(@Path("email")String email);
}
