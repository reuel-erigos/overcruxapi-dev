package br.com.crux.to;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class EmpresaTO {

	private Long id;
	private String codigo;
	private String nomeRazaoSocial;
	private String nomeFantasia;
	private String cnpj;
	private String inscricaoEstadual;
	private String inscricaoMunicipal;
	private String ativa;
	private String tipoEmpresa;
	private Double valorIcms;
	private String descricaoTipoEmpresa;
	private String categoriaEmpresa;
	private String descricaoCategoriaEmpresa;
	private String telefone;
	private String email;
	private Boolean autorizaEmail;
	private String homePage;
	private String endereco;
	private String bairro;
	private String cidade;
	private Long cep;
	private String uf;
	private Long usuarioAlteracao;

	public EmpresaTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNomeRazaoSocial() {
		return nomeRazaoSocial;
	}

	public void setNomeRazaoSocial(String nomeRazaoSocial) {
		this.nomeRazaoSocial = nomeRazaoSocial;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public String getInscricaoMunicipal() {
		return inscricaoMunicipal;
	}

	public void setInscricaoMunicipal(String inscricaoMunicipal) {
		this.inscricaoMunicipal = inscricaoMunicipal;
	}

	public String getAtiva() {
		return ativa;
	}

	public void setAtiva(String ativa) {
		this.ativa = ativa;
	}

	public String getTipoEmpresa() {
		return tipoEmpresa;
	}

	public void setTipoEmpresa(String tipoEmpresa) {
		this.tipoEmpresa = tipoEmpresa;
	}

	public Double getValorIcms() {
		return valorIcms;
	}

	public void setValorIcms(Double valorIcms) {
		this.valorIcms = valorIcms;
	}

	public String getDescricaoTipoEmpresa() {
		return descricaoTipoEmpresa;
	}

	public void setDescricaoTipoEmpresa(String descricaoTipoEmpresa) {
		this.descricaoTipoEmpresa = descricaoTipoEmpresa;
	}

	public String getCategoriaEmpresa() {
		return categoriaEmpresa;
	}

	public void setCategoriaEmpresa(String categoriaEmpresa) {
		this.categoriaEmpresa = categoriaEmpresa;
	}

	public String getDescricaoCategoriaEmpresa() {
		return descricaoCategoriaEmpresa;
	}

	public void setDescricaoCategoriaEmpresa(String descricaoCategoriaEmpresa) {
		this.descricaoCategoriaEmpresa = descricaoCategoriaEmpresa;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getAutorizaEmail() {
		return autorizaEmail;
	}

	public void setAutorizaEmail(Boolean autorizaEmail) {
		this.autorizaEmail = autorizaEmail;
	}

	public String getHomePage() {
		return homePage;
	}

	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Long getCep() {
		return cep;
	}

	public void setCep(Long cep) {
		this.cep = cep;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

}
