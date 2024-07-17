package br.com.crux.cmd;

import br.com.crux.builder.ContratoTOBuilder;
import br.com.crux.dao.repository.ContratoRepository;
import br.com.crux.dao.repository.ProgramaContratoRepository;
import br.com.crux.dao.repository.ProjetoContratoRepository;
import br.com.crux.entity.Contrato;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosContratoRule;
import br.com.crux.to.ContratoTO;
import br.com.crux.to.ProgramaContratoTO;
import br.com.crux.to.ProjetoContratoTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AlterarContratoCmd
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

    public void alterar(ContratoTO to)
    {
        Contrato entity = repository.findById(to.getId())
                .orElseThrow(() -> new NotFoundException("Contrato informado não existe."));

        camposObrigatoriosRule.verificar(to);

        Set<Long> updatedProgramaIds = to.getProgramasContrato().stream().map(ProgramaContratoTO::getId)
                .collect(Collectors.toSet());
        Set<Long> updateProjetoIds = to.getProjetosContrato().stream().map(ProjetoContratoTO::getId).collect(Collectors.toSet());

        entity.getProgramasContrato().removeIf(p ->
        {
            if (!updatedProgramaIds.contains(p.getId()))
            {
                repositoryProgramaContrato.deleteById(p.getId());
                return true;
            }
            return false;
        });

        entity.getProjetosContrato().removeIf(p ->
        {
            if (!updateProjetoIds.contains(p.getId()))
            {
                repositoryProjetoContrato.deleteById(p.getId());
                return true;
            }
            return false;
        });

        Contrato updatedEntity = toBuilder.build(to);
        repository.save(updatedEntity);

        updatedEntity.getProgramasContrato().forEach(repositoryProgramaContrato::save);
        updatedEntity.getProjetosContrato().forEach(repositoryProjetoContrato::save);
    }
}
