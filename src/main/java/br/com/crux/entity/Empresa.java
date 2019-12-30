package br.com.crux.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import br.com.crux.enums.CategoriaEmpresa;
import br.com.crux.enums.TipoEmpresa;
import br.com.crux.infra.constantes.Constantes;
import br.com.crux.infra.dao.SimNaoConverter;

/**
 * The persistent class for the empresas database table.
 * 
 */
@Entity
@Table(name = "empresas")
public class Empresa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_empresa")
	@SequenceGenerator(name = "sq_id_empresa", sequenceName = "sq_id_empresa", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_empresa")
	private Long id;

	@Column(name = "cd_empresa")
	private String codigo;

	@Column(name = "nm_razao_social")
	private String nomeRazaoSocial;

	@Column(name = "nm_fantasia")
	private String nomeFantasia;

	@Column(name = "nr_cnpj")
	private String cnpj;

	@Column(name = "nr_inscricao_estadual")
	private String inscricaoEstadual;

	@Column(name = "nr_inscricao_municipal")
	private String inscricaoMunicipal;

	// S - Sim; N - Não
	@Column(name = "st_ativa")
	private String ativa;

	@Column(name = "cs_tipo_empresa")
	@Type(type = "br.com.crux.infra.dao.GenericEnumUserType", parameters = {
			@Parameter(name = "enumClass", value = "br.com.crux.enums.TipoEmpresa"),
			@Parameter(name = "keyName", value = "tipo")})
	private TipoEmpresa tipoEmpresa;

	@Column(name = "vl_icms")
	private Double valorIcms;

	@Column(name = "ds_tipo_empresa")
	private String descricaoTipoEmpresa;

	@Column(name = "cs_categoria_empresa")
	@Type(type = "br.com.crux.infra.dao.GenericEnumUserType", parameters = {
			@Parameter(name = "enumClass", value = "br.com.crux.enums.CategoriaEmpresa"),
			@Parameter(name = "keyName", value = "tipo")})
	private CategoriaEmpresa categoriaEmpresa;

	@Column(name = "ds_categoria_empresa")
	private String descricaoCategoriaEmpresa;

	@Column(name = "nr_telefone")
	private String telefone;

	@Column(name = "ds_email")
	private String email;

	@Convert(converter = SimNaoConverter.class)
	@Column(name = "st_autoriza_email")
	private Boolean autorizaEmail;

	@Column(name = "ds_home_page")
	private String homePage;

	@Column(name = "ds_endereco")
	private String endereco;

	@Column(name = "ds_bairro")
	private String bairro;

	@Column(name = "nm_cidade")
	private String cidade;

	@Column(name = "nr_cep")
	private Long cep;

	@Column(name = "uf_empresa")
	private String uf;

	@Column(name = "id_usuario_apl")
	private Long usuarioAlteracao;

	public Empresa() {
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

	public TipoEmpresa getTipoEmpresa() {
		return tipoEmpresa;
	}

	public void setTipoEmpresa(TipoEmpresa tipoEmpresa) {
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

	public CategoriaEmpresa getCategoriaEmpresa() {
		return categoriaEmpresa;
	}

	public void setCategoriaEmpresa(CategoriaEmpresa categoriaEmpresa) {
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