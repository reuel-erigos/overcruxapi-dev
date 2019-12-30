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
@Table(name="indicadores_projetos")
public class IndicadoresProjeto  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_indicador_projeto")
	@SequenceGenerator(name = "sq_id_indicador_projeto", sequenceName = "sq_id_indicador_projeto", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_indicador_projeto")
	private Long id;

	@Column(name="ds_indicador_projeto")
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
	@JoinColumn(name="id_objetivo_projeto")
	private ObjetivosProjeto objetivosProjeto;

	@OneToMany(mappedBy="indicadoresProjeto")
	private List<MetasProjeto> metasProjetos;

	public IndicadoresProjeto() {
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

	public ObjetivosProjeto getObjetivosProjeto() {
		return objetivosProjeto;
	}

	public void setObjetivosProjeto(ObjetivosProjeto objetivosProjeto) {
		this.objetivosProjeto = objetivosProjeto;
	}

	public List<MetasProjeto> getMetasProjetos() {
		return metasProjetos;
	}

	public void setMetasProjetos(List<MetasProjeto> metasProjetos) {
		this.metasProjetos = metasProjetos;
	}

}