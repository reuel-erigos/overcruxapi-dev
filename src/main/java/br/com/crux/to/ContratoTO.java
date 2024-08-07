package br.com.crux.to;

import java.time.LocalDate;
import java.util.List;

public class ContratoTO
{

    private Long                     id;
    private TipoContratoTO           tipoContrato;
    private String                   numeroContrato;
    private String                   descricaoObjetoContrato;
    private String                   descricaoMetaQuantitativa;
    private String                   numeroProcessoTecnico;
    private String                   numeroProcessoGestao;
    private String                   numeroProcessoPagamento;
    private LocalDate                dataInicioVigencia;
    private LocalDate                dataFimVigencia;
    private Double                   valorContrato;
    private EmpresaTO                empresa;
    private Long                     idInstituicao;
    private Long                     usuarioAlteracao;
    private List<ProgramaContratoTO> programasContrato;
    private List<ProjetoContratoTO>  projetosContrato;
    private List<ObjetivoContratoTO> objetivosContrato;
    private Long                     qtdAtendimentoPrevistos;

    public ContratoTO()
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

    public TipoContratoTO getTipoContrato()
    {
        return tipoContrato;
    }

    public void setTipoContrato(TipoContratoTO tipoContrato)
    {
        this.tipoContrato = tipoContrato;
    }

    public String getNumeroContrato()
    {
        return numeroContrato;
    }

    public void setNumeroContrato(String numeroContrato)
    {
        this.numeroContrato = numeroContrato;
    }

    public String getDescricaoObjetoContrato()
    {
        return descricaoObjetoContrato;
    }

    public void setDescricaoObjetoContrato(String descricaoObjetoContrato)
    {
        this.descricaoObjetoContrato = descricaoObjetoContrato;
    }

    public String getDescricaoMetaQuantitativa()
    {
        return descricaoMetaQuantitativa;
    }

    public void setDescricaoMetaQuantitativa(String descricaoMetaQuantitativa)
    {
        this.descricaoMetaQuantitativa = descricaoMetaQuantitativa;
    }

    public String getNumeroProcessoTecnico()
    {
        return numeroProcessoTecnico;
    }

    public void setNumeroProcessoTecnico(String numeroProcessoTecnico)
    {
        this.numeroProcessoTecnico = numeroProcessoTecnico;
    }

    public String getNumeroProcessoGestao()
    {
        return numeroProcessoGestao;
    }

    public void setNumeroProcessoGestao(String numeroProcessoGestao)
    {
        this.numeroProcessoGestao = numeroProcessoGestao;
    }

    public String getNumeroProcessoPagamento()
    {
        return numeroProcessoPagamento;
    }

    public void setNumeroProcessoPagamento(String numeroProcessoPagamento)
    {
        this.numeroProcessoPagamento = numeroProcessoPagamento;
    }

    public LocalDate getDataInicioVigencia()
    {
        return dataInicioVigencia;
    }

    public void setDataInicioVigencia(LocalDate dataInicioVigencia)
    {
        this.dataInicioVigencia = dataInicioVigencia;
    }

    public LocalDate getDataFimVigencia()
    {
        return dataFimVigencia;
    }

    public void setDataFimVigencia(LocalDate dataFimVigencia)
    {
        this.dataFimVigencia = dataFimVigencia;
    }

    public Double getValorContrato()
    {
        return valorContrato;
    }

    public void setValorContrato(Double valorContrato)
    {
        this.valorContrato = valorContrato;
    }

    public EmpresaTO getEmpresa()
    {
        return empresa;
    }

    public void setEmpresa(EmpresaTO empresa)
    {
        this.empresa = empresa;
    }

    public Long getIdInstituicao()
    {
        return idInstituicao;
    }

    public void setIdInstituicao(Long idInstituicao)
    {
        this.idInstituicao = idInstituicao;
    }

    public Long getUsuarioAlteracao()
    {
        return usuarioAlteracao;
    }

    public void setUsuarioAlteracao(Long usuarioAlteracao)
    {
        this.usuarioAlteracao = usuarioAlteracao;
    }

    public List<ProgramaContratoTO> getProgramasContrato()
    {
        return programasContrato;
    }

    public void setProgramasContrato(List<ProgramaContratoTO> programasContrato)
    {
        this.programasContrato = programasContrato;
    }

    public List<ProjetoContratoTO> getProjetosContrato()
    {
        return projetosContrato;
    }

    public void setProjetosContrato(List<ProjetoContratoTO> projetosContrato)
    {
        this.projetosContrato = projetosContrato;
    }

    public List<ObjetivoContratoTO> getObjetivosContrato()
    {
        return objetivosContrato;
    }

    public void setObjetivosContrato(List<ObjetivoContratoTO> objetivosContrato)
    {
        this.objetivosContrato = objetivosContrato;
    }

    public Long getQtdAtendimentoPrevistos()
    {
        return qtdAtendimentoPrevistos;
    }

    public void setQtdAtendimentoPrevistos(Long qtdAtendimentoPrevistos)
    {
        this.qtdAtendimentoPrevistos = qtdAtendimentoPrevistos;
    }

}