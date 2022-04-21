/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea7AHind;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author hinda
 */
public class Empleado {
    //Atributos
   private String nombre;
    private String dni;
    private String puesto;
    private LocalDate fechaTomaPos;
    private LocalDate fechaCese;
    private String telfono;
    private boolean evaluador;
    private boolean coordinador;
    
    //constrector
    public Empleado() {
    }
    
    //getters y setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public LocalDate getFechaTomaPos() {
        return fechaTomaPos;
    }

    public void setFechaTomaPos(LocalDate fechaTomaPos) {
        this.fechaTomaPos = fechaTomaPos;
    }

    public LocalDate getFechaCese() {
        return fechaCese;
    }

    public void setFechaCese(LocalDate fechaCese) {
        this.fechaCese = fechaCese;
    }

    public String getTelfono() {
        return telfono;
    }

    public void setTelfono(String telfono) {
        this.telfono = telfono;
    }

    public boolean isEvaluador() {
        return evaluador;
    }

    public void setEvaluador(boolean evaluador) {
        this.evaluador = evaluador;
    }

    public boolean isCoordinador() {
        return coordinador;
    }

    public void setCoordinador(boolean coordinador) {
        this.coordinador = coordinador;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.nombre);
        hash = 31 * hash + Objects.hashCode(this.dni);
        hash = 31 * hash + Objects.hashCode(this.puesto);
        hash = 31 * hash + Objects.hashCode(this.fechaTomaPos);
        hash = 31 * hash + Objects.hashCode(this.fechaCese);
        hash = 31 * hash + Objects.hashCode(this.telfono);
        hash = 31 * hash + (this.evaluador ? 1 : 0);
        hash = 31 * hash + (this.coordinador ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Empleado other = (Empleado) obj;
        if (this.evaluador != other.evaluador) {
            return false;
        }
        if (this.coordinador != other.coordinador) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.dni, other.dni)) {
            return false;
        }
        if (!Objects.equals(this.puesto, other.puesto)) {
            return false;
        }
        if (!Objects.equals(this.telfono, other.telfono)) {
            return false;
        }
        if (!Objects.equals(this.fechaTomaPos, other.fechaTomaPos)) {
            return false;
        }
        if (!Objects.equals(this.fechaCese, other.fechaCese)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  nombre + " " + 
                dni + " " + puesto + " " + 
                fechaTomaPos + " " + fechaCese + " " + 
                telfono + " " + evaluador + " " + coordinador;
    }

   
    
    
    
    
}
