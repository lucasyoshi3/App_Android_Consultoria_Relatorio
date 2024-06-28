package com.fateczl.app_android_consultoria_relatorio;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fateczl.app_android_consultoria_relatorio.controller.RelatorioController;
import com.fateczl.app_android_consultoria_relatorio.model.Funcionario;
import com.fateczl.app_android_consultoria_relatorio.model.Relatorio;
import com.fateczl.app_android_consultoria_relatorio.persistence.RelatorioDao;

import java.sql.SQLException;
import java.util.List;

public class ClienteFragment extends Fragment {

    private View view;

    private EditText etCpf;
    private EditText etIdRelatorio;
    private Button btnBuscar;
    private Button btnListar;
    private TextView tvLista;
    private RelatorioController rCont;

    public ClienteFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_cliente, container, false);

        etCpf = view.findViewById(R.id.etCpfCliente);
        etIdRelatorio = view.findViewById(R.id.etInserirIdCliente);

        btnBuscar = view.findViewById(R.id.btnBuscarCpf);
        btnListar = view.findViewById(R.id.btnListarRelatorio);

        tvLista = view.findViewById(R.id.tvListarRelatorio);
        tvLista.setMovementMethod(new ScrollingMovementMethod());
        rCont = new RelatorioController(new RelatorioDao(view.getContext()));

        btnBuscar.setOnClickListener(op -> buscarRelatorios());
        btnListar.setOnClickListener(op -> mostrarRelatorio());

        return view;
    }

    private void buscarRelatorios() {
        Relatorio relatorio = new Relatorio();
        relatorio.setCpfAcessoCliente(etCpf.getText().toString());
        try {
            List<Relatorio> relatorios = rCont.listar(relatorio.getCpfAcessoCliente());
            StringBuffer buffer = new StringBuffer();
            for(Relatorio r : relatorios){
                buffer.append(r.getIdRelatorio() +"; " +r.getTitulo()+ "\n");
            }
            tvLista.setText(buffer.toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        limpaCampos();
    }

    private void mostrarRelatorio() {
        Relatorio relatorio = new Relatorio();
        relatorio.setIdRelatorio(Integer.parseInt(etIdRelatorio.getText().toString()));
        try {
            relatorio = rCont.buscar(relatorio);
            if(relatorio.getTitulo() !=null){
                tvLista.setText("");
                tvLista.setText(relatorio.toString());
            }else{
                Toast.makeText(view.getContext(), "Relatorio nao encontrado",
                        Toast.LENGTH_LONG).show();
                limpaCampos();
            }
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    private void limpaCampos() {
        etCpf.setText("");
        etIdRelatorio.setText("");
    }

}