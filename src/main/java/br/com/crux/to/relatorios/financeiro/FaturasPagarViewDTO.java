package br.com.crux.to.relatorios.financeiro;

import java.math.BigDecimal;
import java.util.List;

import br.com.crux.to.CategoriasContabeisTO;
import br.com.crux.to.ProjetoTO;

public class FaturasPagarViewDTO {
	
	private String descFornecedor;
	private String cnpjCpf;
	private String numeroDocumento;
	private String dataDocumento;
	private Double valorMovimentacao;
	private String dataVencimento;
	private Double valorFatura;
	private Long numeroParcela;
	private String descFatura;
	private Long idMovimentacao;
	private List<ProjetoTO> projetos;
	private List<CategoriasContabeisTO> categorias;
	
	public FaturasPagarViewDTO() {
	}
	
	public FaturasPagarViewDTO(Object[] colunas) {
		this.idMovimentacao            = (colunas[0] != null)? ((BigDecimal)colunas[0]).longValue() : null;
		this.descFornecedor            = (colunas[1] != null) ? (String) colunas[1] : "";
		this.cnpjCpf                   = (colunas[2] != null) ? (String) colunas[2] : "";
		this.numeroDocumento           = (colunas[3] != null) ? (String) colunas[3] : "";
		this.dataDocumento             = (colunas[4] != null) ? (String) colunas[4] : "";
		this.valorMovimentacao         = (colunas[5] != null)? ((BigDecimal)colunas[5]).doubleValue() : null; 
		this.dataVencimento            = (colunas[6] != null) ? (String) colunas[6] : "";
		this.valorFatura               = (colunas[7] != null)? ((BigDecimal)colunas[7]).doubleValue() : null;
		this.numeroParcela             = (colunas[8] != null)? ((BigDecimal)colunas[8]).longValue() : null;
		this.descFatura                = (colunas[9] != null) ? (String) colunas[9] : "";
	}

	public String getDescFornecedor() {
		return descFornecedor;
	}

	public void setDescFornecedor(String descFornecedor) {
		this.descFornecedor = descFornecedor;
	}

	public String getCnpjCpf() {
		return cnpjCpf;
	}

	public void setCnpjCpf(String cnpjCpf) {
		this.cnpjCpf = cnpjCpf;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getDataDocumento() {
		return dataDocumento;
	}

	public void setDataDocumento(String dataDocumento) {
		this.dataDocumento = dataDocumento;
	}

	public Double getValorMovimentacao() {
		return valorMovimentacao;
	}

	public void setValorMovimentacao(Double valorMovimentacao) {
		this.valorMovimentacao = valorMovimentacao;
	}

	public String getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(String dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Double getValorFatura() {
		return valorFatura;
	}

	public void setValorFatura(Double valorFatura) {
		this.valorFatura = valorFatura;
	}

	public Long getNumeroParcela() {
		return numeroParcela;
	}

	public void setNumeroParcela(Long numeroParcela) {
		this.numeroParcela = numeroParcela;
	}

	public String getDescFatura() {
		return descFatura;
	}

	public void setDescFatura(String descFatura) {
		this.descFatura = descFatura;
	}

	public List<ProjetoTO> getProjetos() {
		return projetos;
	}

	public void setProjetos(List<ProjetoTO> projetos) {
		this.projetos = projetos;
	}

	public List<CategoriasContabeisTO> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<CategoriasContabeisTO> categorias) {
		this.categorias = categorias;
	}

	public Long getIdMovimentacao() {
		return idMovimentacao;
	}

	public void setIdMovimentacao(Long idMovimentacao) {
		this.idMovimentacao = idMovimentacao;
	}
	

}
