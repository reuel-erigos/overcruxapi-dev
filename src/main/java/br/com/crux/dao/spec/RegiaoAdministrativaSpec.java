package br.com.crux.dao.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import br.com.crux.entity.RegiaoAdministrativa;
import br.com.crux.to.filtro.FiltroRegiaoAdministrativaTO;

@Component
public class RegiaoAdministrativaSpec{

	public static Specification<RegiaoAdministrativa> findByCriteria(FiltroRegiaoAdministrativaTO criteria) {
		return new Specification<RegiaoAdministrativa>() {

			private static final long serialVersionUID = -7597187532438443858L;

			@Override
			public Predicate toPredicate(Root<RegiaoAdministrativa> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				if(!StringUtils.isEmpty(criteria.getNome())) {
					predicates.add(cb.like(cb.upper(root.get("nome")), criteria.getNome().toUpperCase() + "%"));
				}
				return cb.and(predicates.toArray(new Predicate[] {}));
			}
		};
	}

}
