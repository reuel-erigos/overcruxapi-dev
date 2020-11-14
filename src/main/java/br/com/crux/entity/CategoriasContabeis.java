package br.com.crux.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
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
import br.com.crux.infra.dao.SimNaoConverter;


@Entity
@Table(name="categorias_contabeis")
public class CategoriasContabeis  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_categoria")
	@SequenceGenerator(name = "sq_id_categoria", sequenceName = "sq_id_categoria", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_categoria")
	private Long id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_categoria_superior")
	private CategoriasContabeis categoriaSuperior;
	
	//Classificação do tipo categoria (D = DESPESA; R = RECEITA) 
	@Column(name="st_tipo_categoria")
	private String tipo;	

	@Column(name="nm_categoria")
	private String nome;

	@Column(name="ds_categoria")
	private String descricaoCategoria;
		
	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;
	
	@Column(name="cd_categoria_contabil")
	private String codigoCategoriaContabil;
	
	
	@Column(name="id_instituicao", nullable = true)
	private Long idInstituicao;
	
	@Convert(converter = SimNaoConverter.class)
	@Column(name="st_categoria_sintetica")
	private Boolean sintetica;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CategoriasContabeis getCategoriaSuperior() {
		return categoriaSuperior;
	}

	public void setCategoriaSuperior(CategoriasContabeis categoriaSuperior) {
		this.categoriaSuperior = categoriaSuperior;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricaoCategoria() {
		return descricaoCategoria;
	}

	public void setDescricaoCategoria(String descricaoCategoria) {
		this.descricaoCategoria = descricaoCategoria;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public String getCodigoCategoriaContabil() {
		return codigoCategoriaContabil;
	}

	public void setCodigoCategoriaContabil(String codigoCategoriaContabil) {
		this.codigoCategoriaContabil = codigoCategoriaContabil;
	}

	public Long getIdInstituicao() {
		return idInstituicao;
	}

	public void setIdInstituicao(Long idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	public Boolean getSintetica() {
		return sintetica;
	}

	public void setSintetica(Boolean sintetica) {
		this.sintetica = sintetica;
	}

	
}