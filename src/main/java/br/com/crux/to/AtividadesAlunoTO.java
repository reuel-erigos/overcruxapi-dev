package br.com.crux.to;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.crux.infra.adapter.LocalDateTimeAdapter;

public class AtividadesAlunoTO {

	private Long id;
	private String descDesligamento;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataInicioAtividade;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataDesvinculacao;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataAlteracaoAtividade;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataCadastroAtividade;
	
	private AlunoTO aluno;
	private AtividadesTO atividade;
	private Long usuarioAlteracao;

	public AtividadesAlunoTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescDesligamento() {
		return descDesligamento;
	}

	public void setDescDesligamento(String descDesligamento) {
		this.descDesligamento = descDesligamento;
	}

	public LocalDateTime getDataInicioAtividade() {
		return dataInicioAtividade;
	}

	public void setDataInicioAtividade(LocalDateTime dtInicioAtividade) {
		this.dataInicioAtividade = dtInicioAtividade;
	}

	public LocalDateTime getDataDesvinculacao() {
		return dataDesvinculacao;
	}

	public void setDataDesvinculacao(LocalDateTime dataDesvinculacao) {
		this.dataDesvinculacao = dataDesvinculacao;
	}

	public LocalDateTime getDataAlteracaoAtividade() {
		return dataAlteracaoAtividade;
	}

	public void setDataAlteracaoAtividade(LocalDateTime dataAlteracaoAtividade) {
		this.dataAlteracaoAtividade = dataAlteracaoAtividade;
	}

	public LocalDateTime getDataCadastroAtividade() {
		return dataCadastroAtividade;
	}

	public void setDataCadastroAtividade(LocalDateTime dataCadastroAtividade) {
		this.dataCadastroAtividade = dataCadastroAtividade;
	}

	public AlunoTO getAluno() {
		return aluno;
	}

	public void setAluno(AlunoTO aluno) {
		this.aluno = aluno;
	}

	public AtividadesTO getAtividade() {
		return atividade;
	}

	public void setAtividade(AtividadesTO atividade) {
		this.atividade = atividade;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}


}