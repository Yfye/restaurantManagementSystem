package sn.niit.restauranManagementApplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sn.niit.restauranManagementApplication.domain.BookTable;

public interface BookTableRepository extends JpaRepository<BookTable, Long> {

    @Query(value = "select * from book_table b where b.name like %:keyword% or b.email like %:keyword%", nativeQuery = true)
    List<BookTable> findByKeyword(@Param("keyword") String keyword);

    List<BookTable> findByEmail(String email);

}
