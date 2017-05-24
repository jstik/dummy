package com.example.dao;

import com.example.model.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Julia on 03.05.2017.
 */
@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {
   /* List<Customer> findByEmail(String email);

    List<Customer> findByDate(Date date);*/

    /*// custom query example and return a stream
    @Query("select c from Customer c where c.email = :email")
    Stream<Customer> findByEmailReturnStream(@Param("email") String email);*/

}
