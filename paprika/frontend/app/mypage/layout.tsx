'use client';

import Link from 'next/link';
import styles from './layout.module.css';

export default function MypageLayout({ children }: { children: React.ReactNode }) {
  return (
    <div className={styles.container}>

      {/* 사이드바 */}
      <aside className={styles.sidebar}>
        <Link href="/mypage" style={{ textDecoration: 'none', color: 'inherit' }}>
          <h2 className={styles.sidebarTitle}>마이페이지</h2>
        </Link>

        <hr className={styles.divider} />

        <ul className={styles.navList}>
          <li><Link href="/mypage/reviews" className={styles.navLink}>거래 후기</Link></li>
        </ul>
      </aside>

      {/* 메인 콘텐츠 */}
      <div className={styles.main}>
        {children}
      </div>

    </div>
  );
}