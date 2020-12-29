package br.com.crux.cmd.relatorios.gestao_pessoal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.relatorios.CensoTOBuilder;
import br.com.crux.dao.relatorios.CensoDao;
import br.com.crux.infra.relatorio.GeradorRelatorio;
import br.com.crux.to.relatorios.gestao_pessoal.CensoTO;

@Component
public class GerarRelatorioCensoCmd {

	@Autowired private GeradorRelatorio geradorRelatorio;
	@Autowired private CensoDao dao;
	@Autowired private CensoTOBuilder builder;
	
	public byte[] gerar(List<Integer> listaIdsPessoaFisica,String mimeType) throws Exception  {
		String nomeRelatorio = "RelatorioCenso";
		String[] path = {"casa_azul", "gestao_pessoal", "censo"};
		
		Map<String, Object> parametros = new HashMap<>();
		
		List<CensoTO> dados = builder.buildAll(dao.get(listaIdsPessoaFisica));
		
		return geradorRelatorio.gerar(parametros, dados, nomeRelatorio, path, mimeType);
	}
	
}
