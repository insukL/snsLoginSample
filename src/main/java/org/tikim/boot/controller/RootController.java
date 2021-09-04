package org.tikim.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping(value = "/")
public class RootController {

	@GetMapping({"", "/"})
	public String getAuthorizationMessage() {
		return "home";
	}

	@GetMapping("/board")
	public String board() {
		return "board";
	}

}