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


@Entity
@Table(name="perspectivas")
public class Perspectiva implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_perspectiva")
	@SequenceGenerator(name = "sq_id_perspectiva", sequenceName = "sq_id_perspectiva", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_perspectiva")
	private Long idPerspectiva;

	@Column(name="nm_perspectiva")
	private String nmPerspectiva;

	@Column(name="dt_implantacao")
	private LocalDateTime dtImplantacao;

	@Column(name="dt_termino")
	private LocalDateTime dtTermino;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_unidade")
	private Unidade unidade;

	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;

	public Perspectiva() {
	}

	public Long getIdPerspectiva() {
		return this.idPerspectiva;
	}

	public void setIdPerspectiva(Long idPerspectiva) {
		this.idPerspectiva = idPerspectiva;
	}

	public LocalDateTime getDtImplantacao() {
		return this.dtImplantacao;
	}

	public void setDtImplantacao(LocalDateTime dtImplantacao) {
		this.dtImplantacao = dtImplantacao;
	}

	public LocalDateTime getDtTermino() {
		return this.dtTermino;
	}

	public void setDtTermino(LocalDateTime dtTermino) {
		this.dtTermino = dtTermino;
	}

	public String getNmPerspectiva() {
		return this.nmPerspectiva;
	}

	public void setNmPerspectiva(String nmPerspectiva) {
		this.nmPerspectiva = nmPerspectiva;
	}

	public Unidade getUnidade() {
		return this.unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public Long getUsuarioAlteracao() {
		return this.usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuariosAlteracao) {
		this.usuarioAlteracao = usuariosAlteracao;
	}

}