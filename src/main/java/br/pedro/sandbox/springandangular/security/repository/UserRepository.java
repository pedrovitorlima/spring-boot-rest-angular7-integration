package br.pedro.sandbox.springandangular.security.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.pedro.sandbox.springandangular.security.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
	public User findByLogin(String login);
}
