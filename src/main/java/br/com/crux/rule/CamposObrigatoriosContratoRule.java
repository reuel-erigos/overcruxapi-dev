package br.com.crux.rule;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.ContratoTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class CamposObrigatoriosContratoRule
{

    public void verificar(ContratoTO to)
    {
        if (to.getTipoContrato() == null || to.getTipoContrato().getId() == null)
            throw new CamposObrigatoriosException("Operação não realizada. O campo tipo de contrato deve ser informado.");

        if (StringUtils.isBlank(to.getNumeroContrato()))
            throw new CamposObrigatoriosException("Operação não realizada. O campo número do contrato deve ser informado.");

        if (to.getValorContrato() == null)
            throw new CamposObrigatoriosException("Operação não realizada. O campo valor do contrato deve ser informado.");

        if (to.getEmpresa() == null)
            throw new CamposObrigatoriosException("Operação não realizada. O campo empresa deve ser informado.");

        if (to.getDataInicioVigencia() == null)
            throw new CamposObrigatoriosException("Operação não realizada. O campo data de início deve ser informado.");

        if (StringUtils.isBlank(to.getDescricaoObjetoContrato()))
            throw new CamposObrigatoriosException("Operação não realizada. O campo descrição do objeto do contrato deve ser informado.");
    }
}
