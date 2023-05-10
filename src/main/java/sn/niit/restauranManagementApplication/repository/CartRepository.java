package sn.niit.restauranManagementApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.niit.restauranManagementApplication.domain.Cart;

public interface CartRepository  extends JpaRepository<Cart, Long>
{
    Cart findBytokenSession(String tokenSession);

}
