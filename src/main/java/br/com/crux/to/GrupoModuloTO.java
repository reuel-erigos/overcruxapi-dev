package br.com.crux.to;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GrupoModuloTO {
	
	private Long id;
	private String nome;
	private String descricao;
	
	private ModuloTO modulo;
	private PerfilAcessoTO perfilAcesso;
	private UnidadeTO unidade;
	
	private Long usuarioAlteracao;
	
	
	public GrupoModuloTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public ModuloTO getModulo() {
		return modulo;
	}

	public void setModulo(ModuloTO modulo) {
		this.modulo = modulo;
	}

	public PerfilAcessoTO getPerfilAcesso() {
		return perfilAcesso;
	}

	public void setPerfilAcesso(PerfilAcessoTO perfilAcesso) {
		this.perfilAcesso = perfilAcesso;
	}

	public UnidadeTO getUnidade() {
		return unidade;
	}

	public void setUnidade(UnidadeTO unidade) {
		this.unidade = unidade;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}
	
	
	
}
