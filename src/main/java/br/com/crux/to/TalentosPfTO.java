package br.com.crux.to;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.crux.infra.adapter.LocalDateTimeAdapter;

public class TalentosPfTO {

	private Long id;
	private String respostaTalento;

	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataRespostaTalento;

	private Double nrNotaCompetencia;
	private String observacao;
	private FuncionarioTO funcionario;
	private QuestionarioTO questionario;
	private Long usuarioAlteracao;

	public TalentosPfTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRespostaTalento() {
		return respostaTalento;
	}

	public void setRespostaTalento(String respostaTalento) {
		this.respostaTalento = respostaTalento;
	}

	public LocalDateTime getDataRespostaTalento() {
		return dataRespostaTalento;
	}

	public void setDataRespostaTalento(LocalDateTime dataRespostaTalento) {
		this.dataRespostaTalento = dataRespostaTalento;
	}

	public Double getNrNotaCompetencia() {
		return nrNotaCompetencia;
	}

	public void setNrNotaCompetencia(Double nrNotaCompetencia) {
		this.nrNotaCompetencia = nrNotaCompetencia;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}


	public QuestionarioTO getQuestionario() {
		return questionario;
	}

	public void setQuestionario(QuestionarioTO questionario) {
		this.questionario = questionario;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuariosAlteracao) {
		this.usuarioAlteracao = usuariosAlteracao;
	}

	public FuncionarioTO getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(FuncionarioTO funcionario) {
		this.funcionario = funcionario;
	}

}