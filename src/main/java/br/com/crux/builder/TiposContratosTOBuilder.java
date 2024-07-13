package br.com.crux.builder;

import br.com.crux.cmd.GetUnidadeLogadaCmd;
import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.TiposContratos;
import br.com.crux.to.TiposContratosTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class TiposContratosTOBuilder
{

    @Autowired
    private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
    @Autowired
    private GetUnidadeLogadaCmd getUnidadeLogadaCmd;

    public TiposContratosTO buildTO(TiposContratos entity)
    {
        TiposContratosTO to = new TiposContratosTO();

        if (Objects.isNull(entity))
        {
            return to;
        }

        BeanUtils.copyProperties(entity, to);

        if (entity.getIdInstituicao() != null)
        {
            to.setIdInstituicao(entity.getIdInstituicao());
        }

        return to;
    }

    public List<TiposContratosTO> buildAll(List<TiposContratos> lista)
    {
        return lista.stream().map(this::buildTO).collect(Collectors.toList());
    }

    public TiposContratos build(TiposContratosTO to)
    {
        TiposContratos entity = new TiposContratos();

        BeanUtils.copyProperties(to, entity);

        Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
        entity.setIdInstituicao(idInstituicao);
        entity.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

        return entity;
    }

}
