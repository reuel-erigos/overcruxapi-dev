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


@Entity
@Table(name = "tributos_itens_movimentacoes	")
public class TributosItensMovimentacoes implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_tributo_item_movimentacao")
	@SequenceGenerator(name = "sq_id_tributo_item_movimentacao", sequenceName = "sq_id_tributo_item_movimentacao", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_tributo_item_movimentacao")
	private Long id;

	@Column(name = "id_item_movimentacao")
	private Long idItemMovimentacao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tributo") 
	private Tributos tributo;
	
	@Column(name = "id_usuario_apl")
	private Long usuarioAlteracao;

	
	public TributosItensMovimentacoes() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdItemMovimentacao() {
		return idItemMovimentacao;
	}

	public void setIdItemMovimentacao(Long idItemMovimentacao) {
		this.idItemMovimentacao = idItemMovimentacao;
	}

	public Tributos getTributo() {
		return tributo;
	}

	public void setTributo(Tributos tributo) {
		this.tributo = tributo;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}
	
}