package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DataController {

	@RequestMapping("route.do")
	public ModelAndView processData(@RequestParam("data") String s) {
		String allCaps = s.toUpperCase();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("results.jsp");
		mv.addObject("result", allCaps);
		return mv;
	}
}