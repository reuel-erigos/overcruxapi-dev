package br.com.crux.to;

public class ContasCentrosCustoTO {

	private Long id;
	private ContasBancariaTO contasBancaria;
	private Long idPrograma;
	private Long idProjeto;
	
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
	public Long getIdPrograma() {
		return idPrograma;
	}
	public void setIdPrograma(Long idPrograma) {
		this.idPrograma = idPrograma;
	}
	public Long getIdProjeto() {
		return idProjeto;
	}
	public void setIdProjeto(Long idProjeto) {
		this.idProjeto = idProjeto;
	}
	
}
