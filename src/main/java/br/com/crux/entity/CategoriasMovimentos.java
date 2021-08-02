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
@Table(name = "categorias_movimentacoes")
public class CategoriasMovimentos implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_categoria_movimentacao")
	@SequenceGenerator(name = "sq_id_categoria_movimentacao", sequenceName = "sq_id_categoria_movimentacao", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_categoria_movimentacao")
	private Long id;

	@Column(name = "id_movimentacao")
	private Long idMovimento;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_categoria_origem")
	private CategoriasContabeis categoriaOrigem;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_categoria_destino")
	private CategoriasContabeis categoriaDestino;
	
	@Column(name = "vl_categoria_movimentacao")
	private Double valor;

	@Column(name = "ds_categoria_movimentacao")
	private String descricao;

	@Column(name = "id_usuario_apl")
	private Long usuarioAlteracao;
	
	@Column(name = "dt_movimentacao") 
	private LocalDateTime dataMovimentacao;
	
	@ManyToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name = "id_programa") 
	private Programa programa;
	
	@ManyToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name = "id_projeto") 
	private Projeto projeto;
	
	public CategoriasMovimentos() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdMovimento() {
		return idMovimento;
	}

	public void setIdMovimento(Long idMovimento) {
		this.idMovimento = idMovimento;
	}

	public CategoriasContabeis getCategoriaOrigem() {
		return categoriaOrigem;
	}

	public void setCategoriaOrigem(CategoriasContabeis categoriaOrigem) {
		this.categoriaOrigem = categoriaOrigem;
	}

	public CategoriasContabeis getCategoriaDestino() {
		return categoriaDestino;
	}

	public void setCategoriaDestino(CategoriasContabeis categoriaDestino) {
		this.categoriaDestino = categoriaDestino;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valorCategoria) {
		this.valor = valorCategoria;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public LocalDateTime getDataMovimentacao() {
		return dataMovimentacao;
	}

	public void setDataMovimentacao(LocalDateTime dataMovimentacao) {
		this.dataMovimentacao = dataMovimentacao;
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