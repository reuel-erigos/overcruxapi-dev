package br.com.crux.to;

import java.time.LocalDateTime;
import java.util.List;

public class CategoriasMovimentosTO {

	private Long id;
	private Long idMovimento;
	private CategoriasContabeisTO categoriaOrigem;
	private CategoriasContabeisTO categoriaDestino;
	private Double valor;
	private String descricao;
	private Long usuarioAlteracao;
	private LocalDateTime dataMovimentacao;
	private ProgramaTO programa;
	private ProjetoTO projeto;
	
	private List<RateiosCategoriasMovimentosTO> rateioCategoriasMovimentos;

	public CategoriasMovimentosTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdMovimento() {
		return idMovimento;
	}

	public void setIdMovimento(Long idMovimento) {
		this.idMovimento = idMovimento;
	}

	public CategoriasContabeisTO getCategoriaOrigem() {
		return categoriaOrigem;
	}

	public void setCategoriaOrigem(CategoriasContabeisTO categoriaOrigem) {
		this.categoriaOrigem = categoriaOrigem;
	}

	public CategoriasContabeisTO getCategoriaDestino() {
		return categoriaDestino;
	}

	public void setCategoriaDestino(CategoriasContabeisTO categoriaDestino) {
		this.categoriaDestino = categoriaDestino;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public List<RateiosCategoriasMovimentosTO> getRateioCategoriasMovimentos() {
		return rateioCategoriasMovimentos;
	}

	public void setRateioCategoriasMovimentos(List<RateiosCategoriasMovimentosTO> rateioCategoriasMovimentos) {
		this.rateioCategoriasMovimentos = rateioCategoriasMovimentos;
	}

	public LocalDateTime getDataMovimentacao() {
		return dataMovimentacao;
	}

	public void setDataMovimentacao(LocalDateTime dataMovimentacao) {
		this.dataMovimentacao = dataMovimentacao;
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
