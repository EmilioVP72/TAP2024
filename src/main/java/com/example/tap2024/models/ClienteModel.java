package com.example.tap2024.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClienteModel {
    private int id_cliente;
    private String cliente;
    private String telefono;
    private String correo;

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void Update(){
            String query = "update cliente set cliente = '"+this.cliente+"', telefono = '"+this.telefono+"', correo = '"+this.correo+"' where id_cliente ="+this.id_cliente;
            try {
                Statement stmt = Conexion.connection.createStatement();
                stmt.executeUpdate(query);
            }catch (Exception e) {
                e.printStackTrace();
            }
    }

    public void Insert(){
        String query = "Insert into cliente(cliente,telefono,correo)" + " values('"+this.cliente+"', '"+this.telefono+"', '"+this.correo+"')";
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (SQLException e){
            e.printStackTrace();

        }
    }

    public void Delete(){
        String query = "delete from cliente where id_cliente = " + this.id_cliente;
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ObservableList<ClienteModel> Select(){
        ClienteModel objCte;
        String query = "select * from cliente";
        ObservableList<ClienteModel> listaCliente = FXCollections.observableArrayList();
        try {
            Statement stmt = Conexion.connection.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                objCte = new ClienteModel();
                objCte.id_cliente = res.getInt(0);
                objCte.cliente = res.getString(1);
                objCte.telefono = res.getString(2);
                objCte.correo = res.getString(3);
                listaCliente.add(objCte);

            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        return listaCliente;

    }
}
