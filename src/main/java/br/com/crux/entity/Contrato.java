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
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "contratos")
public class Contrato implements Serializable
{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_contrato")
    @SequenceGenerator(name = "sq_id_contrato", sequenceName = "sq_id_contrato", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
    @Column(name = "id_contrato")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_contrato")
    private TipoContrato tipoContrato;

    @Column(name = "nro_contrato")
    private String numeroContrato;

    @Column(name = "ds_objeto_contrato")
    private String descricaoObjetoContrato;

    @Column(name = "ds_meta_quantitativa")
    private String descricaoMetaQuantitativa;

    @Column(name = "nro_processo_tecnico")
    private String numeroProcessoTecnico;

    @Column(name = "nro_processo_gestao")
    private String numeroProcessoGestao;

    @Column(name = "nro_processo_pagamento")
    private String numeroProcessoPagamento;

    @Column(name = "dt_inicio_vigencia")
    private LocalDateTime dataInicioVigencia;

    @Column(name = "dt_fim_vigencia")
    private LocalDateTime dataFimVigencia;

    @Column(name = "vl_contrato")
    private Double valorContrato;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empresa")
    private Empresa empresa;

    @Column(name = "id_instituicao", nullable = true)
    private Long idInstituicao;

    @Column(name = "id_usuario_apl")
    private Long usuarioAlteracao;

    @OneToMany(mappedBy = "contrato")
    private List<ProgramaContrato> programasContrato;

    @OneToMany(mappedBy = "contrato")
    private List<ProjetoContrato> projetosContrato;

    @OneToMany(mappedBy = "contrato")
    private List<ObjetivoContrato> objetivosContrato;

    public Contrato()
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

    public TipoContrato getTipoContrato()
    {
        return tipoContrato;
    }

    public void setTipoContrato(TipoContrato tipoContrato)
    {
        this.tipoContrato = tipoContrato;
    }

    public String getNumeroContrato()
    {
        return numeroContrato;
    }

    public void setNumeroContrato(String numeroContrato)
    {
        this.numeroContrato = numeroContrato;
    }

    public String getDescricaoObjetoContrato()
    {
        return descricaoObjetoContrato;
    }

    public void setDescricaoObjetoContrato(String descricaoObjetoContrato)
    {
        this.descricaoObjetoContrato = descricaoObjetoContrato;
    }

    public String getDescricaoMetaQuantitativa()
    {
        return descricaoMetaQuantitativa;
    }

    public void setDescricaoMetaQuantitativa(String descricaoMetaQuantitativa)
    {
        this.descricaoMetaQuantitativa = descricaoMetaQuantitativa;
    }

    public String getNumeroProcessoTecnico()
    {
        return numeroProcessoTecnico;
    }

    public void setNumeroProcessoTecnico(String numeroProcessoTecnico)
    {
        this.numeroProcessoTecnico = numeroProcessoTecnico;
    }

    public String getNumeroProcessoGestao()
    {
        return numeroProcessoGestao;
    }

    public void setNumeroProcessoGestao(String numeroProcessoGestao)
    {
        this.numeroProcessoGestao = numeroProcessoGestao;
    }

    public String getNumeroProcessoPagamento()
    {
        return numeroProcessoPagamento;
    }

    public void setNumeroProcessoPagamento(String numeroProcessoPagamento)
    {
        this.numeroProcessoPagamento = numeroProcessoPagamento;
    }

    public LocalDateTime getDataInicioVigencia()
    {
        return dataInicioVigencia;
    }

    public void setDataInicioVigencia(LocalDateTime dataInicioVigencia)
    {
        this.dataInicioVigencia = dataInicioVigencia;
    }

    public LocalDateTime getDataFimVigencia()
    {
        return dataFimVigencia;
    }

    public void setDataFimVigencia(LocalDateTime dataFimVigencia)
    {
        this.dataFimVigencia = dataFimVigencia;
    }

    public Double getValorContrato()
    {
        return valorContrato;
    }

    public void setValorContrato(Double valorContrato)
    {
        this.valorContrato = valorContrato;
    }

    public Empresa getEmpresa()
    {
        return empresa;
    }

    public void setEmpresa(Empresa empresa)
    {
        this.empresa = empresa;
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

    public List<ProgramaContrato> getProgramasContrato()
    {
        return programasContrato;
    }

    public void setProgramasContrato(List<ProgramaContrato> programasContrato)
    {
        this.programasContrato = programasContrato;
    }

    public List<ProjetoContrato> getProjetosContrato()
    {
        return projetosContrato;
    }

    public void setProjetosContrato(List<ProjetoContrato> projetosContrato)
    {
        this.projetosContrato = projetosContrato;
    }

    public List<ObjetivoContrato> getObjetivosContrato()
    {
        return objetivosContrato;
    }

    public void setObjetivosContrato(List<ObjetivoContrato> objetivosContrato)
    {
        this.objetivosContrato = objetivosContrato;
    }

}