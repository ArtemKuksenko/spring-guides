package FuelControl;

import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

//https://spring-projects.ru/guides/handling-form-submission/

@RestController
public class CarsController {

//    private static final String template = "Hello, %s!";
//    private final AtomicLong counter = new AtomicLong();

//    @RequestMapping(value = "/hi", method= RequestMethod.POST)
//    public Greeting greeting(@RequestParam(value="name", required=false, defaultValue="World") String name) {
//        return new Greeting(counter.incrementAndGet(),
//                String.format(template, name));
//    }

    @PostMapping(path = "/car", consumes = "application/json", produces = "application/json")
    public String carcar(@RequestBody NewCarModel newCar) {
        return newCar.Hello();
    }

}
