package br.com.crux.to;

import java.time.LocalDateTime;

public class FuncoesInstituicaoTO {

	private Long id;
	private LocalDateTime dataFim;
	private LocalDateTime dataInicio;
	private FuncionarioTO funcionario;
	private FuncoesTO funcoes;

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

	public FuncionarioTO getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(FuncionarioTO funcionario) {
		this.funcionario = funcionario;
	}

	public FuncoesTO getFuncoes() {
		return funcoes;
	}

	public void setFuncoes(FuncoesTO funcoes) {
		this.funcoes = funcoes;
	}

}
