package br.com.crux.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.crux.infra.constantes.Constantes;
import br.com.crux.infra.dao.SimNaoConverter;


/**
 * The persistent class for the situacoes_vulnerabilidades database table.
 * 
 */
@Entity
@Table(name="situacoes_vulnerabilidades")
public class SituacoesVulnerabilidade {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_vulnerabilidade")
	@SequenceGenerator(name = "sq_id_vulnerabilidade", sequenceName = "sq_id_vulnerabilidade", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_vulnerabilidade")
	private Long id;

	@Column(name="ds_situacao_vulnerabilidade")
	private String descricao;

	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;

	@Convert(converter = SimNaoConverter.class)
	@Column(name = "st_sigilo")
	private Boolean sigiloso;
	
	
	public SituacoesVulnerabilidade() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public Boolean getSigiloso() {
		return sigiloso;
	}

	public void setSigiloso(Boolean sigiloso) {
		this.sigiloso = sigiloso;
	}

	

}