package com.upgrade.companyms.company;



import com.upgrade.companyms.company.dto.CompanyDTO;

import java.util.List;

public interface CompanyService {

    List<Company> getAllCompanies();

    boolean updateCompany(Company company, Long id);

    void createCompany(Company company);

    boolean deleteCompanyById(Long id);

    CompanyDTO getCompanyFullDetails(Long companyId);

}