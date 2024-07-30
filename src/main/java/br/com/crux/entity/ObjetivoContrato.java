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
@Table(name = "objetivos_contratos")
public class ObjetivoContrato implements Serializable
{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_objetivo_contrato")
    @SequenceGenerator(name = "sq_id_objetivo_contrato", sequenceName = "sq_id_objetivo_contrato", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
    @Column(name = "id_objetivo_contrato")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_contrato")
    private Contrato contrato;

    @OneToMany(mappedBy = "objetivoContrato")
    private List<MetaObjetivo> metasObjetivo;

    @Column(name = "nm_objetivo_contrato")
    private String nome;

    @Column(name = "ds_objetivo_contrato")
    private String descricao;

    @Column(name = "id_usuario_apl")
    private Long usuarioAlteracao;

    public ObjetivoContrato()
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

    public Contrato getContrato()
    {
        return contrato;
    }

    public void setContrato(Contrato contrato)
    {
        this.contrato = contrato;
    }

    public List<MetaObjetivo> getMetasObjetivo()
    {
        return metasObjetivo;
    }

    public void setMetasObjetivo(List<MetaObjetivo> metasObjetivo)
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