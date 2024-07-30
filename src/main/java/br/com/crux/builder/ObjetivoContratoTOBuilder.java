package br.com.crux.builder;

import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.Contrato;
import br.com.crux.entity.ObjetivoContrato;
import br.com.crux.to.ContratoTO;
import br.com.crux.to.ObjetivoContratoTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class ObjetivoContratoTOBuilder
{

    @Autowired
    private GetUsuarioLogadoCmd   getUsuarioLogadoCmd;
    @Autowired
    private MetaObjetivoTOBuilder metaObjetivoTOBuilder;

    public ObjetivoContratoTO buildTO(ObjetivoContrato entity)
    {
        ObjetivoContratoTO to = new ObjetivoContratoTO();

        if (Objects.isNull(entity))
            return to;

        BeanUtils.copyProperties(entity, to);
        ContratoTO contrato = new ContratoTO();
        contrato.setId(entity.getContrato().getId());
        to.setContrato(contrato);

        if (entity.getMetasObjetivo() != null)
        {
            to.setMetasObjetivo(entity.getMetasObjetivo().stream().map(o ->
            {
                o.setObjetivoContrato(entity);
                return metaObjetivoTOBuilder.buildTO(o);
            }).collect(Collectors.toList()));
        }

        return to;
    }

    public List<ObjetivoContratoTO> buildAll(List<ObjetivoContrato> lista)
    {
        return lista.stream().map(this::buildTO).collect(Collectors.toList());
    }

    public ObjetivoContrato build(ObjetivoContratoTO to)
    {
        ObjetivoContrato entity = new ObjetivoContrato();
        BeanUtils.copyProperties(to, entity);
        entity.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
        Contrato contrato = new Contrato();
        contrato.setId(to.getContrato().getId());
        entity.setContrato(contrato);

        if (to.getMetasObjetivo() != null)
        {
            entity.setMetasObjetivo(to.getMetasObjetivo().stream().map(o ->
            {
                o.setObjetivoContrato(to);
                return metaObjetivoTOBuilder.build(o);
            }).collect(Collectors.toList()));
        }

        return entity;
    }

}
