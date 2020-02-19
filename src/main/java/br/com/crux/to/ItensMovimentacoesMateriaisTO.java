package br.com.crux.to;

import java.time.LocalDateTime;

public class ItensMovimentacoesMateriaisTO {

	private Long id;
	private LocalDateTime dataEnvioMaterial;
	private LocalDateTime dataRecebimentoMaterial;
	private EstoquesTO estoque;
	private FuncionarioTO funcionarioEnvio;
	private FuncionarioTO funcionarioRecebe;
	private MaterialTO material;
	private String numeroTombamento;
	private Long quantidadeMaterial;
	private ItensMovimentacoesTO itensMovimentacoes;
	private ItensPedidosMateriaisTO itensPedidosMateriais;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataEnvioMaterial() {
		return dataEnvioMaterial;
	}

	public void setDataEnvioMaterial(LocalDateTime dataEnvioMaterial) {
		this.dataEnvioMaterial = dataEnvioMaterial;
	}

	public LocalDateTime getDataRecebimentoMaterial() {
		return dataRecebimentoMaterial;
	}

	public void setDataRecebimentoMaterial(LocalDateTime dataRecebimentoMaterial) {
		this.dataRecebimentoMaterial = dataRecebimentoMaterial;
	}

	public EstoquesTO getEstoque() {
		return estoque;
	}

	public void setEstoque(EstoquesTO estoque) {
		this.estoque = estoque;
	}

	public FuncionarioTO getFuncionarioEnvio() {
		return funcionarioEnvio;
	}

	public void setFuncionarioEnvio(FuncionarioTO funcionarioEnvio) {
		this.funcionarioEnvio = funcionarioEnvio;
	}

	public FuncionarioTO getFuncionarioRecebe() {
		return funcionarioRecebe;
	}

	public void setFuncionarioRecebe(FuncionarioTO funcionarioRecebe) {
		this.funcionarioRecebe = funcionarioRecebe;
	}

	public MaterialTO getMaterial() {
		return material;
	}

	public void setMaterial(MaterialTO material) {
		this.material = material;
	}

	public String getNumeroTombamento() {
		return numeroTombamento;
	}

	public void setNumeroTombamento(String numeroTombamento) {
		this.numeroTombamento = numeroTombamento;
	}

	public Long getQuantidadeMaterial() {
		return quantidadeMaterial;
	}

	public void setQuantidadeMaterial(Long quantidadeMaterial) {
		this.quantidadeMaterial = quantidadeMaterial;
	}

	public ItensMovimentacoesTO getItensMovimentacoes() {
		return itensMovimentacoes;
	}

	public void setItensMovimentacoes(ItensMovimentacoesTO itensMovimentacoes) {
		this.itensMovimentacoes = itensMovimentacoes;
	}

	public ItensPedidosMateriaisTO getItensPedidosMateriais() {
		return itensPedidosMateriais;
	}

	public void setItensPedidosMateriais(ItensPedidosMateriaisTO itensPedidosMateriais) {
		this.itensPedidosMateriais = itensPedidosMateriais;
	}

}
