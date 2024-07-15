package br.com.crux.builder;

import br.com.crux.cmd.GetUnidadeLogadaCmd;
import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.TipoContrato;
import br.com.crux.to.TipoContratoTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class TipoContratoTOBuilder
{

    @Autowired
    private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
    @Autowired
    private GetUnidadeLogadaCmd getUnidadeLogadaCmd;

    public TipoContratoTO buildTO(TipoContrato entity)
    {
        TipoContratoTO to = new TipoContratoTO();

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

    public List<TipoContratoTO> buildAll(List<TipoContrato> lista)
    {
        return lista.stream().map(this::buildTO).collect(Collectors.toList());
    }

    public TipoContrato build(TipoContratoTO to)
    {
        TipoContrato entity = new TipoContrato();

        BeanUtils.copyProperties(to, entity);

        Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
        entity.setIdInstituicao(idInstituicao);
        entity.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

        return entity;
    }

}
