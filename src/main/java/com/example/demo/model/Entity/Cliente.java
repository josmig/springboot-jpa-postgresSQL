package com.example.demo.model.Entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
//Con esto indicamos el nombre de la tabla
@Table(name = "clientes")
public class Cliente implements Serializable {

	// Es recomendable siempre implementar serializable en nuestras clases Entity en
	// JPA
	private static final long serialVersionUID = 1L;

	@Id
	// Con esto decimos que es un llave autoincrementada
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	@NotEmpty //validacion para string , no debe estar vacio es o que significa
	private String nombre;
	@Column
	@NotEmpty
	private String apellido;
	@Column
	@NotEmpty
	@Email
	private String email;

	@Column(name = "create_at") // asi esta en la BD por eso ponemos que esto
	//@NotNull //validacion para numeros
	@Temporal(TemporalType.TIMESTAMP)// indica el formato que se guardara la fecha en la BD
	private Date createAt;

	//Metodo para darle una fecha
	@PrePersist
	public void prePersist(){
		createAt = new Date();
	}


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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
