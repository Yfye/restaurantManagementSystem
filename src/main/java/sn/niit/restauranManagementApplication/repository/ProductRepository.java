package sn.niit.restauranManagementApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sn.niit.restauranManagementApplication.domain.Category;
import sn.niit.restauranManagementApplication.domain.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "select * from produit p where p.name like %:keyword% or p.price like %:keyword%", nativeQuery = true)
    List<Product> findByKeyword(@Param("keyword") String keyword);

    List<Product> findByCategory(Category category);
}
