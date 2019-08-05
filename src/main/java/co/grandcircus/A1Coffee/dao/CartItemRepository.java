package co.grandcircus.A1Coffee.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.grandcircus.A1Coffee.entities.CartItem;


@Repository 
@Transactional
public interface CartItemRepository extends JpaRepository<CartItem, Long>{

	CartItem findByProductIdAndUserId(Long userId, Long productId);
}
