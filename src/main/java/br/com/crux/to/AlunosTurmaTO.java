package br.com.crux.to;

import java.time.LocalDateTime;
import java.util.List;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.crux.infra.adapter.LocalDateTimeAdapter;

public class AlunosTurmaTO {

	private Long id;
	private AlunoTO aluno;
	private TurmasTO turma;

	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataCadastro;
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataInicio;
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataDesvinculacao;
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataAlteracao;

	private String descricaoDesligamento;
	private Long usuarioAlteracao;
	
	private List<AtividadesAlunoTO> oficinas;

	public AlunosTurmaTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AlunoTO getAluno() {
		return aluno;
	}

	public void setAluno(AlunoTO aluno) {
		this.aluno = aluno;
	}

	public TurmasTO getTurma() {
		return turma;
	}

	public void setTurma(TurmasTO turma) {
		this.turma = turma;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public LocalDateTime getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDateTime getDataDesvinculacao() {
		return dataDesvinculacao;
	}

	public void setDataDesvinculacao(LocalDateTime dataDesvinculacao) {
		this.dataDesvinculacao = dataDesvinculacao;
	}

	public LocalDateTime getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(LocalDateTime dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public String getDescricaoDesligamento() {
		return descricaoDesligamento;
	}

	public void setDescricaoDesligamento(String descricaoDesligamento) {
		this.descricaoDesligamento = descricaoDesligamento;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public List<AtividadesAlunoTO> getOficinas() {
		return oficinas;
	}

	public void setOficinas(List<AtividadesAlunoTO> oficinas) {
		this.oficinas = oficinas;
	}

	
}