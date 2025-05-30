package ar.com.chocolateria.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class) // se pueed customizar con el null pointer, etc
	public String handleException (Exception e, Model model) {
		model.addAttribute("error", e.getMessage());
		return "error";
	}
	
}
