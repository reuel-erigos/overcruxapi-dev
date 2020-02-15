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
 * The persistent class for the pedidos_materiais database table.
 * 
 */
@Entity
@Table(name = "pedidos_materiais")
public class PedidosMateriais implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_pedidos_materiais")
	@SequenceGenerator(name = "sq_id_pedidos_materiais", sequenceName = "sq_id_pedidos_materiais", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_pedido")
	private Long id;

	@Column(name = "ds_pedido")
	private String descricaoPedido;

	@Column(name = "dt_fim_periodo")
	private LocalDateTime dataFimPeriodo;

	@Column(name = "dt_inicio_periodo")
	private LocalDateTime dataInicioPeriodo;

	@Column(name = "dt_pedido")
	private LocalDateTime dataPedido;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_departamento")
	private Departamentos departamento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_funcionario_pedido")
	private Funcionario funcionarioPedido;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_funcionario_rec_ped")
	private Funcionario funcionarioRecPed;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_programa")
	private Programa programa;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_projeto")
	private Projeto projeto;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_unidade")
	private Unidade unidade;

	@Column(name = "id_usuario_apl")
	private Long usuarioAlteracao;


	public PedidosMateriais() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricaoPedido() {
		return descricaoPedido;
	}

	public void setDescricaoPedido(String descricaoPedido) {
		this.descricaoPedido = descricaoPedido;
	}

	public LocalDateTime getDataFimPeriodo() {
		return dataFimPeriodo;
	}

	public void setDataFimPeriodo(LocalDateTime dataFimPeriodo) {
		this.dataFimPeriodo = dataFimPeriodo;
	}

	public LocalDateTime getDataInicioPeriodo() {
		return dataInicioPeriodo;
	}

	public void setDataInicioPeriodo(LocalDateTime dataInicioPeriodo) {
		this.dataInicioPeriodo = dataInicioPeriodo;
	}

	public LocalDateTime getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDateTime dataPedido) {
		this.dataPedido = dataPedido;
	}

	public Departamentos getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamentos departamento) {
		this.departamento = departamento;
	}

	public Funcionario getFuncionarioPedido() {
		return funcionarioPedido;
	}

	public void setFuncionarioPedido(Funcionario funcionarioPedido) {
		this.funcionarioPedido = funcionarioPedido;
	}

	public Funcionario getFuncionarioRecPed() {
		return funcionarioRecPed;
	}

	public void setFuncionarioRecPed(Funcionario funcionarioRecPed) {
		this.funcionarioRecPed = funcionarioRecPed;
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

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}



}