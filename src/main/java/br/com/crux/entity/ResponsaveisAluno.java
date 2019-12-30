package br.com.crux.entity;

import java.time.LocalDateTime;

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


/**
 * The persistent class for the responsaveis_alunos database table.
 * 
 */
@Entity
@Table(name="responsaveis_alunos")
public class ResponsaveisAluno  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_responsavel_aluno")
	@SequenceGenerator(name = "sq_id_responsavel_aluno", sequenceName = "sq_id_responsavel_aluno", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_responsavel_aluno")
	private Long id;

	@Column(name="ds_desligamento")
	private String descDesligamento;

	@Column(name="ds_grau_parentesco")
	private String descGrauParentesco;

	@Column(name="dt_desvinculacao")
	private LocalDateTime dataDesvinculacao;

	@Column(name="dt_vinculacao")
	private LocalDateTime dataVinculacao;

	@Column(name="st_mesmo_ender_resp")
	private String mesmoEnderResponsavel;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_aluno")
	private Aluno aluno;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_familiar")
	private Familiares familiar;

	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;
		
	@Column(name="st_transporta_aluno")
	@Convert(converter = SimNaoConverter.class)
	private Boolean transportaAluno;

	@Column(name="st_tutela_aluno")
	@Convert(converter = SimNaoConverter.class)
	private Boolean tutelaAluno;

	@Column(name="st_resp_fin_aluno")
	@Convert(converter = SimNaoConverter.class)
	private Boolean responsavelFinanceiroPeloAluno;
	
	

	public ResponsaveisAluno() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescDesligamento() {
		return descDesligamento;
	}

	public void setDescDesligamento(String descDesligamento) {
		this.descDesligamento = descDesligamento;
	}

	public String getDescGrauParentesco() {
		return descGrauParentesco;
	}

	public void setDescGrauParentesco(String descGrauParentesco) {
		this.descGrauParentesco = descGrauParentesco;
	}

	public LocalDateTime getDataDesvinculacao() {
		return dataDesvinculacao;
	}

	public void setDataDesvinculacao(LocalDateTime dataDesvinculacao) {
		this.dataDesvinculacao = dataDesvinculacao;
	}

	public LocalDateTime getDataVinculacao() {
		return dataVinculacao;
	}

	public void setDataVinculacao(LocalDateTime dataVinculacao) {
		this.dataVinculacao = dataVinculacao;
	}

	public String getMesmoEnderResponsavel() {
		return mesmoEnderResponsavel;
	}

	public void setMesmoEnderResponsavel(String mesmoEnderResponsavel) {
		this.mesmoEnderResponsavel = mesmoEnderResponsavel;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Familiares getFamiliar() {
		return familiar;
	}

	public void setFamiliar(Familiares familiar) {
		this.familiar = familiar;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public Boolean getTransportaAluno() {
		return transportaAluno;
	}

	public void setTransportaAluno(Boolean transportaAluno) {
		this.transportaAluno = transportaAluno;
	}

	public Boolean getTutelaAluno() {
		return tutelaAluno;
	}

	public void setTutelaAluno(Boolean tutelaAluno) {
		this.tutelaAluno = tutelaAluno;
	}

	public Boolean getResponsavelFinanceiroPeloAluno() {
		return responsavelFinanceiroPeloAluno;
	}

	public void setResponsavelFinanceiroPeloAluno(Boolean responsavelFinanceiroPeloAluno) {
		this.responsavelFinanceiroPeloAluno = responsavelFinanceiroPeloAluno;
	}

	

}