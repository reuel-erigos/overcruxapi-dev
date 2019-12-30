package br.com.crux.to;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.crux.infra.adapter.LocalDateTimeAdapter;


public class AlunosTrabalhandoTO {

	private Long id;
	private String descTipoEmpreendimento;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataFimAlunoTrabalhando;
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataInicioAlunoTrabalhando;

	private String nomeEmpreendimento;
	private AlunoTO aluno;
	private Long usuarioAlteracao;

	public AlunosTrabalhandoTO() {
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

	public AlunoTO getAluno() {
		return this.aluno;
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