package au.com.qut.cpm.capstone;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("Index")
public class IndexController {

    @GetMapping("/{id}", produces = "application/json")
    public @ResponseBody Index getIndex(@PathVariable int id) {
        return findIndexbyId(id);
    }

    private Index findIndexById(int id) {
        return(Index(ID));
    }
}
@Controller
@RequestMapping("Event")
public class EventController {
    @GetMapping("/{id}", produces = "application/json")
    public @ResponseBody Event getEvent(@PathVariable int id) {
        return findEventbyId(id);
    }

    private Event findEventById(int id) {
        return(Event(ID));
    }
}
