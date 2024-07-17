package br.com.crux.cmd;

import br.com.crux.builder.ContratoTOBuilder;
import br.com.crux.dao.repository.ContratoRepository;
import br.com.crux.dao.spec.ContratoSpec;
import br.com.crux.entity.Contrato;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.ContratoTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class GetContratoCmd
{

    @Autowired
    private ContratoRepository  repository;
    @Autowired
    private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
    @Autowired
    private ContratoTOBuilder   toBuilder;

    public List<ContratoTO> getAllTO()
    {
        Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
        Optional<List<Contrato>> lista = repository.findByIdInstituicao(idInstituicao);
        if (lista.isPresent())
            return toBuilder.buildAll(lista.get());

        return new ArrayList<>();
    }

    public Contrato getById(Long id)
    {
        return repository.findById(id).orElse(null);
    }

    public ContratoTO getTOById(Long id)
    {
        Contrato entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Contrato não encontrado."));
        return toBuilder.buildTO(entity);
    }

    public List<ContratoTO> findByFilters(Long idEmpresa, Long idPrograma, Long idProjeto, LocalDateTime dataInicioVigencia,
            LocalDateTime dataFimVigencia, String numeroContrato, Double valorContrato)
    {
        idEmpresa = Objects.isNull(idEmpresa) ? null : idEmpresa;
        idPrograma = Objects.isNull(idPrograma) ? null : idPrograma;
        idProjeto = Objects.isNull(idProjeto) ? null : idProjeto;
        numeroContrato = StringUtils.isBlank(numeroContrato) ? null : numeroContrato;
        valorContrato = Objects.isNull(valorContrato) ? null : valorContrato;

        Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();

        List<Contrato> lista = repository.findAll(
                ContratoSpec.findByCriteria(idInstituicao, idEmpresa, idPrograma, idProjeto, dataInicioVigencia, dataFimVigencia,
                        numeroContrato, valorContrato));
        return toBuilder.buildAll(lista);
    }

}
