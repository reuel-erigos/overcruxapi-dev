package br.com.crux.cmd;

import br.com.crux.builder.TiposContratosTOBuilder;
import br.com.crux.dao.repository.TiposContratosRepository;
import br.com.crux.entity.TiposContratos;
import br.com.crux.rule.CamposObrigatoriosTiposContratosRule;
import br.com.crux.to.TiposContratosTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CadastrarTiposContratosCmd
{

    @Autowired
    private TiposContratosRepository             repository;
    @Autowired
    private TiposContratosTOBuilder              toBuilder;
    @Autowired
    private CamposObrigatoriosTiposContratosRule camposObrigatoriosRule;

    public void cadastrar(TiposContratosTO to)
    {
        camposObrigatoriosRule.verificar(to);
        TiposContratos entity = toBuilder.build(to);
        repository.save(entity);

    }
}
