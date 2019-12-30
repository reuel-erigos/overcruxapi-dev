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


/**
 * The persistent class for the planos_acao database table.
 * 
 */
@Entity
@Table(name="planos_acao")
public class PlanosAcao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_plano_acao")
	@SequenceGenerator(name = "sq_id_plano_acao", sequenceName = "sq_id_plano_acao", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_plano_acao")
	private Long id;

	@Column(name="dt_fim_plano_acao")
	private LocalDateTime dataFim;

	@Column(name="dt_inicio_plano_acao")
	private LocalDateTime dataInicio;

	@Column(name="nm_plano_acao")
	private String nome;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="iniciativas_id_iniciativa")
	private Iniciativa iniciativa;

	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;

	public PlanosAcao() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long idPlanoAcao) {
		this.id = idPlanoAcao;
	}

	public LocalDateTime getDataFim() {
		return this.dataFim;
	}

	public void setDataFim(LocalDateTime dtFimPlanoAcao) {
		this.dataFim = dtFimPlanoAcao;
	}

	public LocalDateTime getDataInicio() {
		return this.dataInicio;
	}

	public void setDataInicio(LocalDateTime dtInicioPlanoAcao) {
		this.dataInicio = dtInicioPlanoAcao;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nmPlanoAcao) {
		this.nome = nmPlanoAcao;
	}

	public Iniciativa getIniciativa() {
		return this.iniciativa;
	}

	public void setIniciativa(Iniciativa iniciativa) {
		this.iniciativa = iniciativa;
	}

	public Long getUsuarioAlteracao() {
		return this.usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuariosSistema) {
		this.usuarioAlteracao = usuariosSistema;
	}

}