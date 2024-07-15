package br.com.crux.cmd;

import br.com.crux.builder.TipoContratoTOBuilder;
import br.com.crux.dao.repository.TipoContratoRepository;
import br.com.crux.entity.TipoContrato;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosTipoContratoRule;
import br.com.crux.to.TipoContratoTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AlterarTipoContratoCmd
{

    @Autowired
    private TipoContratoRepository               repository;
    @Autowired
    private TipoContratoTOBuilder              toBuilder;
    @Autowired
    private CamposObrigatoriosTipoContratoRule camposObrigatoriosRule;

    public void alterar(TipoContratoTO to)
    {
        Optional<TipoContrato> entityOptional = repository.findById(to.getId());

        if (!entityOptional.isPresent())
        {
            throw new NotFoundException("Tipo de contrato informado não existe.");
        }

        camposObrigatoriosRule.verificar(to);

        TipoContrato entity = entityOptional.get();

        entity = toBuilder.build(to);

        repository.save(entity);

    }
}
