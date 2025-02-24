package SpringPractice.UserFeignClient.GlobalExceptionHandling;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.swagger.v3.oas.annotations.Hidden;

@Hidden
@RestControllerAdvice
public class ExceptionHandling {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> VadlidationException(MethodArgumentNotValidException E){
		
		Map<String, String> Errors = new HashMap<String, String>();
		
		List<FieldError> errorsList = E.getBindingResult().getFieldErrors();
		
		for (FieldError fieldError : errorsList) {
			String fieldName = fieldError.getField();
			String ErrorMessage = fieldError.getDefaultMessage();
			Errors.put(fieldName, ErrorMessage);
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Errors);
	}
	
}
