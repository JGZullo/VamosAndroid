package br.com.vamos.vamos.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.vamos.vamos.R;

public class EscolherPerfilCadastroActivity extends AppCompatActivity {

    Intent trocarTela;
    Button btnPassageiro, btnMotorista, btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolher_perfil_cadastro);

        btnPassageiro = findViewById(R.id.btnPassageiro);
        btnMotorista = findViewById(R.id.btnMotorista);
        btnVoltar = findViewById(R.id.btnVoltar);

        btnPassageiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                trocarTela = new Intent(EscolherPerfilCadastroActivity.this, CadastroPassageiroActivity.class);
                startActivity(trocarTela);
            }
        });

        btnMotorista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                trocarTela = new Intent(EscolherPerfilCadastroActivity.this, CadastroMotoristaActivity.class);
                startActivity(trocarTela);
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                trocarTela = new Intent(EscolherPerfilCadastroActivity.this, LoginActivity.class);
                startActivity(trocarTela);
            }
        });
    }
}
