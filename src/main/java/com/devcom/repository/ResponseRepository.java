package com.devcom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devcom.entity.Response;

@Repository
public interface ResponseRepository extends JpaRepository<Response,Integer> {

}
