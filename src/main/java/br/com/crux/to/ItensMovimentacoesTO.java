package br.com.crux.to;

public class ItensMovimentacoesTO {

	private Long id;
	private String descricaoItemMovimentacao;
	private CategoriasContabeisTO categoria;
	private DepartamentoTO departamento;
	private MaterialTO material;
	private MovimentacoesTO movimentacao;
	private UnidadeTO unidade;
	private Long quantidadeMaterial;
	private Double valorTotalItem;
	private Double valorUnitarioItem;
	private PedidosMateriaisTO pedidosMateriais;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricaoItemMovimentacao() {
		return descricaoItemMovimentacao;
	}

	public void setDescricaoItemMovimentacao(String descricaoItemMovimentacao) {
		this.descricaoItemMovimentacao = descricaoItemMovimentacao;
	}

	public CategoriasContabeisTO getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriasContabeisTO categoria) {
		this.categoria = categoria;
	}

	public DepartamentoTO getDepartamento() {
		return departamento;
	}

	public void setDepartamento(DepartamentoTO departamento) {
		this.departamento = departamento;
	}

	public MaterialTO getMaterial() {
		return material;
	}

	public void setMaterial(MaterialTO material) {
		this.material = material;
	}

	public MovimentacoesTO getMovimentacao() {
		return movimentacao;
	}

	public void setMovimentacao(MovimentacoesTO movimentacao) {
		this.movimentacao = movimentacao;
	}

	public UnidadeTO getUnidade() {
		return unidade;
	}

	public void setUnidade(UnidadeTO unidade) {
		this.unidade = unidade;
	}


	public Long getQuantidadeMaterial() {
		return quantidadeMaterial;
	}

	public void setQuantidadeMaterial(Long quantidadeMaterial) {
		this.quantidadeMaterial = quantidadeMaterial;
	}

	public Double getValorTotalItem() {
		return valorTotalItem;
	}

	public void setValorTotalItem(Double valorTotalItem) {
		this.valorTotalItem = valorTotalItem;
	}

	public Double getValorUnitarioItem() {
		return valorUnitarioItem;
	}

	public void setValorUnitarioItem(Double valorUnitarioItem) {
		this.valorUnitarioItem = valorUnitarioItem;
	}

	public PedidosMateriaisTO getPedidosMateriais() {
		return pedidosMateriais;
	}

	public void setPedidosMateriais(PedidosMateriaisTO pedidosMateriais) {
		this.pedidosMateriais = pedidosMateriais;
	}

}
