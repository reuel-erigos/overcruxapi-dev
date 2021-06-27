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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.crux.infra.constantes.Constantes;


@Entity
@Table(name = "rateios_categorias_movimentacoes")
public class RateiosCategoriasMovimentos implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_rateio_categoria_movimentacao")
	@SequenceGenerator(name = "sq_id_rateio_categoria_movimentacao", sequenceName = "sq_id_rateio_categoria_movimentacao", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_rateio_categoria_movimentacao")
	private Long id;

	@Column(name = "id_categoria_movimentacao")
	private Long idCategoriaMovimento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_programa") 
	private Programa programa;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_projeto") 
	private Projeto projeto;
	
	@Column(name = "vl_rateio")
	private Double valorRateio;
	
	@Column(name = "id_usuario_apl")
	private Long usuarioAlteracao;

	
	public RateiosCategoriasMovimentos() {
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getIdCategoriaMovimento() {
		return idCategoriaMovimento;
	}


	public void setIdCategoriaMovimento(Long idCategoriaMovimento) {
		this.idCategoriaMovimento = idCategoriaMovimento;
	}


	public Programa getPrograma() {
		return programa;
	}


	public void setPrograma(Programa programa) {
		this.programa = programa;
	}


	public Projeto getProjeto() {
		return projeto;
	}


	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}


	public Double getValorRateio() {
		return valorRateio;
	}


	public void setValorRateio(Double valorRateio) {
		this.valorRateio = valorRateio;
	}


	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}


	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	
}