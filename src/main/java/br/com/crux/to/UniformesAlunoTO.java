package br.com.crux.to;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.crux.infra.adapter.LocalDateTimeAdapter;

public class UniformesAlunoTO {

	private Long id;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataUniformeEntregue;
	
	private String nomeUniforme;
	private Long qtdUniformeEntregue;
	private AtividadesAlunoTO atividadesAluno;
	private Long usuarioAlteracao;

	public UniformesAlunoTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataUniformeEntregue() {
		return dataUniformeEntregue;
	}

	public void setDataUniformeEntregue(LocalDateTime dataUniformeEntregue) {
		this.dataUniformeEntregue = dataUniformeEntregue;
	}

	public String getNomeUniforme() {
		return nomeUniforme;
	}

	public void setNomeUniforme(String nomeUniforme) {
		this.nomeUniforme = nomeUniforme;
	}

	public Long getQtdUniformeEntregue() {
		return qtdUniformeEntregue;
	}

	public void setQtdUniformeEntregue(Long qtdUniformeEntregue) {
		this.qtdUniformeEntregue = qtdUniformeEntregue;
	}

	public AtividadesAlunoTO getAtividadesAluno() {
		return atividadesAluno;
	}

	public void setAtividadesAluno(AtividadesAlunoTO atividadesAluno) {
		this.atividadesAluno = atividadesAluno;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}


}