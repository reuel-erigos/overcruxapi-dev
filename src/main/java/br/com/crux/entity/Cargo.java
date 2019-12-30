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

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import br.com.crux.enums.TipoCargo;
import br.com.crux.infra.constantes.Constantes;


@Entity
@Table(name="cargos")
public class Cargo{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_cargo")
	@SequenceGenerator(name = "sq_id_cargo", sequenceName = "sq_id_cargo", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_cargo")
	private Long id;

	@Column(name="cd_cargo")
	private String codigo;

	@Column(name="nm_cargo")
	private String nome;

	@Column(name="st_tipo_cargo")
	@Type(type = "br.com.crux.infra.dao.GenericEnumUserType", 
	           parameters = { @Parameter(name = "enumClass", value = "br.com.crux.enums.TipoCargo") ,
	        		          @Parameter(name = "keyName", value = "tipo")}) 
	private TipoCargo tipoCargo;	

	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_cbo")
	private Cbo cbo;
	
	@Column(name="ds_perfil_profissional")
	private String descricaoPerfilProfissional;
	
	@Column(name="ds_resumo_atividades")
	private String descricaoResumoAtividades;	
	
	@Column(name="qt_horas")
	private Long qtdHoras;	

	public Cargo() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long idCargo) {
		this.id = idCargo;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String cdCargo) {
		this.codigo = cdCargo;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nmCargo) {
		this.nome = nmCargo;
	}

	public TipoCargo getTipoCargo() {
		return tipoCargo;
	}

	public void setTipoCargo(TipoCargo tipoCargo) {
		this.tipoCargo = tipoCargo;
	}

	public Long getUsuarioAlteracao() {
		return this.usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuariosSistema) {
		this.usuarioAlteracao = usuariosSistema;
	}

	public Cbo getCbo() {
		return cbo;
	}

	public void setCbo(Cbo cbo) {
		this.cbo = cbo;
	}

	public String getDescricaoPerfilProfissional() {
		return descricaoPerfilProfissional;
	}

	public void setDescricaoPerfilProfissional(String descricaoPerfilProfissional) {
		this.descricaoPerfilProfissional = descricaoPerfilProfissional;
	}

	public String getDescricaoResumoAtividades() {
		return descricaoResumoAtividades;
	}

	public void setDescricaoResumoAtividades(String descricaoResumoAtividades) {
		this.descricaoResumoAtividades = descricaoResumoAtividades;
	}

	public Long getQtdHoras() {
		return qtdHoras;
	}

	public void setQtdHoras(Long qtdHoras) {
		this.qtdHoras = qtdHoras;
	}

	
	

}