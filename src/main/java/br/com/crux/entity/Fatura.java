package br.com.crux.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

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

	@Column(name = "id_movimentacao")
	private Long idMovimentacao;

	@Column(name = "dt_vencimento")
	private LocalDateTime dataVencimento;
	
	@Column(name = "vl_fatura")
	private Double valor;

	@Column(name = "nr_parcela")
	private Long numeroParcela;

	@Column(name = "nr_codigo_barras")
	private String codigoBarra;
	
	@Column(name = "id_usuario_apl")
	private Long usuarioAlteracao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tributo_movimentacao") 
	private TributosMovimentacoes tributoMovimentacao;
	
	@Column(name = "ds_fatura")
	private String descricao;
	
	
	public Fatura() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getIdMovimentacao() {
		return idMovimentacao;
	}

	public void setIdMovimentacao(Long idMovimentacao) {
		this.idMovimentacao = idMovimentacao;
	}

	public String getCodigoBarra() {
		return codigoBarra;
	}

	public void setCodigoBarra(String codigoBarra) {
		this.codigoBarra = codigoBarra;
	}

	public TributosMovimentacoes getTributoMovimentacao() {
		return tributoMovimentacao;
	}

	public void setTributoMovimentacao(TributosMovimentacoes tributoMovimentacao) {
		this.tributoMovimentacao = tributoMovimentacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	

}