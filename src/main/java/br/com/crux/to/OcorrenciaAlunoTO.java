package br.com.crux.to;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.crux.infra.adapter.LocalDateTimeAdapter;

public class OcorrenciaAlunoTO {

	private Long id;
	
	private AlunoTO aluno;
	private FuncionarioTO funcionario;
	private TipoOcorrenciaAlunoTO tipoOcorrenciaAluno;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataOcorrencia;
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataCiencia;
	
	private String descricao;
	private String local;
	private Long usuarioAlteracao;

	public OcorrenciaAlunoTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AlunoTO getAluno() {
		return aluno;
	}

	public void setAluno(AlunoTO aluno) {
		this.aluno = aluno;
	}

	public FuncionarioTO getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(FuncionarioTO funcionario) {
		this.funcionario = funcionario;
	}

	public TipoOcorrenciaAlunoTO getTipoOcorrenciaAluno() {
		return tipoOcorrenciaAluno;
	}

	public void setTipoOcorrenciaAluno(TipoOcorrenciaAlunoTO tipoOcorrenciaAluno) {
		this.tipoOcorrenciaAluno = tipoOcorrenciaAluno;
	}

	public LocalDateTime getDataOcorrencia() {
		return dataOcorrencia;
	}

	public void setDataOcorrencia(LocalDateTime dataOcorrencia) {
		this.dataOcorrencia = dataOcorrencia;
	}

	public LocalDateTime getDataCiencia() {
		return dataCiencia;
	}

	public void setDataCiencia(LocalDateTime dataCiencia) {
		this.dataCiencia = dataCiencia;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}
	
	



}