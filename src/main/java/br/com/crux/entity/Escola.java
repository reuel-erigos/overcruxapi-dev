package br.com.crux.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.crux.infra.constantes.Constantes;


/**
 * The persistent class for the acoes database table.
 * 
 */
@Entity
@Table(name="escolas")
public class Escola implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_escola")
	@SequenceGenerator(name = "sq_id_escola", sequenceName = "sq_id_escola", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_escola", unique=true, nullable=false, precision=10)
	private Long id;

	@Column(name="cd_escola")
	private String codigo;	
	
	@Column(name="nm_escola")
	private String nome;

	@Column(name="cs_tipo_escola")
	private String tipo;
	
	@Column(name="ds_etapa_ensino")
	private String etapaEnsino;
	
	@Column(name="nr_telefone")
	private String telefone;
	
	@Column(name="nr_celular")
	private String celular;
	
	@Column(name="ds_email")
	private String email;
	
	@Column(name="ds_home_page")
	private String homePage;
	
	@Column(name="cs_tipo_localidade")
	private String tipoLocalidade;
	
	@Column(name="nr_cep")
	private Long cep;
	
	@Column(name="ds_endereco")
	private String endereco;
	
	@Column(name="ds_complemento_endereco")
	private String complemento;
	
	@Column(name="ds_bairro")
	private String bairro;
	
	@Column(name="uf_escola")
	private String uf;
	
	@Column(name="nm_cidade")
	private String cidade;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_ra")
	private RegiaoAdministrativa regiaoAdministrativa;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_instituicao")
	private Instituicao instituicao;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_usuario_apl")
	private UsuariosSistema usuarioSistema;
	
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

	public RegiaoAdministrativa getRegiaoAdministrativa() {
		return regiaoAdministrativa;
	}

	public void setRegiaoAdministrativa(RegiaoAdministrativa regiaoAdministrativa) {
		this.regiaoAdministrativa = regiaoAdministrativa;
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	public UsuariosSistema getUsuarioSistema() {
		return usuarioSistema;
	}

	public void setUsuarioSistema(UsuariosSistema usuarioSistema) {
		this.usuarioSistema = usuarioSistema;
	}

}