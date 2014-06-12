package kr.pe.stella.sample.controller;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {

		return LocalDateTime.now().toString(DateTimeFormat.fullDateTime());
	}
}
