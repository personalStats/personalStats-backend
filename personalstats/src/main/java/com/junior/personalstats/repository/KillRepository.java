package com.junior.personalstats.repository;

import java.math.BigInteger;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.junior.personalstats.model.Kill;

public interface KillRepository extends MongoRepository<Kill, BigInteger>{


}
