package pl.pb.web.spring.thymeleafe.controllers;

import pl.pb.web.spring.thymeleafe.controllers.validators.ContactValidation;
import pl.pb.web.spring.thymeleafe.reposytory.dao.ContactDao;
import pl.pb.web.spring.thymeleafe.reposytory.model.ContactEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/indexAjax")
public class ContactAjaxController {

    @Autowired
    ContactDao contactDao;

    @Autowired
    ContactValidation contactValidation;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.addValidators(contactValidation);
    }

    @RequestMapping()
    public ModelAndView contacts() {
        ModelAndView modelAndView = new ModelAndView("/indexAjax");
        modelAndView.addObject("contactList", contactDao.findAll());
        return modelAndView;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("indexAjax :: editPane");
        ContactEntity contactEntity = contactDao.findOne(id);
        modelAndView.addObject("contact", contactEntity);
        return modelAndView;
    }

    @RequestMapping(path = "/new", method = RequestMethod.GET)
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("indexAjax :: editPane");
        modelAndView.addObject("contact", new ContactEntity());
        return modelAndView;
    }

    @RequestMapping(path = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView savePost(@RequestBody @Validated ContactEntity contactEntity) {
        contactEntity.setId(null);
        contactDao.save(contactEntity);
        ModelAndView modelAndView = new ModelAndView("indexAjax :: content");
        modelAndView.addObject("contactList", contactDao.findAll());
        return modelAndView;
    }

    @RequestMapping(path = "/save", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView savePut(@RequestBody @Validated ContactEntity contactEntity) {
        contactDao.save(contactEntity);
        ModelAndView modelAndView = new ModelAndView("indexAjax :: content");
        modelAndView.addObject("contactList", contactDao.findAll());
        return modelAndView;
    }

    @RequestMapping(path = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView savePut(@PathVariable("id") Long id) {

        if (contactDao.exists(id)) {
            contactDao.delete(id);
        }
        ModelAndView modelAndView = new ModelAndView("indexAjax :: table");
        modelAndView.addObject("contactList", contactDao.findAll());
        return modelAndView;
    }
}
