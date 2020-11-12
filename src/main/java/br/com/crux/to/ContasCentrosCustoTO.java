package br.com.crux.to;

public class ContasCentrosCustoTO {

	private Long id;
	private ContasBancariaTO contasBancaria;
	private Long idParceriasPrograma;
	private Long idParceriasProjeto;
	
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
	public Long getIdParceriasPrograma() {
		return idParceriasPrograma;
	}
	public void setIdParceriasPrograma(Long idParceriasPrograma) {
		this.idParceriasPrograma = idParceriasPrograma;
	}
	public Long getIdParceriasProjeto() {
		return idParceriasProjeto;
	}
	public void setIdParceriasProjeto(Long idParceriasProjeto) {
		this.idParceriasProjeto = idParceriasProjeto;
	}
	
	
	
}
