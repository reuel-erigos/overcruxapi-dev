package br.com.crux.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.crux.infra.constantes.Constantes;

@Entity
@Table(name = "ocorrencias_alunos")
public class OcorrenciaAluno  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_ocorrencia_aluno")
	@SequenceGenerator(name = "sq_id_ocorrencia_aluno", sequenceName = "sq_id_ocorrencia_aluno", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_ocorrencia_aluno")
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_aluno")
	private Aluno aluno;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_funcionario")
	private Funcionario funcionario;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_tipo_ocorrencia_aluno")
	private TipoOcorrenciaAluno tipoOcorrenciaAluno;
	
	@Column(name="dt_ocorrencia")
	private LocalDateTime dataOcorrencia;
	
	@Column(name = "ds_ocorrencia")
	private String descricao;
	
	@Column(name = "ds_local_ocorrencia")
	private String local;

	@Column(name="dt_ciencia_ocorrencia")
	private LocalDateTime dataCiencia;
	
	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;
	
	public OcorrenciaAluno() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public TipoOcorrenciaAluno getTipoOcorrenciaAluno() {
		return tipoOcorrenciaAluno;
	}

	public void setTipoOcorrenciaAluno(TipoOcorrenciaAluno tipoOcorrenciaAluno) {
		this.tipoOcorrenciaAluno = tipoOcorrenciaAluno;
	}

	public LocalDateTime getDataOcorrencia() {
		return dataOcorrencia;
	}

	public void setDataOcorrencia(LocalDateTime dataOcorrencia) {
		this.dataOcorrencia = dataOcorrencia;
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

	public LocalDateTime getDataCiencia() {
		return dataCiencia;
	}

	public void setDataCiencia(LocalDateTime dataCiencia) {
		this.dataCiencia = dataCiencia;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}
	
	

	
}