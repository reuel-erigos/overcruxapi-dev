package br.com.crux.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the centros_custo database table.
 * 
 */
@Entity
@Table(name="centros_custo")
@NamedQuery(name="CentrosCusto.findAll", query="SELECT c FROM CentrosCusto c")
public class CentrosCusto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_centro_custo")
	private Long idCentroCusto;

	@Column(name="nm_centro_custo")
	private String nmCentroCusto;

	@Column(name="nr_centro_custo")
	private String nrCentroCusto;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_usuario_apl")
	private UsuariosSistema usuariosSistema;

	public CentrosCusto() {
	}

	public Long getIdCentroCusto() {
		return this.idCentroCusto;
	}

	public void setIdCentroCusto(Long idCentroCusto) {
		this.idCentroCusto = idCentroCusto;
	}

	public String getNmCentroCusto() {
		return this.nmCentroCusto;
	}

	public void setNmCentroCusto(String nmCentroCusto) {
		this.nmCentroCusto = nmCentroCusto;
	}

	public String getNrCentroCusto() {
		return this.nrCentroCusto;
	}

	public void setNrCentroCusto(String nrCentroCusto) {
		this.nrCentroCusto = nrCentroCusto;
	}

	public UsuariosSistema getUsuariosSistema() {
		return this.usuariosSistema;
	}

	public void setUsuariosSistema(UsuariosSistema usuariosSistema) {
		this.usuariosSistema = usuariosSistema;
	}

}