package br.com.crux.to;

import java.time.LocalDateTime;
import java.util.List;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.crux.infra.adapter.LocalDateTimeAdapter;

public class TurmasTO  {

	private Long id;
	private String descricao;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataPrevisaoInicio;
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataPrevisaoTermino;
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataInicioTurma;
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataFimTurma;
	
	private Long horaInicio;	
	private Long horaFim;	
	
	private Long numeroMaximoParticipantes;	
	private Long numeroCargaHoraria;	
	
	private String observacao;
	
	private ProgramaTO programa;
	private ProjetoTO projeto;
	private UnidadeTO unidade;
	
	//M = MATUTINO, V = VESPERTINO, N = NOTURNO, O = OUTRO
	private String turno;
	private NiveisTurmasTO niveisTurma;
	private Long usuarioAlteracao;
	
	
	private List<ColaboradoresTurmaTO> colaboradores;
	private List<AtividadesTO> oficinas;
	
	
	public TurmasTO() {
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

	public LocalDateTime getDataInicioTurma() {
		return dataInicioTurma;
	}

	public void setDataInicioTurma(LocalDateTime dataInicioTurma) {
		this.dataInicioTurma = dataInicioTurma;
	}

	public LocalDateTime getDataFimTurma() {
		return dataFimTurma;
	}

	public void setDataFimTurma(LocalDateTime dataFimTurma) {
		this.dataFimTurma = dataFimTurma;
	}

	public Long getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Long horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Long getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(Long horaFim) {
		this.horaFim = horaFim;
	}

	public Long getNumeroMaximoParticipantes() {
		return numeroMaximoParticipantes;
	}

	public void setNumeroMaximoParticipantes(Long numeroMaximoParticipantes) {
		this.numeroMaximoParticipantes = numeroMaximoParticipantes;
	}

	public Long getNumeroCargaHoraria() {
		return numeroCargaHoraria;
	}

	public void setNumeroCargaHoraria(Long numeroCargaHoraria) {
		this.numeroCargaHoraria = numeroCargaHoraria;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public ProgramaTO getPrograma() {
		return programa;
	}

	public void setPrograma(ProgramaTO programa) {
		this.programa = programa;
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

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public NiveisTurmasTO getNiveisTurma() {
		return niveisTurma;
	}

	public void setNiveisTurma(NiveisTurmasTO niveisTurma) {
		this.niveisTurma = niveisTurma;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public List<ColaboradoresTurmaTO> getColaboradores() {
		return colaboradores;
	}

	public void setColaboradores(List<ColaboradoresTurmaTO> colaboradoresTurma) {
		this.colaboradores = colaboradoresTurma;
	}

	public List<AtividadesTO> getOficinas() {
		return oficinas;
	}

	public void setOficinas(List<AtividadesTO> oficinas) {
		this.oficinas = oficinas;
	}
	

}