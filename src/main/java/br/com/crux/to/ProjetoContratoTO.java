package br.com.crux.to;

import java.time.LocalDateTime;

public class ProjetoContratoTO
{
    private Long          id;
    private ProjetoTO     projeto;
    private ContratoTO    contrato;
    private LocalDateTime dataInicioProjetoContrato;
    private LocalDateTime dataFimProjetoContrato;
    private Double        valorProjetoContrato;
    private Long          usuarioAlteracao;

    public ProjetoContratoTO()
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

    public ProjetoTO getProjeto()
    {
        return projeto;
    }

    public void setProjeto(ProjetoTO projeto)
    {
        this.projeto = projeto;
    }

    public ContratoTO getContrato()
    {
        return contrato;
    }

    public void setContrato(ContratoTO contrato)
    {
        this.contrato = contrato;
    }

    public LocalDateTime getDataInicioProjetoContrato()
    {
        return dataInicioProjetoContrato;
    }

    public void setDataInicioProjetoContrato(LocalDateTime dataInicioProjetoContrato)
    {
        this.dataInicioProjetoContrato = dataInicioProjetoContrato;
    }

    public LocalDateTime getDataFimProjetoContrato()
    {
        return dataFimProjetoContrato;
    }

    public void setDataFimProjetoContrato(LocalDateTime dataFimProjetoContrato)
    {
        this.dataFimProjetoContrato = dataFimProjetoContrato;
    }

    public Double getValorProjetoContrato()
    {
        return valorProjetoContrato;
    }

    public void setValorProjetoContrato(Double valorProjetoContrato)
    {
        this.valorProjetoContrato = valorProjetoContrato;
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