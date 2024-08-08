package br.com.crux.entity;

import br.com.crux.infra.constantes.Constantes;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "percursos")
public class Percurso implements Serializable
{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_percurso")
    @SequenceGenerator(name = "sq_id_percurso", sequenceName = "sq_id_percurso", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
    @Column(name = "id_percurso")
    private Long id;

    @Column(name = "nm_percurso")
    private String nome;

    @Column(name = "ds_percurso")
    private String descricao;

    @Column(name = "id_instituicao", nullable = true)
    private Long idInstituicao;

    @Column(name = "id_usuario_apl")
    private Long usuarioAlteracao;

    public Percurso()
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