package br.com.crux.to;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.crux.infra.adapter.LocalDateTimeAdapter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DependentesTO {

	private Long id;
	
	private String descricaoGrauParentesco;
	private String descricaoOutrasInformacoes;
	private String descricaoDesligamento;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataDesligamento;
		
	private Long idFuncionario;
	private PessoaFisicaTO pessoaFisica;	
	private Long usuarioAlteracao;		

	public DependentesTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public PessoaFisicaTO getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisicaTO pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public Long getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(Long idFuncionario) {
		this.idFuncionario = idFuncionario;
	}
	
	

}
