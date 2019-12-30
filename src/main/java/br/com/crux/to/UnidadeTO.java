package br.com.crux.to;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.crux.infra.adapter.LocalDateTimeAdapter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UnidadeTO {

	private Long idUnidade;
	private String siglaUnidade;
	private String nomeUnidade;
	private String endereco;
	private String telefone;
	private String descricaoSituacaoImovel;
	private String descricaoEstruturaFisicaImovel;
	private Long arquivo;
	private Long usuarioAlteracao;
	private String visao;
	private String missao;
	private String email;
	private Long cep;
	private String bairro;
	private String uf;
	private String celular;
	private String tipoUnidade;
	private String classificacaoSituacaoImovel;
	private String nomeFantasia;
	private String cnpj;
	private String inscricaoEstadual;
	private String inscricaoMunicipal;
	private String homePage;
	private String cidade;
	private InstituicaoTO instituicao;
	private String numeroCnas;
	private String numeroCdca;
	private Boolean unidadeLogada;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataVigenciaCdca;
	
	private String numeroCas;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataVigenciaCas;
	

	public UnidadeTO() {
	}

	public Long getIdUnidade() {
		return idUnidade;
	}

	public void setIdUnidade(Long idUnidade) {
		this.idUnidade = idUnidade;
	}

	public String getSiglaUnidade() {
		return siglaUnidade;
	}

	public void setSiglaUnidade(String codigoUnidade) {
		this.siglaUnidade = codigoUnidade;
	}

	public String getNomeUnidade() {
		return nomeUnidade;
	}

	public void setNomeUnidade(String nomeUnidade) {
		this.nomeUnidade = nomeUnidade;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getDescricaoSituacaoImovel() {
		return descricaoSituacaoImovel;
	}

	public void setDescricaoSituacaoImovel(String descricaoSituacaoImovel) {
		this.descricaoSituacaoImovel = descricaoSituacaoImovel;
	}

	public String getDescricaoEstruturaFisicaImovel() {
		return descricaoEstruturaFisicaImovel;
	}

	public void setDescricaoEstruturaFisicaImovel(String descricaoEstruturaFisicaImovel) {
		this.descricaoEstruturaFisicaImovel = descricaoEstruturaFisicaImovel;
	}

	public Long getArquivo() {
		return arquivo;
	}

	public void setArquivo(Long arquivo) {
		this.arquivo = arquivo;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getCep() {
		return cep;
	}

	public void setCep(Long cep) {
		this.cep = cep;
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

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getTipoUnidade() {
		return tipoUnidade;
	}

	public void setTipoUnidade(String tipoUnidade) {
		this.tipoUnidade = tipoUnidade;
	}

	public String getClassificacaoSituacaoImovel() {
		return classificacaoSituacaoImovel;
	}

	public void setClassificacaoSituacaoImovel(String classificacaoSituacaoImovel) {
		this.classificacaoSituacaoImovel = classificacaoSituacaoImovel;
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

	public String getHomePage() {
		return homePage;
	}

	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public InstituicaoTO getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(InstituicaoTO instituicao) {
		this.instituicao = instituicao;
	}

	public String getNumeroCnas() {
		return numeroCnas;
	}

	public void setNumeroCnas(String numeroCnas) {
		this.numeroCnas = numeroCnas;
	}

	public String getNumeroCdca() {
		return numeroCdca;
	}

	public void setNumeroCdca(String numeroCdca) {
		this.numeroCdca = numeroCdca;
	}

	public LocalDateTime getDataVigenciaCdca() {
		return dataVigenciaCdca;
	}

	public void setDataVigenciaCdca(LocalDateTime dataVigenciaCdca) {
		this.dataVigenciaCdca = dataVigenciaCdca;
	}

	public String getNumeroCas() {
		return numeroCas;
	}

	public void setNumeroCas(String numeroCas) {
		this.numeroCas = numeroCas;
	}

	public LocalDateTime getDataVigenciaCas() {
		return dataVigenciaCas;
	}

	public void setDataVigenciaCas(LocalDateTime dataVigenciaCas) {
		this.dataVigenciaCas = dataVigenciaCas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idUnidade == null) ? 0 : idUnidade.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UnidadeTO other = (UnidadeTO) obj;
		if (idUnidade == null) {
			if (other.idUnidade != null)
				return false;
		} else if (!idUnidade.equals(other.idUnidade))
			return false;
		return true;
	}

	public Boolean getUnidadeLogada() {
		if(Objects.isNull(unidadeLogada)) {
			unidadeLogada = Boolean.FALSE;
		}
		
		return unidadeLogada;
	}

	public void setUnidadeLogada(Boolean unidadeLogada) {
		this.unidadeLogada = unidadeLogada;
	}

	
	
}
