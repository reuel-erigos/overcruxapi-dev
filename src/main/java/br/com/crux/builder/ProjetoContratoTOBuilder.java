package br.com.crux.builder;

import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.Contrato;
import br.com.crux.entity.ProjetoContrato;
import br.com.crux.to.ContratoTO;
import br.com.crux.to.ProjetoContratoTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class ProjetoContratoTOBuilder
{

    @Autowired
    private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

    @Autowired
    private ProjetoTOBuilder projetoTOBuilder;

    public ProjetoContratoTO buildTO(ProjetoContrato entity)
    {
        ProjetoContratoTO to = new ProjetoContratoTO();

        if (Objects.isNull(entity))
            return to;

        BeanUtils.copyProperties(entity, to);
        to.setProjeto(projetoTOBuilder.buildTO(entity.getProjeto()));
        ContratoTO contrato = new ContratoTO();
        contrato.setId(entity.getContrato().getId());
        to.setContrato(contrato);
        return to;
    }

    public List<ProjetoContratoTO> buildAll(List<ProjetoContrato> lista)
    {
        return lista.stream().map(this::buildTO).collect(Collectors.toList());
    }

    public ProjetoContrato build(ProjetoContratoTO to)
    {
        ProjetoContrato entity = new ProjetoContrato();
        BeanUtils.copyProperties(to, entity);
        entity.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
        entity.setProjeto(projetoTOBuilder.build(to.getProjeto()));
        Contrato contrato = new Contrato();
        contrato.setId(to.getContrato().getId());
        entity.setContrato(contrato);
        return entity;
    }

}
