package com.upgrade.companyms.company;

import com.upgrade.companyms.company.clients.JobClient;
import com.upgrade.companyms.company.clients.ReviewClient;
import com.upgrade.companyms.company.dto.CompanyDTO;
import com.upgrade.companyms.company.dto.JobDTO;
import com.upgrade.companyms.company.dto.ReviewDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Fallback;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepo companyRepo;

    @Autowired
    private JobClient jobClient;

    @Autowired
    private ReviewClient reviewClient;


    public CompanyServiceImpl(CompanyRepo companyRepo) {
        this.companyRepo = companyRepo;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepo.findAll();
    }

    @Override
    public boolean updateCompany(Company company, Long id) {
        Optional<Company> companyOptional = companyRepo.findById(id);
        if (companyOptional.isPresent()) {
            Company companyToUpdate = companyOptional.get();
            companyToUpdate.setDescription(company.getDescription());
            companyToUpdate.setName(company.getName());
            companyRepo.save(companyToUpdate);
            return true;
        }
        return false;
    }

    @Override
    public void createCompany(Company company) {
        companyRepo.save(company);
    }

    @Override
    public boolean deleteCompanyById(Long id) {
        if(companyRepo.existsById(id)) {
            companyRepo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    @CircuitBreaker(name = "companyCircuteBreaker", fallbackMethod = "fullDetailsFallBack")
    public CompanyDTO getCompanyFullDetails(Long companyId) {

        Company company = companyRepo.findById(companyId).orElse(null);

        List<JobDTO> jobs = jobClient.jobsByCompId(companyId);

        List<ReviewDTO> reviews = reviewClient.reviewsByCompId(companyId);

        CompanyDTO companyDTO = new CompanyDTO();

        companyDTO.setId(companyId);
        companyDTO.setName(company.getName());
        companyDTO.setDescription(company.getDescription());

        companyDTO.setJobs(jobs);
        companyDTO.setReviews(reviews);

        return companyDTO;
    }

    public CompanyDTO fullDetailsFallBack(Exception ex){

        CompanyDTO cdto = new CompanyDTO();
        cdto.setName("Service Unavailable");
        cdto.setDescription("we are facing some issues, pleas try again later");

        return cdto;
    }

}