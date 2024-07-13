package br.com.crux.cmd;

import br.com.crux.dao.repository.TiposContratosRepository;
import br.com.crux.exception.ParametroNaoInformadoException;
import br.com.crux.exception.TabaleReferenciaEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ExcluirTiposContratosCmd
{

    @Autowired
    private TiposContratosRepository repository;

    public void excluir(Long id)
    {
        try
        {
            if (Objects.isNull(id))
            {
                throw new ParametroNaoInformadoException("Erro ao excluir. Parâmetro ausente.");
            }
            repository.deleteById(id);
        } catch (Exception e)
        {
            if (Objects.nonNull(e.getCause()))
            {
                if (e.getCause() instanceof DataIntegrityViolationException || e.getCause().toString()
                        .contains("ConstraintViolationException"))
                {
                    throw new TabaleReferenciaEncontradaException(
                            "Erro ao excluir, verifique se há outro cadastro com referência com esse registro.");
                }
            }

            throw new RuntimeException(e.getMessage());
        }

    }
}
