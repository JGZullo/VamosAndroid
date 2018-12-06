package br.com.vamos.vamos.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.vamos.vamos.R;

public class CadastroPassageiroActivity extends AppCompatActivity {

    Intent trocarTela;
    TextInputLayout til;
    Spinner spinInstituicao, spinPeriodo;
    Button cadastro, limpar, voltar;
    EditText nome, dataNasc, endereco, tel1, tel2, email, senha;
    String regex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_passageiro);

        cadastro = findViewById(R.id.btnCadastro);
        limpar = findViewById(R.id.btnLimpar);
        voltar = findViewById(R.id.btnVoltar);
        nome = findViewById(R.id.edNome);
        dataNasc = findViewById(R.id.edDataNasc);
        endereco  = findViewById(R.id.edEndereco);
        tel1  = findViewById(R.id.edTel1);
        tel2  = findViewById(R.id.edTel2);
        email  = findViewById(R.id.edEmail);
        senha  = findViewById(R.id.edSenha);

        limpar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                nome.setText("");
                dataNasc.setText("");
                endereco.setText("");
                tel1.setText("");
                tel2.setText("");
                email.setText("");
                senha.setText("");
            }
        });

        voltar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                trocarTela = new Intent(CadastroPassageiroActivity.this, EscolherPerfilCadastroActivity.class);
                startActivity(trocarTela);
            }
        });

        cadastro.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                boolean sucesso = true;

                if(!validarNome(nome.getText().toString())){
                    til = findViewById(R.id.edLayoutNome);
                    til.setError("O nome deve conter apenas letras");
                    sucesso = false;
                }
                if(!validarDataNasc(dataNasc.getText().toString())) {
                    til = findViewById(R.id.edLayoutDataNasc);
                    til.setError("Formato de data inválido");
                    sucesso = false;
                }
                if(!validarTelefone1(tel1.getText().toString())){
                    til = findViewById(R.id.edLayoutTel1);
                    til.setError("Telefone inválido");
                    sucesso = false;
                }
                if(!validarTelefone2(tel2.getText().toString())){
                    til = findViewById(R.id.edLayoutTel2);
                    til.setError("Telefone inválido");
                    sucesso = false;
                }
                if(!validarEmail(email.getText().toString())){
                    til = findViewById(R.id.edLayoutEmail);
                    til.setError("Email inválido");
                    sucesso = false;
                }
                if(!validarSenha(senha.getText().toString())){
                    til = findViewById(R.id.edLayoutSenha);
                    til.setError("Senha deve conter entre 6 e 12 caracteres.");
                    sucesso = false;
                }

                if(sucesso) {
                    AlertDialog.Builder dialogo = new AlertDialog.Builder(CadastroPassageiroActivity.this);
                    dialogo.setTitle("Cadastro");
                    dialogo.setMessage("Cadastro realizado com sucesso!");
                    dialogo.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            trocarTela = new Intent(CadastroPassageiroActivity.this, PrincipalActivity.class);
                            startActivity(trocarTela);
                            finish();
                        }
                    });

                    dialogo.show();
                }
            }
        });
    }

    private boolean validarNome(String nome){
        regex = "^[\\p{L} .'-]+$";
        Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(nome);
        return matcher.find();
    }

    private static boolean validarDataNasc(String data) {
        return data.matches("^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$/g");
    }

    private static boolean validarTelefone1(String tel){
        return tel.matches("/\\(\\d\\d\\)+\\s+\\d{7,11}|\\d\\d+\\s+\\d{8,11}/g");
    }

    private static boolean validarTelefone2(String tel){
        if(tel.matches("/\\(\\d\\d\\)+\\s+\\d{7,11}|\\d\\d+\\s+\\d{8,11}/g") || tel == "")
            return true;

        return false;
    }

    private static boolean validarEmail(String email){
        return email.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$/g");
    }

    private static boolean validarSenha(String senha) {
        if (senha.length() > 5 && senha.length() < 20)
            return true;

        return false;
    }
}
