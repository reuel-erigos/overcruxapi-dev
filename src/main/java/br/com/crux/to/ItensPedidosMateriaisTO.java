package br.com.crux.to;

import org.springframework.stereotype.Component;

import br.com.crux.entity.Material;

@Component
public class ItensPedidosMateriaisTO {

	private Long id;
	private Material material;
	private Long usuarioAlteracao;
	private Long qtdPedido;
	private PedidosMateriaisTO pedidosMateriais;
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
	public PedidosMateriaisTO getPedidosMateriais() {
		return pedidosMateriais;
	}
	public void setPedidosMateriai(PedidosMateriaisTO pedidosMateriais) {
		this.pedidosMateriais = pedidosMateriais;
	}
	
	

}
