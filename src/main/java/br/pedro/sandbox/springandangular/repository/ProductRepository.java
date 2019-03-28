package br.pedro.sandbox.springandangular.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.pedro.sandbox.springandangular.domain.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{

}
