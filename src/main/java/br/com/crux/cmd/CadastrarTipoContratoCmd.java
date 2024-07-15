package br.com.crux.cmd;

import br.com.crux.builder.TipoContratoTOBuilder;
import br.com.crux.dao.repository.TipoContratoRepository;
import br.com.crux.entity.TipoContrato;
import br.com.crux.rule.CamposObrigatoriosTipoContratoRule;
import br.com.crux.to.TipoContratoTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CadastrarTipoContratoCmd
{

    @Autowired
    private TipoContratoRepository               repository;
    @Autowired
    private TipoContratoTOBuilder              toBuilder;
    @Autowired
    private CamposObrigatoriosTipoContratoRule camposObrigatoriosRule;

    public void cadastrar(TipoContratoTO to)
    {
        camposObrigatoriosRule.verificar(to);
        TipoContrato entity = toBuilder.build(to);
        repository.save(entity);

    }
}
