# 🫑 Paprika - 동네 중고 마켓

> Spring Boot 3 + Next.js 14 + CockroachDB + Cloudinary

---

## 팀 담당 영역

| 담당자 | 도메인 | 백엔드 패키지 | 프론트엔드 |
|--------|--------|---------------|-----------|
| **A - 민동현** | 인증/보안 | `domain/auth` + `global/config/Security` | `app/(auth)/**`, `lib/auth.ts` |
| **B - 백성민** | 중고 상품 게시판 | `domain/product` | `app/products/**`, `components/product` |
| **C - 한대천** | 실시간 채팅 & 알림 | `domain/chat` + `global/config/WebSocket` | `app/chat/**`, `components/chat`, `lib/socket.ts` |
| **D - 이동준** | 거래 및 결제 시스템 | `domain/transaction` | 상품 상세 페이지 거래 파트 |
| **E - 장인호** | 마이페이지 & 관리자 | `domain/mypage` | `app/mypage/**`, `app/admin/**`, `components/mypage` |

---

## 기술 스택

| 분류 | 기술 |
|------|------|
| **Backend** | Spring Boot 3.3, Java 21, Gradle |
| **Frontend** | Next.js 14 (App Router), TypeScript, CSS Modules |
| **Database** | CockroachDB (PostgreSQL 드라이버), 공유 DB 사용 |
| **인증** | Spring Security + JWT + OAuth2 (Google, Naver, GitHub) |
| **실시간** | WebSocket + STOMP |
| **이미지** | Cloudinary (무료 플랜, 썸네일 자동 변환) |

---

## ⚡ 시작하기 (팀원 필독)

### 1단계 — 환경변수 파일 받기

`.env` 파일은 git에 올라가지 않는다. 팀장에게 카카오톡/슬랙으로 받아서 `paprika/` 루트에 놓는다.

```
paprika/
├── .env          ← 여기에 놓기 (팀장에게 받은 파일)
├── backend/
└── frontend/
```

> ⚠️ `.env` 파일을 절대 git commit/push 하지 말 것. `.gitignore`에 등록되어 있음.

---

### 2단계 — 백엔드 실행

```bash
cd backend
./gradlew bootRun
# → http://localhost:8080
```

`spring-dotenv` 라이브러리가 루트의 `.env`를 자동으로 읽어준다. 별도 설정 불필요.

> IntelliJ 사용 시: Run Configuration에서 Working Directory를 `paprika/backend`로 설정

---

### 3단계 — 프론트엔드 실행

```bash
cd frontend
npm install
npm run dev
# → http://localhost:3000
```

프론트 환경변수는 `.env` 파일이 아닌 `.env.local`을 사용한다.

```bash
# frontend/ 디렉토리 안에 .env.local 생성
NEXT_PUBLIC_API_URL=http://localhost:8080
NEXT_PUBLIC_WS_URL=http://localhost:8080
```

> ⚠️ `NEXT_PUBLIC_WS_URL`은 `http://`로 시작해야 한다. SockJS가 내부적으로 WebSocket으로 업그레이드하며, `socket.ts`에서 `/ws`를 자동으로 붙인다. `ws://`로 쓰면 연결이 안 됨.

---

## 🔑 환경변수 목록

