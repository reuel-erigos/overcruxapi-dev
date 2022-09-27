package br.com.crux.dao.spec;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import br.com.crux.entity.Aluno;
import br.com.crux.entity.AlunosTurma;
import br.com.crux.entity.Instituicao;
import br.com.crux.entity.Oficinas;
import br.com.crux.entity.PessoaFisica;
import br.com.crux.entity.Turmas;
import br.com.crux.entity.Unidade;
import br.com.crux.to.filtro.FiltroAlunoTurmaTO;

@Component
public class AlunoTurmaSpec{

	public static Specification<AlunosTurma> findByCriteria(FiltroAlunoTurmaTO criteria) {
		return new Specification<AlunosTurma>() {

			private static final long serialVersionUID = -7597187532438443858L;

			@Override
			public Predicate toPredicate(Root<AlunosTurma> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				if(!StringUtils.isEmpty(criteria.getBeneficiario())) {
					Join<AlunosTurma, Aluno> join = root.join("aluno");
					Join<Aluno, PessoaFisica> joinPessoaFisica = join.join("pessoasFisica");
					predicates.add(cb.like(cb.upper(joinPessoaFisica.get("nome")), criteria.getBeneficiario().toUpperCase() + "%"));
				}
				if(!StringUtils.isEmpty(criteria.getCpf())) {
					Join<AlunosTurma, Aluno> join = root.join("aluno");
					Join<Aluno, PessoaFisica> joinPessoaFisica = join.join("pessoasFisica");
					predicates.add(cb.equal(joinPessoaFisica.get("cpf"), criteria.getCpf().toUpperCase()));
				}
				if(Objects.nonNull(criteria.getIdTurma())) {
					Join<AlunosTurma, Turmas> join = root.join("turma");
					predicates.add(cb.equal(join.get("id"), criteria.getIdTurma()));
				}
				if(Objects.nonNull(criteria.getIdOficina())) {
					Join<AlunosTurma, Turmas> join = root.join("turma");
					Join<Turmas, Oficinas> joinOficinas = join.join("atividades");
					predicates.add(cb.equal(joinOficinas.get("id"), criteria.getIdOficina()));
				}
				if(Objects.nonNull(criteria.getIdUnidade())) {
					Join<AlunosTurma, Aluno> join = root.join("aluno");
					Join<Aluno, Unidade> joinUnidade = join.join("unidade");
					predicates.add(cb.equal(joinUnidade.get("idUnidade"), criteria.getIdUnidade()));
				}
				return cb.and(predicates.toArray(new Predicate[] {}));
			}
		};
	}

}
