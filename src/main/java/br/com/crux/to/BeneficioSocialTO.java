package br.com.crux.to;

public class BeneficioSocialTO {

	private Long id;
	private String nome;
	private String descricao;
	private String origemSocial;
	private Long idInstituicao;
	
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
	public String getOrigemSocial() {
		return origemSocial;
	}
	public void setOrigemSocial(String origemSocial) {
		this.origemSocial = origemSocial;
	}
	public Long getIdInstituicao() {
		return idInstituicao;
	}
	public void setIdInstituicao(Long idInstituicao) {
		this.idInstituicao = idInstituicao;
	}
	public Long getIdUsuarioApl(){ return  idUsuarioApl;}
	public void setIdUsuarioApl(Long idUsuarioApl){
		this.idUsuarioApl = idUsuarioApl;
	}


}