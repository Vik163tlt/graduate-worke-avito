package ru.skypro.homework.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.service.AdsService;


@Slf4j
@AllArgsConstructor
@RestController
@CrossOrigin(value = "http://localhost:3000")
public class SearchController {

    private final AdsService adsService;

    @GetMapping
    public ResponseEntity<?> searchAds(@PathVariable String query) throws UserNotFoundException {
        return ResponseEntity.ok().body(adsService.searchAds(query));
    }
}
