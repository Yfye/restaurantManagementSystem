package sn.niit.restauranManagementApplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.niit.restauranManagementApplication.domain.Cart;
import sn.niit.restauranManagementApplication.domain.State;
import sn.niit.restauranManagementApplication.domain.User;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findByTokenSession(String tokenSession);

    Cart findByUserAndState(User user, State state);

    List<Cart> findByUser(User user);
}
