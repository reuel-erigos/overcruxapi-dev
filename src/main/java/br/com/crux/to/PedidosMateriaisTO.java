
package br.com.crux.to;

import java.time.LocalDateTime;

public class PedidosMateriaisTO {

	private Long id;
	private String descricaoPedido;
	private LocalDateTime dataFimPeriodo;
	private LocalDateTime dataInicioPeriodo;
	private LocalDateTime dataPedido;
	private DepartamentoTO departamento;
	private FuncionarioTO funcionarioPedido;
	private FuncionarioTO funcionarioRecPed;
	private ProgramaTO programa;
	private ProjetoTO projeto;
	private UnidadeTO unidade;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricaoPedido() {
		return descricaoPedido;
	}
	public void setDescricaoPedido(String descricaoPedido) {
		this.descricaoPedido = descricaoPedido;
	}
	public LocalDateTime getDataFimPeriodo() {
		return dataFimPeriodo;
	}
	public void setDataFimPeriodo(LocalDateTime dataFimPeriodo) {
		this.dataFimPeriodo = dataFimPeriodo;
	}
	public LocalDateTime getDataInicioPeriodo() {
		return dataInicioPeriodo;
	}
	public void setDataInicioPeriodo(LocalDateTime dataInicioPeriodo) {
		this.dataInicioPeriodo = dataInicioPeriodo;
	}
	public LocalDateTime getDataPedido() {
		return dataPedido;
	}
	public void setDataPedido(LocalDateTime dataPedido) {
		this.dataPedido = dataPedido;
	}
	public DepartamentoTO getDepartamento() {
		return departamento;
	}
	public void setDepartamento(DepartamentoTO departamento) {
		this.departamento = departamento;
	}
	public FuncionarioTO getFuncionarioPedido() {
		return funcionarioPedido;
	}
	public void setFuncionarioPedido(FuncionarioTO funcionarioPedido) {
		this.funcionarioPedido = funcionarioPedido;
	}
	public FuncionarioTO getFuncionarioRecPed() {
		return funcionarioRecPed;
	}
	public void setFuncionarioRecPed(FuncionarioTO funcionarioRecPed) {
		this.funcionarioRecPed = funcionarioRecPed;
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
	
}