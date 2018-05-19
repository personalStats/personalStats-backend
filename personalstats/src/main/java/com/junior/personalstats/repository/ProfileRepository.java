package com.junior.personalstats.repository;

import java.math.BigInteger;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.junior.personalstats.model.Profile;

public interface ProfileRepository extends MongoRepository<Profile, BigInteger>{

}
