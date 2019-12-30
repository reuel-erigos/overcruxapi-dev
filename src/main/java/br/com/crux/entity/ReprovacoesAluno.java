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
@Table(name="reprovacoes_aluno")
public class ReprovacoesAluno {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_reprovacao_aluno")
	@SequenceGenerator(name = "sq_id_reprovacao_aluno", sequenceName = "sq_id_reprovacao_aluno", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_reprovacao_aluno")
	private Long id;

	@Column(name="ds_reprovacao_aluno")
	private String descricaoMotivo;

	@Column(name="ds_serie_reprovacao")
	private String serieReprovacao;

	@Column(name="dt_reprovacao_aluno")
	private LocalDateTime dataReprovacao;

	@Column(name="qtd_reprovacao")
	private Long qtdReprovacao;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_aluno")
	private Aluno aluno;

	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;

	public ReprovacoesAluno() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricaoMotivo() {
		return descricaoMotivo;
	}

	public void setDescricaoMotivo(String descricaoMotivo) {
		this.descricaoMotivo = descricaoMotivo;
	}

	public String getSerieReprovacao() {
		return serieReprovacao;
	}

	public void setSerieReprovacao(String serieReprovacao) {
		this.serieReprovacao = serieReprovacao;
	}

	public LocalDateTime getDataReprovacao() {
		return dataReprovacao;
	}

	public void setDataReprovacao(LocalDateTime dataReprovacao) {
		this.dataReprovacao = dataReprovacao;
	}

	public Long getQtdReprovacao() {
		return qtdReprovacao;
	}

	public void setQtdReprovacao(Long qtdReprovacao) {
		this.qtdReprovacao = qtdReprovacao;
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