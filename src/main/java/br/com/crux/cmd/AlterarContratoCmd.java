package br.com.crux.cmd;

import br.com.crux.builder.ContratoTOBuilder;
import br.com.crux.dao.repository.ContratoRepository;
import br.com.crux.dao.repository.IndicadorMetaRepository;
import br.com.crux.dao.repository.MetaObjetivoRepository;
import br.com.crux.dao.repository.ObjetivoContratoRepository;
import br.com.crux.dao.repository.ProgramaContratoRepository;
import br.com.crux.dao.repository.ProjetoContratoRepository;
import br.com.crux.entity.Contrato;
import br.com.crux.entity.ObjetivoContrato;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosContratoRule;
import br.com.crux.to.ContratoTO;
import br.com.crux.to.IndicadorMetaTO;
import br.com.crux.to.MetaObjetivoTO;
import br.com.crux.to.ObjetivoContratoTO;
import br.com.crux.to.ProgramaContratoTO;
import br.com.crux.to.ProjetoContratoTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
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
    @Autowired
    private ObjetivoContratoRepository repositoryObjetivoContrato;
    @Autowired
    private MetaObjetivoRepository     repositoryMetaObjetivo;
    @Autowired
    private IndicadorMetaRepository    repositoryIndicadorMeta;

    public void alterar(ContratoTO to)
    {
        Contrato entity = repository.findById(to.getId())
                .orElseThrow(() -> new NotFoundException("Contrato informado não existe."));

        camposObrigatoriosRule.verificar(to);

        Set<Long> updatedProgramaIds = to.getProgramasContrato().stream().map(ProgramaContratoTO::getId)
                .collect(Collectors.toSet());
        Set<Long> updateProjetoIds = to.getProjetosContrato().stream().map(ProjetoContratoTO::getId).collect(Collectors.toSet());

        Set<Long> updatedObjetivoIds = new HashSet<>();
        Set<Long> updatedMetaIds = new HashSet<>();
        Set<Long> updatedIndicadorIds = new HashSet<>();
        for (ObjetivoContratoTO o : to.getObjetivosContrato())
        {
            if (o.getId() != null)
            {
                updatedObjetivoIds.add(o.getId());
                for (MetaObjetivoTO m : o.getMetasObjetivo())
                {
                    if (m.getId() != null)
                    {
                        updatedMetaIds.add(m.getId());
                        for (IndicadorMetaTO i : m.getIndicadoresMeta())
                        {
                            if (i.getId() != null)
                                updatedIndicadorIds.add(i.getId());
                        }
                    }
                }
            }
        }

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

        for (ObjetivoContrato o : entity.getObjetivosContrato())
        {
            o.getMetasObjetivo().forEach(m ->
            {
                m.getIndicadoresMeta().removeIf(i ->
                {
                    if (!updatedIndicadorIds.contains(i.getId()))
                    {
                        repositoryIndicadorMeta.deleteById(i.getId());
                        return true;
                    }
                    return false;
                });
            });

            o.getMetasObjetivo().removeIf(m ->
            {
                if (!updatedMetaIds.contains(m.getId()))
                {
                    repositoryMetaObjetivo.deleteById(m.getId());
                    return true;
                }
                return false;
            });
        }

        entity.getObjetivosContrato().removeIf(o ->
        {
            if (!updatedObjetivoIds.contains(o.getId()))
            {
                repositoryObjetivoContrato.deleteById(o.getId());
                return true;
            }
            return false;
        });

        Contrato updatedEntity = toBuilder.build(to);
        repository.save(updatedEntity);

        updatedEntity.getProgramasContrato().forEach(repositoryProgramaContrato::save);
        updatedEntity.getProjetosContrato().forEach(repositoryProjetoContrato::save);

        Optional.ofNullable(updatedEntity.getObjetivosContrato()).ifPresent(objetos -> objetos.forEach(o ->
        {
            o.setContrato(updatedEntity);
            repositoryObjetivoContrato.save(o);
            Optional.ofNullable(o.getMetasObjetivo()).ifPresent(metas -> metas.forEach(m ->
            {
                m.setObjetivoContrato(o);
                repositoryMetaObjetivo.save(m);
                Optional.ofNullable(m.getIndicadoresMeta()).ifPresent(indicadores -> indicadores.forEach(i ->
                {
                    i.setMetaObjetivo(m);
                    repositoryIndicadorMeta.save(i);
                }));
            }));
        }));
    }

}
