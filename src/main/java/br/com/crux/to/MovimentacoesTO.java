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
	private ProgramaTO programa;
	private ProjetoTO projeto;
	private UnidadeTO unidade;
	private DepartamentoTO departamento;
	private Long qtdParcelas;
	
	private List<ItensMovimentacoesTO> itensMovimentacoes; 
	private List<FaturaTO> faturas; 
	
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
	public ProgramaTO getPrograma() {
		return programa;
	}
	public void setPrograma(ProgramaTO programa) {
		this.programa = programa;
	}
	public ProjetoTO getProjeto() {
		return projeto;
	}
	public void setProjeto(ProjetoTO projeto) {
		this.projeto = projeto;
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

}