| 변수명 | 설명 | 사용 위치 |
|--------|------|-----------|
| `DB_HOST` | 공유 DB 접속 주소 | Backend |
| `DB_USERNAME` | 공유 DB 아이디 | Backend |
| `DB_PASSWORD` | 공유 DB 비밀번호 | Backend |
| `CLOUDINARY_CLOUD_NAME` | Cloudinary Cloud Name | Backend |
| `CLOUDINARY_API_KEY` | Cloudinary API Key | Backend |
| `CLOUDINARY_API_SECRET` | Cloudinary API Secret | Backend |
| `JWT_SECRET` | JWT 서명 키 | Backend |
| `GOOGLE_CLIENT_ID` | 구글 OAuth2 Client ID | Backend |
| `GOOGLE_CLIENT_SECRET` | 구글 OAuth2 Client Secret | Backend |
| `NAVER_CLIENT_ID` | 네이버 OAuth2 Client ID | Backend |
| `NAVER_CLIENT_SECRET` | 네이버 OAuth2 Client Secret | Backend |
| `GITHUB_CLIENT_ID` | 깃허브 OAuth2 Client ID | Backend |
| `GITHUB_CLIENT_SECRET` | 깃허브 OAuth2 Client Secret | Backend |
| `NEXT_PUBLIC_API_URL` | 백엔드 API URL | Frontend |
| `NEXT_PUBLIC_WS_URL` | WebSocket URL | Frontend |
| `NEXT_PUBLIC_KAKAO_MAP_KEY` | 카카오맵 API Key | Frontend |
| `NEXT_PUBLIC_GOOGLE_MAP_KEY` | 구글맵 API Key | Frontend |

> OAuth2 키는 각자 담당 기능 필요 시 발급. 초반 개발에는 없어도 됨.

---

## 🛠 개발 가이드

### 백엔드 개발 순서

각자 담당 도메인 안에서만 작업한다. 다른 도메인 파일은 건드리지 말 것.

```
1. entity/ 확인 (이미 작성됨)
2. dto/ 확인 및 필드 추가 (이미 뼈대 작성됨)
3. repository/ 에 필요한 쿼리 메서드 추가
4. service/ 에 비즈니스 로직 구현
5. controller/ 에 TODO 주석 따라 엔드포인트 구현
```

**공통 응답 형식** — 반드시 `ApiResponse<T>`로 감싸서 반환

```java
// 성공
return ResponseEntity.ok(ApiResponse.ok(data));
return ResponseEntity.ok(ApiResponse.ok("메시지", data));

// 실패 → GlobalExceptionHandler가 자동 처리
throw new PaprikaException(ErrorCode.NOT_FOUND);
```

**예외 처리** — 직접 try-catch 말고 `PaprikaException` 사용

```java
// ErrorCode에 새 에러 코드 추가 후 사용
throw new PaprikaException(ErrorCode.PRODUCT_NOT_FOUND);
```

---

### 프론트엔드 개발 순서

```
1. types/index.ts 의 타입 확인 (이미 작성됨)
2. lib/api.ts 의 axios 인스턴스 사용
3. 페이지/컴포넌트 구현
4. CSS는 CSS Modules (.module.css) 사용
```

**API 호출** — 반드시 `lib/api.ts`의 인스턴스 사용 (토큰 자동 첨부됨)

```typescript
import api from '@/lib/api';

// GET
const res = await api.get('/api/v1/products');

// POST
const res = await api.post('/api/v1/products', { title, price, ... });
```

**이미지 업로드** — `lib/image.ts` 사용

```typescript
import { uploadImages, cloudinaryUrl } from '@/lib/image';

// 업로드 (Cloudinary URL 반환)
const urls = await uploadImages(files);  // string[]

// 썸네일 (URL 변환만 하면 됨, 서버 요청 없음)
cloudinaryUrl(url, 'w_300,h_300,c_fill')  // 300x300 썸네일
cloudinaryUrl(url, 'f_auto,q_auto')       // 웹 최적화
cloudinaryUrl(url, 'w_800,f_auto,q_auto') // 상세 이미지
```

**WebSocket(채팅)** — `lib/socket.ts` 사용 (C - 한대천 담당)

```typescript
import { connectSocket, subscribeToRoom, sendMessage } from '@/lib/socket';
import { getAccessToken } from '@/lib/auth';

// 연결 (token 필수)
const token = getAccessToken() ?? '';
connectSocket(token, () => {
  // 연결 완료 후 구독
  subscribeToRoom(roomId, (message) => {
    const body = JSON.parse(message.body);
    // body.content, body.senderId 등 사용
  });
});

// 메시지 전송
sendMessage(roomId, content);
```

---

### 공유 DB 사용 규칙

