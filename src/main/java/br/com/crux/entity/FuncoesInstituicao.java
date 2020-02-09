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
@Table(name="funcoes_instituicao")
public class FuncoesInstituicao  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_funcao_instituicao")
	@SequenceGenerator(name = "sq_id_funcao_instituicao", sequenceName = "sq_id_funcao_instituicao", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_funcao_instituicao")
	private Long id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_funcao")
	private Funcoes funcoes;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_instituicao")
	private Instituicao instituicao;

	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_funcionario")
	private Funcionario funcionario;
	
	@Column(name="dt_inicio_funcao")
	private LocalDateTime dataInicioFuncao;

	@Column(name="dt_fim_funcao")
	private LocalDateTime dataFimFuncao;

	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;

	public FuncoesInstituicao() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Funcoes getFuncoes() {
		return funcoes;
	}

	public void setFuncoes(Funcoes funcoes) {
		this.funcoes = funcoes;
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public LocalDateTime getDataInicioFuncao() {
		return dataInicioFuncao;
	}

	public void setDataInicioFuncao(LocalDateTime dataInicioFuncao) {
		this.dataInicioFuncao = dataInicioFuncao;
	}

	public LocalDateTime getDataFimFuncao() {
		return dataFimFuncao;
	}

	public void setDataFimFuncao(LocalDateTime dataFimFuncao) {
		this.dataFimFuncao = dataFimFuncao;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

		
	
}