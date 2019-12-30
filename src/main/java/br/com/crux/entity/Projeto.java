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
 * The persistent class for the projetos database table.
 * 
 */
@Entity
@Table(name="projetos")
public class Projeto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_projeto")
	@SequenceGenerator(name = "sq_id_projeto", sequenceName = "sq_id_projeto", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_projeto")
	private Long id;

	@Column(name="nm_projeto")
	private String nome;
	
	@Column(name="ds_projeto")
	private String descricao;

	@Column(name="dt_fim_projeto")
	private LocalDateTime dataFim;

	@Column(name="dt_inicio_projeto")
	private LocalDateTime dataInicio;

	@Column(name="dt_prev_projeto")
	private LocalDateTime dataPrevisaoInicio;

	@Column(name="dt_prev_termino")
	private LocalDateTime dataPrevisaoTermino;


	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="iniciativas_id_iniciativa")
	private Iniciativa iniciativa;

	//bi-directional many-to-one association to Programa
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="programas_id_programa")
	private Programa programa;
	
	@Column(name="ds_publico_alvo")
	private String publicoAlvo;
	
	@Column(name="ds_justificativa")
	private String justificativa;
	
	@Column(name="ds_objetivo_geral")
	private String objetivoGeral;
	
	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;
	
	

	public Projeto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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

	public LocalDateTime getDataPrevisaoInicio() {
		return dataPrevisaoInicio;
	}

	public void setDataPrevisaoInicio(LocalDateTime dataPrevisaoInicio) {
		this.dataPrevisaoInicio = dataPrevisaoInicio;
	}

	public LocalDateTime getDataPrevisaoTermino() {
		return dataPrevisaoTermino;
	}

	public void setDataPrevisaoTermino(LocalDateTime dataPrevisaoTermino) {
		this.dataPrevisaoTermino = dataPrevisaoTermino;
	}

	public Iniciativa getIniciativa() {
		return iniciativa;
	}

	public void setIniciativa(Iniciativa iniciativa) {
		this.iniciativa = iniciativa;
	}

	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public String getPublicoAlvo() {
		return publicoAlvo;
	}

	public void setPublicoAlvo(String publicoAlvo) {
		this.publicoAlvo = publicoAlvo;
	}

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}

	public String getObjetivoGeral() {
		return objetivoGeral;
	}

	public void setObjetivoGeral(String objetivoGeral) {
		this.objetivoGeral = objetivoGeral;
	}

	
	
}