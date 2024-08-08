package br.com.crux.rule;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.PercursoTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class CamposObrigatoriosPercursoRule
{

    public void verificar(PercursoTO to)
    {
        if (StringUtils.isBlank(to.getNome()))
            throw new CamposObrigatoriosException("Nome do percurso deve ser informado.");

        if (StringUtils.isBlank(to.getDescricao()))
            throw new CamposObrigatoriosException("Descrição do percurso deve ser informado.");
    }
}
