'use client';

/**
 * 공용 리뷰 카드 컴포넌트
 * 담당: E - 장인호
 *
 * mypage/reviews, otheruser/[id]에서 공통으로 사용.
 * style을 넘기면 배경색 등 페이지별 스타일을 덮어쓸 수 있음(인라인 스타일이라 항상 우선 적용됨).
 */
import type { Review } from '@/types';
import type { CSSProperties } from 'react';
import styles from './ReviewCard.module.css';

function StarDisplay({ rating }: { rating: number }) {
  return (
    <div className={styles.stars}>
      {'★'.repeat(rating)}{'☆'.repeat(5 - rating)}
    </div>
  );
}

interface ReviewCardProps {
  review: Review;
  style?: CSSProperties;
}

export default function ReviewCard({ review, style }: ReviewCardProps) {
  return (
    <div className={styles.reviewCard} style={style}>
      {review.postId != null && <p className={styles.reviewPost}>상품 #{review.postId}</p>}
      <StarDisplay rating={review.rating} />
      <p className={styles.content}>{review.content}</p>
      <p className={styles.meta}>
        {review.reviewerNickname ?? '알 수 없음'} · {new Date(review.createdAt).toLocaleDateString()}
      </p>
    </div>
  );
}
