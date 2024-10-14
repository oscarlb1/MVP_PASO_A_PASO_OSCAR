package com.enrique.rakutentv.beans;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Film {
    private String imgurl;
    private String nombre;
    private int visitas;
    private String sinopsis;
    private String fechaestreno;
    private String urlTrailer;
    private int numCal;



    public Film(String imgurl, String nombre, int visitas) {
        this.imgurl = imgurl;
        this.nombre = nombre;
        this.visitas = visitas;
    }

    public Film() {
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getFechaestreno() {
        return fechaestreno;
    }

    public void setFechaestreno(String fechaestreno) {
        this.fechaestreno = fechaestreno;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getVisitas() {
        return visitas;
    }
    public void setVisitas(int visitas) {
        this.visitas = visitas;
    }

    public String getUrlTrailer() {
        return urlTrailer;
    }

    public void setUrlTrailer(String urlTrailer) {
        this.urlTrailer = urlTrailer;
    }

    public int getNumCal() {
        return numCal;
    }

    public void setNumCal(int numCal) {
        this.numCal = numCal;
    }

    public static ArrayList<Film> getArrayListFromJSon(JSONArray datos){
        ArrayList<Film> lista = null;
        Film film = null;
        try {
            if(datos!=null && datos.length() > 0 ){
                lista = new ArrayList<Film>();
            }
            for (int i = 0; i < datos.length(); i++) {
                JSONObject json_data = datos.getJSONObject(i);
                film = new Film();
                film.setNombre(json_data.getString("titulo"));
                film.setVisitas(json_data.getInt("nVotos"));
                film.setImgurl(json_data.getString("url"));
                film.setFechaestreno(json_data.getString("fechaEstreno"));
                film.setSinopsis(json_data.getString("sinopsis"));
                film.setUrlTrailer(json_data.getString("trailer"));
                film.setNumCal(json_data.getInt("sPuntuacion"));
                lista.add(film);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }		return lista;
    }
}