- `ddl-auto: update` 설정이므로 엔티티 수정 시 **자동으로 스키마가 바뀜**
- 컬럼 추가는 괜찮지만 **컬럼명 변경·삭제는 팀원에게 공지 후 진행**
- 테이블명 충돌 방지: 각자 담당 테이블만 수정

| 담당 | 테이블 |
|------|--------|
| A | `users` |
| B | `products`, `product_images` |
| C | `chat_rooms`, `chat_messages` |
| D | `transactions` |
| E | `reviews`, `wish_list` |

---

### 개발 모드 / 운영 모드

현재 **개발 모드**로 설정되어 있어 모든 API가 인증 없이 호출 가능하다.

```yaml
# application.yml
spring:
  profiles:
    active: dev   # 개발 중
                  # JWT 완성 후 prod 로 변경
```

A(민동현)가 JWT 필터 완성하면 `active: prod`로 변경 후 공지.

---

## 📁 프로젝트 구조

```
paprika/
├── .env                              ← 팀장에게 받은 환경변수 (git 제외)
├── .env.example                      ← 환경변수 목록 예시
├── backend/
│   └── src/main/java/com/paprika/
│       ├── domain/
│       │   ├── auth/                 # A - 민동현
│       │   │   ├── controller/
│       │   │   ├── dto/              # SignupRequest, LoginRequest, AuthResponse
│       │   │   ├── entity/User.java
│       │   │   ├── repository/
│       │   │   └── service/
│       │   ├── product/              # B - 백성민
│       │   │   ├── dto/              # ProductCreateRequest, ProductResponse
│       │   │   └── ...
│       │   ├── chat/                 # C - 한대천
│       │   │   ├── dto/              # ChatRoomResponse, ChatMessageRequest
│       │   │   └── ...
│       │   ├── transaction/          # D - 이동준
│       │   │   ├── dto/              # TransactionCreateRequest, TransactionResponse
│       │   │   └── ...
│       │   └── mypage/               # E - 장인호
│       │       ├── dto/              # ReviewCreateRequest, ReviewResponse
│       │       └── ...
│       └── global/
│           ├── config/               # Security, WebSocket, CORS, JPA, Cloudinary
│           ├── exception/            # PaprikaException, ErrorCode, GlobalExceptionHandler
│           ├── image/                # ImageService, ImageController (Cloudinary)
│           └── response/             # ApiResponse<T>
│
└── frontend/
    ├── app/
    │   ├── (auth)/                   # A - 민동현
    │   │   ├── login/
    │   │   └── register/
    │   ├── products/                 # B - 백성민
    │   │   ├── [id]/                 # 상품 상세 (거래 파트는 D - 이동준과 협의)
    │   │   └── new/                  # 상품 등록
    │   ├── chat/                     # C - 한대천
    │   │   └── [roomId]/
    │   ├── mypage/                   # E - 장인호
    │   └── admin/                    # E - 장인호
    ├── components/
    │   ├── layout/                   # Header, BottomTabBar (공통)
    │   ├── common/                   # Button 등 재사용 컴포넌트
    │   ├── product/                  # B - 백성민
    │   ├── chat/                     # C - 한대천
    │   └── mypage/                   # E - 장인호
    ├── lib/
    │   ├── api.ts                    # Axios 인스턴스 (토큰 자동 첨부)
    │   ├── auth.ts                   # 토큰 저장/삭제
    │   ├── socket.ts                 # STOMP WebSocket
    │   └── image.ts                  # Cloudinary 업로드 + URL 변환
    ├── types/index.ts                # 공통 TypeScript 타입 (수정 전 팀원 공지)
    └── styles/globals.css            # 디자인 토큰 (색상, 폰트)
```

---

## 📡 API 엔드포인트 목록

