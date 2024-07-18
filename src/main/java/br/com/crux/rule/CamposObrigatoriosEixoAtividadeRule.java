package br.com.crux.rule;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.EixoAtividadeTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class CamposObrigatoriosEixoAtividadeRule
{

    public void verificar(EixoAtividadeTO to)
    {
        if (StringUtils.isEmpty(to.getNome()))
            throw new CamposObrigatoriosException("Operação não realizada. O campo nome deve ser informado.");

        if (StringUtils.isEmpty(to.getDescricao()))
            throw new CamposObrigatoriosException("Operação não realizada. O campo descrição deve ser informado.");

    }
}
