package br.com.crux.to;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.crux.infra.adapter.LocalDateTimeAdapter;


public class FaltasFuncionarioTO  {

	private Long id;
	private String descricao;
	private String jutificativaFalta;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataCadastro;
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataFaltaFuncionario;
	
	private FuncionarioTO funcionarioFaltou;
	private FuncionarioTO funcionarioCadastrouFalta;
	private Long usuarioAlteracao;

	public FaltasFuncionarioTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getJutificativaFalta() {
		return jutificativaFalta;
	}

	public void setJutificativaFalta(String jutificativaFalta) {
		this.jutificativaFalta = jutificativaFalta;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public LocalDateTime getDataFaltaFuncionario() {
		return dataFaltaFuncionario;
	}

	public void setDataFaltaFuncionario(LocalDateTime dataFaltaFuncionario) {
		this.dataFaltaFuncionario = dataFaltaFuncionario;
	}

	public FuncionarioTO getFuncionarioFaltou() {
		return funcionarioFaltou;
	}

	public void setFuncionarioFaltou(FuncionarioTO funcionarioFaltou) {
		this.funcionarioFaltou = funcionarioFaltou;
	}

	public FuncionarioTO getFuncionarioCadastrouFalta() {
		return funcionarioCadastrouFalta;
	}

	public void setFuncionarioCadastrouFalta(FuncionarioTO funcionarioCadastrouFalta) {
		this.funcionarioCadastrouFalta = funcionarioCadastrouFalta;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}


}