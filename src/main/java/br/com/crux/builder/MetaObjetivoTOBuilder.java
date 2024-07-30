package br.com.crux.builder;

import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.MetaObjetivo;
import br.com.crux.entity.ObjetivoContrato;
import br.com.crux.to.MetaObjetivoTO;
import br.com.crux.to.ObjetivoContratoTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class MetaObjetivoTOBuilder
{

    @Autowired
    private GetUsuarioLogadoCmd    getUsuarioLogadoCmd;
    @Autowired
    private IndicadorMetaTOBuilder indicadorMetaTOBuilder;

    public MetaObjetivoTO buildTO(MetaObjetivo entity)
    {
        MetaObjetivoTO to = new MetaObjetivoTO();

        if (Objects.isNull(entity))
            return to;

        BeanUtils.copyProperties(entity, to);
        ObjetivoContratoTO objetivo = new ObjetivoContratoTO();
        objetivo.setId(entity.getObjetivoContrato().getId());
        to.setObjetivoContrato(objetivo);

        if (entity.getIndicadoresMeta() != null)
        {
            to.setIndicadoresMeta(entity.getIndicadoresMeta().stream().map(o ->
            {
                o.setMetaObjetivo(entity);
                return indicadorMetaTOBuilder.buildTO(o);
            }).collect(Collectors.toList()));
        }

        return to;
    }

    public List<MetaObjetivoTO> buildAll(List<MetaObjetivo> lista)
    {
        return lista.stream().map(this::buildTO).collect(Collectors.toList());
    }

    public MetaObjetivo build(MetaObjetivoTO to)
    {
        MetaObjetivo entity = new MetaObjetivo();
        BeanUtils.copyProperties(to, entity);
        entity.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
        ObjetivoContrato objetivo = new ObjetivoContrato();
        objetivo.setId(to.getObjetivoContrato().getId());
        entity.setObjetivoContrato(objetivo);

        if (to.getIndicadoresMeta() != null)
        {
            entity.setIndicadoresMeta(to.getIndicadoresMeta().stream().map(o ->
            {
                o.setMetaObjetivo(to);
                return indicadorMetaTOBuilder.build(o);
            }).collect(Collectors.toList()));
        }

        return entity;
    }

}
