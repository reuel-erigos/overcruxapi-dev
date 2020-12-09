package br.com.crux.entity;

import java.io.Serializable;
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
@Table(name="situacoes_ex_alunos")
public class SituacaoExAluno implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_situacao_aluno")
	@SequenceGenerator(name = "sq_id_situacao_aluno", sequenceName = "sq_id_situacao_aluno", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_situacao_aluno")
	private Long id;
	
	@Column(name="ds_profissao_atual")
	private String profissao;

	@Column(name="ds_local_trabalho")
	private String localTrabalho;

	@Column(name="ds_condicao_atual")
	private String condicaoAtual;
	
	@Column(name="dt_avaliacao")
	private LocalDateTime dataAvaliacao;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_aluno")
	private Aluno aluno;
	
	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;
	
	
	public SituacaoExAluno() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long idPrograma) {
		this.id = idPrograma;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public String getLocalTrabalho() {
		return localTrabalho;
	}

	public void setLocalTrabalho(String localTrabalho) {
		this.localTrabalho = localTrabalho;
	}

	public String getCondicaoAtual() {
		return condicaoAtual;
	}

	public void setCondicaoAtual(String condicaoAtual) {
		this.condicaoAtual = condicaoAtual;
	}

	public LocalDateTime getDataAvaliacao() {
		return dataAvaliacao;
	}

	public void setDataAvaliacao(LocalDateTime dataAvaliacao) {
		this.dataAvaliacao = dataAvaliacao;
	}

	public Aluno getAluno() {
		return aluno;
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