package br.com.crux.to;

public class ContasCentrosCustoTO {

	private Long id;
	private ContasBancariaTO contasBancaria;
	private ProgramaTO programa;
	private ProjetoTO projeto;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ContasBancariaTO getContasBancaria() {
		return contasBancaria;
	}
	public void setContasBancaria(ContasBancariaTO contasBancaria) {
		this.contasBancaria = contasBancaria;
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

	
	
}
