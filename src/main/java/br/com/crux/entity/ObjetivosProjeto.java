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
@Table(name="objetivos_projetos")
public class ObjetivosProjeto  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_objetivo_projeto")
	@SequenceGenerator(name = "sq_id_objetivo_projeto", sequenceName = "sq_id_objetivo_projeto", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_objetivo_projeto")
	private Long id;

	@Column(name="ds_objetivo")
	private String descricaoObjetivo;

	@Column(name="dt_implantacao")
	private LocalDateTime dataImplantacao;

	@Column(name="dt_termino")
	private LocalDateTime dataTermino;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_projeto")
	private Projeto projeto;

	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;

	@Column(name="nm_objetivo")
	private String nomeObjetivo;

	@OneToMany(mappedBy="objetivosProjeto")
	private List<IndicadoresProjeto> indicadoresProjetos;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_objetivo_programa")
	private ObjetivosPrograma objetivosPrograma;

	public ObjetivosProjeto() {
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

	public String getNomeObjetivo() {
		return nomeObjetivo;
	}

	public void setNomeObjetivo(String nomeObjetivo) {
		this.nomeObjetivo = nomeObjetivo;
	}

	public List<IndicadoresProjeto> getIndicadoresProjetos() {
		return indicadoresProjetos;
	}

	public void setIndicadoresProjetos(List<IndicadoresProjeto> indicadoresProjetos) {
		this.indicadoresProjetos = indicadoresProjetos;
	}

	public ObjetivosPrograma getObjetivosPrograma() {
		return objetivosPrograma;
	}

	public void setObjetivosPrograma(ObjetivosPrograma objetivosPrograma) {
		this.objetivosPrograma = objetivosPrograma;
	}


}