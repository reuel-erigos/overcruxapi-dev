package br.com.crux.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.crux.infra.constantes.Constantes;

/**
 * The persistent class for the contas_bancarias database table.
 * 
 */
@Entity
@Table(name = "contas_bancarias")
public class ContasBancaria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_conta_bancaria") 
	@SequenceGenerator(name = "sq_id_conta_bancaria", sequenceName = "sq_id_conta_bancaria", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_conta_bancaria")
	private Long id;

	@Column(name = "cs_tipo_conta_bancaria") 
	private String tipoContaBancaria;

	@Column(name = "nm_banco") 
	private String nomeBanco;

	@Column(name = "nr_agencia") 
	private String numeroAgencia;

	@Column(name = "nr_banco") 
	private String numeroBanco;

	@Column(name = "nr_conta_bancaria") 
	private String numeroContaBancaria;

	@ManyToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name = "id_unidade") 
	private Unidade unidade;

	@Column(name = "id_usuario_apl") 
	private Long usuarioAlteracao;

	@Column(name = "nm_titular") 
	private String nomeTitular;

	@Column(name = "nm_contato") 
	private String nomeContato;

	@Column(name = "nr_cpf_cnpj_titular") 
	private String cpfCnpjTitular;

	@Column(name = "nr_telefone_titular") 
	private String telefoneTitular;

	@Column(name = "ds_email_titular") 
	private String emailTitular;

	public ContasBancaria() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoContaBancaria() {
		return tipoContaBancaria;
	}

	public void setTipoContaBancaria(String tipoContaBancaria) {
		this.tipoContaBancaria = tipoContaBancaria;
	}

	public String getNomeBanco() {
		return nomeBanco;
	}

	public void setNomeBanco(String nomeBanco) {
		this.nomeBanco = nomeBanco;
	}

	public String getNumeroAgencia() {
		return numeroAgencia;
	}

	public void setNumeroAgencia(String numeroAgencia) {
		this.numeroAgencia = numeroAgencia;
	}

	public String getNumeroBanco() {
		return numeroBanco;
	}

	public void setNumeroBanco(String numeroBanco) {
		this.numeroBanco = numeroBanco;
	}

	public String getNumeroContaBancaria() {
		return numeroContaBancaria;
	}

	public void setNumeroContaBancaria(String numeroContaBancaria) {
		this.numeroContaBancaria = numeroContaBancaria;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
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

	
	
}