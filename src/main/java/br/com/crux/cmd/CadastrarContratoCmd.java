package br.com.crux.cmd;

import br.com.crux.builder.ContratoTOBuilder;
import br.com.crux.dao.repository.ContratoRepository;
import br.com.crux.dao.repository.ProgramaContratoRepository;
import br.com.crux.dao.repository.ProjetoContratoRepository;
import br.com.crux.entity.Contrato;
import br.com.crux.rule.CamposObrigatoriosContratoRule;
import br.com.crux.to.ContratoTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CadastrarContratoCmd
{

    @Autowired
    private ContratoRepository             repository;
    @Autowired
    private ContratoTOBuilder              toBuilder;
    @Autowired
    private CamposObrigatoriosContratoRule camposObrigatoriosRule;

    @Autowired
    private ProgramaContratoRepository repositoryProgramaContrato;
    @Autowired
    private ProjetoContratoRepository  repositoryProjetoContrato;

    public void cadastrar(ContratoTO to)
    {
        camposObrigatoriosRule.verificar(to);
        Contrato entity = toBuilder.build(to);
        repository.save(entity);

        entity.getProgramasContrato().forEach(p ->
        {
            p.setContrato(entity);
            repositoryProgramaContrato.save(p);
        });

        entity.getProjetosContrato().forEach(p ->
        {
            p.setContrato(entity);
            repositoryProjetoContrato.save(p);
        });
    }
}
