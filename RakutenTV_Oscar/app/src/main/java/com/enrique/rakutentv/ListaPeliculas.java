package com.enrique.rakutentv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.enrique.rakutentv.adapter.AdapterFilms;
import com.enrique.rakutentv.beans.Film;
import com.enrique.rakutentv.lstFilms.LstFilmsContract;
import com.enrique.rakutentv.lstFilms.LstFilmsPresenter;

import java.util.ArrayList;

import missing.namespace.R;

public class ListaPeliculas extends AppCompatActivity  implements LstFilmsContract.View {

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;
    private LstFilmsPresenter lstFilmsPresenter;
    private ArrayList<Film> lstFilms;

    private Button btnlstPelis;
    private Button btngridPelis;
    private Button btnusuario;

    private String titulo, imagen, visitas, fechaEstreno,sinopsis, trailer;
    private int numCal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_peliculas);
        /*Instanciar Presenter*/
        lstFilmsPresenter = new LstFilmsPresenter(this);
        lstFilmsPresenter.getFilms();
        /*Fin*/

        // Obtener el Recycler
        recycler = (RecyclerView) findViewById(R.id.reciclador);
        recycler.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);
        //recycler.setLayoutManager(new GridLayoutManager(this, 2));
        //Asignamos botones

        btnlstPelis = (Button) findViewById(R.id.btnlista);
        btngridPelis = (Button) findViewById(R.id.btnGrid);
        btnusuario = (Button) findViewById(R.id.btnUser);

        btnusuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cambioUser = new Intent(getApplicationContext(), VistaUsuario.class);
                startActivity(cambioUser);
            }
        });
        btngridPelis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent cambioGrid = new Intent(getApplicationContext(), GridPeliculas.class);
                startActivity(cambioGrid);
            }
        });
        btnlstPelis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cambioList = new Intent(getApplicationContext(),ListaPeliculas.class);
                startActivity(cambioList);
            }
        });
    }

    @Override
    public void sucessListFilms(final ArrayList<Film> lstFilms) {
        // Crear un nuevo adaptador
        AdapterFilms adapter = new AdapterFilms(lstFilms);
        this.lstFilms=lstFilms;
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent cambio1 = new Intent(getApplicationContext(), FichaPelicula.class);
                /*Guardar y exportar parametros*/
               guardarParam(v);
               exportarParamyAbrir(cambio1, titulo, imagen, visitas, fechaEstreno, sinopsis, trailer, numCal);
               /*Fin*/
            }
        });
        recycler.setAdapter(adapter);
    }

    @Override
    public void failureListFilms(String message) {

    }
    /*Metodo para guardar parametros*/
    public void guardarParam(View v){
        this.titulo = lstFilms.get(recycler.getChildAdapterPosition(v)).getNombre();
        this.imagen = lstFilms.get(recycler.getChildAdapterPosition(v)).getImgurl();
        this.fechaEstreno = lstFilms.get(recycler.getChildAdapterPosition(v)).getFechaestreno();
        this.sinopsis = lstFilms.get(recycler.getChildAdapterPosition(v)).getSinopsis();
        this.trailer = lstFilms.get(recycler.getChildAdapterPosition(v)).getUrlTrailer();
        this.numCal = lstFilms.get(recycler.getChildAdapterPosition(v)).getNumCal();
    }
    /*Fin*/
     /*Metodo para Exportar parametros y abrir nuevo activity*/
    public void exportarParamyAbrir(Intent intent, String titulo, String imagen, String visitas, String fechaEstreno, String sinopsis, String trailer, int numCal){
        intent.putExtra("titulo", titulo);
        intent.putExtra("imagen", imagen);
        intent.putExtra("visitas", visitas);
        intent.putExtra("fechaEstreno", fechaEstreno);
        intent.putExtra("sinopsis", sinopsis);
        intent.putExtra("trailer", trailer);
        intent.putExtra("calificacion", numCal);
        startActivity(intent);
    }
    /*Fin*/
}
