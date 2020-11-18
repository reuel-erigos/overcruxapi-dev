package br.com.crux.to;

import java.time.LocalDateTime;
import java.util.List;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.crux.infra.adapter.LocalDateTimeAdapter;

public class ProjetoTO {

	private Long id;
	private String nome;
	private String descricao;

	private Boolean restricao;

	private Long idCoordenador;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataFim;
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataInicio;
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataPrevisaoInicio;
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataPrevisaoTermino;

	private ProgramaTO programa;
	private List<UnidadeTO> unidades;
	private List<ColaboradoresProjetoTO> colaboradoresProjeto;
	private List<ParceriasProjetoTO> parceriasProjeto;
	private List<ComposicaoRhProjetoTO> composicaoRhProjeto;
	private String publicoAlvo;
	private String justificativa;
	private String objetivoGeral;
	private Long usuarioAlteracao;
	private Long faixaEtariaInicial;
	private Long faixaEtariaFinal;
	private String objetivoEspecifico;
	
	public ProjetoTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getRestricao() {
		return restricao;
	}

	public void setRestricao(Boolean restricao) {
		this.restricao = restricao;
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

	public LocalDateTime getDataPrevisaoInicio() {
		return dataPrevisaoInicio;
	}

	public void setDataPrevisaoInicio(LocalDateTime dataPrevisaoInicio) {
		this.dataPrevisaoInicio = dataPrevisaoInicio;
	}

	public LocalDateTime getDataPrevisaoTermino() {
		return dataPrevisaoTermino;
	}

	public void setDataPrevisaoTermino(LocalDateTime dataPrevisaoTermino) {
		this.dataPrevisaoTermino = dataPrevisaoTermino;
	}

	public ProgramaTO getPrograma() {
		return programa;
	}

	public void setPrograma(ProgramaTO programa) {
		this.programa = programa;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public List<UnidadeTO> getUnidades() {
		return unidades;
	}

	public void setUnidades(List<UnidadeTO> unidadeTO) {
		this.unidades = unidadeTO;
	}

	public List<ColaboradoresProjetoTO> getColaboradoresProjeto() {
		return colaboradoresProjeto;
	}

	public void setColaboradoresProjeto(List<ColaboradoresProjetoTO> colaboradoresProjeto) {
		this.colaboradoresProjeto = colaboradoresProjeto;
	}

	public List<ParceriasProjetoTO> getParceriasProjeto() {
		return parceriasProjeto;
	}

	public void setParceriasProjeto(List<ParceriasProjetoTO> parceriasProjeto) {
		this.parceriasProjeto = parceriasProjeto;
	}

	public List<ComposicaoRhProjetoTO> getComposicaoRhProjeto() {
		return composicaoRhProjeto;
	}

	public void setComposicaoRhProjeto(List<ComposicaoRhProjetoTO> composicaoRhProjeto) {
		this.composicaoRhProjeto = composicaoRhProjeto;
	}

	public String getPublicoAlvo() {
		return publicoAlvo;
	}

	public void setPublicoAlvo(String publicoAlvo) {
		this.publicoAlvo = publicoAlvo;
	}

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}

	public String getObjetivoGeral() {
		return objetivoGeral;
	}

	public void setObjetivoGeral(String objetivoGeral) {
		this.objetivoGeral = objetivoGeral;
	}
	
	public Long getIdCoordenador() {
		return idCoordenador;
	}

	public void setIdCoordenador(Long idCoordenador) {
		this.idCoordenador = idCoordenador;
	}


	public Long getFaixaEtariaInicial() {
		return faixaEtariaInicial;
	}

	public void setFaixaEtariaInicial(Long faixaEtariaInicial) {
		this.faixaEtariaInicial = faixaEtariaInicial;
	}

	public Long getFaixaEtariaFinal() {
		return faixaEtariaFinal;
	}

	public void setFaixaEtariaFinal(Long faixaEtariaFinal) {
		this.faixaEtariaFinal = faixaEtariaFinal;
	}

	public String getObjetivoEspecifico() {
		return objetivoEspecifico;
	}

	public void setObjetivoEspecifico(String objetivoEspecifico) {
		this.objetivoEspecifico = objetivoEspecifico;
	}

}
