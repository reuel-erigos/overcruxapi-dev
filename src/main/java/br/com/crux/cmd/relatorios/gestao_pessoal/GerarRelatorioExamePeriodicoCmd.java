package br.com.crux.cmd.relatorios.gestao_pessoal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.relatorios.ExamePeriodicoTOBuilder;
import br.com.crux.dao.relatorios.ExamePeriodicoDao;
import br.com.crux.infra.relatorio.GeradorRelatorio;
import br.com.crux.to.relatorios.gestao_pessoal.ExamePeriodicoTO;

@Component
public class GerarRelatorioExamePeriodicoCmd {

	@Autowired private GeradorRelatorio geradorRelatorio;
	@Autowired private ExamePeriodicoDao dao;
	@Autowired private ExamePeriodicoTOBuilder builder;
	
	public byte[] gerar(List<Integer> listaIdsPessoaFisica,String mimeType) throws Exception  {
		String nomeRelatorio = "RelacaoExamePeriodico";
		String[] path = {"casa_azul", "gestao_pessoal", "exame_periodico"};
		
		Map<String, Object> parametros = new HashMap<>();
		
		List<ExamePeriodicoTO> dados = builder.buildAll(dao.get(listaIdsPessoaFisica));
		
		return geradorRelatorio.gerar(parametros, dados, nomeRelatorio, path, mimeType);
	}
	
}
