package br.com.crux.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.crux.infra.constantes.Constantes;


@Entity
@Table(name="materiais")
public class Material  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_material")
	@SequenceGenerator(name = "sq_id_material", sequenceName = "sq_id_material", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_material")
	private Long id;

	@Column(name="nm_material")
	private String nome;

	//Classificação do tipo do material (M = PERMANENTE; C = DE CONSUMO) 
	@Column(name="st_tipo_material")
	private String tipo;	
	
	@Column(name="nm_material_nf")
	private String nomeNotaFiscal;	

	@Column(name="cd_unidade_medida")
	private String codigoUnidadeMedida;	
	
	@Column(name="ds_unidade_medida")
	private String descricaoUnidadeMedida;	
	
	@Column(name="qtd_limite_pedido")
	private Integer quantidadeLimitePedido;	

	@Column(name="nr_vida_util")
	private Integer numeroVidaUtil;	
	
	@Column(name="vl_depreciacao")
	private Integer valorDepreciacao;	

	
	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;

	
	public Material() {
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public String getNomeNotaFiscal() {
		return nomeNotaFiscal;
	}


	public void setNomeNotaFiscal(String nomeNotaFiscal) {
		this.nomeNotaFiscal = nomeNotaFiscal;
	}


	public String getCodigoUnidadeMedida() {
		return codigoUnidadeMedida;
	}


	public void setCodigoUnidadeMedida(String codigoUnidadeMedida) {
		this.codigoUnidadeMedida = codigoUnidadeMedida;
	}


	public String getDescricaoUnidadeMedida() {
		return descricaoUnidadeMedida;
	}


	public void setDescricaoUnidadeMedida(String descricaoUnidadeMedida) {
		this.descricaoUnidadeMedida = descricaoUnidadeMedida;
	}


	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}


	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}


	public Integer getQuantidadeLimitePedido() {
		return quantidadeLimitePedido;
	}


	public void setQuantidadeLimitePedido(Integer quantidadeLimitePedido) {
		this.quantidadeLimitePedido = quantidadeLimitePedido;
	}


	public Integer getNumeroVidaUtil() {
		return numeroVidaUtil;
	}


	public void setNumeroVidaUtil(Integer numeroVidaUtil) {
		this.numeroVidaUtil = numeroVidaUtil;
	}


	public Integer getValorDepreciacao() {
		return valorDepreciacao;
	}


	public void setValorDepreciacao(Integer valorDepreciacao) {
		this.valorDepreciacao = valorDepreciacao;
	}

	
	
}