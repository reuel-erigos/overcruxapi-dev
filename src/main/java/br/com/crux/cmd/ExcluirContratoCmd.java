package br.com.crux.cmd;

import br.com.crux.dao.repository.*;
import br.com.crux.entity.Contrato;
import br.com.crux.entity.IndicadorMeta;
import br.com.crux.entity.MetaObjetivo;
import br.com.crux.entity.ObjetivoContrato;
import br.com.crux.exception.ParametroNaoInformadoException;
import br.com.crux.exception.TabaleReferenciaEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ExcluirContratoCmd
{

    @Autowired
    private ContratoRepository         repository;
    @Autowired
    private ProgramaContratoRepository programaContratoRepository;
    @Autowired
    private ProjetoContratoRepository  projetoContratoRepository;
    @Autowired
    private ObjetivoContratoRepository objetivoContratoRepository;
    @Autowired
    private MetaObjetivoRepository     metaObjetivoRepository;
    @Autowired
    private IndicadorMetaRepository    indicadorMetaRepository;

    public void excluir(Long id)
    {
        if (Objects.isNull(id))
            throw new ParametroNaoInformadoException("Erro ao excluir. Parâmetro ausente.");

        Contrato contrato = repository.findById(id)
                .orElseThrow(() -> new ParametroNaoInformadoException("Erro ao excluir. Registro não encontrado."));

        try
        {
            programaContratoRepository.deleteByContratoId(id);
            projetoContratoRepository.deleteByContratoId(id);
            excluirObjetivos(id, contrato);
            repository.deleteById(id);
        } catch (Exception e)
        {
            if (e.getCause() instanceof DataIntegrityViolationException || (e.getCause() != null && e.getCause().toString()
                    .contains("ConstraintViolationException")))
            {
                throw new TabaleReferenciaEncontradaException(
                        "Erro ao excluir, verifique se há outro cadastro com referência com esse registro.");
            }
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private void excluirObjetivos(Long id, Contrato contrato) {
        for (ObjetivoContrato o : contrato.getObjetivosContrato()) {
            for (MetaObjetivo m : o.getMetasObjetivo()) {
                indicadorMetaRepository.deleteByMetaObjetivoId(m.getId());
            }
            metaObjetivoRepository.deleteByObjetivoContratoId(o.getId());
        }
        objetivoContratoRepository.deleteByContratoId(id);
    }
}
