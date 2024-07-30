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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "indicadores_metas")
public class IndicadorMeta implements Serializable
{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_indicador_meta")
    @SequenceGenerator(name = "sq_id_indicador_meta", sequenceName = "sq_id_indicador_meta", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
    @Column(name = "id_indicador_meta")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_meta_objetivo")
    private MetaObjetivo metaObjetivo;

    @Column(name = "cd_indicador")
    private String codigo;

    @Column(name = "ds_indicador")
    private String descricao;

    @Column(name = "ds_indice_minimo")
    private String indiceMinimo;

    @Column(name = "ds_acoes_previstas")
    private String acoesPrevistas;

    @Column(name = "qtd_plano_trabalho")
    private Long quantidadePlanoTrabalho;

    @Column(name = "id_usuario_apl")
    private Long usuarioAlteracao;

    public IndicadorMeta()
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

    public MetaObjetivo getMetaObjetivo()
    {
        return metaObjetivo;
    }

    public void setMetaObjetivo(MetaObjetivo metaObjetivo)
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