package br.com.crux.to;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

public class Movimentacoes {

	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate data;
	
	private String numero;
	private String descricao;
	
	@NumberFormat(pattern = "#,##0.00")
	private Double valorEntrada;
	
	@NumberFormat(pattern = "#,##0.00")
	private Double valorSaida;
	
	@NumberFormat(pattern = "#,##0.00")
	private Double saldo;
	
	public Movimentacoes() {
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
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
		return saldo != null ? saldo : 0;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	
	
	
}
