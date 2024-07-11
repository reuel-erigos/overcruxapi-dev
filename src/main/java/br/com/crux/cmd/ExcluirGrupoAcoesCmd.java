package br.com.crux.cmd;

import br.com.crux.dao.repository.AcaoRepository;
import br.com.crux.dao.repository.GrupoAcoesRepository;
import br.com.crux.entity.Acoes;
import br.com.crux.exception.ParametroNaoInformadoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class ExcluirGrupoAcoesCmd
{

    @Autowired
    private GrupoAcoesRepository grupoAcoesRepository;
    @Autowired
    private AcaoRepository       acaoRepository;

    public void excluir(Long id)
    {
        if (Objects.isNull(id))
            throw new ParametroNaoInformadoException("Erro ao excluir a Grupo de Ações. Parâmetro ausente.");

        Optional<List<Acoes>> acoes = acaoRepository.findByGrupoAcoesId(id);
        if (acoes.isPresent())
            acoes.get().forEach(acao -> acaoRepository.deleteById(acao.getId()));

        grupoAcoesRepository.deleteById(id);
    }
}
