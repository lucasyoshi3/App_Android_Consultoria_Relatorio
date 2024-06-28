package com.fateczl.app_android_consultoria_relatorio;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.fateczl.app_android_consultoria_relatorio.controller.RelatorioController;
import com.fateczl.app_android_consultoria_relatorio.model.Relatorio;
import com.fateczl.app_android_consultoria_relatorio.persistence.RelatorioDao;

import java.sql.SQLException;

public class RelatorioFragment extends Fragment {

    View view;

    private EditText etTitulo;
    private EditText etResumo;
    private EditText etTexto;
    private EditText etImagem;
    private EditText etLink;
    private RadioButton rbAnonimato;
    private EditText etCpfCliente;
    private EditText etIdFuncionario;
    private Button btnManterRelatorio;

    private RelatorioController rCont;
    private static int idContador =100;

    public RelatorioFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_relatorio, container, false);

        etTitulo = view.findViewById(R.id.etTituloRe);
        etResumo = view.findViewById(R.id.etResumoRe);
        etTexto = view.findViewById(R.id.etTextoRe);
        etLink = view.findViewById(R.id.etLinkRe);
        rbAnonimato = view.findViewById(R.id.rbAnonimatoRe);
        etCpfCliente = view.findViewById(R.id.etCpfRe);
        etImagem = view.findViewById(R.id.etImagenRe);
        etIdFuncionario = view.findViewById(R.id.etIdFuncionarioRe);

        rCont = new RelatorioController(new RelatorioDao(view.getContext()));

        btnManterRelatorio = view.findViewById(R.id.btnRealizarRe);

        btnManterRelatorio.setOnClickListener(op -> manterRelatorio());
        return view;
    }

    private void manterRelatorio() {
        Relatorio relatorio = montaCampos();
        try {
            rCont.inserir(relatorio);
            Toast.makeText(view.getContext(), "Relatorio realizado com sucesso",
                    Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        limparCampos();
    }

    private Relatorio montaCampos() {
        Relatorio r = new Relatorio();
        r.setIdRelatorio(idContador);
        idContador++;
        r.setTitulo(etTitulo.getText().toString());
        r.setResumo(etResumo.getText().toString());
        r.setTexto(etTexto.getText().toString());
        r.setImegem(etImagem.getText().toString());
        r.setLink(etLink.getText().toString());
        r.setFuncionarioId(Integer.parseInt(etIdFuncionario.getText().toString()));

        if(!rbAnonimato.isChecked()){
            r.setAnonimato(0);
        }else{
            r.setIdRelatorio(1);
        }

        if(etCpfCliente!= null){
            r.setCpfAcessoCliente(etCpfCliente.getText().toString());
        }else{
            r.setCpfAcessoCliente("null");
        }

        return r;
    }

    private void limparCampos(){
        etTitulo.setText("");
        etLink.setText("");
        etCpfCliente.setText("");
        etTexto.setText("");
        etResumo.setText("");
        etImagem.setText("");
    }


}