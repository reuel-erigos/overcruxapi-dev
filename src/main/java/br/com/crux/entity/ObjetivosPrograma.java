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
@Table(name="objetivos_programas")
public class ObjetivosPrograma  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_objetivo_programa")
	@SequenceGenerator(name = "sq_id_objetivo_programa", sequenceName = "sq_id_objetivo_programa", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_objetivo_programa")
	private Long id;

	@Column(name="ds_objetivo")
	private String descricaoObjetivo;

	@Column(name="dt_implantacao")
	private LocalDateTime dataImplantacao;

	@Column(name="dt_termino")
	private LocalDateTime dataTermino;


	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_programa")
	private Programa programa;

	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;

	@Column(name="nm_objetivo")
	private String nmObjetivo;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_objetivo_unidade")
	private ObjetivosUnidade objetivosUnidade;
	
	@OneToMany(mappedBy="objetivosPrograma")
	private List<IndicadoresPrograma> indicadoresProgramas;

	@OneToMany(mappedBy="objetivosPrograma")
	private List<ObjetivosProjeto> objetivosProjetos;

	public ObjetivosPrograma() {
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

	public String getNmObjetivo() {
		return nmObjetivo;
	}

	public void setNmObjetivo(String nmObjetivo) {
		this.nmObjetivo = nmObjetivo;
	}

	public ObjetivosUnidade getObjetivosUnidade() {
		return objetivosUnidade;
	}

	public void setObjetivosUnidade(ObjetivosUnidade objetivosUnidade) {
		this.objetivosUnidade = objetivosUnidade;
	}

	public List<IndicadoresPrograma> getIndicadoresProgramas() {
		return indicadoresProgramas;
	}

	public void setIndicadoresProgramas(List<IndicadoresPrograma> indicadoresProgramas) {
		this.indicadoresProgramas = indicadoresProgramas;
	}

	public List<ObjetivosProjeto> getObjetivosProjetos() {
		return objetivosProjetos;
	}

	public void setObjetivosProjetos(List<ObjetivosProjeto> objetivosProjetos) {
		this.objetivosProjetos = objetivosProjetos;
	}


}