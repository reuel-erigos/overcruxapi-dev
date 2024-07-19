package br.com.crux.cmd;

import br.com.crux.builder.PeriodoAtividadeTOBuilder;
import br.com.crux.dao.repository.PeriodoAtividadeRepository;
import br.com.crux.entity.PeriodoAtividade;
import br.com.crux.rule.CamposObrigatoriosPeriodoAtividadeRule;
import br.com.crux.to.PeriodoAtividadeTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CadastrarPeriodoAtividadeCmd
{

    @Autowired
    private PeriodoAtividadeRepository             repository;
    @Autowired
    private PeriodoAtividadeTOBuilder              toBuilder;
    @Autowired
    private CamposObrigatoriosPeriodoAtividadeRule camposObrigatoriosRule;

    public void cadastrar(PeriodoAtividadeTO to)
    {
        camposObrigatoriosRule.verificar(to);
        PeriodoAtividade entity = toBuilder.build(to);
        repository.save(entity);

    }

}
