package br.com.crux.to;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.crux.infra.adapter.LocalDateTimeAdapter;


/**
 * The persistent class for the familiares database table.
 * 
 */
public class FamiliarResponsavelTO {

	private Long               id;
	private String             descGrauParentesco;
	private String             situacaoParentesco;
	private String             descOutrasInformacoes;
	private String             descDesligamento;
	private PessoaFisicaTO     pessoasFisica;
	private AlunoTO            aluno;
	private Long               usuarioAlteracao;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime      dataCadastro;
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime      dataDesligamento;

	private Boolean transportaAluno;
	private Boolean tutelaAluno;
	private Boolean responsavelFinanceiroPeloAluno;
	
	public FamiliarResponsavelTO() {
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

	public String getSituacaoParentesco() {
		return situacaoParentesco;
	}

	public void setSituacaoParentesco(String situacaoParentesco) {
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

	public PessoaFisicaTO getPessoasFisica() {
		return pessoasFisica;
	}

	public void setPessoasFisica(PessoaFisicaTO pessoasFisica) {
		this.pessoasFisica = pessoasFisica;
	}

	public AlunoTO getAluno() {
		return aluno;
	}

	public void setAluno(AlunoTO aluno) {
		this.aluno = aluno;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuariosSistema) {
		this.usuarioAlteracao = usuariosSistema;
	}

	public Boolean getTransportaAluno() {
		return transportaAluno;
	}

	public void setTransportaAluno(Boolean transportaAluno) {
		this.transportaAluno = transportaAluno;
	}

	public Boolean getTutelaAluno() {
		return tutelaAluno;
	}

	public void setTutelaAluno(Boolean tutelaAluno) {
		this.tutelaAluno = tutelaAluno;
	}

	public Boolean getResponsavelFinanceiroPeloAluno() {
		return responsavelFinanceiroPeloAluno;
	}

	public void setResponsavelFinanceiroPeloAluno(Boolean responsavelFinanceiroPeloAluno) {
		this.responsavelFinanceiroPeloAluno = responsavelFinanceiroPeloAluno;
	}

	
}