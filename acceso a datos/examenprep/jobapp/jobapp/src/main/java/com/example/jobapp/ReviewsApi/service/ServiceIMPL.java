package com.example.jobapp.ReviewsApi.service;

import com.example.jobapp.ReviewsApi.Reviws;
import com.example.jobapp.ReviewsApi.repository.reviewsRepository;
import com.example.jobapp.ReviewsApi.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ServiceIMPL implements ReviewsService {
    @Autowired
    reviewsRepository ReviewsRepository;

    @Override
    public List<Reviws> findAll() {
        return ReviewsRepository.findAll();
    }

    public Optional<Reviws> getReviewsbyId(Long id) {
        return ReviewsRepository.findById(id);
    }

    @Override
    public Reviws createReview(Reviws review) {
        ReviewsRepository.save(review);
        return review;
    }


    @Override
    public Reviws updateReview(Long id, Reviws reviws) {
        Optional<Reviws> reviws1 = ReviewsRepository.findById(id);
        if (reviws1.isPresent()){
            Reviws reviwstoupdate = reviws1.get();
            reviwstoupdate.setContent(reviws.getContent());
            reviwstoupdate.setCompany(reviws.getCompany());
        }
        return reviws;
    }

    @Override
    public boolean deleteReviews(Long id) {
        if (ReviewsRepository.existsById(id)) {
            ReviewsRepository.deleteById(id);
            return true;
        }else
            return false;
    }
}
