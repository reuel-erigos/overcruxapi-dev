package br.com.crux.to;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InstituicaoTO {

	private Long id;
	private String nome;
	private Long idArquivo;
	private Long usuarioAlteracao;
	private Boolean mostraLista;
	private String areaAtuacao;
	private String apresentacao;
	private String visao;
	private String missao;
	private String valores;
	private String finalidadesEstatutarias;
	
	
	public InstituicaoTO() {
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

	public Long getIdArquivo() {
		return idArquivo;
	}

	public void setIdArquivo(Long arquivo) {
		this.idArquivo = arquivo;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public Boolean getMostraLista() {
		return mostraLista;
	}

	public void setMostraLista(Boolean mostraLista) {
		this.mostraLista = mostraLista;
	}

	public String getAreaAtuacao() {
		return areaAtuacao;
	}

	public void setAreaAtuacao(String areaAtuacao) {
		this.areaAtuacao = areaAtuacao;
	}

	public String getApresentacao() {
		return apresentacao;
	}

	public void setApresentacao(String apresentacao) {
		this.apresentacao = apresentacao;
	}

	public String getVisao() {
		return visao;
	}

	public void setVisao(String visao) {
		this.visao = visao;
	}

	public String getMissao() {
		return missao;
	}

	public void setMissao(String missao) {
		this.missao = missao;
	}

	public String getValores() {
		return valores;
	}

	public void setValores(String valores) {
		this.valores = valores;
	}

	public String getFinalidadesEstatutarias() {
		return finalidadesEstatutarias;
	}

	public void setFinalidadesEstatutarias(String finalidadesEstatutarias) {
		this.finalidadesEstatutarias = finalidadesEstatutarias;
	}

	

}
