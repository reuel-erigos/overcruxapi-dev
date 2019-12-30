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
@Table(name="metas_unidades")
public class MetasUnidade  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_meta_unidade")
	@SequenceGenerator(name = "sq_id_meta_unidade", sequenceName = "sq_id_meta_unidade", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_meta_unidade")
	private Long id;

	@Column(name="ds_formula_meta_unidade")
	private String descricaoFormulaMetaUnidade;

	@Column(name="ds_meta_unidade")
	private String descricaoMetaUnidade;

	@Column(name="dt_fim_meta")
	private LocalDateTime dataFim;

	@Column(name="dt_inicio_meta")
	private LocalDateTime dataInicio;

	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;

	@Column(name="nm_meta_unidade")
	private String nomeMetaUnidade;

	@Column(name="qt_meta_unidade")
	private Long qtdMetaUnidade;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_indicador_unidade")
	private IndicadoresUnidade indicadoresUnidade;

	public MetasUnidade() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricaoFormulaMetaUnidade() {
		return descricaoFormulaMetaUnidade;
	}

	public void setDescricaoFormulaMetaUnidade(String descricaoFormulaMetaUnidade) {
		this.descricaoFormulaMetaUnidade = descricaoFormulaMetaUnidade;
	}

	public String getDescricaoMetaUnidade() {
		return descricaoMetaUnidade;
	}

	public void setDescricaoMetaUnidade(String descricaoMetaUnidade) {
		this.descricaoMetaUnidade = descricaoMetaUnidade;
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

	public String getNomeMetaUnidade() {
		return nomeMetaUnidade;
	}

	public void setNomeMetaUnidade(String nomeMetaUnidade) {
		this.nomeMetaUnidade = nomeMetaUnidade;
	}

	public Long getQtdMetaUnidade() {
		return qtdMetaUnidade;
	}

	public void setQtdMetaUnidade(Long qtdMetaUnidade) {
		this.qtdMetaUnidade = qtdMetaUnidade;
	}

	public IndicadoresUnidade getIndicadoresUnidade() {
		return indicadoresUnidade;
	}

	public void setIndicadoresUnidade(IndicadoresUnidade indicadoresUnidade) {
		this.indicadoresUnidade = indicadoresUnidade;
	}

}