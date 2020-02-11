package br.com.crux.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

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
	private BigDecimal numeroBanco;

	@Column(name = "nr_conta_bancaria")
	private Timestamp numeroContaBancaria;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_unidade")
	private Unidade unidade;

	@Column(name = "id_usuario_apl")
	private Long usuarioAlteracao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_saldo_conta_bancaria")
	private SaldosContasBancaria saldoContaBancaria;

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

	public BigDecimal getNumeroBanco() {
		return numeroBanco;
	}

	public void setNumeroBanco(BigDecimal numeroBanco) {
		this.numeroBanco = numeroBanco;
	}

	public Timestamp getNumeroContaBancaria() {
		return numeroContaBancaria;
	}

	public void setNumeroContaBancaria(Timestamp numeroContaBancaria) {
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

	public SaldosContasBancaria getSaldoContaBancaria() {
		return saldoContaBancaria;
	}

	public void setSaldoContaBancaria(SaldosContasBancaria saldoContaBancaria) {
		this.saldoContaBancaria = saldoContaBancaria;
	}

}