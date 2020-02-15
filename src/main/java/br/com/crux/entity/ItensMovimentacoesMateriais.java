package br.com.crux.entity;

import java.io.Serializable;
import javax.persistence.*;

import br.com.crux.entity.Estoques;
import br.com.crux.entity.Funcionario;
import br.com.crux.entity.Material;
import br.com.crux.infra.constantes.Constantes;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * The persistent class for the itens_movimentacoes_materiais database table.
 * 
 */
@Entity
@Table(name = "itens_movimentacoes_materiais")
public class ItensMovimentacoesMateriais implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_item_movimentacao_material")
	@SequenceGenerator(name = "sq_id_item_movimentacao_material", sequenceName = "sq_id_item_movimentacao_material", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_item_movimentacao_material")
	private Long id;

	@Column(name = "dt_envio_material")
	private LocalDateTime dataEnvioMaterial;

	@Column(name = "dt_recebimento_material")
	private LocalDateTime dataRecebimentoMaterial;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_estoque")
	private Estoques estoque;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_funcionario_envio")
	private Funcionario funcionarioEnvio;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_funcionario_recebe")
	private Funcionario funcionarioRecebe;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_material")
	private Material material;

	@Column(name = "id_usuario_apl")
	private Long usuarioAlteracao;

	@Column(name = "nr_tombamento")
	private String numeroTombamento;

	@Column(name = "qtd_material")
	private Long quantidadeMaterial;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_item_movimentacao")
	private ItensMovimentacoes itensMovimentacoes;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_item_pedido")
	private ItensPedidosMateriais itensPedidosMateriais;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_movimentacao_material")
	private MovimentacoesMateriais movimentacoesMateriais;

	public ItensMovimentacoesMateriais() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataEnvioMaterial() {
		return dataEnvioMaterial;
	}

	public void setDataEnvioMaterial(LocalDateTime dataEnvioMaterial) {
		this.dataEnvioMaterial = dataEnvioMaterial;
	}

	public LocalDateTime getDataRecebimentoMaterial() {
		return dataRecebimentoMaterial;
	}

	public void setDataRecebimentoMaterial(LocalDateTime dataRecebimentoMaterial) {
		this.dataRecebimentoMaterial = dataRecebimentoMaterial;
	}

	public Estoques getEstoque() {
		return estoque;
	}

	public void setEstoque(Estoques estoque) {
		this.estoque = estoque;
	}

	public Funcionario getFuncionarioEnvio() {
		return funcionarioEnvio;
	}

	public void setFuncionarioEnvio(Funcionario funcionarioEnvio) {
		this.funcionarioEnvio = funcionarioEnvio;
	}

	public Funcionario getFuncionarioRecebe() {
		return funcionarioRecebe;
	}

	public void setFuncionarioRecebe(Funcionario funcionarioRecebe) {
		this.funcionarioRecebe = funcionarioRecebe;
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

	public String getNumeroTombamento() {
		return numeroTombamento;
	}

	public void setNumeroTombamento(String numeroTombamento) {
		this.numeroTombamento = numeroTombamento;
	}

	public Long getQuantidadeMaterial() {
		return quantidadeMaterial;
	}

	public void setQuantidadeMaterial(Long quantidadeMaterial) {
		this.quantidadeMaterial = quantidadeMaterial;
	}

	public ItensMovimentacoes getItensMovimentacoes() {
		return this.itensMovimentacoes;
	}

	public void setItensMovimentacoes(ItensMovimentacoes itensMovimentacoe) {
		this.itensMovimentacoes = itensMovimentacoe;
	}

	public ItensPedidosMateriais getItensPedidosMateriais() {
		return this.itensPedidosMateriais;
	}

	public void setItensPedidosMateriais(ItensPedidosMateriais itensPedidosMateriai) {
		this.itensPedidosMateriais = itensPedidosMateriai;
	}

	public MovimentacoesMateriais getMovimentacoesMateriais() {
		return this.movimentacoesMateriais;
	}

	public void setMovimentacoesMateriais(MovimentacoesMateriais movimentacoesMateriai) {
		this.movimentacoesMateriais = movimentacoesMateriai;
	}

}