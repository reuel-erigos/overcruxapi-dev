package br.com.crux.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.crux.infra.constantes.Constantes;


/**
 * The persistent class for the arquivos database table.
 * 
 */
@Entity
@Table(name="arquivos")
public class Arquivo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_arquivo")
	@SequenceGenerator(name = "sq_id_arquivo", sequenceName = "sq_id_arquivo", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_arquivo", unique=true, nullable=false, precision=10)
	private Long idArquivo;

	private byte[] blob;

	@Column(name="ds_tipo_arquivo")
	private String dsTipoArquivo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dt_criacao")
	private Date dtCriacao;

	private String hash;

	@Column(name="nm_arquivo")
	private String nmArquivo;

	@Column(name="nr_tamanho_arquivo")
	private Long nrTamanhoArquivo;

	@JoinColumn(name="id_usuario_apl")
	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;

	
	public Arquivo() {
	}

	public Long getIdArquivo() {
		return this.idArquivo;
	}

	public void setIdArquivo(Long idArquivo) {
		this.idArquivo = idArquivo;
	}

	public byte[] getBlob() {
		return this.blob;
	}

	public void setBlob(byte[] blob) {
		this.blob = blob;
	}

	public String getDsTipoArquivo() {
		return this.dsTipoArquivo;
	}

	public void setDsTipoArquivo(String dsTipoArquivo) {
		this.dsTipoArquivo = dsTipoArquivo;
	}

	public Date getDtCriacao() {
		return this.dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public String getHash() {
		return this.hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getNmArquivo() {
		return this.nmArquivo;
	}

	public void setNmArquivo(String nmArquivo) {
		this.nmArquivo = nmArquivo;
	}

	public Long getNrTamanhoArquivo() {
		return this.nrTamanhoArquivo;
	}

	public void setNrTamanhoArquivo(Long nrTamanhoArquivo) {
		this.nrTamanhoArquivo = nrTamanhoArquivo;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	

}