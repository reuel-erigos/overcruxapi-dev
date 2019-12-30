package br.com.crux.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.crux.infra.constantes.Constantes;


@Entity
@Table(name="objetivos_unidades")
public class ObjetivosUnidade  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_objetivo_unidade")
	@SequenceGenerator(name = "sq_id_objetivo_unidade", sequenceName = "sq_id_objetivo_unidade", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_objetivo_unidade")
	private Long id;

	@Column(name="ds_objetivo")
	private String descricaoObjetivo;

	@Column(name="dt_implantacao")
	private LocalDateTime dataImplantacao;

	@Column(name="dt_termino")
	private LocalDateTime dataTermino;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_objetivo")
	private Objetivo objetivo;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_unidade")
	private Unidade unidade;

	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;

	@Column(name="nm_objetivo")
	private String nomeObjetivo;

	@OneToMany(mappedBy="objetivosUnidade")
	private List<IndicadoresUnidade> indicadoresUnidades;

	@OneToMany(mappedBy="objetivosUnidade")
	private List<ObjetivosPrograma> objetivosProgramas;

	public ObjetivosUnidade() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricaoObjetivo() {
		return descricaoObjetivo;
	}

	public void setDescricaoObjetivo(String descricaoObjetivo) {
		this.descricaoObjetivo = descricaoObjetivo;
	}

	public LocalDateTime getDataImplantacao() {
		return dataImplantacao;
	}

	public void setDataImplantacao(LocalDateTime dataImplantacao) {
		this.dataImplantacao = dataImplantacao;
	}

	public LocalDateTime getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(LocalDateTime dataTermino) {
		this.dataTermino = dataTermino;
	}

	public Objetivo getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(Objetivo objetivo) {
		this.objetivo = objetivo;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public String getNomeObjetivo() {
		return nomeObjetivo;
	}

	public void setNomeObjetivo(String nomeObjetivo) {
		this.nomeObjetivo = nomeObjetivo;
	}

	public List<IndicadoresUnidade> getIndicadoresUnidades() {
		return indicadoresUnidades;
	}

	public void setIndicadoresUnidades(List<IndicadoresUnidade> indicadoresUnidades) {
		this.indicadoresUnidades = indicadoresUnidades;
	}

	public List<ObjetivosPrograma> getObjetivosProgramas() {
		return objetivosProgramas;
	}

	public void setObjetivosProgramas(List<ObjetivosPrograma> objetivosProgramas) {
		this.objetivosProgramas = objetivosProgramas;
	}

}