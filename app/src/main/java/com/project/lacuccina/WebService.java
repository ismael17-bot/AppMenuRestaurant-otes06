package com.project.lacuccina;

import android.content.Context;
import android.net.wifi.WifiManager;

import android.support.v7.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *  Classe responsável pela chamada do WebService geral:
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
public class WebService extends AppCompatActivity {

    public static String buscaWS(String cEndPoint) {
        String cRetorno = "ERRO: Não reconhecido";
        BufferedReader bufferedReader = null;
        // Efetuo requisição:
        URL urlServidor;

        try {
            urlServidor = new URL(cEndPoint);
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlServidor.openConnection();
            StringBuilder stringBuilder = new StringBuilder();
            bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

            String linha;

            while((linha = bufferedReader.readLine()) != null){
                stringBuilder.append(linha+"\n");
            }
            cRetorno = stringBuilder.toString();

        } catch (final MalformedURLException e) {
            cRetorno = "ERRO_I1: " + e.getMessage();
        } catch (final IOException e) {
            cRetorno = "ERRO_I2: " + e.getMessage();
        }

        return cRetorno;
    }
}
