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
@Table(name="tipos_contratacoes")
public class TiposContratacoes  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_tipo_contratacao")
	@SequenceGenerator(name = "sq_id_tipo_contratacao", sequenceName = "sq_id_tipo_contratacao", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_tipo_contratacao")
	private Long id;

	@Column(name="cd_tipo_contratacao")
	private String codigoTipoContratacao;

	@Column(name="ds_tipo_contratacao")
	private String descricaoTipoContratacao;

	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;


	public TiposContratacoes() {
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getCodigoTipoContratacao() {
		return codigoTipoContratacao;
	}


	public void setCodigoTipoContratacao(String codigoTipoContratacao) {
		this.codigoTipoContratacao = codigoTipoContratacao;
	}


	public String getDescricaoTipoContratacao() {
		return descricaoTipoContratacao;
	}


	public void setDescricaoTipoContratacao(String descricaoTipoContratacao) {
		this.descricaoTipoContratacao = descricaoTipoContratacao;
	}


	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}


	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}


}