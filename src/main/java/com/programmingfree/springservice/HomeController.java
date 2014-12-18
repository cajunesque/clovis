package com.programmingfree.springservice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/demo")
	public String demo() {
		return "demo";
	} // because this will become extinct

	@RequestMapping("/dictionary")
	public String dictionary() {
		return "dictionary";
	} // the first of all the managers

	@RequestMapping("/drill")
	public String drill() { return "drill"; } // gradually takes all new drill: word, phrase, clause

}
