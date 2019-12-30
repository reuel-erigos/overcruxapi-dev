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

import br.com.crux.infra.constantes.Constantes;


/**
 * The persistent class for the colaboradores_projetos database table.
 * 
 */
@Entity
@Table(name="colaboradores_projetos")
public class ColaboradoresProjeto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_colaborador_projeto")
	@SequenceGenerator(name = "sq_id_colaborador_projeto", sequenceName = "sq_id_colaborador_projeto", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_colaborador_projeto")
	private Long id;

	@Column(name="dt_entrada_projeto")
	private LocalDateTime dataInicio;

	@Column(name="dt_saida_projeto")
	private LocalDateTime dataFim;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_cargo")
	private Cargo cargo;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_funcionario")
	private Funcionario funcionario;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_projeto")
	private Projeto projeto;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_tipo_contratacao")
	private TiposContratacoes tiposContratacoes;

	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;

	public ColaboradoresProjeto() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long idColaboradorProjeto) {
		this.id = idColaboradorProjeto;
	}

	public LocalDateTime getDataInicio() {
		return this.dataInicio;
	}

	public void setDataInicio(LocalDateTime dtEntradaProjeto) {
		this.dataInicio = dtEntradaProjeto;
	}

	public LocalDateTime getDataFim() {
		return this.dataFim;
	}

	public void setDataFim(LocalDateTime dtSaidaProjeto) {
		this.dataFim = dtSaidaProjeto;
	}

	public Cargo getCargo() {
		return this.cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Funcionario getFuncionario() {
		return this.funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Projeto getProjeto() {
		return this.projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public Long getUsuarioAlteracao() {
		return this.usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuariosSistema) {
		this.usuarioAlteracao = usuariosSistema;
	}

	public TiposContratacoes getTiposContratacoes() {
		return tiposContratacoes;
	}

	public void setTiposContratacoes(TiposContratacoes tiposContratacoes) {
		this.tiposContratacoes = tiposContratacoes;
	}

}