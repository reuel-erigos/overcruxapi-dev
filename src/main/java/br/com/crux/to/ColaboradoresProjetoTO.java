package br.com.crux.to;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.crux.infra.adapter.LocalDateTimeAdapter;

public class ColaboradoresProjetoTO {

	private Long id;

	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataInicio;
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataFim;

	private FuncionarioTO funcionario;
	private CargoTO cargo;
	private ProjetoTO projeto;

	private TiposContratacoesTO tiposContratacoes;
	
	private Long usuarioAlteracao;

	
	public ColaboradoresProjetoTO() {
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDateTime getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDateTime dataFim) {
		this.dataFim = dataFim;
	}

	public FuncionarioTO getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(FuncionarioTO funcionario) {
		this.funcionario = funcionario;
	}

	public ProjetoTO getProjeto() {
		return projeto;
	}

	public void setProjeto(ProjetoTO to) {
		this.projeto = to;
	}

	public CargoTO getCargo() {
		return cargo;
	}

	public void setCargo(CargoTO cargoTO) {
		this.cargo = cargoTO;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}


	public TiposContratacoesTO getTiposContratacoes() {
		return tiposContratacoes;
	}


	public void setTiposContratacoes(TiposContratacoesTO tiposContratacoes) {
		this.tiposContratacoes = tiposContratacoes;
	}


}