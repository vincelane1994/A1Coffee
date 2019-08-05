package co.grandcircus.A1Coffee.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.grandcircus.A1Coffee.entities.Product;

@Repository 
@Transactional
public interface ProductRepository extends JpaRepository<Product, Long> {

}
