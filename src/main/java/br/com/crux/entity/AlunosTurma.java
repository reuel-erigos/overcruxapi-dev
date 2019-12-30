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
@Table(name = "alunos_turma")
public class AlunosTurma  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_aluno_turma")
	@SequenceGenerator(name = "sq_id_aluno_turma", sequenceName = "sq_id_aluno_turma", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_aluno_turma")
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_aluno")
	private Aluno aluno;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_turma")
	private Turmas turma;

	@Column(name="dt_cadastro_turma")
	private LocalDateTime dataCadastro;

	@Column(name="dt_inicio_turma")
	private LocalDateTime dataInicio;
	
	@Column(name="dt_desvinculacao_turma")
	private LocalDateTime dataDesvinculacao;
	
	@Column(name="dt_alteracao_turma")
	private LocalDateTime dataAlteracao;
	
	@Column(name = "ds_desligamento")
	private String descricaoDesligamento;

	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;
	
	public AlunosTurma() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Turmas getTurma() {
		return turma;
	}

	public void setTurma(Turmas turma) {
		this.turma = turma;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public LocalDateTime getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDateTime getDataDesvinculacao() {
		return dataDesvinculacao;
	}

	public void setDataDesvinculacao(LocalDateTime dataDesvinculacao) {
		this.dataDesvinculacao = dataDesvinculacao;
	}

	public LocalDateTime getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(LocalDateTime dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public String getDescricaoDesligamento() {
		return descricaoDesligamento;
	}

	public void setDescricaoDesligamento(String descricaoDesligamento) {
		this.descricaoDesligamento = descricaoDesligamento;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}


	
}