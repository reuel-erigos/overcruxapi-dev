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


@Entity
@Table(name="programas")
public class Programa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_programa")
	@SequenceGenerator(name = "sq_id_programa", sequenceName = "sq_id_programa", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_programa")
	private Long id;

	@Column(name="nm_programa")
	private String nome;

	@Column(name="ds_programa")
	private String descricao;

	@Column(name="dt_implantacao")
	private LocalDateTime dataInicio;

	@Column(name="dt_termino")
	private LocalDateTime dataFim;

	@Column(name="id_coordenador")
	private Long idCoordenador;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_objetivo")
	private Objetivo objetivo;


	@Column(name="nr_faixa_etaria_fim")
	private Long faixaEtariaFim;

	@Column(name="nr_faixa_etaria_ini")
	private Long faixaEtariaInicio;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="iniciativas_id_iniciativa")
	private Iniciativa iniciativa;
	
	@Column(name="ds_publico_alvo")
	private String publicoAlvo;
	
	@Column(name="ds_justificativa")
	private String justificativa;
	
	@Column(name="ds_objetivo_geral")
	private String objetivoGeral;

	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;

	
	
	public Programa() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long idPrograma) {
		this.id = idPrograma;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String dsPrograma) {
		this.descricao = dsPrograma;
	}

	public LocalDateTime getDataInicio() {
		return this.dataInicio;
	}

	public void setDataInicio(LocalDateTime dtImplantacao) {
		this.dataInicio = dtImplantacao;
	}

	public LocalDateTime getDataFim() {
		return this.dataFim;
	}

	public void setDataFim(LocalDateTime dtTermino) {
		this.dataFim = dtTermino;
	}

	public Long getIdCoordenador() {
		return this.idCoordenador;
	}

	public void setIdCoordenador(Long idCoordenador) {
		this.idCoordenador = idCoordenador;
	}

	public Objetivo getObjetivo() {
		return this.objetivo;
	}

	public void setObjetivo(Objetivo objetivo) {
		this.objetivo = objetivo;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nmPrograma) {
		this.nome = nmPrograma;
	}

	public Long getFaixaEtariaFim() {
		return this.faixaEtariaFim;
	}

	public void setFaixaEtariaFim(Long nrFaixaEtariaFim) {
		this.faixaEtariaFim = nrFaixaEtariaFim;
	}

	public Long getFaixaEtariaInicio() {
		return this.faixaEtariaInicio;
	}

	public void setFaixaEtariaInicio(Long nrFaixaEtariaIni) {
		this.faixaEtariaInicio = nrFaixaEtariaIni;
	}

	public Iniciativa getIniciativa() {
		return this.iniciativa;
	}

	public void setIniciativa(Iniciativa iniciativa) {
		this.iniciativa = iniciativa;
	}

	public Long getUsuarioAlteracao() {
		return this.usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuariosSistema) {
		this.usuarioAlteracao = usuariosSistema;
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