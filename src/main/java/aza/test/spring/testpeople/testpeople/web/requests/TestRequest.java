package aza.test.spring.testpeople.testpeople.web.requests;

import aza.test.spring.testpeople.testpeople.web.dao.PersonDAO;
import aza.test.spring.testpeople.testpeople.web.models.Country;
import aza.test.spring.testpeople.testpeople.web.models.FindRegion;
import aza.test.spring.testpeople.testpeople.web.models.Person;
import aza.test.spring.testpeople.testpeople.web.models.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    List<Person> test() {
        return personDAO.index();
    }

    @GetMapping("/getCountry/{name}")
    public @ResponseBody
    List<FindRegion> test4(@PathVariable String name) {
        return personDAO.getCIDByCName(name);
    }
}
