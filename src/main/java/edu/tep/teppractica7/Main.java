/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tep.teppractica7;

//import static spark.Spark.get;
import edu.tep.teppractica7.encapsulaciones.Estudiante;
import edu.tep.teppractica7.servicios.ConexionService;
import edu.tep.teppractica7.servicios.EstudianteService;
import edu.tep.teppractica7.utilidades.JsonTransformer;
import java.util.List;
import spark.Request;
import spark.Response;
import spark.Route;
import static spark.Spark.*;

/**
 *
 * @author vacax
 */
public class Main {

    //Teniendo la referencia de la conexionService.
    private static ConexionService conexionService = ConexionService.getInstancia();

    public static void main(String[] args) {

        //cambiar el puerto de spark por defecto 4567.
        //port(8080); 
        //Ruta por defecto...
        get("/", (request, response) -> {
            //Probando  la conexion
            conexionService.pruebaConexion();

            return "Práctica #7";
        });

        //Lo mismo que arriba, unicamente de la forma anterior a java 8.
        get("/otraForma", new Route() {
            @Override
            public Object handle(Request rqst, Response rspns) throws Exception {
                return " Práctica #7 de la forma larga";
            }
        });

        get("/listar", (request, response) -> {
            //Obtendiendo la lista de los estudiantes.
            List<Estudiante> listarEstudiante = EstudianteService.getInstancia()
                    .listarEstudiante();
            return listarEstudiante;
        }, new JsonTransformer());
        
         get("/estudiante/:matricula", (request, response) -> {
            //Obtendiendo la lista de los estudiantes.
            Estudiante estu = EstudianteService.getInstancia().
                    getEstudiante(request.params("matricula"));
            return estu;
        }, new JsonTransformer());

        // http://localhost:4567/insertar?matricula=2001-1136&nombre=Carlos&apellido=Camacho&telefono=1111111
        get("/insertar", (request, response) -> {
            //Recuperando los como parametro de la URL desde del ?.
            //TODO: validaciones...
            Estudiante est = new Estudiante();
            est.setMatricula(request.queryParams("matricula"));
            est.setNombre(request.queryParams("nombre"));
            est.setApellido(request.queryParams("apellido"));
            est.setTelefono(request.queryParams("telefono"));
            //Insertar en la base datos.
            EstudianteService.getInstancia().insertarEstudianteOpcion2(est);

            return String.format("Insertando el estudiante %s - %s",
                    est.getMatricula(), est.getNombre());
        });

    }
}
