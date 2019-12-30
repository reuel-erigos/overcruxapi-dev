package br.com.crux.entity;

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
@Table(name="projetos_unidades")
public class ProjetosUnidade  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_projeto_unidade")
	@SequenceGenerator(name = "sq_id_projeto_unidade", sequenceName = "sq_id_projeto_unidade", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_projeto_unidade")
	private Long id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_projeto")	
	private Projeto projeto;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_unidade")	
	private Unidade unidade;

	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;

	
	public ProjetosUnidade() {
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Projeto getProjeto() {
		return projeto;
	}


	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}


	public Unidade getUnidade() {
		return unidade;
	}


	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}


	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}


	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((unidade == null) ? 0 : unidade.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProjetosUnidade other = (ProjetosUnidade) obj;
		if (unidade == null) {
			if (other.unidade != null)
				return false;
		} else if (!unidade.equals(other.unidade))
			return false;
		return true;
	}
	
	

}