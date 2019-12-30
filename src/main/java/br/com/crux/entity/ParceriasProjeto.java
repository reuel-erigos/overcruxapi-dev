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
@Table(name="parcerias_projetos")
public class ParceriasProjeto  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_parceria_projeto")
	@SequenceGenerator(name = "sq_id_parceria_projeto", sequenceName = "sq_id_parceria_projeto", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_parceria_projeto")
	private Long id;

	@Column(name="ds_tipo_parceria")
	private String descricaoTipoParceria;

	@Column(name="dt_fim_parceria")
	private LocalDateTime dataFim;

	@Column(name="dt_inicio_parceria")
	private LocalDateTime dataInicio;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_empresa")
	private Empresa empresa;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_projeto")
	private Projeto projeto;

	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;

	@Column(name="vl_parceria")
	private Double valorParceria;

	@OneToMany(mappedBy="parceriasProjeto")
	private List<MateriaisProjeto> materiaisProjetos;

	public ParceriasProjeto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricaoTipoParceria() {
		return descricaoTipoParceria;
	}

	public void setDescricaoTipoParceria(String descricaoTipoParceria) {
		this.descricaoTipoParceria = descricaoTipoParceria;
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

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
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

	public Double getValorParceria() {
		return valorParceria;
	}

	public void setValorParceria(Double valorParceria) {
		this.valorParceria = valorParceria;
	}

	public List<MateriaisProjeto> getMateriaisProjetos() {
		return materiaisProjetos;
	}

	public void setMateriaisProjetos(List<MateriaisProjeto> materiaisProjetos) {
		this.materiaisProjetos = materiaisProjetos;
	}
	
	

}