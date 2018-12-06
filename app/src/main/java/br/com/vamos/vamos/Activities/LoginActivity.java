package br.com.vamos.vamos.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.vamos.vamos.Domain.Credencial;
import br.com.vamos.vamos.Domain.LoginResponse;
import br.com.vamos.vamos.Domain.Passageiro;
import br.com.vamos.vamos.R;
import br.com.vamos.vamos.RetrofitConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private Intent trocarTela;
    private EditText login, password;
    private Button entrar, cadastrar;
    private String loginBanco, senhaBanco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        entrar = findViewById(R.id.btnEntrar);
        cadastrar = findViewById(R.id.btnCadastro);
        login = findViewById(R.id.email);
        password = findViewById(R.id.Senha);
        loginBanco = "teste";
        senhaBanco = "teste";

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginRequest();
            }
        });

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trocarTela = new Intent(LoginActivity.this, EscolherPerfilCadastroActivity.class);
                startActivity(trocarTela);
            }
        });
    }

    private void loginRequest() {
        Credencial credencial = new Credencial(login.getText().toString(), password.getText().toString());
        Call<LoginResponse> call = new RetrofitConfig().getPassageiroService().login(credencial);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                AlertDialog.Builder dialogo = new AlertDialog.Builder(LoginActivity.this);
                dialogo.setTitle("Login");
                if (response.code() == 200) {
                    dialogo.setMessage("Login efetuado com sucesso!");
                    dialogo.setNeutralButton("OK", null);
                    dialogo.show();
                }
                else{
                    dialogo.setMessage("Email ou senha incorreta");
                    dialogo.setNeutralButton("OK", null);
                    dialogo.show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("ERRO   ", "Erro" + t.getMessage());
            }
        });
    }

    private void studentRequest() {
        Call<Passageiro> call = new RetrofitConfig().getPassageiroService().buscarPassageiro(login.getText().toString());
        call.enqueue(new Callback<Passageiro>() {
            @Override
            public void onResponse(Call<Passageiro> call, Response<Passageiro> response) {

            }

            @Override
            public void onFailure(Call<Passageiro> call, Throwable t) {
                Log.e("ERRO   ", "Erro" + t.getMessage());
            }
        });
    }
}