| 도메인 | Method | Path | 담당 |
|--------|--------|------|------|
| Image | POST | `/api/v1/images/upload` | 공통 |
| Image | POST | `/api/v1/images/upload/batch` | 공통 |
| Auth | POST | `/api/v1/auth/signup` | A |
| Auth | POST | `/api/v1/auth/login` | A |
| Auth | POST | `/api/v1/auth/reissue` | A |
| Auth | POST | `/api/v1/auth/logout` | A |
| Product | GET | `/api/v1/products` | B |
| Product | POST | `/api/v1/products` | B |
| Product | GET | `/api/v1/products/{id}` | B |
| Product | PUT | `/api/v1/products/{id}` | B |
| Product | DELETE | `/api/v1/products/{id}` | B |
| Product | GET | `/api/v1/products/search` | B |
| Product | POST | `/api/v1/products/{id}/status` | B |
| Chat | GET | `/api/v1/chat/rooms` | C |
| Chat | GET | `/api/v1/chat/rooms/{id}` | C |
| Chat | GET | `/api/v1/chat/rooms/{id}/messages` | C |
| Chat | WS | `/ws` (STOMP) | C |
| Transaction | POST | `/api/v1/transactions` | D |
| Transaction | GET | `/api/v1/transactions/{id}` | D |
| Transaction | PATCH | `/api/v1/transactions/{id}/status` | D |
| Transaction | PATCH | `/api/v1/transactions/{id}/tracking` | D |
| Transaction | POST | `/api/v1/transactions/{id}/complete` | D |
| MyPage | GET | `/api/v1/users/me` | E |
| MyPage | PATCH | `/api/v1/users/me` | E |
| MyPage | GET | `/api/v1/users/me/products` | E |
| MyPage | GET | `/api/v1/users/me/wishlist` | E |
| MyPage | POST | `/api/v1/users/me/wishlist/{productId}` | E |
| MyPage | DELETE | `/api/v1/users/me/wishlist/{productId}` | E |
| MyPage | GET | `/api/v1/users/{id}/reviews` | E |
| MyPage | POST | `/api/v1/reviews` | E |
| Admin | GET | `/api/v1/admin/notices` | E |
| Admin | GET | `/api/v1/admin/reports` | E |

---

## 🔗 도메인 간 연동 포인트

연동이 필요한 부분은 **반드시 두 담당자가 사전 협의** 후 진행.

| 연동 | 내용 | 관련 담당 |
|------|------|-----------|
| B ↔ D | 거래 완료 시 Product 상태 → `SOLD` | B, D |
| D ↔ E | 거래 완료 후 리뷰 작성 가능 여부 검증 | D, E |
| C ↔ B | 채팅방 생성 시 productId 연결 | C, B |
| A ↔ 전체 | JWT 완성 후 SecurityContext에서 현재 유저 조회 | A + 전원 |

---

## 🎨 디자인 시스템

기반: Stitch 디자인 (`_1` 마이페이지 · `_2` 채팅 · `_3` 상품등록 · `_4` 상품상세 · `_5` 홈)

모든 디자인 토큰은 `frontend/styles/globals.css`의 CSS 변수로 정의되어 있음.  
CSS Modules(`.module.css`) 안에서 `var(--토큰명)`으로 사용.

### 색상

```css
/* .module.css 파일 안에서 */
color: var(--color-primary);               /* Paprika Orange #a83900 — CTA, 활성 */
color: var(--color-on-primary);            /* #ffffff */
background: var(--color-primary-container);/* #ff5a00 */

color: var(--color-secondary);             /* Fresh Green #006e1c — 성공, 배지 */
background: var(--color-secondary-container); /* #91f78e */

background: var(--color-surface);          /* #fcf9f8 — 기본 배경 */
background: var(--color-surface-container-lowest); /* #ffffff — 카드 배경 */
background: var(--color-surface-container); /* #f0eded — 입력 필드 배경 */

color: var(--color-on-surface);            /* #1b1c1c — 기본 텍스트 */
color: var(--color-on-surface-variant);    /* #5b4137 — 보조 텍스트 */
color: var(--color-outline);               /* #907065 — 구분선, 아이콘 */

color: var(--color-error);                 /* #ba1a1a — 에러 */
```

### 타이포그래피

