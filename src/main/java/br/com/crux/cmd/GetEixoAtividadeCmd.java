package br.com.crux.cmd;

import br.com.crux.builder.EixoAtividadeTOBuilder;
import br.com.crux.dao.repository.EixoAtividadeRepository;
import br.com.crux.entity.EixoAtividade;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.EixoAtividadeTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class GetEixoAtividadeCmd
{

    @Autowired
    private EixoAtividadeRepository repository;
    @Autowired
    private GetUnidadeLogadaCmd     getUnidadeLogadaCmd;
    @Autowired
    private EixoAtividadeTOBuilder  toBuilder;

    public List<EixoAtividadeTO> getAllTO()
    {
        Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();

        Optional<List<EixoAtividade>> lista = repository.findByIdInstituicao(idInstituicao);
        if (lista.isPresent())
        {
            return toBuilder.buildAll(lista.get());
        }
        return new ArrayList<EixoAtividadeTO>();
    }

    public EixoAtividade getById(Long id)
    {
        return repository.findById(id).orElse(null);
    }

    public EixoAtividadeTO getTOById(Long id)
    {
        EixoAtividade entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Eixo de atividade não encontrado."));
        return toBuilder.buildTO(entity);
    }

    public List<EixoAtividadeTO> findByFilters(String nome, String descricao)
    {
        nome = Objects.isNull(nome) ? null : nome.trim().toLowerCase();
        descricao = Objects.isNull(descricao) ? null : descricao.trim().toLowerCase();

        Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();

        Optional<List<EixoAtividade>> entitys = repository.findByFilters(idInstituicao, "%" + nome + "%", "%" + descricao + "%");

        if (entitys.isPresent())
            return toBuilder.buildAll(entitys.get());

        return new ArrayList<>();
    }
}
