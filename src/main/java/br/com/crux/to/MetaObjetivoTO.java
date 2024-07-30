package br.com.crux.to;

import java.util.List;

public class MetaObjetivoTO
{

    private Long                  id;
    private ObjetivoContratoTO    objetivoContrato;
    private List<IndicadorMetaTO> indicadoresMeta;
    private String                codigo;
    private String                descricao;
    private Long                  usuarioAlteracao;

    public MetaObjetivoTO()
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

    public ObjetivoContratoTO getObjetivoContrato()
    {
        return objetivoContrato;
    }

    public void setObjetivoContrato(ObjetivoContratoTO objetivoContrato)
    {
        this.objetivoContrato = objetivoContrato;
    }

    public List<IndicadorMetaTO> getIndicadoresMeta()
    {
        return indicadoresMeta;
    }

    public void setIndicadoresMeta(List<IndicadorMetaTO> indicadoresMeta)
    {
        this.indicadoresMeta = indicadoresMeta;
    }

    public String getCodigo()
    {
        return codigo;
    }

    public void setCodigo(String codigo)
    {
        this.codigo = codigo;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
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