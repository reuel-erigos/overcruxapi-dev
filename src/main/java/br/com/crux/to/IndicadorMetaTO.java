package br.com.crux.to;

public class IndicadorMetaTO
{

    private Long           id;
    private MetaObjetivoTO metaObjetivo;
    private String         codigo;
    private String         descricao;
    private String         indiceMinimo;
    private String         acoesPrevistas;
    private Long           quantidadePlanoTrabalho;
    private Long           usuarioAlteracao;

    public IndicadorMetaTO()
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

    public MetaObjetivoTO getMetaObjetivo()
    {
        return metaObjetivo;
    }

    public void setMetaObjetivo(MetaObjetivoTO metaObjetivo)
    {
        this.metaObjetivo = metaObjetivo;
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

    public String getIndiceMinimo()
    {
        return indiceMinimo;
    }

    public void setIndiceMinimo(String indiceMinimo)
    {
        this.indiceMinimo = indiceMinimo;
    }

    public String getAcoesPrevistas()
    {
        return acoesPrevistas;
    }

    public void setAcoesPrevistas(String acoesPrevistas)
    {
        this.acoesPrevistas = acoesPrevistas;
    }

    public Long getQuantidadePlanoTrabalho()
    {
        return quantidadePlanoTrabalho;
    }

    public void setQuantidadePlanoTrabalho(Long quantidadePlanoTrabalho)
    {
        this.quantidadePlanoTrabalho = quantidadePlanoTrabalho;
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