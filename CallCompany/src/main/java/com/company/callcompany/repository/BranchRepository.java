package com.company.callcompany.repository;

import com.company.callcompany.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;
@RepositoryRestResource(path ="branch",collectionResourceRel = "listOfBranches")
public interface BranchRepository extends JpaRepository<Branch, UUID> {
}
