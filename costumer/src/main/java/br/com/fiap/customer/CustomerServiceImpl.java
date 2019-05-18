package br.com.fiap.customer;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.stream.Stream;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepo customerRepo;

    @Override
    public CustomerResponse findById(int id) {
        Customer customer = customerRepo.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(NOT_FOUND, "Customer not found!"));

        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setCustomer(customer);
        return customerResponse;
    }


    private void validateGender(String gender)
    {
        Stream.of("MALE", "FEMALE")
                .filter(s -> s.equals(gender.toUpperCase()))
                .findFirst()
                .orElseThrow(() -> new HttpClientErrorException((UNPROCESSABLE_ENTITY), "Gender is invalid!"));
    }

    @Override
    public CustomerCreateResponse create(CustomerRequest customerRequest) {
        validateGender(customerRequest.getGender());

        Customer customer = new Customer();
        customer.setName(customerRequest.getName());
        customer.setLastName(customerRequest.getLastName());
        customer.setAge(customerRequest.getAge());
        customer.setGender(customerRequest.getGender());

        Customer createdCustomer = customerRepo.save(customer);

        CustomerCreateResponse customerCreateResponse = new CustomerCreateResponse();
        customerCreateResponse.setId(createdCustomer.getId());


        return customerCreateResponse;
    }
}
