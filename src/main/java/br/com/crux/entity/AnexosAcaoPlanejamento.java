package br.com.crux.entity;

import java.io.Serializable;

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
@Table(name="anexos_acao_planejamento")
public class AnexosAcaoPlanejamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_anexo_acao_planejamento")
	@SequenceGenerator(name = "sq_id_anexo_acao_planejamento", sequenceName = "sq_id_anexo_acao_planejamento", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_anexo_acao_planejamento", unique=true, nullable=false, precision=10)
	private Long id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_arquivo_metadado")
	private ArquivoMetadado metadados;
	
	@Column(name="id_acao")
	private Long idAcao;

	
	public AnexosAcaoPlanejamento() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ArquivoMetadado getMetadados() {
		return metadados;
	}

	public void setMetadados(ArquivoMetadado metadados) {
		this.metadados = metadados;
	}

	public Long getIdAcao() {
		return idAcao;
	}

	public void setIdAcao(Long idAcao) {
		this.idAcao = idAcao;
	}


}