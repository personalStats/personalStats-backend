package com.junior.personalstats.repository;

import org.springframework.data.repository.CrudRepository;

import com.junior.personalstats.model.ProfileDTO;

public interface ProfileRepository extends CrudRepository<ProfileDTO, Integer>{

}
