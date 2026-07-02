'use client';

import { useState, useEffect } from 'react';
import api from '@/lib/api';
import { Review } from '@/types';
import Pagination from '@/components/mypage/Pagination';
import ReviewCard from '@/components/mypage/ReviewCard';
import styles from '../page.module.css';

const PAGE_SIZE = 10;

// mypage 도메인 리뷰 카드는 섹션 배경(--color-surface-container-lowest)과 동일해서
// otheruser 기본값(--color-surface)과 다르게 덮어씀
const reviewCardStyle = { background: 'var(--color-surface-container-lowest)' };

export default function ReviewsPage() {
  const [reviews, setReviews] = useState<Review[]>([]);
  const [page, setPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);

  useEffect(() => {
    api.get('/api/v1/users/me')
      .then((res) => {
        const userId = res.data.id;
        return api.get(`/api/v1/users/${userId}/reviews?page=${page}&size=${PAGE_SIZE}`);
      })
      .then((res) => {
        const fetchedTotalPages = res.data.data.totalPages;
        if (page > 0 && page >= fetchedTotalPages) {
          setPage(fetchedTotalPages - 1);
          return;
        }
        setReviews(res.data.data.content);
        setTotalPages(fetchedTotalPages);
      })
      .catch(() => setReviews([]));
  }, [page]);

  return (
    <section>
      <h1 className={styles.title}>거래 후기</h1>

      {reviews.length === 0 ? (
        <div className={styles.empty}>받은 후기가 없습니다.</div>
      ) : (
        <>
        <div className={styles.list}>
          {reviews.map((r) => (
            <ReviewCard key={r.id} review={r} style={reviewCardStyle} />
          ))}
        </div>
        <Pagination page={page} totalPages={totalPages} onPageChange={setPage} />
        </>
      )}
    </section>
  );
}