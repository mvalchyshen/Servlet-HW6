package ua.goit.service.companies;

import ua.goit.dao.Repository;
import ua.goit.dao.model.CompaniesDAO;
import ua.goit.dto.CompaniesDTO;

public class CompaniesService {
    private Repository<CompaniesDAO> repository;

    public CompaniesService(Repository<CompaniesDAO> repository){
        this.repository = repository;
    }

    public CompaniesDTO create(CompaniesDTO companiesDTO) {
        CompaniesDAO companiesDAO = CompaniesConverter.toCompaniesCollection(companiesDTO);
        repository.create(companiesDAO);
        CompaniesDAO savedCompanyDAO = repository.findById(companiesDAO.getCompanyId());
        return CompaniesConverter.fromCompany(savedCompanyDAO);
    }

    public CompaniesDTO update(CompaniesDTO companiesDTO) {
        CompaniesDAO companiesDAO = CompaniesConverter.toCompaniesCollection(companiesDTO);
        repository.update(companiesDAO);
        CompaniesDAO updatedCompaniessDAO = repository.findByUniqueValue(companiesDTO.getCompanyName());
        return CompaniesConverter.fromCompany(updatedCompaniessDAO);
    }

    public void delete (String name) {
        repository.delete(name);
    }

    public CompaniesDTO findByName (String name) {
        return CompaniesConverter.fromCompany(repository.findByUniqueValue(name));
    }

}
