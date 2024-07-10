package br.com.crux.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
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
import br.com.crux.infra.dao.SimNaoConverter;


@Entity
@Table(name="grupos_atividades")
public class GrupoAcoes implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_grupo_acao")
	@SequenceGenerator(name = "sq_id_grupo_acao", sequenceName = "sq_id_grupo_acao", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_grupo_acao", unique=true, nullable=false, precision=10)
	private Long id;

	@Column(name="nr_grupo")
	private String numeroGrupo;	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_atividade")
	private Oficinas atividade;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario_analise")
	private UsuariosSistema usuarioAnalise;
	
	@Column(name="dt_analise")
	private LocalDateTime dataAnalise;
	
	// (A = APROVADA; R = REPROVADA
	@Column(name="st_analise")
	private String statusAnalise;
	
	@Convert(converter = SimNaoConverter.class)
	@Column(name = "st_envio_analise")
	private Boolean statusEnvioAnalise;
	
	@Column(name="ds_avaliacao_grupo")
	private String descricao;
	
	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;
	
	@OneToMany(mappedBy = "idGrupoAcao")
	private List<Acoes> acoes;

	public GrupoAcoes() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroGrupo() {
		return numeroGrupo;
	}

	public void setNumeroGrupo(String numeroGrupo) {
		this.numeroGrupo = numeroGrupo;
	}

	public Oficinas getAtividade() {
		return atividade;
	}

	public void setAtividade(Oficinas atividade) {
		this.atividade = atividade;
	}

	public UsuariosSistema getUsuarioAnalise() {
		return usuarioAnalise;
	}

	public void setUsuarioAnalise(UsuariosSistema usuarioAnalise) {
		this.usuarioAnalise = usuarioAnalise;
	}

	public LocalDateTime getDataAnalise() {
		return dataAnalise;
	}

	public void setDataAnalise(LocalDateTime dataAnalise) {
		this.dataAnalise = dataAnalise;
	}

	public String getStatusAnalise() {
		return statusAnalise;
	}

	public void setStatusAnalise(String statusAnaliuse) {
		this.statusAnalise = statusAnaliuse;
	}

	public Boolean getStatusEnvioAnalise() {
		return statusEnvioAnalise;
	}

	public void setStatusEnvioAnalise(Boolean statusEnvioAnalise) {
		this.statusEnvioAnalise = statusEnvioAnalise;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public List<Acoes> getAcoes() {
		return acoes;
	}

	public void setAcoes(List<Acoes> acoes) {
		this.acoes = acoes;
	}

}