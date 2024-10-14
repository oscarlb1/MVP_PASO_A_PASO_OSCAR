package com.enrique.rakutentv.lstFilms;

import com.enrique.rakutentv.beans.Film;

import java.util.ArrayList;

public class LstFilmsPresenter implements LstFilmsContract.Presenter {
    /*Presenter, tiene que poder hablar con el Modelo
     y desencadenar acciones en la vista*/
    private LstFilmsContract.View vista;
    private LstFilmsModel modelo;

    public LstFilmsPresenter(LstFilmsContract.View vista) {
        this.vista = vista;
        this.modelo = new LstFilmsModel();
    }

    @Override
    public void getFilms() {
        this.modelo.getFilmsService(new LstFilmsContract.Model.OnLstFilmsListener() {
            @Override
            public void onFinished(ArrayList<Film> lstFilms) {
                vista.sucessListFilms(lstFilms);

            }

            @Override
            public void onFailure(String error) {

            }
        });

    }
}
