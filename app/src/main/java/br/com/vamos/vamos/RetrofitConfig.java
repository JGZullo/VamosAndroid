package br.com.vamos.vamos;

import br.com.vamos.vamos.Services.MotoristaService;
import br.com.vamos.vamos.Services.PassageiroService;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfig {

    private final Retrofit retrofit;

    public RetrofitConfig() {
        this.retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:8080")
                        .addConverterFactory(JacksonConverterFactory.create())
                        .build();
    }

    public MotoristaService getMotoristaService(){
        return this.retrofit.create(MotoristaService.class);
    }

    public PassageiroService getPassageiroService(){
        return this.retrofit.create(PassageiroService.class);
    }
}
