
package br.com.crux.to;

public class ContasBancariaTO {

	private Long id;
	private BancoTO banco;
	private String tipoContaBancaria;
	private String numeroAgencia;
	private String numeroContaBancaria;
	private UnidadeTO unidade;
	private String nomeTitular;
	private String nomeContato;
	private String cpfCnpjTitular;
	private String telefoneTitular;
	private String emailTitular;
	private Long contaAssociada;
	private CategoriasContabeisTO categoriasContabeis;
	private String descricaoCompleta;
	
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

	public String getNomeTitular() {
		return nomeTitular;
	}

	public void setNomeTitular(String nomeTitular) {
		this.nomeTitular = nomeTitular;
	}

	public String getNomeContato() {
		return nomeContato;
	}

	public void setNomeContato(String nomeContato) {
		this.nomeContato = nomeContato;
	}

	public String getCpfCnpjTitular() {
		return cpfCnpjTitular;
	}

	public void setCpfCnpjTitular(String cpfCnpjTitular) {
		this.cpfCnpjTitular = cpfCnpjTitular;
	}

	public String getTelefoneTitular() {
		return telefoneTitular;
	}

	public void setTelefoneTitular(String telefoneTitular) {
		this.telefoneTitular = telefoneTitular;
	}

	public String getEmailTitular() {
		return emailTitular;
	}

	public void setEmailTitular(String emailTitular) {
		this.emailTitular = emailTitular;
	}

	public Long getContaAssociada() {
		return contaAssociada;
	}

	public void setContaAssociada(Long contaAssociada) {
		this.contaAssociada = contaAssociada;
	}

	public CategoriasContabeisTO getCategoriasContabeis() {
		return categoriasContabeis;
	}

	public void setCategoriasContabeis(CategoriasContabeisTO categoriasContabeis) {
		this.categoriasContabeis = categoriasContabeis;
	}

	public String getDescricaoCompleta() {
		return descricaoCompleta;
	}

	public void setDescricaoCompleta(String descricaoCompleta) {
		this.descricaoCompleta = descricaoCompleta;
	}

}