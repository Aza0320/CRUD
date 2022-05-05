package aza.test.spring.testpeople.testpeople.web.requests;

import aza.test.spring.testpeople.testpeople.web.dao.PersonDAO;
import aza.test.spring.testpeople.testpeople.web.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/peopleRequests")
public class PeopleRequest {

    private final PersonDAO personDAO;

    @Autowired
    public PeopleRequest(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping("/getCountry/{name}")
    public @ResponseBody
    List<FindRegion> takeRegions(@PathVariable String name) {
        return personDAO.getCIDByC_name(name);
    }

    @GetMapping("/total")
    public List<Count> total() {
        return personDAO.getTotal();
    }

    @PostMapping("/peopleDT")
    public @ResponseBody
    Map<String, Object> tdt(@RequestParam int start, @RequestParam int draw, @RequestParam int length,
                            @RequestParam Map<String, String> search) {
        String value = "search[value]";
        String gender = "columns[4][search][value]";
        String country = "columns[5][search][value]";
        String region = "columns[6][search][value]";
        String dob = "columns[7][search][value]";

        System.out.println(search.get(value));

        Map<String, Object> map = new HashMap<>();
        map.put("draw", draw);
        map.put("recordsTotal", personDAO.getTotal().get(0).getCount());
        map.put("recordsFiltered", personDAO.filteredCount(search.get(value), search.get(gender), search.get(country), search.get(region), search.get(dob)).get(0).getCount());
        map.put("data", personDAO.search(search.get(value), start, length, search.get(gender), search.get(country), search.get(region), search.get(dob)));

        return map;
    }
}
