
package br.com.crux.to;

import java.time.LocalDateTime;

public class CotacoesMaterialTO {

	private Long id;
	private LocalDateTime dataCotacao;
	private LocalDateTime dataValidadeCotacao;
	private EmpresaTO empresa;
	private MaterialTO material;
	private PedidosMateriaisTO pedidosMaterial;
	private Double quantidadeMaterial;
	private Double valorTotalCotacao;
	private Double valorUnitarioCotacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataCotacao() {
		return dataCotacao;
	}

	public void setDataCotacao(LocalDateTime dataCotacao) {
		this.dataCotacao = dataCotacao;
	}

	public LocalDateTime getDataValidadeCotacao() {
		return dataValidadeCotacao;
	}

	public void setDataValidadeCotacao(LocalDateTime dataValidadeCotacao) {
		this.dataValidadeCotacao = dataValidadeCotacao;
	}

	public EmpresaTO getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresaTO empresa) {
		this.empresa = empresa;
	}

	public MaterialTO getMaterial() {
		return material;
	}

	public void setMaterial(MaterialTO material) {
		this.material = material;
	}

	public PedidosMateriaisTO getPedidosMaterial() {
		return pedidosMaterial;
	}

	public void setPedidosMaterial(PedidosMateriaisTO pedido) {
		this.pedidosMaterial = pedido;
	}

	public Double getQuantidadeMaterial() {
		return quantidadeMaterial;
	}

	public void setQuantidadeMaterial(Double quantidadeMaterial) {
		this.quantidadeMaterial = quantidadeMaterial;
	}

	public Double getValorTotalCotacao() {
		return valorTotalCotacao;
	}

	public void setValorTotalCotacao(Double valorTotalCotacao) {
		this.valorTotalCotacao = valorTotalCotacao;
	}

	public Double getValorUnitarioCotacao() {
		return valorUnitarioCotacao;
	}

	public void setValorUnitarioCotacao(Double valorUnitarioCotacao) {
		this.valorUnitarioCotacao = valorUnitarioCotacao;
	}

}