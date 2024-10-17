package com.advancia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.advancia.model.Impasto;

@Repository
public interface ImpastoRepository extends JpaRepository<Impasto, Integer> {

}
