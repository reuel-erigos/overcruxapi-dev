package br.com.crux.cmd.relatorios.gestao_pessoal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.relatorios.FuncaoTOBuilder;
import br.com.crux.dao.relatorios.FuncaoDao;
import br.com.crux.infra.relatorio.GeradorRelatorio;
import br.com.crux.to.relatorios.gestao_pessoal.FuncaoTO;

@Component
public class GerarRelatorioFuncaoCmd {

	@Autowired private GeradorRelatorio geradorRelatorio;
	@Autowired private FuncaoDao dao;
	@Autowired private FuncaoTOBuilder builder;
	
	public byte[] gerar(List<Integer> listaIdsPessoaFisica,String mimeType) throws Exception  {
		String nomeRelatorio = "RelatorioFuncao";
		String[] path = {"casa_azul", "gestao_pessoal", "funcao"};
		
		Map<String, Object> parametros = new HashMap<>();
		
		List<FuncaoTO> dados = builder.buildAll(dao.get(listaIdsPessoaFisica));
		
		return geradorRelatorio.gerar(parametros, dados, nomeRelatorio, path, mimeType);
	}
	
}
