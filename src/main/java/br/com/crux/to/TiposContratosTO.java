package br.com.crux.to;

public class TiposContratosTO
{
    private Long   id;
    private String descricao;
    private Long   idInstituicao;
    private Long   usuarioAlteracao;

    public TiposContratosTO()
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

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
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

}