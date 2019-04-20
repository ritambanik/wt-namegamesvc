package com.willowtree.test;

import com.willowtree.test.data.ChallengeResponse;
import com.willowtree.test.data.ImageChallenge;
import com.willowtree.test.data.NameChallenge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/challenge")
public class NameGameController {

    @Autowired
    private NameGameService service;

    @GetMapping("/challenge/images")
    public ResponseEntity<NameChallenge> getImagesWithName(
            @RequestParam(value = "count", required = false) Integer count,
            @RequestParam(value = "questionId", required = false) String questionId) {
        return new ResponseEntity<NameChallenge>(service.getNameChallenge(count, questionId), HttpStatus.OK);
    }

    @GetMapping("/challenge/names")
    public ResponseEntity<ImageChallenge> getNamesWithImage(
            @RequestParam(value = "count", required = false) Integer count,
            @RequestParam(value = "questionId", required = false) String questionId) {
        return new ResponseEntity<ImageChallenge>(service.getImageChallenge(count, questionId), HttpStatus.OK);
    }

    @GetMapping("/challenge/allMatts")
    public ResponseEntity<NameChallenge> getImagesForMatt(@RequestParam(value = "questionId", required = false) String questionId) {
        return new ResponseEntity<NameChallenge>(service.getMattChallenge(questionId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Boolean> submitResponse(
            @RequestBody ChallengeResponse response) {
        return new ResponseEntity<Boolean>(service.validateResponse(response), HttpStatus.OK);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleError(RuntimeException ex) {
        return new ResponseEntity<>(
                "Request failed with message: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
