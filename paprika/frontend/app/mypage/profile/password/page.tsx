/**
 * 비밀번호 변경 페이지
 * 담당: E - 장인호
 *
 * 소셜 로그인(GOOGLE/NAVER/GITHUB) 계정은 비밀번호가 없으므로 접근을 막는다.
 * (사이드바/프로필 페이지에서 버튼 자체를 숨기지만, 주소를 직접 입력해 들어오는 경우를 대비해
 *  이 페이지에서도 provider를 다시 확인한다.)
 *
 * TODO: A(민동현)님이 PATCH /api/v1/auth/password 구현 전까지는 버튼만 동작하고 실제 변경은 안 됨.
 *       엔드포인트가 생기면 별도 수정 없이 바로 정상 동작함.
 */
'use client';

import { useState, useEffect } from 'react';
import { useRouter } from 'next/navigation';
import Link from 'next/link';
import api from '@/lib/api';
import styles from './page.module.css';

export default function PasswordChangePage() {
  const router = useRouter();
  const [checking, setChecking] = useState(true);
  const [allowed, setAllowed] = useState(false);

  const [currentPassword, setCurrentPassword] = useState('');
  const [newPassword, setNewPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [submitting, setSubmitting] = useState(false);

  useEffect(() => {
    api.get('/api/v1/users/me')
      .then((res) => {
        if (res.data.provider === 'LOCAL') {
          setAllowed(true);
        } else {
          alert('소셜 로그인 계정은 비밀번호를 변경할 수 없습니다.');
          router.replace('/mypage/profile');
        }
      })
      .catch(() => router.replace('/mypage/profile'))
      .finally(() => setChecking(false));
  }, [router]);

  const canSubmit =
    currentPassword.length > 0 &&
    newPassword.length >= 8 &&
    newPassword === confirmPassword;

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!canSubmit || submitting) return;

    setSubmitting(true);
    try {
      await api.patch('/api/v1/auth/password', { currentPassword, newPassword });//임의로 만듬 실제 엔드포인트랑 맞출필요있음
      alert('비밀번호가 변경되었습니다.');
      router.push('/mypage/profile');
    } catch (err: any) {
      alert(err.response?.data?.message ?? '비밀번호 변경에 실패했습니다.');
    } finally {
      setSubmitting(false);
    }
  };

  if (checking) return null;
  if (!allowed) return null;

  return (
    <section>
      <h1 className={styles.title}>비밀번호 변경</h1>

      <form className={styles.card} onSubmit={handleSubmit}>
        <div className={styles.field}>
          <label className={styles.label}>현재 비밀번호</label>
          <input
            type="password"
            className={styles.input}
            value={currentPassword}
            onChange={(e) => setCurrentPassword(e.target.value)}
            autoComplete="current-password"
          />
        </div>

        <div className={styles.field}>
          <label className={styles.label}>새 비밀번호</label>
          <input
            type="password"
            className={styles.input}
            value={newPassword}
            onChange={(e) => setNewPassword(e.target.value)}
            autoComplete="new-password"
            placeholder="8자 이상"
          />
        </div>

        <div className={styles.field}>
          <label className={styles.label}>새 비밀번호 확인</label>
          <input
            type="password"
            className={styles.input}
            value={confirmPassword}
            onChange={(e) => setConfirmPassword(e.target.value)}
            autoComplete="new-password"
          />
          {confirmPassword.length > 0 && newPassword !== confirmPassword && (
            <p className={styles.errorMsg}>새 비밀번호가 일치하지 않습니다.</p>
          )}
        </div>

        <div className={styles.btnRow}>
          <Link href="/mypage/profile" className={styles.cancelBtn}>취소</Link>
          <button type="submit" className={styles.submitBtn} disabled={!canSubmit || submitting}>
            {submitting ? '변경 중...' : '변경'}
          </button>
        </div>
      </form>
    </section>
  );
}
