package br.com.crux.to;

public class EscolaTO {

	private Long id;

	private String codigo;	
	
	private String nome;

	private String tipo;
	
	private String etapaEnsino;
	
	private String telefone;
	
	private String celular;
	
	private String email;
	
	private String homePage;
	
	private String tipoLocalidade;
	
	private Long cep;
	
	private String endereco;
	
	private String complemento;
	
	private String bairro;
	
	private String uf;
	
	private String cidade;
	
	private RegiaoAdministrativaTO regiaoAdministrativa;
	
	private InstituicaoTO instituicao;
	
	private UsuariosSistemaTO usuarioSistema;

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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getEtapaEnsino() {
		return etapaEnsino;
	}

	public void setEtapaEnsino(String etapaEnsino) {
		this.etapaEnsino = etapaEnsino;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHomePage() {
		return homePage;
	}

	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}

	public String getTipoLocalidade() {
		return tipoLocalidade;
	}

	public void setTipoLocalidade(String tipoLocalidade) {
		this.tipoLocalidade = tipoLocalidade;
	}

	public Long getCep() {
		return cep;
	}

	public void setCep(Long cep) {
		this.cep = cep;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public RegiaoAdministrativaTO getRegiaoAdministrativa() {
		return regiaoAdministrativa;
	}

	public void setRegiaoAdministrativa(RegiaoAdministrativaTO regiaoAdministrativa) {
		this.regiaoAdministrativa = regiaoAdministrativa;
	}

	public InstituicaoTO getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(InstituicaoTO instituicao) {
		this.instituicao = instituicao;
	}

	public UsuariosSistemaTO getUsuarioSistema() {
		return usuarioSistema;
	}

	public void setUsuarioSistema(UsuariosSistemaTO usuarioSistema) {
		this.usuarioSistema = usuarioSistema;
	}
	
}
