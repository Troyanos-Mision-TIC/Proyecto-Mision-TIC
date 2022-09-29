package com.example.demo.services;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.Role;
import com.example.demo.model.Profile;
import com.example.demo.repositories.ProfileRepository;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public Profile createProfile(Profile profile) {
        return this.profileRepository.save(profile);
    }

    public Profile getOrCreateProfile(Map<String, Object> data) {
        String email = (String) data.get("email");
        Profile dbProfile = profileRepository.findByEmail(email);
        
        if (dbProfile == null) {
            String image = (String) data.get("picture");
            String auth0Id = (String) data.get("sub");
            Profile newProfile = new Profile(email, auth0Id, image, null, null);
            newProfile.setRole(Role.OPERATOR);
            return createProfile(newProfile);
        }
        return dbProfile;
    }

}
