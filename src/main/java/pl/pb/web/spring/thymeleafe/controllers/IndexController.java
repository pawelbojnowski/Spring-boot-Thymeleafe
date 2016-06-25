package pl.pb.web.spring.thymeleafe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @Autowired
    ContactController contactController;

    //default endpoint
    @RequestMapping("/")
    public ModelAndView greeting(Model model) {
        return contactController.contacts();
    }

}
