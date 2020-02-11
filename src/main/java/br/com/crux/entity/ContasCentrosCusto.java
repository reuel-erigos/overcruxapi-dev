package br.com.crux.entity;

import java.io.Serializable;
import javax.persistence.*;

import br.com.crux.entity.ContasBancaria;
import br.com.crux.entity.Programa;
import br.com.crux.entity.Projeto;
import br.com.crux.infra.constantes.Constantes;


/**
 * The persistent class for the contas_centros_custo database table.
 * 
 */
@Entity
@Table(name="contas_centros_custo")
public class ContasCentrosCusto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_conta_centro_custo")
	@SequenceGenerator(name = "sq_id_conta_centro_custo", sequenceName = "sq_id_conta_centro_custo", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_conta_centro_custo")
	private Long id;

	@Column(name = "id_usuario_apl")
	private Long usuarioAlteracao;

	@ManyToOne
	@JoinColumn(name="id_conta_bancaria")
	private ContasBancaria contasBancaria;

	@ManyToOne
	@JoinColumn(name="id_programa")
	private Programa programa;

	@ManyToOne
	@JoinColumn(name="id_projeto")
	private Projeto projeto;

	public ContasCentrosCusto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public ContasBancaria getContasBancaria() {
		return contasBancaria;
	}

	public void setContasBancaria(ContasBancaria contasBancaria) {
		this.contasBancaria = contasBancaria;
	}

	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	
	
}