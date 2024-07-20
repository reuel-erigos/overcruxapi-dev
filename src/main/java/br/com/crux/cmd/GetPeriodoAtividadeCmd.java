package br.com.crux.cmd;

import br.com.crux.builder.PeriodoAtividadeTOBuilder;
import br.com.crux.dao.repository.PeriodoAtividadeRepository;
import br.com.crux.entity.PeriodoAtividade;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.PeriodoAtividadeTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class GetPeriodoAtividadeCmd
{

    @Autowired
    private PeriodoAtividadeRepository repository;
    @Autowired
    private GetUnidadeLogadaCmd        getUnidadeLogadaCmd;
    @Autowired
    private PeriodoAtividadeTOBuilder  toBuilder;

    public List<PeriodoAtividadeTO> getAllTO()
    {
        Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();

        Optional<List<PeriodoAtividade>> lista = repository.findByIdInstituicao(idInstituicao);
        if (lista.isPresent())
        {
            return toBuilder.buildAll(lista.get());
        }
        return new ArrayList<PeriodoAtividadeTO>();
    }

    public PeriodoAtividade getById(Long id)
    {
        return repository.findById(id).orElse(null);
    }

    public PeriodoAtividadeTO getTOById(Long id)
    {
        PeriodoAtividade entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Periodo de atividade não encontrado."));
        return toBuilder.buildTO(entity);
    }

    public List<PeriodoAtividadeTO> findByFilters(String nome, String descricao)
    {
        nome = Objects.isNull(nome) ? null : nome.trim().toLowerCase();
        descricao = Objects.isNull(descricao) ? null : descricao.trim().toLowerCase();

        Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();

        Optional<List<PeriodoAtividade>> entitys = repository.findByFilters(idInstituicao, "%" + nome + "%",
                "%" + descricao + "%");

        if (entitys.isPresent())
            return toBuilder.buildAll(entitys.get());

        return new ArrayList<>();
    }

}
