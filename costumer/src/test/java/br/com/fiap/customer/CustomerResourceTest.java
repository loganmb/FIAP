package br.com.fiap.customer;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class CustomerResourceTest {

    @Autowired
    private CustomerRepo customerRepo;

    @LocalServerPort
    private int port;

    public void setup()
    {
        stubCreateCustomer();
        RestAssured.baseURI = "https://localhost";
        RestAssured.port = this.port;
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .addHeader("Content-Type", "application/json")
        .build();
    }

    private void stubCreateCustomer()
    {
        Customer customer = new Customer();
        customer.setName("Logan");
        customer.setLastName("Baruch");
        customer.setGender("male");
        customer.setAge(24);

        customerRepo.save(customer);

    }


    @Test
    public void shouldFindCustomerById()
    {
        RestAssured.get("/customer/1")
        .then()
        .assertThat()
        .statusCode(200)
        .body("name", Matchers.is("Logan"))
        .body("last_name", Matchers.is("Baruch"))
        .body("gender", Matchers.is("male"))
        .body("age", Matchers.is(24));
    }

    @Test
    public void cannotFindPersonById()
    {

        RestAssured.get("/people/300")
                .then()
                .assertThat()
                .statusCode(404)
                .body("messege_error", Matchers.is("Not Found"));

    }


    @Test
    public void shouldCreateCustomer()
    {
        CustomerRequest customerRequest = new CustomerRequest();

        customerRequest.setName("Logan");
        customerRequest.setLastName("Baruch");
        customerRequest.setAge(24);
        customerRequest.setGender("male");

        RestAssured.given()
                .body(customerRequest)
                .post("/customers/")
                .then()
                .assertThat()
                .statusCode(201)
                .body("customerId", Matchers.any(Integer.class));

    }

    @Test
    public void cannotCreateCustomerWhenGenderIsInvalid()
    {
        CustomerRequest customerRequest = new CustomerRequest();

        customerRequest.setName("Logan");
        customerRequest.setLastName("Baruch");
        customerRequest.setAge(24);
        customerRequest.setGender("none");

        RestAssured.given()
                .body(customerRequest)
                .post("/customers/")
                .then()
                .assertThat()
                .statusCode(422)
                .body("message_error", Matchers.is("Gender is invalid!"));

    }

}
