package br.com.fiap.carservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarResourceTest3 {

    @Autowired
    private CarResource carResource;

    @Test
    public void shouldFindCarsByColourTest()
    {
        String colour = "preto";
        String model = "corvette";

        Car car = new Car();

        car.setColour(colour);
        car.setModel(model);

        carResource.save(car);

        List<Car> cars = carResource.findCarByColour(colour);

        for (Car c : cars) {
            assertEquals(c.getColour(), car.getColour());
            assertEquals(c.getModel(), car.getModel());
        }


    }


}
