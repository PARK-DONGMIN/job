# 📝 웹 블로그 서비스

> 사용자가 게시글을 작성하고 다양한 정보를 공유할 수 있는 웹 기반 블로그 서비스입니다.
> 이미지 업로드, YouTube 링크 공유, 지도 정보 공유 기능을 제공하며
> React + Spring Boot REST API 구조로 설계한 개인 프로젝트입니다.

🔗 **프론트엔드 레포:** [job_react](https://github.com/PARK-DONGMIN/job_react)
📅 **개발 기간:** 2025.09 ~ 2025.11 (7주, 개인 프로젝트)

---

## 📌 주요 기능

- **게시글 CRUD** — 작성·수정·삭제·조회
- **카테고리 분류** — 드롭다운 UI를 통한 카테고리 선택 및 게시글 필터링
- **이미지 업로드** — MultipartFile 기반 이미지 업로드, 서버 저장 후 경로만 DB에 관리
- **YouTube 링크 공유** — 게시글 작성 시 YouTube 영상 링크 함께 등록
- **지도 정보 공유** — 게시글에 지도 위치 정보 첨부
- **JWT 인증** — 로그인 시 JWT 발급, 쿠키 저장소에 저장하여 인증 상태 유지
- **사용자 관리** — 회원가입, 로그인, 사용자 정보 수정

---

## 🛠 기술 스택

### Backend
| 분류 | 기술 |
|------|------|
| Framework | Spring Boot |
| ORM | Spring Data JPA |
| DB | Oracle |
| 인증 | JWT |
| 개발 도구 | STS |

### Frontend
| 분류 | 기술 |
|------|------|
| Framework | React, React Router |
| HTTP | Axios |
| 개발 도구 | VS Code |

---

## 📁 프로젝트 구조

```
src/main/java/dev/jpa/resort/
├── Contents/    # 게시글 CRUD
├── Cate/        # 카테고리 관리
├── Employee/    # 사용자 관리 및 JWT 인증
└── Upload/      # 이미지 업로드
```

---

## ⚙️ 실행 방법

### 사전 준비
- Java 17 이상
- Oracle DB
- STS (Spring Tool Suite)

### 실행

```bash
# 빌드
./gradlew build

# 실행
java -jar build/libs/*.jar
```

---

## 👨‍💻 담당 역할

- 개인 프로젝트로 전체 서비스 설계 및 개발 단독 수행
- DB 테이블 구조 설계 및 데이터 흐름 정의
- Spring Boot 기반 REST API 설계 및 구현
- 게시글 및 사용자 관리 기능 개발
- 게시글 이미지 업로드 기능 구현
- React 기반 SPA 구조 및 주요 페이지 구현

---

## 👨‍💻 개발자

| 항목 | 내용 |
|------|------|
| 이름 | 박동민 |
| 이메일 | pdm6547@naver.com |
| GitHub | [@PARK-DONGMIN](https://github.com/PARK-DONGMIN) |
