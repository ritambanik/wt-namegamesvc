package com.willowtree.test;

import com.willowtree.test.data.ChallengeResponse;
import com.willowtree.test.data.ImageChallenge;
import com.willowtree.test.data.NameChallenge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/name-game")
public class NameGameController {

    @Autowired
    private NameGameService service;

    @GetMapping("/name-game/images")
    public ResponseEntity<NameChallenge> getImagesWithName(
            @RequestParam(value = "count", required = false) Integer count) {
        return new ResponseEntity<NameChallenge>(service.getNameChallenge(count), HttpStatus.OK);
    }

    @GetMapping("/name-game/names")
    public ResponseEntity<ImageChallenge> getNamesWithImage(
            @RequestParam(value = "count", required = false) Integer count) {
        return new ResponseEntity<ImageChallenge>(service.getImageChallenge(count), HttpStatus.OK);
    }

    @GetMapping("/name-game/allMatts")
    public ResponseEntity<NameChallenge> getImagesForMatt() {
        return new ResponseEntity<NameChallenge>(service.getMattChallenge(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Boolean> submitResponse(
            @RequestBody ChallengeResponse response) {
        return new ResponseEntity<Boolean>(service.validateResponse(response), HttpStatus.OK);
    }

}
