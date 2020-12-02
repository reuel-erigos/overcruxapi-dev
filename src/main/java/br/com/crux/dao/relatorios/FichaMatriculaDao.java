package br.com.crux.dao.relatorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import br.com.crux.dao.base.BaseDao;
import br.com.crux.to.relatorios.FichaMatriculaDTO;

@Component
public class FichaMatriculaDao extends BaseDao{
	
	
	public List<FichaMatriculaDTO> get() {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select                                ");
		sql.append("       id_pessoa_fisica              , ");
		sql.append("       nome                          , ");
		sql.append("       ds_endereco                   , ");
		sql.append("       ds_bairro                     , ");
		sql.append("       sg_uf_endereco                , ");
		sql.append("       nr_cep                        , ");
		sql.append("       nr_fone_celular               , ");
		sql.append("       nr_telefone_residencial       , ");
		sql.append("       nr_telefone_comercial         , ");
		sql.append("       ds_sexo                       , ");
		sql.append("       dt_nascimento                 , ");
		sql.append("       naturalidade                  , ");
		sql.append("       nm_mae                        , ");
		sql.append("       nm_pai                        , ");
		sql.append("       encaminhado                   , ");
		sql.append("       ds_publico_prioritario        , ");
		sql.append("       dt_matricula                  , ");
		sql.append("       ds_turno                      , ");
		sql.append("       atividade                     , ");
		sql.append("       ds_escola                     , ");
		sql.append("       ds_serie_escola               , ");
		sql.append("       ds_problema_saude             , ");
		sql.append("       ds_medicamentos_controlados   , ");
		sql.append("       st_mora_pais                  , ");
		sql.append("       st_pais_casados               , ");
		sql.append("       renda_familiar                , ");
		sql.append("       fonte_renda_familiar          , ");
		sql.append("       tx_observacoes                , ");
		sql.append("       ds_busca_escola               , ");
		sql.append("       st_apr_externa_pub            , ");
		sql.append("       nome_responsavel              , ");
		sql.append("       dt_nascimento_responsavel     , ");
		sql.append("       rg_responsavel                , ");
		sql.append("       nr_cpf_responsavel            , ");
		sql.append("       fone_celular_responsavel      , ");
		sql.append("       fone_residencial_responsavel  , ");
		sql.append("       fone_comercial_responsavel    , ");
		sql.append("       nm_grau_parentesco            , ");
		sql.append("       nr_nis                        , ");
		sql.append("       nis_responsavel                 ");
		sql.append("  from vw_relatorio_ficha_cadastral    ");

		
		
		
		Query query = em.createNativeQuery(sql.toString());
		//query.setParameter("idInstituicao", idInstituicao);
		
		@SuppressWarnings("unchecked")
		List<Object[]> values = query.getResultList();
		
		List<FichaMatriculaDTO> retorno = new ArrayList<FichaMatriculaDTO>();
		values.stream().forEach( colunas -> retorno.add(new FichaMatriculaDTO(colunas)));
		
		return retorno;
		
	}
	
}
