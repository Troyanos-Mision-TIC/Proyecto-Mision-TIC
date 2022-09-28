package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Profile findByEmail(String email);
}
