package br.com.crux.to;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.crux.infra.adapter.LocalDateTimeAdapter;

public class AvaliacoesAlunosTO {

	private Long id;
	private String descriaoAvaliacao;

	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class) private LocalDateTime dataAvaliacao;

	private AtividadesAlunoTO atividadesAluno;
	private AvaliacoesTO avaliacoes;

	private String notaAvaliacao;
	private Long usuarioAlteracao;

	public AvaliacoesAlunosTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescriaoAvaliacao() {
		return descriaoAvaliacao;
	}

	public void setDescriaoAvaliacao(String descriaoAvaliacao) {
		this.descriaoAvaliacao = descriaoAvaliacao;
	}

	public LocalDateTime getDataAvaliacao() {
		return dataAvaliacao;
	}

	public void setDataAvaliacao(LocalDateTime dataAvaliacao) {
		this.dataAvaliacao = dataAvaliacao;
	}

	public String getNotaAvaliacao() {
		return notaAvaliacao;
	}

	public void setNotaAvaliacao(String notaAvaliacao) {
		this.notaAvaliacao = notaAvaliacao;
	}

	public AtividadesAlunoTO getAtividadesAluno() {
		return atividadesAluno;
	}

	public void setAtividadesAluno(AtividadesAlunoTO atividadesAluno) {
		this.atividadesAluno = atividadesAluno;
	}

	public AvaliacoesTO getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(AvaliacoesTO avaliacoe) {
		this.avaliacoes = avaliacoe;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

}