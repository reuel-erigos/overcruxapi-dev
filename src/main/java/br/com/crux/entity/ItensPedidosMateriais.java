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
 * The persistent class for the itens_pedidos_materiais database table.
 * 
 */
@Entity
@Table(name = "itens_pedidos_materiais")
public class ItensPedidosMateriais implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_itens_pedidos_materiais")
	@SequenceGenerator(name = "sq_id_itens_pedidos_materiais", sequenceName = "sq_id_itens_pedidos_materiais", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_item_pedido")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_material")
	private Material material;

	@Column(name = "id_usuario_apl")
	private Long usuarioAlteracao;

	@Column(name = "qtd_pedido")
	private Long qtdPedido;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_pedido")
	private PedidosMateriais pedidosMateriais;

	public ItensPedidosMateriais() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public Long getQtdPedido() {
		return qtdPedido;
	}

	public void setQtdPedido(Long qtdPedido) {
		this.qtdPedido = qtdPedido;
	}

	public PedidosMateriais getPedidosMateriais() {
		return this.pedidosMateriais;
	}

	public void setPedidosMateriais(PedidosMateriais pedidosMateriais) {
		this.pedidosMateriais = pedidosMateriais;
	}

}