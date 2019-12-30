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
@Table(name = "atividades_aluno")
public class AtividadesAluno {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_atividade_aluno")
	@SequenceGenerator(name = "sq_id_atividade_aluno", sequenceName = "sq_id_atividade_aluno", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_atividade_aluno")
	private Long id;

	@Column(name = "dt_inicio_atividade")
	private LocalDateTime dataInicioAtividade;

	@Column(name = "ds_desligamento")
	private String descDesligamento;

	@Column(name = "dt_desvinculacao")
	private LocalDateTime dataDesvinculacao;

	@Column(name = "dt_alteracao_atividade")
	private LocalDateTime dataAlteracaoAtividade;

	@Column(name = "dt_cadastro_atividade")
	private LocalDateTime dataCadastroAtividade;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "aluno_id_aluno")
	private Aluno aluno;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "atividades_id_atividade")
	private Atividades atividade;

	@Column(name = "id_usuario_apl")
	private Long usuarioAlteracao;

	public AtividadesAluno() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataInicioAtividade() {
		return dataInicioAtividade;
	}

	public void setDataInicioAtividade(LocalDateTime dtInicioAtividade) {
		this.dataInicioAtividade = dtInicioAtividade;
	}

	public String getDescDesligamento() {
		return descDesligamento;
	}

	public void setDescDesligamento(String descDesligamento) {
		this.descDesligamento = descDesligamento;
	}

	public LocalDateTime getDataDesvinculacao() {
		return dataDesvinculacao;
	}

	public void setDataDesvinculacao(LocalDateTime dataDesvinculacao) {
		this.dataDesvinculacao = dataDesvinculacao;
	}

	public LocalDateTime getDataAlteracaoAtividade() {
		return dataAlteracaoAtividade;
	}

	public void setDataAlteracaoAtividade(LocalDateTime dataAlteracaoAtividade) {
		this.dataAlteracaoAtividade = dataAlteracaoAtividade;
	}

	public LocalDateTime getDataCadastroAtividade() {
		return dataCadastroAtividade;
	}

	public void setDataCadastroAtividade(LocalDateTime dataCadastroAtividade) {
		this.dataCadastroAtividade = dataCadastroAtividade;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Atividades getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividades atividade) {
		this.atividade = atividade;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

}