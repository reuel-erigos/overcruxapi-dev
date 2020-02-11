package br.com.crux.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.crux.infra.constantes.Constantes;

/**
 * The persistent class for the faturas database table.
 * 
 */
@Entity
@Table(name = "faturas_movimentacoes")
public class Fatura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_fatura")
	@SequenceGenerator(name = "sq_id_fatura", sequenceName = "sq_id_fatura", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_fatura")
	private Long id;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_movimentacao")
	private List<Movimentacoes> movimentos;

	@Column(name = "dt_vencimento")
	private LocalDateTime dataVencimento;

	@Column(name = "vl_fatura")
	private Double valor;

	@Column(name = "nr_parcela")
	private Long numeroParcela;

	@Column(name = "id_usuario_apl")
	private Long usuarioAlteracao;

	public Fatura() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Movimentacoes> getMovimentos() {
		return movimentos;
	}

	public void setMovimentos(List<Movimentacoes> movimentos) {
		this.movimentos = movimentos;
	}

	public LocalDateTime getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDateTime dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Long getNumeroParcela() {
		return numeroParcela;
	}

	public void setNumeroParcela(Long numeroParcela) {
		this.numeroParcela = numeroParcela;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

}