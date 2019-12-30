package br.com.crux.to;

import java.time.LocalDateTime;
import java.util.List;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.crux.infra.adapter.LocalDateTimeAdapter;

public class ProgramaTO {

	private Long id;
	private String nome;
	private String descricao;

	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class) private LocalDateTime dataInicio;
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class) private LocalDateTime dataFim;

	private Long idCoordenador;
	private Long faixaEtariaFim;
	private Long faixaEtariaInicio;

	private ObjetivoTO objetivo;

	private List<UnidadeTO> unidades;

	private List<ColaboradoresProgramaTO> colaboradoresPrograma;

	private List<ParceriasProgramaTO> parceriasPrograma;

	private List<ComposicaoRhProgramaTO> composicaoRhPrograma;

	private List<MateriaisProgramaTO> materiaisPrograma;
	
	private String publicoAlvo;

	private String justificativa;

	private String objetivoGeral;
	

	private Long usuarioAlteracao;

	public ProgramaTO() {
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDateTime getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDateTime getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDateTime dataFim) {
		this.dataFim = dataFim;
	}

	public Long getIdCoordenador() {
		return idCoordenador;
	}

	public void setIdCoordenador(Long idCoordenador) {
		this.idCoordenador = idCoordenador;
	}

	public Long getFaixaEtariaFim() {
		return faixaEtariaFim;
	}

	public void setFaixaEtariaFim(Long faixaEtariaFim) {
		this.faixaEtariaFim = faixaEtariaFim;
	}

	public Long getFaixaEtariaInicio() {
		return faixaEtariaInicio;
	}

	public void setFaixaEtariaInicio(Long faixaEtariaInicio) {
		this.faixaEtariaInicio = faixaEtariaInicio;
	}

	public ObjetivoTO getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(ObjetivoTO objetivo) {
		this.objetivo = objetivo;
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

	public void setUnidades(List<UnidadeTO> unidades) {
		this.unidades = unidades;
	}

	public List<ColaboradoresProgramaTO> getColaboradoresPrograma() {
		return colaboradoresPrograma;
	}

	public void setColaboradoresPrograma(List<ColaboradoresProgramaTO> colaboradoresPrograma) {
		this.colaboradoresPrograma = colaboradoresPrograma;
	}

	public List<ParceriasProgramaTO> getParceriasPrograma() {
		return parceriasPrograma;
	}

	public void setParceriasPrograma(List<ParceriasProgramaTO> parceriasPrograma) {
		this.parceriasPrograma = parceriasPrograma;
	}

	public List<ComposicaoRhProgramaTO> getComposicaoRhPrograma() {
		return composicaoRhPrograma;
	}

	public void setComposicaoRhPrograma(List<ComposicaoRhProgramaTO> composicaoRhPrograma) {
		this.composicaoRhPrograma = composicaoRhPrograma;
	}

	public List<MateriaisProgramaTO> getMateriaisPrograma() {
		return materiaisPrograma;
	}

	public void setMateriaisPrograma(List<MateriaisProgramaTO> materiaisPrograma) {
		this.materiaisPrograma = materiaisPrograma;
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
	
	

}