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
	
	@Column(name="nr_tombamento")
	private String numeroTombamento;	

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


	public String getNumeroTombamento() {
		return numeroTombamento;
	}


	public void setNumeroTombamento(String numeroTombamento) {
		this.numeroTombamento = numeroTombamento;
	}


	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}


	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	
}