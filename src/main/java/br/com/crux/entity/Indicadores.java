package br.com.crux.entity;

import java.io.Serializable;
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


/**
 * The persistent class for the indicadores database table.
 * 
 */
@Entity
@Table(name="indicadores")
public class Indicadores implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_indicador")
	@SequenceGenerator(name = "sq_id_indicador", sequenceName = "sq_id_indicador", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_indicador")
	private Long idIndicador;

	@Column(name="dt_fim_indicador")
	private LocalDateTime dataFim;

	@Column(name="dt_inicio_indicador")
	private LocalDateTime dataInicio;

	@Column(name="nm_indicador")
	private String nome;

	//bi-directional many-to-one association to Objetivo
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="objetivos_id_objetivo")
	private Objetivo objetivo;

	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;


	public Indicadores() {
	}

	public Long getIdIndicador() {
		return this.idIndicador;
	}

	public void setIdIndicador(Long idIndicador) {
		this.idIndicador = idIndicador;
	}

	public LocalDateTime getDataFim() {
		return this.dataFim;
	}

	public void setDataFim(LocalDateTime dtFimIndicador) {
		this.dataFim = dtFimIndicador;
	}

	public LocalDateTime getDataInicio() {
		return this.dataInicio;
	}

	public void setDataInicio(LocalDateTime dtInicioIndicador) {
		this.dataInicio = dtInicioIndicador;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nmIndicador) {
		this.nome = nmIndicador;
	}

	public Objetivo getObjetivo() {
		return this.objetivo;
	}

	public void setObjetivo(Objetivo objetivo) {
		this.objetivo = objetivo;
	}

	public Long getUsuarioAlteracao() {
		return this.usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuariosSistema) {
		this.usuarioAlteracao = usuariosSistema;
	}


}