package br.com.crux.to;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.crux.infra.adapter.LocalDateTimeAdapter;

public class AcaoTO {

	private Long id;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class) 
	private LocalDateTime dataFim;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class) 
	private LocalDateTime dataInicio;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class) 
	private LocalDateTime dataPrevisaoFim;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class) 
	private LocalDateTime dataPrevisaoInicio;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class) 
	private LocalDateTime dataAprovaAcao;	
	
	private String descricao;	
	private String descricaoObjetivoAcao;
	private String descricaoMetodologiaAcao;
	private String descricaoAvaliacaoAcao;
	private String descricaoOcorrenciaAcao;
	
	private FuncionarioTO funcionarioPlanejamentoAcao;	
	private FuncionarioTO funcionarioAprovaAcao;
	private FuncionarioTO funcionarioExecutaAcao;	
	
	private List<MateriaisAcoesTO> materiaisAcao = new ArrayList<MateriaisAcoesTO>();
	
	private Long usuarioAlteracao;
	
	private OficinasTO oficina;
	private Long idInstituicao;
	private String localExecucao;

	
	public AcaoTO() {
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

	public LocalDateTime getDataPrevisaoFim() {
		return dataPrevisaoFim;
	}

	public void setDataPrevisaoFim(LocalDateTime dataPrevisaoFim) {
		this.dataPrevisaoFim = dataPrevisaoFim;
	}

	public LocalDateTime getDataPrevisaoInicio() {
		return dataPrevisaoInicio;
	}

	public void setDataPrevisaoInicio(LocalDateTime dataPrevisaoInicio) {
		this.dataPrevisaoInicio = dataPrevisaoInicio;
	}

	public OficinasTO getOficina() {
		return oficina;
	}

	public void setOficina(OficinasTO atividade) {
		this.oficina = atividade;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricaoObjetivoAcao() {
		return descricaoObjetivoAcao;
	}

	public void setDescricaoObjetivoAcao(String descricaoObjetivoAcao) {
		this.descricaoObjetivoAcao = descricaoObjetivoAcao;
	}

	public String getDescricaoMetodologiaAcao() {
		return descricaoMetodologiaAcao;
	}

	public void setDescricaoMetodologiaAcao(String descricaoMetodologiaAcao) {
		this.descricaoMetodologiaAcao = descricaoMetodologiaAcao;
	}

	public String getDescricaoAvaliacaoAcao() {
		return descricaoAvaliacaoAcao;
	}

	public void setDescricaoAvaliacaoAcao(String descricaoAvaliacaoAcao) {
		this.descricaoAvaliacaoAcao = descricaoAvaliacaoAcao;
	}

	public String getDescricaoOcorrenciaAcao() {
		return descricaoOcorrenciaAcao;
	}

	public void setDescricaoOcorrenciaAcao(String descricaoOcorrenciaAcao) {
		this.descricaoOcorrenciaAcao = descricaoOcorrenciaAcao;
	}

	public FuncionarioTO getFuncionarioPlanejamentoAcao() {
		return funcionarioPlanejamentoAcao;
	}

	public void setFuncionarioPlanejamentoAcao(FuncionarioTO funcionarioPlanejamentoAcao) {
		this.funcionarioPlanejamentoAcao = funcionarioPlanejamentoAcao;
	}

	public FuncionarioTO getFuncionarioAprovaAcao() {
		return funcionarioAprovaAcao;
	}

	public void setFuncionarioAprovaAcao(FuncionarioTO funcionarioAprovaAcao) {
		this.funcionarioAprovaAcao = funcionarioAprovaAcao;
	}

	public FuncionarioTO getFuncionarioExecutaAcao() {
		return funcionarioExecutaAcao;
	}

	public void setFuncionarioExecutaAcao(FuncionarioTO funcionarioExecutaAcao) {
		this.funcionarioExecutaAcao = funcionarioExecutaAcao;
	}

	public LocalDateTime getDataAprovaAcao() {
		return dataAprovaAcao;
	}

	public void setDataAprovaAcao(LocalDateTime dataAprovaAcao) {
		this.dataAprovaAcao = dataAprovaAcao;
	}

	public List<MateriaisAcoesTO> getMateriaisAcao() {
		return materiaisAcao;
	}

	public void setMateriaisAcao(List<MateriaisAcoesTO> materiaisAcao) {
		this.materiaisAcao = materiaisAcao;
	}

	public Long getIdInstituicao() {
		return idInstituicao;
	}

	public void setIdInstituicao(Long idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	public String getLocalExecucao() {
		return localExecucao;
	}

	public void setLocalExecucao(String localExecucao) {
		this.localExecucao = localExecucao;
	}
	
	

}