globals.css에 유틸 클래스로 정의되어 있어 `.module.css` 없이 바로 사용 가능.

```tsx
<h1 className="text-display">40px / Bold</h1>
<h2 className="text-headline-lg">32px / Bold</h2>
<h3 className="text-headline-lg-mobile">24px / Bold</h3>
<h4 className="text-headline-md">20px / SemiBold</h4>
<p  className="text-body-lg">18px / Regular</p>
<p  className="text-body-md">16px / Regular</p>
<p  className="text-body-sm">14px / Regular</p>
<span className="text-label-md">14px / SemiBold — 버튼, 탭</span>
<span className="text-label-sm">12px / Medium — 뱃지, 캡션</span>
```

### 간격 (Spacing)

```css
padding: var(--space-xs);           /* 4px */
padding: var(--space-sm);           /* 12px */
padding: var(--space-md);           /* 24px — 섹션 내부 기본 */
padding: var(--space-lg);           /* 40px — 섹션 간 */
padding: var(--space-xl);           /* 64px */
padding: var(--container-margin);   /* 20px — 좌우 여백 */
gap:     var(--gutter);             /* 16px — 그리드 gap */
```

### 모서리 (Border Radius)

```css
border-radius: var(--radius-sm);   /* 4px */
border-radius: var(--radius);      /* 8px — 기본 */
border-radius: var(--radius-md);   /* 12px */
border-radius: var(--radius-lg);   /* 16px — 카드, 버튼 */
border-radius: var(--radius-xl);   /* 24px */
border-radius: var(--radius-full); /* 9999px — 필 버튼, 뱃지 */
```

### 그림자 (Elevation)

```css
box-shadow: var(--shadow-card);     /* 카드 — 0px 4px 12px rgba(0,0,0,0.05) */
box-shadow: var(--shadow-floating); /* 네비바, 플로팅 버튼 — 0px 8px 24px rgba(0,0,0,0.08) */
```

### 아이콘 (Material Symbols)

layout.tsx에 폰트가 전역 로드되어 있음. `<span>` 태그로 바로 사용.

```tsx
<span className="material-symbols-outlined">search</span>
<span className="material-symbols-outlined">favorite</span>
<span className="material-symbols-outlined">chat</span>
<span className="material-symbols-outlined">person</span>
<span className="material-symbols-outlined">add</span>
```

아이콘 목록: https://fonts.google.com/icons

---

## ❓ 자주 물어볼 것 같은 것들

### 공통

**Q. 서버 실행했는데 DB 연결 오류가 난다.**  
A. `paprika/` 루트(backend 아님)에 `.env` 파일이 있는지 확인. IntelliJ라면 Run Configuration의 Working Directory가 `paprika/backend`인지 확인.

**Q. `@Value` 또는 `${변수명}` 주입이 안 된다 / null이다.**  
A. `spring-dotenv`는 루트 `.env`를 읽지만 `application.yml`에서 `${변수명}` 형태로 참조해야 한다. `.env`에 변수가 있는지, 오타는 없는지 먼저 확인.

**Q. 다른 사람 도메인 코드를 수정해야 할 것 같다.**  
A. 직접 수정 금지. 해당 담당자에게 메서드 추가 요청하거나, 연동 포인트 표를 보고 사전 협의 후 진행.

**Q. `ApiResponse`를 어떻게 쓰는지 모르겠다.**  
A. 성공 시 `ApiResponse.ok(data)` 또는 `ApiResponse.ok("메시지", data)`. 실패는 `throw new PaprikaException(ErrorCode.XXX)` — GlobalExceptionHandler가 자동으로 `ApiResponse.fail`로 변환해서 내려줌.

---

### A - 민동현 (인증)

**Q. 다른 팀원들이 현재 로그인한 유저 정보를 어떻게 꺼내 쓰냐고 묻는다.**  
A. JWT 필터 완성 전까지는 devMode라 SecurityContext가 비어 있다. 임시로 `@RequestHeader("X-User-Id") Long userId`를 파라미터로 받는 방식으로 테스트 가능. 필터 완성 후 `SecurityContextHolder.getContext().getAuthentication()`으로 교체.

