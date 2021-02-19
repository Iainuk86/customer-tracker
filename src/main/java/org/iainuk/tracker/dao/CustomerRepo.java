package org.iainuk.tracker.dao;

import org.iainuk.tracker.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepo extends CrudRepository<Customer, Integer> {

    List<Customer> findAllByOrderByFirstName();

}
