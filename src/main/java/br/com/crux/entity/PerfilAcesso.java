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
 * The persistent class for the perfis_acessos database table.
 * 
 */
@Entity
@Table(name="perfis_acessos")
public class PerfilAcesso {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_perfil_acesso")
	@SequenceGenerator(name = "sq_id_perfil_acesso", sequenceName = "sq_id_perfil_acesso", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_perfil_acesso")
	private Long id;

	@Column(name="cs_altera")
	@Convert(converter = SimNaoConverter.class)
	private Boolean altera;

	@Column(name="cs_consulta")
	@Convert(converter = SimNaoConverter.class)
	private Boolean consulta;

	@Column(name="cs_deleta")
	@Convert(converter = SimNaoConverter.class)
	private Boolean deleta;

	@Column(name="cs_insere")
	@Convert(converter = SimNaoConverter.class)
	private Boolean insere;

	@Column(name="nm_perfil_acesso")
	private String nmPerfilAcesso;

	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;

	public PerfilAcesso() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long idPerfilAcesso) {
		this.id = idPerfilAcesso;
	}

	public Boolean getAltera() {
		return this.altera;
	}

	public void setAltera(Boolean csAltera) {
		this.altera = csAltera;
	}

	public Boolean getConsulta() {
		return this.consulta;
	}

	public void setConsulta(Boolean csConsulta) {
		this.consulta = csConsulta;
	}

	public Boolean getDeleta() {
		return this.deleta;
	}

	public void setDeleta(Boolean csDeleta) {
		this.deleta = csDeleta;
	}

	public Boolean getInsere() {
		return this.insere;
	}

	public void setInsere(Boolean csInsere) {
		this.insere = csInsere;
	}

	public String getNmPerfilAcesso() {
		return this.nmPerfilAcesso;
	}

	public void setNmPerfilAcesso(String nmPerfilAcesso) {
		this.nmPerfilAcesso = nmPerfilAcesso;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuariosSistema) {
		this.usuarioAlteracao = usuariosSistema;
	}
	

}