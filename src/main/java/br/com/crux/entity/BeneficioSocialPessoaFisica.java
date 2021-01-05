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
@Table(name="beneficios_sociais_pf")
public class BeneficioSocialPessoaFisica  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_beneficio_social_pf")
	@SequenceGenerator(name = "sq_id_beneficio_social_pf", sequenceName = "sq_id_beneficio_social_pf", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_beneficio_social_pf")
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_beneficio_social",nullable = false)
	private BeneficioSocial beneficioSocial;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_pessoa_fisica",nullable = false)
	private PessoaFisica pessoaFisica;

	@Column(name="dt_inicio_beneficio")
	private LocalDateTime dataInicio;

	@Column(name="dt_fim_beneficio")
	private LocalDateTime dataFim;
	
	@Column(name="vl_beneficio")
	private Double valor;

	@Column(name="id_instituicao")
	private Long idInstituicao;

	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;

	public BeneficioSocialPessoaFisica() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDateTime getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDateTime dataFim) {
		this.dataFim = dataFim;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
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

	public BeneficioSocial getBeneficioSocial() {
		return beneficioSocial;
	}

	public void setBeneficioSocial(BeneficioSocial beneficioSocial) {
		this.beneficioSocial = beneficioSocial;
	}

	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

	

}