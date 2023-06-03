package sn.niit.restauranManagementApplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sn.niit.restauranManagementApplication.domain.BookTable;

public interface BookTableRepository extends JpaRepository<BookTable, Long> {

    List<BookTable> findByEmail(String email);

}
