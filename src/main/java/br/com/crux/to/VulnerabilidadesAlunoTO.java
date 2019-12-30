package br.com.crux.to;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.crux.infra.adapter.LocalDateTimeAdapter;

public class VulnerabilidadesAlunoTO {

	private Long id;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataIdentificacao;
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataSolucao;
	
	private Long idAluno;
	private SituacoesVulnerabilidadeTO situacoesVulnerabilidade;
	private SolucoesTO solucoes;
	private Long usuarioAlteracao;

	public VulnerabilidadesAlunoTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataIdentificacao() {
		return dataIdentificacao;
	}

	public void setDataIdentificacao(LocalDateTime dataIdentificacao) {
		this.dataIdentificacao = dataIdentificacao;
	}

	public LocalDateTime getDataSolucao() {
		return dataSolucao;
	}

	public void setDataSolucao(LocalDateTime dataSolucao) {
		this.dataSolucao = dataSolucao;
	}


	public Long getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(Long idAluno) {
		this.idAluno = idAluno;
	}

	public SituacoesVulnerabilidadeTO getSituacoesVulnerabilidade() {
		return situacoesVulnerabilidade;
	}

	public void setSituacoesVulnerabilidade(SituacoesVulnerabilidadeTO situacoesVulnerabilidade) {
		this.situacoesVulnerabilidade = situacoesVulnerabilidade;
	}

	public SolucoesTO getSolucoes() {
		return solucoes;
	}

	public void setSolucoes(SolucoesTO solucoe) {
		this.solucoes = solucoe;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}


}