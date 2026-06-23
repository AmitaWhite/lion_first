package com.paprika.domain.mypage.repository;

import com.paprika.domain.mypage.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 담당: E - 장인호
 */
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByRevieweeIdOrderByCreatedAtDesc(Long revieweeId);

    boolean existsByTransactionIdAndReviewerId(Long transactionId, Long reviewerId);

    @Query("SELECT COALESCE(SUM(r.mannerScore), 0) FROM Review r WHERE r.revieweeId = :userId")
    Integer sumMannerScoreByRevieweeId(@Param("userId") Long userId);
}
