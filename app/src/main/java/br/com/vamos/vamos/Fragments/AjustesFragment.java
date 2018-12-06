package br.com.vamos.vamos.Fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import br.com.vamos.vamos.Activities.LoginActivity;
import br.com.vamos.vamos.R;

public class AjustesFragment extends Fragment {

    Button sobre, sair;
    AlertDialog.Builder dialogo;
    Intent trocarTela;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sobre = view.findViewById(R.id.btnSobre);
        sair = view.findViewById(R.id.btnSair);

        sobre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogo = new AlertDialog.Builder(getActivity());
                dialogo.setTitle("Sobre o aplicativo");
                dialogo.setMessage("Aplicativo desenvolvido em Android Studio para Projeto D. Versão Android 5.0+");
                dialogo.setPositiveButton("Entendi.", null);

                dialogo.show();
            }
        });

        sair.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dialogo = new AlertDialog.Builder(getActivity());
                dialogo.setTitle("Sair");
                dialogo.setMessage("Deseja deslogar da conta?");
                dialogo.setNegativeButton("Não", null);
                dialogo.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        trocarTela = new Intent(getActivity(), LoginActivity.class);
                        startActivity(trocarTela);

                        getActivity().finish();
                    }
                });

                dialogo.show();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ajustes, null);
    }
}
