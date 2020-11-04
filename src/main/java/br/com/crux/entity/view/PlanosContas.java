package br.com.crux.entity.view;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "vw_planos_contas")
public class PlanosContas {

	@Id
	@Column(name = "id_categoria")
	private Long idCategoria;
	
	@Column(name = "plano_conta")
	private String planoConta;
	
	@Column(name = "cd_categoria_contabil")
	private String codigoCategoriaContabil;
	
	@Column(name = "id_instituicao")
	private Long idInstituicao;
	
	public PlanosContas() {
	}

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
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


	
}
