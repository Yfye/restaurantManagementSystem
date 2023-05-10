package sn.niit.restauranManagementApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.niit.restauranManagementApplication.domain.LineItem;

public interface LineItemRepository extends JpaRepository<LineItem,Long>
{
}
