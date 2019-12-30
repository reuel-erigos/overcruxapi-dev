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
@Table(name="prestacoes_contas")
public class PrestacoesConta  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_prestacao_conta")
	@SequenceGenerator(name = "sq_id_prestacao_conta", sequenceName = "sq_id_prestacao_conta", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_prestacao_conta")
	private Long id;

	@Column(name="ds_analise")
	private String descricaoAnalise;

	@Column(name="ds_ocorrencias")
	private String descricaoOcorrencias;

	@Column(name="dt_fim")
	private LocalDateTime dataFim;

	@Column(name="dt_inicio")
	private LocalDateTime dataInicio;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_programa")
	private Programa programa;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_projeto")
	private Projeto projeto;

	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;

	public PrestacoesConta() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricaoAnalise() {
		return descricaoAnalise;
	}

	public void setDescricaoAnalise(String descricaoAnalise) {
		this.descricaoAnalise = descricaoAnalise;
	}

	public String getDescricaoOcorrencias() {
		return descricaoOcorrencias;
	}

	public void setDescricaoOcorrencias(String descricaoOcorrencias) {
		this.descricaoOcorrencias = descricaoOcorrencias;
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

	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}


}