package backEnd.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Actividad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    private String encargado;
    private String horario;
    private int edadMinima;
    private String lugar;
    private int cupo;
    private boolean permiteMenores;

    @ManyToMany
    private List<Socio> inscriptos = new ArrayList<>();

    public Actividad() {
    }

    public Actividad(String nombre, String encargado, String horario, int edadMinima, String lugar, int cupo, boolean permiteMenores) {
        this.nombre = nombre;
        this.encargado = encargado;
        this.horario = horario;
        this.edadMinima = edadMinima;
        this.lugar = lugar;
        this.cupo = cupo;
        this.permiteMenores = permiteMenores;
    }

    // Métodos de inscripción
    public boolean agregarInscripcion(Socio socio) {
        if (socio.getEdad() < edadMinima || inscriptos.size() >= cupo) {
            return false;
        }
        return inscriptos.add(socio);
    }

    public boolean eliminarInscripcion(Socio socio) {
        return inscriptos.remove(socio);
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEncargado() {
        return encargado;
    }

    public void setEncargado(String encargado) {
        this.encargado = encargado;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public int getEdadMinima() {
        return edadMinima;
    }

    public void setEdadMinima(int edadMinima) {
        this.edadMinima = edadMinima;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public int getCupo() {
        return cupo;
    }

    public void setCupo(int cupo) {
        this.cupo = cupo;
    }

    public List<Socio> getInscriptos() {
        return inscriptos;
    }

    public void setInscriptos(List<Socio> inscriptos) {
        this.inscriptos = inscriptos;
    }

    @Override
    public String toString() {
        return nombre + " * " + lugar;
    }

    public boolean isPermiteMenores() {
        return this.permiteMenores;
    }
}
