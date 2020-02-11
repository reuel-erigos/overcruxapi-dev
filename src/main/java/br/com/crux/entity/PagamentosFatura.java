package br.com.crux.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

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
 * The persistent class for the pagamentos_faturas database table.
 * 
 */
@Entity
@Table(name = "pagamentos_faturas")
public class PagamentosFatura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_pagamento_fatura")
	@SequenceGenerator(name = "sq_id_pagamento_fatura", sequenceName = "sq_id_pagamento_fatura", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_pagamento")
	private Long idPagamentoFatura;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_fatura")
	private Fatura fatura;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_conta_bancaria")
	private ContasBancaria contaBancaria;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_saldo_conta_bancaria")
	private SaldosContasBancaria saldoContaBancaria;

	@Column(name = "vl_pagamento")
	private Double valorPagamento;

	@Column(name = "dt_pagamento")
	private LocalDateTime dataPagamento;

	// Classificador da forma de pagamento (C = CHEQUE; C = CARTÃO DE CRÉDITO; B =
	// DÉBITO EM CARTÃO; D = EM DINHEIRO
	@Column(name = "ds_forma_pagamento")
	private String formaPagamento;

	@Column(name = "nr_doc_pagamento")
	private String numeroDocPagamento;

	@Column(name = "id_usuario_apl")
	private Long usuarioAlteracao;

	public PagamentosFatura() {
	}

	public Long getIdPagamentoFatura() {
		return idPagamentoFatura;
	}

	public void setIdPagamentoFatura(Long idPagamentoFatura) {
		this.idPagamentoFatura = idPagamentoFatura;
	}

	public Fatura getFatura() {
		return fatura;
	}

	public void setFatura(Fatura fatura) {
		this.fatura = fatura;
	}

	public ContasBancaria getContaBancaria() {
		return contaBancaria;
	}

	public void setContaBancaria(ContasBancaria contaBancaria) {
		this.contaBancaria = contaBancaria;
	}

	public SaldosContasBancaria getSaldoContaBancaria() {
		return saldoContaBancaria;
	}

	public void setSaldoContaBancaria(SaldosContasBancaria saldoContaBancaria) {
		this.saldoContaBancaria = saldoContaBancaria;
	}

	public Double getValorPagamento() {
		return valorPagamento;
	}

	public void setValorPagamento(Double valorPagamento) {
		this.valorPagamento = valorPagamento;
	}

	public LocalDateTime getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDateTime dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public String getNumeroDocPagamento() {
		return numeroDocPagamento;
	}

	public void setNumeroDocPagamento(String numeroDocPagamento) {
		this.numeroDocPagamento = numeroDocPagamento;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

}