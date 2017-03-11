/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tep.teppractica7.servicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase aplicando el patron singleton simplificado.
 *
 * @author vacax
 */
public class ConexionService {

    //una propiedad privada pero estatica.
    private static ConexionService instancia;

    private ConexionService() { // constructor es privado
        registrarDriver();
    }

    //Unico metodo para acceder a la instancia de la clase.
    public static ConexionService getInstancia() {
        if (instancia == null) {
            instancia = new ConexionService();
        }
        return instancia;
    }

    /**
     * Paso #1 para la conexión JDBC.
     */
    private void registrarDriver() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Paso #2 - Realizando la conexión con la base de datos.
     *
     * @return
     */
    public Connection getConexion() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/practica7", "sa", "");
        } catch (SQLException ex) {
            Logger.getLogger(ConexionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    public void pruebaConexion() {
        Connection conexion = getConexion();
        //en este punto estoy conectado....
        System.out.println("Conectado...");
        
        //Cerrando la conexion.
        try {
            conexion.close(); //Cerrar la conexión.
        } catch (SQLException ex) {
            Logger.getLogger(ConexionService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
