package br.com.crux.builder;

import br.com.crux.cmd.GetUnidadeLogadaCmd;
import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.PeriodoAtividade;
import br.com.crux.to.PeriodoAtividadeTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class PeriodoAtividadeTOBuilder
{

    @Autowired
    private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
    @Autowired
    private GetUnidadeLogadaCmd getUnidadeLogadaCmd;

    public PeriodoAtividadeTO buildTO(PeriodoAtividade entity)
    {
        PeriodoAtividadeTO to = new PeriodoAtividadeTO();

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

    public List<PeriodoAtividadeTO> buildAll(List<PeriodoAtividade> lista)
    {
        return lista.stream().map(this::buildTO).collect(Collectors.toList());
    }

    public PeriodoAtividade build(PeriodoAtividadeTO to)
    {
        PeriodoAtividade entity = new PeriodoAtividade();

        BeanUtils.copyProperties(to, entity);

        Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
        entity.setIdInstituicao(idInstituicao);
        entity.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

        return entity;
    }

}
