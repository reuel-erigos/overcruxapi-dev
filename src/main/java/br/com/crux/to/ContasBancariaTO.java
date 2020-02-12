
package br.com.crux.to;

public class ContasBancariaTO {

	private Long id;
	private BancoTO banco;
	private String tipoContaBancaria;
	private String numeroAgencia;
	private String numeroContaBancaria;
	private UnidadeTO unidade;
	private SaldosContasBancariaTO saldosContasBancaria;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BancoTO getBanco() {
		return banco;
	}

	public void setBanco(BancoTO banco) {
		this.banco = banco;
	}

	public String getTipoContaBancaria() {
		return tipoContaBancaria;
	}

	public void setTipoContaBancaria(String tipoContaBancaria) {
		this.tipoContaBancaria = tipoContaBancaria;
	}

	public String getNumeroAgencia() {
		return numeroAgencia;
	}

	public void setNumeroAgencia(String numeroAgencia) {
		this.numeroAgencia = numeroAgencia;
	}

	public String getNumeroContaBancaria() {
		return numeroContaBancaria;
	}

	public void setNumeroContaBancaria(String numeroContaBancaria) {
		this.numeroContaBancaria = numeroContaBancaria;
	}

	public UnidadeTO getUnidade() {
		return unidade;
	}

	public void setUnidade(UnidadeTO unidade) {
		this.unidade = unidade;
	}

	public SaldosContasBancariaTO getSaldosContasBancaria() {
		return saldosContasBancaria;
	}

	public void setSaldosContasBancaria(SaldosContasBancariaTO saldoContaBancaria) {
		this.saldosContasBancaria = saldoContaBancaria;
	}

}