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

import br.com.crux.entity.Aluno;
import br.com.crux.entity.PessoaFisica;
import br.com.crux.to.filtro.FiltroAlunoTO;

@Component
public class AlunoSpec{

	public static Specification<Aluno> findByCriteria(FiltroAlunoTO criteria) {
		return new Specification<Aluno>() {

			private static final long serialVersionUID = -7597187532438443858L;

			@Override
			public Predicate toPredicate(Root<Aluno> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				if(!StringUtils.isEmpty(criteria.getBeneficiario())) {
					Join<Aluno, PessoaFisica> join = root.join("pessoasFisica");
					predicates.add(cb.like(cb.upper(join.get("nome")), criteria.getBeneficiario().toUpperCase() + "%"));
				}
				if(!StringUtils.isEmpty(criteria.getMae())) {
					Join<Aluno, PessoaFisica> join = root.join("pessoasFisica");
					predicates.add(cb.like(cb.upper(join.get("nomeMae")), criteria.getMae().toUpperCase() + "%"));
				}
				if(!StringUtils.isEmpty(criteria.getCpf())) {
					Join<Aluno, PessoaFisica> join = root.join("pessoasFisica");
					predicates.add(cb.equal(join.get("cpf"), criteria.getCpf().toUpperCase()));
				}
				if(criteria.getDataInicioEntradaInstituicao() != null) {
					predicates.add(cb.greaterThanOrEqualTo(root.get("dataEntrada"), criteria.getDataInicioEntradaInstituicao()));
				}
				if(criteria.getDataFimEntradaInstituicao() != null) {
					predicates.add(cb.lessThanOrEqualTo(root.get("dataDesligamento"), criteria.getDataFimEntradaInstituicao()));
				}
				return cb.and(predicates.toArray(new Predicate[] {}));
			}
		};
	}

}
