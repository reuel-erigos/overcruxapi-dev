package br.com.crux.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.crux.infra.constantes.Constantes;


/**
 * The persistent class for the objetivos database table.
 * 
 */
@Entity
@Table(name="objetivos")
public class Objetivo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_objetivo")
	@SequenceGenerator(name = "sq_id_objetivo", sequenceName = "sq_id_objetivo", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_objetivo")
	private Long idObjetivo;

	@Column(name="nm_objetivo")
	private String nome;

	@Column(name="dt_implantacao")
	private LocalDateTime dataImplantacao;

	@Column(name="dt_termino")
	private LocalDateTime dataTermino;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_perspectiva")
	private Perspectiva perspectiva;

	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;

	public Objetivo() {
	}

	public Long getIdObjetivo() {
		return this.idObjetivo;
	}

	public void setIdObjetivo(Long idObjetivo) {
		this.idObjetivo = idObjetivo;
	}

	public LocalDateTime getDataImplantacao() {
		return this.dataImplantacao;
	}

	public void setDataImplantacao(LocalDateTime dtImplantacao) {
		this.dataImplantacao = dtImplantacao;
	}

	public LocalDateTime getDataTermino() {
		return this.dataTermino;
	}

	public void setDataTermino(LocalDateTime dtTermino) {
		this.dataTermino = dtTermino;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nmObjetivo) {
		this.nome = nmObjetivo;
	}

	public Perspectiva getPerspectiva() {
		return this.perspectiva;
	}

	public void setPerspectiva(Perspectiva perspectiva) {
		this.perspectiva = perspectiva;
	}

	public Long getUsuarioAlteracao() {
		return this.usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuariosSistema) {
		this.usuarioAlteracao = usuariosSistema;
	}

}