package backEnd.entities;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import jakarta.persistence.*;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Entity
@Table(name = "socios")
public class Socio {

	@Id
	@Column(length = 15)
	@NotBlank(message = "El DNI no puede estar vacío")
	@CsvBindByName(column = "dni")
	private String dni;

	@NotBlank(message = "El apellido no puede estar vacío")
	@CsvBindByName(column = "apellido")
	private String apellido;

	@NotBlank(message = "El nombre no puede estar vacío")
	@CsvBindByName(column = "nombre")
	private String nombre;

	@Min(value = 1, message = "La edad mínima es 1")
	@Max(value = 99, message = "La edad máxima es 99")
	@CsvBindByName(column = "edad")
	private int edad = 18;

	@NotNull(message = "La fecha de nacimiento es obligatoria")
	@CsvBindByName(column = "fechaNacimiento")
	@CsvDate("yyyy-MM-dd")
	private LocalDate fechaNacimiento;

	@NotBlank(message = "La dirección no puede estar vacía")
	@CsvBindByName(column = "direccion")
	private String direccion;

	@NotBlank(message = "El teléfono no puede estar vacío")
	@CsvBindByName(column = "telefono")
	private String telefono;

	public Socio() {}

	public Socio(String dni, String apellido, String nombre, int edad, LocalDate fechaNacimiento, String direccion, String telefono) {
		this.dni = dni;
		this.apellido = apellido;
		this.nombre = nombre;
		this.edad = edad;
		this.fechaNacimiento = fechaNacimiento;
		this.direccion = direccion;
		this.telefono = telefono;
	}

	// Getters y Setters

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	@Override
	public String toString() {
		return "Socio{" +
				"dni='" + dni + '\'' +
				", apellido='" + apellido + '\'' +
				", nombre='" + nombre + '\'' +
				", edad=" + edad +
				", fechaNacimiento=" + fechaNacimiento +
				", direccion='" + direccion + '\'' +
				", telefono='" + telefono + '\'' +
				'}';
	}
}
