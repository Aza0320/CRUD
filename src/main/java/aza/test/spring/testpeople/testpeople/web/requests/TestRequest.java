package aza.test.spring.testpeople.testpeople.web.requests;

import aza.test.spring.testpeople.testpeople.web.dao.PersonDAO;
import aza.test.spring.testpeople.testpeople.web.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
    public @ResponseBody List<Person> test() {
        return personDAO.index();
    }

}
