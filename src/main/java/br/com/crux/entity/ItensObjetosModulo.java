package br.com.crux.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the itens_objetos_modulos database table.
 * 
 */
@Entity
@Table(name="itens_objetos_modulos")
@NamedQuery(name="ItensObjetosModulo.findAll", query="SELECT i FROM ItensObjetosModulo i")
public class ItensObjetosModulo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_item_objeto_modulo")
	private Long idItemObjetoModulo;

	@Column(name="ds_codigo")
	private byte[] dsCodigo;

	@Temporal(TemporalType.DATE)
	@Column(name="dt_alteracao")
	private Date dtAlteracao;

	@Column(name="nm_item")
	private String nmItem;

	@Column(name="tx_observacao")
	private String txObservacao;

	//bi-directional many-to-one association to ObjetosModulo
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_objeto_modulo")
	private ObjetosModulo objetosModulo;

	//bi-directional many-to-one association to UsuariosSistema
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_usuario_apl")
	private UsuariosSistema usuariosSistema;

	public ItensObjetosModulo() {
	}

	public Long getIdItemObjetoModulo() {
		return this.idItemObjetoModulo;
	}

	public void setIdItemObjetoModulo(Long idItemObjetoModulo) {
		this.idItemObjetoModulo = idItemObjetoModulo;
	}

	public byte[] getDsCodigo() {
		return this.dsCodigo;
	}

	public void setDsCodigo(byte[] dsCodigo) {
		this.dsCodigo = dsCodigo;
	}

	public Date getDtAlteracao() {
		return this.dtAlteracao;
	}

	public void setDtAlteracao(Date dtAlteracao) {
		this.dtAlteracao = dtAlteracao;
	}

	public String getNmItem() {
		return this.nmItem;
	}

	public void setNmItem(String nmItem) {
		this.nmItem = nmItem;
	}

	public String getTxObservacao() {
		return this.txObservacao;
	}

	public void setTxObservacao(String txObservacao) {
		this.txObservacao = txObservacao;
	}

	public ObjetosModulo getObjetosModulo() {
		return this.objetosModulo;
	}

	public void setObjetosModulo(ObjetosModulo objetosModulo) {
		this.objetosModulo = objetosModulo;
	}

	public UsuariosSistema getUsuariosSistema() {
		return this.usuariosSistema;
	}

	public void setUsuariosSistema(UsuariosSistema usuariosSistema) {
		this.usuariosSistema = usuariosSistema;
	}

}