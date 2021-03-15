package br.com.crux.dao.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

public class TransferenciaValoresDTO {
	
	private Long idMovimentacao;
	private String descContaOrigem;
	private String descContaDestino;
	private Double valor;
	private LocalDate data;
	
	
	public TransferenciaValoresDTO() {
	}
	
	public TransferenciaValoresDTO(Object[] colunas) {
		this.idMovimentacao      = (colunas[0] != null)? ((BigDecimal)colunas[0]).longValue() : null;
		this.descContaOrigem     = (colunas[1] != null) ? (String) colunas[1] : "";
		this.descContaDestino    = (colunas[2] != null) ? (String) colunas[2] : "";
		this.valor               = (colunas[3] != null)? ((BigDecimal)colunas[3]).doubleValue() : null;
		this.data                = (colunas[4] != null)? ((Timestamp)colunas[4]).toLocalDateTime().toLocalDate() : null;
	}

	public Long getIdMovimentacao() {
		return idMovimentacao;
	}

	public void setIdMovimentacao(Long idMovimentacao) {
		this.idMovimentacao = idMovimentacao;
	}

	public String getDescContaOrigem() {
		return descContaOrigem;
	}

	public void setDescContaOrigem(String descContaOrigem) {
		this.descContaOrigem = descContaOrigem;
	}

	public String getDescContaDestino() {
		return descContaDestino;
	}

	public void setDescContaDestino(String descContaDestino) {
		this.descContaDestino = descContaDestino;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	
}

