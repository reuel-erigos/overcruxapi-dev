package br.com.crux.entity.view;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import br.com.crux.infra.dao.SimNaoConverter;

@Entity
@Immutable
@Table(name = "vw_planos_contas")
public class PlanosContas {

	@Id
	@Column(name = "id_categoria")
	private Long id;
	
	@Column(name = "plano_conta")
	private String planoConta;
	
	@Column(name = "cd_categoria_contabil")
	private String codigoCategoriaContabil;
	
	@Column(name = "id_instituicao")
	private Long idInstituicao;
	
	@Convert(converter = SimNaoConverter.class)
	@Column(name="st_categoria_sintetica")
	private Boolean sintetica;
	
	public PlanosContas() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long idCategoria) {
		this.id = idCategoria;
	}

	public String getPlanoConta() {
		return planoConta;
	}

	public void setPlanoConta(String planoConta) {
		this.planoConta = planoConta;
	}

	public String getCodigoCategoriaContabil() {
		return codigoCategoriaContabil;
	}

	public void setCodigoCategoriaContabil(String categoriaContabil) {
		this.codigoCategoriaContabil = categoriaContabil;
	}

	public Long getIdInstituicao() {
		return idInstituicao;
	}

	public void setIdInstituicao(Long idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	public Boolean getSintetica() {
		return sintetica;
	}

	public void setSintetica(Boolean sintetica) {
		this.sintetica = sintetica;
	}


	
}
