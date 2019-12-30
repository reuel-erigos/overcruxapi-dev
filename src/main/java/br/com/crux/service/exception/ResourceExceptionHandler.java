package br.com.crux.service.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.crux.exception.base.NegocioException;

@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler{
	
	private static final int STATUS_ERRO_NEGOCIO = 418;
	private final Logger log = LoggerFactory.getLogger(ResourceExceptionHandler.class);
	
	@Autowired
	private HandleExceptionCmd handleExceptionCmd;
	
	@ExceptionHandler(Throwable.class)
	public ResponseEntity<ApiError> handleNotFoundException(final Throwable throwable) {
		Integer status;
		ApiError apiError;
		
		if (throwable instanceof NegocioException || throwable.getCause() instanceof NegocioException) {
			status = STATUS_ERRO_NEGOCIO;
			apiError = new ApiError(HttpStatus.NOT_FOUND.value(), throwable.getMessage());
		} else {
			status = HttpStatus.INTERNAL_SERVER_ERROR.value();
			log.error("server exception", throwable);

			try {
				final Integer codigoErro = handleExceptionCmd.handle(throwable);
				String mensagemCodErro = " O código " + codigoErro + " foi gravado.";

				apiError = new ApiError(codigoErro, "Ocorreu um erro interno." + mensagemCodErro);
			} catch (Exception e) {
				apiError = new ApiError(null, "Ocorreu um erro interno.");
			}
		}
		return ResponseEntity.status(status).body(apiError);
	}

}
