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

    @GetMapping("/getTest")
    public @ResponseBody
    List<Test> test(@RequestParam int offset, @RequestParam int next, @RequestParam String allParam) {
        return personDAO.getTest(offset, next, allParam);
    }

    @GetMapping("/getCountry/{name}")
    public @ResponseBody
    List<FindRegion> takeRegions(@PathVariable String name) {
        return personDAO.getCIDByCName(name);
    }

    @GetMapping("/total")
    public List<Count> total() {
        return personDAO.getTotal();
    }
}
