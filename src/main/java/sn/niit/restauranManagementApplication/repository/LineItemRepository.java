package sn.niit.restauranManagementApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sn.niit.restauranManagementApplication.domain.LineItem;

@Repository
public interface LineItemRepository extends JpaRepository<LineItem, Long> {
}
