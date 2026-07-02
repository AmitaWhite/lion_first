/**
 * 다른 유저 공개 프로필 페이지
 * 담당: E - 장인호
 *
 * 상품 상세페이지의 판매자 정보, 채팅방의 상대방 정보 등에서 진입.
 * 마이페이지(본인 전용)와 달리 거래내역/설정 없이 프로필 + 매너온도 + 받은 후기만 보여준다.
 */
'use client';

import { useEffect, useState } from 'react';
import { useParams } from 'next/navigation';
import api from '@/lib/api';
import type { PublicProfile, MannerTemperature as MannerTemperatureType, Review } from '@/types';
import MannerTemperature from '@/components/mypage/MannerTemperature';
import Pagination from '@/components/mypage/Pagination';
import ReviewCard from '@/components/mypage/ReviewCard';
import styles from './page.module.css';

const PAGE_SIZE = 10;
const DEFAULT_AVATAR = '/images/avatar-placeholder.svg';

function formatJoinedAt(createdAt: string | null | undefined) {
  if (!createdAt) return null;
  const date = new Date(createdAt);
  if (Number.isNaN(date.getTime())) return null;
  return `${date.getFullYear()}.${String(date.getMonth() + 1).padStart(2, '0')} 가입`;
}

export default function OtherUserProfilePage() {
  const params = useParams();
  const userId = params?.id as string;

  const [profile, setProfile] = useState<PublicProfile | null>(null);
  const [manner, setManner] = useState<MannerTemperatureType | null>(null);
  const [reviews, setReviews] = useState<Review[]>([]);
  const [page, setPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [notFound, setNotFound] = useState(false);

  useEffect(() => {
    if (!userId) return;
    api.get(`/api/v1/users/${userId}`)
      .then((res) => setProfile(res.data.data))
      .catch(() => setNotFound(true));

    api.get(`/api/v1/users/${userId}/manner`)
      .then((res) => setManner(res.data.data))
      .catch(() => {});
  }, [userId]);

  useEffect(() => {
    if (!userId) return;
    api.get(`/api/v1/users/${userId}/reviews?page=${page}&size=${PAGE_SIZE}`)
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
  }, [userId, page]);

  if (notFound) {
    return (
      <section className={styles.page}>
        <div className={styles.empty}>존재하지 않는 사용자입니다.</div>
      </section>
    );
  }

  const joinedAt = formatJoinedAt(profile?.createdAt);

  return (
    <section className={styles.page}>
      <div className={styles.profileCard}>
        <img
          src={profile?.profileImageUrl || DEFAULT_AVATAR}
          alt="프로필"
          className={styles.avatar}
        />
        <div className={styles.profileInfo}>
          <p className={styles.nickname}>{profile?.nickname ?? '...'}</p>
          {joinedAt && <p className={styles.joinedAt}>{joinedAt}</p>}
          <MannerTemperature score={manner?.temperature ?? 50} />
          <p className={styles.reviewCount}>받은 후기 {manner?.reviewCount ?? 0}개</p>
        </div>
      </div>

      <div className={styles.reviewSection}>
        <h2 className={styles.sectionTitle}>거래 후기</h2>
        {reviews.length === 0 ? (
          <div className={styles.empty}>받은 후기가 없습니다.</div>
        ) : (
          <>
            <div className={styles.list}>
              {reviews.map((r) => (
                <ReviewCard key={r.id} review={r} />
              ))}
            </div>
            <Pagination page={page} totalPages={totalPages} onPageChange={setPage} />
          </>
        )}
      </div>
    </section>
  );
}
