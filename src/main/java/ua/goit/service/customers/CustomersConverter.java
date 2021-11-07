package ua.goit.service.customers;

import ua.goit.dao.model.CustomersDAO;
import ua.goit.dto.CustomersDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomersConverter {
    public static CustomersDAO toCustomer(CustomersDTO customersDTO){
        return new CustomersDAO(customersDTO.getCustomerId(), customersDTO.getCustomerName());
    }

    public static CustomersDTO fromCustomer(CustomersDAO customersDAO) {
        return new CustomersDTO(customersDAO.getCustomerId(), customersDAO.getCustomerName());
    }

    public static CustomersDAO toCustomer(ResultSet resultSet) throws SQLException {
        CustomersDAO customersDAO = new CustomersDAO();
        while (resultSet.next()){
            customersDAO.setCustomerId(resultSet.getLong("customer_id"));
            customersDAO.setCustomerName(resultSet.getString("customer_name"));
        }
        return customersDAO;
    }
}
