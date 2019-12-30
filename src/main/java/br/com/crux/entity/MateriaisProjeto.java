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
@Table(name="materiais_projetos")
public class MateriaisProjeto  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_material_parceria_projeto")
	@SequenceGenerator(name = "id_material_parceria_projeto", sequenceName = "id_material_parceria_projeto", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_material_projeto")
	private Long id;

	@Column(name="dt_fim_alocacao")
	private LocalDateTime dataFim;

	@Column(name="dt_inicio_alocacao")
	private LocalDateTime dataInicio;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_material")
	private Material material;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_projeto")
	private Projeto projeto;

	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;

	@Column(name="vl_material")
	private Double valorMaterial;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_parceria_projeto")
	private ParceriasProjeto parceriasProjeto;

	public MateriaisProjeto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
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

	public Double getValorMaterial() {
		return valorMaterial;
	}

	public void setValorMaterial(Double valorMaterial) {
		this.valorMaterial = valorMaterial;
	}

	public ParceriasProjeto getParceriasProjeto() {
		return parceriasProjeto;
	}

	public void setParceriasProjeto(ParceriasProjeto parceriasProjeto) {
		this.parceriasProjeto = parceriasProjeto;
	}


}