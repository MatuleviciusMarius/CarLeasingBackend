package com.marius.leasing.repositories;

import com.marius.leasing.models.FamilyMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface FamilyMemberRepository extends JpaRepository<FamilyMember, Float> {
    Set<FamilyMember> findByPersonId(Long personId);

}
