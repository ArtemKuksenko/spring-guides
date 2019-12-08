package FuelControl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.StringWriter;
import java.util.concurrent.atomic.AtomicLong;

//https://spring-projects.ru/guides/handling-form-submission/

@RestController
public class CarsController {

    @PostMapping(path = "/addcar", consumes = "application/json", produces = "application/json")
    public String addCar(@RequestBody Car newCar) {
        FileWork file = new FileWork();
        if ( file.isFile( newCar.getNumber()) )
            return "{\"status\":\"error\",\"error\":\"this car is registrated\"}";

        ObjectMapper mapper = new ObjectMapper();
        StringWriter writer = new StringWriter();
        try {
            mapper.writeValue(writer, newCar);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String result = writer.toString();
        try {
            file.writeFile(newCar.getNumber(),result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "{\"status\":\"ok\"}";

//        return "newCar";
    }

}
