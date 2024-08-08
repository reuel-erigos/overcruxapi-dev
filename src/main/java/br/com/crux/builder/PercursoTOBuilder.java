package br.com.crux.builder;

import br.com.crux.cmd.GetUnidadeLogadaCmd;
import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.Percurso;
import br.com.crux.to.PercursoTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class PercursoTOBuilder
{

    @Autowired
    private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
    @Autowired
    private GetUnidadeLogadaCmd getUnidadeLogadaCmd;

    @Autowired
    private EmpresaTOBuilder empresaTOBuilder;

    public PercursoTO buildTO(Percurso entity)
    {
        PercursoTO to = new PercursoTO();

        if (Objects.isNull(entity))
            return to;

        BeanUtils.copyProperties(entity, to);

        return to;
    }

    public List<PercursoTO> buildAll(List<Percurso> lista)
    {
        return lista.stream().map(this::buildTO).collect(Collectors.toList());
    }

    public Percurso build(PercursoTO to)
    {
        Percurso entity = new Percurso();

        BeanUtils.copyProperties(to, entity);

        Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
        entity.setIdInstituicao(idInstituicao);
        entity.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

        return entity;
    }

}
