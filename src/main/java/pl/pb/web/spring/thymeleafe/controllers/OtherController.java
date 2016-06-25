package pl.pb.web.spring.thymeleafe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

@Controller
@RequestMapping("/other")
public class OtherController {

    @RequestMapping()
    public ModelAndView other() {
        ModelAndView modelAndView = new ModelAndView("other");
        OtherModel otherModel = getCreateModel();
        modelAndView.addObject("model", otherModel);
        modelAndView.addObject("jsonModel", otherModel.toString());
        return modelAndView;
    }

    private OtherModel getCreateModel() {
        OtherModel otherModel = new OtherModel();
        otherModel.setName("Name of Something");
        otherModel.setDate(new Date());
        otherModel.setListOfObject(Arrays.asList("CD", "DVD", "HHD", "SDD"));
        otherModel.setEmptyList(new ArrayList<>());
        return otherModel;
    }


    @RequestMapping("/reloadModel")
    public ModelAndView reloadModel() {
        ModelAndView modelAndView = new ModelAndView("fragment :: reloadModel");
        OtherModel otherModel = getCreateModel();
        modelAndView.addObject("model", otherModel);
        modelAndView.addObject("jsonModel", otherModel.toString());
        return modelAndView;
    }
}
