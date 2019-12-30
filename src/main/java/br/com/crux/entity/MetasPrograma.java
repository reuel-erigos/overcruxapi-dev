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
@Table(name="metas_programas")
public class MetasPrograma  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_meta_programa")
	@SequenceGenerator(name = "sq_id_meta_programa", sequenceName = "sq_id_meta_programa", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_meta_programa")
	private Long id;

	@Column(name="ds_formula_meta_programa")
	private String descricaoFormulaMetaPrograma;

	@Column(name="ds_meta_programa")
	private String descricaoMetaPrograma;

	@Column(name="dt_fim_meta")
	private LocalDateTime dataFim;

	@Column(name="dt_inicio_meta")
	private LocalDateTime dataInicio;

	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;

	@Column(name="nm_meta_programa")
	private String nomemMetaPrograma;

	@Column(name="qt_meta_programa")
	private Long qtdMetaPrograma;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_indicador_programa")
	private IndicadoresPrograma indicadoresPrograma;

	public MetasPrograma() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricaoFormulaMetaPrograma() {
		return descricaoFormulaMetaPrograma;
	}

	public void setDescricaoFormulaMetaPrograma(String descricaoFormulaMetaPrograma) {
		this.descricaoFormulaMetaPrograma = descricaoFormulaMetaPrograma;
	}

	public String getDescricaoMetaPrograma() {
		return descricaoMetaPrograma;
	}

	public void setDescricaoMetaPrograma(String descricaoMetaPrograma) {
		this.descricaoMetaPrograma = descricaoMetaPrograma;
	}

	public LocalDateTime getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDateTime dataFim) {
		this.dataFim = dataFim;
	}

	public LocalDateTime getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public String getNomemMetaPrograma() {
		return nomemMetaPrograma;
	}

	public void setNomemMetaPrograma(String nomemMetaPrograma) {
		this.nomemMetaPrograma = nomemMetaPrograma;
	}

	public Long getQtdMetaPrograma() {
		return qtdMetaPrograma;
	}

	public void setQtdMetaPrograma(Long qtdMetaPrograma) {
		this.qtdMetaPrograma = qtdMetaPrograma;
	}

	public IndicadoresPrograma getIndicadoresPrograma() {
		return indicadoresPrograma;
	}

	public void setIndicadoresPrograma(IndicadoresPrograma indicadoresPrograma) {
		this.indicadoresPrograma = indicadoresPrograma;
	}


}