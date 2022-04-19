package aza.test.spring.testpeople.testpeople.web.requests;

import aza.test.spring.testpeople.testpeople.web.dao.PersonDAO;
import aza.test.spring.testpeople.testpeople.web.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/testAza")
public class TestRequest {

    private final PersonDAO personDAO;

    @Autowired
    public TestRequest(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public @ResponseBody
    List<Person> getAllPeople() {
        return personDAO.index();
    }

    @GetMapping("/getTest4")
    public @ResponseBody
    List<Test> test4(@RequestParam int offset, @RequestParam int next, @RequestParam String sex, @RequestParam String country,
                     @RequestParam String region, @RequestParam String dob) {
        return personDAO.getTest(offset, next, sex, country, region, dob);
    }

    @GetMapping("/getTest3")
    public @ResponseBody
    List<Test> test3(@RequestParam int offset, @RequestParam int next, @RequestParam String sex, @RequestParam String country,
                     @RequestParam String region) {
        return personDAO.getTest(offset, next, sex, country, region);
    }

    @GetMapping("/getTest2")
    public @ResponseBody
    List<Test> test2(@RequestParam int offset, @RequestParam int next, @RequestParam String sex, @RequestParam String country) {
        return personDAO.getTest(offset, next, sex, country);
    }

    @GetMapping("/getTest1")
    public @ResponseBody
    List<Test> test1(@RequestParam int offset, @RequestParam int next, @RequestParam String gender) {
        return personDAO.getTest(offset, next, gender);
    }

    @GetMapping("/getTest0")
    public @ResponseBody
    List<Test> test(@RequestParam int offset, @RequestParam int next) {
        return personDAO.getTest(offset, next);
    }

    @GetMapping("/getCountry/{name}")
    public @ResponseBody
    List<FindRegion> test4(@PathVariable String name) {
        return personDAO.getCIDByCName(name);
    }
}
