package aza.test.spring.testpeople.testpeople.web.controllers;


import aza.test.spring.testpeople.testpeople.web.dao.PersonDAO;
import aza.test.spring.testpeople.testpeople.web.models.Person;
import aza.test.spring.testpeople.testpeople.web.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(@ModelAttribute("person") Person person) {
        return "/index";
    }

    @PostMapping("/addPerson")
    public String create(@ModelAttribute("person") @Valid Person person, HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult) throws ServletException, IOException {
        if (bindingResult.hasErrors()) return "/show";
        personDAO.save(person);

        int fileName = personDAO.getPersonByPassport(person.getPassport()).getId();
        File file = new ClassPathResource("static/images/Toshkent viloyati/M/Yangiyo'l shahar.jpg").getFile();
        String path = file.toString().split("target")[0] + "src/main/resources/static/images/" + person.getCountry() + "/" + person.getSex() + "/";
        for (Part part : request.getParts()) {
            part.write(path + fileName + ".jpg");
        }
        response.getWriter().print("The file uploaded successfully.");

        return "redirect:/people";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("person") Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "/show";

        personDAO.update(person);
        return "redirect:/people";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute("person") Person person, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) return "/show";

        Person editPerson = personDAO.getPerson(person.getId());

        model.addAttribute("name", editPerson.getName());
        model.addAttribute("id", editPerson.getId());
        model.addAttribute("surname", editPerson.getSurname());
        model.addAttribute("country", editPerson.getCountry());
        model.addAttribute("region", editPerson.getRegion());
        model.addAttribute("passport", editPerson.getPassport());
        model.addAttribute("sex", editPerson.getSex());
        model.addAttribute("dob", editPerson.getDobString());
        model.addAttribute("doe", editPerson.getDoeString());
        model.addAttribute("doi", editPerson.getDoiString());
        return "/edit";
    }

    @PostMapping("/delete")
    public String delete(Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "/show";
        try {
            File file = new ClassPathResource("static/images/Toshkent viloyati/M/Yangiyo'l shahar.jpg").getFile();
            String path = file.toString().split("target")[0] + "src/main/resources/static/images/" + person.getCountry() + "/" + person.getSex() + "/" + person.getRegion() + ".jpg";
            File delete = new File(path);
            if (delete.delete()) personDAO.delete(Integer.parseInt(person.getSurname()));
            else System.out.println("File not found");
        } catch (Exception ignored) {
        }

        personDAO.delete(Integer.parseInt(person.getSurname()));
        return "redirect:/people";
    }

    @GetMapping(value = "/getPdf/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public @ResponseBody
    byte[] getPdf(@PathVariable String id) throws Exception {
        Person person = personDAO.getPerson(Integer.parseInt(id));
        return PersonServices.createPDF(person);
    }

    @GetMapping(value = "/getImg/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody
    byte[] getImg(@PathVariable String id) throws Exception {
        Person person = personDAO.getPerson(Integer.parseInt(id));
        return PersonServices.createImg(PersonServices.createPDF(person), person);
    }
}
