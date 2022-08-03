package com.annuaire.loginservice.repository;

import com.annuaire.loginservice.model.AccountUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<AccountUser,String> {

    Optional<AccountUser> findByIdentifiant(String identifiant);
}
