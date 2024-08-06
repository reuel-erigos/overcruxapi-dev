package br.com.crux.builder;

import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.dao.repository.ContratoRepository;
import br.com.crux.entity.Aluno;
import br.com.crux.entity.AlunoContrato;
import br.com.crux.entity.Contrato;
import br.com.crux.to.AlunoContratoTO;
import br.com.crux.to.AlunoTO;
import br.com.crux.to.ContratoTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class AlunoContratoTOBuilder {

    @Autowired
    private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
    @Autowired
    private ContratoTOBuilder contratoTOBuilder;
    @Autowired
    private ContratoRepository contratoRepository;

    public AlunoContratoTO buildTO(AlunoContrato entity) {
        AlunoContratoTO to = new AlunoContratoTO();

        if (Objects.isNull(entity)) return to;

        BeanUtils.copyProperties(entity, to);

        Contrato contrato = entity.getContrato();
        if (contrato == null) {
            return to;
        }
        contrato = contratoRepository.findById(contrato.getId()).orElse(null);
        ContratoTO contratoTO = contratoTOBuilder.buildTO(contrato);
        to.setContrato(contratoTO);

        AlunoTO aluno = new AlunoTO();
        aluno.setId(entity.getAluno().getId());
        to.setAluno(aluno);


        return to;
    }

    public List<AlunoContratoTO> buildAll(List<AlunoContrato> lista) {
        return lista.stream().map(this::buildTO).collect(Collectors.toList());
    }

    public AlunoContrato build(AlunoContratoTO to) {
        AlunoContrato entity = new AlunoContrato();
        BeanUtils.copyProperties(to, entity);
        entity.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
        Contrato contrato = new Contrato();
        contrato.setId(to.getContrato().getId());
        entity.setContrato(contrato);

        Aluno aluno = new Aluno();
        aluno.setId(to.getAluno().getId());
        entity.setAluno(aluno);

        return entity;
    }

}
