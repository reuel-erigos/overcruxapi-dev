package br.com.crux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.crux.cmd.GetBeneficioSocialCmd;
import br.com.crux.to.BeneficioSocialTO;

@RestController
@RequestMapping(value = "beneficiossociais")
public class BeneficioSocialService {

	@Autowired private GetBeneficioSocialCmd getCmd;
	
	@GetMapping
	public List<BeneficioSocialTO> getAll() {
		return getCmd.getAll();
	}

}
