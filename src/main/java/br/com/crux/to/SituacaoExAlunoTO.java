package br.com.crux.to;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.crux.infra.adapter.LocalDateTimeAdapter;

public class SituacaoExAlunoTO {
	
	private Long id;
	
	private String profissao;

	private String localTrabalho;

	private String condicaoAtual;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class) 
	private LocalDateTime dataAvaliacao;

	private AlunoTO aluno;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public AlunoTO getAluno() {
		return aluno;
	}

	public void setAluno(AlunoTO aluno) {
		this.aluno = aluno;
	}
	
	

}