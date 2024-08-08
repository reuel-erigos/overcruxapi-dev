package br.com.crux.cmd;

import br.com.crux.dao.repository.PercursoRepository;
import br.com.crux.entity.Percurso;
import br.com.crux.exception.ParametroNaoInformadoException;
import br.com.crux.exception.TabaleReferenciaEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ExcluirPercursoCmd
{

    @Autowired
    private PercursoRepository repository;

    public void excluir(Long id)
    {
        if (Objects.isNull(id))
            throw new ParametroNaoInformadoException("Erro ao excluir. Parâmetro ausente.");

        Percurso contrato = repository.findById(id)
                .orElseThrow(() -> new ParametroNaoInformadoException("Erro ao excluir. Registro não encontrado."));

        try
        {
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
