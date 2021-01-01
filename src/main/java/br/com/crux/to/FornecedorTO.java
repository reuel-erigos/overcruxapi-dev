package br.com.crux.to;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.crux.infra.adapter.LocalDateTimeAdapter;


public class FornecedorTO {

	private Long id;
	private PessoaFisicaTO pessoasFisica;
	private EmpresaTO empresa;
	private Boolean ativo;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class) 
	private LocalDateTime dataInicioVinculo;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class) 
	private LocalDateTime dataFimVinculo;
	
	
	public FornecedorTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PessoaFisicaTO getPessoasFisica() {
		return pessoasFisica;
	}

	public void setPessoasFisica(PessoaFisicaTO pessoasFisica) {
		this.pessoasFisica = pessoasFisica;
	}

	public EmpresaTO getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresaTO empresa) {
		this.empresa = empresa;
	}

	public LocalDateTime getDataInicioVinculo() {
		return dataInicioVinculo;
	}

	public void setDataInicioVinculo(LocalDateTime dataInicioVinculo) {
		this.dataInicioVinculo = dataInicioVinculo;
	}

	public LocalDateTime getDataFimVinculo() {
		return dataFimVinculo;
	}

	public void setDataFimVinculo(LocalDateTime dataFimVinculo) {
		this.dataFimVinculo = dataFimVinculo;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

}