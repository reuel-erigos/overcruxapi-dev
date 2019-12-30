package br.com.crux.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the estoques database table.
 * 
 */
@Entity
@Table(name="estoques")
public class Estoques {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_estoque")
	private Long idEstoque;

	@Column(name="cs_tipo_mov_estoque")
	private String csTipoMovEstoque;

	@Column(name="ds_mov_estoque")
	private String dsMovEstoque;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dt_atz_estoque")
	private Date dtAtzEstoque;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dt_mov_estoque")
	private Date dtMovEstoque;

	@Column(name="qt_estoque")
	private BigDecimal qtEstoque;

	@Column(name="qt_mov_estoque")
	private BigDecimal qtMovEstoque;

	//bi-directional many-to-one association to Funcionario
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_funcionario")
	private Funcionario funcionario;

	//bi-directional many-to-one association to Produto
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_produto")
	private Material material;

	//bi-directional many-to-one association to Unidade
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_unidade")
	private Unidade unidade;

	//bi-directional many-to-one association to UsuariosSistema
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_usuario_apl")
	private UsuariosSistema usuariosSistema;

	public Estoques() {
	}

	public Long getIdEstoque() {
		return this.idEstoque;
	}

	public void setIdEstoque(Long idEstoque) {
		this.idEstoque = idEstoque;
	}

	public String getCsTipoMovEstoque() {
		return this.csTipoMovEstoque;
	}

	public void setCsTipoMovEstoque(String csTipoMovEstoque) {
		this.csTipoMovEstoque = csTipoMovEstoque;
	}

	public String getDsMovEstoque() {
		return this.dsMovEstoque;
	}

	public void setDsMovEstoque(String dsMovEstoque) {
		this.dsMovEstoque = dsMovEstoque;
	}

	public Date getDtAtzEstoque() {
		return this.dtAtzEstoque;
	}

	public void setDtAtzEstoque(Date dtAtzEstoque) {
		this.dtAtzEstoque = dtAtzEstoque;
	}

	public Date getDtMovEstoque() {
		return this.dtMovEstoque;
	}

	public void setDtMovEstoque(Date dtMovEstoque) {
		this.dtMovEstoque = dtMovEstoque;
	}

	public BigDecimal getQtEstoque() {
		return this.qtEstoque;
	}

	public void setQtEstoque(BigDecimal qtEstoque) {
		this.qtEstoque = qtEstoque;
	}

	public BigDecimal getQtMovEstoque() {
		return this.qtMovEstoque;
	}

	public void setQtMovEstoque(BigDecimal qtMovEstoque) {
		this.qtMovEstoque = qtMovEstoque;
	}

	public Funcionario getFuncionario() {
		return this.funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Material getMaterial() {
		return this.material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public Unidade getUnidade() {
		return this.unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public UsuariosSistema getUsuariosSistema() {
		return this.usuariosSistema;
	}

	public void setUsuariosSistema(UsuariosSistema usuariosSistema) {
		this.usuariosSistema = usuariosSistema;
	}

}