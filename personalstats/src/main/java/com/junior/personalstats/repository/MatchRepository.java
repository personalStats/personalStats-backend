package com.junior.personalstats.repository;

import java.math.BigInteger;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.junior.personalstats.model.Match;

public interface MatchRepository extends MongoRepository<Match, BigInteger>{

}
