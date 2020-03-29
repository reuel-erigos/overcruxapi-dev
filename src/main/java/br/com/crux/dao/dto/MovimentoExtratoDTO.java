package br.com.crux.dao.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class MovimentoExtratoDTO {
	
	private String numero;
	private String descricao;
	private LocalDateTime data;
	private Double valorEntrada;
	private Double valorSaida;
	private Double saldo;
	
	public MovimentoExtratoDTO() {
	}
	
	public MovimentoExtratoDTO(Object[] colunas) {
		this.numero          = (String) colunas[0];
		this.descricao       = (String) colunas[1];
		this.data            = (colunas[2] != null)? ((Timestamp)colunas[2]).toLocalDateTime() : null;
		this.valorEntrada    = (colunas[3] != null)? ((BigDecimal)colunas[3]).doubleValue() : null;
		this.valorSaida      = (colunas[4] != null)? ((BigDecimal)colunas[4]).doubleValue() : null;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
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

	public Double getValorEntrada() {
		return valorEntrada;
	}

	public void setValorEntrada(Double valorEntrada) {
		this.valorEntrada = valorEntrada;
	}

	public Double getValorSaida() {
		return valorSaida;
	}

	public void setValorSaida(Double valorSaida) {
		this.valorSaida = valorSaida;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	
}
