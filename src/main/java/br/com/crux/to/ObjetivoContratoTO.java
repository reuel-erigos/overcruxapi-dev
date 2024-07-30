package br.com.crux.to;

import java.util.List;

public class ObjetivoContratoTO
{

    private Long                 id;
    private ContratoTO           contrato;
    private List<MetaObjetivoTO> metasObjetivo;
    private String               nome;
    private String               descricao;
    private Long                 usuarioAlteracao;

    public ObjetivoContratoTO()
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

    public ContratoTO getContrato()
    {
        return contrato;
    }

    public void setContrato(ContratoTO contrato)
    {
        this.contrato = contrato;
    }

    public List<MetaObjetivoTO> getMetasObjetivo()
    {
        return metasObjetivo;
    }

    public void setMetasObjetivo(List<MetaObjetivoTO> metasObjetivo)
    {
        this.metasObjetivo = metasObjetivo;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
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