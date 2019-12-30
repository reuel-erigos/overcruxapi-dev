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


/**
 * The persistent class for the departamentos_unidades database table.
 * 
 */
@Entity
@Table(name="departamentos_unidades")
public class Departamentos implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_departamento")
	@SequenceGenerator(name = "sq_id_departamento", sequenceName = "sq_id_departamento", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_departamento")
	private Long idDepartamento;

	@Column(name="cd_unidade_departamento")
	private String cdUnidadeDepartamento;

	@Column(name="ds_endereco_departamento")
	private String dsEnderecoDepartamento;

	@Column(name="nm_departamento")
	private String nmDepartamento;

	@Column(name="nr_telefone_departamento")
	private String nrTelefoneDepartamento;
	
	//bi-directional many-to-one association to DepartamentosUnidade
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_departamento_sup")
	private Departamentos departamentosSuperior;

	//bi-directional many-to-one association to Unidade
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_unidade")
	private Unidade unidade;

	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;

	public Departamentos() {
	}

	public Long getIdDepartamento() {
		return this.idDepartamento;
	}

	public void setIdDepartamento(Long idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public String getCdUnidadeDepartamento() {
		return this.cdUnidadeDepartamento;
	}

	public void setCdUnidadeDepartamento(String cdUnidadeDepartamento) {
		this.cdUnidadeDepartamento = cdUnidadeDepartamento;
	}

	public String getDsEnderecoDepartamento() {
		return this.dsEnderecoDepartamento;
	}

	public void setDsEnderecoDepartamento(String dsEnderecoDepartamento) {
		this.dsEnderecoDepartamento = dsEnderecoDepartamento;
	}

	public String getNmDepartamento() {
		return this.nmDepartamento;
	}

	public void setNmDepartamento(String nmDepartamento) {
		this.nmDepartamento = nmDepartamento;
	}

	public String getNrTelefoneDepartamento() {
		return this.nrTelefoneDepartamento;
	}

	public void setNrTelefoneDepartamento(String nrTelefoneDepartamento) {
		this.nrTelefoneDepartamento = nrTelefoneDepartamento;
	}

	public Departamentos getDepartamentosSuperior() {
		return this.departamentosSuperior;
	}

	public void setDepartamentosSuperior(Departamentos departamentosUnidade) {
		this.departamentosSuperior = departamentosUnidade;
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

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

}