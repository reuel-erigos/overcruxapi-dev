package br.com.crux.cmd;

import br.com.crux.builder.PercursoTOBuilder;
import br.com.crux.dao.repository.PercursoRepository;
import br.com.crux.entity.Percurso;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosPercursoRule;
import br.com.crux.to.PercursoTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AlterarPercursoCmd
{

    @Autowired
    private PercursoRepository             repository;
    @Autowired
    private PercursoTOBuilder              toBuilder;
    @Autowired
    private CamposObrigatoriosPercursoRule camposObrigatoriosRule;

    public void alterar(PercursoTO to)
    {
       repository.findById(to.getId()).orElseThrow(
                () -> new NotFoundException("Percurso informado não existe."));

        camposObrigatoriosRule.verificar(to);

        Percurso percurso = toBuilder.build(to);
        repository.save(percurso);
    }

}
