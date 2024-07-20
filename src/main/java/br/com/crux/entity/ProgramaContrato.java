package br.com.crux.entity;

import br.com.crux.infra.constantes.Constantes;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "programas_contratos")
public class ProgramaContrato implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_programa_contrato")
    @SequenceGenerator(name = "sq_id_programa_contrato", sequenceName = "sq_id_programa_contrato", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
    @Column(name = "id_programa_contrato")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_programa")
    private Programa programa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_contrato")
    private Contrato contrato;

    @Column(name = "dt_inicio_programa_contrato")
    private LocalDateTime dataInicioProgramaContrato;

    @Column(name = "dt_fim_programa_contrato")
    private LocalDateTime dataFimProgramaContrato;

    @Column(name = "vl_programa_contrato")
    private Double valorProgramaContrato;

    @Column(name = "id_usuario_apl")
    private Long usuarioAlteracao;

    public ProgramaContrato()
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

    public Programa getPrograma()
    {
        return programa;
    }

    public void setPrograma(Programa programa)
    {
        this.programa = programa;
    }

    public Contrato getContrato()
    {
        return contrato;
    }

    public void setContrato(Contrato contrato)
    {
        this.contrato = contrato;
    }

    public LocalDateTime getDataInicioProgramaContrato()
    {
        return dataInicioProgramaContrato;
    }

    public void setDataInicioProgramaContrato(LocalDateTime dataInicioProgramaContrato)
    {
        this.dataInicioProgramaContrato = dataInicioProgramaContrato;
    }

    public LocalDateTime getDataFimProgramaContrato()
    {
        return dataFimProgramaContrato;
    }

    public void setDataFimProgramaContrato(LocalDateTime dataFimProgramaContrato)
    {
        this.dataFimProgramaContrato = dataFimProgramaContrato;
    }

    public Double getValorProgramaContrato()
    {
        return valorProgramaContrato;
    }

    public void setValorProgramaContrato(Double valorProgramaContrato)
    {
        this.valorProgramaContrato = valorProgramaContrato;
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