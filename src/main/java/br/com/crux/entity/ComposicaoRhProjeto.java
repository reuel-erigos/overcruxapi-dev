package br.com.crux.entity;

import java.math.BigDecimal;

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
@Table(name="composicao_rh_projeto")
public class ComposicaoRhProjeto  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_composicao_projeto")
	@SequenceGenerator(name = "sq_id_composicao_projeto", sequenceName = "sq_id_composicao_projeto", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_composicao_projeto")
	private Long id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_cargo")
	private Cargo cargo;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_projeto")
	private Projeto projeto;

	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;

	@Column(name="qtd")
	private BigDecimal qtd;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_tipo_contratacao")
	private TiposContratacoes tiposContratacoes;

	public ComposicaoRhProjeto() {
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

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public BigDecimal getQtd() {
		return qtd;
	}

	public void setQtd(BigDecimal qtd) {
		this.qtd = qtd;
	}

	public TiposContratacoes getTiposContratacoes() {
		return tiposContratacoes;
	}

	public void setTiposContratacoes(TiposContratacoes tiposContratacoes) {
		this.tiposContratacoes = tiposContratacoes;
	}
	
	

}