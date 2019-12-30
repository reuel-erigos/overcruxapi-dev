package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.crux.rule.CepInvalidoRule;
import br.com.crux.to.CepTO;

@Component	
public class GetEnderecoPorCepCmd {
	private static final String viacepUrl = "https://viacep.com.br/ws/%s/json";
	
	@Autowired private CepInvalidoRule cepInvalidoRule;

	public CepTO getEndereco(String cep) {
		cepInvalidoRule.validar(cep);
		ResponseEntity<CepTO> responseEntity = getCep(cep.replaceAll("\\D", ""));
		return responseEntity.getBody(); 
	}

	private ResponseEntity<CepTO> getCep(String cep) {
		RestTemplate restTemplate = new RestTemplate();
		
		String url = String.format(viacepUrl, cep);
		ResponseEntity<CepTO> response  = restTemplate.getForEntity(url, CepTO.class); 
		
		return response;
	}
}
