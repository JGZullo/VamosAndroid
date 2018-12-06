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

import br.com.vamos.vamos.Passageiro;
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

        entrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                AlertDialog.Builder dialogo = new AlertDialog.Builder(LoginActivity.this);
                dialogo.setTitle("Login");
                if(loginBanco.equals(login.getText().toString()) && senhaBanco.equals(password.getText().toString())){
                    dialogo.setMessage("Login efetuado com sucesso!");

                    dialogo.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            trocarTela = new Intent(LoginActivity.this, PrincipalActivity.class);
                            startActivity(trocarTela);
                        }
                    });

                    dialogo.show();
                }
                else {
                    if (!loginBanco.equals(login.getText().toString())) {
                        dialogo.setMessage("Login não foi efetuado: e-mail incorreto!");
                        dialogo.setNeutralButton("OK", null);
                        dialogo.show();
                    }
                    if (!senhaBanco.equals(password.getText().toString())) {
                        dialogo.setMessage("Login não foi efetuado: senha incorreta!");
                        dialogo.setNeutralButton("OK", null);
                        dialogo.show();
                    }
                }
            }
        });

        cadastrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                trocarTela = new Intent(LoginActivity.this, EscolherPerfilCadastroActivity.class);
                startActivity(trocarTela);
            }
        });
    }
}

/*Call<Passageiro> call = new RetrofitConfig().getPassageiroService().buscarPassageiro(login.getText().toString());
                call.enqueue(new Callback<Passageiro>() {
                    @Override
                    public void onResponse(Call<Passageiro> call, Response<Passageiro> response) {
                        Passageiro passageiro = response.body();

                        AlertDialog.Builder dialogo = new AlertDialog.Builder(LoginActivity.this);
                        dialogo.setTitle("Login");
                        if(passageiro.getEmail().equals(login.getText().toString()) && passageiro.getPassword().equals(password.getText().toString())){
                            dialogo.setMessage("Login efetuado com sucesso!");

                            dialogo.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    trocarTela = new Intent(LoginActivity.this, PrincipalActivity.class);
                                    startActivity(trocarTela);
                                }
                            });

                            dialogo.show();
                        }
                        else {
                            if (!passageiro.getEmail().equals(login.getText().toString())) {
                                dialogo.setMessage("Login não foi efetuado: e-mail incorreto!");
                                dialogo.setNeutralButton("OK", null);
                                dialogo.show();
                            }
                            if (!passageiro.getPassword().equals(password.getText().toString())) {
                                dialogo.setMessage("Login não foi efetuado: senha incorreta!");
                                dialogo.setNeutralButton("OK", null);
                                dialogo.show();
                            }
                        }
                    }
                });*/