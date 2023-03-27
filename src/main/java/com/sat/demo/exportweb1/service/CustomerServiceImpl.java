package com.sat.demo.exportweb1.service;

import com.sat.demo.exportweb1.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Customer> getCustomersList() {
        String sql = "select customer_id,contact,country,dob,email,first_name,last_name,gender from customers_info";
        List<Customer> customersList = jdbcTemplate.query(sql,
                (rs, rowNum) -> new Customer(rs.getInt("customer_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("gender"),
                        rs.getString("contact"),
                        rs.getString("country"),
                        rs.getString("dob")
                )
        );
        return customersList;
    }
}
