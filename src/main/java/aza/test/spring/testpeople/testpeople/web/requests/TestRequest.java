package aza.test.spring.testpeople.testpeople.web.requests;

import aza.test.spring.testpeople.testpeople.web.dao.PersonDAO;
import aza.test.spring.testpeople.testpeople.web.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    @GetMapping("/gat")
    public @ResponseBody
    Map<String, Object> gat() {
        Map<String, Object> map = new HashMap<>();
        map.put("draw", 1);
        map.put("recordsTotal", 20047);
        map.put("recordsFiltered", 20047);
        map.put("data", personDAO.getTest(0, 100));

        return map;
    }

    @GetMapping("/getCountry/{name}")
    public @ResponseBody
    List<FindRegion> takeRegions(@PathVariable String name) {
        return personDAO.getCIDByCName(name);
    }

    @GetMapping("/getCountry")
    public @ResponseBody
    List<Country> getCountry() {
        return personDAO.getCountry();
    }

    @GetMapping("/getRegion")
    public @ResponseBody
    List<Region> getRegion() {
        return personDAO.getRegion();
    }

    @GetMapping("/total")
    public List<Count> total() {
        return personDAO.getTotal();
    }

    @PostMapping("/testDT")
    public @ResponseBody
    Map<String, Object> tdt(@RequestParam int start, @RequestParam int draw, @RequestParam int length,
                            @RequestParam Map<String, String> search) {
        String value = "search[value]";
        String gender = "columns[4][search][value]";
        String country = "columns[5][search][value]";
        String region = "columns[6][search][value]";

        String dob = "columns[7][search][value]";

        System.out.println(search.get(region));
        System.out.println(search.get(country));

        Map<String, Object> map = new HashMap<>();
        map.put("draw", draw);
        map.put("recordsTotal", personDAO.getTotal().get(0).getCount());
        map.put("recordsFiltered",  personDAO.filteredCount(search.get(value), search.get(gender), search.get(country), search.get(region), search.get(dob)).get(0).getCount());
        map.put("data", personDAO.search(search.get(value), start, length, search.get(gender), search.get(country), search.get(region), search.get(dob)));

        return map;
    }
}
