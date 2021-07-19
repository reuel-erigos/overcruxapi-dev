
package br.com.crux.to;

import java.time.LocalDateTime;

public class MovimentacoesContabeisTO {

	private Long id;
	private LocalDateTime dataMovimentacao;
	private Double valorMovimentacao;
	private String descricao;
	private ProgramaTO programa01;
	private ProjetoTO projeto01;
	private CategoriasContabeisTO categoriaOrigem01;
	private CategoriasContabeisTO categoriaDestino01;
	private ProgramaTO programa02;
	private ProjetoTO projeto02;
	private CategoriasContabeisTO categoriaOrigem02;
	private CategoriasContabeisTO categoriaDestino02;
	private Long idInstituicao;
	
	public MovimentacoesContabeisTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataMovimentacao() {
		return dataMovimentacao;
	}

	public void setDataMovimentacao(LocalDateTime dataMovimentacao) {
		this.dataMovimentacao = dataMovimentacao;
	}

	public Double getValorMovimentacao() {
		return valorMovimentacao;
	}

	public void setValorMovimentacao(Double valorMovimentacao) {
		this.valorMovimentacao = valorMovimentacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public ProgramaTO getPrograma01() {
		return programa01;
	}

	public void setPrograma01(ProgramaTO programa01) {
		this.programa01 = programa01;
	}

	public ProjetoTO getProjeto01() {
		return projeto01;
	}

	public void setProjeto01(ProjetoTO projeto01) {
		this.projeto01 = projeto01;
	}

	public CategoriasContabeisTO getCategoriaOrigem01() {
		return categoriaOrigem01;
	}

	public void setCategoriaOrigem01(CategoriasContabeisTO categoriaOrigem01) {
		this.categoriaOrigem01 = categoriaOrigem01;
	}

	public CategoriasContabeisTO getCategoriaDestino01() {
		return categoriaDestino01;
	}

	public void setCategoriaDestino01(CategoriasContabeisTO categoriaDestino01) {
		this.categoriaDestino01 = categoriaDestino01;
	}

	public ProgramaTO getPrograma02() {
		return programa02;
	}

	public void setPrograma02(ProgramaTO programa02) {
		this.programa02 = programa02;
	}

	public ProjetoTO getProjeto02() {
		return projeto02;
	}

	public void setProjeto02(ProjetoTO projeto02) {
		this.projeto02 = projeto02;
	}

	public CategoriasContabeisTO getCategoriaOrigem02() {
		return categoriaOrigem02;
	}

	public void setCategoriaOrigem02(CategoriasContabeisTO categoriaOrigem02) {
		this.categoriaOrigem02 = categoriaOrigem02;
	}

	public CategoriasContabeisTO getCategoriaDestino02() {
		return categoriaDestino02;
	}

	public void setCategoriaDestino02(CategoriasContabeisTO categoriaDestino02) {
		this.categoriaDestino02 = categoriaDestino02;
	}

	public Long getIdInstituicao() {
		return idInstituicao;
	}

	public void setIdInstituicao(Long idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	
}