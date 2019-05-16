package br.com.fiap.carservice;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "cars", collectionResourceRel = "cars")
public interface CarResource extends PagingAndSortingRepository<Car, Integer> {

    List<Car> findCarByColour(@Param("colour") String colour);
}
