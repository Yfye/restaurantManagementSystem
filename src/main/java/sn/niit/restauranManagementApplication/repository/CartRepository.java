package sn.niit.restauranManagementApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.niit.restauranManagementApplication.domain.Cart;
import sn.niit.restauranManagementApplication.domain.User;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findBytokenSession(String tokenSession);

    Cart findByUserAndActive(User user, Boolean active);

    Cart findByUser(User user);
}
