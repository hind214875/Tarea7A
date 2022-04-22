/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tarea7AHind;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;


/**
 *
 * @author hinda
 */
public class Utils {

    //metodos
    /*A partir de una lista de empleados y un nombre, indique si hay algún empleado con ese nombre.*/

    public static boolean encuentraElempleado(ArrayList<Empleado> lista, String nombre) {
        boolean resulta = false;
        for (Empleado obj : lista) {
            if (obj.getNombre().equals(nombre)) {
                resulta = true;
            }
        }
        return resulta;
    }
    
    /*A partir de una lista de empleados y un nombre de departamento, 
    indique el número de empleados Coordinadores de ese departamento.*/
    
    public static int numeroEmpleadosPorDep(ArrayList<Empleado> lista, String dep){
        int numero=0;
        for (Empleado obj : lista) {
            if(obj.getPuesto().equalsIgnoreCase(dep)){
                numero++;
            }
        }
        return numero;
    }
    
    /*A partir de una lista de empleados y una letra del NIF (char), 
    obtener una nueva lista ordenada alfabéticamente SOLO con los apellidos de 
    los empleados cuyo NIF contenga esa letra.*/
    
    public static ArrayList<String> listaEmpleadoConNifDado(ArrayList<Empleado> lista, char letra){
        ArrayList<String> listaObtenga=new ArrayList<>();
        
        for (Empleado obj : lista) {
            if(obj.getDni().indexOf(letra)!=-1){
               listaObtenga.add(obj.getNombre());
            }
        }
        
        //ordenar la lista con nombre
         Collections.sort(listaObtenga);
        
        return listaObtenga;
    }
    
    /*A partir de una lista de empleados y una fecha,
    obtener una nueva lista con los NIF (ordenados de forma inversa) 
    de todos los empleados cuya toma de posesión coincida con esa fecha.*/
    
    public static ArrayList<String> empleadosFechaCoicidencia(ArrayList<Empleado> lista, LocalDate fecha){
        ArrayList<String>  listaNifs=new ArrayList<>();
        
        for (Empleado obj : lista) {
            if(obj.getFechaTomaPos().isEqual(fecha)){
                listaNifs.add(obj.getDni());
            }
        }
        
        //ordenar Nifs de forma inversa
        Collections.reverse(listaNifs);
        
        return listaNifs;
    }
}
