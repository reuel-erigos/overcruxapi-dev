package br.com.crux.builder;

import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.Contrato;
import br.com.crux.entity.ProgramaContrato;
import br.com.crux.to.ContratoTO;
import br.com.crux.to.ProgramaContratoTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class ProgramaContratoTOBuilder
{

    @Autowired
    private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

    @Autowired
    private ProgramaTOBuilder programaTOBuilder;

    public ProgramaContratoTO buildTO(ProgramaContrato entity)
    {
        ProgramaContratoTO to = new ProgramaContratoTO();

        if (Objects.isNull(entity))
            return to;

        BeanUtils.copyProperties(entity, to);
        to.setPrograma(programaTOBuilder.buildTO(entity.getPrograma()));
        ContratoTO contrato = new ContratoTO();
        contrato.setId(entity.getContrato().getId());
        to.setContrato(contrato);
        return to;
    }

    public List<ProgramaContratoTO> buildAll(List<ProgramaContrato> lista)
    {
        return lista.stream().map(this::buildTO).collect(Collectors.toList());
    }

    public ProgramaContrato build(ProgramaContratoTO to)
    {
        ProgramaContrato entity = new ProgramaContrato();
        BeanUtils.copyProperties(to, entity);
        entity.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
        entity.setPrograma(programaTOBuilder.build(to.getPrograma()));
        Contrato contrato = new Contrato();
        contrato.setId(to.getContrato().getId());
        entity.setContrato(contrato);
        return entity;
    }

}
