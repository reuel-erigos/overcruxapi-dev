package br.com.crux.cmd;

import br.com.crux.builder.ContratoTOBuilder;
import br.com.crux.dao.repository.ContratoRepository;
import br.com.crux.dao.repository.IndicadorMetaRepository;
import br.com.crux.dao.repository.MetaObjetivoRepository;
import br.com.crux.dao.repository.ObjetivoContratoRepository;
import br.com.crux.dao.repository.ProgramaContratoRepository;
import br.com.crux.dao.repository.ProjetoContratoRepository;
import br.com.crux.entity.Contrato;
import br.com.crux.rule.CamposObrigatoriosContratoRule;
import br.com.crux.to.ContratoTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

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
    @Autowired
    private ObjetivoContratoRepository repositoryObjetivoContrato;
    @Autowired
    private MetaObjetivoRepository     repositoryMetaObjetivo;
    @Autowired
    private IndicadorMetaRepository    repositoryIndicadorMeta;

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

        Optional.ofNullable(entity.getObjetivosContrato()).ifPresent(objetos -> objetos.forEach(o ->
        {
            o.setContrato(entity);
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
