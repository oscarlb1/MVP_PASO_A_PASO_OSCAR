package com.enrique.rakutentv.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import missing.namespace.R;
import androidx.recyclerview.widget.RecyclerView;


import com.enrique.rakutentv.beans.Film;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterFilms extends
        RecyclerView.Adapter<AdapterFilms.FilmViewHolder>
        implements View.OnClickListener {

    private ArrayList<Film> lstFilms;
    private View.OnClickListener listener;



    public static class FilmViewHolder extends RecyclerView.ViewHolder {
                // Campos respectivos de un item
                public ImageView imagen;
                public TextView nombre;
                public TextView visitas;
                public RatingBar calificacion;

                    public FilmViewHolder(View v) {
                        super(v);
                        imagen = (ImageView) v.findViewById(R.id.imagen);
                        nombre = (TextView) v.findViewById(R.id.nombre);
                        visitas = (TextView) v.findViewById(R.id.visitas);
                        calificacion= (RatingBar) v.findViewById(R.id.calificacion);
                    }
            }

    public AdapterFilms(ArrayList<Film> lstFilms) {
        this.lstFilms = lstFilms;
    }

    @Override
    public FilmViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.filmrow,
                        viewGroup, false);
        v.setOnClickListener(this);
        return new FilmViewHolder(v);
    }
    @Override
    /*Necesario cambiar URL en
            Picasso.get().load("http://192.168.1.137:8080/RakutenTVAndroid/images/"+lstFilms.get(posFila).getImgurl()+".png").into(viewHolder.imagen);
      Comprobar y colocar la actual
     */
    public void onBindViewHolder(FilmViewHolder viewHolder, int posFila) {
        Picasso.get().load("http://192.168.1.137:8080/RakutenTVAndroid/images/"+lstFilms.get(posFila).getImgurl()+".png").into(viewHolder.imagen); //Cambiar URL según IP
        viewHolder.nombre.setText(lstFilms.get(posFila).getNombre());
        viewHolder.visitas.setText("Visitas:"+String.valueOf(lstFilms.get(posFila).getVisitas()));
        int numCal;
        numCal = lstFilms.get(posFila).getNumCal();

        Calificacion(numCal,viewHolder);
    }
    @Override
    public int getItemCount() {
        return lstFilms.size();
    }


    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;

    }
    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }

    }

    public void Calificacion(int numCal, FilmViewHolder viewHolder){
        if (numCal < 300) {
            viewHolder.calificacion.setRating(1);
        } else if (numCal >= 300 && numCal < 400) {
            viewHolder.calificacion.setRating(2);
        } else if (numCal >= 400 && numCal < 500) {
            viewHolder.calificacion.setRating(3);
        } else if (numCal >= 500 && numCal < 600) {
            viewHolder.calificacion.setRating(4);
        } else if (numCal > 600) {
            viewHolder.calificacion.setRating(5);
        }
    }


}

