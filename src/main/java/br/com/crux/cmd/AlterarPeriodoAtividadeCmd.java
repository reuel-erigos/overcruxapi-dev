package br.com.crux.cmd;

import br.com.crux.builder.PeriodoAtividadeTOBuilder;
import br.com.crux.dao.repository.PeriodoAtividadeRepository;
import br.com.crux.entity.PeriodoAtividade;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosPeriodoAtividadeRule;
import br.com.crux.to.PeriodoAtividadeTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AlterarPeriodoAtividadeCmd
{

    @Autowired
    private PeriodoAtividadeRepository             repository;
    @Autowired
    private PeriodoAtividadeTOBuilder              toBuilder;
    @Autowired
    private CamposObrigatoriosPeriodoAtividadeRule camposObrigatoriosRule;

    public void alterar(PeriodoAtividadeTO to)
    {
        Optional<PeriodoAtividade> entityOptional = repository.findById(to.getId());

        if (!entityOptional.isPresent())
            throw new NotFoundException("Período de atividade não encontrado.");

        camposObrigatoriosRule.verificar(to);
        PeriodoAtividade entity = toBuilder.build(to);
        repository.save(entity);

    }

}
