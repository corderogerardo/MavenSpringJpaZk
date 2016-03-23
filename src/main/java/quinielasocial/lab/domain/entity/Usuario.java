package quinielasocial.lab.domain.entity;
// Generated 23/03/2016 01:44:51 AM by Hibernate Tools 4.3.1.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Usuario generated by hbm2java
 */
@Entity
@Table(name = "usuario", schema = "public")
public class Usuario implements java.io.Serializable {

	private long usuarioId;
	private Persona persona;
	private Rol rol;
	private String contrasena;
	private Date fechaIngreso;
	private Boolean estado;

	public Usuario() {
	}

	public Usuario(long usuarioId, Persona persona, Rol rol, String contrasena, Date fechaIngreso) {
		this.usuarioId = usuarioId;
		this.persona = persona;
		this.rol = rol;
		this.contrasena = contrasena;
		this.fechaIngreso = fechaIngreso;
	}

	public Usuario(long usuarioId, Persona persona, Rol rol, String contrasena, Date fechaIngreso, Boolean estado) {
		this.usuarioId = usuarioId;
		this.persona = persona;
		this.rol = rol;
		this.contrasena = contrasena;
		this.fechaIngreso = fechaIngreso;
		this.estado = estado;
	}

	@Id

	@Column(name = "usuario_id", unique = true, nullable = false)
	public long getUsuarioId() {
		return this.usuarioId;
	}

	public void setUsuarioId(long usuarioId) {
		this.usuarioId = usuarioId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "correo", nullable = false)
	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idrol", nullable = false)
	public Rol getRol() {
		return this.rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	@Column(name = "contrasena", nullable = false)
	public String getContrasena() {
		return this.contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_ingreso", nullable = false, length = 29)
	public Date getFechaIngreso() {
		return this.fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	@Column(name = "estado")
	public Boolean getEstado() {
		return this.estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

}
