package br.com.crux.cmd;

import br.com.crux.builder.TiposContratosTOBuilder;
import br.com.crux.dao.repository.TiposContratosRepository;
import br.com.crux.entity.TiposContratos;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosTiposContratosRule;
import br.com.crux.to.TiposContratosTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AlterarTiposContratosCmd
{

    @Autowired
    private TiposContratosRepository             repository;
    @Autowired
    private TiposContratosTOBuilder              toBuilder;
    @Autowired
    private CamposObrigatoriosTiposContratosRule camposObrigatoriosRule;

    public void alterar(TiposContratosTO to)
    {
        Optional<TiposContratos> entityOptional = repository.findById(to.getId());

        if (!entityOptional.isPresent())
        {
            throw new NotFoundException("Tipo de contrato informado não existe.");
        }

        camposObrigatoriosRule.verificar(to);

        TiposContratos entity = entityOptional.get();

        entity = toBuilder.build(to);

        repository.save(entity);

    }
}
