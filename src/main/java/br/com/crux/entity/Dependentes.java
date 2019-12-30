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

import br.com.crux.infra.constantes.Constantes;

@Entity
@Table(name = "dependentes")
public class Dependentes  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_dependente")
	@SequenceGenerator(name = "sq_id_dependente", sequenceName = "sq_id_dependente", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_dependente")
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_funcionario")
	private Funcionario funcionario;
	
	@Column(name = "ds_grau_parentesco")
	private String descricaoGrauParentesco;

	@Column(name = "ds_outras_informacoes")
	private String descricaoOutrasInformacoes;
	
	@Column(name="dt_desligamento")
	private LocalDateTime dataDesligamento;
	
	@Column(name = "ds_desligamento")
	private String descricaoDesligamento;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_pessoa_fisica")
	private PessoaFisica pessoaFisica;	
	

	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;
	
	public Dependentes() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public String getDescricaoGrauParentesco() {
		return descricaoGrauParentesco;
	}

	public void setDescricaoGrauParentesco(String descricaoGrauParentesco) {
		this.descricaoGrauParentesco = descricaoGrauParentesco;
	}

	public String getDescricaoOutrasInformacoes() {
		return descricaoOutrasInformacoes;
	}

	public void setDescricaoOutrasInformacoes(String descricaoOutrasInformacoes) {
		this.descricaoOutrasInformacoes = descricaoOutrasInformacoes;
	}

	public LocalDateTime getDataDesligamento() {
		return dataDesligamento;
	}

	public void setDataDesligamento(LocalDateTime dataDesligamento) {
		this.dataDesligamento = dataDesligamento;
	}

	public String getDescricaoDesligamento() {
		return descricaoDesligamento;
	}

	public void setDescricaoDesligamento(String descricaoDesligamento) {
		this.descricaoDesligamento = descricaoDesligamento;
	}

	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	
}