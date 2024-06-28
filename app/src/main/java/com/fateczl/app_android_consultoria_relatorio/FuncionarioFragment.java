package com.fateczl.app_android_consultoria_relatorio;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fateczl.app_android_consultoria_relatorio.controller.FuncionarioController;
import com.fateczl.app_android_consultoria_relatorio.model.Funcionario;
import com.fateczl.app_android_consultoria_relatorio.persistence.FuncionarioDao;

import java.sql.SQLException;
import java.util.List;

public class FuncionarioFragment extends Fragment {

    private View view;

    private EditText etCodigo;
    private EditText etNome;
    private EditText etApelido;
    private Button btnManter;
    private Button btnBuscar;
    private Button btnExcluir;
    private Button btnListar;
    private Button btnModificar;
    private TextView tvListar;
    private FuncionarioController fCont;

    public FuncionarioFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_funcionario, container, false);
        etCodigo = view.findViewById(R.id.etIdFun);
        etNome = view.findViewById(R.id.etNomeFun);
        etApelido = view.findViewById(R.id.etApelidoFun);

        btnManter = view.findViewById(R.id.btnInserirFuncionario);
        btnExcluir = view.findViewById(R.id.btnExluirFun);
        btnModificar = view.findViewById(R.id.btnModificarFun);
        btnBuscar = view.findViewById(R.id.btnBuscarFun);
        btnListar = view.findViewById(R.id.btnListarFun);
        tvListar = view.findViewById(R.id.tvListarFun);

        fCont = new FuncionarioController(new FuncionarioDao(view.getContext()));

        btnManter.setOnClickListener(op -> acaoInserir());
        btnExcluir.setOnClickListener(op -> acaoExcluir());
        btnModificar.setOnClickListener(op -> acaoModificar());
        btnBuscar.setOnClickListener(op -> acaoBuscar());
        btnListar.setOnClickListener(op -> acaoListar());


        return view;
    }

    private void acaoInserir() {
        Funcionario funcionario = montaCampos();
        try {
            fCont.inserir(funcionario);
            Toast.makeText(view.getContext(), "Funcionario inserido com sucesso",
                    Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }

    private void acaoModificar(){
        Funcionario funcionario = montaCampos();
        try {
            fCont.modificar(funcionario);
            Toast.makeText(view.getContext(), "Funcionario atualizado com sucesso",
                    Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }

    private void acaoExcluir(){
        Funcionario funcionario = montaCampos();
        try {
            fCont.deletar(funcionario);
            Toast.makeText(view.getContext(), "Funcionario excluido com sucesso",
                    Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }

    private void acaoBuscar(){
        Funcionario funcionario = montaCampos();
        try {
            fCont.buscar(funcionario);
            if(funcionario.getNome() !=null){
                preencheCampos(funcionario);
            }else{
                Toast.makeText(view.getContext(), "Funcionario nao encontrado",
                        Toast.LENGTH_LONG).show();
                limpaCampos();
            }
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void acaoListar(){
        try {
            List<Funcionario> funcionarios = fCont.listar();
            StringBuffer buffer = new StringBuffer();
            for(Funcionario f : funcionarios){
                buffer.append(f.toString() + "\n");
            }
            tvListar.setText(buffer.toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Funcionario montaCampos(){
        Funcionario f = new Funcionario();
        f.setIdPessoa(Integer.parseInt(etCodigo.getText().toString()));
        f.setNome(etNome.getText().toString());
        f.setApelido(etApelido.getText().toString());

        return f;
    }

    private void preencheCampos(Funcionario f){
        etCodigo.setText(String.valueOf(f.getIdPessoa()));
        etNome.setText(f.getNome());
        etApelido.setText(f.getApelido());
    }

    private void limpaCampos(){
        etCodigo.setText("");
        etNome.setText("");
        etApelido.setText("");
    }
}