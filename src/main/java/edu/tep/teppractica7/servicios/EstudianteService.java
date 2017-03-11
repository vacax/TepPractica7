/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tep.teppractica7.servicios;

import edu.tep.teppractica7.encapsulaciones.Estudiante;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vacax
 */
public class EstudianteService {
    
    private static EstudianteService instancia;
    
    private EstudianteService(){
        
    }
    
    public static EstudianteService getInstancia(){
        if(instancia == null){
            instancia = new EstudianteService();
        }
        return instancia;
    }
  
    
    public void insertarEstudianteOpcion1(Estudiante estudiante){
        try (Connection conexion = ConexionService.getInstancia().getConexion()) {
            Statement sentencia = conexion.createStatement();
            String sql = "insert into estudiante (matricula, nombre, apellido, telefono) "
                    + "value('"+estudiante.getMatricula()+"', '"+estudiante.getNombre()+"',"
                    +"'"+estudiante.getApellido()+"', '"+estudiante.getTelefono()+"')";
            int cantidad_fila_insertadas = sentencia.executeUpdate(sql);
            System.out.println("Las filas insertandas: "+cantidad_fila_insertadas);
        } catch (SQLException ex) {
            Logger.getLogger(EstudianteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void insertarEstudianteOpcion2(Estudiante estudiante){
        try (Connection conexion = ConexionService.getInstancia().getConexion()) {
            String sql = "insert into estudiante (matricula, nombre, apellido, telefono) "
                    + "values(?,?,?,?)";
            PreparedStatement pre = conexion.prepareStatement(sql);
            pre.setString(1, estudiante.getMatricula());
            pre.setString(2, estudiante.getNombre());
            pre.setString(3, estudiante.getApellido());
            pre.setString(4, estudiante.getTelefono());
            //enviar la instrucción comando o sentencia.
            int cantidad_filas_insertadas = pre.executeUpdate();
            System.out.println("Las filas insertada: "+cantidad_filas_insertadas);
        } catch (SQLException ex) {
            Logger.getLogger(EstudianteService.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            System.out.println("termino de insertar...");
        }
        
    }
}
