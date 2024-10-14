package com.enrique.rakutentv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.UnsupportedEncodingException;

import missing.namespace.R;

public class FichaPelicula extends AppCompatActivity {
    /*Declaración de variables*/
    private ImageView imagenpeli;
    private TextView titulo;
    private TextView fechaEstreno;
    private TextView visitas;
    private TextView sinopsis;
    private ImageButton verTrailer;
    String tituloimport=null, imagenimport=null, visitasimport=null, fechaimport=null, sinopsisimport=null, trailerimport = null;
    int numCal;
    /*Fin*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficha_pelicula);
        /*Traer Valores activity anterior*/
        traerValores();
        /*FIN*/
        /*"Mapear" objetos interfaz*/

        TextView titulo = (TextView)findViewById(R.id.titulo);
        TextView fechaEstreno = (TextView)findViewById(R.id.fechaEstreno);
        //TextView visitas = (TextView)findViewById(R.id.visitas);
        RatingBar calificacion = (RatingBar) findViewById(R.id.calificacion);
        TextView sinopsis = (TextView)findViewById(R.id.sinopsis);
        ImageView imagenpeli = (ImageView)findViewById(R.id.imagenpeli);
        ImageButton verTrailer = (ImageButton)findViewById(R.id.verTrailer);

        /*FIN*/

        /*Mostrar valores*/
        Picasso.get().load("http://192.168.1.137:8080/RakutenTVAndroid/images/"+imagenimport+".png").into(imagenpeli); //Cambiar URL según IP
        titulo.setText(convertirTexto(tituloimport));
        fechaEstreno.setText(fechaimport);
        sinopsis.setText(convertirTexto(sinopsisimport));
        Calificacion(numCal, calificacion);
        /*Fin*/
        /*Boton para ver el Trailer*/
        verTrailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri = Uri.parse(trailerimport);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        /*FIN*/
    }
    /*Cambiar codificacion texto*/
    public String convertirTexto(String texto){

        String sinopsisCharSet = null;
        try {
            sinopsisCharSet = new String(texto.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sinopsisCharSet;
    }
    /*FIN*/

    /*Metodos para traer valores del Activity anterior*/
    public void traerValores(){
        this.tituloimport = traerTitulo();
        this.imagenimport = traerImagen();
        this.fechaimport = traerFechaEstreno();
        this.sinopsisimport = traerSinopsis();
        this.trailerimport = traerTrailer();
        this.numCal = traernumCal();
    }
    public String traerTitulo(){
        Bundle bundle = getIntent().getExtras();
        String tituloimport=bundle.getString("titulo");
        return tituloimport;
    }
    public String traerImagen(){
        Bundle bundle = getIntent().getExtras();
        String imagenImport=bundle.getString("imagen");
        return imagenImport;
    }
    public String traerFechaEstreno(){
        Bundle bundle = getIntent().getExtras();
        String fechaImport=bundle.getString("fechaEstreno");
        return fechaImport;
    }
    public String traerSinopsis(){
        Bundle bundle = getIntent().getExtras();
        String sinopsisImport=bundle.getString("sinopsis");
        return sinopsisImport;
    }
    public String traerTrailer(){
        Bundle bundle = getIntent().getExtras();
        String trailerImport=bundle.getString("trailer");
        return trailerImport;
    }
    public int traernumCal(){
        Bundle bundle = getIntent().getExtras();
        int cal = bundle.getInt("calificacion");
        return cal;
    }
    /*Fin */

    /*Metodo para Asignar estrellas al RatingBar*/
    public void Calificacion(int numCal, RatingBar cal){
        if (numCal < 300) {
            cal.setRating(1);
        } else if (numCal >= 300 && numCal < 400) {
            cal.setRating(2);
        } else if (numCal >= 400 && numCal < 500) {
            cal.setRating(3);
        } else if (numCal >= 500 && numCal < 600) {
            cal.setRating(4);
        } else if (numCal > 600) {
            cal.setRating(5);
        }
    }
    /*Fin*/
}