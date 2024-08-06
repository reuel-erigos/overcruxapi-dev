package br.com.crux.entity;

import br.com.crux.infra.constantes.Constantes;

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
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "alunos_contratos")
public class AlunoContrato implements Serializable
{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_aluno_contrato")
    @SequenceGenerator(name = "sq_id_aluno_contrato", sequenceName = "sq_id_aluno_contrato", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
    @Column(name = "id_aluno_contrato")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_aluno")
    private Aluno aluno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_contrato")
    private Contrato contrato;

    @Column(name = "dt_inicio_aluno_contrato")
    private LocalDateTime dataInicio;

    @Column(name = "dt_fim_aluno_contrato")
    private LocalDateTime dataFim;

    @Column(name = "vl_aluno_contrato")
    private Double valor;

    @Column(name = "id_usuario_apl")
    private Long usuarioAlteracao;

    public AlunoContrato()
    {
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Contrato getContrato()
    {
        return contrato;
    }

    public void setContrato(Contrato contrato)
    {
        this.contrato = contrato;
    }

    public Aluno getAluno()
    {
        return aluno;
    }

    public void setAluno(Aluno aluno)
    {
        this.aluno = aluno;
    }

    public LocalDateTime getDataInicio()
    {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio)
    {
        this.dataInicio = dataInicio;
    }

    public LocalDateTime getDataFim()
    {
        return dataFim;
    }

    public void setDataFim(LocalDateTime dataFim)
    {
        this.dataFim = dataFim;
    }

    public Double getValor()
    {
        return valor;
    }

    public void setValor(Double valor)
    {
        this.valor = valor;
    }

    public Long getUsuarioAlteracao()
    {
        return usuarioAlteracao;
    }

    public void setUsuarioAlteracao(Long usuarioAlteracao)
    {
        this.usuarioAlteracao = usuarioAlteracao;
    }

}