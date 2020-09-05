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


@Entity
@Table(name="doadores")
public class Doadores implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_doador")
	@SequenceGenerator(name = "sq_id_doador", sequenceName = "sq_id_doador", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_doador")
	private Long id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_pessoa_fisica")
	private PessoaFisica pessoasFisica;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_empresa")
	private Empresa empresa;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_tipo_doador")
	private TiposDoadores tipoDoador;

	@Column(name="dt_inicio_vinculo_instituicao")
	private LocalDateTime dataInicioVinculo;

	@Column(name="dt_fim_vinculo_instituicao")
	private LocalDateTime dataFimVinculo;

	@Column(name="id_instituicao")
	private Long idInstituicao;

	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;
	
	
	public Doadores() {
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

	public TiposDoadores getTipoDoador() {
		return tipoDoador;
	}

	public void setTipoDoador(TiposDoadores tipoDoador) {
		this.tipoDoador = tipoDoador;
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

	public Long getIdInstituicao() {
		return idInstituicao;
	}

	public void setIdInstituicao(Long idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

}