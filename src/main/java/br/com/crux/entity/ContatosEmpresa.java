package br.com.crux.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the contatos_empresa database table.
 * 
 */
@Entity
@Table(name="contatos_empresa")
@NamedQuery(name="ContatosEmpresa.findAll", query="SELECT c FROM ContatosEmpresa c")
public class ContatosEmpresa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_contato_empresa")
	private Long idContatoEmpresa;

	@Column(name="ds_fim_contato")
	private String dsFimContato;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dt_fim_contato")
	private Date dtFimContato;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dt_inicio_contato")
	private Date dtInicioContato;

	//bi-directional many-to-one association to Cargo
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_cargo")
	private Cargo cargo;

	//bi-directional many-to-one association to Empresa
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_empresa")
	private Empresa empresa;

	//bi-directional many-to-one association to PessoasFisica
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_pessoa_fisica")
	private PessoaFisica pessoasFisica;

	//bi-directional many-to-one association to UsuariosSistema
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_usuario_apl")
	private UsuariosSistema usuariosSistema;

	public ContatosEmpresa() {
	}

	public Long getIdContatoEmpresa() {
		return this.idContatoEmpresa;
	}

	public void setIdContatoEmpresa(Long idContatoEmpresa) {
		this.idContatoEmpresa = idContatoEmpresa;
	}

	public String getDsFimContato() {
		return this.dsFimContato;
	}

	public void setDsFimContato(String dsFimContato) {
		this.dsFimContato = dsFimContato;
	}

	public Date getDtFimContato() {
		return this.dtFimContato;
	}

	public void setDtFimContato(Date dtFimContato) {
		this.dtFimContato = dtFimContato;
	}

	public Date getDtInicioContato() {
		return this.dtInicioContato;
	}

	public void setDtInicioContato(Date dtInicioContato) {
		this.dtInicioContato = dtInicioContato;
	}

	public Cargo getCargo() {
		return this.cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public PessoaFisica getPessoasFisica() {
		return this.pessoasFisica;
	}

	public void setPessoasFisica(PessoaFisica pessoasFisica) {
		this.pessoasFisica = pessoasFisica;
	}

	public UsuariosSistema getUsuariosSistema() {
		return this.usuariosSistema;
	}

	public void setUsuariosSistema(UsuariosSistema usuariosSistema) {
		this.usuariosSistema = usuariosSistema;
	}

}