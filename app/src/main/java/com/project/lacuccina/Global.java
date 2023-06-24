package com.project.lacuccina;

import android.app.Application;
import android.util.Log;

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *  Classe responsável por manipular as variáveis globais:
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
public class Global extends Application {
    // Controle de versão:
    public String idPedido = "";
    public String qtdItem [][];
    public int tamanho;

    public void setIdPedido(String id){
        idPedido = id;
    }

    public String getIdPedido(){
        return idPedido;
    }

    public void setQtdItem(String[][] qtd){
        qtdItem = qtd;
    }

    public String[][] getQtdItem(){
        return qtdItem;
    }

    public String[][] getQtd(){
        return qtdItem;
    }

    public void setQtdTam(int tam){
        tamanho = tam;
    }

    public int getQtdTam(){
        return tamanho;
    }
}
