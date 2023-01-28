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

import br.com.crux.entity.CategoriasContabeis;
import br.com.crux.entity.CategoriasMovimentos;
import br.com.crux.entity.Empresa;
import br.com.crux.entity.Fatura;
import br.com.crux.entity.Movimentacoes;
import br.com.crux.entity.PagamentosFatura;
import br.com.crux.entity.Projeto;
import br.com.crux.entity.RateiosMovimentacoes;
import br.com.crux.entity.Unidade;
import br.com.crux.to.filtro.FiltroMovimentacoesTO;

@Component
public class MovimentacoesSpec{

	public static Specification<Movimentacoes> findByCriteria(FiltroMovimentacoesTO criteria) {
		return new Specification<Movimentacoes>() {

			private static final long serialVersionUID = -7597187532438443858L;

			@Override
			public Predicate toPredicate(Root<Movimentacoes> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				if(!StringUtils.isEmpty(criteria.getNumeroDocumento())) {
					predicates.add(cb.equal(root.get("nrDocumento"), criteria.getNumeroDocumento()));
				}
				if(Objects.nonNull(criteria.getValor())) {
					predicates.add(cb.equal(root.get("valorMovimentacao"), criteria.getValor()));
				}
				if(Objects.nonNull(criteria.getDataInicioDoc())) {
					predicates.add(cb.greaterThanOrEqualTo(root.get("dataDocumento"), criteria.getDataInicioDoc()));
				}
				if(Objects.nonNull(criteria.getDataFimDoc())) {
					predicates.add(cb.lessThanOrEqualTo(root.get("dataDocumento"), criteria.getDataFimDoc()));
				}
				if(Objects.nonNull(criteria.getEmpresa()) && Objects.nonNull(criteria.getEmpresa().getId())) {
					Join<Movimentacoes, Empresa> join = root.join("empresa");
					predicates.add(cb.equal(join.get("id"), criteria.getEmpresa().getId()));
				}
				if(Objects.nonNull(criteria.getProjeto()) && Objects.nonNull(criteria.getProjeto().getId())) {
					Join<Movimentacoes, CategoriasMovimentos> join = root.join("categorias");
					Join<CategoriasMovimentos, Projeto> projeto = join.join("projeto");
					predicates.add(cb.equal(projeto.get("id"), criteria.getProjeto().getId()));
				}
				if(Objects.nonNull(criteria.getDataInicioPag())) {
					Join<Movimentacoes, Fatura> join = root.join("faturas");
					Join<Fatura, PagamentosFatura> joinPagamentoFatura = join.join("pagamentosFatura");
					predicates.add(cb.greaterThanOrEqualTo(joinPagamentoFatura.get("dataPagamento"), criteria.getDataInicioPag()));
				}
				if(Objects.nonNull(criteria.getDataFimPag())) {
					Join<Movimentacoes, Fatura> join = root.join("faturas");
					Join<Fatura, PagamentosFatura> joinPagamentoFatura = join.join("pagamentosFatura");
					predicates.add(cb.lessThanOrEqualTo(joinPagamentoFatura.get("dataPagamento"), criteria.getDataFimPag()));
				}
				if(Objects.nonNull(criteria.getValorCategoria())) {
					Join<Movimentacoes, CategoriasMovimentos> join = root.join("categorias");
					predicates.add(cb.equal(join.get("valor"), criteria.getValorCategoria()));
				}
				if(Objects.nonNull(criteria.getContaAdicional()) && Objects.nonNull(criteria.getContaAdicional().getId())) {
					Join<Movimentacoes, CategoriasMovimentos> join = root.join("categorias");
					Join<CategoriasMovimentos, CategoriasContabeis> joinCategorias = join.join("categoriaAdicional");
					predicates.add(cb.equal(joinCategorias.get("id"), criteria.getContaAdicional().getId()));
				}
				if(Objects.nonNull(criteria.getContaCredito()) && Objects.nonNull(criteria.getContaCredito().getId())) {
					Join<Movimentacoes, CategoriasMovimentos> join = root.join("categorias");
					Join<CategoriasMovimentos, CategoriasContabeis> joinCategorias = join.join("categoriaOrigem");
					predicates.add(cb.equal(joinCategorias.get("id"), criteria.getContaCredito().getId()));
				}
				if(Objects.nonNull(criteria.getContaDebito()) && Objects.nonNull(criteria.getContaDebito().getId())) {
					Join<Movimentacoes, CategoriasMovimentos> join = root.join("categorias");
					Join<CategoriasMovimentos, CategoriasContabeis> joinCategorias = join.join("categoriaDestino");
					predicates.add(cb.equal(joinCategorias.get("id"), criteria.getContaDebito().getId()));
				}
				if(Objects.nonNull(criteria.getIdUnidade())) {
					Join<Movimentacoes, Unidade> join = root.join("unidade");
					predicates.add(cb.equal(join.get("idUnidade"), criteria.getIdUnidade()));
				}
				predicates.add(cb.notEqual(root.get("stTipoMovimentacao"), "T"));
				query.distinct(true);
				return cb.and(predicates.toArray(new Predicate[] {}));
			}
		};
	}

}
