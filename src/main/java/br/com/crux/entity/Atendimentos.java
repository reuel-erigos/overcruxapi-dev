package br.com.crux.entity;

import java.time.LocalDateTime;

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
@Table(name="atendimentos")
public class Atendimentos {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_atendimento")
	@SequenceGenerator(name = "sq_id_atendimento", sequenceName = "sq_id_atendimento", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_atendimento", unique=true, nullable=false, precision=10)
	private Long id;

	@Column(name="ds_diagnostico")
	private String descDiagnostico;

	@Column(name="ds_solucao")
	private String descSolucao;

	@Column(name="dt_atendimento")
	private LocalDateTime dataAtendimento;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="solucoes_id_solucao")
	private Solucoes solucoes;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="aluno_id_aluno")
	private Aluno aluno;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="diagnosticos_id_diagnostico")
	private Diagnosticos diagnostico;

	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;

	public Atendimentos() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescDiagnostico() {
		return descDiagnostico;
	}

	public void setDescDiagnostico(String descDiagnostico) {
		this.descDiagnostico = descDiagnostico;
	}

	public String getDescSolucao() {
		return descSolucao;
	}

	public void setDescSolucao(String descSolucao) {
		this.descSolucao = descSolucao;
	}

	public LocalDateTime getDataAtendimento() {
		return dataAtendimento;
	}

	public void setDataAtendimento(LocalDateTime dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}

	public Solucoes getSolucoes() {
		return solucoes;
	}

	public void setSolucoes(Solucoes solucoe) {
		this.solucoes = solucoe;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Diagnosticos getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(Diagnosticos diagnostico) {
		this.diagnostico = diagnostico;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}


}