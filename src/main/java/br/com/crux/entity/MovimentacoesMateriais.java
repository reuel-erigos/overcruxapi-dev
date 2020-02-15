package br.com.crux.entity;

import java.io.Serializable;

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
 * The persistent class for the movimentacoes_materiais database table.
 * 
 */
@Entity
@Table(name="movimentacoes_materiais")
public class MovimentacoesMateriais implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_movimentacao_material")
	@SequenceGenerator(name = "sq_id_movimentacao_material", sequenceName = "sq_id_movimentacao_material", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_movimentacao_material")
	private Long id;

	@Column(name="ds_movimentacao_material")
	private String descricaoMovimentacaoMaterial;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_departamento")
	private Departamentos departamento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_empresa")
	private Empresa empresa;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_programa")
	private Programa programa;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_projeto")
	private Projeto projeto;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_unidade")
	private Unidade unidade;

	@Column(name = "id_usuario_apl")
	private Long usuarioAlteracao;

	@Column(name="nr_documento")
	private String numeroDocumento;

	@Column(name="st_tipo_movimentacao")
	private String tipoMovimentacao;

	public MovimentacoesMateriais() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricaoMovimentacaoMaterial() {
		return descricaoMovimentacaoMaterial;
	}

	public void setDescricaoMovimentacaoMaterial(String descricaoMovimentacaoMaterial) {
		this.descricaoMovimentacaoMaterial = descricaoMovimentacaoMaterial;
	}

	public Departamentos getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamentos departamento) {
		this.departamento = departamento;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
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

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public void setTipoMovimentacao(String tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}


}