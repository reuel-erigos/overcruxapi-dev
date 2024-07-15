package br.com.crux.rule;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.TipoContratoTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class CamposObrigatoriosTipoContratoRule
{

    public void verificar(TipoContratoTO to)
    {
        if (StringUtils.isEmpty(to.getDescricao()))
        {
            throw new CamposObrigatoriosException("Operação não realizada. O campo descrição deve ser informado.");
        }

    }
}
