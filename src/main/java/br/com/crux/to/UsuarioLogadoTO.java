package br.com.crux.to;

import java.util.List;

public class UsuarioLogadoTO {

	private Long   idUsuario;
	private String username;
	private String nomeUsuario;
	private Boolean trocarSenha;
	private String token;
	private AcessoUnidadeTO unidadeLogada;
	private Long idPessoaFisica;

	private List<AcessoUnidadeTO> unidades;
	
	
	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long id) {
		this.idUsuario = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	public List<AcessoUnidadeTO> getUnidades() {
		return unidades;
	}

	public void setUnidades(List<AcessoUnidadeTO> unidades) {
		this.unidades = unidades;
	}

	public AcessoUnidadeTO getUnidadeLogada() {
		return unidadeLogada;
	}

	public void setUnidadeLogada(AcessoUnidadeTO unidadeLogada) {
		this.unidadeLogada = unidadeLogada;
	}

	public Boolean getTrocarSenha() {
		return trocarSenha;
	}

	public void setTrocarSenha(Boolean trocarSenha) {
		this.trocarSenha = trocarSenha;
	}

	public Long getIdPessoaFisica() {
		return idPessoaFisica;
	}

	public void setIdPessoaFisica(Long idPessoaFisica) {
		this.idPessoaFisica = idPessoaFisica;
	}
	
}
