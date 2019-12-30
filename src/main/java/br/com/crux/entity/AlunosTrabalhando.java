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
@Table(name = "alunos_trabalhando")
public class AlunosTrabalhando {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_aluno_trabalhando")
	@SequenceGenerator(name = "sq_id_aluno_trabalhando", sequenceName = "sq_id_aluno_trabalhando", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_aluno_trabalhando", unique = true, nullable = false, precision = 10)
	private Long id;

	@Column(name = "ds_tipo_empreendimento")
	private String descTipoEmpreendimento;

	@Column(name = "dt_fim_aluno_trabalhando")
	private LocalDateTime dataFimAlunoTrabalhando;

	@Column(name = "dt_inicio_aluno_trabalhando")
	private LocalDateTime dataInicioAlunoTrabalhando;

	@Column(name = "nm_empreendimento")
	private String nomeEmpreendimento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_aluno")
	private Aluno aluno;

	@Column(name = "id_usuario_apl")
	private Long usuarioAlteracao;

	public AlunosTrabalhando() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescTipoEmpreendimento() {
		return descTipoEmpreendimento;
	}

	public void setDescTipoEmpreendimento(String descTipoEmpreendimento) {
		this.descTipoEmpreendimento = descTipoEmpreendimento;
	}

	public LocalDateTime getDataFimAlunoTrabalhando() {
		return dataFimAlunoTrabalhando;
	}

	public void setDataFimAlunoTrabalhando(LocalDateTime dataFimAlunoTrabalhando) {
		this.dataFimAlunoTrabalhando = dataFimAlunoTrabalhando;
	}

	public LocalDateTime getDataInicioAlunoTrabalhando() {
		return dataInicioAlunoTrabalhando;
	}

	public void setDataInicioAlunoTrabalhando(LocalDateTime dataInicioAlunoTrabalhando) {
		this.dataInicioAlunoTrabalhando = dataInicioAlunoTrabalhando;
	}

	public String getNomeEmpreendimento() {
		return nomeEmpreendimento;
	}

	public void setNomeEmpreendimento(String nomeEmpreendimento) {
		this.nomeEmpreendimento = nomeEmpreendimento;
	}

	public Aluno getAluno() {
		return this.aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

}