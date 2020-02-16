package br.com.crux.to;

import java.time.LocalDateTime;

import br.com.crux.entity.Movimentacoes;

public class FaturaTO {

	private Long id;
	private Movimentacoes movimentacao;
	private LocalDateTime dataVencimento;
	private Double valor;
	private Long numeroParcela;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Movimentacoes getMovimentacao() {
		return movimentacao;
	}
	public void setMovimentacao(Movimentacoes movimentacao) {
		this.movimentacao = movimentacao;
	}
	public LocalDateTime getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(LocalDateTime dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Long getNumeroParcela() {
		return numeroParcela;
	}
	public void setNumeroParcela(Long numeroParcela) {
		this.numeroParcela = numeroParcela;
	}
	
	

}
