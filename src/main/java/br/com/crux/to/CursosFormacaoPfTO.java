package br.com.crux.to;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.crux.infra.adapter.LocalDateTimeAdapter;


public class CursosFormacaoPfTO  {

	private Long id;

	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataFim;
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataInicio;
	
	private String nome;
	private String nomeInstuicao;
	private PessoaFisicaTO pessoaFisica;
	private Long usuarioAlteracao;

	public CursosFormacaoPfTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDateTime dataFim) {
		this.dataFim = dataFim;
	}

	public LocalDateTime getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeInstuicao() {
		return nomeInstuicao;
	}

	public void setNomeInstuicao(String nomeInstuicao) {
		this.nomeInstuicao = nomeInstuicao;
	}

	public PessoaFisicaTO getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisicaTO pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	

}