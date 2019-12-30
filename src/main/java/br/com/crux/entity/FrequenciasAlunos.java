package br.com.crux.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
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
import br.com.crux.infra.dao.SimNaoConverter;


@Entity
@Table(name="frequencias_alunos")
public class FrequenciasAlunos {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_frequencia_aluno")
	@SequenceGenerator(name = "sq_id_frequencia_aluno", sequenceName = "sq_id_frequencia_aluno", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_frequencia_aluno")
	private Long id;

	@Column(name="ds_justificativa")
	private String justificativa;

	@Column(name="dt_frequencia")
	private LocalDate dataFrequencia;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_atividade_aluno")
	private AtividadesAluno atividadesAluno;

	@Convert(converter = SimNaoConverter.class)
	@Column(name = "st_frequencia")
	private Boolean frequencia;
	
	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;

	
	public FrequenciasAlunos() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}

	public LocalDate getDataFrequencia() {
		return dataFrequencia;
	}

	public void setDataFrequencia(LocalDate dataFrequencia) {
		this.dataFrequencia = dataFrequencia;
	}

	public AtividadesAluno getAtividadesAluno() {
		return atividadesAluno;
	}

	public void setAtividadesAluno(AtividadesAluno atividadesAluno) {
		this.atividadesAluno = atividadesAluno;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public Boolean getFrequencia() {
		return frequencia;
	}

	public void setFrequencia(Boolean frequencia) {
		this.frequencia = frequencia;
	}
	
	

}