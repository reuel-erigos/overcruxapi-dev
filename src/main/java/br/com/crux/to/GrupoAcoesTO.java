package br.com.crux.to;

import java.time.LocalDateTime;
import java.util.List;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.crux.infra.adapter.LocalDateTimeAdapter;

public class GrupoAcoesTO {

	private Long id;
	private String numeroGrupo;	
	private OficinasTO atividade;
	private FuncionarioTO funcionarioAnalise;		
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataAnalise;
	
	// (A = APROVADA; R = REPROVADA
	private String statusAnaliuse;
	private Boolean statusEnvioAnalise;
	private String descricao;
	private Long usuarioAlteracao;
	private List<AcaoTO> acoes;
	
	
	
	public GrupoAcoesTO() {
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

	public OficinasTO getAtividade() {
		return atividade;
	}

	public void setAtividade(OficinasTO atividade) {
		this.atividade = atividade;
	}

	public FuncionarioTO getFuncionarioAnalise() {
		return funcionarioAnalise;
	}

	public void setFuncionarioAnalise(FuncionarioTO funcionarioAnalise) {
		this.funcionarioAnalise = funcionarioAnalise;
	}

	public LocalDateTime getDataAnalise() {
		return dataAnalise;
	}

	public void setDataAnalise(LocalDateTime dataAnalise) {
		this.dataAnalise = dataAnalise;
	}

	public String getStatusAnaliuse() {
		return statusAnaliuse;
	}

	public void setStatusAnaliuse(String statusAnaliuse) {
		this.statusAnaliuse = statusAnaliuse;
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

	public List<AcaoTO> getAcoes() {
		return acoes;
	}

	public void setAcoes(List<AcaoTO> acoes) {
		this.acoes = acoes;
	}

}
