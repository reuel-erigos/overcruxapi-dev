package br.com.crux.builder;

import br.com.crux.cmd.GetUnidadeLogadaCmd;
import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.EixoAtividade;
import br.com.crux.to.EixoAtividadeTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class EixoAtividadeTOBuilder
{

    @Autowired
    private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
    @Autowired
    private GetUnidadeLogadaCmd getUnidadeLogadaCmd;

    public EixoAtividadeTO buildTO(EixoAtividade entity)
    {
        EixoAtividadeTO to = new EixoAtividadeTO();

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

    public List<EixoAtividadeTO> buildAll(List<EixoAtividade> lista)
    {
        return lista.stream().map(this::buildTO).collect(Collectors.toList());
    }

    public EixoAtividade build(EixoAtividadeTO to)
    {
        EixoAtividade entity = new EixoAtividade();

        BeanUtils.copyProperties(to, entity);

        Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
        entity.setIdInstituicao(idInstituicao);
        entity.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

        return entity;
    }

}
