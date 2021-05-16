package be.ledfan.springredisevents.example;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MainController {

    private final GenerateService generateService;

    public MainController(GenerateService generateService) {
        this.generateService = generateService;
    }

    @RequestMapping(value = "/api/generate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResult generateId() {
        return new JsonResult(generateService.generate(), "Hello World");
    }

    @RequestMapping(value = "/api/delete/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResult deleteId(@PathVariable("id") int id) {
        generateService.delete(id);
        return new JsonResult(id, "deleted");
    }

}


