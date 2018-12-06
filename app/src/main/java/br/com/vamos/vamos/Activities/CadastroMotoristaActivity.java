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

import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.vamos.vamos.R;

public class CadastroMotoristaActivity extends AppCompatActivity {

    private Intent trocarTela;
    private Button cadastrar, limpar, voltar;
    private EditText nome, dataNasc, cnh, cpfOuCnpj, tel1, tel2, email, senha;
    private String regex;
    private TextInputLayout til;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_motorista);

        cadastrar = findViewById(R.id.btnCadastro);
        limpar = findViewById(R.id.btnLimpar);
        voltar = findViewById(R.id.btnVoltar);
        nome = findViewById(R.id.edNome);
        dataNasc = findViewById(R.id.edDataNasc);
        cnh  = findViewById(R.id.edEndereco);
        cpfOuCnpj  = findViewById(R.id.edCPFouCNPJ);
        tel1  = findViewById(R.id.edTel1);
        tel2  = findViewById(R.id.edTel2);
        email  = findViewById(R.id.edEmail);
        senha  = findViewById(R.id.edSenha);

        limpar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                nome.setText("");
                dataNasc.setText("");
                cnh.setText("");
                cpfOuCnpj.setText("");
                tel1.setText("");
                tel2.setText("");
                email.setText("");
                senha.setText("");
            }
        });

        voltar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                trocarTela = new Intent(CadastroMotoristaActivity.this, EscolherPerfilCadastroActivity.class);
                startActivity(trocarTela);
                finish();
            }
        });

        cadastrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                boolean sucesso = true;

                resetarErros();

                if(!validarNome(nome.getText().toString())){
                    til = findViewById(R.id.edLayoutNome);
                    til.setErrorEnabled(true);
                    til.setError("O nome deve conter apenas letras");
                    sucesso = false;
                }
                if(!validarDataNasc(dataNasc.getText().toString())) {
                    til = findViewById(R.id.edLayoutDataNasc);
                    til.setErrorEnabled(true);
                    til.setError("Formato de data inválido");
                    sucesso = false;
                }
                if(!validarCPFouCNPJ(cpfOuCnpj.getText().toString())){
                    til = findViewById(R.id.edLayoutCPFouCNPJ);
                    til.setErrorEnabled(true);
                    til.setError("CPF ou CNPJ inválidos");
                    sucesso = false;
                }
                if(!validarTelefone1(tel1.getText().toString())){
                    til = findViewById(R.id.edLayoutTel1);
                    til.setErrorEnabled(true);
                    til.setError("Telefone inválido");
                    sucesso = false;
                }
                if(!validarTelefone2(tel2.getText().toString())){
                    til = findViewById(R.id.edLayoutTel2);
                    til.setErrorEnabled(true);
                    til.setError("Telefone inválido");
                    sucesso = false;
                }
                if(!validarEmail(email.getText().toString())){
                    til = findViewById(R.id.edLayoutEmail);
                    til.setErrorEnabled(true);
                    til.setError("Email inválido");
                    sucesso = false;
                }
                if(!validarSenha(senha.getText().toString())){
                    til = findViewById(R.id.edLayoutSenha);
                    til.setErrorEnabled(true);
                    til.setError("Senha deve conter entre 6 e 12 caracteres.");
                    sucesso = false;
                }

                if(sucesso) {
                    AlertDialog.Builder dialogo = new AlertDialog.Builder(CadastroMotoristaActivity.this);
                    dialogo.setTitle("Cadastro");
                    dialogo.setMessage("Cadastro realizado com sucesso!");
                    dialogo.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            trocarTela = new Intent(CadastroMotoristaActivity.this, PrincipalActivity.class);
                            startActivity(trocarTela);
                            finish();
                        }
                    });

                    dialogo.show();
                }
            }
        });
    }

    private void resetarErros(){
        til = findViewById(R.id.edLayoutNome);
        til.setError(null);
        til.setErrorEnabled(false);

        til = findViewById(R.id.edLayoutDataNasc);
        til.setError(null);
        til.setErrorEnabled(false);

        til = findViewById(R.id.edLayoutCPFouCNPJ);
        til.setError(null);
        til.setErrorEnabled(false);

        til = findViewById(R.id.edLayoutTel1);
        til.setError(null);
        til.setErrorEnabled(false);

        til = findViewById(R.id.edLayoutTel2);
        til.setError(null);
        til.setErrorEnabled(false);

        til = findViewById(R.id.edLayoutEmail);
        til.setError(null);
        til.setErrorEnabled(false);

        til = findViewById(R.id.edLayoutSenha);
        til.setError(null);
        til.setErrorEnabled(false);
    }

    private boolean validarNome(String nome){
        regex = "^[\\p{L} .'-]+$";
        Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(nome);
        return matcher.find();
    }

    private static boolean validarDataNasc(String data) {
        return data.matches("^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$");
    }

    private static boolean validarTelefone1(String tel){
        return tel.matches("/\\(\\d\\d\\)+\\s+\\d{7,11}|\\d\\d+\\s+\\d{8,11}");
    }

    private static boolean validarTelefone2(String tel){
        if(tel.matches("/\\(\\d\\d\\)+\\s+\\d{7,11}|\\d\\d+\\s+\\d{8,11}"))
            return true;
        if(tel.matches(""))
            return true;

        return false;
    }

    private static boolean validarEmail(String email){
        return email.matches("/\\w+@[a-zA-Z]+\\.com/g");
    }

    private static boolean validarSenha(String senha) {
        if (senha.length() > 5 && senha.length() < 13)
            return true;

        return false;
    }

    private static boolean validarCPFouCNPJ(String cpfOuCnpj){
        if(validarCPF(cpfOuCnpj))
            return true;
        if(validarCNPJ(cpfOuCnpj))
            return true;

        return false;
    }

    private static boolean validarCPF(String cpf){
        if (cpf.equals("00000000000") ||
                cpf.equals("11111111111") ||
                cpf.equals("22222222222") || cpf.equals("33333333333") ||
                cpf.equals("44444444444") || cpf.equals("55555555555") ||
                cpf.equals("66666666666") || cpf.equals("77777777777") ||
                cpf.equals("88888888888") || cpf.equals("99999999999") ||
                (cpf.length() != 11))
            return(false);

        char dig10, dig11;
        int sm, i, r, num, peso;

        try {
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {
                num = (int)(cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48);

            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
                num = cpf.charAt(i) - 48;
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char)(r + 48);

            if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10)))
                return(true);
            else return(false);
        } catch (InputMismatchException erro) {
            return(false);
        }
    }

    private static boolean validarCNPJ(String cnpj) {
        if (cnpj.equals("00000000000000") || cnpj.equals("11111111111111") ||
                cnpj.equals("22222222222222") || cnpj.equals("33333333333333") ||
                cnpj.equals("44444444444444") || cnpj.equals("55555555555555") ||
                cnpj.equals("66666666666666") || cnpj.equals("77777777777777") ||
                cnpj.equals("88888888888888") || cnpj.equals("99999999999999") ||
                (cnpj.length() != 14))
            return(false);

        char dig13, dig14;
        int sm, i, r, num, peso;

        try {
            sm = 0;
            peso = 2;
            for (i=11; i>=0; i--) {
                num = (int)(cnpj.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10)
                    peso = 2;
            }

            r = sm % 11;
            if ((r == 0) || (r == 1))
                dig13 = '0';
            else dig13 = (char)((11-r) + 48);

            sm = 0;
            peso = 2;
            for (i=12; i>=0; i--) {
                num = (int)(cnpj.charAt(i)- 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10)
                    peso = 2;
            }

            r = sm % 11;
            if ((r == 0) || (r == 1))
                dig14 = '0';
            else dig14 = (char)((11-r) + 48);

            if ((dig13 == cnpj.charAt(12)) && (dig14 == cnpj.charAt(13)))
                return(true);
            else return(false);
        } catch (InputMismatchException erro) {
            return(false);
        }
    }
}
