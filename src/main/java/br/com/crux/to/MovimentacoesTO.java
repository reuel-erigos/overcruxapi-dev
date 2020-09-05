package br.com.crux.to;

import java.time.LocalDateTime;
import java.util.List;

public class MovimentacoesTO {

	private Long id;
	private EmpresaTO empresa;
	private String stTipoMovimentacao;
	private LocalDateTime dataMovimentacao;
	private String descricaoMovimentacao;
	private String nrDocumento;
	private LocalDateTime dataDocumento;
	private Double valorMovimentacao;
	private UnidadeTO unidade;
	private DepartamentoTO departamento;
	private Long qtdParcelas;
	private Boolean statusRegistroSaldo;
	
	private List<ItensMovimentacoesTO> itensMovimentacoes; 
	private List<FaturaTO> faturas; 
	private List<PagamentosFaturaTO> pagamentosFatura; 
	
	
	private Double valorISS;
	private Double valorICMS;
	private Double valorIPI;
	private Double valorPisConfinsCsll;
	private Double valorInss;
	private ContasBancariaTO contaBancaria;
	private ContasBancariaTO contaBancariaDestino;
	
	private List<RateiosMovimentacoesTO> rateios;
	private List<RateiosMovimentacoesUnidadesTO> rateiosUnidades;
	
	private DoadoresTO doador;
	
	public MovimentacoesTO() {
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public EmpresaTO getEmpresa() {
		return empresa;
	}
	public void setEmpresa(EmpresaTO empresa) {
		this.empresa = empresa;
	}
	public String getStTipoMovimentacao() {
		return stTipoMovimentacao;
	}
	public void setStTipoMovimentacao(String stTipoMovimentacao) {
		this.stTipoMovimentacao = stTipoMovimentacao;
	}
	public LocalDateTime getDataMovimentacao() {
		return dataMovimentacao;
	}
	public void setDataMovimentacao(LocalDateTime dataMovimentacao) {
		this.dataMovimentacao = dataMovimentacao;
	}
	public String getDescricaoMovimentacao() {
		return descricaoMovimentacao;
	}
	public void setDescricaoMovimentacao(String descricaoMovimentacao) {
		this.descricaoMovimentacao = descricaoMovimentacao;
	}
	public String getNrDocumento() {
		return nrDocumento;
	}
	public void setNrDocumento(String nrDocumento) {
		this.nrDocumento = nrDocumento;
	}
	public LocalDateTime getDataDocumento() {
		return dataDocumento;
	}
	public void setDataDocumento(LocalDateTime dataDocumento) {
		this.dataDocumento = dataDocumento;
	}
	public Double getValorMovimentacao() {
		return valorMovimentacao;
	}
	public void setValorMovimentacao(Double valorMovimentacao) {
		this.valorMovimentacao = valorMovimentacao;
	}
	public UnidadeTO getUnidade() {
		return unidade;
	}
	public void setUnidade(UnidadeTO unidade) {
		this.unidade = unidade;
	}
	public DepartamentoTO getDepartamento() {
		return departamento;
	}
	public void setDepartamento(DepartamentoTO departamento) {
		this.departamento = departamento;
	}
	public Long getQtdParcelas() {
		return qtdParcelas;
	}
	public void setQtdParcelas(Long qtdParcelas) {
		this.qtdParcelas = qtdParcelas;
	}
	public List<ItensMovimentacoesTO> getItensMovimentacoes() {
		return itensMovimentacoes;
	}
	public void setItensMovimentacoes(List<ItensMovimentacoesTO> itensMovimentacoes) {
		this.itensMovimentacoes = itensMovimentacoes;
	}
	public List<FaturaTO> getFaturas() {
		return faturas;
	}
	public void setFaturas(List<FaturaTO> faturas) {
		this.faturas = faturas;
	}
	public List<PagamentosFaturaTO> getPagamentosFatura() {
		return pagamentosFatura;
	}
	public void setPagamentosFatura(List<PagamentosFaturaTO> pagamentosFaturaTO) {
		this.pagamentosFatura = pagamentosFaturaTO;
	}
	public Double getValorISS() {
		return valorISS;
	}
	public void setValorISS(Double valorISS) {
		this.valorISS = valorISS;
	}
	public Double getValorICMS() {
		return valorICMS;
	}
	public void setValorICMS(Double valorICMS) {
		this.valorICMS = valorICMS;
	}
	public Double getValorIPI() {
		return valorIPI;
	}
	public void setValorIPI(Double valorIPI) {
		this.valorIPI = valorIPI;
	}
	public Double getValorPisConfinsCsll() {
		return valorPisConfinsCsll;
	}
	public void setValorPisConfinsCsll(Double valorPisConfinsCsll) {
		this.valorPisConfinsCsll = valorPisConfinsCsll;
	}
	public Double getValorInss() {
		return valorInss;
	}
	public void setValorInss(Double valorInss) {
		this.valorInss = valorInss;
	}
	public ContasBancariaTO getContaBancaria() {
		return contaBancaria;
	}
	public void setContaBancaria(ContasBancariaTO contaBancaria) {
		this.contaBancaria = contaBancaria;
	}
	public Boolean getStatusRegistroSaldo() {
		return statusRegistroSaldo;
	}
	public void setStatusRegistroSaldo(Boolean statusRegistroSaldo) {
		this.statusRegistroSaldo = statusRegistroSaldo;
	}
	public List<RateiosMovimentacoesTO> getRateios() {
		return rateios;
	}
	public void setRateios(List<RateiosMovimentacoesTO> rateios) {
		this.rateios = rateios;
	}

	public ContasBancariaTO getContaBancariaDestino() {
		return contaBancariaDestino;
	}

	public void setContaBancariaDestino(ContasBancariaTO contaBancariaDestino) {
		this.contaBancariaDestino = contaBancariaDestino;
	}

	public List<RateiosMovimentacoesUnidadesTO> getRateiosUnidades() {
		return rateiosUnidades;
	}

	public void setRateiosUnidades(List<RateiosMovimentacoesUnidadesTO> rateiosUnidades) {
		this.rateiosUnidades = rateiosUnidades;
	}

	public DoadoresTO getDoador() {
		return doador;
	}

	public void setDoador(DoadoresTO doador) {
		this.doador = doador;
	}
}
