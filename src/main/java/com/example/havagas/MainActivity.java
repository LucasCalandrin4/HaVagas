package com.example.havagas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Objetos de binding com as Views
    private EditText nomeEt;
    private EditText emailEt;
    private EditText telefoneEt;
    private RadioGroup telefonoRG;
    private RadioButton residencialRb;
    private RadioButton comercialRb;
    private Spinner celularSp;
    private LinearLayout celularLl;
    private EditText celularEt;
    private RadioGroup sexoRg;
    private RadioButton masculinoRb;
    private RadioButton femininoRb;
    private EditText dataEt;
    private Spinner formacaoSp;
    private LinearLayout fundamentalMedioLl;
    private EditText anoFormaturaEt;
    private LinearLayout graduacaoEspecLl;
    private EditText anoConclusaoEt;
    private EditText instituicaoEt;
    private LinearLayout mestradoDoutoradoLl;
    private EditText anoConclusaoMDEt;
    private EditText instituicaoMDEt;
    private EditText tituloMonografiaEt;
    private EditText orientadorEt;
    private EditText vagasInteresseEt;
    private CheckBox receberEmailCb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();

        celularSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String celular = ((TextView) view).getText().toString();
                if (celular.equals("Adicionar")){
                    celularLl.setVisibility(View.VISIBLE);
                }
                else{
                    celularLl.setVisibility(View.GONE);
                    celularEt.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        formacaoSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String formacao = ((TextView)view).getText().toString();
                if(formacao.equals("Fundamental") || formacao.equals("Médio")){
                    fundamentalMedioLl.setVisibility(View.VISIBLE);
                    graduacaoEspecLl.setVisibility(View.GONE);
                    anoConclusaoEt.setText("");
                    instituicaoEt.setText("");
                    mestradoDoutoradoLl.setVisibility(View.GONE);
                    anoConclusaoMDEt.setText("");
                    instituicaoMDEt.setText("");
                    tituloMonografiaEt.setText("");
                    orientadorEt.setText("");
                }
                else if (formacao.equals("Graduação") || formacao.equals("Especialização")){
                    graduacaoEspecLl.setVisibility(View.VISIBLE);
                    fundamentalMedioLl.setVisibility(View.GONE);
                    anoFormaturaEt.setText("");
                    mestradoDoutoradoLl.setVisibility(View.GONE);
                    anoConclusaoMDEt.setText("");
                    instituicaoMDEt.setText("");
                    tituloMonografiaEt.setText("");
                    orientadorEt.setText("");
                }
                else if (formacao.equals("Mestrado") || formacao.equals("Doutorado")){
                    mestradoDoutoradoLl.setVisibility(View.VISIBLE);
                    fundamentalMedioLl.setVisibility(View.GONE);
                    anoFormaturaEt.setText("");
                    graduacaoEspecLl.setVisibility(View.GONE);
                    anoConclusaoEt.setText("");
                    instituicaoEt.setText("");
                }
                else{
                    fundamentalMedioLl.setVisibility(View.GONE);
                    anoFormaturaEt.setText("");
                    graduacaoEspecLl.setVisibility(View.GONE);
                    anoConclusaoEt.setText("");
                    instituicaoEt.setText("");
                    mestradoDoutoradoLl.setVisibility(View.GONE);
                    anoConclusaoMDEt.setText("");
                    instituicaoMDEt.setText("");
                    tituloMonografiaEt.setText("");
                    orientadorEt.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void onClick(View view){

        // Verificação Spinner Celular
        String stCelular;
        if(((TextView) celularSp.getSelectedView()).getText().equals("Adicionar")){
            stCelular = celularEt.getText().toString();
        }
        else{
            stCelular = "Não Adicionar";
        }

        //Verificação Spinner Formação
        String stFormacao;
        if(((TextView) formacaoSp.getSelectedView()).getText().equals("Fundamental") || ((TextView) formacaoSp.getSelectedView()).getText().equals("Médio")){
            stFormacao = (((TextView) formacaoSp.getSelectedView()).getText()) + "\n" + anoFormaturaEt.getText().toString();
        }
        else if(((TextView) formacaoSp.getSelectedView()).getText().equals("Graduação") || ((TextView) formacaoSp.getSelectedView()).getText().equals("Especialização")){
            stFormacao = (((TextView) formacaoSp.getSelectedView()).getText()) + "\n" + anoConclusaoEt.getText().toString() + "\n" + instituicaoEt.getText().toString();
        }
        else{
            stFormacao = (((TextView) formacaoSp.getSelectedView()).getText()) + "\n" + anoConclusaoMDEt.getText().toString() + "\n" + instituicaoMDEt.getText().toString() +
                    "\n" + tituloMonografiaEt.getText().toString() + "\n" +orientadorEt.getText().toString();
        }

        //StringBuilder
        StringBuilder sb = new StringBuilder();
        sb.append(nomeEt.getText().toString());
        sb.append("\n");
        sb.append(emailEt.getText().toString());
        sb.append("\n");
        sb.append(telefoneEt.getText().toString());
        sb.append("\n");
        sb.append(residencialRb.isChecked()?"residencial":"comercial");
        sb.append("\n");
        sb.append(stCelular);
        sb.append("\n");
        sb.append(masculinoRb.isChecked()?"Masculino":"Feminino");
        sb.append("\n");
        sb.append(dataEt.getText().toString());
        sb.append("\n");
        sb.append(stFormacao);
        sb.append("\n");
        sb.append(vagasInteresseEt.getText().toString());
        sb.append("\n");
        sb.append(receberEmailCb.isChecked()?"Deseja receber e-mail":"Não deseja receber");


        if(view.getId() == R.id.salvarBt){
            Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show();
        }
        if(view.getId() == R.id.limparBt){
            Toast.makeText(this, "Botão Limpar Clicado", Toast.LENGTH_SHORT).show();
        }
    }

    private void bindViews(){
        nomeEt = findViewById(R.id.nomeEt);
        emailEt = findViewById(R.id.emailEt);
        telefoneEt = findViewById(R.id.telefoneEt);
        telefonoRG = findViewById(R.id.tipoTelefoneRg);
        residencialRb = findViewById(R.id.residencialRb);
        comercialRb = findViewById(R.id.comercialRb);
        celularSp = findViewById(R.id.celularSp);
        celularLl = findViewById(R.id.celularLl);
        celularEt = findViewById(R.id.celularEt);
        sexoRg = findViewById(R.id.sexoRg);
        masculinoRb = findViewById(R.id.masculinoRb);
        femininoRb = findViewById(R.id.femininoRb);
        dataEt = findViewById(R.id.dataEt);
        formacaoSp = findViewById(R.id.formaçãoSp);
        fundamentalMedioLl = findViewById(R.id.fundamentalMedioLl);
        anoFormaturaEt = findViewById(R.id.anoFormaturaEt);
        graduacaoEspecLl = findViewById(R.id.graduacaoEspecLl);
        anoConclusaoEt = findViewById(R.id.anoConclusaoEt);
        instituicaoEt = findViewById(R.id.instituicaoEt);
        mestradoDoutoradoLl = findViewById(R.id.mestradoDoutoradoLl);
        anoConclusaoMDEt = findViewById(R.id.anoConclusaoMDEt);
        instituicaoMDEt = findViewById(R.id.instituicaoMDEt);
        tituloMonografiaEt = findViewById(R.id.tituloMonografiaEt);
        orientadorEt = findViewById(R.id.orientadorEt);
        vagasInteresseEt = findViewById(R.id.vagasInteresseEt);
        receberEmailCb = findViewById(R.id.receberEmailCb);
    }
}