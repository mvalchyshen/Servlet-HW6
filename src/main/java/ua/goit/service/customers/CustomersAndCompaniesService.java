package ua.goit.service.customers;

import ua.goit.dao.CustomersAndCompaniesRepository;
import ua.goit.dao.model.CustomersAndCompaniesDAO;
import ua.goit.dto.CustomersAndCompaniesDTO;

import java.util.List;
import java.util.stream.Collectors;

public class CustomersAndCompaniesService {
    private CustomersAndCompaniesRepository repository;

    public CustomersAndCompaniesService(CustomersAndCompaniesRepository repository) {
        this.repository = repository;
    }

    public CustomersAndCompaniesDTO create(CustomersAndCompaniesDTO customersAndCompaniesDTO) {
        CustomersAndCompaniesDAO customersAndCompaniesDAO = CustomersAndCompaniesConverter.toCustomerAndCompaniesCollection(customersAndCompaniesDTO);
        repository.create(customersAndCompaniesDAO);
        CustomersAndCompaniesDAO savedCustomersAndCompaniesDAO = repository.findUniqueRecord(
                customersAndCompaniesDTO.getCompanyId(),
                customersAndCompaniesDTO.getCustomerId(),
                customersAndCompaniesDTO.getProjectId()
        );
        return CustomersAndCompaniesConverter.fromCustomerAndCompanies(savedCustomersAndCompaniesDAO);
    }

    public CustomersAndCompaniesDTO update(CustomersAndCompaniesDTO customersAndCompaniesDTO) {
        CustomersAndCompaniesDAO customersAndCompaniesDAO = CustomersAndCompaniesConverter.toCustomerAndCompaniesCollection(customersAndCompaniesDTO);
        repository.update(customersAndCompaniesDAO);
        CustomersAndCompaniesDAO updatedCustomersAndCompaniesDAO = repository.findUniqueRecord(
                customersAndCompaniesDTO.getCompanyId(),
                customersAndCompaniesDTO.getCustomerId(),
                customersAndCompaniesDTO.getProjectId()
        );
        return CustomersAndCompaniesConverter.fromCustomerAndCompanies(updatedCustomersAndCompaniesDAO);
    }

    public void deleteUniqueRecord(long companyId, long customerId, long projectId) {
        repository.deleteUniqueRecord(companyId, customerId, projectId);
    }

    public void deleteForCompany(long companyId) {
        repository.deleteForCompany(companyId);
    }

    public void deleteForCustomer(long customerId) {
        repository.deleteForCustomer(customerId);
    }

    public void deleteForProject(long projectId) {
        repository.deleteForProject(projectId);
    }

    public List<CustomersAndCompaniesDTO> findByProjectId(long projectId) {
        List<CustomersAndCompaniesDAO> customersAndCompaniesDAOList = repository.findForProject(projectId);
        return customersAndCompaniesDAOList.stream()
                .map(CustomersAndCompaniesConverter::fromCustomerAndCompanies)
                .collect(Collectors.toList());
    }

    public List<CustomersAndCompaniesDTO> findByCompanyId(long companyId) {
        List<CustomersAndCompaniesDAO> customersAndCompaniesDAOList = repository.findForCompany(companyId);
        return customersAndCompaniesDAOList.stream()
                .map(CustomersAndCompaniesConverter::fromCustomerAndCompanies)
                .collect(Collectors.toList());
    }
}
