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
@Table(name="encaminha_alunos")
public class EncaminhaAlunos  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_encaminha_aluno")
	@SequenceGenerator(name = "sq_id_encaminha_aluno", sequenceName = "sq_id_encaminha_aluno", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_encaminha_aluno")
	private Long id;

	@Column(name="ds_encaminha_aluno")
	private String descricao;

	@Column(name="dt_encaminha_aluno")
	private LocalDateTime dataEncaminhaAluno;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_aluno")
	private Aluno aluno;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_entidade_social")
	private EntidadesSociais entidadesSocial;

	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;

	public EncaminhaAlunos() {
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

	public LocalDateTime getDataEncaminhaAluno() {
		return dataEncaminhaAluno;
	}

	public void setDataEncaminhaAluno(LocalDateTime dataEncaminhaAluno) {
		this.dataEncaminhaAluno = dataEncaminhaAluno;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public EntidadesSociais getEntidadesSocial() {
		return entidadesSocial;
	}

	public void setEntidadesSocial(EntidadesSociais entidadesSociai) {
		this.entidadesSocial = entidadesSociai;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}


}