package br.com.crux.entity;

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
@Table(name="entidades_sociais")
public class EntidadesSociais  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_entidade_social")
	@SequenceGenerator(name = "sq_id_entidade_social", sequenceName = "sq_id_entidade_social", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_entidade_social")
	private Long id;

	@Column(name="dt_fim_entidade_social")
	private LocalDateTime dataFim;

	@Column(name="dt_vinculo_entidade_social")
	private LocalDateTime dataVinculo;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_empresa")
	private Empresa empresa;

	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;

	public EntidadesSociais() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDateTime dataFim) {
		this.dataFim = dataFim;
	}

	public LocalDateTime getDataVinculo() {
		return dataVinculo;
	}

	public void setDataVinculo(LocalDateTime dataVinculo) {
		this.dataVinculo = dataVinculo;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

}