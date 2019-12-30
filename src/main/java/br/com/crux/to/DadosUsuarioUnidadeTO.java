package br.com.crux.to;

public class DadosUsuarioUnidadeTO {
	
	private Long idPessoaFisica;
	private String nomeCompleto;
	private String email;
	private Long idUsuario;
	
	public DadosUsuarioUnidadeTO() {
	}
	
	
	public DadosUsuarioUnidadeTO(Long idPessoaFisica, String nomeCompleto, String email, Long idUsuario) {
		super();
		this.idPessoaFisica = idPessoaFisica;
		this.nomeCompleto = nomeCompleto;
		this.email = email;
		this.idUsuario = idUsuario;
	}
	
	
	public Long getIdPessoaFisica() {
		return idPessoaFisica;
	}
	public void setIdPessoaFisica(Long idPessoaFisica) {
		this.idPessoaFisica = idPessoaFisica;
	}
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	
	

}
