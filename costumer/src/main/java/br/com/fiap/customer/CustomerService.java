package br.com.fiap.customer;

public interface CustomerService {

    CustomerResponse findById(int id);
    CustomerCreateResponse create(CustomerRequest customerRequest);

}
