package br.com.crux.cmd;

import br.com.crux.builder.PercursoTOBuilder;
import br.com.crux.dao.repository.PercursoRepository;
import br.com.crux.dao.spec.PercursoSpec;
import br.com.crux.entity.Percurso;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.PercursoTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class GetPercursoCmd
{

    @Autowired
    private PercursoRepository  repository;
    @Autowired
    private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
    @Autowired
    private PercursoTOBuilder   toBuilder;

    public List<PercursoTO> getAllTO()
    {
        Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();

        Optional<List<Percurso>> lista = repository.findByIdInstituicao(idInstituicao);
        if (lista.isPresent())
            return toBuilder.buildAll(lista.get());

        return new ArrayList<>();
    }

    public Percurso getById(Long id)
    {
        return repository.findById(id).orElse(null);
    }

    public PercursoTO getTOById(Long id)
    {
        Percurso entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Percurso não encontrado."));
        return toBuilder.buildTO(entity);
    }

    public List<PercursoTO> findByFilters(String nome, String descricao)
    {
        nome = StringUtils.isBlank(nome) ? null : nome;
        descricao = StringUtils.isBlank(descricao) ? null : descricao;

        Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();

        List<Percurso> lista = repository.findAll(PercursoSpec.findByCriteria(idInstituicao, nome, descricao));
        return toBuilder.buildAll(lista);
    }

}
