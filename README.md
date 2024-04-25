# DB_Project
프로젝트
# 1. 주제

- **올룸골룸  : 부동산 중개 서비스**

# 2. 역할 분담

- **요구사항 영역별 정리**
    - 검색기능(지역검색, 거래방식, 평 수, 층 수), 검색 목록 페이지, 사용자 방페이지(찜기능: Update))까지 구현 : 김세호
    - 방 등록 페이지(테이블 생성, 데이터입력) 방과 관련된 역할 서포트 : 이경섭
    - 로그인과 회원가입 및 회원테이블 관련 기능 구현 : 김동환
    - 마이페이지 기능(찜목록, 방 관리목록, 회원정보 수정 및 탈퇴, 로그아웃) 구현 : 서용욱
    - 관리자 페이지 구현
- **ERD 및 테이블/시퀀스 구성**
    - 테이블 제작 및 상세 기능 설계 : 이경섭, 서용욱, 김동환, 김세호
    - 샘플 데이터 입력
        - 1. 회원(MEMBER) 테이블 데이터 : 김동환
        - 2. 방 등록(ROOMINFO) 테이블 데이터 : 김세호
        - 3. 마이페이지(MY_PAGE) 테이블 데이터 : 서용욱
        - 4. 찜한 방 목록(LOVE ROOM)테이블 데이터 : 이경섭
        - 5. 관리자 계정(ADMIN INFO)테이블 데이터 : 이경섭, 김동환
    - 시퀀스 구성
        - RoomInfo 테이블의 house_num , Love Room 테이블의 Shouse_num 시퀀스 적용 : 이경섭
- **쿼리 작성**
    - INFO :  김동환
    - ROOMINFO : 이경섭
    - MY_PAGE : 서용욱
    - LOVE ROOM : 김세호
    - ROOM SEARCH : 김세호
    - ADMIN INFO : 김동환
- **JDBC**
    - Common
        - Util :  이경섭
    - VO
        - InfoVo :  김동환
        - RoomInfoVo : 이경섭
        - MyPageVo: 서용욱
        - LoveRoomVo : 김세호
        - RoomSearchVo : 김세호
        - AdminInfoVo : 김동환
    - DAO
        - InfoDAO :  김동환
        - RoomInfoDAO : 이경섭
        - MyPageDAO: 서용욱
        - LoveRoomDAO : 김세호
        - RoomSearchDAO : 김세호
        - AdminInfoDAO : 김동환
    - Main : 이경섭
- **Thymeleaf**
    - RoomInfo select/insert : 김세호
    - RoomInfo insert/select + css :  이경섭
    - MyPage select/update/delete : 서용욱
    - AdminInfo select/insert : 김동환
    - Login select : 김동환
- **PPT 협업**

https://github.com/Leegyeongseob/jdbc_thymeleaf_PR/assets/67867076/94364018-f821-4db9-98bc-824a984407d1

