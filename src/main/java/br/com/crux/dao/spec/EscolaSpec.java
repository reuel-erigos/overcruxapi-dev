package br.com.crux.dao.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import br.com.crux.entity.Escola;
import br.com.crux.entity.Instituicao;
import br.com.crux.to.filtro.FiltroEscolaTO;

@Component
public class EscolaSpec{

	public static Specification<Escola> findByCriteria(FiltroEscolaTO criteria) {
		return new Specification<Escola>() {

			private static final long serialVersionUID = -7597187532438443858L;

			@Override
			public Predicate toPredicate(Root<Escola> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				if(!StringUtils.isEmpty(criteria.getNome())) {
					predicates.add(cb.like(cb.upper(root.get("nome")), criteria.getNome().toUpperCase() + "%"));
				}
				if(!StringUtils.isEmpty(criteria.getTipo())) {
					predicates.add(cb.equal(cb.upper(root.get("tipo")), criteria.getTipo().toUpperCase()));
				}
				if(!StringUtils.isEmpty(criteria.getIdInstituicao())) {
					Join<Escola, Instituicao> join = root.join("instituicao");
					predicates.add(cb.equal(join.get("id"), criteria.getIdInstituicao()));
				}
				return cb.and(predicates.toArray(new Predicate[] {}));
			}
		};
	}

}
