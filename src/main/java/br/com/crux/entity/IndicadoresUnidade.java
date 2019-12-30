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
@Table(name="indicadores_unidades")
public class IndicadoresUnidade  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_indicador_unidade")
	@SequenceGenerator(name = "sq_id_indicador_unidade", sequenceName = "sq_id_indicador_unidade", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_indicador_unidade")
	private Long id;

	@Column(name="ds_indicador_unidade")
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
	@JoinColumn(name="id_objetivo_unidade")
	private ObjetivosUnidade objetivosUnidade;

	@OneToMany(mappedBy="indicadoresUnidade")
	private List<MetasUnidade> metasUnidades;

	public IndicadoresUnidade() {
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

	public ObjetivosUnidade getObjetivosUnidade() {
		return objetivosUnidade;
	}

	public void setObjetivosUnidade(ObjetivosUnidade objetivosUnidade) {
		this.objetivosUnidade = objetivosUnidade;
	}

	public List<MetasUnidade> getMetasUnidades() {
		return metasUnidades;
	}

	public void setMetasUnidades(List<MetasUnidade> metasUnidades) {
		this.metasUnidades = metasUnidades;
	}

	
}