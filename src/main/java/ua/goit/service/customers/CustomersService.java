package ua.goit.service.customers;

import ua.goit.dao.Repository;
import ua.goit.dao.model.CustomersDAO;
import ua.goit.dto.CustomersDTO;

public class CustomersService {
    private Repository<CustomersDAO> repository;

    public CustomersService(Repository<CustomersDAO> repository) {
        this.repository = repository;
    }

    public CustomersDTO create(CustomersDTO customersDTO) {
        CustomersDAO customersDAO = CustomersConverter.toCustomer(customersDTO);
        repository.create(customersDAO);
        CustomersDAO savedCustomersDAO = repository.findById(customersDAO.getCustomerId());
        return CustomersConverter.fromCustomer(savedCustomersDAO);
    }

    public CustomersDTO update(CustomersDTO customersDTO) {
        CustomersDAO customersDAO = CustomersConverter.toCustomer(customersDTO);
        repository.update(customersDAO);
        CustomersDAO updatedCustomersDAO = repository.findByUniqueValue(customersDTO.getCustomerName());
        return CustomersConverter.fromCustomer(updatedCustomersDAO);
    }
}