**Q. OAuth2 로그인 후 프론트로 토큰을 어떻게 전달하나?**  
A. OAuth2 successHandler에서 JWT를 발급하고 `http://localhost:3000/oauth/callback?token=xxx` 형태로 리다이렉트하는 방식이 일반적. 프론트(A)와 협의 필요.

---

### B - 백성민 (상품)

**Q. 상품 등록 시 이미지를 어떻게 처리하나?**  
A. 프론트에서 먼저 `POST /api/v1/images/upload/batch`로 Cloudinary에 업로드 → URL 목록을 받아서 → `POST /api/v1/products` 본문에 `imageUrls: [...]`로 담아서 보내는 방식. 백엔드는 URL만 저장하면 됨.

**Q. 페이징을 어떻게 구현하나?**  
A. Spring Data의 `Pageable`이 이미 컨트롤러에 들어가 있다. `productRepository.findAll(pageable)` 호출하면 끝. 프론트는 `?page=0&size=20` 쿼리스트링으로 요청.

---

### C - 한대천 (채팅)

**Q. WebSocket 메시지가 안 온다.**  
A. `NEXT_PUBLIC_WS_URL`이 `http://`로 시작하는지 확인 (`ws://` 아님). `connectSocket` 호출 후 `onConnect` 콜백 안에서 `subscribeToRoom`을 해야 한다 — 연결 전에 구독하면 무시됨.

**Q. STOMP destination 경로가 맞는지 모르겠다.**  
A. 전송: `/app/chat/{roomId}` (서버 `@MessageMapping("/chat/{roomId}")` 처리)  
   수신 구독: `/topic/chat/{roomId}` (서버에서 `SimpMessagingTemplate.convertAndSend`로 브로드캐스트)

**Q. 채팅방 생성은 누가 하나?**  
A. `GET /api/v1/chat/rooms/{productId}?sellerId=xxx` 같은 방식으로 채팅방을 조회하고 없으면 자동 생성하는 패턴 권장. B(백성민)와 productId 연결 방식 먼저 협의.

---

### D - 이동준 (거래)

**Q. 거래 완료 시 Product 상태를 SOLD로 바꾸는 건 어디서 하나?**  
A. `TransactionService`에서 거래 완료 처리 시 B(백성민)의 `ProductRepository`를 직접 주입받아 상태 변경. 또는 B에게 `ProductService.markAsSold(productId)` 메서드 추가를 요청. **B에게 미리 말하고** 방식 합의 필요.

**Q. `meetingTime`이 `LocalDateTime`인데 프론트에서 어떻게 보내나?**  
A. ISO 8601 형식 문자열로 전달 → `"2025-07-01T14:00:00"`. DTO에서 `LocalDateTime`으로 자동 역직렬화됨.

---

### E - 장인호 (마이페이지/관리자)

**Q. 매너 온도 계산은 어떻게 하나?**  
A. `Review.mannerScore`가 +1/-1/0으로 들어온다. 리뷰 작성 시 `User.trustScore`를 업데이트. 초기값 36.5 기준으로 +1이면 +0.1, -1이면 -0.1 같은 식으로 E가 규칙 결정 후 구현.

**Q. 관리자 API에 권한 체크가 없는데?**  
A. 현재 devMode라 전부 허용 상태. `SecurityConfig`에 `ADMIN` 역할 체크 로직이 이미 prod 설정에 있음 — A(민동현)의 JWT 필터 완성 후 자동으로 적용됨. 지금은 그냥 구현만 하면 됨.

**Q. 찜(WishList) 추가 시 중복 체크를 어떻게 하나?**  
A. `WishList` 엔티티에 `(user_id, product_id)` unique constraint가 이미 걸려 있다. 중복 시 DB 예외가 발생하므로 `DataIntegrityViolationException`을 잡아서 `CONFLICT` 응답으로 처리하면 됨.
