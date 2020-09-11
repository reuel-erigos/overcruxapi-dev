package br.com.crux.to;

public class ItensMovimentacoesTO {

	private Long id;
	private String descricaoItemMovimentacao;
	private CategoriasContabeisTO categoria;
	private DepartamentoTO departamento;
	private MaterialTO material;
	private Long idMovimentacao;
	private UnidadeTO unidade;
	private Long quantidadeMaterial;
	private Double valorTotalItem;
	private Double valorUnitarioItem;
	private PedidosMateriaisTO pedidosMateriais;

	private Double valorISS;
	private Double valorICMS;
	private Double valorIPI;
	private Double valorPisConfinsCsll;
	private Double valorInss;

	
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

	public Long getIdMovimentacao() {
		return idMovimentacao;
	}

	public void setIdMovimentacao(Long idMovimentacao) {
		this.idMovimentacao = idMovimentacao;
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
	
	

}
