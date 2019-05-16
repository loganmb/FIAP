package br.com.fiap.carservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;


import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CarResourceMockTest {

    @Mock
    private CarResource carResource;

    @Test
    public void shouldFindCarsByColourTest()
    {
        String colour = "preto";
        String model = "corvette";

        Car car = new Car();

        car.setColour(colour);
        car.setModel(model);

        List<Car> cars = Arrays.asList(car);


        //
        Mockito.when(carResource.findCarByColour(colour)).thenReturn(cars);

        // execute

        List<Car> carResponse = carResource.findCarByColour(colour);


        for (Car c : cars) {
            assertEquals(c.getColour(), car.getColour());
            assertEquals(c.getModel(), car.getModel());
        }


    }


}
