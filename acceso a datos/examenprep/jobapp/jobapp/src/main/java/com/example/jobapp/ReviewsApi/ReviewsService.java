package com.example.jobapp.ReviewsApi;

import java.util.List;
import java.util.Optional;

public interface ReviewsService {
    List<Reviws> findAll();
    Optional<Reviws> getReviewsbyId(Long id);
    Reviws createReview(Reviws review);
    Reviws updateReview(Long id, Reviws reviws);
    boolean deleteReviews(Long id);
}
