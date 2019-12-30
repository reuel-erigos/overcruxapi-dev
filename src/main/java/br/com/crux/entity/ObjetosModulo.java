package br.com.crux.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the objetos_modulos database table.
 * 
 */
@Entity
@Table(name="objetos_modulos")
@NamedQuery(name="ObjetosModulo.findAll", query="SELECT o FROM ObjetosModulo o")
public class ObjetosModulo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_objeto_modulo")
	private Long idObjetoModulo;

	@Column(name="nm_modulo")
	private String nmModulo;

	@Column(name="nm_sistema")
	private String nmSistema;

	@Column(name="tx_chamada")
	private String txChamada;

	@Column(name="tx_observacao")
	private String txObservacao;

	//bi-directional many-to-one association to ItensObjetosModulo
	@OneToMany(mappedBy="objetosModulo")
	private List<ItensObjetosModulo> itensObjetosModulos;

	//bi-directional many-to-one association to UsuariosSistema
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_usuario_apl")
	private UsuariosSistema usuariosSistema;

	public ObjetosModulo() {
	}

	public Long getIdObjetoModulo() {
		return this.idObjetoModulo;
	}

	public void setIdObjetoModulo(Long idObjetoModulo) {
		this.idObjetoModulo = idObjetoModulo;
	}

	public String getNmModulo() {
		return this.nmModulo;
	}

	public void setNmModulo(String nmModulo) {
		this.nmModulo = nmModulo;
	}

	public String getNmSistema() {
		return this.nmSistema;
	}

	public void setNmSistema(String nmSistema) {
		this.nmSistema = nmSistema;
	}

	public String getTxChamada() {
		return this.txChamada;
	}

	public void setTxChamada(String txChamada) {
		this.txChamada = txChamada;
	}

	public String getTxObservacao() {
		return this.txObservacao;
	}

	public void setTxObservacao(String txObservacao) {
		this.txObservacao = txObservacao;
	}

	public List<ItensObjetosModulo> getItensObjetosModulos() {
		return this.itensObjetosModulos;
	}

	public void setItensObjetosModulos(List<ItensObjetosModulo> itensObjetosModulos) {
		this.itensObjetosModulos = itensObjetosModulos;
	}

	public ItensObjetosModulo addItensObjetosModulo(ItensObjetosModulo itensObjetosModulo) {
		getItensObjetosModulos().add(itensObjetosModulo);
		itensObjetosModulo.setObjetosModulo(this);

		return itensObjetosModulo;
	}

	public ItensObjetosModulo removeItensObjetosModulo(ItensObjetosModulo itensObjetosModulo) {
		getItensObjetosModulos().remove(itensObjetosModulo);
		itensObjetosModulo.setObjetosModulo(null);

		return itensObjetosModulo;
	}

	public UsuariosSistema getUsuariosSistema() {
		return this.usuariosSistema;
	}

	public void setUsuariosSistema(UsuariosSistema usuariosSistema) {
		this.usuariosSistema = usuariosSistema;
	}

}