package br.com.crux.to;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.crux.infra.adapter.LocalDateTimeAdapter;

public class OficinasTO {

	private Long id;
	private String descricao;
	private String descricaoLocalExecucao;

	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class) 
	private LocalDateTime dataFim;

	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class) 
	private LocalDateTime dataInicio;

	private String horaFim;
	private String horaInicio;
	private Long cargaHoraria;
	private Long maximoParticipantes;
	private String localExecucao;

	private Boolean segunda;
	private Boolean terca;
	private Boolean quarta;
	private Boolean quinta;
	private Boolean sexta;
	private Boolean sabado;
	private Boolean domingo;

	private Double valorCustoAtividade;

	private ProjetoTO projeto;
	private ProgramaTO programa;
	private UnidadeTO unidade;

	private Long idTurma;
	
	private Long tiposAtividades;
	
	private List<ColaboradoresAtividadeTO> colaboradoresAtividade;
	private List<MateriaisAtividadeTO> materiaisAtividade;
	
	private Long usuarioAlteracao;

	public OficinasTO() {
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

	public String getDescricaoLocalExecucao() {
		return descricaoLocalExecucao;
	}

	public void setDescricaoLocalExecucao(String descricaoLocalExecucao) {
		this.descricaoLocalExecucao = descricaoLocalExecucao;
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

	public String getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(String horaFim) {
		this.horaFim = horaFim;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Long getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(Long cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public Long getMaximoParticipantes() {
		return maximoParticipantes;
	}

	public void setMaximoParticipantes(Long maximoParticipantes) {
		this.maximoParticipantes = maximoParticipantes;
	}

	public Boolean getDomingo() {
		return domingo;
	}

	public void setDomingo(Boolean domingo) {
		this.domingo = domingo;
	}

	public String getLocalExecucao() {
		return localExecucao;
	}

	public void setLocalExecucao(String localExecucao) {
		this.localExecucao = localExecucao;
	}

	public Boolean getQuarta() {
		return quarta;
	}

	public void setQuarta(Boolean quarta) {
		this.quarta = quarta;
	}

	public Boolean getQuinta() {
		return quinta;
	}

	public void setQuinta(Boolean quinta) {
		this.quinta = quinta;
	}

	public Boolean getSabado() {
		return sabado;
	}

	public void setSabado(Boolean sabado) {
		this.sabado = sabado;
	}

	public Boolean getSegunda() {
		return segunda;
	}

	public void setSegunda(Boolean segunda) {
		this.segunda = segunda;
	}

	public Boolean getSexta() {
		return sexta;
	}

	public void setSexta(Boolean sexta) {
		this.sexta = sexta;
	}

	public Boolean getTerca() {
		return terca;
	}

	public void setTerca(Boolean terca) {
		this.terca = terca;
	}

	public Double getValorCustoAtividade() {
		return valorCustoAtividade;
	}

	public void setValorCustoAtividade(Double valorCustoAtividade) {
		this.valorCustoAtividade = valorCustoAtividade;
	}

	public ProjetoTO getProjeto() {
		return projeto;
	}

	public void setProjeto(ProjetoTO projeto) {
		this.projeto = projeto;
	}

	public UnidadeTO getUnidade() {
		return unidade;
	}

	public void setUnidade(UnidadeTO unidade) {
		this.unidade = unidade;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public List<ColaboradoresAtividadeTO> getColaboradoresAtividade() {
		if (Objects.isNull(colaboradoresAtividade)) {
			return new ArrayList<ColaboradoresAtividadeTO>();
		}
		return colaboradoresAtividade;
	}

	public void setColaboradoresAtividade(List<ColaboradoresAtividadeTO> colaboradoresAtividade) {
		this.colaboradoresAtividade = colaboradoresAtividade;
	}

	public ProgramaTO getPrograma() {
		return programa;
	}

	public void setPrograma(ProgramaTO programa) {
		this.programa = programa;
	}

	public Long getIdTurma() {
		return idTurma;
	}

	public void setIdTurma(Long idTurma) {
		this.idTurma = idTurma;
	}

	public List<MateriaisAtividadeTO> getMateriaisAtividade() {
		return materiaisAtividade;
	}

	public void setMateriaisAtividade(List<MateriaisAtividadeTO> materiaisAtividade) {
		this.materiaisAtividade = materiaisAtividade;
	}

	public Long getTiposAtividades() {
		return tiposAtividades;
	}

	public void setTiposAtividades(Long idTipoAtividade) {
		this.tiposAtividades = idTipoAtividade;
	}

}
