package br.com.crux.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.crux.infra.constantes.Constantes;
import br.com.crux.infra.dao.SimNaoConverter;


@Entity
@Table(name = "rateios_pagamentos")
public class RateiosPagamentos implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_rateio_pagamento")
	@SequenceGenerator(name = "sq_id_rateio_pagamento", sequenceName = "sq_id_rateio_pagamento", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_rateio_pagamento")
	private Long id;

	@Column(name = "id_pagamento") 
	private Long idPagamentoFatura;
	
	@Column(name = "id_rateio_movimentacao") 
	private Long idRateioMovimentacao;
	
	@Convert(converter = SimNaoConverter.class)
	@Column(name = "st_percentual")
	private Boolean statusPercentual;
	
	@Column(name = "vl_rateio")
	private Double valorRateio;
	
	@Column(name = "id_usuario_apl")
	private Long usuarioAlteracao;

	
	public RateiosPagamentos() {
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

	public Long getIdRateioMovimentacao() {
		return idRateioMovimentacao;
	}

	public void setIdRateioMovimentacao(Long idRateioMovimentacao) {
		this.idRateioMovimentacao = idRateioMovimentacao;
	}

	public Boolean getStatusPercentual() {
		return statusPercentual;
	}

	public void setStatusPercentual(Boolean statusPercentual) {
		this.statusPercentual = statusPercentual;
	}

	public Double getValorRateio() {
		return valorRateio;
	}

	public void setValorRateio(Double valorRateio) {
		this.valorRateio = valorRateio;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}
	
}