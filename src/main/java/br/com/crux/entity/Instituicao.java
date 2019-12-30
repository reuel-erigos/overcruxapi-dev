package br.com.crux.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.crux.infra.constantes.Constantes;
import br.com.crux.infra.dao.SimNaoConverter;

@Entity
@Table(name = "instituicoes")
public class Instituicao {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_instituicao")
	@SequenceGenerator(name = "sq_instituicao", sequenceName = "sq_instituicao", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_instituicao")
	private Long id;

	@Column(name = "nm_instituicao")
	private String nome;

	@Column(name = "id_arquivo_logomarca")
	private Long idArquivo;

	@Column(name = "id_usuario_apl")
	private Long usuarioAlteracao;
	
	@Column(name = "st_mostra_lista")
	@Convert(converter = SimNaoConverter.class)
	private Boolean mostraLista;
	
	@Column(name = "ds_area_autacao	")
	private String areaAtuacao;

	@Column(name = "ds_apresentacao	")
	private String apresentacao;
	
	@Column(name = "ds_visao")
	private String visao;
	
	@Column(name = "ds_missao")
	private String missao;
	
	@Column(name = "ds_valores")
	private String valores;
	
	@Column(name = "ds_finalidades_estatutarias")
	private String finalidadesEstatutarias;

	public Instituicao() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getIdArquivo() {
		return idArquivo;
	}

	public void setIdArquivo(Long idArquivo) {
		this.idArquivo = idArquivo;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public Boolean getMostraLista() {
		return mostraLista;
	}

	public void setMostraLista(Boolean mostraLista) {
		this.mostraLista = mostraLista;
	}

	public String getAreaAtuacao() {
		return areaAtuacao;
	}

	public void setAreaAtuacao(String areaAtuacao) {
		this.areaAtuacao = areaAtuacao;
	}

	public String getApresentacao() {
		return apresentacao;
	}

	public void setApresentacao(String apresentacao) {
		this.apresentacao = apresentacao;
	}

	public String getVisao() {
		return visao;
	}

	public void setVisao(String visao) {
		this.visao = visao;
	}

	public String getMissao() {
		return missao;
	}

	public void setMissao(String missao) {
		this.missao = missao;
	}

	public String getValores() {
		return valores;
	}

	public void setValores(String valores) {
		this.valores = valores;
	}

	public String getFinalidadesEstatutarias() {
		return finalidadesEstatutarias;
	}

	public void setFinalidadesEstatutarias(String finalidadesEstatutarias) {
		this.finalidadesEstatutarias = finalidadesEstatutarias;
	}
	
	
	

}