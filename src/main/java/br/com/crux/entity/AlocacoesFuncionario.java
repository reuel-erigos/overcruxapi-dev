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
@Table(name="alocacoes_funcionarios")
public class AlocacoesFuncionario  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_alocacao_funcionario")
	@SequenceGenerator(name = "sq_id_alocacao_funcionario", sequenceName = "sq_id_alocacao_funcionario", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_alocacao_funcionario")
	private Long id;

	@Column(name="dt_fim_vinculacao")
	private LocalDateTime dataFimVinculacao;

	@Column(name="dt_inicio_vinculacao")
	private LocalDateTime dataInicioVinculacao;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_funcionario")
	private Funcionario funcionario;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_programa")
	private Programa programa;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_projeto")	
	private Projeto projeto;

	@Column(name="vl_alocacao")
	private Double valorAlocacao;
	
	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;

	public AlocacoesFuncionario() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataFimVinculacao() {
		return dataFimVinculacao;
	}

	public void setDataFimVinculacao(LocalDateTime dataFimVinculacao) {
		this.dataFimVinculacao = dataFimVinculacao;
	}

	public LocalDateTime getDataInicioVinculacao() {
		return dataInicioVinculacao;
	}

	public void setDataInicioVinculacao(LocalDateTime dataInicioVinculacao) {
		this.dataInicioVinculacao = dataInicioVinculacao;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public Double getValorAlocacao() {
		return valorAlocacao;
	}

	public void setValorAlocacao(Double valorAlocacao) {
		this.valorAlocacao = valorAlocacao;
	}

	
	
}