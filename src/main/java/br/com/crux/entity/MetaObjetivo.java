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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "metas_objetivos")
public class MetaObjetivo implements Serializable
{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_meta_objetivo")
    @SequenceGenerator(name = "sq_id_meta_objetivo", sequenceName = "sq_id_meta_objetivo", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
    @Column(name = "id_meta_objetivo")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_objetivo_contrato")
    private ObjetivoContrato objetivoContrato;

    @OneToMany(mappedBy = "metaObjetivo")
    private List<IndicadorMeta> indicadoresMeta;

    @Column(name = "cd_meta")
    private String codigo;

    @Column(name = "ds_meta")
    private String descricao;

    @Column(name = "id_usuario_apl")
    private Long usuarioAlteracao;

    public MetaObjetivo()
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

    public ObjetivoContrato getObjetivoContrato()
    {
        return objetivoContrato;
    }

    public void setObjetivoContrato(ObjetivoContrato objetivoContrato)
    {
        this.objetivoContrato = objetivoContrato;
    }

    public List<IndicadorMeta> getIndicadoresMeta()
    {
        return indicadoresMeta;
    }

    public void setIndicadoresMeta(List<IndicadorMeta> indicadoresMetas)
    {
        this.indicadoresMeta = indicadoresMetas;
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