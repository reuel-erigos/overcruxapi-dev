package br.com.crux.dao.spec;

import br.com.crux.entity.Contrato;
import br.com.crux.infra.util.Java8DateUtil;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ContratoSpec
{

    public static Specification<Contrato> findByCriteria(Long idInstituicao, Long idEmpresa, Long idPrograma, Long idProjeto,
            Long dataInicioVigencia, Long dataFimVigencia, String numeroContrato, Double valorContrato)
    {
        return (root, query, cb) ->
        {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("idInstituicao"), idInstituicao));
            if (idEmpresa != null)
                predicates.add(cb.equal(root.get("empresa").get("id"), idEmpresa));

            if (idPrograma != null)
            {
                Join<Object, Object> programasContratoJoin = root.join("programasContrato");
                predicates.add(cb.equal(programasContratoJoin.get("programa").get("id"), idPrograma));
            }
            if (idProjeto != null)
            {
                Join<Object, Object> projetosContratoJoin = root.join("projetosContrato");
                predicates.add(cb.equal(projetosContratoJoin.get("projeto").get("id"), idProjeto));
            }

            if (dataInicioVigencia != null)
                predicates.add(cb.equal(root.get("dataInicioVigencia"),
                        Java8DateUtil.getLocalDateTimeZeroTiming(new Date(dataInicioVigencia))));

            if (dataFimVigencia != null)
                predicates.add(cb.equal(root.get("dataFimVigencia"),
                        Java8DateUtil.getLocalDateTimeZeroTiming(new Date(dataFimVigencia))));

            if (!StringUtils.isEmpty(numeroContrato))
                predicates.add(cb.like(root.get("numeroContrato"), "%" + numeroContrato + "%"));

            if (valorContrato != null)
                predicates.add(cb.equal(root.get("valorContrato"), valorContrato));

            return cb.and(predicates.toArray(new Predicate[] {}));
        };
    }

}
