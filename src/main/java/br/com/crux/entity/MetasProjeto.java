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
@Table(name="metas_projetos")
public class MetasProjeto  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_meta_projeto")
	@SequenceGenerator(name = "sq_id_meta_projeto", sequenceName = "sq_id_meta_projeto", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_meta_projeto")
	private Long id;

	@Column(name="ds_formula_meta_projeto")
	private String descricaoFormulaMetaProjeto;

	@Column(name="ds_meta_projeto")
	private String descricaoMetaProjeto;

	@Column(name="dt_fim_meta")
	private LocalDateTime dataFim;

	@Column(name="dt_inicio_meta")
	private LocalDateTime dataInicio;

	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;

	@Column(name="nm_meta_projeto")
	private String nomeMetaProjeto;

	@Column(name="qt_meta_projeto")
	private Long qtdMetaProjeto;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_indicador_projeto")
	private IndicadoresProjeto indicadoresProjeto;

	public MetasProjeto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricaoFormulaMetaProjeto() {
		return descricaoFormulaMetaProjeto;
	}

	public void setDescricaoFormulaMetaProjeto(String descricaoFormulaMetaProjeto) {
		this.descricaoFormulaMetaProjeto = descricaoFormulaMetaProjeto;
	}

	public String getDescricaoMetaProjeto() {
		return descricaoMetaProjeto;
	}

	public void setDescricaoMetaProjeto(String descricaoMetaProjeto) {
		this.descricaoMetaProjeto = descricaoMetaProjeto;
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

	public String getNomeMetaProjeto() {
		return nomeMetaProjeto;
	}

	public void setNomeMetaProjeto(String nomeMetaProjeto) {
		this.nomeMetaProjeto = nomeMetaProjeto;
	}

	public Long getQtdMetaProjeto() {
		return qtdMetaProjeto;
	}

	public void setQtdMetaProjeto(Long qtdMetaProjeto) {
		this.qtdMetaProjeto = qtdMetaProjeto;
	}

	public IndicadoresProjeto getIndicadoresProjeto() {
		return indicadoresProjeto;
	}

	public void setIndicadoresProjeto(IndicadoresProjeto indicadoresProjeto) {
		this.indicadoresProjeto = indicadoresProjeto;
	}


}