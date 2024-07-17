package br.com.crux.to;

import java.time.LocalDateTime;

public class ProgramaContratoTO
{
    private Long          id;
    private ProgramaTO    programa;
    private ContratoTO    contrato;
    private LocalDateTime dataInicioProgramaContrato;
    private LocalDateTime dataFimProgramaContrato;
    private Double        valorProgramaContrato;
    private Long          usuarioAlteracao;

    public ProgramaContratoTO()
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

    public ProgramaTO getPrograma()
    {
        return programa;
    }

    public void setPrograma(ProgramaTO programa)
    {
        this.programa = programa;
    }

    public ContratoTO getContrato()
    {
        return contrato;
    }

    public void setContrato(ContratoTO contrato)
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