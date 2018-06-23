package cn.alauwahios.front.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("testController")
public class TestController {
	private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    @RequestMapping(value = "/indexTest", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("recipient", "World Controller的中文。");
        logger.info("第一个index。");
        return "index";
    }
}
