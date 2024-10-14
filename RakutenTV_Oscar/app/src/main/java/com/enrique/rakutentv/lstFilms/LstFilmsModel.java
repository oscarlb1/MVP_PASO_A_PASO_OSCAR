package com.enrique.rakutentv.lstFilms;

import android.os.AsyncTask;
import android.util.Log;

import com.enrique.rakutentv.beans.Film;
import com.enrique.rakutentv.utils.Post;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

public class LstFilmsModel implements LstFilmsContract.Model {

    private OnLstFilmsListener onLstFilmsListener;
    private ArrayList<Film>  lstFilms;

    @Override
    public void getFilmsService(final OnLstFilmsListener onLstFilmsListener) {
        this.onLstFilmsListener = onLstFilmsListener;
        HashMap<String,String> param = new HashMap<>();
        param.put("ACTION", "PELICULA.FIND_ALL");
        TareaSegundoPlano miTarea = new TareaSegundoPlano(param);
        //Cambiar URL Segun IP
        miTarea.execute("http://192.168.1.137:8080/RakutenTVAndroid/Controller"); //Cambiar URL segun IP
    }
    /*Clase ASYNKTASK*/
    // HILO ASYNCTASK
    class TareaSegundoPlano extends AsyncTask<String, Integer, Boolean> {
        private HashMap<String, String> parametros = null;

        public TareaSegundoPlano( HashMap<String, String> parametros) {
            super();
            this.parametros = parametros;
        }

        @Override
        protected Boolean  doInBackground(String... params) {
            String url_select = params[0];
            try {
                Post post = new Post();
                JSONArray result = post.getServerDataPost(parametros,url_select);
                lstFilms = Film.getArrayListFromJSon(result);
            } catch (Exception e) {
                Log.e("log_tag", "Error in http connection " + e.toString());
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean resp) {
            try {
                if(resp){
                    onLstFilmsListener.onFinished(lstFilms);

                }
            }catch (Exception e) {
                    onLstFilmsListener.onFailure("Fallo:Listar Peliculas");
            }
        }
    }

}
