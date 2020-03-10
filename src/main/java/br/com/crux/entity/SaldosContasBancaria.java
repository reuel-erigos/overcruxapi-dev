package br.com.crux.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.crux.infra.constantes.Constantes;


/**
 * The persistent class for the saldos_contas_bancarias database table.
 * 
 */
@Entity
@Table(name="saldos_contas_bancarias")
public class SaldosContasBancaria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_saldo_conta_bancaria")
	@SequenceGenerator(name = "sq_id_saldo_conta_bancaria", sequenceName = "sq_id_saldo_conta_bancaria", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_saldo_conta_bancaria")
	private Long id;

	@Column(name="ds_saldo")
	private String descricaoSaldo;

	@Column(name="dt_saldo")
	private LocalDateTime dataSaldo;

	@Column(name = "id_usuario_apl")
	private Long usuarioAlteracao;

	@Column(name="vl_saldo")
	private Double valorSaldo;

	@ManyToOne
	@JoinColumn(name="id_conta_bancaria")
	private ContasBancaria contaBancaria;

	public SaldosContasBancaria() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricaoSaldo() {
		return descricaoSaldo;
	}

	public void setDescricaoSaldo(String descricaoSaldo) {
		this.descricaoSaldo = descricaoSaldo;
	}

	public LocalDateTime getDataSaldo() {
		return dataSaldo;
	}

	public void setDataSaldo(LocalDateTime dataSaldo) {
		this.dataSaldo = dataSaldo;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public Double getValorSaldo() {
		return valorSaldo;
	}

	public void setValorSaldo(Double valorSaldo) {
		this.valorSaldo = valorSaldo;
	}

	public ContasBancaria getContaBancaria() {
		return contaBancaria;
	}

	public void setContaBancaria(ContasBancaria contaBancaria) {
		this.contaBancaria = contaBancaria;
	}

	

}