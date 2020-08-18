package com.marius.leasing.repositories;

import com.marius.leasing.models.CreditApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditApplicationRepository extends JpaRepository<CreditApplication, Long> {
}
