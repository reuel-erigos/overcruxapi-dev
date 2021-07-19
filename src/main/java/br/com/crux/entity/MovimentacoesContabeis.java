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
@Table(name = "movimentacoes_contabeis")
public class MovimentacoesContabeis  {

	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_movimentacao_contabil") 
	@SequenceGenerator(name = "sq_id_movimentacao_contabil", sequenceName = "sq_id_movimentacao_contabil", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_movimentacao_contabil")
	private Long id;

	@Column(name = "dt_movimentacao") 
	private LocalDateTime dataMovimentacao;

	@Column(name = "vl_movimentacao")
	private Double valorMovimentacao;
	
	@Column(name = "ds_movimentacao") 
	private String descricao;


	@ManyToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name = "id_programa_1") 
	private Programa programa01;
	
	@ManyToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name = "id_projeto_1") 
	private Projeto projeto01;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_categoria_origem_1") 
	private CategoriasContabeis categoriaOrigem01;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_categoria_destino_1") 
	private CategoriasContabeis categoriaDestino01;

	
	@ManyToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name = "id_programa_2") 
	private Programa programa02;
	
	@ManyToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name = "id_projeto_2") 
	private Projeto projeto02;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_categoria_origem_2") 
	private CategoriasContabeis categoriaOrigem02;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_categoria_destino_2") 
	private CategoriasContabeis categoriaDestino02;
	
	@Column(name = "id_usuario_apl") 
	private Long usuarioAlteracao;

	@Column(name="id_instituicao", nullable = true)
	private Long idInstituicao;
	
	public MovimentacoesContabeis() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataMovimentacao() {
		return dataMovimentacao;
	}

	public void setDataMovimentacao(LocalDateTime dataMovimentacao) {
		this.dataMovimentacao = dataMovimentacao;
	}

	public Double getValorMovimentacao() {
		return valorMovimentacao;
	}

	public void setValorMovimentacao(Double valorMovimentacao) {
		this.valorMovimentacao = valorMovimentacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Programa getPrograma01() {
		return programa01;
	}

	public void setPrograma01(Programa programa01) {
		this.programa01 = programa01;
	}

	public Projeto getProjeto01() {
		return projeto01;
	}

	public void setProjeto01(Projeto projeto01) {
		this.projeto01 = projeto01;
	}

	public CategoriasContabeis getCategoriaOrigem01() {
		return categoriaOrigem01;
	}

	public void setCategoriaOrigem01(CategoriasContabeis categoriaOrigem01) {
		this.categoriaOrigem01 = categoriaOrigem01;
	}

	public CategoriasContabeis getCategoriaDestino01() {
		return categoriaDestino01;
	}

	public void setCategoriaDestino01(CategoriasContabeis categoriaDestino01) {
		this.categoriaDestino01 = categoriaDestino01;
	}

	public Programa getPrograma02() {
		return programa02;
	}

	public void setPrograma02(Programa programa02) {
		this.programa02 = programa02;
	}

	public Projeto getProjeto02() {
		return projeto02;
	}

	public void setProjeto02(Projeto projeto02) {
		this.projeto02 = projeto02;
	}

	public CategoriasContabeis getCategoriaOrigem02() {
		return categoriaOrigem02;
	}

	public void setCategoriaOrigem02(CategoriasContabeis categoriaOrigem02) {
		this.categoriaOrigem02 = categoriaOrigem02;
	}

	public CategoriasContabeis getCategoriaDestino02() {
		return categoriaDestino02;
	}

	public void setCategoriaDestino02(CategoriasContabeis categoriaDestino02) {
		this.categoriaDestino02 = categoriaDestino02;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public Long getIdInstituicao() {
		return idInstituicao;
	}

	public void setIdInstituicao(Long idInstituicao) {
		this.idInstituicao = idInstituicao;
	}


	
}