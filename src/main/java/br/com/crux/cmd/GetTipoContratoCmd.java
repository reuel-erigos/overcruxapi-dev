package br.com.crux.cmd;

import br.com.crux.builder.TipoContratoTOBuilder;
import br.com.crux.dao.repository.TipoContratoRepository;
import br.com.crux.entity.TipoContrato;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.TipoContratoTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class GetTipoContratoCmd
{

    @Autowired
    private TipoContratoRepository repository;
    @Autowired
    private GetUnidadeLogadaCmd    getUnidadeLogadaCmd;
    @Autowired
    private TipoContratoTOBuilder  toBuilder;

    public List<TipoContratoTO> getAllTO()
    {
        Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();

        Optional<List<TipoContrato>> lista = repository.findByIdInstituicao(idInstituicao);
        if (lista.isPresent())
        {
            return toBuilder.buildAll(lista.get());
        }
        return new ArrayList<>();
    }

    public TipoContrato getById(Long id)
    {
        return repository.findById(id).orElse(null);
    }

    public TipoContratoTO getTOById(Long id)
    {
        TipoContrato entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tipo de contrato informado não existe."));
        return toBuilder.buildTO(entity);
    }

    public List<TipoContratoTO> getTOByDescricao(String descricao)
    {
        if (descricao != null)
            descricao = descricao.toLowerCase().trim();

        Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
        Optional<List<TipoContrato>> lista = repository.findByIdInstituicaoAndDescricao(idInstituicao, "%" + descricao + "%");
        if (lista.isPresent())
            return toBuilder.buildAll(lista.get());

        return new ArrayList<>();
    }
}
