package br.com.crux.entity;

import br.com.crux.infra.constantes.Constantes;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "projetos_contratos")
public class ProjetoContrato implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_projeto_contrato")
    @SequenceGenerator(name = "sq_id_projeto_contrato", sequenceName = "sq_id_projeto_contrato", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
    @Column(name = "id_projeto_contrato")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_projeto")
    private Projeto projeto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_contrato")
    private Contrato contrato;

    @Column(name = "dt_inicio_projeto_contrato")
    private LocalDateTime dataInicioProjetoContrato;

    @Column(name = "dt_fim_projeto_contrato")
    private LocalDateTime dataFimProjetoContrato;

    @Column(name = "vl_projeto_contrato")
    private Double valorProjetoContrato;

    @Column(name = "id_usuario_apl")
    private Long usuarioAlteracao;

    public ProjetoContrato()
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

    public Projeto getProjeto()
    {
        return projeto;
    }

    public void setProjeto(Projeto projeto)
    {
        this.projeto = projeto;
    }

    public Contrato getContrato()
    {
        return contrato;
    }

    public void setContrato(Contrato contrato)
    {
        this.contrato = contrato;
    }

    public LocalDateTime getDataInicioProjetoContrato()
    {
        return dataInicioProjetoContrato;
    }

    public void setDataInicioProjetoContrato(LocalDateTime dataInicioProjetoContrato)
    {
        this.dataInicioProjetoContrato = dataInicioProjetoContrato;
    }

    public LocalDateTime getDataFimProjetoContrato()
    {
        return dataFimProjetoContrato;
    }

    public void setDataFimProjetoContrato(LocalDateTime dataFimProjetoContrato)
    {
        this.dataFimProjetoContrato = dataFimProjetoContrato;
    }

    public Double getValorProjetoContrato()
    {
        return valorProjetoContrato;
    }

    public void setValorProjetoContrato(Double valorProjetoContrato)
    {
        this.valorProjetoContrato = valorProjetoContrato;
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