package br.com.crux.to;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.crux.infra.adapter.LocalDateTimeAdapter;


public class AtendimentosTO {

	private Long id;
	private String descDiagnostico;
	private String descSolucao;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataAtendimento;
	
	private SolucoesTO solucoes;
	private AlunoTO aluno;
	private DiagnosticosTO diagnostico;
	private Long usuarioAlteracao;

	public AtendimentosTO() {
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

	public SolucoesTO getSolucoes() {
		return solucoes;
	}

	public void setSolucoes(SolucoesTO solucoe) {
		this.solucoes = solucoe;
	}

	public AlunoTO getAluno() {
		return aluno;
	}

	public void setAluno(AlunoTO aluno) {
		this.aluno = aluno;
	}

	public DiagnosticosTO getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(DiagnosticosTO diagnostico) {
		this.diagnostico = diagnostico;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	
}