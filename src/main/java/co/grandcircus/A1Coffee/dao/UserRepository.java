package co.grandcircus.A1Coffee.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import co.grandcircus.A1Coffee.entities.User;


public interface UserRepository extends JpaRepository<User, Long>{

	User findByEmailAndPassword(String email, String password);
	
}
