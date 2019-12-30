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
@Table(name="talentos_pf")
public class TalentosPf  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_talento_pf")
	@SequenceGenerator(name = "sq_id_talento_pf", sequenceName = "sq_id_talento_pf", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_talento_pf")
	private Long id;

	@Column(name="ds_resposta_talento")
	private String respostaTalento;

	@Column(name="dt_resposta_talento")
	private LocalDateTime dataRespostaTalento;

	@Column(name="nr_nota_competencia")
	private Double nrNotaCompetencia;

	@Column(name="tx_observacao")
	private String observacao;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_pessoa_fisica")
	private PessoaFisica pessoasFisica;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="questionarios_id_quetionario")
	private Questionario questionario;

	@Column(name="id_usuario_apl")
	private Long usuariosAlteracao;

	public TalentosPf() {
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

	public PessoaFisica getPessoasFisica() {
		return pessoasFisica;
	}

	public void setPessoasFisica(PessoaFisica pessoasFisica) {
		this.pessoasFisica = pessoasFisica;
	}

	public Questionario getQuestionario() {
		return questionario;
	}

	public void setQuestionario(Questionario questionario) {
		this.questionario = questionario;
	}

	public Long getUsuariosAlteracao() {
		return usuariosAlteracao;
	}

	public void setUsuariosAlteracao(Long usuariosAlteracao) {
		this.usuariosAlteracao = usuariosAlteracao;
	}


}