package com.marius.leasing.controllers;

import com.marius.leasing.models.CreditApplication;
import com.marius.leasing.services.LeasingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/leasing")
public class LeasingController {

    @Autowired
    LeasingService carLeasingService;

    @PostMapping("new")
    public ResponseEntity<Object> createCreditApplication(@RequestBody CreditApplication creditApplication, @RequestParam("pId") Long personId, @RequestParam("vId") Long vehicleId) {
        return carLeasingService.createNewLeasingApplication(creditApplication, vehicleId, personId);
    }

    @GetMapping("getStatus")
    public boolean getApplicationStatusById(@RequestParam(name = "id") Long id) {
        return carLeasingService.getApplicationStatusById(id);
    }

    @PutMapping("update")
    public ResponseEntity<Object> updateApplication(@RequestBody CreditApplication creditApplication){
        return carLeasingService.updateApplication(creditApplication);
    }

    @DeleteMapping("delete")
    public ResponseEntity<Object> deleteApplication(@RequestParam Long id){
        return carLeasingService.deleteApplicationById(id);
    }

    @GetMapping("get")
    public ResponseEntity<Object> getApplicationById(@RequestParam Long id){
        return carLeasingService.getApplicationById(id);
    }

}
