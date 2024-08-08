package br.com.crux.dao.spec;

import br.com.crux.entity.Percurso;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class PercursoSpec
{

    public static Specification<Percurso> findByCriteria(Long idInstituicao, String nome, String descricao)
    {
        return (root, query, cb) ->
        {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("idInstituicao"), idInstituicao));

            if (!StringUtils.isEmpty(nome))
                predicates.add(cb.like(cb.lower(root.get("nome")), "%" + nome.toLowerCase() + "%"));

            if (!StringUtils.isEmpty(descricao))
                predicates.add(cb.like(cb.lower(root.get("descricao")), "%" + descricao.toLowerCase() + "%"));

            return cb.and(predicates.toArray(new Predicate[] {}));
        };
    }

}
