package br.com.crux.to.relatorios.financeiro;

public class SaldoProjetoTO {

	private String nomeProgramaProjeto;
	private String tipoTransacao;
	private String descricao;
	private String numeroDocumento;
	private String dataOperacao;
	private Double valorOperacao;
	private String banco;
	private String conta;
	private String parceiro;
	private Double saldo;

	public SaldoProjetoTO() {
	}

	public String getNomeProgramaProjeto() {
		return nomeProgramaProjeto;
	}

	public void setNomeProgramaProjeto(String nomeProgramaProjeto) {
		this.nomeProgramaProjeto = nomeProgramaProjeto;
	}

	public String getTipoTransacao() {
		return tipoTransacao;
	}

	public void setTipoTransacao(String tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getDataOperacao() {
		return dataOperacao;
	}

	public void setDataOperacao(String dataOperacao) {
		this.dataOperacao = dataOperacao;
	}

	public Double getValorOperacao() {
		return valorOperacao;
	}

	public void setValorOperacao(Double valorOperacao) {
		this.valorOperacao = valorOperacao;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public String getParceiro() {
		return parceiro;
	}

	public void setParceiro(String parceiro) {
		this.parceiro = parceiro;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	

}
