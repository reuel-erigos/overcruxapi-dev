package br.com.crux.cmd;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.ConciliacaoDao;
import br.com.crux.exception.ConciliacaoNaoGeradoException;
import br.com.crux.infra.util.Java8DateUtil;

@Component
public class GerarConciliacaoCmd {

	@Autowired private ConciliacaoDao dao;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;


	public void gerar(String tipoAcao, Long idContaBancaria, Long dataInicio, Long dataFim) {
		try {
			LocalDate pDataInicio  = Objects.nonNull(dataInicio) ? Java8DateUtil.getLocalDateTime(new Date(dataInicio)).toLocalDate() : null;
			LocalDate pDataFim     = Objects.nonNull(dataFim) ? Java8DateUtil.getLocalDateTime(new Date(dataFim)).toLocalDate() : null;
			
			Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
			dao.exportar(tipoAcao, idInstituicao, idContaBancaria, pDataInicio, pDataFim);
			
		} catch (Exception e) {
			throw new ConciliacaoNaoGeradoException(e.getMessage());
		}
	}


}
