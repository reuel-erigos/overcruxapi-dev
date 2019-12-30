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
@Table(name="vulnerabilidades_familiar")
public class VulnerabilidadesFamiliar  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_vulnerabilidade_fam")
	@SequenceGenerator(name = "sq_id_vulnerabilidade_fam", sequenceName = "sq_id_vulnerabilidade_fam", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_vulnerabilidade_fam")
	private Long id;

	@Column(name="dt_ident_vulnerabilidade")
	private LocalDateTime dataIdentificacao;

	@Column(name="dt_solucao_vulnerabilidade")
	private LocalDateTime dataSolucao;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_familiar")
	private Familiares familiar;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_vulnerabilidade")
	private SituacoesVulnerabilidade situacoesVulnerabilidade;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_solucao")
	private Solucoes solucoes;

	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;

	public VulnerabilidadesFamiliar() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataIdentificacao() {
		return dataIdentificacao;
	}

	public void setDataIdentificacao(LocalDateTime dataIdentificacao) {
		this.dataIdentificacao = dataIdentificacao;
	}

	public LocalDateTime getDataSolucao() {
		return dataSolucao;
	}

	public void setDataSolucao(LocalDateTime dataSolucao) {
		this.dataSolucao = dataSolucao;
	}

	public Familiares getFamiliar() {
		return familiar;
	}

	public void setFamiliar(Familiares familiare) {
		this.familiar = familiare;
	}

	public SituacoesVulnerabilidade getSituacoesVulnerabilidade() {
		return situacoesVulnerabilidade;
	}

	public void setSituacoesVulnerabilidade(SituacoesVulnerabilidade situacoesVulnerabilidade) {
		this.situacoesVulnerabilidade = situacoesVulnerabilidade;
	}

	public Solucoes getSolucoes() {
		return solucoes;
	}

	public void setSolucoes(Solucoes solucoe) {
		this.solucoes = solucoe;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	
}