package br.com.crux.builder;

import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.IndicadorMeta;
import br.com.crux.entity.MetaObjetivo;
import br.com.crux.to.IndicadorMetaTO;
import br.com.crux.to.MetaObjetivoTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class IndicadorMetaTOBuilder
{

    @Autowired
    private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

    public IndicadorMetaTO buildTO(IndicadorMeta entity)
    {
        IndicadorMetaTO to = new IndicadorMetaTO();

        if (Objects.isNull(entity))
            return to;

        BeanUtils.copyProperties(entity, to);
        MetaObjetivoTO meta = new MetaObjetivoTO();
        meta.setId(entity.getMetaObjetivo().getId());
        to.setMetaObjetivo(meta);

        return to;
    }

    public List<IndicadorMetaTO> buildAll(List<IndicadorMeta> lista)
    {
        return lista.stream().map(this::buildTO).collect(Collectors.toList());
    }

    public IndicadorMeta build(IndicadorMetaTO to)
    {
        IndicadorMeta entity = new IndicadorMeta();
        BeanUtils.copyProperties(to, entity);
        entity.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
        MetaObjetivo meta = new MetaObjetivo();
        meta.setId(to.getMetaObjetivo().getId());
        entity.setMetaObjetivo(meta);

        return entity;
    }

}
