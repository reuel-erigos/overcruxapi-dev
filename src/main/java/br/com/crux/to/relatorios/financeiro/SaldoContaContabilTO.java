package br.com.crux.to.relatorios.financeiro;

public class SaldoContaContabilTO {
	
	private Double saldoDataInicio      ;
	private Double saldoDataFim         ;
	
	public SaldoContaContabilTO() {
	}
	
	public SaldoContaContabilTO(Double saldoDataInicio, Double saldoDataFim) {
		super();
		this.saldoDataInicio = saldoDataInicio;
		this.saldoDataFim = saldoDataFim;
	}

	public Double getSaldoDataInicio() {
		return saldoDataInicio;
	}

	public void setSaldoDataInicio(Double saldoDataInicio) {
		this.saldoDataInicio = saldoDataInicio;
	}

	public Double getSaldoDataFim() {
		return saldoDataFim;
	}

	public void setSaldoDataFim(Double saldoDataFim) {
		this.saldoDataFim = saldoDataFim;
	}

	

}
