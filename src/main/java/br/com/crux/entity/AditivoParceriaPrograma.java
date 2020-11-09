package br.com.crux.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.crux.infra.constantes.Constantes;

@Entity
@Table(name = "aditivos_parcerias_programas")
public class AditivoParceriaPrograma implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_aditivo_parceria_programa") 
	@SequenceGenerator(name = "sq_id_aditivo_parceria_programa", 
				sequenceName = "sq_id_aditivo_parceria_programa", 
				schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_aditivo_parceria_programa") 
	private Long id;

	@ManyToOne 
	@JoinColumn(name = "id_parceria_programa") 
	private ParceriasPrograma parceriasPrograma;

	@Column(name = "dt_aditivo")
	private LocalDateTime dataAditivo;
	
	@Column(name = "vl_aditivo") 
	private Double valorAditivo;
	
	@Column(name = "id_usuario_apl") 
	private Long usuarioAlteracao;

	public AditivoParceriaPrograma() {
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

	public ParceriasPrograma getParceriasPrograma() {
		return parceriasPrograma;
	}

	public void setParceriasPrograma(ParceriasPrograma parceriasPrograma) {
		this.parceriasPrograma = parceriasPrograma;
	}

	public LocalDateTime getDataAditivo() {
		return dataAditivo;
	}

	public void setDataAditivo(LocalDateTime dataAditivo) {
		this.dataAditivo = dataAditivo;
	}

	public Double getValorAditivo() {
		return valorAditivo;
	}

	public void setValorAditivo(Double valorAditivo) {
		this.valorAditivo = valorAditivo;
	}

}