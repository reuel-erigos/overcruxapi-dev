package br.com.crux.entity;

import br.com.crux.infra.constantes.Constantes;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tipos_contratos")
public class TipoContrato implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_tipo_contrato")
    @SequenceGenerator(name = "sq_id_tipo_contrato", sequenceName = "sq_id_tipo_contrato", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
    @Column(name = "id_tipo_contrato")
    private Long id;

    @Column(name = "ds_tipo_contrato")
    private String descricao;

    @Column(name = "id_instituicao", nullable = true)
    private Long idInstituicao;

    @Column(name = "id_usuario_apl")
    private Long usuarioAlteracao;

    public TipoContrato()
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

    public Long getUsuarioAlteracao()
    {
        return usuarioAlteracao;
    }

    public void setUsuarioAlteracao(Long usuarioAlteracao)
    {
        this.usuarioAlteracao = usuarioAlteracao;
    }

    public Long getIdInstituicao()
    {
        return idInstituicao;
    }

    public void setIdInstituicao(Long instituicao)
    {
        this.idInstituicao = instituicao;
    }

}