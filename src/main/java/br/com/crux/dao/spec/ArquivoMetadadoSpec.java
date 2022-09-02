package br.com.crux.dao.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import br.com.crux.entity.ArquivoMetadado;
import br.com.crux.to.filtro.FiltroArquivoTO;

@Component
public class ArquivoMetadadoSpec{

	public static Specification<ArquivoMetadado> findByCriteria(FiltroArquivoTO criteria) {
		return new Specification<ArquivoMetadado>() {

			private static final long serialVersionUID = -7597187532438443858L;

			@Override
			public Predicate toPredicate(Root<ArquivoMetadado> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				if(!StringUtils.isEmpty(criteria.getTipo())) {
					predicates.add(cb.equal(root.get("tipo"), criteria.getTipo().toUpperCase()));
				}
				if(!CollectionUtils.isEmpty(criteria.getTipos())) {
					Expression<String> inTipos = root.get("tipo");
					predicates.add(inTipos.in(criteria.getTipos()));
				}
				
				return cb.and(predicates.toArray(new Predicate[] {}));
			}
		};
	}

}
