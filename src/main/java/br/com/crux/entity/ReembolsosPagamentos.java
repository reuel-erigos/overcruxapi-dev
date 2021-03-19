package br.com.crux.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
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
import br.com.crux.infra.dao.SimNaoConverter;


@Entity
@Table(name = "reembolsos_pagamentos")
public class ReembolsosPagamentos implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_reembolso_pagamento")
	@SequenceGenerator(name = "sq_id_reembolso_pagamento", sequenceName = "sq_id_reembolso_pagamento", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_reembolso_pagamento")
	private Long id;

	@Column(name = "id_pagamento") 
	private Long idPagamentoFatura;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_conta_bancaria") 
	private ContasBancaria contaBancaria;
	
	@Column(name = "ds_descricao") 
	private String descricao;
	
	@Column(name="dt_reembolso")
	private LocalDateTime data;
	
	@Convert(converter = SimNaoConverter.class)
	@Column(name = "st_percentual")
	private Boolean statusPercentual;
	
	@Column(name = "vl_reembolso")
	private Double valor;
	
	@Column(name = "id_usuario_apl")
	private Long usuarioAlteracao;	
	
	@Column(name = "nr_transacao") 
	private String transacao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_conta_bancaria_destino") 
	private ContasBancaria contaBancariaDestino;

	public ReembolsosPagamentos() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdPagamentoFatura() {
		return idPagamentoFatura;
	}

	public void setIdPagamentoFatura(Long idPagamentoFatura) {
		this.idPagamentoFatura = idPagamentoFatura;
	}

	public ContasBancaria getContaBancaria() {
		return contaBancaria;
	}

	public void setContaBancaria(ContasBancaria contaBancaria) {
		this.contaBancaria = contaBancaria;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public Boolean getStatusPercentual() {
		return statusPercentual;
	}

	public void setStatusPercentual(Boolean statusPercentual) {
		this.statusPercentual = statusPercentual;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valorRateio) {
		this.valor = valorRateio;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public String getTransacao() {
		return transacao;
	}

	public void setTransacao(String transacao) {
		this.transacao = transacao;
	}

	public ContasBancaria getContaBancariaDestino() {
		return contaBancariaDestino;
	}

	public void setContaBancariaDestino(ContasBancaria contaBancariaDestino) {
		this.contaBancariaDestino = contaBancariaDestino;
	}
	
}