package br.com.crux.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the servicos database table.
 * 
 */
@Entity
@Table(name="servicos")
@NamedQuery(name="Servico.findAll", query="SELECT s FROM Servico s")
public class Servico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_servico")
	private Long idServico;

	@Column(name="nm_servico")
	private String nmServico;

	//bi-directional many-to-one association to ItensFatura
	@OneToMany(mappedBy="servico")
	private List<ItensFatura> itensFaturas;

	//bi-directional many-to-one association to Pedido
	@OneToMany(mappedBy="servico")
	private List<Pedido> pedidos;

	//bi-directional many-to-one association to UsuariosSistema
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_usuario_apl")
	private UsuariosSistema usuariosSistema;

	public Servico() {
	}

	public Long getIdServico() {
		return this.idServico;
	}

	public void setIdServico(Long idServico) {
		this.idServico = idServico;
	}

	public String getNmServico() {
		return this.nmServico;
	}

	public void setNmServico(String nmServico) {
		this.nmServico = nmServico;
	}

	public List<ItensFatura> getItensFaturas() {
		return this.itensFaturas;
	}

	public void setItensFaturas(List<ItensFatura> itensFaturas) {
		this.itensFaturas = itensFaturas;
	}

	public ItensFatura addItensFatura(ItensFatura itensFatura) {
		getItensFaturas().add(itensFatura);
		itensFatura.setServico(this);

		return itensFatura;
	}

	public ItensFatura removeItensFatura(ItensFatura itensFatura) {
		getItensFaturas().remove(itensFatura);
		itensFatura.setServico(null);

		return itensFatura;
	}

	public List<Pedido> getPedidos() {
		return this.pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public Pedido addPedido(Pedido pedido) {
		getPedidos().add(pedido);
		pedido.setServico(this);

		return pedido;
	}

	public Pedido removePedido(Pedido pedido) {
		getPedidos().remove(pedido);
		pedido.setServico(null);

		return pedido;
	}

	public UsuariosSistema getUsuariosSistema() {
		return this.usuariosSistema;
	}

	public void setUsuariosSistema(UsuariosSistema usuariosSistema) {
		this.usuariosSistema = usuariosSistema;
	}

}