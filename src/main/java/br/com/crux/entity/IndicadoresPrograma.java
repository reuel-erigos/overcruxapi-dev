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
@Table(name="indicadores_programas")
public class IndicadoresPrograma  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_indicador_programa")
	@SequenceGenerator(name = "sq_id_indicador_programa", sequenceName = "sq_id_indicador_programa", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_indicador_programa")
	private Long id;

	@Column(name="ds_indicador_programa")
	private String descricao;

	@Column(name="dt_fim_indicador")
	private LocalDateTime dataFim;

	@Column(name="dt_inicio_indicador")
	private LocalDateTime dataInicio;

	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;

	@Column(name="nm_indicador")
	private String nome;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_objetivo_programa")
	private ObjetivosPrograma objetivosPrograma;

	@OneToMany(mappedBy="indicadoresPrograma")
	private List<MetasPrograma> metasProgramas;

	public IndicadoresPrograma() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ObjetivosPrograma getObjetivosPrograma() {
		return objetivosPrograma;
	}

	public void setObjetivosPrograma(ObjetivosPrograma objetivosPrograma) {
		this.objetivosPrograma = objetivosPrograma;
	}

	public List<MetasPrograma> getMetasProgramas() {
		return metasProgramas;
	}

	public void setMetasProgramas(List<MetasPrograma> metasProgramas) {
		this.metasProgramas = metasProgramas;
	}

	
}