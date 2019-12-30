package br.com.crux.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the causas_efeitos database table.
 * 
 */
@Entity
@Table(name="causas_efeitos")
@NamedQuery(name="CausasEfeito.findAll", query="SELECT c FROM CausasEfeito c")
public class CausasEfeito implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_causa_efeito")
	private Long idCausaEfeito;

	@Column(name="ds_causa_efeito")
	private String dsCausaEfeito;

	//bi-directional many-to-one association to Indicadore
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_indicador")
	private Indicadores indicadore;

	//bi-directional many-to-one association to UsuariosSistema
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_usuario_apl")
	private UsuariosSistema usuariosSistema;

	public CausasEfeito() {
	}

	public Long getIdCausaEfeito() {
		return this.idCausaEfeito;
	}

	public void setIdCausaEfeito(Long idCausaEfeito) {
		this.idCausaEfeito = idCausaEfeito;
	}

	public String getDsCausaEfeito() {
		return this.dsCausaEfeito;
	}

	public void setDsCausaEfeito(String dsCausaEfeito) {
		this.dsCausaEfeito = dsCausaEfeito;
	}

	public Indicadores getIndicadore() {
		return this.indicadore;
	}

	public void setIndicadore(Indicadores indicadore) {
		this.indicadore = indicadore;
	}

	public UsuariosSistema getUsuariosSistema() {
		return this.usuariosSistema;
	}

	public void setUsuariosSistema(UsuariosSistema usuariosSistema) {
		this.usuariosSistema = usuariosSistema;
	}

}