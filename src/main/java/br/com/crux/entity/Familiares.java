package br.com.crux.entity;

import java.time.LocalDateTime;

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

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import br.com.crux.enums.SituacaoParentesco;
import br.com.crux.infra.constantes.Constantes;


/**
 * The persistent class for the familiares database table.
 * 
 */
@Entity
@Table(name="familiares")
public class Familiares {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_familiar")
	@SequenceGenerator(name = "sq_id_familiar", sequenceName = "sq_id_familiar", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_familiar")
	private Long id;
	
	@Column(name="dt_cadastro")
	private LocalDateTime dataCadastro;

	@Column(name="ds_grau_parentesco")
	private String descGrauParentesco;

	//Classificador da situação do grau de parentesco (E = ESTREMECIDO; I = INTERROMPIDO; X = INEXISTENTE)
	@Column(name="st_situacao_parentesco")
	@Type(type = "br.com.crux.infra.dao.GenericEnumUserType", 
	parameters = { @Parameter(name = "enumClass", value = "br.com.crux.enums.SituacaoParentesco"),
			       @Parameter(name = "keyName", value = "tipo")}) 
	private SituacaoParentesco situacaoParentesco;
	
	@Column(name="ds_outras_informacoes")
	private String descOutrasInformacoes;
	
	@Column(name="ds_desligamento")
	private String descDesligamento;

	@Column(name="dt_desligamento")
	private LocalDateTime dataDesligamento;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_pessoa_fisica")
	private PessoaFisica pessoasFisica;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_aluno")
	private Aluno aluno;

	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;

	
	public Familiares() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getDescGrauParentesco() {
		return descGrauParentesco;
	}

	public void setDescGrauParentesco(String descGrauParentesco) {
		this.descGrauParentesco = descGrauParentesco;
	}

	public SituacaoParentesco getSituacaoParentesco() {
		return situacaoParentesco;
	}

	public void setSituacaoParentesco(SituacaoParentesco situacaoParentesco) {
		this.situacaoParentesco = situacaoParentesco;
	}

	public String getDescOutrasInformacoes() {
		return descOutrasInformacoes;
	}

	public void setDescOutrasInformacoes(String descOutrasInformacoes) {
		this.descOutrasInformacoes = descOutrasInformacoes;
	}

	public String getDescDesligamento() {
		return descDesligamento;
	}

	public void setDescDesligamento(String descDesligamento) {
		this.descDesligamento = descDesligamento;
	}

	public LocalDateTime getDataDesligamento() {
		return dataDesligamento;
	}

	public void setDataDesligamento(LocalDateTime dataDesligamento) {
		this.dataDesligamento = dataDesligamento;
	}

	public PessoaFisica getPessoasFisica() {
		return pessoasFisica;
	}

	public void setPessoasFisica(PessoaFisica pessoasFisica) {
		this.pessoasFisica = pessoasFisica;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuariosSistema) {
		this.usuarioAlteracao = usuariosSistema;
	}

	
}