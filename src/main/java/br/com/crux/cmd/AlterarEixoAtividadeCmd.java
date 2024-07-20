package br.com.crux.cmd;

import br.com.crux.builder.EixoAtividadeTOBuilder;
import br.com.crux.dao.repository.EixoAtividadeRepository;
import br.com.crux.entity.EixoAtividade;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosEixoAtividadeRule;
import br.com.crux.to.EixoAtividadeTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AlterarEixoAtividadeCmd
{

    @Autowired
    private EixoAtividadeRepository             repository;
    @Autowired
    private EixoAtividadeTOBuilder              toBuilder;
    @Autowired
    private CamposObrigatoriosEixoAtividadeRule camposObrigatoriosRule;

    public void alterar(EixoAtividadeTO to)
    {
        Optional<EixoAtividade> entityOptional = repository.findById(to.getId());

        if (!entityOptional.isPresent())
            throw new NotFoundException("Eixo de atividade não encontrado.");

        camposObrigatoriosRule.verificar(to);
        EixoAtividade entity = toBuilder.build(to);
        repository.save(entity);

    }
}
