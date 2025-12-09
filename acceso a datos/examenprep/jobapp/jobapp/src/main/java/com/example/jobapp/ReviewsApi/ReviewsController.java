package com.example.jobapp.ReviewsApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reviews")
public class ReviewsController {

    @Autowired
    ReviewsService reviewsService;

    @GetMapping
    public ResponseEntity<List<Reviws>> getAllJobs() {
        return ResponseEntity.ok(reviewsService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<? extends Object> getJobById(@PathVariable Long id) {
        Optional<Reviws> Review= reviewsService.getReviewsbyId(id);
        return Review != null ? ResponseEntity.ok(Review) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Reviws> createReview(@RequestBody Reviws reviws) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewsService.createReview(reviws));
    }

    @PutMapping("/{id}")
    public ResponseEntity<? extends Object> updateJob(@PathVariable Long id, @RequestBody Reviws reviws) {
        Reviws updated = reviewsService.updateReview(id, reviws);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        return reviewsService.deleteReviews(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

}
