/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tep.teppractica7.servicios;

import edu.tep.teppractica7.encapsulaciones.Estudiante;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
            //Estoy formateando el String, con la información....
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
            //Trabaja con comodines...
            String sql = "insert into estudiante (matricula, nombre, apellido, telefono) "
                    + "values(?,?,?,?)";
            PreparedStatement pre = conexion.prepareStatement(sql);
            //inserto los parametros..
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
    
    public List<Estudiante> listarEstudiante(){
        List<Estudiante> listar=new ArrayList<>();
        
        try (Connection conexion = ConexionService.getInstancia().getConexion()) {
            //La consulta.
            String sql = "select * from estudiante";
            //
            PreparedStatement pre = conexion.prepareStatement(sql);
            //enviar los parametros.. si aplica...
            ResultSet result = pre.executeQuery();
            while(result.next()){ //valida que exista información y incrementa el cursor.
                //Obtengo la informacióñ....
                Estudiante estu = new Estudiante();
                estu.setMatricula(result.getString("matricula"));
                estu.setNombre(result.getString("nombre"));
                estu.setApellido(result.getString("apellido"));
                estu.setTelefono(result.getString("telefono"));
                //agregando a la lista
                listar.add(estu);
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(EstudianteService.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            System.out.println("termino de insertar...");
        }
        
        return listar;
    }
    
    /**
     * 
     * @param matricula
     * @return 
     */
    public Estudiante getEstudiante(String matricula){
        Estudiante estu=null;
        
        try (Connection conexion = ConexionService.getInstancia().getConexion()) {
            //La consulta.
            String sql = "select * from estudiante where matricula=?";
            //
            PreparedStatement pre = conexion.prepareStatement(sql);
            //enviar los parametros..
            pre.setString(1, matricula);
            //
            ResultSet result = pre.executeQuery();
            while(result.next()){ //valida que exista información y incrementa el cursor.
                //Obtengo la informacióñ....
                estu = new Estudiante();
                estu.setMatricula(result.getString("matricula"));
                estu.setNombre(result.getString("nombre"));
                estu.setApellido(result.getString("apellido"));
                estu.setTelefono(result.getString("telefono"));                
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(EstudianteService.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            System.out.println("termino de insertar...");
        }
        
        return estu;
    }
}
