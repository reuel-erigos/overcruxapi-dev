package br.com.crux.entity;

import java.io.Serializable;
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

import br.com.crux.entity.Funcionario;
import br.com.crux.entity.Funcoes;
import br.com.crux.entity.Instituicao;
import br.com.crux.infra.constantes.Constantes;


/**
 * The persistent class for the funcoes_instituicao database table.
 * 
 */
@Entity
@Table(name="funcoes_instituicao")
public class FuncoesInstituicao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_funcao_instituicao")
	@SequenceGenerator(name = "sq_id_funcao_instituicao", sequenceName = "sq_id_funcao_instituicao", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_funcao_instituicao")
	private Long id;

	@Column(name="dt_fim_funcao")
	private LocalDateTime dataFim;
	
	@Column(name="dt_inicio_funcao")
	private LocalDateTime dataInicio;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_funcionario")
	private Funcionario funcionario;

	@Column(name = "id_usuario_apl")
	private Long usuarioAlteracao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_funcao")
	private Funcoes funcao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_instituicao")
	private Instituicao instituicao;

	public FuncoesInstituicao() {
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

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public Funcoes getFuncao() {
		return funcao;
	}

	public void setFuncao(Funcoes funcao) {
		this.funcao = funcao;
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	

}