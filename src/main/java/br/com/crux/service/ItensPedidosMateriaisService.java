package br.com.crux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.crux.cmd.GetItensPedidosMateriaisCmd;
import br.com.crux.to.ItensPedidosMateriaisTO;

@RestController
@RequestMapping(value = "itenspedidosmateriais")
public class ItensPedidosMateriaisService {

	@Autowired private GetItensPedidosMateriaisCmd getCmd;

	@GetMapping("/combo")
	public List<ItensPedidosMateriaisTO> getAllCombo() {
		return getCmd.getAllCombo();
	}

}
