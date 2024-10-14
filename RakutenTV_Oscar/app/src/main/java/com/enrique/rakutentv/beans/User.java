package com.enrique.rakutentv.beans;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
/*La clase usuario implementa la interfaz Parcelable
para posteriormente mandar un Array de Users
en un Bundle
 */
public class User implements Parcelable {
    private String email;
    private String password;
    private String nombre;
    private String apellido;

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    private String fechaRegistro;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User() {
    }

    protected User(Parcel in) {
        email = in.readString();
        password = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static ArrayList<User> getArrayListFromJSon(JSONArray datos){
        ArrayList<User> lista = null;
        User user = null;
        try {
            if(datos!=null && datos.length() > 0 ){
                lista = new ArrayList<User>();
            }
            for (int i = 0; i < datos.length(); i++) {
                JSONObject json_data = datos.getJSONObject(i);
                user = new User();
                user.setEmail(json_data.getString("email"));
                user.setPassword(json_data.getString("password"));
                user.setNombre(json_data.getString("nombre"));
                user.setApellido(json_data.getString("apellido"));
                user.setFechaRegistro(json_data.getString("fechaRegistro"));

                lista.add(user);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }		return lista;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeString(password);
    }
}