[올룸골룸 (1).pdf](https://github.com/Leegyeongseob/jdbc_thymeleaf_PR/files/15060384/1.pdf)

# 3. 진행 순서

## [1단계] 요구사항 분석(External Schema)

1. **검색 기능**: 사용자는 지역, 가격대, 크기, 시설 등을 기준으로 부동산을 검색
2. **매물 정보**: 부동산의 사진, 가격, 위치, 설명 등에 대한 자세한 정보가 제공
3. **저장 기능**: 사용자가 관심 있는 매물을 저장하거나 북마크 기능
4. **사용자 프로필**: 사용자는 개인 프로필을 설정 및 관리
5. **보안**: 개인정보 보호 및 결제 시스템
6. **사용자 경험**: 사용자가 쉽게  검색하고 필요한 정보를 얻을 수 있는 직관적인 인터페이스

## [2단계] 개념적 설계 (Conceptual Schema)

1. **사용자 관리 시스템**:
    - 사용자 등록 및 로그인 기능
    - 사용자 프로필 관리
2. **매물 관리 시스템**:
    - 부동산 매물 정보 데이터베이스
    - 매물 등록 및 수정 기능
    - 매물 검색 및 필터링 기능
3. **보안 및 개인정보 보호**:
    - 안전한 로그인 및 데이터 전송을 위한 보안 시스템
4. **사용자 경험 개선을 위한 UI/UX**:
    - 직관적이고 사용하기 쉬운 인터페이스 디자인
    - 모바일 및 데스크톱 버전의 호환성 고려

## [3단계] 논리적 설계  (Logical Schema)

- **[로그인 페이지]**
    - [로그인 페이지] ID(이메일 형식), PASSWORD 입력 후 일치 여부, 확인(SELECT).
    - [회원가입 페이지] ID, 이름, PASSWORD, 기타 개인정보 확인 후 입력완료하면 마이페이지에 데이터를 생성하고 로그인 페이지로 이동(INSERT).
    - 로그인 성공시 올룸/골룸(Insert/Select) 선택 페이지로 이동.
- **[방 입력 페이지]**
    - [방 등록 페이지] 판매할 방 데이터 추가(방 사진, 거래방식, 가격대, 옵션 등) 후 방 등록 완료 → 메인페이지 이동(INSERT)
- **[마이페이지]**
    - [개인정보 수정] PASSWORD, 핸드폰 번호, 주소, 닉네임 수정 및 탈퇴(UPDATE,DELETE)
    - [골룸 목록] 찜한방 목록 테이블에서 매물번호로 방정보를 연결 후 거래 방식, 가격 , 주소, 비고 데이터로 방 정보 표시 후 사용자 방 페이지로 이동 (SELECT)
    - [올룸 목록] roominfo테이블에서 유저id로 올린 1.방데이터 2. 옵션 데이터 수정 및 삭제(SELECT, Update, DELECT)
    - [로그아웃] 로그인 페이지로 이동.
    - 메인페이지 이동.
- **[검색 페이지]**
    - [1단계] 지역 검색 : 방 테이블에서 주소를 보고 행정구역상 ‘구’ 까지 검색(SELECT)
    - [2단계] 거래방식: 월세, 전세, 매매 선택 후 월세(보증금, 월세), 전세(전세금), 매매(매매가) 로 검색(SELECT),
    - [3단계] 면적 검색 : 단위 평 수로 검색(SELECT)
    - [4단계] 층수 검색 : 반지층, 지층, 루프탑 선택 검색(SELECT)
    - 검색완료를 누르면 검색 목록 페이지 이동
    - 메인페이지 이동.
- **[검색 목록 페이지]**
    - [방 목록] 거래 방식, 가격 , 주소, 비고로 방 정보 표시 후 사용자 방 페이지로 이동(SELECT)
- **[사용자 방 페이지]**
    - 해당되는 방 테이블 정보를 불러오기(SELECT), 찜하기(Update)
- **[관리자 페이지]**
    - [관리권한] 회원 관리와 방 테이블 관리( SELECT, INSERT,UPDATE,DELETE)
    - 관리자 계정, 전화번호 표시

## [4단계] 물리적 설계

- **[테이블생성]**
- **[데이터 입력]**
- **[쿼리문 작성]**
- **[JDBC와 연결]**

## [5단계] 구현

- Console 구현
    
https://github.com/Leegyeongseob/jdbc_thymeleaf_PR/assets/67867076/ce484707-cde6-4550-b1f1-d0f59350cb09

- thymeleaf 구현

https://github.com/Leegyeongseob/jdbc_thymeleaf_PR/assets/67867076/cdb5e2c4-6503-446f-a0b7-5641ce9ddb18

# 5.DB 테이블 구성

## **초기 구현 단계**

### <03월 26일 : 주제 선정 후 프로젝트 Flow 초안>

![20240326_171913](https://github.com/Leegyeongseob/jdbc_thymeleaf_PR/assets/67867076/95adeddc-0ca9-4f53-acac-814e300e03be)

### <03월 27일 : 테이블 ERD로 표현 후 관계 연결>

![올룸골룸](https://github.com/Leegyeongseob/jdbc_thymeleaf_PR/assets/67867076/7b5dda68-5090-435c-890c-ea1797840146)

### <03월 28일 : 1차 회의 후 불필요 테이블 삭제>

![올룸골룸 EDR(03 28)](https://github.com/Leegyeongseob/jdbc_thymeleaf_PR/assets/67867076/69dc9652-a663-407d-97ca-7e952d85024d)

### <03월 29일 : 데이터, 연결 관계 수정>

![올룸골룸 (1)](https://github.com/Leegyeongseob/jdbc_thymeleaf_PR/assets/67867076/db55a93a-b8ec-41fa-acf7-3366131acef7)

# 6. 쿼리문 작성

## ERD 최종

![erd최종 PNG (1)](https://github.com/Leegyeongseob/jdbc_thymeleaf_PR/assets/67867076/95610e20-2bc5-4623-8813-9047b2f9c540)

## 스토리 보드

![555555555555555555555555555555 (1)](https://github.com/Leegyeongseob/jdbc_thymeleaf_PR/assets/67867076/ee89294f-9e4b-4301-9159-eae2a08052a3)

## 1. **회원(MEMBER) 테이블**

| 이름 | 컬럼 이름 | 자료형 | 제약 조건 |
| --- | --- | --- | --- |
| 유저 아이디 | USER_MAIL | VARCHAR2(255 BYTE) | PRIMARY KEY |
| 비밀번호 | PASSWORD | VARCHAR2(255 BYTE) | NOT NULL |
| 이름 | NAME | VARCHAR2(255 BYTE) | NOT NULL |
| 주민번호 앞자리 | FIRST_ID_CARDNUM | NUMBER(6) | NOT NULL |
| 핸드폰 번호 | PHONE_NUMBER | NUMBER(11) | NOT NULL |
| 주소 | ADDRESS | VARCHAR2(255 BYTE) | NULL 허용 |
| 성별 | GENDER | NUMBER(2) | NOT NULL |
| 닉네임 | NICKNAME | VARCHAR2(255 BYTE) | NOT NULL |

```sql
-- 1. 회원(MEMBER) 테이블

CREATE TABLE INFO(
   USER_MAIL VARCHAR2(255) PRIMARY KEY NOT NULL,
   PASSWORD VARCHAR2(255) NOT NULL,
   NAME VARCHAR2(255) NOT NULL,
   FIRST_ID_CARDNUM NUMBER(6) NOT NULL,
   PHONE_NUMBER NUMBER(11) NOT NULL,
   ADDRESS VARCHAR2(255),
   GENDER NUMBER(2) NOT NULL,
   NICKNAME VARCHAR2(255) NOT NULL
   );
```

## 2. 방 등록(ROOMINFO) **테이블**

| 이름 | 컬럼 이름 | 자료형 | 제약 조건 |
| --- | --- | --- | --- |
| 매물번호 | HOUSE_NUM | NUMBER(10) | PRIMARY KEY |
| 유저 아이디 | USER_MAIL | VARCHAR2(255 BYTE) | NOT NULL |
| 사진 URL | PHOTO_URL | VARCHAR2(255 BYTE) | NULL 허용 |
| 거래 방식 | TRADE_METHOD | VARCHAR2(50 BYTE) | NOT NULL |
| 보증금 | DEPOSIT | NUMBER(15) | NULL 허용 |
| 월세 | MONTHLY | NUMBER(15) | NULL 허용 |
| 전세 | JEONSEGEUM | NUMBER(15) | NULL 허용 |
| 매매가 | SALE_PRICE | NUMBER(15) | NULL 허용 |
| 면적(평수) | AREA | VARCHAR2(255 BYTE) | NOT NULL |
| 사용 허가 날짜 | ACCEPT_DATE | DATE | NULL 허용 |
| 주소 | ADDRESS | VARCHAR2(255 BYTE) | NOT NULL |
| 층수 | FLOOR1 | VARCHAR2(255 BYTE) | NOT NULL |
| 등록자 핸드폰 번호 | PHONENUMBER | NUMBER(15) | NOT NULL |
| 등록날짜 | REGIT_DATE | DATE | NULL 허용 |
| 비고(옵션) | REMARK | VARCHAR2(255 BYTE) | NULL 허용 |

```sql
-- 2. 방 등록(GRADE) 테이블 
CREATE TABLE RoomInfo(
    house_num NUMBER(10) PRIMARY KEY NOT NULL, -- 매물번호(시퀀스로 자동생성)
    user_id VARCHAR2(255) NOT NULL, -- 등록자 아이디
    photo_url VARCHAR2(255), -- 방 사진URL
    trade_method VARCHAR(50) NOT NULL, -- 거래 방식
    deposit NUMBER(15), -- 보증금(만원) : 월세만 해당.
    monthly NUMBER(15), -- 월세(만원) : 월세만 해당.
    jeonsegeum NUMBER(15), -- 전세금(만원) : 전세만 해당
    Sale_price NUMBER(15), -- 매매가(만원) : 매매만 해당
    area VARCHAR2(255) NOT NULL, -- 평수 : ~평
    accept_date DATE, -- 사용 승인일
    address VARCHAR2(255) NOT NULL, -- 매물주소
    floor1 VARCHAR2(255) NOT NULL, -- 층수
    phonenumber  NUMBER(15) NOT NULL, -- 집주인 전화번호
    regit_date DATE, -- 매물 등록일
    remark VARCHAR2(255) -- 비고(옵션)
);
```

## 3. 마이페이지(MY_PAGE) 테이블

| 이름 | 컬럼 이름 | 자료형 | 제약 조건 |
| --- | --- | --- | --- |
| 유저 아이디 | USER_MAIL | VARCHAR2(255 BYTE) | UNIQUE |
| 찜한 방 | LOVE_ROOM  | NUMBER(2) | NULL 허용 |
| 프로필 사진 | PROFILE_PHOTO | VARCHAR2(255 BYTE) | NULL 허용 |

```sql
-- 3. 마이페이지(MY PAGE) 테이블
CREATE TABLE MY_PAGE(
   USER_MAIL VARCHAR2(255) UNIQUE NOT NULL, -- 유저 아이디
   LOVE_ROOM NUMBER(2), -- 찜한 데이터 유무
   profile_photo VARCHAR2(255) -- 프로필 사진URL
   );
```

## 4. 찜한 방 목록(LOVE ROOM)테이블

| 이름 | 컬럼 이름 | 자료형 | 제약 조건 |
| --- | --- | --- | --- |
| 유저 아이디 | USER_MAIL | VARCHAR2(255 BYTE) | FOREIGN KEY, NOT NULL |
| 매물번호 | PASSWORD | NUMBER(10) | FOREIGN KEY, NOT NULL |

```jsx
-- 4. 찜한 방 목록 테이블 생성
create table love_room(
   user_id varchar(255) REFERENCES my_page(USER_MAIL) not NULL, -- 유저아이디(찜한방 목록에 있어야함.)
   Shouse_num NUMBER(10) references RoomInfo(house_num) NOT NULL -- 매물번호(방 정보 테이블에서 시퀀스로 입력)
);
```

## 5. 관리자 계정(ADMIN INFO)테이블

| 이름 | 컬럼 이름 | 자료형 | 제약 조건 |
| --- | --- | --- | --- |
| 관리자 번호 | ADM_NO  | NUMBER(10) | UNIQUE, NOT NULL |
| 관리자 아이디 | ADM_ID  | VARCHAR2(255 BYTE) | NOT NULL |
| 관리자 비밀번호 | ADM_PW  | VARCHAR2(255 BYTE) | NOT NULL |

```sql
-- 5. 관리자 계정(ADMIN INFO)
CREATE TABLE ADMIN_INFO(
   ADM_NO NUMBER(10) UNIQUE NOT NULL, -- 관리자 번호
   ADM_ID VARCHAR(255) NOT NULL, -- 관리자 아이디
   ADM_PW VARCHAR(255) NOT NULL -- 관리자 PASSWORD
   );
```

## DATABASE

### **1. 회원(MEMBER) 테이블 데이터**

작성자 : 서용욱

```sql
INSERT ALL 
INTO INFO VALUES ('dyddnr33@naver.com', 'asdf', '서용욱', 960125, 01098111691, '서울시 관악구', 1, '막강한바지')
INTO INFO VALUES ('wnsdud12@daum.com', '123456', '김준영', 940321, 01043453454, '서울시 강남구', 2, '불법유턴')
INTO INFO VALUES ('kimfjd@naver.com', '1234','김동환',991130,01022645648,'경기도 이천시',1,'집가자')
INTO INFO VALUES ('khk28@naver.com', '1325','김현근',970923,01042675900,'경기도 남양주시',1,'집으로')
INTO INFO VALUES ('user1@example.com', 'password1', '홍길동', 980101, 01012345678, '서울시 강남구', 1, '의적')
INTO INFO VALUES ('user2@example.com', 'password2', '이순신', 970202, 01023456789, '서울시 종로구', 2, '충무공')
INTO INFO VALUES ('user3@example.com', 'password3', '김유신', 990303, 01034567890, '경기도 수원시', 1, '산책하기')
INTO INFO VALUES ('user4@example.com', 'password4', '신사임당', 960404, 01045678901, '인천시 남구', 2, '5만원')
INTO INFO VALUES ('user5@example.com', 'password5', '강감찬', 950505, 01056789012, '대구시 중구', 1, '운동하기')
INTO INFO VALUES ('user6@example.com', 'password6', '을지문덕', 940606, 01067890123, '부산시 해운대구', 2, '살수대첩')
SELECT * FROM DUAL;
INSERT ALL
INTO INFO VALUES ('user7@example.com','password7','홍길동',980101,01012345678,'서울시 강남구',2,'의적')
INTO INFO VALUES ('user8@example.com','password8','김유신',990303,01034567890,'서울시 강남구',2,'산책하기')
INTO INFO VALUES ('user9@example.com','password9','홍길동',980101,01012345678,'서울시 강남구',1,'의적')
INTO INFO VALUES ('user10@example.com','password10','김유신',990303,01034567890,'서울시 강남구',1,'산책하기')
INTO INFO VALUES ('user11@example.com','password11','신사임당',960404,01045678901,'서울시 강남구',2,'5만원')
INTO INFO VALUES ('user12@example.com','password12','김유신',990303,01034567890,'서울시 강남구',2,'산책하기')
INTO INFO VALUES ('user13@example.com','password13','을지문덕',940606,01067890123,'서울시 강남구',1,'살수대첩')
INTO INFO VALUES ('user14@example.com','password14','홍길동',980101,01012345678,'서울시 강남구',2,'의적')
INTO INFO VALUES ('user15@example.com','password15','김유신',990303,01034567890,'서울시 강남구',2,'산책하기')
INTO INFO VALUES ('user16@example.com','password16','홍길동',980101,01012345678,'서울시 강남구',2,'의적')
INTO INFO VALUES ('user17@example.com','password17','이순신',970202,01023456789,'서울시 강남구',2,'충무공')
INTO INFO VALUES ('user18@example.com','password18','김유신',990303,01034567890,'서울시 강남구',2,'산책하기')
INTO INFO VALUES ('user19@example.com','password19','을지문덕',940606,01067890123,'서울시 강남구',1,'살수대첩')
INTO INFO VALUES ('user20@example.com','password20','이순신',970202,01023456789,'서울시 강남구',2,'충무공')
INTO INFO VALUES ('user21@example.com','password21','강감찬',950505,01056789012,'서울시 강남구',2,'운동하기')
INTO INFO VALUES ('user22@example.com','password22','신사임당',960404,01045678901,'서울시 강남구',1,'5만원')
INTO INFO VALUES ('user23@example.com','password23','홍길동',980101,01012345678,'서울시 강남구',2,'의적')
INTO INFO VALUES ('user24@example.com','password24','을지문덕',940606,01067890123,'서울시 강남구',2,'살수대첩')
INTO INFO VALUES ('user25@example.com','password25','을지문덕',940606,01067890123,'서울시 종로구',2,'살수대첩')
INTO INFO VALUES ('user26@example.com','password26','홍길동',980101,01012345678,'서울시 종로구',1,'의적')
INTO INFO VALUES ('user27@example.com','password27','신사임당',960404,01045678901,'서울시 종로구',1,'5만원')
INTO INFO VALUES ('user28@example.com','password28','김유신',990303,01034567890,'서울시 종로구',1,'산책하기')
INTO INFO VALUES ('user29@example.com','password29','강감찬',950505,01056789012,'서울시 종로구',2,'운동하기')
INTO INFO VALUES ('user30@example.com','password30','이순신',970202,01023456789,'서울시 종로구',2,'충무공')
INTO INFO VALUES ('user31@example.com','password31','홍길동',980101,01012345678,'서울시 종로구',1,'의적')
INTO INFO VALUES ('user32@example.com','password32','을지문덕',940606,01067890123,'서울시 종로구',1,'살수대첩')
INTO INFO VALUES ('user33@example.com','password33','을지문덕',940606,01067890123,'서울시 종로구',2,'살수대첩')
INTO INFO VALUES ('user34@example.com','password34','이순신',970202,01023456789,'서울시 종로구',1,'충무공')
INTO INFO VALUES ('user35@example.com','password35','을지문덕',940606,01067890123,'서울시 종로구',1,'살수대첩')
INTO INFO VALUES ('user36@example.com','password36','홍길동',980101,01012345678,'서울시 종로구',1,'의적')
INTO INFO VALUES ('user37@example.com','password37','강감찬',950505,01056789012,'서울시 종로구',2,'운동하기')
INTO INFO VALUES ('user38@example.com','password38','신사임당',960404,01045678901,'서울시 종로구',1,'5만원')
INTO INFO VALUES ('user39@example.com','password39','을지문덕',940606,01067890123,'서울시 종로구',2,'살수대첩')
INTO INFO VALUES ('user40@example.com','password40','이순신',970202,01023456789,'경기도 수원시',1,'충무공')
INTO INFO VALUES ('user41@example.com','password41','을지문덕',940606,01067890123,'경기도 수원시',1,'살수대첩')
INTO INFO VALUES ('user42@example.com','password42','홍길동',980101,01012345678,'경기도 수원시',1,'의적')
INTO INFO VALUES ('user43@example.com','password43','이순신',970202,01023456789,'경기도 수원시',2,'충무공')
INTO INFO VALUES ('user44@example.com','password44','신사임당',960404,01045678901,'경기도 수원시',1,'5만원')
INTO INFO VALUES ('user45@example.com','password45','강감찬',950505,01056789012,'경기도 수원시',1,'운동하기')
INTO INFO VALUES ('user46@example.com','password46','김유신',990303,01034567890,'경기도 수원시',1,'산책하기')
INTO INFO VALUES ('user47@example.com','password47','강감찬',950505,01056789012,'경기도 수원시',1,'운동하기')
INTO INFO VALUES ('user48@example.com','password48','강감찬',950505,01056789012,'인천시 남구',1,'운동하기')
INTO INFO VALUES ('user49@example.com','password49','강감찬',950505,01056789012,'인천시 남구',1,'운동하기')
INTO INFO VALUES ('user50@example.com','password50','신사임당',960404,01045678901,'인천시 남구',2,'5만원')
SELECT * FROM DUAL;
```

### **2.** 방 등록(ROOMINFO) **테이블 데이터**

작성자 : 김동환

```sql
INSERT ALL 
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(1,'user6@example.com',null,'월세',1021,184,NULL,NULL,9,'2023-08-21','서울특별시 강남구','1','01093471548','2024-01-31','풀옵션')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(2,'user20@example.com',null,'매매',NULL,NULL,NULL,169728,44,'2023-09-20','서울특별시 강북구','1','01077617068','2024-03-20','세탁기,침대,전자레인지,냉장고')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(3,'user40@example.com',null,'월세',1291,37,NULL,NULL,7,'2024-02-06','서울특별시 강남구','3','01013426296','2023-10-22','풀옵션')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(4,'user19@example.com',null,'전세',NULL,NULL,25867,NULL,36,'2024-02-09','서울특별시 강동구','3','01066257107','2023-12-05','옵션없음')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(5,'user10@example.com',null,'매매',NULL,NULL,NULL,277034,26,'2023-12-27','서울특별시 강남구','-1','01065914256','2023-04-20','침대,에어컨,전자레인지,가스레인지')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(6,'user4@example.com',null,'전세',NULL,NULL,45551,NULL,31,'2023-09-30','서울특별시 강북구','5','01000611150','2023-08-17','냉장고,에어컨,가스레인지,침대')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(7,'user36@example.com',null,'전세',NULL,NULL,43416,NULL,41,'2023-05-09','서울특별시 강북구','2','01017712567','2023-07-25','에어컨,세탁기,냉장고,침대')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(8,'user27@example.com',null,'전세',NULL,NULL,9111,NULL,6,'2024-02-01','서울특별시 강남구','5','01091963462','2023-08-08','가스레인지,에어컨,침대,세탁기')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(9,'user6@example.com',null,'전세',NULL,NULL,22589,NULL,5,'2023-05-12','서울특별시 강서구','4','01097098554','2024-02-10','침대,에어컨')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(10,'user15@example.com',null,'전세',NULL,NULL,33984,NULL,28,'2023-07-20','서울특별시 강남구','-1','01009494887','2024-03-02','에어컨,침대')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(11,'user9@example.com',null,'매매',NULL,NULL,NULL,188033,16,'2023-04-25','서울특별시 강동구','2','01010943141','2024-03-20','냉장고,에어컨,가스레인지,침대,전자레인지')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(12,'user44@example.com',null,'전세',NULL,NULL,35913,NULL,55,'2023-11-22','서울특별시 강남구','3','01029819008','2023-07-08','침대,세탁기,에어컨,가스레인지')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(13,'user21@example.com',null,'월세',1482,24,NULL,NULL,10,'2023-09-22','서울특별시 강서구','-1','01067167160','2023-07-05','침대,가스레인지')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(14,'user17@example.com',null,'매매',NULL,NULL,NULL,221745,39,'2023-08-06','서울특별시 강동구','-1','01013937901','2023-07-25','세탁기,침대,에어컨,전자레인지,가스레인지')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(15,'user21@example.com',null,'월세',1036,133,NULL,NULL,19,'2023-11-05','서울특별시 강남구','4','01008759820','2024-02-28','에어컨,가스레인지,침대,전자레인지,냉장고')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(16,'user42@example.com',null,'전세',NULL,NULL,31279,NULL,14,'2024-02-02','서울특별시 강동구','3','01020612346','2023-07-11','옵션없음')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(17,'user17@example.com',null,'월세',729,26,NULL,NULL,44,'2023-12-24','서울특별시 강서구','3','01052819350','2023-05-08','냉장고,전자레인지')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(18,'user3@example.com',null,'월세',1200,165,NULL,NULL,39,'2023-11-12','서울특별시 강동구','-1','01053906892','2024-03-08','전자레인지,침대,가스레인지,세탁기')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(19,'user19@example.com',null,'전세',NULL,NULL,48164,NULL,9,'2023-09-26','서울특별시 강서구','1','01026667287','2023-07-28','침대,가스레인지')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(20,'user6@example.com',null,'월세',714,160,NULL,NULL,38,'2023-12-09','서울특별시 강서구','5','01000223408','2023-08-13','가스레인지,전자레인지,에어컨')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(21,'user34@example.com',null,'전세',NULL,NULL,12590,NULL,26,'2023-11-25','서울특별시 강동구','-1','01043924419','2023-09-23','가스레인지,에어컨,냉장고,침대')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(22,'user29@example.com',null,'월세',1399,124,NULL,NULL,33,'2024-01-08','서울특별시 강동구','1','01084169557','2023-07-01','옵션없음')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(23,'user19@example.com',null,'전세',NULL,NULL,38646,NULL,5,'2023-07-17','서울특별시 강서구','2','01090280566','2023-12-01','전자레인지,냉장고,에어컨,침대')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(24,'user38@example.com',null,'월세',568,143,NULL,NULL,8,'2024-01-01','서울특별시 강동구','1','01097018670','2024-03-27','가스레인지')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(25,'user13@example.com',null,'월세',1059,181,NULL,NULL,17,'2023-06-02','서울특별시 강북구','rooftop','01037555579','2023-12-08','침대,가스레인지,세탁기,에어컨')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(51,'user28@example.com',null,'월세',533,43,NULL,NULL,40,'2024-02-11','서울특별시 강서구','-1','01091671936','2023-05-08','침대,냉장고,전자레인지')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(27,'user3@example.com',null,'매매',NULL,NULL,NULL,71427,53,'2024-03-11','서울특별시 강서구','1','01017516418','2023-11-18','풀옵션')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(28,'user32@example.com',null,'매매',NULL,NULL,NULL,101192,7,'2023-11-05','서울특별시 강동구','4','01075720607','2023-12-16','세탁기,전자레인지,가스레인지')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(29,'user25@example.com',null,'전세',NULL,NULL,29006,NULL,21,'2023-08-24','서울특별시 강동구','5','01004627642','2023-05-11','가스레인지')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(30,'user4@example.com',null,'전세',NULL,NULL,28667,NULL,55,'2023-07-15','서울특별시 강서구','2','01007869320','2023-09-06','냉장고,가스레인지,에어컨')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(31,'user31@example.com',null,'월세',580,76,NULL,NULL,15,'2023-07-22','서울특별시 강남구','4','01062192858','2023-05-02','전자레인지,세탁기,가스레인지,냉장고')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(32,'user50@example.com',null,'전세',NULL,NULL,8475,NULL,8,'2023-04-22','서울특별시 강북구','3','01030912592','2023-09-03','가스레인지,전자레인지,에어컨,침대')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(33,'user45@example.com',null,'전세',NULL,NULL,44516,NULL,28,'2023-11-30','서울특별시 강남구','5','01004254304','2023-12-21','전자레인지,냉장고')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(34,'user4@example.com',null,'월세',1496,45,NULL,NULL,50,'2023-11-02','서울특별시 강동구','rooftop','01057427714','2023-10-14','냉장고,가스레인지,전자레인지')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(35,'user41@example.com',null,'월세',1275,78,NULL,NULL,56,'2023-09-02','서울특별시 강동구','-1','01007773921','2024-03-21','전자레인지,냉장고,세탁기,에어컨')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(36,'user9@example.com',null,'매매',NULL,NULL,NULL,62436,21,'2023-07-10','서울특별시 강서구','rooftop','01095835269','2023-04-27','풀옵션')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(37,'user43@example.com',null,'매매',NULL,NULL,NULL,113162,16,'2023-09-29','서울특별시 강서구','1','01027779375','2023-08-01','냉장고,침대,가스레인지,전자레인지,에어컨')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(38,'user3@example.com',null,'전세',NULL,NULL,20873,NULL,44,'2023-12-09','서울특별시 강북구','2','01041950762','2023-06-01','옵션없음')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(39,'user44@example.com',null,'월세',704,90,NULL,NULL,21,'2023-11-07','서울특별시 강서구','4','01017779959','2023-09-24','풀옵션')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(40,'user30@example.com',null,'월세',784,79,NULL,NULL,58,'2024-02-29','서울특별시 강서구','5','01014571225','2024-01-16','침대,에어컨,전자레인지,냉장고')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(41,'user37@example.com',null,'전세',NULL,NULL,35244,NULL,29,'2024-02-05','서울특별시 강북구','rooftop','01039057592','2023-10-04','풀옵션')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(42,'user9@example.com',null,'전세',NULL,NULL,43101,NULL,45,'2023-05-27','서울특별시 강북구','5','01053953341','2023-08-13','옵션없음')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(43,'user2@example.com',null,'전세',NULL,NULL,25993,NULL,57,'2023-11-30','서울특별시 강서구','3','01015540774','2024-03-27','침대,전자레인지,에어컨')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(44,'user48@example.com',null,'월세',369,67,NULL,NULL,45,'2023-05-11','서울특별시 강남구','4','01026933609','2024-02-01','옵션없음')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(45,'user15@example.com',null,'전세',NULL,NULL,12565,NULL,16,'2023-10-05','서울특별시 강동구','3','01054863885','2024-01-11','가스레인지,세탁기')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(46,'user26@example.com',null,'전세',NULL,NULL,46086,NULL,44,'2024-03-03','서울특별시 강북구','4','01026361638','2023-06-16','풀옵션')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(47,'user4@example.com',null,'월세',550,116,NULL,NULL,45,'2024-02-03','서울특별시 강동구','-1','01001176999','2023-11-12','옵션없음')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(48,'user35@example.com',null,'전세',NULL,NULL,49843,NULL,60,'2024-03-18','서울특별시 강남구','3','01009829059','2023-06-09','에어컨,세탁기,가스레인지,전자레인지')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(49,'user26@example.com',null,'전세',NULL,NULL,13864,NULL,25,'2023-07-01','서울특별시 강북구','1','01021386944','2023-08-14','풀옵션')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(50,'user44@example.com',null,'전세',NULL,NULL,10264,NULL,47,'2023-08-31','서울특별시 강남구','rooftop','01029684532','2023-12-22','가스레인지,전자레인지')
SELECT * FROM DUAL;
```

### 3. 마이페이지(MY_PAGE) 테이블 데이터

작성자 : 서용욱, 김동환

```sql
INSERT ALL
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user7@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user8@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user9@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user10@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user11@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user12@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user13@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user14@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user15@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user16@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user17@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user18@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user19@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user20@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user21@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user22@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user23@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user24@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user25@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user26@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user27@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user28@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user29@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user30@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user31@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user32@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user33@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user34@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user35@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user36@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user37@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user38@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user39@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user40@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user41@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user42@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user43@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user44@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user45@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user46@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user47@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user48@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user49@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user50@example.com', 1)
SELECT * FROM DUAL;
INSERT ALL 
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('dyddnr33@naver.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('wnsdud12@daum.com', 1) 
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('kimfjd@naver.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('khk28@naver.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user1@example.com', 1) 
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user2@example.com', 1) 
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user3@example.com', 0) 
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user4@example.com', 1) 
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user5@example.com', 1) 
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user6@example.com', 1) 
SELECT * FROM DUAL;
```

### 4. 찜한 방 목록(LOVE ROOM)테이블 데이터

작성자 : 이경섭, 김동환

```sql
-- 5. 찜 목록 페이지 데이터
-- DECLARE ~ END 한 묶음씩 삽입 할 것
DECLARE                     
   v_house_num   NUMBER   :=6;            
BEGIN                     
INSERT   INTO   love_room   (user_id,   Shouse_num)   VALUES   ('user38@example.com',   v_house_num);
END;   

DECLARE                     
   v_house_num   NUMBER   :=21;            
BEGIN                     
INSERT   INTO   love_room   (user_id,   Shouse_num)   VALUES   ('user22@example.com',   v_house_num);
END;                     

DECLARE                     
   v_house_num   NUMBER   :=36;            
BEGIN                     
INSERT   INTO   love_room   (user_id,   Shouse_num)   VALUES   ('user36@example.com',   v_house_num);
END;

DECLARE                     
   v_house_num   NUMBER   :=41;            
BEGIN                     
INSERT   INTO   love_room   (user_id,   Shouse_num)   VALUES   ('user46@example.com',   v_house_num);
END;                     

DECLARE                     
   v_house_num   NUMBER   :=23;            
BEGIN                     
INSERT   INTO   love_room   (user_id,   Shouse_num)   VALUES   ('user40@example.com',   v_house_num);
END;                     

DECLARE                     
   v_house_num   NUMBER   :=13;            
BEGIN                     
INSERT   INTO   love_room   (user_id,   Shouse_num)   VALUES   ('user24@example.com',   v_house_num);
END;                     

DECLARE                     
   v_house_num   NUMBER   :=29;            
BEGIN                     
INSERT   INTO   love_room   (user_id,   Shouse_num)   VALUES   ('user25@example.com',   v_house_num);
END;                     

DECLARE                     
   v_house_num   NUMBER   :=29;            
BEGIN                     
INSERT   INTO   love_room   (user_id,   Shouse_num)   VALUES   ('user44@example.com',   v_house_num);
END;                     

DECLARE                     
   v_house_num   NUMBER   :=48;            
BEGIN                     
INSERT   INTO   love_room   (user_id,   Shouse_num)   VALUES   ('user31@example.com',   v_house_num);
END;                     

DECLARE                     
   v_house_num   NUMBER   :=19;            
BEGIN                     
INSERT   INTO   love_room   (user_id,   Shouse_num)   VALUES   ('user8@example.com',   v_house_num);
END;                     

DECLARE                     
   v_house_num   NUMBER   :=43;            
BEGIN                     
INSERT   INTO   love_room   (user_id,   Shouse_num)   VALUES   ('user8@example.com',   v_house_num);
END;

DECLARE                     
   v_house_num   NUMBER   :=47;            
BEGIN                     
INSERT   INTO   love_room   (user_id,   Shouse_num)   VALUES   ('user45@example.com',   v_house_num);
END;                     

DECLARE                     
   v_house_num   NUMBER   :=17;            
BEGIN                     
INSERT   INTO   love_room   (user_id,   Shouse_num)   VALUES   ('user29@example.com',   v_house_num);
END;                     

DECLARE                     
   v_house_num   NUMBER   :=15;            
BEGIN                     
INSERT   INTO   love_room   (user_id,   Shouse_num)   VALUES   ('user40@example.com',   v_house_num);
END;      

DECLARE                     
   v_house_num   NUMBER   :=36;            
BEGIN                     
INSERT   INTO   love_room   (user_id,   Shouse_num)   VALUES   ('user17@example.com',   v_house_num);
END;                     

DECLARE                     
   v_house_num   NUMBER   :=5;            
BEGIN                     
INSERT   INTO   love_room   (user_id,   Shouse_num)   VALUES   ('user17@example.com',   v_house_num);
END;                     

DECLARE                     
   v_house_num   NUMBER   :=20;            
BEGIN                     
INSERT   INTO   love_room   (user_id,   Shouse_num)   VALUES   ('user35@example.com',   v_house_num);
END;                     

DECLARE                     
   v_house_num   NUMBER   :=17;            
BEGIN                     
INSERT   INTO   love_room   (user_id,   Shouse_num)   VALUES   ('user21@example.com',   v_house_num);
END;      

DECLARE                     
   v_house_num   NUMBER   :=44;            
BEGIN                     
INSERT   INTO   love_room   (user_id,   Shouse_num)   VALUES   ('user29@example.com',   v_house_num);
END;         

DECLARE                     
   v_house_num   NUMBER   :=7;            
BEGIN                     
INSERT   INTO   love_room   (user_id,   Shouse_num)   VALUES   ('user40@example.com',   v_house_num);
END;
```

### 5. 관리자 계정(ADMIN INFO)테이블 데이터

작성자 : 이경섭

```sql
INSERT INTO ADMIN_INFO VALUES (1, 'can3487@naver.com','admin3487');
INSERT INTO ADMIN_INFO VALUES (2, 'dyddnr33@naver.com','admin1691');
INSERT INTO ADMIN_INFO VALUES (3, 'kimfid@naver.com','admin4920');
INSERT INTO ADMIN_INFO VALUES (4, 'tpgh1554@naver.com','admin5648');

```

## 전체 쿼리문(수정)

```sql
-- 1. 회원(MEMBER) 테이블

CREATE TABLE INFO(
   USER_MAIL VARCHAR2(255) PRIMARY KEY NOT NULL,
   PASSWORD VARCHAR2(255) NOT NULL,
   NAME VARCHAR2(255) NOT NULL,
   FIRST_ID_CARDNUM NUMBER(6) NOT NULL,
   PHONE_NUMBER NUMBER(11) NOT NULL,
   ADDRESS VARCHAR2(255),
   GENDER NUMBER(2) NOT NULL,
   NICKNAME VARCHAR2(255) NOT NULL
   );

INSERT INTO INFO VALUES ('dyddnr33@naver.com', 'asdf', '서용욱', 960125, 01098111691, '서울시 관악구', 1, '막강한바지');
INSERT INTO INFO VALUES ('wnsdud12@daum.com', '123456', '김준영', 940321, 01043453454, '서울시 강남구', 2, '불법유턴');
INSERT INTO INFO VALUES ('kimfjd@naver.com', '1234','김동환',991130,01022645648,'경기도 이천시',1,'집가자');
INSERT INTO INFO VALUES ('khk28@naver.com', '1325','김현근',970923,01042675900,'경기도 남양주시',1,'집으로');
INSERT INTO INFO VALUES ('user1@example.com', 'password1', '홍길동', 980101, 01012345678, '서울시 강남구', 1, '의적');
INSERT INTO INFO VALUES ('user2@example.com', 'password2', '이순신', 970202, 01023456789, '서울시 종로구', 2, '충무공');
INSERT INTO INFO VALUES ('user3@example.com', 'password3', '김유신', 990303, 01034567890, '경기도 수원시', 1, '산책하기');
INSERT INTO INFO VALUES ('user4@example.com', 'password4', '신사임당', 960404, 01045678901, '인천시 남구', 2, '5만원');
INSERT INTO INFO VALUES ('user5@example.com', 'password5', '강감찬', 950505, 01056789012, '대구시 중구', 1, '운동하기');
INSERT INTO INFO VALUES ('user6@example.com', 'password6', '을지문덕', 940606, 01067890123, '부산시 해운대구', 2, '살수대첩');

-- 방 등록(GRADE) 테이블 
CREATE TABLE RoomInfo(
    house_num NUMBER(10) PRIMARY KEY NOT NULL,
    user_id VARCHAR2(255) UNIQUE NOT NULL,
    photo_url VARCHAR2(255),
    trade_method VARCHAR(50) NOT NULL, 
    price NUMBER(15) NOT NULL,
    area VARCHAR2(255) NOT NULL,
    accept_date DATE,
    address VARCHAR2(255) NOT NULL,
    floor1 VARCHAR2(255) NOT NULL,
    phonenumber  NUMBER(15) NOT NULL,
    regit_date DATE,
    remark VARCHAR2(255),
    love NUMBER(2) NULL
);

INSERT INTO RoomInfo VALUES(123456,'ABC123','URL1', '월세',300000,'13','2024-03-29','서울특별시 강남구',
'3층','01022221111','2024-03-29','냉장고, 세탁기', null);
INSERT INTO RoomInfo VALUES(456789,'POI321','URL2', '월세',380000,'14','2023-12-29','서울특별시 성북구',
'2층','01012345678','2023-12-29','가스레인지', null);
INSERT INTO RoomInfo VALUES(958175,'ZXC156','URL3', '전세',180000000,'16','2024-02-14','서울특별시 성동구',
'13층','01032165489','2024-02-14', null, null);
INSERT INTO RoomInfo VALUES(654186,'GER412','URL4', '월세',500000,'14','2024-01-06','서울특별시 강동구',
'3층','01022234231','2024-01-06','냉장고, 세탁기, 가스레인지', null);
INSERT INTO RoomInfo VALUES(127353,'JNR623','URL5', '월세',250000,'14','2024-03-19','서울특별시 강서구',
'반지층','01012535345','2024-03-19', null, null);
INSERT INTO RoomInfo VALUES(843568,'MFG234','URL6', '월세',320000,'12','2024-02-20','서울특별시 강남구',
'5층','01094626485','2024-02-20','냉장고, 가스레인지', null);
INSERT INTO RoomInfo VALUES(54372,'KGD676','URL7', '월세',300000,'13','2024-03-11','서울특별시 강남구',
'3층','01034517864','2024-03-11', null, null);
INSERT INTO RoomInfo VALUES(765434,'KTA424','URL8', '월세',360000,'14','2024-01-29','서울특별시 성북구',
'루프탑','01046184634','2024-01-29','냉장고, 세탁기', null);
INSERT INTO RoomInfo VALUES(973451,'ERT634','URL9', '월세',560000,'16','2023-11-29','서울특별시 성동구',
'2층','01032415846','2023-11-29','풀옵션', null);
INSERT INTO RoomInfo VALUES(364562,'MDG543','URL10', '월세',330000,'13','2024-03-08','서울특별시 강동구',
'3층','01076454564','2024-03-08','냉장고', null);

-- 3. 마이페이지(MY PAGE) 테이블
CREATE TABLE MY_PAGE(
   USER_MAIL VARCHAR2(255) UNIQUE NOT NULL,
   LOVE_ROOM NUMBER(2),
   profile_photo VARCHAR2(255) 
   );

INSERT ALL 
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('dyddnr33@naver.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('wnsdud12@daum.com', 1) 
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('kimfjd@naver.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('khk28@naver.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user1@example.com', 1) 
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user2@example.com', 1) 
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user3@example.com', 0) 
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user4@example.com', 1) 
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user5@example.com', 1) 
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user6@example.com', 1) 
SELECT * FROM DUAL;

-- 4. 찜한 방 목록 테이블 생성
create table love_room(
   user_id varchar(255) REFERENCES my_page(USER_MAIL) not NULL,
   Shouse_num NUMBER(10) references RoomInfo(house_num) NOT null
);
INSERT INTO love_room VALUES('dyddnr33@naver.com',123456);
INSERT INTO love_room VALUES('dyddnr33@naver.com',456789);
INSERT INTO love_room VALUES('dyddnr33@naver.com',958175);
INSERT INTO love_room VALUES('wnsdud12@daum.com',958175);
INSERT INTO love_room VALUES('kimfjd@naver.com',843568);
INSERT INTO love_room VALUES('kimfjd@naver.com',958175);
INSERT INTO love_room VALUES('kimfjd@naver.com',456789);
INSERT INTO love_room VALUES('khk28@naver.com',123456);
INSERT INTO love_room VALUES('user1@example.com',123456);
INSERT INTO love_room VALUES('user1@example.com',958175);
INSERT INTO love_room VALUES('user1@example.com',654186);
INSERT INTO love_room VALUES('user2@example.com',123456);
INSERT INTO love_room VALUES('user2@example.com',54372);
INSERT INTO love_room VALUES('user2@example.com',127353);
INSERT INTO love_room VALUES('user2@example.com',654186);
INSERT INTO love_room VALUES('user4@example.com',456789);
INSERT INTO love_room VALUES('user4@example.com',654186);
INSERT INTO love_room VALUES('user4@example.com',54372);
INSERT INTO love_room VALUES('user5@example.com',654186);
INSERT INTO love_room VALUES('user5@example.com',127353);
INSERT INTO love_room VALUES('user5@example.com',765434);
INSERT INTO love_room VALUES('user5@example.com',973451);
INSERT INTO love_room VALUES('user6@example.com',364562);
INSERT INTO love_room VALUES('user6@example.com',973451);

-- 5. 관리자 계정(ADMIN INFO)

CREATE TABLE ADMIN_INFO(
   ADM_NO NUMBER(10) UNIQUE NOT NULL,
   ADM_ID VARCHAR(255) NOT NULL,
   ADM_PW VARCHAR(255)
   );

INSERT INTO ADMIN_INFO VALUES (1, 'can3487@naver.com','admin3487');
INSERT INTO ADMIN_INFO VALUES (2, 'dyddnr33@naver.com','admin1691');
INSERT INTO ADMIN_INFO VALUES (3, 'kimfid@naver.com','admin4920');
INSERT INTO ADMIN_INFO VALUES (4, 'tpgh1554@naver.com','admin5648');

SELECT * FROM INFO;
SELECT * FROM RoomInfo;
SELECT * FROM MY_PAGE;
SELECT * FROM love_room;
SELECT * FROM ADMIN_INFO;

--DROP TABLE INFO;
--DROP TABLE RoomInfo;
--DROP TABLE MY_PAGE;
--DROP TABLE love_room;
--DROP TABLE ADMIN_INFO;

```

## 전체 쿼리문 최종

ROOMINFO 데이터, LOVE_ROOM은 임의 데이터 삽입, ROOINFO 테이블에 가격(price)를 보증금(deposit), 월세(monthly), 전세금(jeonsegeum), 매매가(Sale_price)로 변경

```sql
-- 1. 회원(MEMBER) 테이블
CREATE TABLE INFO(
   USER_MAIL VARCHAR2(255) PRIMARY KEY NOT NULL, -- USER 아이디
   PASSWORD VARCHAR2(255) NOT NULL, -- PASSWORD
   NAME VARCHAR2(255) NOT NULL,-- 이름
   FIRST_ID_CARDNUM NUMBER(6) NOT NULL, -- 주민번호 앞자리
   PHONE_NUMBER NUMBER(11) NOT NULL, -- 전화번호(-빼고)
   ADDRESS VARCHAR2(255), -- 주소
   GENDER NUMBER(2) NOT NULL, -- 성별
   NICKNAME VARCHAR2(255) NOT NULL -- 닉네임
   );

-- 2. 마이페이지(MY PAGE) 테이블
CREATE TABLE MY_PAGE(
   USER_MAIL VARCHAR2(255) UNIQUE NOT NULL, -- 유저 아이디
   LOVE_ROOM NUMBER(2), -- 찜한 데이터 유무
   profile_photo VARCHAR2(255) -- 프로필 사진URL
   );

-- 3. 방 등록(GRADE) 테이블 
CREATE TABLE RoomInfo(
    house_num NUMBER(10) PRIMARY KEY NOT NULL, -- 매물번호(시퀀스로 자동생성)
    user_id VARCHAR2(255) NOT NULL, -- 등록자 아이디
    photo_url VARCHAR2(255), -- 방 사진URL
    trade_method VARCHAR(50) NOT NULL, -- 거래 방식
    deposit NUMBER(15), -- 보증금(만원) : 월세만 해당.
    monthly NUMBER(15), -- 월세(만원) : 월세만 해당.
    jeonsegeum NUMBER(15), -- 전세금(만원) : 전세만 해당
    Sale_price NUMBER(15), -- 매매가(만원) : 매매만 해당
    area VARCHAR2(255) NOT NULL, -- 평수 : ~평
    accept_date DATE, -- 사용 승인일
    address VARCHAR2(255) NOT NULL, -- 매물주소
    floor1 VARCHAR2(255) NOT NULL, -- 층수
    phonenumber  NUMBER(15) NOT NULL, -- 집주인 전화번호
    regit_date DATE, -- 매물 등록일
    remark VARCHAR2(255) -- 비고(옵션)
);

-- 4. 찜한 방 목록 테이블 생성
create table love_room(
   user_id varchar(255) REFERENCES my_page(USER_MAIL) not NULL, -- 유저아이디(찜한방 목록에 있어야함.)
   Shouse_num NUMBER(10) references RoomInfo(house_num) NOT NULL -- 매물번호(방 정보 테이블에서 시퀀스로 입력)
);

-- 5. 관리자 계정(ADMIN INFO)
CREATE TABLE ADMIN_INFO(
   ADM_NO NUMBER(10) UNIQUE NOT NULL, -- 관리자 번호
   ADM_ID VARCHAR(255) NOT NULL, -- 관리자 아이디
   ADM_PW VARCHAR(255) NOT NULL -- 관리자 PASSWORD
   );

-- SEQ_ROOM 시퀀스 테이블 생성
CREATE SEQUENCE SEQ_ROOM
START WITH 1
INCREMENT BY 1
MAXVALUE 10000 -- 10000개 매물까지
MINVALUE 1
NOCYCLE;
--======================================================= 데이터 입력 ===============================================================

--1. 회원 테이블 데이터
INSERT ALL 
INTO INFO VALUES ('dyddnr33@naver.com', 'asdf', '서용욱', 960125, 01098111691, '서울시 관악구', 1, '막강한바지')
INTO INFO VALUES ('wnsdud12@daum.com', '123456', '김준영', 940321, 01043453454, '서울시 강남구', 2, '불법유턴')
INTO INFO VALUES ('kimfjd@naver.com', '1234','김동환',991130,01022645648,'경기도 이천시',1,'집가자')
INTO INFO VALUES ('khk28@naver.com', '1325','김현근',970923,01042675900,'경기도 남양주시',1,'집으로')
INTO INFO VALUES ('user1@example.com', 'password1', '홍길동', 980101, 01012345678, '서울시 강남구', 1, '의적')
INTO INFO VALUES ('user2@example.com', 'password2', '이순신', 970202, 01023456789, '서울시 종로구', 2, '충무공')
INTO INFO VALUES ('user3@example.com', 'password3', '김유신', 990303, 01034567890, '경기도 수원시', 1, '산책하기')
INTO INFO VALUES ('user4@example.com', 'password4', '신사임당', 960404, 01045678901, '인천시 남구', 2, '5만원')
INTO INFO VALUES ('user5@example.com', 'password5', '강감찬', 950505, 01056789012, '대구시 중구', 1, '운동하기')
INTO INFO VALUES ('user6@example.com', 'password6', '을지문덕', 940606, 01067890123, '부산시 해운대구', 2, '살수대첩')
SELECT * FROM DUAL;
INSERT ALL
INTO INFO VALUES ('user7@example.com','password7','홍길동',980101,01012345678,'서울시 강남구',2,'의적')
INTO INFO VALUES ('user8@example.com','password8','김유신',990303,01034567890,'서울시 강남구',2,'산책하기')
INTO INFO VALUES ('user9@example.com','password9','홍길동',980101,01012345678,'서울시 강남구',1,'의적')
INTO INFO VALUES ('user10@example.com','password10','김유신',990303,01034567890,'서울시 강남구',1,'산책하기')
INTO INFO VALUES ('user11@example.com','password11','신사임당',960404,01045678901,'서울시 강남구',2,'5만원')
INTO INFO VALUES ('user12@example.com','password12','김유신',990303,01034567890,'서울시 강남구',2,'산책하기')
INTO INFO VALUES ('user13@example.com','password13','을지문덕',940606,01067890123,'서울시 강남구',1,'살수대첩')
INTO INFO VALUES ('user14@example.com','password14','홍길동',980101,01012345678,'서울시 강남구',2,'의적')
INTO INFO VALUES ('user15@example.com','password15','김유신',990303,01034567890,'서울시 강남구',2,'산책하기')
INTO INFO VALUES ('user16@example.com','password16','홍길동',980101,01012345678,'서울시 강남구',2,'의적')
INTO INFO VALUES ('user17@example.com','password17','이순신',970202,01023456789,'서울시 강남구',2,'충무공')
INTO INFO VALUES ('user18@example.com','password18','김유신',990303,01034567890,'서울시 강남구',2,'산책하기')
INTO INFO VALUES ('user19@example.com','password19','을지문덕',940606,01067890123,'서울시 강남구',1,'살수대첩')
INTO INFO VALUES ('user20@example.com','password20','이순신',970202,01023456789,'서울시 강남구',2,'충무공')
INTO INFO VALUES ('user21@example.com','password21','강감찬',950505,01056789012,'서울시 강남구',2,'운동하기')
INTO INFO VALUES ('user22@example.com','password22','신사임당',960404,01045678901,'서울시 강남구',1,'5만원')
INTO INFO VALUES ('user23@example.com','password23','홍길동',980101,01012345678,'서울시 강남구',2,'의적')
INTO INFO VALUES ('user24@example.com','password24','을지문덕',940606,01067890123,'서울시 강남구',2,'살수대첩')
INTO INFO VALUES ('user25@example.com','password25','을지문덕',940606,01067890123,'서울시 종로구',2,'살수대첩')
INTO INFO VALUES ('user26@example.com','password26','홍길동',980101,01012345678,'서울시 종로구',1,'의적')
INTO INFO VALUES ('user27@example.com','password27','신사임당',960404,01045678901,'서울시 종로구',1,'5만원')
INTO INFO VALUES ('user28@example.com','password28','김유신',990303,01034567890,'서울시 종로구',1,'산책하기')
INTO INFO VALUES ('user29@example.com','password29','강감찬',950505,01056789012,'서울시 종로구',2,'운동하기')
INTO INFO VALUES ('user30@example.com','password30','이순신',970202,01023456789,'서울시 종로구',2,'충무공')
INTO INFO VALUES ('user31@example.com','password31','홍길동',980101,01012345678,'서울시 종로구',1,'의적')
INTO INFO VALUES ('user32@example.com','password32','을지문덕',940606,01067890123,'서울시 종로구',1,'살수대첩')
INTO INFO VALUES ('user33@example.com','password33','을지문덕',940606,01067890123,'서울시 종로구',2,'살수대첩')
INTO INFO VALUES ('user34@example.com','password34','이순신',970202,01023456789,'서울시 종로구',1,'충무공')
INTO INFO VALUES ('user35@example.com','password35','을지문덕',940606,01067890123,'서울시 종로구',1,'살수대첩')
INTO INFO VALUES ('user36@example.com','password36','홍길동',980101,01012345678,'서울시 종로구',1,'의적')
INTO INFO VALUES ('user37@example.com','password37','강감찬',950505,01056789012,'서울시 종로구',2,'운동하기')
INTO INFO VALUES ('user38@example.com','password38','신사임당',960404,01045678901,'서울시 종로구',1,'5만원')
INTO INFO VALUES ('user39@example.com','password39','을지문덕',940606,01067890123,'서울시 종로구',2,'살수대첩')
INTO INFO VALUES ('user40@example.com','password40','이순신',970202,01023456789,'경기도 수원시',1,'충무공')
INTO INFO VALUES ('user41@example.com','password41','을지문덕',940606,01067890123,'경기도 수원시',1,'살수대첩')
INTO INFO VALUES ('user42@example.com','password42','홍길동',980101,01012345678,'경기도 수원시',1,'의적')
INTO INFO VALUES ('user43@example.com','password43','이순신',970202,01023456789,'경기도 수원시',2,'충무공')
INTO INFO VALUES ('user44@example.com','password44','신사임당',960404,01045678901,'경기도 수원시',1,'5만원')
INTO INFO VALUES ('user45@example.com','password45','강감찬',950505,01056789012,'경기도 수원시',1,'운동하기')
INTO INFO VALUES ('user46@example.com','password46','김유신',990303,01034567890,'경기도 수원시',1,'산책하기')
INTO INFO VALUES ('user47@example.com','password47','강감찬',950505,01056789012,'경기도 수원시',1,'운동하기')
INTO INFO VALUES ('user48@example.com','password48','강감찬',950505,01056789012,'인천시 남구',1,'운동하기')
INTO INFO VALUES ('user49@example.com','password49','강감찬',950505,01056789012,'인천시 남구',1,'운동하기')
INTO INFO VALUES ('user50@example.com','password50','신사임당',960404,01045678901,'인천시 남구',2,'5만원')
SELECT * FROM DUAL;

-- 2. 마이페이지 테이블 데이터(회원 테이블 데이터와 아이디 동일해야함.)
INSERT ALL
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user7@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user8@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user9@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user10@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user11@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user12@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user13@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user14@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user15@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user16@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user17@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user18@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user19@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user20@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user21@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user22@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user23@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user24@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user25@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user26@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user27@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user28@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user29@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user30@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user31@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user32@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user33@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user34@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user35@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user36@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user37@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user38@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user39@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user40@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user41@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user42@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user43@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user44@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user45@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user46@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user47@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user48@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user49@example.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user50@example.com', 1)
SELECT * FROM DUAL;
INSERT ALL 
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('dyddnr33@naver.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('wnsdud12@daum.com', 1) 
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('kimfjd@naver.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('khk28@naver.com', 1)
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user1@example.com', 1) 
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user2@example.com', 1) 
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user3@example.com', 0) 
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user4@example.com', 1) 
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user5@example.com', 1) 
INTO MY_PAGE(USER_MAIL, LOVE_ROOM) VALUES('user6@example.com', 1) 
SELECT * FROM DUAL;

-- 3. 방 정보 페이지 데이터

INSERT ALL 
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(1,'user6@example.com',null,'월세',1021,184,NULL,NULL,9,'2023-08-21','서울특별시 강남구','1','01093471548','2024-01-31','풀옵션')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(2,'user20@example.com',null,'매매',NULL,NULL,NULL,169728,44,'2023-09-20','서울특별시 강북구','1','01077617068','2024-03-20','세탁기,침대,전자레인지,냉장고')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(3,'user40@example.com',null,'월세',1291,37,NULL,NULL,7,'2024-02-06','서울특별시 강남구','3','01013426296','2023-10-22','풀옵션')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(4,'user19@example.com',null,'전세',NULL,NULL,25867,NULL,36,'2024-02-09','서울특별시 강동구','3','01066257107','2023-12-05','옵션없음')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(5,'user10@example.com',null,'매매',NULL,NULL,NULL,277034,26,'2023-12-27','서울특별시 강남구','-1','01065914256','2023-04-20','침대,에어컨,전자레인지,가스레인지')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(6,'user4@example.com',null,'전세',NULL,NULL,45551,NULL,31,'2023-09-30','서울특별시 강북구','5','01000611150','2023-08-17','냉장고,에어컨,가스레인지,침대')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(7,'user36@example.com',null,'전세',NULL,NULL,43416,NULL,41,'2023-05-09','서울특별시 강북구','2','01017712567','2023-07-25','에어컨,세탁기,냉장고,침대')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(8,'user27@example.com',null,'전세',NULL,NULL,9111,NULL,6,'2024-02-01','서울특별시 강남구','5','01091963462','2023-08-08','가스레인지,에어컨,침대,세탁기')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(9,'user6@example.com',null,'전세',NULL,NULL,22589,NULL,5,'2023-05-12','서울특별시 강서구','4','01097098554','2024-02-10','침대,에어컨')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(10,'user15@example.com',null,'전세',NULL,NULL,33984,NULL,28,'2023-07-20','서울특별시 강남구','-1','01009494887','2024-03-02','에어컨,침대')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(11,'user9@example.com',null,'매매',NULL,NULL,NULL,188033,16,'2023-04-25','서울특별시 강동구','2','01010943141','2024-03-20','냉장고,에어컨,가스레인지,침대,전자레인지')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(12,'user44@example.com',null,'전세',NULL,NULL,35913,NULL,55,'2023-11-22','서울특별시 강남구','3','01029819008','2023-07-08','침대,세탁기,에어컨,가스레인지')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(13,'user21@example.com',null,'월세',1482,24,NULL,NULL,10,'2023-09-22','서울특별시 강서구','-1','01067167160','2023-07-05','침대,가스레인지')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(14,'user17@example.com',null,'매매',NULL,NULL,NULL,221745,39,'2023-08-06','서울특별시 강동구','-1','01013937901','2023-07-25','세탁기,침대,에어컨,전자레인지,가스레인지')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(15,'user21@example.com',null,'월세',1036,133,NULL,NULL,19,'2023-11-05','서울특별시 강남구','4','01008759820','2024-02-28','에어컨,가스레인지,침대,전자레인지,냉장고')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(16,'user42@example.com',null,'전세',NULL,NULL,31279,NULL,14,'2024-02-02','서울특별시 강동구','3','01020612346','2023-07-11','옵션없음')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(17,'user17@example.com',null,'월세',729,26,NULL,NULL,44,'2023-12-24','서울특별시 강서구','3','01052819350','2023-05-08','냉장고,전자레인지')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(18,'user3@example.com',null,'월세',1200,165,NULL,NULL,39,'2023-11-12','서울특별시 강동구','-1','01053906892','2024-03-08','전자레인지,침대,가스레인지,세탁기')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(19,'user19@example.com',null,'전세',NULL,NULL,48164,NULL,9,'2023-09-26','서울특별시 강서구','1','01026667287','2023-07-28','침대,가스레인지')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(20,'user6@example.com',null,'월세',714,160,NULL,NULL,38,'2023-12-09','서울특별시 강서구','5','01000223408','2023-08-13','가스레인지,전자레인지,에어컨')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(21,'user34@example.com',null,'전세',NULL,NULL,12590,NULL,26,'2023-11-25','서울특별시 강동구','-1','01043924419','2023-09-23','가스레인지,에어컨,냉장고,침대')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(22,'user29@example.com',null,'월세',1399,124,NULL,NULL,33,'2024-01-08','서울특별시 강동구','1','01084169557','2023-07-01','옵션없음')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(23,'user19@example.com',null,'전세',NULL,NULL,38646,NULL,5,'2023-07-17','서울특별시 강서구','2','01090280566','2023-12-01','전자레인지,냉장고,에어컨,침대')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(24,'user38@example.com',null,'월세',568,143,NULL,NULL,8,'2024-01-01','서울특별시 강동구','1','01097018670','2024-03-27','가스레인지')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(25,'user13@example.com',null,'월세',1059,181,NULL,NULL,17,'2023-06-02','서울특별시 강북구','rooftop','01037555579','2023-12-08','침대,가스레인지,세탁기,에어컨')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(51,'user28@example.com',null,'월세',533,43,NULL,NULL,40,'2024-02-11','서울특별시 강서구','-1','01091671936','2023-05-08','침대,냉장고,전자레인지')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(27,'user3@example.com',null,'매매',NULL,NULL,NULL,71427,53,'2024-03-11','서울특별시 강서구','1','01017516418','2023-11-18','풀옵션')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(28,'user32@example.com',null,'매매',NULL,NULL,NULL,101192,7,'2023-11-05','서울특별시 강동구','4','01075720607','2023-12-16','세탁기,전자레인지,가스레인지')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(29,'user25@example.com',null,'전세',NULL,NULL,29006,NULL,21,'2023-08-24','서울특별시 강동구','5','01004627642','2023-05-11','가스레인지')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(30,'user4@example.com',null,'전세',NULL,NULL,28667,NULL,55,'2023-07-15','서울특별시 강서구','2','01007869320','2023-09-06','냉장고,가스레인지,에어컨')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(31,'user31@example.com',null,'월세',580,76,NULL,NULL,15,'2023-07-22','서울특별시 강남구','4','01062192858','2023-05-02','전자레인지,세탁기,가스레인지,냉장고')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(32,'user50@example.com',null,'전세',NULL,NULL,8475,NULL,8,'2023-04-22','서울특별시 강북구','3','01030912592','2023-09-03','가스레인지,전자레인지,에어컨,침대')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(33,'user45@example.com',null,'전세',NULL,NULL,44516,NULL,28,'2023-11-30','서울특별시 강남구','5','01004254304','2023-12-21','전자레인지,냉장고')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(34,'user4@example.com',null,'월세',1496,45,NULL,NULL,50,'2023-11-02','서울특별시 강동구','rooftop','01057427714','2023-10-14','냉장고,가스레인지,전자레인지')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(35,'user41@example.com',null,'월세',1275,78,NULL,NULL,56,'2023-09-02','서울특별시 강동구','-1','01007773921','2024-03-21','전자레인지,냉장고,세탁기,에어컨')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(36,'user9@example.com',null,'매매',NULL,NULL,NULL,62436,21,'2023-07-10','서울특별시 강서구','rooftop','01095835269','2023-04-27','풀옵션')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,phonenumber,regit_date,remark) VALUES(37,'user43@example.com',null,'매매',NULL,NULL,NULL,113162,16,'2023-09-29','서울특별시 강서구','1','01027779375','2023-08-01','냉장고,침대,가스레인지,전자레인지,에어컨')
INTO RoomInfo(house_num,user_id,photo_url,trade_method,deposit,monthly,jeonsegeum,Sale_price,area,accept_date,address,floor1,ph
