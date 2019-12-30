package br.com.crux.to;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.crux.infra.adapter.LocalDateTimeAdapter;

public class ReprovacoesAlunoTO {

	private Long id;
	private String descricaoMotivo;
	private String serieReprovacao;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataReprovacao;
	
	private Long qtdReprovacao;
	private AlunoTO aluno;
	private Long usuarioAlteracao;

	public ReprovacoesAlunoTO() {
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

	public AlunoTO getAluno() {
		return aluno;
	}

	public void setAluno(AlunoTO aluno) {
		this.aluno = aluno;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

}