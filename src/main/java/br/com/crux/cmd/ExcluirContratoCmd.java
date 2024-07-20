package br.com.crux.cmd;

import br.com.crux.dao.repository.ContratoRepository;
import br.com.crux.dao.repository.ProgramaContratoRepository;
import br.com.crux.dao.repository.ProjetoContratoRepository;
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

    public void excluir(Long id)
    {
        if (Objects.isNull(id))
            throw new ParametroNaoInformadoException("Erro ao excluir. Parâmetro ausente.");

        repository.findById(id)
                .orElseThrow(() -> new ParametroNaoInformadoException("Erro ao excluir. Registro não encontrado."));

        try
        {
            programaContratoRepository.deleteByContratoId(id);
            projetoContratoRepository.deleteByContratoId(id);
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
}
