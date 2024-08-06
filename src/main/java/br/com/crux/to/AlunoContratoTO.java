package br.com.crux.to;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AlunoContratoTO
{

    private Long       id;
    private ContratoTO contrato;
    private AlunoTO    aluno;
    private LocalDateTime dataInicio;
    private LocalDateTime  dataFim;
    private Double     valor;
    private Long       usuarioAlteracao;

    public AlunoContratoTO()
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

    public AlunoTO getAluno()
    {
        return aluno;
    }

    public void setAluno(AlunoTO aluno)
    {
        this.aluno = aluno;
    }

    public LocalDateTime getDataInicio()
    {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio)
    {
        this.dataInicio = dataInicio;
    }

    public LocalDateTime getDataFim()
    {
        return dataFim;
    }

    public void setDataFim(LocalDateTime dataFim)
    {
        this.dataFim = dataFim;
    }

    public Double getValor()
    {
        return valor;
    }

    public void setValor(Double valor)
    {
        this.valor = valor;
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