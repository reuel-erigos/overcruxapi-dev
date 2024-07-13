package br.com.crux.cmd;

import br.com.crux.builder.TiposContratosTOBuilder;
import br.com.crux.dao.repository.TiposContratosRepository;
import br.com.crux.entity.TiposContratos;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.TiposContratosTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class GetTiposContratosCmd
{

    @Autowired
    private TiposContratosRepository repository;
    @Autowired
    private GetUnidadeLogadaCmd      getUnidadeLogadaCmd;
    @Autowired
    private TiposContratosTOBuilder  toBuilder;

    public List<TiposContratosTO> getAllTO()
    {
        Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();

        Optional<List<TiposContratos>> lista = repository.findByIdInstituicao(idInstituicao);
        if (lista.isPresent())
        {
            return toBuilder.buildAll(lista.get());
        }
        return new ArrayList<>();
    }

    public TiposContratos getById(Long id)
    {
        return repository.findById(id).orElse(null);
    }

    public TiposContratosTO getTOById(Long id)
    {
        TiposContratos entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tipo de contrato informado não existe."));
        return toBuilder.buildTO(entity);
    }

    public List<TiposContratosTO> getTOByDescricao(String descricao)
    {
        if (descricao != null)
            descricao = descricao.toLowerCase().trim();

        Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
        Optional<List<TiposContratos>> lista = repository.findByIdInstituicaoAndDescricao(idInstituicao, "%" + descricao + "%");
        if (lista.isPresent())
            return toBuilder.buildAll(lista.get());

        return new ArrayList<>();
    }
}
