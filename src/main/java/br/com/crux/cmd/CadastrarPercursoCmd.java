package br.com.crux.cmd;

import br.com.crux.builder.PercursoTOBuilder;
import br.com.crux.dao.repository.PercursoRepository;
import br.com.crux.entity.Percurso;
import br.com.crux.rule.CamposObrigatoriosPercursoRule;
import br.com.crux.to.PercursoTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CadastrarPercursoCmd
{

    @Autowired
    private PercursoRepository             repository;
    @Autowired
    private PercursoTOBuilder              toBuilder;
    @Autowired
    private CamposObrigatoriosPercursoRule camposObrigatoriosRule;

    public void cadastrar(PercursoTO to)
    {
        camposObrigatoriosRule.verificar(to);
        Percurso entity = toBuilder.build(to);
        repository.save(entity);
    }

}
