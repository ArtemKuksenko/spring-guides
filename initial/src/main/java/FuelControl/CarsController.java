package FuelControl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.concurrent.atomic.AtomicLong;

//https://spring-projects.ru/guides/handling-form-submission/

@RestController
public class CarsController {

    @PostMapping(path = "/addCar", consumes = "application/json", produces = "application/json")
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
    }

    @PostMapping(path = "/addCheck", consumes = "application/json", produces = "application/json")
    public String addCheck(@RequestBody CheckModel check) {
        FileWork file = new FileWork();
        if( !file.isFile(check.getNumber()) )
            return "{\"status\":\"error\",\"error\":\"this car is registrated\"}";
        ObjectMapper mapper = new ObjectMapper();
        StringReader reader = new StringReader(file.readFile( check.getNumber() ));
        StringWriter writer = new StringWriter();
        try {
            Car car = mapper.readValue(reader, Car.class);
            car.mileage.add(check.getMilage());
            car.liter.add(check.getLiter());
            car.money.add(check.getMoney());
            mapper.writeValue(writer, car);
            String result = writer.toString();
            file.writeFile(check.getNumber(), result);
            return "{\"status\":\"ok\"}";
        } catch (IOException e) {
            e.printStackTrace();
            return "{\"status\":\"error\",\"error\":\" ¯\\_(ツ)_/¯ \"}";
        }
    }

}
