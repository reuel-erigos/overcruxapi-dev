package br.com.crux.to;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.crux.infra.adapter.LocalDateTimeAdapter;

public class RateiosProgramaProjetoTO {

	private Long idRateioMovimentacao;
	private Long idMovimentacao;
	private String nomeProgramaProjeto;
	private String fornecedor;
	private String numeroDocumento;
	private String cnpf_cpf;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataDocumento;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataUltimoPagamento;

	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataVencimento;

	private Double valor;
	private String numeroTransacao;
	private String categorias;
	
	public RateiosProgramaProjetoTO() {
	}

	public Long getIdMovimentacao() {
		return idMovimentacao;
	}

	public void setIdMovimentacao(Long idMovimentacao) {
		this.idMovimentacao = idMovimentacao;
	}

	public Long getIdRateioMovimentacao() {
		return idRateioMovimentacao;
	}

	public void setIdRateioMovimentacao(Long idRateioMovimentacao) {
		this.idRateioMovimentacao = idRateioMovimentacao;
	}

	public String getNomeProgramaProjeto() {
		return nomeProgramaProjeto;
	}

	public void setNomeProgramaProjeto(String nomeProgramaProjeto) {
		this.nomeProgramaProjeto = nomeProgramaProjeto;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getCnpf_cpf() {
		return cnpf_cpf;
	}

	public void setCnpf_cpf(String cnpf_cpf) {
		this.cnpf_cpf = cnpf_cpf;
	}

	public LocalDateTime getDataDocumento() {
		return dataDocumento;
	}

	public void setDataDocumento(LocalDateTime dataDocumento) {
		this.dataDocumento = dataDocumento;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getNumeroTransacao() {
		return numeroTransacao;
	}

	public void setNumeroTransacao(String numeroTransacao) {
		this.numeroTransacao = numeroTransacao;
	}

	public LocalDateTime getDataUltimoPagamento() {
		return dataUltimoPagamento;
	}

	public void setDataUltimoPagamento(LocalDateTime dataUltimoPagamento) {
		this.dataUltimoPagamento = dataUltimoPagamento;
	}

	public LocalDateTime getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDateTime dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public String getCategorias() {
		return categorias;
	}

	public void setCategorias(String categorias) {
		this.categorias = categorias;
	}
	
	
}
