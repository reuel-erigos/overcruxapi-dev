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
@Table(name = "tributos_movimentacoes")
public class TributosMovimentacoes implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_tributo_movimentacao")
	@SequenceGenerator(name = "sq_id_tributo_movimentacao", sequenceName = "sq_id_tributo_movimentacao", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_tributo_movimentacao")
	private Long id;

	@Column(name = "id_movimentacao")
	private Long idMovimentacao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tributo") 
	private Tributos tributo;
	
	@Column(name = "vl_tributo") 
	private Double valor;
	
	@Column(name = "id_usuario_apl")
	private Long usuarioAlteracao;

	
	public TributosMovimentacoes() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdMovimentacao() {
		return idMovimentacao;
	}

	public void setIdMovimentacao(Long idMovimentacao) {
		this.idMovimentacao = idMovimentacao;
	}

	public Tributos getTributo() {
		return tributo;
	}

	public void setTributo(Tributos tributo) {
		this.tributo = tributo;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}
	
}