package br.com.crux.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@Table(name="fornecedores")
public class Fornecedor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_fornecedor")
	@SequenceGenerator(name = "sq_id_fornecedor", sequenceName = "sq_id_fornecedor", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_fornecedor")
	private Long id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_pessoa_fisica")
	private PessoaFisica pessoasFisica;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_empresa")
	private Empresa empresa;
	
	@Convert(converter = SimNaoConverter.class)
	@Column(name="st_ativo")
	private Boolean ativo;

	@Column(name="dt_inicio_vinculo")
	private LocalDateTime dataInicioVinculo;

	@Column(name="dt_fim_vinculo")
	private LocalDateTime dataFimVinculo;

	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;
	
	
	public Fornecedor() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PessoaFisica getPessoasFisica() {
		return pessoasFisica;
	}

	public void setPessoasFisica(PessoaFisica pessoasFisica) {
		this.pessoasFisica = pessoasFisica;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}


	public LocalDateTime getDataInicioVinculo() {
		return dataInicioVinculo;
	}

	public void setDataInicioVinculo(LocalDateTime dataInicioVinculo) {
		this.dataInicioVinculo = dataInicioVinculo;
	}

	public LocalDateTime getDataFimVinculo() {
		return dataFimVinculo;
	}

	public void setDataFimVinculo(LocalDateTime dataFimVinculo) {
		this.dataFimVinculo = dataFimVinculo;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	

}