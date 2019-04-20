package com.willowtree.test;

import com.willowtree.test.data.*;
import com.willowtree.test.repository.ImageChallengeRepository;
import com.willowtree.test.repository.MetricsRepository;
import com.willowtree.test.repository.NameChallengeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;


@Service
public class NameGameService {

    private final Logger LOG = LoggerFactory.getLogger(NameGameService.class);
    private RestTemplate restTemplate;
    @Autowired
    private MetricsRepository metricsRepository;
    @Autowired
    private NameChallengeRepository nameChallengeRepository;
    @Autowired
    private ImageChallengeRepository imageChallengeRepository;

    public NameGameService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${namegame-svc.default.name-options}")
    private int defaultNumOfNameOptions;

    @Value("${namegame-svc.default.image-options}")
    private int defaultNumOfImageOptions;

    @Value("${namegame-svc.url}")
    private String url;

    /**
     * Fetches existing name challenge or create a new name challenge.
     *
     * @param count number of options to be shown; it's optional
     * @param questionId existing question id; it's optional
     * @return existing or new name challenge
     */
    public NameChallenge getNameChallenge(Integer count, String questionId) {
        LOG.debug("# of options = {} and question {}", count, questionId);
        if (count == null) {
            count = defaultNumOfNameOptions;
        }
        List<Profile> profiles;
        if(!StringUtils.isEmpty(questionId)){
            return nameChallengeRepository.findByQuestionId(questionId);
        } else {
            if(getAllProfiles().isPresent()) {
                profiles = getAllProfiles().get().stream().limit(count).collect(Collectors.toList());
                Profile any = profiles.get(new Random().nextInt(profiles.size()));
                List<ImageRecord> images = profiles.stream().map(
                        profile -> new ImageRecord(profile.getId(), profile.getHeadshot())).collect(Collectors.toList());
                NameChallenge nameChallenge = new NameChallenge(new NameRecord(any.getFirstName(), any.getLastName(), any.getId()), images);
                nameChallengeRepository.save(nameChallenge);
                return nameChallenge;
            }
            throw new RuntimeException("Profile information cannot be retrieved");
        }
    }

    /**
     * Fetches existing image challenge or create new image challenge.
     *
     * @param count number of options to be shown; it's optional
     * @param questionId existing question id; it's optional
     * @return existing or new name challenge
     */
    public ImageChallenge getImageChallenge(Integer count, String questionId) {
        LOG.debug("# of options = {} and question {}", count, questionId);
        if (count == null) {
            count = defaultNumOfImageOptions;
        }
        List<Profile> profiles;
        if (!StringUtils.isEmpty(questionId)) {
            return imageChallengeRepository.findByQuestionId(questionId);
        } else {
            if (getAllProfiles().isPresent()) {
                profiles = getAllProfiles().get().stream().limit(count).collect(Collectors.toList());
                Profile any = profiles.get(new Random().nextInt(profiles.size()));
                List<NameRecord> names = profiles.stream().map(profile -> new NameRecord(
                        profile.getFirstName(), profile.getLastName(), profile.getId())).collect(Collectors.toList());
                ImageChallenge imageChallenge = new ImageChallenge(new ImageRecord(any.getId(), any.getHeadshot()), names);
                imageChallengeRepository.save(imageChallenge);
                return imageChallenge;
            }
            throw new RuntimeException("Profile information cannot be retrieved");
        }
    }

    /**
     * Fetches name challenge for Mat(t).
     *
     * @param questionId existing question id; it's optional
     * @return existing or new name challenge for Mat(t)
     */
    public NameChallenge getMattChallenge(String questionId) {
        LOG.debug("Question {} ", questionId);
        List<Profile> profiles;
        if(!StringUtils.isEmpty(questionId)){
            return nameChallengeRepository.findByQuestionId(questionId);
        } else {
            if (getAllProfiles().isPresent()) {
                profiles = getAllProfiles().get().stream().filter(profile -> (
                        "Mat".equals(profile.getFirstName())
                                || "Matt".equals(profile.getFirstName()))).collect(Collectors.toList());
                Profile any = profiles.get(new Random().nextInt(profiles.size()));
                List<ImageRecord> images = profiles.stream().map(
                        profile -> new ImageRecord(profile.getId(), profile.getHeadshot())).collect(Collectors.toList());
                NameChallenge nameChallenge = new NameChallenge(
                        new NameRecord(any.getFirstName(), any.getLastName(), any.getId()), images);
                nameChallengeRepository.save(nameChallenge);
                return nameChallenge;
            }
            throw new RuntimeException("Profile information cannot be retrieved");
        }
    }

    public boolean validateResponse(ChallengeResponse response) {
        Event event = new Event();
        event.setUserId(response.getUser());
        event.setQuestionId(response.getQuestionId());
        if (response.getName().getId().equals(response.getImage().getId())) {
            LOG.debug("Matched by {} for question {}", response.getUser(), response.getQuestionId());
            event.setCorrect(true);
            metricsRepository.save(event);
            return true;
        }
        event.setCorrect(false);
        metricsRepository.save(event);
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
