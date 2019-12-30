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
@Table(name="parcerias_programas")
public class ParceriasPrograma  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_parceria_programa")
	@SequenceGenerator(name = "sq_id_parceria_programa", sequenceName = "sq_id_parceria_programa", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_parceria_programa")
	private Long id;

	@Column(name="ds_tipo_parceria")
	private String dsTipoParceria;

	@Column(name="dt_fim_parceria")
	private LocalDateTime dtFimParceria;

	@Column(name="dt_inicio_parceria")
	private LocalDateTime dtInicioParceria;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_empresa")
	private Empresa empresa;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_programa")
	private Programa programa;

	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;

	@Column(name="vl_parceria")
	private Double valorParceria;

	@OneToMany(mappedBy="parceriasPrograma")
	private List<MateriaisPrograma> materiaisProgramas;

	public ParceriasPrograma() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDsTipoParceria() {
		return dsTipoParceria;
	}

	public void setDsTipoParceria(String dsTipoParceria) {
		this.dsTipoParceria = dsTipoParceria;
	}

	public LocalDateTime getDtFimParceria() {
		return dtFimParceria;
	}

	public void setDtFimParceria(LocalDateTime dtFimParceria) {
		this.dtFimParceria = dtFimParceria;
	}

	public LocalDateTime getDtInicioParceria() {
		return dtInicioParceria;
	}

	public void setDtInicioParceria(LocalDateTime dtInicioParceria) {
		this.dtInicioParceria = dtInicioParceria;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
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

	public Double getValorParceria() {
		return valorParceria;
	}

	public void setValorParceria(Double valorParceria) {
		this.valorParceria = valorParceria;
	}

	public List<MateriaisPrograma> getMateriaisProgramas() {
		return materiaisProgramas;
	}

	public void setMateriaisProgramas(List<MateriaisPrograma> materiaisProgramas) {
		this.materiaisProgramas = materiaisProgramas;
	}

	

}