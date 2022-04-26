/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea7AHind;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;
import static tarea7AHind.Utils.empleadosFechaCoicidencia;
import static tarea7AHind.Utils.encuentraElempleado;
import static tarea7AHind.Utils.listaEmpleadoConNifDado;
import static tarea7AHind.Utils.numeroEmpleadosPorDep;
 




/**
 *
 * @author hinda
 */
public class LecturaFichero {

    public static ArrayList<Empleado> lecturaFichero() {
        // Fichero a leer
        String idFichero = "RelPerCen.csv";

        // Variables para guardar los datos que se van leyendo
        //lista
        String[] tokens;
        String linea;
        ArrayList<Empleado> empleados = new ArrayList<>();

        System.out.println("Leyendo el fichero: " + idFichero);
        try (Scanner datosFichero = new Scanner(new File(idFichero), "ISO_8859_1")) {
            datosFichero.nextLine();
            // hasNextLine devuelve true mientras haya líneas por leer
            while (datosFichero.hasNextLine()) {
                // Guarda la línea completa en un String
                linea = datosFichero.nextLine();
                linea = linea.replaceAll("\"", "");//para quitar las comillas dobles
                // Se guarda en el array de String cada elemento de la linea 
                //separando con coma
                tokens = linea.split(",");
                //imprimo lineas
               // System.out.println(linea+"\t");
                //crear objeto empleado
                Empleado emp = new Empleado();

                emp.setNombre(tokens[0].concat(tokens[1]));
                emp.setDni(tokens[2]);
                emp.setPuesto(tokens[3]);

                emp.setFechaTomaPos(LocalDate.parse(tokens[4], DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                emp.setFechaCese(tokens[5].isEmpty() ? LocalDate.parse(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), DateTimeFormatter.ofPattern("dd/MM/yyyy")) : LocalDate.parse(tokens[5], DateTimeFormatter.ofPattern("dd/MM/yyyy")));

                emp.setTelfono(tokens[6]);
                emp.setEvaluador(Boolean.parseBoolean(tokens[7]));
                emp.setCoordinador(Boolean.parseBoolean(tokens[8]));
                empleados.add(emp);
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return empleados;
    }

    public static Map<String, Integer> GenerarMapaEmpleados(ArrayList<Empleado> lista) {
        Map<String, Integer> mapa = new HashMap<>();
        int cnt = 1;
        for (Empleado obj : lista) {
            if (mapa.containsKey(obj.getPuesto())) {
                cnt++;
                mapa.put(obj.getPuesto(), cnt);
            } else {
                mapa.put(obj.getPuesto(), 1);
            }
        }
        return mapa;
    }

    /*Guarda en otro fichero CSV los registros de aquellos empleados que hayan trabajado más de 100 días en el curso 20/21*/
    public static ArrayList<Empleado> generarTrabajadorplus100(ArrayList<Empleado> lista) {
        ArrayList<Empleado> listaAux = new ArrayList<>();
        for (Empleado obj : lista) {
            //si los dias son mas que 100 añadimos este trabajador a la lista
            if (daysBetween(obj.getFechaTomaPos(), obj.getFechaCese()) > 100) {
                listaAux.add(obj);
            }
        }
        return listaAux;
    }

    //para calcular los dias de trabajo entre la fecha ini y fin 
    public static int daysBetween(LocalDate dateIni, LocalDate dateFin) {
        int numberDays;
        LocalDate fechaIni=LocalDate.of(2020,1,1);
        LocalDate fechaFin=LocalDate.of(2021, 12, 31);
        //si la fecha de inicio que pasemos como parametro is after 2020 y la fecha de cese is befor 2021
        //entonces esta dentro del rango que queremos
        if (dateIni.isAfter(fechaIni) && dateFin.isBefore(fechaFin)) {
            //calcular numero de dias hay 
            numberDays = (int) ChronoUnit.DAYS.between(dateIni, dateFin);
        } else {
            numberDays = 0;
        }
        return numberDays;
        
    }

    public static void escrituraEnFicheroTrabajadoresMas100(ArrayList<Empleado> lista) {
        // Fichero a crear. Ruta relativa a la carpeta raíz del proyecto
        String idFichero = "registrosTrabajores100Dias.csv";
        ArrayList<Empleado> listaAux = generarTrabajadorplus100(lista);

        try (BufferedWriter flujo = new BufferedWriter(new FileWriter(idFichero))) {
            flujo.write("Nombre\tDNI/Pasaporte\tPuesto\tFecha de Toma\tFecha de Cese\tTeléfono\tEvaluador\tCoodinador\tDiasTrabajo");
            flujo.newLine();
            //recorrer la lista de empleados generada y escribir en el fichero
            for (Empleado obj : listaAux) {
                //añadir el numero de dias trabajan
                flujo.write(obj + "\t"+daysBetween(obj.getFechaTomaPos(),obj.getFechaCese()));
                flujo.newLine();
            }
            // Metodo fluh() guarda cambios en disco 
            flujo.flush();
            System.out.println("Fichero " + idFichero + " creado correctamente.");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void escrituraFichero() {
        // Fichero a crear. Ruta relativa a la carpeta raíz del proyecto
        String idFichero = "profesoresPorDepartamento.csv";
        Map<String, Integer> mapa = GenerarMapaEmpleados(lecturaFichero());

        try (BufferedWriter flujo = new BufferedWriter(new FileWriter(idFichero))) {
            for (Map.Entry<String, Integer> entry : mapa.entrySet()) {
                flujo.write(entry.getKey() + "\t" + entry.getValue());
                flujo.newLine();
            }

            // Metodo fluh() guarda cambios en disco 
            flujo.flush();
            System.out.println("Fichero " + idFichero + " creado correctamente.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
    

    public static void main(String[] args) {
        //lesta empleados a partir del fichero
        ArrayList<Empleado> empleados = lecturaFichero();

        //total profesores existen en el fichero
        System.out.println("el total de profesores existen es: " + empleados.size());
        //muestra la lista de empleados
        //empleados.forEach(System.out::println);
        System.out.println("**************mostrar la lista*********");
        empleados.forEach(p->System.out.println(p));

        /*Genera un map para almacenar cuantos profesores hay por cada departamento diferente. 
        Vuelca esta información en otro fichero CSV, llamado "profesoresPorDepartamento.csv", separando los campos con un tabulador.*/
        escrituraFichero();
        
        /*Guarda en otro fichero CSV los registros de aquellos empleados que hayan trabajado más de 100 días en el curso 20/21. */
        
        escrituraEnFicheroTrabajadoresMas100(empleados);
        
        //el premir metodo
        System.out.println(encuentraElempleado(empleados,"Arroyo Pretel Jean Paul"));
        
        //secand metodo
        System.out.println(numeroEmpleadosPorDep(empleados,"Matemáticas P.E.S."));
        
        //tercer metodo
        System.out.println(listaEmpleadoConNifDado(empleados,'V'));
        
        //la ultima metodo
        LocalDate fecha=LocalDate.of(2020, Month.SEPTEMBER, 16);
        System.out.println("metodo empleado fechacoincidencia");
        System.out.println(empleadosFechaCoicidencia(empleados,fecha));
        
        //sorted the arrayList using stream
        System.out.println("sorted the list usinf stream");
        Stream.of(empleados).sorted()
                .forEach(System.out::println);
         
        //sorted and mapear the arrayList
        /*System.out.println("test ");
        empleados.stream()
                .map(() -> Empleado::toLowerCase)
                .forEach(System.out::println);*/

    }
}
