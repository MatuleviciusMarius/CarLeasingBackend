package com.marius.leasing.services;

import com.marius.leasing.models.Car;
import com.marius.leasing.models.CreditApplication;
import com.marius.leasing.models.FamilyMember;
import com.marius.leasing.models.Person;
import com.marius.leasing.repositories.CreditApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@Service
public class LeasingService {
    @Autowired
    private CreditApplicationRepository creditApplicationRepository;
    @Autowired
    private PersonService personService;
    @Autowired
    private CarService carService;

    @Transactional
    public ResponseEntity<Object> createNewLeasingApplication(CreditApplication creditApplication, Long vehicleId, Long personId) {
        Car car = carService.getVehicleById(vehicleId).getBody();
        Person person = personService.getPersonById(personId).getBody();
        if (ObjectUtils.isEmpty(person)) {
            return ResponseEntity.badRequest().body("Failed to create a new credit application. Person with this ID: " + personId + " does not exist");
        }
        if (ObjectUtils.isEmpty(car)) {
            return ResponseEntity.badRequest().body("Failed to create a new credit application, Vehicle with this ID: " + vehicleId + " does not exist");
        }
        CreditApplication newCreditApplication = new CreditApplication();
        newCreditApplication.setCar(car);
        newCreditApplication.setPerson(person);
        newCreditApplication.setLeaseTermMonths(creditApplication.getLeaseTermMonths());
        newCreditApplication.setApproved(determineIfApplicationApproved(person));
        creditApplicationRepository.save(newCreditApplication);

        if (creditApplicationRepository.findById(newCreditApplication.getId()) != null) {
            return ResponseEntity.accepted().body(newCreditApplication);
        } else {
            return ResponseEntity.unprocessableEntity().body("Failed to create a new credit application");
        }
    }

    @Transactional
    public Boolean getApplicationStatusById(Long id) {
        Optional<CreditApplication> creditApplication = creditApplicationRepository.findById(id);
        if (creditApplication.isPresent())
            return creditApplication.get().getApproved();
        else throw new NoSuchElementException("Element with id:" + id + "does not exist");
    }

    @Transactional
    public ResponseEntity<Object> getApplicationById(Long id) {
        Optional<CreditApplication> creditApplication = creditApplicationRepository.findById(id);
        if (creditApplication.isPresent()) {
            return ResponseEntity.ok(creditApplication);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Transactional
    public ResponseEntity<Object> deleteApplicationById(Long id) {
        Optional<CreditApplication> creditApplication = creditApplicationRepository.findById(id);
        if (creditApplication.isPresent()) {
            creditApplicationRepository.deleteById(id);
            return ResponseEntity.ok().body("Application deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Transactional
    public ResponseEntity<Object> updateApplication(CreditApplication newCreditApplication) {
        Optional<CreditApplication> creditApplication = creditApplicationRepository.findById(newCreditApplication.getId());
        if (creditApplication.isPresent()) {
            creditApplicationRepository.save(newCreditApplication);
            return ResponseEntity.ok(creditApplicationRepository.findById(newCreditApplication.getId()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private Boolean determineIfApplicationApproved(Person person) {
        if (person.getIncome().compareTo(BigDecimal.valueOf(600)) < 0) {
            return false;
        }
        Set<FamilyMember> familyMembers = personService.getFamilyMembersByPersonID(person.getId()).getBody();
        for (FamilyMember familyMember : familyMembers) {
            if (familyMember.getIncome().compareTo(BigDecimal.valueOf(600)) < 0) {
                return false;
            }
        }
        return true;
    }

}
