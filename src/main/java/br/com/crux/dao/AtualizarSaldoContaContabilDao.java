package br.com.crux.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.dao.base.BaseDao;
import br.com.crux.infra.util.DataUtil;

@Component
public class AtualizarSaldoContaContabilDao extends BaseDao {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	
	
	public void atualizarSaldo(Long idPlanoConta, LocalDate dataInicio) {
		Session session = null;
		try {
			session = getSession();
			session.beginTransaction();

			session.doWork(new Work() {
				@Override
				public void execute(Connection connection) throws SQLException {
					try {
						CallableStatement statement = null;
						
						String sqlString = "{call fn_atualiza_saldos_categorias_contabeis(?,?,?)}";
						
						statement = connection.prepareCall(sqlString);
						statement.setLong(1, idPlanoConta);
						
						Date pDataInicio = DataUtil.parseDate(dataInicio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
						statement.setTimestamp(2, new Timestamp(pDataInicio.getTime()));
						
						statement.setLong(3, getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
						
					    statement.executeUpdate();
					} finally {}
				}
			});
			session.getTransaction().commit();

		} finally {
			closeEntityManager();
		}
	}

}
