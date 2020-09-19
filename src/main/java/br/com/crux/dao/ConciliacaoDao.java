package br.com.crux.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.springframework.stereotype.Component;

import br.com.crux.dao.base.BaseDao;
import br.com.crux.dao.dto.ConciliacaoDTO;
import br.com.crux.infra.util.DataUtil;

@Component
public class ConciliacaoDao extends BaseDao {

	public void exportar(Long idInstituicao, Long idContaBancaria, LocalDate dataInicio, LocalDate dataFim){
        Session session =null;
        try{
        	session = em.unwrap(Session.class);
            session.beginTransaction();
            
            session.doWork(new Work(){
              @Override
              public void execute(Connection connection) throws SQLException {
                    CallableStatement statement =null;
                    
                    String sqlString = "{call fn_gera_conciliacao_bancaria(?,?,?,?)}";

                    statement = connection.prepareCall(sqlString);
                    statement.setLong(1,idInstituicao);
                    
                    if(Objects.nonNull(idContaBancaria)) {
                    	statement.setLong(2,idContaBancaria);
                    } else {
                    	statement.setNull(2, Types.INTEGER);
                    }
                    
                    if(Objects.nonNull(dataInicio)) {
                    	Date pDataInicio = DataUtil.parseDate(dataInicio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    	statement.setTimestamp(3, new Timestamp(pDataInicio.getTime()));
                    } else {
                    	statement.setNull(3, Types.TIMESTAMP);
                    }
                    
                    if(Objects.nonNull(dataFim)) {
                    	Date pDataFim = DataUtil.parseDate(dataFim.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    	statement.setTimestamp(4, new Timestamp(pDataFim.getTime()));
                    } else {
                    	statement.setNull(4, Types.TIMESTAMP);
                    }
                    
                    int count = statement.executeUpdate();
                    System.out.println(count +" registro(s) afetados");
                }
            });
            session.getTransaction().commit();

        }finally{
            session.close();
        }
    }

	public List<ConciliacaoDTO> getAll(Long idInstituicao, Long idContaBancaria, LocalDate dataInicio, LocalDate dataFim) {
		StringBuilder sql = new StringBuilder();

		sql.append("select fn_gera_conciliacao_bancaria(");
		sql.append(idInstituicao);
		sql.append(",");
		
		if(Objects.nonNull(idContaBancaria)) {
			sql.append(idContaBancaria);
		}else {
			sql.append("null");
		}
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/mm/yyyy");
		
		sql.append(",");
		sql.append("DATE_TRUNC('DAY', to_date(" + formatter.format(dataInicio) + ",'dd/mm/yyyy') )");
		sql.append(",");
		sql.append("DATE_TRUNC('DAY', to_date(" + formatter.format(dataFim) + ",'dd/mm/yyyy') )");
		sql.append(")");

		Query query = em.createNativeQuery(sql.toString());

		@SuppressWarnings("unchecked")
		List<Object[]> values = query.getResultList();

		List<ConciliacaoDTO> retorno = new ArrayList<ConciliacaoDTO>();
		values.stream().forEach(colunas -> retorno.add(new ConciliacaoDTO(colunas)));

		return retorno;

	}

}
