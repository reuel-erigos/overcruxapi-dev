package br.com.crux.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
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
import br.com.crux.infra.dao.SimNaoConverter;


@Entity
@Table(name = "tributos")
public class Tributos implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_tributo")
	@SequenceGenerator(name = "sq_id_tributo", sequenceName = "sq_id_tributo", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_tributo")
	private Long id;

	@Column(name = "cd_tributo")
	private String codigo;

	@Column(name = "ds_tributo")
	private String descricao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_categoria") 
	private CategoriasContabeis categoria;
	
	@Convert(converter = SimNaoConverter.class)
	@Column(name = "st_percentual")
	private Boolean statusPercentual;
	
	@Column(name = "vl_rateio")
	private Double valorRateio;
	
	@Column(name = "id_usuario_apl")
	private Long usuarioAlteracao;

	@Column(name = "id_instituicao")
	private Long idInstituicao;

	@Column(name = "dia_vencimento")
	private Long diaVencimento;

	
	public Tributos() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public CategoriasContabeis getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriasContabeis categoria) {
		this.categoria = categoria;
	}

	public Boolean getStatusPercentual() {
		return statusPercentual;
	}

	public void setStatusPercentual(Boolean statusPercentual) {
		this.statusPercentual = statusPercentual;
	}

	public Double getValorRateio() {
		return valorRateio;
	}

	public void setValorRateio(Double valorRateio) {
		this.valorRateio = valorRateio;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public Long getIdInstituicao() {
		return idInstituicao;
	}

	public void setIdInstituicao(Long idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	public Long getDiaVencimento() {
		return diaVencimento;
	}

	public void setDiaVencimento(Long diaVencimento) {
		this.diaVencimento = diaVencimento;
	}
	
}