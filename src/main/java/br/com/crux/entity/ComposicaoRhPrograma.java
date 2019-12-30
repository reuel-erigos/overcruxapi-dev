package br.com.crux.entity;

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
@Table(name="composicao_rh_programa")
public class ComposicaoRhPrograma  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_composicao")
	@SequenceGenerator(name = "sq_id_composicao", sequenceName = "sq_id_composicao", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_composicao")
	private Long id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_cargo")
	private Cargo cargo;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_programa")
	private Programa programa;

	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;

	@Column(name="qtd")
	private Long qtd;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_tipo_contratacao")
	private TiposContratacoes tiposContratacoes;

	public ComposicaoRhPrograma() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public Long getQtd() {
		return qtd;
	}

	public void setQtd(Long qtd) {
		this.qtd = qtd;
	}

	public TiposContratacoes getTiposContratacoes() {
		return tiposContratacoes;
	}

	public void setTiposContratacoes(TiposContratacoes tiposContratacoe) {
		this.tiposContratacoes = tiposContratacoe;
	}
	
	


}