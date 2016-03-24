package quinielasocial.lab.domain.entity;
// Generated 23/03/2016 01:44:51 AM by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Mensajejugador generated by hbm2java
 */
@Entity
@Table(name = "mensajejugador", schema = "public")
public class Mensajejugador implements java.io.Serializable {

	private long mensajejugadorId;
	private Mensaje mensaje;
	private Persona personaByCedulades;
	private Persona personaByCedularem;

	public Mensajejugador() {
	}

	public Mensajejugador(long mensajejugadorId, Mensaje mensaje) {
		this.mensajejugadorId = mensajejugadorId;
		this.mensaje = mensaje;
	}

	public Mensajejugador(long mensajejugadorId, Mensaje mensaje, Persona personaByCedulades,
			Persona personaByCedularem) {
		this.mensajejugadorId = mensajejugadorId;
		this.mensaje = mensaje;
		this.personaByCedulades = personaByCedulades;
		this.personaByCedularem = personaByCedularem;
	}

	@Id

	@Column(name = "mensajejugador_id", unique = true, nullable = false)
	public long getMensajejugadorId() {
		return this.mensajejugadorId;
	}

	public void setMensajejugadorId(long mensajejugadorId) {
		this.mensajejugadorId = mensajejugadorId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idmensajejugador", nullable = false)
	public Mensaje getMensaje() {
		return this.mensaje;
	}

	public void setMensaje(Mensaje mensaje) {
		this.mensaje = mensaje;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cedulades")
	public Persona getPersonaByCedulades() {
		return this.personaByCedulades;
	}

	public void setPersonaByCedulades(Persona personaByCedulades) {
		this.personaByCedulades = personaByCedulades;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cedularem")
	public Persona getPersonaByCedularem() {
		return this.personaByCedularem;
	}

	public void setPersonaByCedularem(Persona personaByCedularem) {
		this.personaByCedularem = personaByCedularem;
	}

}