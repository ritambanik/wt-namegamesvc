package com.willowtree.test;

import com.willowtree.test.data.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;


@Service
public class NameGameService {

    private final Logger LOG = LoggerFactory.getLogger(NameGameService.class);
    private RestTemplate restTemplate;

    public NameGameService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${namegame-svc.default.name-options}")
    private int defaultNumOfNameOptions;

    @Value("${namegame-svc.default.image-options}")
    private int defaultNumOfImageOptions;

    @Value("${namegame-svc.url}")
    private String url;

    public NameChallenge getNameChallenge(Integer count) {
        LOG.debug("# of options = {}", count);
        if (count == null) {
            count = defaultNumOfNameOptions;
        }
        List<Profile> profiles;
        if (getAllProfiles().isPresent()) {
            profiles = getAllProfiles().get().stream().limit(count).collect(Collectors.toList());
            Profile any = profiles.get(new Random().nextInt(profiles.size()));
            List<ImageRecord> images = profiles.stream().map(
                    profile -> new ImageRecord(profile.getId(), profile.getHeadshot())).collect(Collectors.toList());
            return new NameChallenge(new NameRecord(any.getFirstName(), any.getLastName(), any.getId()), images);
        }
        throw new RuntimeException("Profile information cannot be retrieved");
    }

    public ImageChallenge getImageChallenge(Integer count) {
        LOG.debug("# of options = {}", count);
        if (count == null) {
            count = defaultNumOfImageOptions;
        }
        List<Profile> profiles;
        if (getAllProfiles().isPresent()) {
            profiles = getAllProfiles().get().stream().limit(count).collect(Collectors.toList());
            Profile any = profiles.get(new Random().nextInt(profiles.size()));
            List<NameRecord> names = profiles.stream().map(profile -> new NameRecord(
                    profile.getFirstName(), profile.getLastName(), profile.getId())).collect(Collectors.toList());
            return new ImageChallenge(new ImageRecord(any.getId(), any.getHeadshot()), names);
        }
        throw new RuntimeException("Profile information cannot be retrieved");

    }

    public NameChallenge getMattChallenge() {
        List<Profile> profiles;
        if (getAllProfiles().isPresent()) {
            profiles = getAllProfiles().get().stream().filter(profile -> (
                    "Mat".equals(profile.getFirstName()) || "Matt".equals(profile.getFirstName()))).collect(Collectors.toList());
            Profile any = profiles.get(new Random().nextInt(profiles.size()));
            List<ImageRecord> images = profiles.stream().map(
                    profile -> new ImageRecord(profile.getId(), profile.getHeadshot())).collect(Collectors.toList());
            return new NameChallenge(new NameRecord(any.getFirstName(), any.getLastName(), any.getId()), images);
        }
        throw new RuntimeException("Profile information cannot be retrieved");
    }

    public boolean validateResponse(ChallengeResponse response) {
        if (response.getName().getId().equals(response.getImage().getId())) {
            LOG.debug("Matched by {} for question {}", response.getUser(), response.getQuestionId());
            return true;
        }
        return false;
    }

    /**
     *
     * @return an optional of all profiles or absent
     */
    private Optional<List<Profile>> getAllProfiles() {
        ResponseEntity<List<Profile>> response = restTemplate.exchange(
                url, HttpMethod.GET, null,new ParameterizedTypeReference<List<Profile>>(){});
       return  Optional.ofNullable(response.getBody());
    }
}
