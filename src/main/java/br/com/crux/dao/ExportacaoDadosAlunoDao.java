package br.com.crux.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import br.com.crux.dao.base.BaseDao;
import br.com.crux.dao.dto.ExportacaoDadosAlunoDTO;

@Component
public class ExportacaoDadosAlunoDao extends BaseDao{
	
	
	public Optional<List<ExportacaoDadosAlunoDTO>> getAllFilter(String cpf, Long idBeneficiario, Long idMae, Long idPai, Long idPrograma,
														        Long idProjeto, Long idUnidade, Long idResponsavel, 
														        LocalDate dataInicioInstituicao, LocalDate dataFimInstituicao, Long idInstituicao){
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select a.id_aluno,                                                           ");
		sql.append("        pf.nm_pessoa_fisica                                                   ");
		sql.append("  from alunos a                                                               ");
		sql.append(" inner join pessoas_fisicas pf on a.id_pessoa_fisica = pf.id_pessoa_fisica    ");
		sql.append(" where 1=1                                                                    ");
		sql.append("  and pf.id_instituicao = :idInstituicao                                      ");
		
		Query query = em.createNativeQuery(sql.toString());
		query.setParameter("idInstituicao", idInstituicao);
		
		
		@SuppressWarnings("unchecked")
		List<Object[]> values = query.getResultList();
		
		List<ExportacaoDadosAlunoDTO> retorno = new ArrayList<ExportacaoDadosAlunoDTO>();
		values.stream().forEach( colunas -> retorno.add(new ExportacaoDadosAlunoDTO(colunas)));
		

		//Dados mockados
		retorno.addAll(mockDados());
		
		return Optional.ofNullable(retorno);
		
	}
	
	
	private List<ExportacaoDadosAlunoDTO> mockDados() {
		List<ExportacaoDadosAlunoDTO> lista = new ArrayList<ExportacaoDadosAlunoDTO>();
		for (int i = 0; i < 11; i++) {
			ExportacaoDadosAlunoDTO d1 = new ExportacaoDadosAlunoDTO();
			d1.setId(Long.valueOf(i));
			d1.setNome("Fulano de souza " + i);
			lista.add(d1);
		}
		return lista;
	}
	
}
