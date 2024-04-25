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

-- 4. 관리자 페이지 데이터
INSERT ALL
INTO ADMIN_INFO VALUES (1, 'can3487@naver.com','admin3487')
INTO ADMIN_INFO VALUES (2, 'dyddnr33@naver.com','admin1691')
INTO ADMIN_INFO VALUES (3, 'kimfid@naver.com','admin4920')
INTO ADMIN_INFO VALUES (4, 'tpgh1554@naver.com','admin5648')
SELECT * FROM DUAL;

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
--=============================================================================================================================================================

-- 테이블 보기
SELECT * FROM INFO;
SELECT * FROM MY_PAGE;
SELECT * FROM RoomInfo;
SELECT * FROM love_room;
SELECT * FROM ADMIN_INFO;

-- 테이블 삭제
DROP TABLE INFO;
DROP TABLE RoomInfo;
DROP TABLE MY_PAGE;
DROP TABLE love_room;
DROP TABLE ADMIN_INFO;
-- 시퀀스 삭제
DROP SEQUENCE SEQ_ROOM;
```

# 7. JDBC

## TEST SAMPLE

- TEST Info
    - InfoDao
        
        작성자 : 서용욱
        
        ```java
        package 올룸골룸.VO;
        
        import KH_JDBC.COMMON.Common;
        import KH_JDBC.VO.EmpVo;
        import 올룸골룸.VO.lumCommon;
        import 올룸골룸.VO.lumDAO;
        import 올룸골룸.VO.OlumGollum;
        import 올룸골룸.VO.lumMain;
        
        import java.math.BigDecimal;
        import java.sql.*;
        import java.text.SimpleDateFormat;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.Scanner;
        
            public class lumDAO extends lumMain {
                Connection conn = null;
                Statement stmt =null; // create Statement 방식
                PreparedStatement pSmt = null; // Prepared Statement 방식
                ResultSet rs = null; // database 부터 결과를 받는 변수
                Scanner sc = new Scanner(System.in);
                // SELECT문(조회)
                public List<OlumGollum> olumGollumSelect() {
                    List<OlumGollum> list = new ArrayList<>();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        conn = lumCommon.getConnection();
                        stmt = conn.createStatement();
                        String sql = "SELECT * FROM INFO";
                        rs = stmt.executeQuery(sql);
                        while (rs.next()) {
                            String user_mail = rs.getString("USER_MAIL");
                            String password = rs.getString("PASSWORD");
                            String name = rs.getString("NAME");
                            int id_card_number = rs.getInt("FIRST_ID_CARDNUM");
                            int phone_number = rs.getInt("PHONE_NUMBER");
                            String address = rs.getString("ADDRESS");
                            int gender = rs.getInt("GENDER");
                            String nickname = rs.getString("NICKNAME");
                            list.add(new OlumGollum(user_mail, password, name, id_card_number, phone_number, address, gender, nickname));
                        }
                        lumCommon.close(rs);
                        lumCommon.close(stmt);
                        lumCommon.close(conn);
        
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    return list;
                }
                public void infoInsert(){
                    OlumGollum olumGollum = InputFunc();
                    String query = "INSERT INTO INFO(USER_MAIL, PASSWORD, NAME, FIRST_ID_CARDNUM, PHONE_NUMBER, ADDRESS, GENDER, NICKNAME)" +
                            "VALUES(?,?,?,?,?,?,?,?)";
                    try{
                        conn = Common.getConnection();
                        pSmt = conn.prepareStatement(query);
                        pSmt.setString(1,olumGollum.getUser_mail());
                        pSmt.setString(2,olumGollum.getPassword());
                        pSmt.setString(3,olumGollum.getName());
                        pSmt.setInt(4,olumGollum.getFirst_id_cardnum());
                        pSmt.setInt(5,olumGollum.getPhone_number());
                        pSmt.setString(6,olumGollum.getAddress());
                        pSmt.setInt(7,olumGollum.getGender());
                        pSmt.setString(8,olumGollum.getNickname());
                        int rst = pSmt.executeUpdate();
                        System.out.print("rst :" + rst);
        
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    Common.close(pSmt);
                    Common.close(conn);
                }
                public void Selectprn(List<OlumGollum> list){
                    for (OlumGollum e : list){
                        System.out.print(e.getUser_mail() + " ");
                        System.out.print(e.getAddress() + " ");
                        System.out.print(e.getPassword() + " ");
                        System.out.print(e.getName() + " ");
                        System.out.print(e.getGender()+ " ");
                        System.out.print(e.getPhone_number() + " ");
                        System.out.print(e.getFirst_id_cardnum() + " ");
                        System.out.println(e.getNickname());
                    }
                }
            }
        
        ```
        
    - InfoMain
        
        작성자: 서용욱
        
        ```java
        package 올룸골룸.VO;
        
        import KH_JDBC.VO.EmpVo;
        
        import java.math.BigDecimal;
        import java.util.List;
        import java.util.Scanner;
        
        public class lumMain {
            public static void main(String[] args) {
                Scanner sc = new Scanner(System.in);
                lumDAO dao = new lumDAO();
        
                while (true) {
                    System.out.println("=====================================================================");
                    System.out.print("메뉴 선택 :[0]EXITS [1]SELECT [2]INSERT [3]UPDATE [4]DELETE [5]QUIT : ");
                    int sel = sc.nextInt();
                    switch (sel) {
                        case 1:
                            List<OlumGollum> list = dao.olumGollumSelect();
                            dao.Selectprn(list);
                            break;
                        case 2:
                            dao.infoInsert();
                            break;
                        default:
                            System.out.print("메뉴를 잘못 선택하셨습니다.");
                    }
                    if (sel == 0 || sel == 5) break;
                }
        
            }
            public OlumGollum InputFunc() {
                Scanner sc = new Scanner(System.in);
                System.out.println("회원가입 페이지 : ");
                System.out.print("아이디 입력(mail) : ");
                String user_mail = sc.next();
                System.out.print("비밀번호 입력 : ");
                String password = sc.next();
                System.out.print("이름 : ");
                String name = sc.next();
                System.out.print("생년월일 입력 : ");
                int first_id_cardnum = sc.nextInt();
                System.out.print("휴대폰 번호 입력 : ");
                int phone_number = sc.nextInt();
                sc.nextLine();
                System.out.print("주소 입력 : ");
                String address = sc.nextLine();
                System.out.print("성별 입력 : ");
                int gender = sc.nextInt();
                System.out.print("닉네임 입력 : ");
                String  nickname = sc.next();
                OlumGollum olumGollum = new OlumGollum(user_mail, password, name, first_id_cardnum, phone_number, address, gender, nickname);
                return olumGollum;
            }
        }
        
        ```
        
- TEST RoomInfo
    - RoomInfoDao
        
        작성자 :이경섭
        
        ```java
        package 올룸골룸.Roominfo;
        
        import 올룸골룸.COMMON;
        
        import java.sql.*;
        import java.util.ArrayList;
        import java.util.Date;
        import java.util.List;
        
        public class RoominfoDao {
            Connection conn = null;
            Statement stmt =null; // create Statement 방식
            PreparedStatement pSmt = null; // Prepared Statement 방식
            ResultSet rs = null; // database 부터 결과를 받는 변수
        
            public List<RoomInfoVo> RoomInfoSelect() {
                List<RoomInfoVo> list = new ArrayList<>();
                try {
                    conn = COMMON.getConnection();
                    stmt = conn.createStatement();
                    String sql = "SELECT * FROM ROOMINFO";
                    rs = stmt.executeQuery(sql);
                    while (rs.next()) {
                        int house_num = rs.getInt("house_num");
                        String user_id = rs.getString("user_id");
                        String photo_url = rs.getString("photo_url");
                        String trade_method = rs.getString("trade_method");
                        int price = rs.getInt("price");
                        String area = rs.getString("area");
                        Date accept_date = rs.getDate("accept_date");
                        int household = rs.getInt("household");
                        String address = rs.getString("address");
                        String floor1 = rs.getString("floor1");
                        String remark = rs.getString("remark");
                        int phonenumber = rs.getInt("phonenumber");
                        Date regit_date = rs.getDate("regit_date");
                        String roomtitle = rs.getString("roomtitle");
                        int option_id = rs.getInt("option_id");
                        list.add(new RoomInfoVo(house_num, user_id, photo_url,trade_method, price, area, accept_date, household,address,floor1,remark,phonenumber,regit_date,roomtitle,option_id));
        
                    }
                    COMMON.close(rs);
                    COMMON.close(stmt);
                    COMMON.close(conn);
        
                } catch (Exception e){
                    e.printStackTrace();
                }
                return list;
            }
            public void RoomInfoprintf(List<RoomInfoVo> list){
                for (RoomInfoVo e : list)
                {
                    System.out.print(e.getHouse_num()+ " ");
                    System.out.print(e.getUser_id() + " ");
                    System.out.print(e.getPhoto_url() + " ");
                    System.out.print(e.getTrade_method() + " ");
                    System.out.print(e.getPrice()+ " ");
                    System.out.print(e.getArea()+ " ");
                    System.out.print(e.getAccept_date() + " ");
                    System.out.print(e.getHousehold() + " ");
                    System.out.print(e.getPhonenumber() + " ");
                    System.out.print(e.getRoomtitle() + " ");
                    System.out.println(e.getOption_id());
                }
        
            }
        }
        
        ```
        
    - RoomInfoMain
        
        작성자: 이경섭
        
        ```java
        package 올룸골룸.Roominfo;
        
        import java.util.List;
        import java.util.Scanner;
        
        public class RoomInfoMain {
            public static void main(String[] args) {
                Scanner sc = new Scanner(System.in);
                RoominfoDao dao = new RoominfoDao();
                while (true) {
                    System.out.println("=====================================================================");
                    System.out.print("메뉴 선택 :[0]EXITS [1]SELECT [2]INSERT [3]UPDATE [4]DELETE [5]QUIT : ");
                    int sel = sc.nextInt();
                    switch (sel) {
                        case 1:
                            List<RoomInfoVo> list = dao.RoomInfoSelect();
                            dao.RoomInfoprintf(list);
                            break;
                        default:
                            System.out.println("입력이 잘못 되었습니다.");
                    }
                    if (sel == 0 || sel == 5)
                    {
                        System.out.println("종료합니다.");
                        break;
                    }
                }
            }
        }
        
        ```
        
- TEST HouseOption
    - HouseOptionDao
        
        작성자 :
        
        ```java
        package OLUMGOLLUM.dao;
        
        import OLUMGOLLUM.Common.OlumgollumCommon;
        import OLUMGOLLUM.Vo.OlumgollumVo;
        
        import java.sql.*;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.Scanner;
        
        public class OlumgollumDao {
            Connection conn=null;
            Statement stmt=null; // create Statement 방식
            PreparedStatement pStmt=null; // Prepared Statment 방식
            ResultSet rs = null; // database 로 부터 결과를 받는 변수
            Scanner sc= new Scanner(System.in);
        
            public List<OlumgollumVo> OlumgollumSelect(){
                List<OlumgollumVo> list = new ArrayList<>();
                try{
                    conn=OlumgollumCommon.getConnection();
                    stmt=conn.createStatement();
                    String sql= "SELECT * FROM house_option";
                    rs= stmt.executeQuery(sql);
        
                    while (rs.next()){
                        int option_id= rs.getInt("option_id");
                        int refrigerator= rs.getInt("refrigerator");
                        int washing_machine= rs.getInt("washing_machine");
                        int sink= rs.getInt("sink");
                        int gas_stove= rs.getInt("gas_stove");
                        int microwave= rs.getInt("microwave");
                        int air_conditioner= rs.getInt("air_conditioner");
                        int parkinglot= rs.getInt("parkinglot");
                        list.add(new OlumgollumVo(option_id,refrigerator,washing_machine,sink,gas_stove,microwave,air_conditioner,parkinglot));
                    }
                    OlumgollumCommon.close(rs);
                    OlumgollumCommon.close(pStmt);
                    OlumgollumCommon.close(conn);
                }catch (Exception e){
                    e.printStackTrace();
                }
                return list;
            }
        
            public void house_optionInsert(){
                System.out.println("가지고 있는 옵션 표시");
                System.out.println("옵션 아이디");
                int opt=sc.nextInt();
                System.out.println("냉장고 [0] 있다 [1] 없다");
                int ref=sc.nextInt();
                System.out.println("세탁기 [0] 있다 [1] 없다");
                int was=sc.nextInt();
                System.out.println("싱크대 [0] 있다 [1] 없다");
                int sin=sc.nextInt();
                System.out.println("가스레인지 [0] 있다 [1] 없다");
                int gas=sc.nextInt();
                System.out.println("전자레인지 [0] 있다 [1] 없다");
                int mic=sc.nextInt();
                System.out.println("에어컨 [0] 있다 [1] 없다");
                int air=sc.nextInt();
                System.out.println("주차장 [0] 있다 [1] 없다");
                int par=sc.nextInt();
                String sql="INSERT INTO house_option(OPTION_ID, REFRIGERATOR, WASHING_MACHINE, SINK, GAS_STOVE, MICROWAVE, AIR_CONDITIONER, PARKINGLOT) VALUES (?,?,?,?,?,?,?,?)";
        
                try{
                    conn = OlumgollumCommon.getConnection();
                    pStmt=conn.prepareStatement(sql);
                    pStmt.setInt(1,opt);
                    pStmt.setInt(2,ref);
                    pStmt.setInt(3,was);
                    pStmt.setInt(4,sin);
                    pStmt.setInt(5,gas);
                    pStmt.setInt(6,mic);
                    pStmt.setInt(7,air);
                    pStmt.setInt(8,par);
                    int rst = pStmt.executeUpdate();
                    System.out.println("rst : "+ rst);
                }catch (SQLIntegrityConstraintViolationException e) {
                    System.out.println("고유 제약 조건 위배: 이미 존재하는 값입니다.");}
                catch (Exception e){
                    e.printStackTrace();
                }
                OlumgollumCommon.close(pStmt);
                OlumgollumCommon.close(conn);
            }
            public void OlumgollumSelectPrn(List<OlumgollumVo> list){
                for(OlumgollumVo e : list){
                    System.out.print(e.getOption_id()+" ");
                    System.out.print(e.getRefrigerator()+" ");
                    System.out.print(e.getWashing_machine()+" ");
                    System.out.print(e.getSink()+" ");
                    System.out.print(e.getGas_stove()+" ");
                    System.out.print(e.getMicrowave()+" ");
                    System.out.print(e.getAir_conditioner()+" ");
                    System.out.print(e.getParkinglot()+" ");
                    System.out.println();
                }
            }
        }
        ```
        
    - HouseOptionMain
        
        작성자:
        
        ```java
        package OLUMGOLLUM;
        
        import OLUMGOLLUM.Vo.OlumgollumVo;
        import OLUMGOLLUM.dao.OlumgollumDao;
        
        import java.util.List;
        import java.util.Scanner;
        
        public class Main {
            public static void main(String[] args) {
                Scanner sc = new Scanner(System.in);
                OlumgollumDao dao=new OlumgollumDao();
                System.out.println("==================");
                System.out.println("대충 방등록 끝냈고 이제 옵션 설정: [1] 맞다 [2] 다른 방 조회 [3] 아님");
                int sel =sc.nextInt();
                switch (sel){
                    case 1:
                        dao.house_optionInsert();
                        break;
                    case 2:
                        List<OlumgollumVo> list = dao.OlumgollumSelect();
                        dao.OlumgollumSelectPrn(list);
                        break;
                    case 3:
                        break;
                    default:
                        break;
                }
            }
        }
        ```
        
- TEST MyPage
    - MyPageDao
        
        작성자 : 이경섭
        
        ```java
        package 올룸골룸.Mypage;
        
        import KH_JDBC.COMMON.Common;
        import 올룸골룸.COMMON;
        
        import java.sql.Connection;
        import java.sql.PreparedStatement;
        import java.sql.ResultSet;
        import java.sql.Statement;
        import java.util.ArrayList;
        import java.util.List;
        
        public class MypageDao {
            Connection conn = null;
            Statement stmt = null;
            PreparedStatement pSmt = null;
            ResultSet rs = null;
        
            public List<MypageVo> MypageSelect() {
                List<MypageVo> list = new ArrayList<>();
                try{
                    conn = COMMON.getConnection();
                    stmt = conn.createStatement();
                    String sql = "SELECT * FROM MY_PAGE";
                    rs = stmt.executeQuery(sql);
                    while (rs.next()){
                        String USER_MAIL = rs.getString("USER_MAIL");
                        Boolean LOVE_ROOM = rs.getBoolean("LOVE_ROOM");
                        String profile_photo = rs.getString("profile_photo");
                        list.add(new MypageVo(USER_MAIL,LOVE_ROOM,profile_photo));
                    }
                    COMMON.close(rs);
                    COMMON.close(stmt);
                    COMMON.close(conn);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return list;
            }
            public void Mypageprintf(List<MypageVo> list){
                for (MypageVo e : list) {
                    System.out.print(e.getUSER_MAIL()+ " ");
                    System.out.print(e.isLOVE_ROOM()+ " ");
                    System.out.println(e.getProfile_photo()+ " ");
        
                }
            }
        }
        
        ```
        
    - MyPageMain
        
        작성자: 이경섭
        
        ```java
        package 올룸골룸.Mypage;
        
        import java.util.Scanner;
        import java.util.List;
        
        public class MypageMain {
            public static void main(String[] args) {
                Scanner sc = new Scanner(System.in);
                MypageDao dao = new MypageDao();
                while (true){
                    System.out.println("=============================================================");
                    System.out.print("메뉴 선택 :[0]EXITS [1]SELECT [2]INSERT [3]UPDATE [4]DELETE [5]QUIT : ");
                    int sel = sc.nextInt();
                    switch (sel){
                        case 1:
                            List<MypageVo> list = dao.MypageSelect();
                            dao.Mypageprintf(list);
                            break;
                        default:
                            System.out.println("입력이 잘못 되었습니다.");
                    }
                    if(sel == 0 || sel == 5)
                    {
                        System.out.println("종료합니다.");
                        break;
                    }
                }
        
            }
        }
        
        ```
        
- TEST LoveRoom
    - LoveRoomDao
        
        작성자 :
        
        ```java
        package AllRoomGolRoom.dao;
        
        import AllRoomGolRoom.common.Common;
        import AllRoomGolRoom.vo.LoveRoomVo;
        
        import java.sql.Connection;
        import java.sql.PreparedStatement;
        import java.sql.ResultSet;
        import java.sql.Statement;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.Scanner;
        
        public class LoveRoomDao {
            Connection conn = null;
            Statement stmt = null; // create Statement 방식
            PreparedStatement pStmt = null; // Prepared Statement 방식
            ResultSet rs = null; // database로 부터 결과를 받는 변수
            Scanner sc = new Scanner(System.in);
        
            public List<LoveRoomVo> loveRoomSelect() {
                List<LoveRoomVo> list = new ArrayList<>();
                try {
                    conn = Common.getConnection();
                    stmt = conn.createStatement();
                    String sql = "SELECT * FROM LOVE_ROOM";
                    rs = stmt.executeQuery(sql);
        
                    while(rs.next()){
                        String roomtitle = rs.getString("ROOMTITLE");
                        String user_id = rs.getString("USER_ID");
                        int Shouse_num = rs.getInt("SHOUSE_NUM");
                        list.add(new LoveRoomVo(roomtitle, user_id, Shouse_num));
                    }
                    Common.close(rs);
                    Common.close(stmt);
                    Common.close(conn);
        
                }catch (Exception e) {
                    e.printStackTrace();
                }
                return list;
            }
            public void loveRoomSelectPrn(List<LoveRoomVo> list) {
                for (LoveRoomVo e : list) {
                    System.out.print(e.getRoomtitle() + " ");
                    System.out.print(e.getUser_id() + " ");
                    System.out.print(e.getShouse_num() + " ");
                    System.out.println();
                }
            }
        }
        
        ```
        
    - LoveRoomMain
        
        작성자:
        
        ```java
        package AllRoomGolRoom;
        
        import AllRoomGolRoom.dao.LoveRoomDao;
        import AllRoomGolRoom.vo.LoveRoomVo;
        
        import java.util.List;
        import java.util.Scanner;
        
        public class Jdbc_Main {
            public static void main(String[] args) {
                Scanner sc = new Scanner(System.in);
                LoveRoomDao dao = new LoveRoomDao();
        
                while (true) {
                    System.out.print("메뉴 선택 : [1]Select [2]Insert [3]Update [4]Delete [5]Quit : ");
                    int sel = sc.nextInt();
                    switch (sel) {
                        case 1:
                            List<LoveRoomVo> list = dao.loveRoomSelect();
                            dao.loveRoomSelectPrn(list);
                            break;
                        default:
                            System.out.println("메뉴를 잘못 선택했습니다.");
                    }
                }
        
            }
        }
        
        ```
        
- TEST AdminInfo
    - AdminInfoDao
        
        작성자 : 김세호
        
        ```java
        package asdf_jdbc.dao;
        
        import asdf_jdbc.common.Common;
        import asdf_jdbc.vo.AdminInfoVo;
        
        import java.sql.Connection;
        import java.sql.PreparedStatement;
        import java.sql.ResultSet;
        import java.sql.Statement;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.Scanner;
        
        public class AdminInfoDao {
            Connection conn = null;
            Statement stmt = null; // create Statement 방식
            PreparedStatement pStmt = null; // Prepared Statement 방식
            ResultSet rs = null; // database로 부터 결과를 받는 변수
        
            public List<AdminInfoVo> AdmSelect() {
                List<AdminInfoVo> list = new ArrayList<>();
                Scanner sc = new Scanner(System.in);
        
                try {
                    conn = Common.getConnection();
                    stmt = conn.createStatement();
                    String sql = "SELECT * FROM ADMIN_INFO";
                    rs = stmt.executeQuery(sql);
                    while (rs.next()) {
                        int ADM_NO = rs.getInt("ADM_NO");
                        String ADM_ID = rs.getString("ADM_ID");
                        String ADM_PW = rs.getString("ADM_PW");
                        list.add(new AdminInfoVo(ADM_NO, ADM_ID, ADM_PW));
                    }
                    Common.close(rs);
                    Common.close(stmt);
                    Common.close(conn);
        
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return list;
            }
            public void admSelectPrn(List<AdminInfoVo> list) {
                for (AdminInfoVo e : list) {
                    System.out.print(e.getADM_NO() + " ");
                    System.out.print(e.getADM_ID()+ " ");
                    System.out.println(e.getADM_PW() + " ");
                }
            }
        }
        
        ```
        
    - AdminInfoMain
        
        작성자: 김세호
        
        ```java
        package asdf_jdbc;
        
        import asdf_jdbc.dao.AdminInfoDao;
        import asdf_jdbc.vo.AdminInfoVo;
        
        import java.util.List;
        import java.util.Scanner;
        
        public class Jdbc_Main {
        
                public static void main(String[] args) {
                    Scanner sc = new Scanner(System.in);
                    AdminInfoDao dao = new AdminInfoDao();
        
                    while (true) {
                        System.out.print("메뉴 선택 : [1]Select [2]Insert [3]Update [4]Delete [5]Quit : ");
                        int sel = sc.nextInt();
                        switch (sel) {
                            case 1:
                                List<AdminInfoVo> list = dao.AdmSelect();
                                dao.admSelectPrn(list);
                                break;
                            default:
                                System.out.println("메뉴를 잘못 선택했습니다.");
                        }
                        if(sel == 5) break;
                    }
            }
        }
        
        ```
        

## COMMON

- Common
    
    작성자 : 이경섭,서용욱,김동환,김세호
    
    ```java
    package 올룸골룸;
    
    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.ResultSet;
    import java.sql.Statement;
    public class COMMON {
        final static String ORACLE_URL = "jdbc:oracle:thin:@localhost:1521:xe";
        final static String ORACLE_ID = "OLUMGOLLUM";
        final static String ORACLE_PW = "1234";
        final static String ORACLE_DRV = "oracle.jdbc.driver.OracleDriver";
        public static Connection getConnection() {
            Connection conn = null;
            try {
                //드라이브
                Class.forName(ORACLE_DRV);
                // URL, ID , PASSWORD를 보고 CONNECT
                conn = DriverManager.getConnection(ORACLE_URL, ORACLE_ID, ORACLE_PW);
                System.out.println("Connection 성공");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return conn;
        }
        public static void close(Connection conn) {
            try {
                if(conn != null && !conn.isClosed()) {
                    conn.close();
                    System.out.println("Connection 해제 성공");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //STATEMENT에 대한 CLOSE문
        public static void close(Statement stmt) {
            try {
                if(stmt != null && !stmt.isClosed()) {
                    stmt.close();
                    System.out.println("Statement 해제 성공");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //RESULTESET에 대한 CLOSE문
        public static void close(ResultSet rset) {
            try {
                if(rset != null && !rset.isClosed()) {
                    rset.close();
                    System.out.println("Result set 해제 성공");
                }
    
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    ```
    

## VO

- InfoVo
    
    작성자 : 김동환
    
    ```java
    package log.VO;
    
    public class Log_Vo {
        private String user_mail; // 유저 이메일
        private String password; //비밀번호
        private String name; // 이름
        private int first_id_cardnum; //생년월일
        private int phone_number; //
        private String address; // float 급여 정보이고 소수점 이하가 존재
        private int gender; // 성과급
        private String nickname;
    
        public String getUser_mail() {
            return user_mail;
        }
    
        public void setUser_mail(String user_mail) {
            this.user_mail = user_mail;
        }
    
        public String getPassword() {
            return password;
        }
    
        public void setPassword(String password) {
            this.password = password;
        }
    
        public String getName() {
            return name;
        }
    
        public void setName(String name) {
            this.name = name;
        }
    
        public int getFirst_id_cardnum() {
            return first_id_cardnum;
        }
    
        public void setFirst_id_cardnum(int first_id_cardnum) {
            this.first_id_cardnum = first_id_cardnum;
        }
    
        public int getPhone_number() {
            return phone_number;
        }
    
        public void setPhone_number(int phone_number) {
            this.phone_number = phone_number;
        }
    
        public String getAddress() {
            return address;
        }
    
        public void setAddress(String address) {
            this.address = address;
        }
    
        public int getGender() {
            return gender;
        }
    
        public void setGender(int gender) {
            this.gender = gender;
        }
    
        public String getNickname() {
            return nickname;
        }
    
        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
    
        public Log_Vo(String user_mail, String password, String name, int first_id_cardnum, int phone_number, String address, int gender, String nickname) {
            this.user_mail = user_mail;
            this.password = password;
            this.name = name;
            this.first_id_cardnum = first_id_cardnum;
            this.phone_number = phone_number;
            this.address = address;
            this.gender = gender;
            this.nickname = nickname;
    
        }
    }
    
    ```
    
- RoomInfoVo
    
    작성자 : 이경섭, 김세호
    
    ```java
    package 올룸골룸.Roominfo;
    
    import java.sql.Date;
    
    public class RoominfoVo {
        private int house_num;
        private String user_id;
        private String photo_url;
        private String trade_method;
        private int deposit;
        private int monthly;
        private int jeonsegeum;
        private int sale_price;
        private String area;
        private Date accept_date;
        private String address;
        private String floor1;
        private int phonenumber;
        private Date regit_date;
        private String remark;
    
        public RoominfoVo(int house_num, String user_id, String photo_url, String trade_method, int deposit, int monthly, int jeonsegeum, int sale_price, String area, Date accept_date, String address, String floor1, int phonenumber, Date regit_date, String remark) {
            this.house_num = house_num;
            this.user_id = user_id;
            this.photo_url = photo_url;
            this.trade_method = trade_method;
            this.deposit = deposit;
            this.monthly = monthly;
            this.jeonsegeum = jeonsegeum;
            this.sale_price = sale_price;
            this.area = area;
            this.accept_date = accept_date;
            this.address = address;
            this.floor1 = floor1;
            this.phonenumber = phonenumber;
            this.regit_date = regit_date;
            this.remark = remark;
        }
    
        public int getHouse_num() {
            return house_num;
        }
    
        public void setHouse_num(int house_num) {
            this.house_num = house_num;
        }
    
        public String getUser_id() {
            return user_id;
        }
    
        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }
    
        public String getPhoto_url() {
            return photo_url;
        }
    
        public void setPhoto_url(String photo_url) {
            this.photo_url = photo_url;
        }
    
        public String getTrade_method() {
            return trade_method;
        }
    
        public void setTrade_method(String trade_method) {
            this.trade_method = trade_method;
        }
    
        public int getDeposit() {
            return deposit;
        }
    
        public void setDeposit(int deposit) {
            this.deposit = deposit;
        }
    
        public int getMonthly() {
            return monthly;
        }
    
        public void setMonthly(int monthly) {
            this.monthly = monthly;
        }
    
        public int getJeonsegeum() {
            return jeonsegeum;
        }
    
        public void setJeonsegeum(int jeonsegeum) {
            this.jeonsegeum = jeonsegeum;
        }
    
        public int getsale_price() {
            return sale_price;
        }
    
        public void setsale_price(int sale_price) {
            sale_price = sale_price;
        }
    
        public String getArea() {
            return area;
        }
    
        public void setArea(String area) {
            this.area = area;
        }
    
        public Date getAccept_date() {
            return accept_date;
        }
    
        public void setAccept_date(Date accept_date) {
            this.accept_date = accept_date;
        }
    
        public String getAddress() {
            return address;
        }
    
        public void setAddress(String address) {
            this.address = address;
        }
    
        public String getFloor1() {
            return floor1;
        }
    
        public void setFloor1(String floor1) {
            this.floor1 = floor1;
        }
    
        public int getPhonenumber() {
            return phonenumber;
        }
    
        public void setPhonenumber(int phonenumber) {
            this.phonenumber = phonenumber;
        }
    
        public Date getRegit_date() {
            return regit_date;
        }
    
        public void setRegit_date(Date regit_date) {
            this.regit_date = regit_date;
        }
    
        public String getRemark() {
            return remark;
        }
    
        public void setRemark(String remark) {
            this.remark = remark;
        }
    }
    
    ```
    
- MyPageVo
    
    작성자 : 서용욱
    
    ```jsx
    package 올룸골룸.Mypage;
    
    public class MypageVo {
        private String USER_MAIL;
        private boolean LOVE_ROOM;
        private String profile_photo;
    
        public MypageVo(String USER_MAIL, boolean LOVE_ROOM, String profile_photo) {
            this.USER_MAIL = USER_MAIL;
            this.LOVE_ROOM = LOVE_ROOM;
            this.profile_photo = profile_photo;
        }
    
        public String getUSER_MAIL() {
            return USER_MAIL;
        }
    
        public boolean isLOVE_ROOM() {
            return LOVE_ROOM;
        }
    
        public String getProfile_photo() {
            return profile_photo;
        }
    
        public void setUSER_MAIL(String USER_MAIL) {
            this.USER_MAIL = USER_MAIL;
        }
    
        public void setLOVE_ROOM(boolean LOVE_ROOM) {
            this.LOVE_ROOM = LOVE_ROOM;
        }
    
        public void setProfile_photo(String profile_photo) {
            this.profile_photo = profile_photo;
        }
    }
    
    ```
    
- LoveRoomVo
    
    작성자 : 서용욱
    
    ```java
    package AllRoomGolRoom.vo;
    
    public class LoveRoomVo{
        private String user_id ;
        private int Shouse_num ;
    
        public LoveRoomVo(String user_id, int shouse_num) {
            this.user_id = user_id;
            this.Shouse_num = shouse_num;
        }
    
        public String getUser_id() {
            return user_id;
        }
    
        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }
    
        public int getShouse_num() {
            return Shouse_num;
        }
    
        public void setShouse_num(int shouse_num) {
            this.Shouse_num = shouse_num;
        }
    }
    
    ```
    
- AdminInfoVo
    
    작성자 : 김동환
    
    ```jsx
    package admin_info.vo;
    
    public class Admin_infoVo {
        private String ADM_ID;
        private String ADM_PW;
    
        public Admin_infoVo(String ADM_ID, String ADM_PW) {
            this.ADM_ID = ADM_ID;
            this.ADM_PW = ADM_PW;
        }
    
        public String getADM_ID() {
            return ADM_ID;
        }
    
        public void setADM_ID(String ADM_ID) {
            this.ADM_ID = ADM_ID;
        }
    
        public String getADM_PW() {
            return ADM_PW;
        }
    
        public void setADM_PW(String ADM_PW) {
            this.ADM_PW = ADM_PW;
        }
    }
    
    ```
    

## DAO

- InfoDao
    
    작성자 김동환
    
    ```java
    package log.dao;
    
    import log.Common.LogCommon;
    import log.VO.Log_Vo;
    
    import java.sql.*;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.Scanner;
    
    public class Log_Dao {
        Connection conn = null;
        Statement stmt = null; // create Statement 방식
        PreparedStatement pStmt = null; // Prepared Statment 방식
        ResultSet rs = null; // database 로 부터 결과를 받는 변수
        Scanner sc = new Scanner(System.in);
    
        String ID = null;
    
        public List<Log_Vo> logsel() {
            List<Log_Vo> list = new ArrayList<>();
            try {
                conn = LogCommon.getConnection();
                stmt = conn.createStatement();
                String sql = "SELECT * FROM INFO";
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    String user_mail = rs.getString("USER_MAIL");
                    String password = rs.getString("PASSWORD");
                    String name = rs.getString("NAME");
                    int first_id_cardnum = rs.getInt("FIRST_ID_CARDNUM");
                    int phone_number = rs.getInt("PHONE_NUMBER");
                    String address = rs.getString("ADDRESS");
                    int gender = rs.getInt("GENDER");
                    String nickname = rs.getString("NICKNAME");
                    list.add(new Log_Vo(user_mail, password, name, first_id_cardnum, phone_number, address, gender, nickname));
                }
                LogCommon.close(rs);
                LogCommon.close(pStmt);
                LogCommon.close(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
    
            return list;
        }
    
        public void login() {
            while (true) {
                System.out.println("아이디를 입력하세요");
                ID = sc.next();
                System.out.println("비밀번호를 입력하세요");
                String password = sc.next();
                String sql = "SELECT PASSWORD FROM INFO WHERE USER_MAIL= ? ";
                String pwd;
                try {
                    conn = LogCommon.getConnection();
                    pStmt = conn.prepareStatement(sql);
                    pStmt.setString(1, ID);
                    rs = pStmt.executeQuery(); // rs 객체에 쿼리 결과를 할당
    
                    if (rs.next()) {
                        pwd = rs.getString("PASSWORD");
                        if (pwd.equals(password)) {
                            System.out.println("로그인 완료");
                            break;
                        } else {
                            System.out.println("비밀번호가 틀렸습니다.");
                        }
                    } else {
                        System.out.println("아이디를 잘못 입력했습니다.");
                    }
    
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    LogCommon.close(rs);
                    LogCommon.close(pStmt);
                    LogCommon.close(conn);
                }
            }
        }
    
        public void logInsert() {
            System.out.println("아이디를 입력 하세요");
            String user_mail = sc.next();
            System.out.println("비밀번호를 입력하세요");
            String password = sc.next();
            System.out.println("이름을 입력해주세요");
            String name = sc.next();
            System.out.println("주민등록번호 앞자리");
            int first_id_cardnum = sc.nextInt();
            System.out.println("전화번호 입력해주세요");
            int phone_number = sc.nextInt();
            sc.nextLine();
            System.out.println("주소를 입력해 주세요");
            String address = sc.nextLine();
            System.out.println("성별을 입력해주세요 남자[1] 여자면[2]");
            int gender = sc.nextInt();
            sc.nextLine();
            System.out.println("닉네임을 입력해주세요");
            String nickname = sc.next();
            String sql = "INSERT INTO INFO(USER_MAIL, PASSWORD, NAME, FIRST_ID_CARDNUM, PHONE_NUMBER, ADDRESS, GENDER, NICKNAME) VALUES (?,?,?,?,?,?,?,?)";
    
            try {
                conn = LogCommon.getConnection();
                pStmt = conn.prepareStatement(sql);
                pStmt.setString(1, user_mail);
                pStmt.setString(2, password);
                pStmt.setString(3, name);
                pStmt.setInt(4, first_id_cardnum);
                pStmt.setInt(5, phone_number);
                pStmt.setString(6, address);
                pStmt.setInt(7, gender);
                pStmt.setString(8, nickname);
                int rst = pStmt.executeUpdate();
                System.out.println("rst : " + rst);
            } catch (Exception e) {
                e.printStackTrace();
            }
            LogCommon.close(pStmt);
            LogCommon.close(conn);
        }
            public void logSelectPrn (List < Log_Vo > list) {
                for (Log_Vo e : list) {
                    System.out.print(e.getUser_mail() + " ");
                    System.out.print(e.getPassword() + " ");
                    System.out.print(e.getName() + " ");
                    System.out.print(e.getFirst_id_cardnum() + " ");
                    System.out.print(e.getPhone_number() + " ");
                    System.out.print(e.getAddress() + " ");
                    System.out.print(e.getGender() + " ");
                    System.out.print(e.getNickname() + " ");
                    System.out.println();
                }
            }
    }
    ```
    
- RoomInfoDao
    
    작성자 : 이경섭
    
    ```java
    package 올룸골룸.Roominfo;
    
    import 올룸골룸.Common.COMMON;
    
    import java.sql.*;
    import java.text.ParseException;
    import java.text.SimpleDateFormat;
    import java.util.Scanner;
    
    public class RoominfoDao {
        Connection conn = null;
        Statement stmt = null; // create Statement 방식
        PreparedStatement psmt = null; // Prepared Statement 방식
        ResultSet rs = null; // database 부터 결과를 받는 변수
        Scanner sc = new Scanner(System.in);
    
        public RoominfoVo RoomInputFunc() throws ParseException {
            //초기값 설정
            RoominfoVo rvo = new RoominfoVo(11,"can3487@naver.com",null,null,0,0,0,0,null,null,null,null,0,null,null);
            //날짜 입력받기 위한 SimpleDateFormat 생성.
            // RoomOlum_Page 화면
            System.out.println("======================================================================");
            System.out.println("|                           RoomOlum_Page                            |");
            System.out.println("/-------------------\\                                               |");
            System.out.println("|                   |                                                |");
            System.out.println("|                   |                                                |");
            System.out.println("|       사  진       |                                                |");
            System.out.println("|                   |                                                |");
            System.out.println("|                   |                                                |");
            System.out.println("\\-------------------/                                               |");
            System.out.print("| url: ");
            rvo.setPhoto_url(sc.nextLine());
            System.out.println("|                                                                    |");
            while(true)
            {
                System.out.print("| 거래 방식(매매,전세,월세): ");
                rvo.setTrade_method(sc.nextLine());
                System.out.println("|                                                                    |");
                switch(rvo.getTrade_method()) {
                    case "매매":
                        System.out.print("| 매매가(만원): ");
                        rvo.setsale_price(sc.nextInt());
                        sc.nextLine();
                        System.out.println("|                                                                    |");
                        break;
                    case "전세":
                        System.out.print("| 전세금(만원): ");
                        rvo.setJeonsegeum(sc.nextInt());
                        sc.nextLine();
                        System.out.println("|                                                                    |");
                        break;
                    case "월세":
                        System.out.print("| 보증금(만원): ");
                        rvo.setDeposit(sc.nextInt());
                        sc.nextLine();
                        System.out.println("|                                                                    |");
                        System.out.print("| 월세(만원): ");
                        rvo.setMonthly(sc.nextInt());
                        sc.nextLine();
                        System.out.println("|                                                                    |");
                        break;
                    default:
                        System.out.print("다시 입력해주세요.");
                        continue;
                }
                break;
            }
            System.out.print("| 평수: ");
            rvo.setArea(sc.nextLine());
            System.out.println("|                                                                    |");
            System.out.print("| 사용 승인일(YYYY-MM-DD): ");
            rvo.setAccept_date(java.sql.Date.valueOf(sc.nextLine()));
            System.out.println("|                                                                    |");
            System.out.print("| 주소: ");
            rvo.setAddress(sc.nextLine());
            System.out.println("|                                                                    |");
            System.out.print("| 층수: ");
            rvo.setFloor1(sc.nextLine());
            System.out.println("|                                                                    |");
            System.out.print("| 집주인 전화번호(-빼고): ");
            rvo.setPhonenumber(sc.nextInt());
            sc.nextLine();
            System.out.println("|                                                                    |");
            System.out.print("| 등록일(YYYY-MM-DD): ");
            rvo.setRegit_date(java.sql.Date.valueOf(sc.nextLine()));
            System.out.println("|                                                                    |");
            System.out.print("| 비고(옵션): ");
            rvo.setRemark(sc.nextLine());
            System.out.println("|                                                                    |");
            System.out.println("======================================================================");
    
            return rvo;
        }
    
        public void RoomInputPrF(RoominfoVo rvo) {
            //날짜 출력을 위한 SimpleDateFormat 생성.(YYYY-MM-DD 형식 지정)
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
            System.out.println("매물번호: " + rvo.getHouse_num() + " 유저ID: " + rvo.getUser_id() + " 사진URL: " + rvo.getPhoto_url() + " 거래 방식: " + rvo.getTrade_method());
            System.out.println(" 평수: " + rvo.getArea() + " 사용승인일: " + date.format(rvo.getAccept_date()) + "주소: " + rvo.getAddress() + " 층수: " + rvo.getAddress());
            System.out.println(" 집주인 전화번호: " + rvo.getPhonenumber() + " 등록일: " + date.format(rvo.getRegit_date()) + " 비고: " + rvo.getRemark());
        }
    
        public void RoomInsert(RoominfoVo rvo) {
            String query = "INSERT INTO RoomInfo VALUES(SEQ_ROOM.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            try {
                conn = COMMON.getConnection();
                psmt = conn.prepareStatement(query);
                psmt.setString(1, rvo.getUser_id());
                psmt.setString(2, rvo.getPhoto_url());
                psmt.setString(3, rvo.getTrade_method());
                psmt.setInt(4, rvo.getDeposit());
                psmt.setInt(5, rvo.getMonthly());
                psmt.setInt(6, rvo.getJeonsegeum());
                psmt.setInt(7, rvo.getsale_price());
                psmt.setString(8, rvo.getArea());
                psmt.setDate(9, rvo.getAccept_date());
                psmt.setString(10, rvo.getAddress());
                psmt.setString(11, rvo.getFloor1());
                psmt.setInt(12, rvo.getPhonenumber());
                psmt.setDate(13, rvo.getRegit_date());
                psmt.setString(14, rvo.getRemark());
                int rst = psmt.executeUpdate();
                System.out.print("INSERT 완료 /rst : " + rst);
            } catch (Exception e) {
                e.printStackTrace();
            }
            COMMON.close(psmt);
            COMMON.close(conn);
        }
    }
    
    ```
    
- RoomSearchDao
    
    작성자 : 김세호
    
    ```java
    package asdf_jdbc.dao;
    
    import asdf_jdbc.common.Common;
    import asdf_jdbc.vo.RoomInfoVo;
    
    import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.Statement;
    import java.util.ArrayList;
    import java.util.Date;
    import java.util.List;
    import java.util.Scanner;
    
    public class RoomInfoDao {
        Connection conn = null;
        Statement stmt = null; // create Statement 방식
        PreparedStatement pStmt = null; // Prepared Statement 방식
        ResultSet rs = null; // database로 부터 결과를 받는 변수
        Scanner sc = new Scanner(System.in);
        // ROOMINFO 테이블 검색 후 리스트 리턴 함수
        public List<RoomInfoVo> roomSelect() {
            List<RoomInfoVo> list = new ArrayList<>();
            try {
                conn = Common.getConnection();
                stmt = conn.createStatement();
                String sql = "SELECT * FROM ROOMINFO";
                rs = stmt.executeQuery(sql);
    
                while (rs.next()) {
                    int house_num = rs.getInt("HOUSE_NUM");
                    String user_id = rs.getString("USER_ID");
                    String photo_url = rs.getString("PHOTO_URL");
                    String trade_method = rs.getString("TRADE_METHOD");
                    int deposit = rs.getInt("DEPOSIT");
                    int monthly = rs.getInt("MONTHLY");
                    int jeonsegeum = rs.getInt("JEONSEGEUM");
                    int Sale_price = rs.getInt("SALE_PRICE");
                    String area = rs.getString("AREA");
                    Date accept_date = rs.getDate("ACCEPT_DATE");
                    String address = rs.getString("ADDRESS");
                    String floor1 = rs.getString("FLOOR1");
                    int phonenumber = rs.getInt("PHONENUMBER");
                    Date regit_date = rs.getDate("REGIT_DATE");
                    String remark = rs.getString("REMARK");
    
                    list.add(new RoomInfoVo(house_num, user_id, photo_url, trade_method, deposit, monthly, jeonsegeum,
                            Sale_price, area, accept_date,address, floor1, phonenumber, regit_date, remark));
                }
                Common.close(rs);
                Common.close(stmt);
                Common.close(conn);
    
            } catch (Exception e) {
                e.printStackTrace();
            }
            return list;
        }
        // 지역별 검색 함수
        public List<RoomInfoVo> adressSearchSelect() {
            List<RoomInfoVo> list = new ArrayList<>();
            System.out.print("조회 할 지역을 입력하세요 : ");
            String add = sc.next();
            String sql = "SELECT * FROM ROOMINFO WHERE address like ?";
    
            try {
                conn = Common.getConnection();
                pStmt = conn.prepareStatement(sql);
                pStmt.setString(1, "%" + add + "%");
                ResultSet rs = pStmt.executeQuery();
                while (rs.next()) {
                    int house_num = rs.getInt("HOUSE_NUM");
                    String user_id = rs.getString("USER_ID");
                    String photo_url = rs.getString("PHOTO_URL");
                    String trade_method = rs.getString("TRADE_METHOD");
                    int deposit = rs.getInt("DEPOSIT");
                    int monthly = rs.getInt("MONTHLY");
                    int jeonsegeum = rs.getInt("JEONSEGEUM");
                    int Sale_price = rs.getInt("SALE_PRICE");
                    String area = rs.getString("AREA");
                    Date accept_date = rs.getDate("ACCEPT_DATE");
                    String address = rs.getString("ADDRESS");
                    String floor1 = rs.getString("FLOOR1");
                    int phonenumber = rs.getInt("PHONENUMBER");
                    Date regit_date = rs.getDate("REGIT_DATE");
                    String remark = rs.getString("REMARK");
    
                    list.add(new RoomInfoVo(house_num, user_id, photo_url, trade_method, deposit, monthly, jeonsegeum,
                            Sale_price, area, accept_date,address, floor1, phonenumber, regit_date, remark));
                }
                Common.close(pStmt);
                Common.close(conn);
    
            } catch (Exception e) {
                e.printStackTrace();
            }
            return list;
        }
        //거래방식 검색함수
        public List<RoomInfoVo> tradeSelect(List<RoomInfoVo> list){
            List<RoomInfoVo> tradeList = new ArrayList<>();
            try {
                while(true) {
                    System.out.print("거래방식을 입력하세요 : ");
                    String trade = sc.next();
                    for (RoomInfoVo e : list)
                    {
                        if (e.getTrade_method().equals(trade)) {
                            tradeList.add(e);
                        }
                    }
                        if(tradeList.size() == 0){
                            System.out.println("다시 입력해주세요.");
                            continue;
                        }
                    break;
                }
            }catch (Exception e){
                e.printStackTrace();
            }return tradeList;
        }
        //평수 검색 함수
        public List<RoomInfoVo> areaTradeSelect(List<RoomInfoVo> tradeList){
            List<RoomInfoVo> areaTradeList = new ArrayList<>();
            try {
                while(true) {
                    System.out.print("평수를 입력하세요 : ");
                    int area = sc.nextInt();
                    for (RoomInfoVo e : tradeList)
                    {
                        if (Integer.parseInt(e.getArea()) >= area) {
                            areaTradeList.add(e);
                        }
                    }
                    if(areaTradeList.size() <= 0){
                        System.out.println("다시 입력해주세요.");
                        continue;
                    }
                    break;
                }
            }catch (Exception e){
                e.printStackTrace();
            }return areaTradeList;
        }
    
        //층 수 검색 함수
        public List<RoomInfoVo> floorTradeSelect(List<RoomInfoVo> tradeList) {
            List<RoomInfoVo> floorTradeList = new ArrayList<>();
            try {
                while (true) {
                    System.out.print("층을 입력하세요 : ");
                    String flo = sc.next();
                    for (RoomInfoVo e : tradeList) {
                        int index = flo.indexOf("층");
                        if (index != -1) {
                            String extract = flo.substring(0, index);
                            int index2 = e.getFloor1().indexOf("층");
                            if (index2 != -1) {
                                String extract2 = e.getFloor1().substring(0, index2);
                                if (flo.equals("루프탑") || flo.equals("반지층") || Integer.parseInt(extract) <= Integer.parseInt(extract2)) {
                                    floorTradeList.add(e);
                                }
                            }
                        }
                    }
                    if (floorTradeList.size() <= 0) {
                        System.out.println("다시 입력해주세요.");
                        continue;
                    }
                    break;
                }
            } catch (Exception ee) {
                ee.printStackTrace();
            }
            return floorTradeList;
        }
        // 리스트 출력 함수
        public void roomSelectPrn(List<RoomInfoVo> list) {
            for (RoomInfoVo e : list) {
                System.out.print(e.getHouse_num() + " ");
                System.out.print(e.getUser_id()+ " ");
                System.out.print(e.getPhoto_url() + " ");
                System.out.print(e.getTrade_method() + " ");
                System.out.print(e.getDeposit() + " ");
                System.out.print(e.getMonthly() + " ");
                System.out.print(e.getJeonsegeum() + " ");
                System.out.print(e.getSale_price() + " ");
                System.out.print(e.getArea() + " ");
                System.out.print(e.getAccept_date() + " ");
                System.out.print(e.getAddress() + " ");
                System.out.print(e.getFloor1() + " ");
                System.out.print(e.getPhonenumber() + " ");
                System.out.print(e.getRegit_date() + " ");
                System.out.println(e.getRemark() + " ");
    //            System.out.println("= ".repeat(30));
            }
        }
    }
    
    ```
    
- MyPageDao
    
    ```java
    package com.kh.Olumgollum_Project.MyPage;
    
    import java.sql.*;
    
    import com.kh.Olumgollum_Project.AdminInfo.AdminInfoVo;
    import com.kh.Olumgollum_Project.COMMON.Common;
    import com.kh.Olumgollum_Project.LoginInfo.InfoUpdateVo;
    import com.kh.Olumgollum_Project.LoginInfo.LoginInfoVo;
    import com.kh.Olumgollum_Project.LoveRoom.LoveRoomVo;
    import com.kh.Olumgollum_Project.RoomInfo.RoomInfoVo;
    
    import java.sql.Date;
    import java.util.*;
    
    public class MyPageDao {
        public static final String black = "\u001B[30m";
        public static final String red = "\u001B[31m";
        public static final String green = "\u001B[32m";
        public static final String yellow = "\u001B[33m";
        public static final String blue = "\u001B[34m";
        public static final String purple = "\u001B[35m";
        public static final String cyan = "\u001B[36;m";
        public static final String white = "\u001B[37m";
        public static final String exit = "\u001B[0m";
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement pSmt = null;
        ResultSet rs = null;
        Scanner sc = new Scanner(System.in);
    
        // 삭제할 방 번호 저장
        // 개인정보 수정 비밀번호 전화번호 주소 닉네임
        public void Userupdate(List<String> id_pw) {
            System.out.print(blue +
                    "|======================================================================|\n" +
    
                    "|       \uD83D\uDD2E      _____  _____  ____   _____  _____  _____        \uD83D\uDD2E     |\n" +
                    "|            \uD83D\uDD2E|  |  ||  _  ||    \\ |  _  ||_   _||   __|              |\n" +
                    "|              |  |  ||   __||  |  ||     |  | |  |   __|      \uD83D\uDD2E      |\n" +
                    "|  \uD83D\uDD2E          |_____||__|   |____/ |__|__|  |_|  |_____|              |\n" +
                    "|======================================================================|\n" +
                    "| \uD83C\uDF26\uFE0F     ------------------              ------------------            |\n" +
                    "|        |[1]   비밀번호    |     \uD83C\uDF38      |[2]   전화번호    |            |\n" +
                    "|        ------------------              ------------------            |\n" +
                    "|        ------------------              ------------------            |\n" +
                    "| \uD83C\uDF38     |[3]     주소     |   \uD83C\uDF26\uFE0F        |[4]   닉네임      | \uD83C\uDF38        |\n" +
                    "|        ------------------              ------------------            |\n" +
                    "|        ------------------                                            |\n" +
                    "|        |[5]    나가기    |         |￣￣￣￣￣￣￣|        ☁\uFE0F   ⭐      |\n" +
                    "|        ------------------         | 뭐 바꿀래?  |            \uD83C\uDF38\uD83C\uDF38     |\n" +
                    "|                   ☁\uFE0F              |＿＿＿＿＿＿＿|    ☁\uFE0F     \uD83C\uDF38\uD83C\uDF38\uD83C\uDF38   |\n" +
                    "|                                   (\\__/) ||              \uD83C\uDF38\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38   |\n" +
                    "|                                   (•ㅅ•).||                  \uD83C\uDF38      |\n" +
                    "|       ☁\uFE0F          \uD83C\uDF26\uFE0F              / . . . .づ                        |\n" +
                    "========================================================================\n" + exit +
                    yellow + "     뭐 바꿀래? : " + exit);
            int up = sc.nextInt();
            System.out.println(blue + "========================================================================" + exit);
    
            String sql = null;
            switch (up) {
                case 1:
                    System.out.print("   수정하고자 하는 비밀번호 입력 : ");
                    String pwd = sc.next();
                    sql = "UPDATE INFO SET PASSWORD = ? WHERE USER_MAIL=?";
                    try {
                        conn = Common.getConnection();
                        pSmt = conn.prepareStatement(sql);
                        pSmt.setString(1, pwd);
                        pSmt.setString(2, id_pw.get(0));
                        pSmt.executeUpdate();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        Common.close(pSmt);
                        Common.close(conn);
                    }
                    break;
                case 2:
                    System.out.print("   수정하고자 하는 전화번호 입력 :");
                    String pn = sc.next();
                    sql = "UPDATE INFO SET PHONE_NUMBER = ? WHERE USER_MAIL=?";
                    try {
                        conn = Common.getConnection();
                        pSmt = conn.prepareStatement(sql);
                        pSmt.setString(1, pn);
                        pSmt.setString(2, id_pw.get(0));
                        pSmt.executeUpdate();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        Common.close(pSmt);
                        Common.close(conn);
                    }
                    break;
                case 3:
                    System.out.println("   수정하고자 하는 주소 입력 :");
                    String ad = sc.nextLine();
                    sql = "UPDATE INFO SET ADDRESS = ? WHERE USER_MAIL=?";
                    try {
                        conn = Common.getConnection();
                        pSmt = conn.prepareStatement(sql);
                        pSmt.setString(1, ad);
                        pSmt.setString(2, id_pw.get(0));
                        pSmt.executeUpdate();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        Common.close(pSmt);
                        Common.close(conn);
                    }
                    break;
                case 4:
                    System.out.println("   수정하고자 하는 닉네임 입력 :");
                    String ni = sc.next();
                    sql = "UPDATE INFO SET NICKNAME = ? WHERE USER_MAIL=?";
                    try {
                        conn = Common.getConnection();
                        pSmt = conn.prepareStatement(sql);
                        pSmt.setString(1, ni);
                        pSmt.setString(2, id_pw.get(0));
                        pSmt.executeUpdate();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        Common.close(pSmt);
                        Common.close(conn);
                    }
                    break;
                case 5:
                    System.exit(0);
            }
        }
    
        //===========================================================================================================================================================================================================
        public List<RoomInfoVo> Love_list(List<String> id_pw) { // 찜한방 목록화
            List<RoomInfoVo> love = new ArrayList<>();
            try {
                conn = Common.getConnection();
                stmt = conn.createStatement();
                String sql = "SELECT rm.house_num,rm.user_id,rm.photo_url,rm.trade_method,rm.deposit,rm.MONTHLY,rm.jeonsegeum,rm.address, rm.area, rm.sale_price " +
                        "FROM ROOMINFO rm JOIN LOVE_ROOM lr " +
                        "ON rm.house_num = lr.shouse_num WHERE lr.user_id = '" + id_pw.get(0) + "'";
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    //내가 찜한 방에 대한 정보들
                    love.add(new RoomInfoVo(rs.getInt("house_num"), rs.getString("user_id"), rs.getString("photo_url"), rs.getString("trade_method"),
                            rs.getInt("deposit"), rs.getInt("MONTHLY"), rs.getInt("jeonsegeum"),
                            rs.getInt("sale_price"), rs.getString("area"), null, rs.getString("address"), null,
                            0, null, null));
                }
                Common.close(rs);
                Common.close(stmt);
                Common.close(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return love;
        }
    
        //==================================================================================================================================================================================================================
        //db에서 찜삭제함수
        public void Love_delete(List<String> id_pw) { // 찜한방 목록 Delete
            try {
                // love 내가 찜한 방의 정보들
                conn = Common.getConnection();
                stmt = conn.createStatement();
                System.out.print("   삭제 할 방 번호를 입력해주세요 : ");
                int num = sc.nextInt();
                // 찜한 방 목록에서 확인 후 삭제
                String sql = "DELETE FROM LOVE_ROOM WHERE USER_ID = '" + id_pw.get(0) + "'" + "AND SHOUSE_NUM = '" + num + "'";
                rs = stmt.executeQuery(sql);
                // 방목록에서 원하는방 삭제
                Common.close(rs);
                Common.close(stmt);
                Common.close(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    
    //==========================================================================================================================================================================
    
        public List<RoomInfoVo> olume_list(List<String> id_pw) { // 올룸 목록
    
            List<RoomInfoVo> olume = new ArrayList<>();
            try {
                conn = Common.getConnection();
                stmt = conn.createStatement();
                String sql = "SELECT address, deposit, area, trade_method, photo_url, MONTHLY, jeonsegeum, sale_price " +
                        "FROM ROOMINFO WHERE USER_ID = '" + id_pw.get(0) + "'";
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    olume.add(new RoomInfoVo(0, null, rs.getString("photo_url"), rs.getString("trade_method"),
                            rs.getInt("deposit"), rs.getInt("monthly"), rs.getInt("jeonsegeum"),
                            rs.getInt("sale_price"), rs.getString("area"), null, rs.getString("address"), null,
                            0, null, null));
                }
                Common.close(rs);
                Common.close(stmt);
                Common.close(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return olume;
        }
    
        //===========================================================================================================================================================================
        // 개인정보 수정 비밀번호 전화번호 주소 닉네임
        public void print(List<RoomInfoVo> list) {
            for (RoomInfoVo e : list)
                System.out.println(e.getUser_id());
        }
    
        //============================================================================================================================
        //올룸 수정 함수
        // 업데이트
        public void RoomUpdate() {
            System.out.print("   수정하고 싶은 정보 [1] 방 사진 [2] 거래방식(가격) [3] 전화번호 [4] 비고 [5] 종료");
            int up = sc.nextInt();
            String sql = null;
            int honum;
            switch (up) {
                case 1:
                    System.out.print("   바꾸고자 하는 매물번호 : ");
                    honum = sc.nextInt();
                    System.out.println("수정 사진 등록");
                    String url = sc.next();
                    sql = "UPDATE ROOMINFO SET photo_url = ? WHERE house_num=?";
                    try {
                        conn = Common.getConnection();
                        pSmt = conn.prepareStatement(sql);
                        pSmt.setString(1, url);
                        pSmt.setInt(2, honum);
                        pSmt.executeUpdate();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        Common.close(pSmt);
                        Common.close(conn);
                    }
                    break;
                case 2:
                    System.out.print("   바꾸고자 하는 매물번호  :");
                    honum = sc.nextInt();
                    System.out.println("바꾸고 싶은 거래방식 선택 [1] 매매 [2] 전세 [3] 월세 [4] 종료");
                    int tr = sc.nextInt();
                    if (tr == 4) {
                        break;
                    }
                    sql = "UPDATE ROOMINFO SET deposit = NULL, monthly = NULL, jeonsegeum = NULL , Sale_price = NULL WHERE house_num =?";
                    try {
                        conn = Common.getConnection();
                        pSmt = conn.prepareStatement(sql);
                        pSmt.setInt(1, honum);
                        pSmt.executeUpdate();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        Common.close(pSmt);
                        Common.close(conn);
                    }
                    switch (tr) {
                        case 1:
                            System.out.print("  매매 가격을 알려주세요");
                            int sal = sc.nextInt();
                            sql = "UPDATE ROOMINFO SET trade_method = ?,Sale_price=? WHERE house_num=?";
                            try {
                                conn = Common.getConnection();
                                pSmt = conn.prepareStatement(sql);
                                pSmt.setString(1, "매매");
                                pSmt.setInt(2, sal);
                                pSmt.setInt(3, honum);
                                pSmt.executeUpdate();
                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                Common.close(pSmt);
                                Common.close(conn);
                            }
                            break;
                        case 2:
                            System.out.print("  전세 가격을 알려주세요");
                            int jeo = sc.nextInt();
                            sql = "UPDATE ROOMINFO SET trade_method=?,jeonsegeum=? WHERE house_num=?";
                            try {
                                conn = Common.getConnection();
                                pSmt = conn.prepareStatement(sql);
                                pSmt.setString(1, "전세");
                                pSmt.setInt(2, jeo);
                                pSmt.setInt(3, honum);
                                pSmt.executeUpdate();
                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                Common.close(pSmt);
                                Common.close(conn);
                            }
                            break;
                        case 3:
                            System.out.print("   보증금 가격 : ");
                            int dep = sc.nextInt();
                            System.out.print("   월세 가격 : ");
                            int mon = sc.nextInt();
                            sql = "UPDATE ROOMINFO SET trade_method=?,deposit=?,monthly=? WHERE house_num=?";
                            try {
                                conn = Common.getConnection();
                                pSmt = conn.prepareStatement(sql);
                                pSmt.setString(1, "월세");
                                pSmt.setInt(2, dep);
                                pSmt.setInt(3, mon);
                                pSmt.setInt(4, honum);
                                pSmt.executeUpdate();
                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                Common.close(pSmt);
                                Common.close(conn);
                            }
                            break;
                    }
                    break;
                case 3:
                    System.out.print("   바꾸고자 하는 매물번호  :");
                    honum = sc.nextInt();
                    System.out.print("   수정 하고자 하는 전화번호  :");
                    int phnum = sc.nextInt();
                    sql = "UPDATE ROOMINFO SET phonenumber = ? WHERE house_num=?";
                    try {
                        conn = Common.getConnection();
                        pSmt = conn.prepareStatement(sql);
                        pSmt.setInt(1, phnum);
                        pSmt.setInt(2, honum);
                        pSmt.executeUpdate();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        Common.close(pSmt);
                        Common.close(conn);
                    }
                    break;
                case 4:
                    System.out.print("   바꾸고자 하는 매물번호  :");
                    honum = sc.nextInt();
                    sc.nextLine();
                    System.out.print("   수정 할 비고  :");
                    String rem = sc.nextLine();
                    sql = "UPDATE ROOMINFO SET remark = ? WHERE house_num=?";
                    try {
                        conn = Common.getConnection();
                        pSmt = conn.prepareStatement(sql);
                        pSmt.setString(1, rem);
                        pSmt.setInt(2, honum);
                        pSmt.executeUpdate();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        Common.close(pSmt);
                        Common.close(conn);
                    }
                    break;
                case 5:
                    System.exit(0);
            }
        }
    
        //올룸 삭제 함수
        // 방삭제
        public void RoomDelete() {
            System.out.print("   삭제 할 매물번호를 입력하세요 : ");
            int num1 = sc.nextInt();
            String sql = "DELETE FROM LOVE_ROOM WHERE SHOUSE_NUM = ?";
            try {
                conn = Common.getConnection();
                pSmt = conn.prepareStatement(sql);
                pSmt.setInt(1, num1);
                pSmt.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Common.close(pSmt);
                Common.close(conn);
            }
        }
    
        //======================================================== 회원 탈퇴=======================================================
        public void UserDelete(List<String> id_pw) {
            System.out.println(red +
                    "=====================================\n" +
                    "   ·▄▄▄▄  ▄▄▄ .▄▄▌  ▄▄▄ .▄▄▄▄▄▄▄▄ .\n" +
                    "   ██▪ ██ ▀▄.▀·██•  ▀▄.▀·•██  ▀▄.▀·\n" +
                    "   ▐█· ▐█▌▐▀▀▪▄██▪  ▐▀▀▪▄ ▐█.▪▐▀▀▪▄\n" +
                    "   ██. ██ ▐█▄▄▌▐█▌▐▌▐█▄▄▌ ▐█▌·▐█▄▄▌\n" +
                    "   ▀▀▀▀▀•  ▀▀▀ .▀▀▀  ▀▀▀  ▀▀▀  ▀▀▀ \n" +
                    "=====================================");
            System.out.println(blue + " [1]\uD83D\uDE2D탈퇴 [2] 나가기  :");
            int ud = sc.nextInt();
            String sql = "DELETE FROM INFO WHERE USER_MAIL =  ?";
            switch (ud) {
                case 1:
                    try {
                        conn = Common.getConnection();
                        pSmt = conn.prepareStatement(sql);
                        pSmt.setString(1, id_pw.get(0));
                        pSmt.executeUpdate();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        Common.close(pSmt);
                        Common.close(conn);
                    }
                    break;
                case 2:
                    break;
            }
        }
    
        //=========================================================================================================================================================
        public void loveprn(List<RoomInfoVo> LP) {
            System.out.println(blue + "=======================================================================" + exit);
            System.out.println(blue +
                    "   —̳͟͞͞ \uD83D\uDC97    ██╗         ██╗    ███████╗    ████████╗        \uD83C\uDF38       \n" +
                    "               ██║         ██║    ██╔════╝    ╚══██╔══╝               \n" +
                    "               ██║         ██║    ███████╗       ██║    —̳͟͞͞ \uD83D\uDC97             \n" +
                    "      \uD83C\uDF38       ██║         ██║    ╚════██║       ██║                  \n" +
                    "               ███████╗    ██║    ███████║       ██║               —̳͟͞͞ \uD83D\uDC97  \n" +
                    "    —̳͟͞͞ \uD83D\uDC97   ╚══════╝    ╚═╝    ╚══════╝       ╚═╝  \uD83C\uDF38                \n");
            System.out.println(blue + "=======================================================================" + exit);
            for (RoomInfoVo e : LP) {
                System.out.println(blue + "|  ⭐------------------⭐                                              " + exit);
                System.out.println(blue + "|   ▏                    ▏  \uD83C\uDFE0" + e.getAddress() + "                           " + exit);
                System.out.println(blue + "|   ▏" + (e.getPhoto_url()) + "               ▏          \uD83D\uDCB8" + e.getJeonsegeum() + "                                  " + exit);
                System.out.println(blue + "|   ▏                    ▏          \uD83D\uDCB8" + e.getSale_price() + "                                " + exit);
                System.out.println(blue + "|   ▏                    ▏          \uD83D\uDCB8" + e.getMonthly() + "                                " + exit);
                System.out.println(blue + "|  ⭐------------------⭐       \uD83D\uDFEB" + e.getArea() + "                                   " + exit);
                System.out.println(blue + "――――――――――――――――――――――――――――――――――――――――――――" + exit);
            }
        }
    
        //===============================================================================================================================================
        public void mprn() {
            System.out.println(blue + "========================================================================" + exit);
            System.out.println(blue +
                    "|   —̳͟͞͞ \uD83D\uDC97     _____ __ __    _____ _____ _____ _____      \uD83C\uDF38        |\n" +
                    "|               |     |  |  |  |  _  |  _  |   __|   __|               |\n" +
                    "|         \uD83C\uDF38    | | | |_   _|  |   __|     |  |  |   __|   —̳͟͞͞ \uD83D\uDC97    |\n" +
                    "|               |_|_|_| |_|    |__|  |__|__|_____|_____|               |\n" +
                    "|         —̳͟͞͞ \uD83D\uDC97                                 —̳͟͞͞ \uD83D\uDC97            |");
            System.out.println(blue + "|======================================================================|" + exit);
            System.out.println(blue + "| \uD83C\uDF26\uFE0F     ------------------              ------------------            |" + exit);
            System.out.println(blue + "|       |" + exit + "[1]   회원 수정    " + blue + "|     \uD83C\uDF38      |" + exit + "[2]    찜 목록    " + blue + "|           |" + exit);
            System.out.println(blue + "|        ------------------              ------------------            |" + exit);
            System.out.println(blue + "|        ------------------              ------------------            |" + exit);
            System.out.println(blue + "| \uD83C\uDF38    |" + exit + "[3]   올룸 목록    " + blue + "|   \uD83C\uDF26\uFE0F        |" + exit + "[4]   회원 탈퇴   " + blue + "| \uD83C\uDF38        |" + exit);
            System.out.println(blue + "|        ------------------              ------------------            |" + exit);
            System.out.println(blue + "|        ------------------                                            |" + exit);
            System.out.println(blue + "|       |" + exit + "[5]    나가기      " + blue + "         |￣￣￣￣￣￣￣|     " + red + "   ☁\uFE0F   ⭐  " + blue + "    |");
            System.out.println(blue + "|        ------------------         |  선택하쇼   |     " + green + "       \uD83C\uDF38\uD83C\uDF38 " + blue + "    |");
            System.out.println(blue + "|                   ☁\uFE0F              |＿＿＿＿＿＿＿|  " + green + "  ☁\uFE0F     \uD83C\uDF38\uD83C\uDF38\uD83C\uDF38    " + blue + "|");
            System.out.println(blue + "|                                   (\\__/) || " + green + "             \uD83C\uDF38\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38   " + blue + "|");
            System.out.println(blue + "|                                   (•ㅅ•).||  " + green + "                \uD83C\uDF38      " + blue + "|");
            System.out.println(blue + "|       ☁\uFE0F          \uD83C\uDF26\uFE0F              / . . . .づ  " + green + "                      " + blue + "|");
            System.out.println(blue + "========================================================================" + exit);
            System.out.print(blue + "|" + exit + "     " + yellow + "|  선택하쇼   |" + exit + "     " + blue + "|     " + exit);
        }
    
        public LoginInfoVo ExtractInfo(String ID) {
            LoginInfoVo LIV = new LoginInfoVo();
            String sql = "SELECT * FROM INFO WHERE USER_MAIL = ?";
            try {
                conn = Common.getConnection();
                pSmt = conn.prepareStatement(sql);
                pSmt.setString(1, ID);
                rs = pSmt.executeQuery();
                while (rs.next()) {
                    String user_mail = rs.getString("USER_MAIL");
                    String password = rs.getString("PASSWORD");
                    String name = rs.getString("NAME");
                    int first_id_cardnum = rs.getInt("FIRST_ID_CARDNUM");
                    int phone_number = rs.getInt("PHONE_NUMBER");
                    String address = rs.getString("ADDRESS");
                    int gender = rs.getInt("GENDER");
                    String nickname = rs.getString("NICKNAME");
                    LIV.setUser_mail(user_mail);
                    LIV.setPassword(password);
                    LIV.setName(name);
                    LIV.setFirst_id_cardnum(first_id_cardnum);
                    LIV.setPhone_number(phone_number);
                    LIV.setAddress(address);
                    LIV.setGender(gender);
                    LIV.setNickname(nickname);
                }
                Common.close(rs);
                Common.close(pSmt);
                Common.close(conn);
    
            } catch (Exception e) {
                e.printStackTrace();
            }
            return LIV;
        }
        public AdminInfoVo AdminExtractInfo(String ID) {
            AdminInfoVo AIV = new AdminInfoVo();
            String sql = "SELECT * FROM ADMIN_INFO WHERE ADM_ID = ?";
            try {
                conn = Common.getConnection();
                pSmt = conn.prepareStatement(sql);
                pSmt.setString(1, ID);
                rs = pSmt.executeQuery();
                while (rs.next()) {
                    String adm_id = rs.getString("ADM_ID");
                    String adm_pw = rs.getString("ADM_PW");
                    AIV.setADM_ID(adm_id);
                    AIV.setADM_PW(adm_pw);
                }
                Common.close(rs);
                Common.close(pSmt);
                Common.close(conn);
    
            } catch (Exception e) {
                e.printStackTrace();
            }
            return AIV;
        }
    
        //덮어 씌우는 함수
        public LoginInfoVo Overwrite(LoginInfoVo LIV, InfoUpdateVo IUV) {
            if (IUV.getUSER_MAIL() != null) {
                LIV.setUser_mail(IUV.getUSER_MAIL());
            } if (IUV.getPASSWORD() != null) {
                LIV.setPassword(IUV.getPASSWORD());
            } if (IUV.getNAME() != null) {
                LIV.setName(IUV.getNAME());
            } if (IUV.getPHONE_NUMBER() != 0) {
                LIV.setPhone_number(IUV.getPHONE_NUMBER());
            } if (IUV.getADDRESS() != null) {
                LIV.setAddress(IUV.getADDRESS());
            } if (IUV.getNICKNAME() != null) {
                LIV.setNickname(IUV.getNICKNAME());
            }
            return LIV;
        }
        public AdminInfoVo AdminOverwrite(AdminInfoVo aiv, AdminInfoVo AIV) {
            if (AIV.getADM_ID() != null) {
                aiv.setADM_ID(AIV.getADM_ID());
            }
            if (AIV.getADM_PW() != null) {
                aiv.setADM_PW(AIV.getADM_PW());
            }
            return aiv;
        }
    
        public void InfoUpdate(LoginInfoVo LIV, String ID) {
            String sql = "UPDATE INFO SET USER_MAIL = ?, PASSWORD = ?, NAME = ?," +
                    "FIRST_ID_CARDNUM = ?,PHONE_NUMBER = ?, ADDRESS = ?, GENDER = ?, NICKNAME = ? " +
                    "WHERE USER_MAIL = ?";
            try {
                conn = Common.getConnection();
                pSmt = conn.prepareStatement(sql);
                pSmt.setString(1, LIV.getUser_mail());
                pSmt.setString(2, LIV.getPassword());
                pSmt.setString(3, LIV.getName());
                pSmt.setInt(4, LIV.getFirst_id_cardnum());
                pSmt.setInt(5, LIV.getPhone_number());
                pSmt.setString(6, LIV.getAddress());
                pSmt.setInt(7, LIV.getGender());
                pSmt.setString(8, LIV.getNickname());
                pSmt.setString(9, ID);
                pSmt.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Common.close(pSmt);
                Common.close(conn);
            }
        }
        public void AdminInfoUpdate(AdminInfoVo AIV, String ID) {
            String sql = "UPDATE ADMIN_INFO SET ADM_ID = ?, ADM_PW = ? WHERE ADM_ID = ?";
            try {
                conn = Common.getConnection();
                pSmt = conn.prepareStatement(sql);
                pSmt.setString(1, AIV.getADM_ID());
                pSmt.setString(2, AIV.getADM_PW());
                pSmt.setString(3, ID);
                pSmt.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Common.close(pSmt);
                Common.close(conn);
            }
        }
    
        public void UserDeletefunc(String ID) {
            String sql = "DELETE FROM INFO WHERE USER_MAIL =  ?";
            try {
                conn = Common.getConnection();
                pSmt = conn.prepareStatement(sql);
                pSmt.setString(1, ID);
                pSmt.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Common.close(pSmt);
                Common.close(conn);
            }
    
        }
    
        public List<RoomInfoVo> RoomSelect() throws SQLException {
            // 일단 전부 뽑아온다.
            List<RoomInfoVo> list = new ArrayList<>();
            try {
                String sql = "SELECT * FROM ROOMINFO";
                conn = Common.getConnection();
                pSmt = conn.prepareStatement(sql);
                rs = pSmt.executeQuery();
                while (rs.next()) {
                    int house_num = rs.getInt("HOUSE_NUM");
                    String user_id = rs.getString("USER_ID");
                    String photo_url = rs.getString("PHOTO_URL");
                    String trade_method = rs.getString("TRADE_METHOD");
                    int deposit = rs.getInt("DEPOSIT");
                    int monthly = rs.getInt("MONTHLY");
                    int jeonsegeum = rs.getInt("JEONSEGEUM");
                    int Sale_price = rs.getInt("SALE_PRICE");
                    String area = rs.getString("AREA");
                    java.sql.Date account_date = rs.getDate("ACCEPT_DATE");
                    String address = rs.getString("ADDRESS");
                    String floor1 = rs.getString("FLOOR1");
                    int phonenumber = rs.getInt("PHONENUMBER");
                    Date regit_date = rs.getDate("REGIT_DATE");
                    String remark = rs.getString("REMARK");
                    list.add(new RoomInfoVo(house_num, user_id, photo_url, trade_method, deposit, monthly, jeonsegeum,
                            Sale_price, area, account_date, address, floor1, phonenumber, regit_date, remark));
                }
            } catch (Exception e) {
            } finally {
                Common.close(rs);
                Common.close(pSmt);
                Common.close(conn);
            }
            return list;
        }
    
        public void RoomDel(int RoomNum) {
            String sql = "DELETE FROM RoomInfo WHERE HOUSE_NUM = ?";
            try {
                conn = Common.getConnection();
                pSmt = conn.prepareStatement(sql);
                pSmt.setInt(1, RoomNum);
                pSmt.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Common.close(pSmt);
                Common.close(conn);
            }
        }
    
        public List<RoomInfoVo> LoveList(String id) { // 찜한방 목록화
            List<RoomInfoVo> love = new ArrayList<>();
            try {
                conn = Common.getConnection();
                stmt = conn.createStatement();
                String sql = "SELECT rm.house_num,rm.user_id,rm.photo_url,rm.trade_method,rm.deposit,rm.MONTHLY,rm.jeonsegeum,rm.address, rm.area, rm.sale_price " +
                        "FROM ROOMINFO rm JOIN LOVE_ROOM lr " +
                        "ON rm.house_num = lr.shouse_num WHERE lr.user_id = '" + id + "'";
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    //내가 찜한 방에 대한 정보들
                    love.add(new RoomInfoVo(rs.getInt("house_num"), rs.getString("user_id"), rs.getString("photo_url"), rs.getString("trade_method"),
                            rs.getInt("deposit"), rs.getInt("MONTHLY"), rs.getInt("jeonsegeum"),
                            rs.getInt("sale_price"), rs.getString("area"), null, rs.getString("address"), null,
                            0, null, null));
                }
                Common.close(rs);
                Common.close(stmt);
                Common.close(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return love;
        }
    
        public void LoveDelete(LoveRoomVo LRV) { // 찜한방 목록 Delete
            try {
                // love 내가 찜한 방의 정보들
                conn = Common.getConnection();
                stmt = conn.createStatement();
                // 찜한 방 목록에서 확인 후 삭제
                String sql = "DELETE FROM LOVE_ROOM WHERE USER_ID = '" + LRV.getUser_id() + "'" + "AND SHOUSE_NUM = '" + LRV.getShouse_num() + "'";
                rs = stmt.executeQuery(sql);
                // 방목록에서 원하는방 삭제
                Common.close(rs);
                Common.close(stmt);
                Common.close(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    
        public List<RoomInfoVo> OlumList(String id) { // 올룸 목록
    
            List<RoomInfoVo> olume = new ArrayList<>();
            try {
                conn = Common.getConnection();
                stmt = conn.createStatement();
                String sql = "SELECT * FROM ROOMINFO WHERE USER_ID = '" + id + "'";
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    int house_num = rs.getInt("HOUSE_NUM");
                    String user_id = rs.getString("USER_ID");
                    String photo_url = rs.getString("PHOTO_URL");
                    String trade_method = rs.getString("TRADE_METHOD");
                    int deposit = rs.getInt("DEPOSIT");
                    int monthly = rs.getInt("MONTHLY");
                    int jeonsegeum = rs.getInt("JEONSEGEUM");
                    int sale_price = rs.getInt("SALE_PRICE");
                    String area = rs.getString("AREA");
                    Date accept_date =  rs.getDate("ACCEPT_DATE");
                    String address =  rs.getString("ADDRESS");
                    String floor1 = rs.getString("FLOOR1");
                    int phonenumber =rs.getInt("PHONENUMBER");
                    Date regit_date =rs.getDate("REGIT_DATE");
                    String remark = rs.getString("REMARK");
                    olume.add(new RoomInfoVo(house_num,user_id ,photo_url,trade_method, deposit,monthly,jeonsegeum,sale_price
                            ,area,accept_date,address,floor1, phonenumber,regit_date,remark));
                }
                Common.close(rs);
                Common.close(stmt);
                Common.close(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return olume;
        }
    
        public void OlumUpdate(RoomInfoVo RIV) {
    
            try {
                String query = "UPDATE ROOMINFO SET photo_url = ?, TRADE_METHOD = ?, DEPOSIT = ?, MONTHLY = ?, JEONSEGEUM = ?, SALE_PRICE = ?,AREA = ?, ADDRESS = ?,FlOOR1 =?,PHONENUMBER = ?, REMARK = ? WHERE HOUSE_NUM = ?";
                conn = Common.getConnection();
                pSmt = conn.prepareStatement(query);
                pSmt.setString(1, RIV.getPhoto_url());
                pSmt.setString(2, RIV.getTrade_method());
                pSmt.setInt(3, RIV.getDeposit());
                pSmt.setInt(4, RIV.getMonthly());
                pSmt.setInt(5, RIV.getJeonsegeum());
                pSmt.setInt(6, RIV.getSale_price());
                pSmt.setString(7, RIV.getArea());
                pSmt.setString(8, RIV.getAddress());
                pSmt.setString(9, RIV.getFloor1());
                pSmt.setInt(10, RIV.getPhonenumber());
                pSmt.setString(11, RIV.getRemark());
                pSmt.setInt(12, RIV.getHouse_num());
                pSmt.executeUpdate();
            } catch (Exception e) {
            } finally {
                Common.close(rs);
                Common.close(pSmt);
                Common.close(conn);
            }
        }
        public void LoveAllDelete(String ID) { // 찜한방 목록 Delete
            try {
                // love 내가 찜한 방의 정보들
                conn = Common.getConnection();
                stmt = conn.createStatement();
                // 찜한 방 목록에서 확인 후 삭제
                String sql = "DELETE FROM LOVE_ROOM WHERE USER_ID = '" + ID + "'";
                rs = stmt.executeQuery(sql);
                // 방목록에서 원하는방 삭제
                Common.close(rs);
                Common.close(stmt);
                Common.close(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        public void RoomAllDel(String ID) {
            String sql = "DELETE FROM RoomInfo WHERE user_id = ?";
            try {
                conn = Common.getConnection();
                pSmt = conn.prepareStatement(sql);
                pSmt.setString(1, ID);
                pSmt.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Common.close(pSmt);
                Common.close(conn);
            }
        }
        public void MyPageAllDel(String ID) {
            String sql = "DELETE FROM MY_PAGE WHERE USER_MAIL = ?";
            try {
                conn = Common.getConnection();
                pSmt = conn.prepareStatement(sql);
                pSmt.setString(1, ID);
                pSmt.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Common.close(pSmt);
                Common.close(conn);
            }
        }
        public void LoveOlumDelete(int num) { // 찜한방 목록 Delete
            try {
                // love 내가 찜한 방의 정보들
                conn = Common.getConnection();
                stmt = conn.createStatement();
                // 찜한 방 목록에서 확인 후 삭제
                String sql = "DELETE FROM LOVE_ROOM WHERE Shouse_num = '" + num + "'";
                rs = stmt.executeQuery(sql);
                // 방목록에서 원하는방 삭제
                Common.close(rs);
                Common.close(stmt);
                Common.close(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        public void MyPageOlumDelete(String ID) { // 찜한방 목록 Delete
            try {
                // love 내가 찜한 방의 정보들
                conn = Common.getConnection();
                stmt = conn.createStatement();
                // 찜한 방 목록에서 확인 후 삭제
                String sql = "DELETE FROM MY_PAGE WHERE USER_MAIL = '" + ID + "'";
                rs = stmt.executeQuery(sql);
                // 방목록에서 원하는방 삭제
                Common.close(rs);
                Common.close(stmt);
                Common.close(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    
    }
    
    ```
    
- AdminInfoDao
    
    작성자: 김동환
    
    ```java
    package com.kh.Olumgollum_Project.AdminInfo;
    
    import com.kh.Olumgollum_Project.COMMON.Common;
    import com.kh.Olumgollum_Project.RoomInfo.RoomInfoVo;
    
    import javax.swing.*;
    import java.sql.*;
    import java.util.ArrayList;
    import java.util.Collections;
    import java.util.List;
    import java.util.Scanner;
    
    public class AdminInfoDao {
        Connection conn = null;
        Statement stmt = null; // create Statement 방식
        PreparedStatement pStmt = null; // Prepared Statment 방식
        ResultSet rs = null; // database 로 부터 결과를 받는 변수
        Scanner sc = new Scanner(System.in);
        public static final String black = "\u001B[30m";
        public static final String red = "\u001B[31m";
        public static final String green = "\u001B[32m";
        public static final String yellow = "\u001B[33m";
        public static final String blue = "\u001B[34m";
        public static final String purple = "\u001B[35m";
        public static final String cyan = "\u001B[36;m";
        public static final String white = "\u001B[37m";
        public static final String exit = "\u001B[0m";
    
        public List<String> Adminlogin() throws InterruptedException {
            List<String> adm_id_pw = new ArrayList<>();
            String AdId = null;
            String Adpwd = null;
            while (true) {
                System.out.println(blue + "========================================================================" + exit);
                System.out.print(red +
                        "⠀⠀⠀⠀⠀⠀⣀⠄⠒⠉⠉⠉⠉⠉⠉⠑⠂⢄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⠀ ⠀⠀⣀⠄⠒⠉⠉⠉⠉⠉⠉⠑⠂⢤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⢠⠞⢁⠀⡀⠀⠀⠀⠀⣀⣠⣀⡀⢠⠺⠧⢀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⠀⠀⢀⠞⢁⠀⡀⠀⠀⠀⠀⣄⣠⣀⡀⢠⠺⠷⢄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⢠⠃⡸⢚⠛⠇⠀⠀⠀⢰⠁⡀⠈⢣⡜⠀⠀⠀⠀⠈⠁⠐⠢⠤⢄⡀⠀⠀⠀ ⠀⠀⢠⠃⡸⢚⠛⠇⠀⠀⠀⢰⠋⡀⠈⢳⡜⠀⠀⠀⠀⠈⠁⠐⠂⠤⢄⡀⠀⠀\n" +
                        "⠀⠀⢀⠃⠀⣇⢀⣠⣧⣤⣦⣤⣜⣤⣀⡠⢮⠃⠀⠀         ⠀⠀⠈⢱⠀⠀⠀⠇⠀⣇⢀⣠⣧⣤⣦⣤⣼⣦⣀⡠⢮⠂⠀⠀       ⠀⠀⠈⢱\n" +
                        "⠀⠀⠸⠀⠀⢀⣿⣿⣿⣿⣿⣿⣿⣿⣆⠀⡔⠀   [1]     ⠀⠀⠀⠆⠀⠀⠸⠀⠀⢀⣿⣿⣿⣿⣿⣿⣿⣿⣆⠀⡔⠀    [2]    ⠀⠀⠀⠆\n" +
                        "⠀⠀⡄⠀⠀⠸⣿⣿⣿⣿⣿⣿⣿⣿⣿⢠   관리자 로그인 ⠰⠀⠀⠀⡄⠀⠀⠸⣿⣿⣿⣿⣿⣿⣿⣿⣿⢢⠁⠀⠀    종료    ⠀⠰⠀\n" +
                        "⠀⠀⡇⠀⠀⠀⠙⢿⣿⣿⣿⣿⣿⠟⠁⠈⠉⠓⠒⠦⠤⣀⡀⠀⠀  ⠀⠀⠆⠀⠀⠀⡇⠀⠀⠀⠙⠿⣿⣿⣿⣿⣿⠟⠁⠈⠉⠓⠒⠦⠤⣀⡀⠀⠀   ⠀⠀⠆⠀\n" +
                        "⠀⢀⠃⠀⠀⠀⠀⠀⠈⠛⠛⠋⠁⠀⠀⠀⠀⠀⠀⣠⠞⠓⠛⠿⣷⡶⢦⡤⠰⠀⠀⠀⢀⠇⠀⠀⠀⠀⠀⠈⠛⠛⠋⠁⠀⠀⠀⠀⠀⠀⢠⠖⠓⠛⠿⣷⡴⢦⡤⠰⠀⠀\n" +
                        "⡰⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠹⣦⣤⡄⠀⠀⠘⡄⠀⠈⠀⠀⡠⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⣦⣤⡄⠀⠀⠘⡄⠀⠉⠀⠀\n"+exit);
                System.out.println(blue + "========================================================================" + exit);
                System.out.print(purple + "   번호 입력 :  ");
                int lo = sc.nextInt();
                System.out.println(blue + "========================================================================" + exit);
                if (lo == 1) {
                    System.out.println(green+"  참고"+exit+" : 테스트를 위한 예시 아이디 : dyddnr33@naver.com 패스워드 : admin1691");
                    System.out.println(blue + "========================================================================" + exit);
                    System.out.print("  아이디 입력 : ");
                    AdId = sc.next();
                    System.out.println(blue + "========================================================================" + exit);
                    System.out.print("  비밀번호 입력 : ");
                    String password = sc.next();
                    System.out.println(blue + "========================================================================" + exit);
                    System.out.println(yellow + "                     로그인 중입니다. 잠시만 기다려주세요..." + exit);
                    System.out.println(blue + "========================================================================" + exit);
                    Thread.sleep(2000);
                    String sql = "SELECT ADM_PW FROM ADMIN_INFO WHERE ADM_ID= ? ";
                    try {
                        conn = Common.getConnection();
                        pStmt = conn.prepareStatement(sql);
                        pStmt.setString(1, AdId);
                        rs = pStmt.executeQuery(); // rs 객체에 쿼리 결과를 할당
    
                        if (rs.next()) {
                            Adpwd = rs.getString("ADM_PW");
                            if (Adpwd.equals(password)) {
                                adm_id_pw.add(AdId);
                                adm_id_pw.add(Adpwd);
                                adm_id_pw.add("Admin");
                                System.out.println("\n" +
                                        "⠀⠀⠀⠀⠀⠀⠀⢀⣴⣾⣿⡿⠛⠛⠛⢛⣽⣿⣿⣿⣦⣄⠀⠀⠀⠀⠀⠀\n" +
                                        "⠀⠀⠀⠀⠀⢀⣴⣿⣿⣿⣿⣿⣦⠀⢼⣾⣿⣿⠏⢿⣿⣿⡆⠀⠀⠀⠀⠀\n" +
                                        "⠀⠀⠀⠀⢠⣾⣿⣿⡏⠙⢿⣿⡿⠛⠀⠻⠿⠋⠁⠴⡽⣻⡅⠀⠀⠀⠀⠀\n" +
                                        "⠀⠀⠀⢰⣿⢿⣿⠏⠇⠀⠀⠀⠀⠀⠀⠀⢀⣠⣤⣄⣈⣈⣧⣀⠀⠀⠀⠀\n" +
                                        "⠀⠀⢠⣿⡿⠧⠂⢡⣤⣤⣤⣴⣶⡆⠠⢾⣿⢿⠛⢛⣋⣫⠤⠬⠟⠂⠀⠀\n" +
                                        "⠀⠀⢸⣿⡗⣮⣶⠶⡟⠛⣛⣛⣙⡱⠤⠞⠚⠚⠉⠉⠀⠀⠀⠀⠀⠀⠸⡄\n" +
                                        "⢀⡾⢉⣿⣷⠤⠔⠒⠒⠉⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇\n" +
                                        "⣸⠌⠸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢣\n" +
                                        "⣿⠺⢟⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣾\n" +
                                        "⠸⡑⢠⣵⣀⠀⠀⠀⠀⠀⠀로그인 성공   ⠀⠀⠀⠘⣿\n" +
                                        "⠀⠈⣿⡿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                                        "⡰⠚⠉⣿⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                                        "⡇⠀⢰⣿⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                                        "⠈⠛⠋⠀⠛⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                                        "⠀⠀⢀⠤⢤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⣤⣤⠀⠐⠂\n" +
                                        "⠀⢀⣏⣀⣠⡇⠀⠀⠀⠀⣀⣀⢠⣤⣤⣴⣶⣾⣿⣿⣿⣿⣿⣿⡿⠀⠀⠀\n");
                                System.out.println(blue + "========================================================================" + exit);
                                return adm_id_pw;
                            } else {
                                System.out.println(red + "    비밀번호가 틀렸습니다." + exit);
                            }
                        } else {
                            System.out.println(red + "    아이디를 잘못 입력했습니다." + exit);
                        }
    
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        Common.close(rs);
                        Common.close(pStmt);
                        Common.close(conn);
                    }
                } else {
                    System.out.println(red + "                           프로그램을 종료합니다." + exit);
                    Thread.sleep(1000);
                    System.exit(0);
                }
    
            }
        }
    
        public List<AdminInfoVo> admin_infoSelect() {
            List<AdminInfoVo> list = new ArrayList<>();
            try {
                conn = Common.getConnection();
                stmt = conn.createStatement();
                String sql = "SELECT * FROM ADMIN_INFO";
                rs = stmt.executeQuery(sql);
    
                while (rs.next()) {
                    String AdmId = rs.getString("ADM_ID");
                    String AdmPw = rs.getString("ADM_PW");
                    list.add(new AdminInfoVo(AdmId, AdmPw));
                }
                Common.close(rs);
                Common.close(stmt);
                Common.close(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
    
            return list;
        }
    
        public void AdminInsert() {
            System.out.println("아이디 입력");
            String ID = sc.next();
            System.out.println("비밀번호 입력");
            String pwd = sc.next();
            String sql = "INSERT INTO ADMIN_INFO(ADM_ID,ADM_PW) VALUES (?,?)";
    
            try {
                conn = Common.getConnection();
                pStmt = conn.prepareStatement(sql);
                pStmt.setString(1, ID);
                pStmt.setString(2, pwd);
                int rst = pStmt.executeUpdate();
                System.out.println("rst : " + rst);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Common.close(pStmt);
            Common.close(conn);
        }
    
        public void AdminDelete(){
            System.out.println("\n" +
                    "⠀⠀⠀⠀⠀⠀⠀⠀⣠⠴⠒⠚⠉⠉⠉⠉⠉⠛⠒⠲⢄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⠀⠀⠀⣴⠚⠁⠀⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀⠀⣩⣗⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⠀⢀⡞⢡⣤⣧⣾⠀⠀⠀⠀⠀⣸⠗⠛⠲⣇⢠⡃⠀⠈⠑⠒⠂⠤⢀⢀⠀⠀⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⢀⠾⠀⡞⠡⠄⠈⡇⠀⠀⠀⠀⡁⠀⠆⠀⢸⡾⠀⠀⠀⠀   ⠀⠀⠀⠈⠁⠂⠒⠤⢀⣀\n" +
                    "⠀⠀⠀⣸⠂⠀⢇⣀⣠⣾⣷⣾⣿⣶⣶⣿⡦⠤⠴⢫⡇⠀⠀⠀삭제하고자하는⠀⠀⠀⠘\n" +
                    "⠀⠀⠀⡇⠀⠀⢀⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⠀⡜⠀⠀   아이디 입력⠀⠀⠀⢀⠇\n" +
                    "⠀⠀⢠⡇⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢦⠇⠀⠀             ⠀⠀⢸⠀\n" +
                    "⠀⠀⢸⠀⠀⠀⠀⠻⣿⣿⣿⣿⣿⣿⣿⣿⡿⠟⠘⠦⠤⢤⣀⠀⠀⠀         ⠀⠀⠃⠀\n" +
                    "⠀⠀⢸⠀⠀⠀⠀⠀⠈⠻⢿⣿⣿⣿⡿⠛⠁⠀⠀⠀⠀⠀⠈⠉⢙⣗⣲⣤⣤⣀⡀⠀⠀⠀⡸⠀⠀\n" +
                    "⠀⢀⡟⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⠋⠉⠉⠙⠻⠿⣜⠃⠵⢲⣃⠀⠀\n" +
                    "⣰⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢛⣷⣶⣤⠀⠀⠀⠘⡆⠀⠀⠀⠀⠀\n");
            String del=sc.next();
            String sql="DELETE FROM ADMIN_INFO WHERE ADM_ID=?";
            try{
                conn=Common.getConnection();
                pStmt=conn.prepareStatement(sql);
                pStmt.setString(1,del);
                int rst = pStmt.executeUpdate();
                System.out.println("rst : "+ rst);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                Common.close(pStmt);
                Common.close(conn);
            }
    
        }
    
        public void LogDelete(){
            System.out.println("\n" +
                    "⠀⠀⠀⠀⠀⠀⠀⠀⣠⠴⠒⠚⠉⠉⠉⠉⠉⠛⠒⠲⢄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⠀⠀⠀⣴⠚⠁⠀⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀⠀⣩⣗⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⠀⢀⡞⢡⣤⣧⣾⠀⠀⠀⠀⠀⣸⠗⠛⠲⣇⢠⡃⠀⠈⠑⠒⠂⠤⢀⢀⠀⠀⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⢀⠾⠀⡞⠡⠄⠈⡇⠀⠀⠀⠀⡁⠀⠆⠀⢸⡾⠀⠀⠀⠀   ⠀⠀⠀⠈⠁⠂⠒⠤⢀⣀\n" +
                    "⠀⠀⠀⣸⠂⠀⢇⣀⣠⣾⣷⣾⣿⣶⣶⣿⡦⠤⠴⢫⡇⠀⠀⠀삭제하고자하는⠀⠀⠀⠘\n" +
                    "⠀⠀⠀⡇⠀⠀⢀⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⠀⡜⠀⠀   아이디 입력⠀⠀⠀⢀⠇\n" +
                    "⠀⠀⢠⡇⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢦⠇⠀⠀             ⠀⠀⢸⠀\n" +
                    "⠀⠀⢸⠀⠀⠀⠀⠻⣿⣿⣿⣿⣿⣿⣿⣿⡿⠟⠘⠦⠤⢤⣀⠀⠀⠀         ⠀⠀⠃⠀\n" +
                    "⠀⠀⢸⠀⠀⠀⠀⠀⠈⠻⢿⣿⣿⣿⡿⠛⠁⠀⠀⠀⠀⠀⠈⠉⢙⣗⣲⣤⣤⣀⡀⠀⠀⠀⡸⠀⠀\n" +
                    "⠀⢀⡟⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⠋⠉⠉⠙⠻⠿⣜⠃⠵⢲⣃⠀⠀\n" +
                    "⣰⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢛⣷⣶⣤⠀⠀⠀⠘⡆⠀⠀⠀⠀⠀\n");
            String del=sc.next();
            String sql="DELETE FROM INFO WHERE USER_MAIL=?";
            try{
                conn= Common.getConnection();
                pStmt=conn.prepareStatement(sql);
                pStmt.setString(1,del);
                int rst = pStmt.executeUpdate();
                System.out.println("rst : "+ rst);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                Common.close(pStmt);
                Common.close(conn);
            }
        }
    
        public List<RoomInfoVo> RoomSelect() {
            List<RoomInfoVo> list = new ArrayList<>();
            try {
                conn = Common.getConnection();
                stmt = conn.createStatement();
                String sql = "SELECT * FROM RoomInfo";
                rs = stmt.executeQuery(sql);
    
                while (rs.next()) {
                    int house_num = rs.getInt("HOUSE_NUM");
                    String user_id = rs.getString("user_id");
                    String photo_url = rs.getString("photo_url");
                    String trade_method = rs.getString("trade_method");
                    int deposit = rs.getInt("deposit");
                    int monthly = rs.getInt("monthly");
                    int jeonsegeum = rs.getInt("jeonsegeum");
                    int Sale_price = rs.getInt("Sale_price");
                    String area = rs.getString("area");
                    Date accept_date = rs.getDate("accept_date");
                    String address = rs.getString("address");
                    String floor1 = rs.getString("floor1");
                    int phonenumber = rs.getInt("phonenumber");
                    Date regit_date = rs.getDate("regit_date");
                    String remark = rs.getString("remark");
                    list.add(new RoomInfoVo(house_num, user_id, photo_url, trade_method, deposit, monthly, jeonsegeum, Sale_price, area, accept_date, address, floor1, phonenumber, regit_date, remark));
                }
                Common.close(rs);
                Common.close(stmt);
                Common.close(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return list;
        }
    
        public void RoomUpdate(){
            System.out.println(blue + "========================================================================" + exit);
            System.out.println(blue +
                    "|   —̳͟͞͞ \uD83D\uDC97     _____ __ __    _____ _____ _____ _____      \uD83C\uDF38        |\n" +
                    "|               |     |  |  |  |  _  |  _  |   __|   __|               |\n" +
                    "|         \uD83C\uDF38    | | | |_   _|  |   __|     |  |  |   __|   —̳͟͞͞ \uD83D\uDC97    |\n" +
                    "|               |_|_|_| |_|    |__|  |__|__|_____|_____|               |\n" +
                    "|         —̳͟͞͞ \uD83D\uDC97                                 —̳͟͞͞ \uD83D\uDC97            |");
            System.out.println(blue + "|======================================================================|" + exit);
            System.out.println(blue + "| \uD83C\uDF26\uFE0F     ------------------              ------------------            |" + exit);
            System.out.println(blue + "|        |" + exit + purple+"[1]   방 사진     "+exit + blue + "|     \uD83C\uDF38     |" + exit + purple+"[2] 거래방식(가격) "+exit + blue + "|            |" + exit);
            System.out.println(blue + "|        ------------------              ------------------            |" + exit);
            System.out.println(blue + "|        ------------------              ------------------            |" + exit);
            System.out.println(blue + "| \uD83C\uDF38     |" + exit + purple+"[3]   전화번호    "+exit + blue + "|   \uD83C\uDF26\uFE0F       |" + exit + purple+"[4]     비고      "+exit + blue + "| \uD83C\uDF38        |" + exit);
            System.out.println(blue + "|        ------------------              ------------------            |" + exit);
            System.out.println(blue + "|        ------------------                                            |" + exit);
            System.out.println(blue + "|        |" + exit + purple+"[5]    나가기     "+exit + blue + "|         |￣￣￣￣￣￣￣|     " + red + "   ☁\uFE0F   ⭐  " + blue + "    |");
            System.out.println(blue + "|        ------------------         |  선택하쇼   |     " + green + "       \uD83C\uDF38\uD83C\uDF38 " + blue + "    |");
            System.out.println(blue + "|                   ☁\uFE0F              |＿＿＿＿＿＿＿|  " + green + "  ☁\uFE0F     \uD83C\uDF38\uD83C\uDF38\uD83C\uDF38    " + blue + "|");
            System.out.println(blue + "|                                   (\\__/) || " + green + "             \uD83C\uDF38\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38   " + blue + "|");
            System.out.println(blue + "|                                   (•ㅅ•).||  " + green + "                \uD83C\uDF38      " + blue + "|");
            System.out.println(blue + "|       ☁\uFE0F          \uD83C\uDF26\uFE0F              / . . . .づ  " + green + "                      " + blue + "|");
            System.out.println(blue + "========================================================================" + exit);
            System.out.print(blue + "|" + exit + yellow + "    선택하쇼   " + exit  + blue + "|     " + exit);
            int up = sc.nextInt();
            System.out.println(blue + "|======================================================================|" + exit);
            String sql = null;
            int honum;
            switch (up) {
                case 1:
                    System.out.println("바꾸고자 하는 매물번호");
                    honum=sc.nextInt();
                    System.out.println("수정 사진 등록");
                    String url = sc.next();
                    sql = "UPDATE ROOMINFO SET photo_url = ? WHERE house_num=?";
                    try{
                        conn=Common.getConnection();
                        pStmt = conn.prepareStatement(sql);
                        pStmt.setString(1,url);
                        pStmt.setInt(2,honum);
                        int rst = pStmt.executeUpdate();
                        System.out.println("rst : " + rst);
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        Common.close(pStmt);
                        Common.close(conn);
                    }
                    break;
                case 2:
                    System.out.println("바꾸고자 하는 매물번호");
                    honum=sc.nextInt();
                    System.out.println(blue + "========================================================================" + exit);
                    System.out.println(blue + "|" + exit + "                       " + purple + "원하시는 기능을 골라주세요~!" + exit + "                        " + blue + "|" + exit);
                    System.out.println(blue + "|======================================================================|" + exit);
                    System.out.println(blue + "|                                                                      |" + exit);
                    System.out.println(blue + "|        ------------------              ------------------            |" + exit);
                    System.out.println(blue + "|       |" + exit + "   [1]매매   " + blue + "|            |" + exit + "   [3] 월세  " + blue + "|           |" + exit);
                    System.out.println(blue + "|        ------------------              ------------------            |" + exit);
                    System.out.println(blue + "|                                                                      |" + exit);
                    System.out.println(blue + "|                                                                      |" + exit);
                    System.out.println(blue + "|        ------------------              ------------------            |" + exit);
                    System.out.println(blue + "|       |" + exit + "   [2] 전세    " + blue + "|             |" + exit + "    [4]종료     " + blue + "|            |" + exit);
                    System.out.println(blue + "|        ------------------              ------------------            |" + exit);
                    System.out.println(blue + "|                                                                      |" + exit);
                    System.out.println(blue + "========================================================================" + exit);
                    int tr=sc.nextInt();
                    if(tr==4){
                        break;
                    }
                    sql = "UPDATE ROOMINFO SET deposit=NULL,monthly=NULL,jeonsegeum=NULL,Sale_price=NULL WHERE house_num=?";
                    try{
                        conn=Common.getConnection();
                        pStmt = conn.prepareStatement(sql);
                        pStmt.setInt(1,honum);
                        int rst = pStmt.executeUpdate();
                        System.out.println("rst : " + rst);
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        Common.close(pStmt);
                        Common.close(conn);
                    }
    
                    switch (tr){
                        case 1:
                            System.out.println("\n" +
                                    "⠀⠀⠀⠀⠀⠀⠀⠀⣠⠴⠒⠚⠉⠉⠉⠉⠉⠛⠒⠲⢄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                                    "⠀⠀⠀⠀⠀⠀⣴⠚⠁⠀⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀⠀⣩⣗⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                                    "⠀⠀⠀⠀⢀⡞⢡⣤⣧⣾⠀⠀⠀⠀⠀⣸⠗⠛⠲⣇⢠⡃⠀⠈⠑⠒⠂⠤⢀⢀⠀⠀⠀⠀⠀⠀⠀\n" +
                                    "⠀⠀⠀⢀⠾⠀⡞⠡⠄⠈⡇⠀⠀⠀⠀⡁⠀⠆⠀⢸⡾⠀⠀⠀⠀   ⠀⠀⠀⠈⠁⠂⠒⠤⢀⣀\n" +
                                    "⠀⠀⠀⣸⠂⠀⢇⣀⣠⣾⣷⣾⣿⣶⣶⣿⡦⠤⠴⢫⡇⠀⠀             ⠀⠀⠘\n" +
                                    "⠀⠀⠀⡇⠀⠀⢀⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⠀⡜⠀⠀  매매 가격 입력⠀⠀⢀⠇\n" +
                                    "⠀⠀⢠⡇⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢦⠇⠀⠀             ⠀⠀⢸⠀\n" +
                                    "⠀⠀⢸⠀⠀⠀⠀⠻⣿⣿⣿⣿⣿⣿⣿⣿⡿⠟⠘⠦⠤⢤⣀⠀⠀⠀         ⠀⠀⠃⠀\n" +
                                    "⠀⠀⢸⠀⠀⠀⠀⠀⠈⠻⢿⣿⣿⣿⡿⠛⠁⠀⠀⠀⠀⠀⠈⠉⢙⣗⣲⣤⣤⣀⡀⠀⠀⠀⡸⠀⠀\n" +
                                    "⠀⢀⡟⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⠋⠉⠉⠙⠻⠿⣜⠃⠵⢲⣃⠀⠀\n" +
                                    "⣰⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢛⣷⣶⣤⠀⠀⠀⠘⡆⠀⠀⠀⠀⠀\n");
                            int sal=sc.nextInt();
                            sql = "UPDATE ROOMINFO SET trade_method=?,Sale_price=? WHERE house_num=?";
                            try{
                                conn=Common.getConnection();
                                pStmt = conn.prepareStatement(sql);
                                pStmt.setString(1,"매매");
                                pStmt.setInt(2,sal);
                                pStmt.setInt(3,honum);
                                int rst = pStmt.executeUpdate();
                                System.out.println("rst : " + rst);
                            }catch (Exception e){
                                e.printStackTrace();
                            }finally {
                                Common.close(pStmt);
                                Common.close(conn);
                            }
                            break;
                        case 2:
                            System.out.println("\n" +
                                    "⠀⠀⠀⠀⠀⠀⠀⠀⣠⠴⠒⠚⠉⠉⠉⠉⠉⠛⠒⠲⢄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                                    "⠀⠀⠀⠀⠀⠀⣴⠚⠁⠀⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀⠀⣩⣗⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                                    "⠀⠀⠀⠀⢀⡞⢡⣤⣧⣾⠀⠀⠀⠀⠀⣸⠗⠛⠲⣇⢠⡃⠀⠈⠑⠒⠂⠤⢀⢀⠀⠀⠀⠀⠀⠀⠀\n" +
                                    "⠀⠀⠀⢀⠾⠀⡞⠡⠄⠈⡇⠀⠀⠀⠀⡁⠀⠆⠀⢸⡾⠀⠀⠀⠀   ⠀⠀⠀⠈⠁⠂⠒⠤⢀⣀\n" +
                                    "⠀⠀⠀⣸⠂⠀⢇⣀⣠⣾⣷⣾⣿⣶⣶⣿⡦⠤⠴⢫⡇⠀⠀             ⠀⠀⠘\n" +
                                    "⠀⠀⠀⡇⠀⠀⢀⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⠀⡜⠀⠀  전세 가격 입력⠀⠀⢀⠇\n" +
                                    "⠀⠀⢠⡇⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢦⠇⠀⠀             ⠀⠀⢸⠀\n" +
                                    "⠀⠀⢸⠀⠀⠀⠀⠻⣿⣿⣿⣿⣿⣿⣿⣿⡿⠟⠘⠦⠤⢤⣀⠀⠀⠀         ⠀⠀⠃⠀\n" +
                                    "⠀⠀⢸⠀⠀⠀⠀⠀⠈⠻⢿⣿⣿⣿⡿⠛⠁⠀⠀⠀⠀⠀⠈⠉⢙⣗⣲⣤⣤⣀⡀⠀⠀⠀⡸⠀⠀\n" +
                                    "⠀⢀⡟⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⠋⠉⠉⠙⠻⠿⣜⠃⠵⢲⣃⠀⠀\n" +
                                    "⣰⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢛⣷⣶⣤⠀⠀⠀⠘⡆⠀⠀⠀⠀⠀\n");
                            int jeo=sc.nextInt();
                            sql = "UPDATE ROOMINFO SET trade_method=?,jeonsegeum=? WHERE house_num=?";
                            try{
                                conn=Common.getConnection();
                                pStmt = conn.prepareStatement(sql);
                                pStmt.setString(1,"전세");
                                pStmt.setInt(2,jeo);
                                pStmt.setInt(3,honum);
                                int rst = pStmt.executeUpdate();
                                System.out.println("rst : " + rst);
                            }catch (Exception e){
                                e.printStackTrace();
                            }finally {
                                Common.close(pStmt);
                                Common.close(conn);
                            }
                            break;
                        case 3:
                            System.out.println("\n" +
                                    "⠀⠀⠀⠀⠀⠀⠀⠀⣠⠴⠒⠚⠉⠉⠉⠉⠉⠛⠒⠲⢄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                                    "⠀⠀⠀⠀⠀⠀⣴⠚⠁⠀⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀⠀⣩⣗⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                                    "⠀⠀⠀⠀⢀⡞⢡⣤⣧⣾⠀⠀⠀⠀⠀⣸⠗⠛⠲⣇⢠⡃⠀⠈⠑⠒⠂⠤⢀⢀⠀⠀⠀⠀⠀⠀⠀\n" +
                                    "⠀⠀⠀⢀⠾⠀⡞⠡⠄⠈⡇⠀⠀⠀⠀⡁⠀⠆⠀⢸⡾⠀⠀⠀⠀   ⠀⠀⠀⠈⠁⠂⠒⠤⢀⣀\n" +
                                    "⠀⠀⠀⣸⠂⠀⢇⣀⣠⣾⣷⣾⣿⣶⣶⣿⡦⠤⠴⢫⡇⠀⠀             ⠀⠀⠘\n" +
                                    "⠀⠀⠀⡇⠀⠀⢀⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⠀⡜⠀월세 보증금 가격 입력⠀⠇\n" +
                                    "⠀⠀⢠⡇⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢦⠇⠀⠀             ⠀⠀⢸⠀\n" +
                                    "⠀⠀⢸⠀⠀⠀⠀⠻⣿⣿⣿⣿⣿⣿⣿⣿⡿⠟⠘⠦⠤⢤⣀⠀⠀⠀         ⠀⠀⠃⠀\n" +
                                    "⠀⠀⢸⠀⠀⠀⠀⠀⠈⠻⢿⣿⣿⣿⡿⠛⠁⠀⠀⠀⠀⠀⠈⠉⢙⣗⣲⣤⣤⣀⡀⠀⠀⠀⡸⠀⠀\n" +
                                    "⠀⢀⡟⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⠋⠉⠉⠙⠻⠿⣜⠃⠵⢲⣃⠀⠀\n" +
                                    "⣰⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢛⣷⣶⣤⠀⠀⠀⠘⡆⠀⠀⠀⠀⠀\n");
                            int dep=sc.nextInt();
                            System.out.println("\n" +
                                    "⠀⠀⠀⠀⠀⠀⠀⠀⣠⠴⠒⠚⠉⠉⠉⠉⠉⠛⠒⠲⢄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                                    "⠀⠀⠀⠀⠀⠀⣴⠚⠁⠀⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀⠀⣩⣗⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                                    "⠀⠀⠀⠀⢀⡞⢡⣤⣧⣾⠀⠀⠀⠀⠀⣸⠗⠛⠲⣇⢠⡃⠀⠈⠑⠒⠂⠤⢀⢀⠀⠀⠀⠀⠀⠀⠀\n" +
                                    "⠀⠀⠀⢀⠾⠀⡞⠡⠄⠈⡇⠀⠀⠀⠀⡁⠀⠆⠀⢸⡾⠀⠀⠀⠀   ⠀⠀⠀⠈⠁⠂⠒⠤⢀⣀\n" +
                                    "⠀⠀⠀⣸⠂⠀⢇⣀⣠⣾⣷⣾⣿⣶⣶⣿⡦⠤⠴⢫⡇⠀⠀             ⠀⠀⠘\n" +
                                    "⠀⠀⠀⡇⠀⠀⢀⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⠀⡜⠀⠀  월세 가격 입력⠀⠀⢀⠇\n" +
                                    "⠀⠀⢠⡇⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢦⠇⠀⠀             ⠀⠀⢸⠀\n" +
                                    "⠀⠀⢸⠀⠀⠀⠀⠻⣿⣿⣿⣿⣿⣿⣿⣿⡿⠟⠘⠦⠤⢤⣀⠀⠀⠀         ⠀⠀⠃⠀\n" +
                                    "⠀⠀⢸⠀⠀⠀⠀⠀⠈⠻⢿⣿⣿⣿⡿⠛⠁⠀⠀⠀⠀⠀⠈⠉⢙⣗⣲⣤⣤⣀⡀⠀⠀⠀⡸⠀⠀\n" +
                                    "⠀⢀⡟⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⠋⠉⠉⠙⠻⠿⣜⠃⠵⢲⣃⠀⠀\n" +
                                    "⣰⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢛⣷⣶⣤⠀⠀⠀⠘⡆⠀⠀⠀⠀⠀\n");
                            int mon=sc.nextInt();
                            sql = "UPDATE ROOMINFO SET trade_method=?,deposit=?,monthly=? WHERE house_num=?";
                            try{
                                conn=Common.getConnection();
                                pStmt = conn.prepareStatement(sql);
                                pStmt.setString(1,"월세");
                                pStmt.setInt(2,dep);
                                pStmt.setInt(3,mon);
                                pStmt.setInt(4,honum);
                                int rst = pStmt.executeUpdate();
                                System.out.println("rst : " + rst);
                            }catch (Exception e){
                                e.printStackTrace();
                            }finally {
                                Common.close(pStmt);
                                Common.close(conn);
                            }
                            break;
                    }
                    break;
                case 3:
                    System.out.println("\n" +
                            "⠀⠀⠀⠀⠀⠀⠀⠀⣠⠴⠒⠚⠉⠉⠉⠉⠉⠛⠒⠲⢄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                            "⠀⠀⠀⠀⠀⠀⣴⠚⠁⠀⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀⠀⣩⣗⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                            "⠀⠀⠀⠀⢀⡞⢡⣤⣧⣾⠀⠀⠀⠀⠀⣸⠗⠛⠲⣇⢠⡃⠀⠈⠑⠒⠂⠤⢀⢀⠀⠀⠀⠀⠀⠀⠀\n" +
                            "⠀⠀⠀⢀⠾⠀⡞⠡⠄⠈⡇⠀⠀⠀⠀⡁⠀⠆⠀⢸⡾⠀⠀⠀⠀   ⠀⠀⠀⠈⠁⠂⠒⠤⢀⣀\n" +
                            "⠀⠀⠀⣸⠂⠀⢇⣀⣠⣾⣷⣾⣿⣶⣶⣿⡦⠤⠴⢫⡇⠀⠀바꾸고자 하는 ⠀⠀⠘\n" +
                            "⠀⠀⠀⡇⠀⠀⢀⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⠀⡜⠀⠀  매물 번호 입력⠀⠀⢀⠇\n" +
                            "⠀⠀⢠⡇⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢦⠇⠀⠀             ⠀⠀⢸⠀\n" +
                            "⠀⠀⢸⠀⠀⠀⠀⠻⣿⣿⣿⣿⣿⣿⣿⣿⡿⠟⠘⠦⠤⢤⣀⠀⠀⠀         ⠀⠀⠃⠀\n" +
                            "⠀⠀⢸⠀⠀⠀⠀⠀⠈⠻⢿⣿⣿⣿⡿⠛⠁⠀⠀⠀⠀⠀⠈⠉⢙⣗⣲⣤⣤⣀⡀⠀⠀⠀⡸⠀⠀\n" +
                            "⠀⢀⡟⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⠋⠉⠉⠙⠻⠿⣜⠃⠵⢲⣃⠀⠀\n" +
                            "⣰⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢛⣷⣶⣤⠀⠀⠀⠘⡆⠀⠀⠀⠀⠀\n");
                    honum=sc.nextInt();
                    System.out.println("수정 하고자 하는 전화번호");
                    int phnum=sc.nextInt();
                    sql = "UPDATE ROOMINFO SET phonenumber = ? WHERE house_num=?";
                    try{
                        conn=Common.getConnection();
                        pStmt = conn.prepareStatement(sql);
                        pStmt.setInt(1,phnum);
                        pStmt.setInt(2,honum);
                        int rst = pStmt.executeUpdate();
                        System.out.println("rst : " + rst);
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        Common.close(pStmt);
                        Common.close(conn);
                    }
                    break;
                case 4:
                    System.out.println("\n" +
                            "⠀⠀⠀⠀⠀⠀⠀⠀⣠⠴⠒⠚⠉⠉⠉⠉⠉⠛⠒⠲⢄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                            "⠀⠀⠀⠀⠀⠀⣴⠚⠁⠀⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀⠀⣩⣗⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                            "⠀⠀⠀⠀⢀⡞⢡⣤⣧⣾⠀⠀⠀⠀⠀⣸⠗⠛⠲⣇⢠⡃⠀⠈⠑⠒⠂⠤⢀⢀⠀⠀⠀⠀⠀⠀⠀\n" +
                            "⠀⠀⠀⢀⠾⠀⡞⠡⠄⠈⡇⠀⠀⠀⠀⡁⠀⠆⠀⢸⡾⠀⠀⠀⠀   ⠀⠀⠀⠈⠁⠂⠒⠤⢀⣀\n" +
                            "⠀⠀⠀⣸⠂⠀⢇⣀⣠⣾⣷⣾⣿⣶⣶⣿⡦⠤⠴⢫⡇⠀⠀바꾸고자 하는 ⠀⠀⠘\n" +
                            "⠀⠀⠀⡇⠀⠀⢀⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⠀⡜⠀⠀  매물 번호 입력⠀⠀⢀⠇\n" +
                            "⠀⠀⢠⡇⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢦⠇⠀⠀             ⠀⠀⢸⠀\n" +
                            "⠀⠀⢸⠀⠀⠀⠀⠻⣿⣿⣿⣿⣿⣿⣿⣿⡿⠟⠘⠦⠤⢤⣀⠀⠀⠀         ⠀⠀⠃⠀\n" +
                            "⠀⠀⢸⠀⠀⠀⠀⠀⠈⠻⢿⣿⣿⣿⡿⠛⠁⠀⠀⠀⠀⠀⠈⠉⢙⣗⣲⣤⣤⣀⡀⠀⠀⠀⡸⠀⠀\n" +
                            "⠀⢀⡟⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⠋⠉⠉⠙⠻⠿⣜⠃⠵⢲⣃⠀⠀\n" +
                            "⣰⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢛⣷⣶⣤⠀⠀⠀⠘⡆⠀⠀⠀⠀⠀\n");
                    honum=sc.nextInt();
                    sc.nextLine();
                    System.out.println("수정 할 비고");
                    String rem=sc.nextLine();
                    sql = "UPDATE ROOMINFO SET remark = ? WHERE house_num=?";
                    try{
                        conn=Common.getConnection();
                        pStmt = conn.prepareStatement(sql);
                        pStmt.setString(1,rem);
                        pStmt.setInt(2,honum);
                        int rst = pStmt.executeUpdate();
                        System.out.println("rst : " + rst);
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        Common.close(pStmt);
                        Common.close(conn);
                    }
                    break;
                case 5:
                    break;
            }
        }
    
        public void RoomDelete(){
            System.out.println("\n" +
                    "⠀⠀⠀⠀⠀⠀⠀⠀⣠⠴⠒⠚⠉⠉⠉⠉⠉⠛⠒⠲⢄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⠀⠀⠀⣴⠚⠁⠀⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀⠀⣩⣗⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⠀⢀⡞⢡⣤⣧⣾⠀⠀⠀⠀⠀⣸⠗⠛⠲⣇⢠⡃⠀⠈⠑⠒⠂⠤⢀⢀⠀⠀⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⢀⠾⠀⡞⠡⠄⠈⡇⠀⠀⠀⠀⡁⠀⠆⠀⢸⡾⠀⠀⠀⠀   ⠀⠀⠀⠈⠁⠂⠒⠤⢀⣀\n" +
                    "⠀⠀⠀⣸⠂⠀⢇⣀⣠⣾⣷⣾⣿⣶⣶⣿⡦⠤⠴⢫⡇⠀⠀삭제 하고자 하는⠀⠀⠘\n" +
                    "⠀⠀⠀⡇⠀⠀⢀⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⠀⡜⠀⠀  매물 번호 입력⠀⠀⢀⠇\n" +
                    "⠀⠀⢠⡇⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢦⠇⠀⠀             ⠀⠀⢸⠀\n" +
                    "⠀⠀⢸⠀⠀⠀⠀⠻⣿⣿⣿⣿⣿⣿⣿⣿⡿⠟⠘⠦⠤⢤⣀⠀⠀⠀         ⠀⠀⠃⠀\n" +
                    "⠀⠀⢸⠀⠀⠀⠀⠀⠈⠻⢿⣿⣿⣿⡿⠛⠁⠀⠀⠀⠀⠀⠈⠉⢙⣗⣲⣤⣤⣀⡀⠀⠀⠀⡸⠀⠀\n" +
                    "⠀⢀⡟⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⠋⠉⠉⠙⠻⠿⣜⠃⠵⢲⣃⠀⠀\n" +
                    "⣰⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢛⣷⣶⣤⠀⠀⠀⠘⡆⠀⠀⠀⠀⠀\n");
            int del=sc.nextInt();
            String sql="DELETE FROM RoomInfo WHERE house_num=?";
            try{
                conn= Common.getConnection();
                pStmt=conn.prepareStatement(sql);
                pStmt.setInt(1,del);
                int rst = pStmt.executeUpdate();
                System.out.println("rst : "+ rst);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                Common.close(pStmt);
                Common.close(conn);
            }
        }
    
        public void AdminSelectPrn(List<AdminInfoVo> list) {
            for (AdminInfoVo e : list) {
                System.out.print(e.getADM_ID() + " ");
                System.out.println(e.getADM_PW());
                System.out.println(blue + "========================================================================" + exit);
            }
        }
    
        public void RoomSelcetPrn(List<RoomInfoVo> list) {
            for (RoomInfoVo e : list) {
                System.out.print(e.getHouse_num() + " ");
                System.out.print(e.getUser_id() + " ");
                System.out.print(e.getPhoto_url() + " ");
                System.out.print(e.getTrade_method() + " ");
                System.out.print(e.getDeposit() + " ");
                System.out.print(e.getMonthly() + " ");
                System.out.print(e.getJeonsegeum() + " ");
                System.out.print(e.getSale_price() + " ");
                System.out.print(e.getArea() + " ");
                System.out.print(e.getAccept_date() + " ");
                System.out.print(e.getAddress() + " ");
                System.out.print(e.getFloor1() + " ");
                System.out.print(e.getPhonenumber() + " ");
                System.out.print(e.getRegit_date() + " ");
                System.out.println(e.getRemark());
                System.out.println(blue + "========================================================================" + exit);
            }
    
        }
        public void AdminInsertfunc(AdminInfoVo AIV) {
            String sql = "INSERT INTO ADMIN_INFO(ADM_ID,ADM_PW) VALUES (?,?)";
            try {
                conn = Common.getConnection();
                pStmt = conn.prepareStatement(sql);
                pStmt.setString(1, AIV.getADM_ID());
                pStmt.setString(2, AIV.getADM_PW());
                pStmt.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Common.close(pStmt);
            Common.close(conn);
        }
        public void AdminDeletefunc(AdminInfoVo AIV){
            String sql="DELETE FROM ADMIN_INFO WHERE ADM_ID=? AND ADM_PW=?";
            try{
                conn = Common.getConnection();
                pStmt = conn.prepareStatement(sql);
                pStmt.setString(1,AIV.getADM_ID());
                pStmt.setString(2,AIV.getADM_PW());
                pStmt.executeUpdate();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                Common.close(pStmt);
                Common.close(conn);
            }
    
        }
        public void AdminDelete(String ID){
            String sql="DELETE FROM ADMIN_INFO WHERE ADM_ID=?";
            try{
                conn=Common.getConnection();
                pStmt=conn.prepareStatement(sql);
                pStmt.setString(1,ID);
                pStmt.executeUpdate();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                Common.close(pStmt);
                Common.close(conn);
            }
    
        }
    }
    
    ```
    

## MAIN

- OlumGollumMAIN
    
    작성자 : 이경섭
    
    ```java
    package 올룸골룸.OlumGollumMain;
    
    import 올룸골룸.Roominfo.RoominfoDao;
    import 올룸골룸.Roominfo.RoominfoMain;
    
    import java.text.ParseException;
    import java.util.Scanner;
    
    public class OlumGollumMain {
        public static void main(String[] args) throws ParseException {
            Scanner sc = new Scanner(System.in);
            RoominfoDao dao = new RoominfoDao();
            System.out.println("======================================================================");
            System.out.println("|                      Olumgollum_MainPage           |  [0]마이페이지  |");
            System.out.println("|                                                     ---------------|");
            System.out.println("|                    ------------------------                        |");
            System.out.println("|                   |       [1] 방 올룸       |                       |");
            System.out.println("|                    ------------------------                        |");
            System.out.println("|                                                                    |");
            System.out.println("|                    ------------------------                        |");
            System.out.println("|                   |       [2] 방 골룸       |                       |");
            System.out.println("|                    ------------------------                        |");
            System.out.println("|                                                    -----------------");
            System.out.println("|                                                    |  [3]강제 종료   |");
            System.out.println("======================================================================");
            System.out.print("원하시는 페이지를 선택해주세요 : ");
            int sel = sc.nextInt();
            switch (sel) {
                // 마이페이지로 이동
                case 0:
                    break;
                    // 방 올룸 페이지로 이동
                case 1:
                    RoominfoMain rm = new RoominfoMain();
                    rm.RoominfoMainfunc();
                    break;
                    // 방 골룸 페이지로 이동
                case 2:
                    break;
                case 3:
                    System.out.println(" 강제 종료합니다.");
                    break;
                default:
                    System.out.println("입력이 잘못 되었습니다.");
            }
        }
    }
    
    ```
    
- InfoMAIN
    
    작성자 김동환
    
    ```java
    package log;
    
    import log.VO.Log_Vo;
    import log.dao.Log_Dao;
    
    import java.util.List;
    import java.util.Scanner;
    
    public class login_main {
        public static void main(String[] args) {
            Scanner sc=new Scanner(System.in);
            Log_Dao dao=new Log_Dao();
            System.out.println("[1] 로그인 창 [2] 회원가입 [3] (임시) 회원 정보 조회 [4] 종료");
            int log=sc.nextInt();
            switch (log){
                case 1 :
                    dao.login();
                        break;
                case 2 :
                    dao.logInsert();
                    break;
                case 3:
                    List<Log_Vo> list=dao.logsel();
                    dao.logSelectPrn(list);
                case 4:
                    break;
                default:
                    System.out.println("번호를 잘못 입력함");
                    break;
            }
        }
    }
    
    ```
    
- RoomInfoMAIN
    
    작성자 : 이경섭
    
    ```java
    package 올룸골룸.Roominfo;
    
    import java.text.ParseException;
    import java.text.SimpleDateFormat;
    import java.util.Date;
    import java.util.Scanner;
    
    //방 올룸 페이지
    public class RoominfoMain {
        public void RoominfoMainfunc() throws ParseException {
            Scanner sc = new Scanner(System.in);
            RoominfoDao dao = new RoominfoDao();
            //방 데이터 입력 input함수 rvo 객체에 저장
            RoominfoVo rvo = dao.RoomInputFunc();
            while(true){
                System.out.print("[0]종료하기 [1]입력된 데이터 확인하기 [2]데이터 DB에 저장 : ");
                int sel = sc.nextInt();
                switch(sel) {
                    case 0:
                        System.out.println("종료합니다.");
                        break;
                    case 1:
                        //입력된 방 데이터 확인 함수 rvo 객체 출력
                        dao.RoomInputPrF(rvo);
                        break;
                    case 2:
                        //데이터베이스에 Insert하는 함수 rvo객체 INSERT
                        dao.RoomInsert(rvo);
                        break;
                    default:
                        System.out.println("잘못 입력했습니다.");
                }
                if(sel == 0) break;
            }
        }
    
    }
    
    ```
    
- RoomSearchMAIN
    
    작성자 : 김세호
    
    ```java
    package asdf_jdbc;
    
    import asdf_jdbc.dao.RoomInfoDao;
    import asdf_jdbc.vo.RoomInfoVo;
    
    import java.util.ArrayList;
    import java.util.List;
    import java.util.Scanner;
    
    public class Jdbc_Main {
            public static void main(String[] args) {
                Scanner sc = new Scanner(System.in);
                RoomInfoDao dao = new RoomInfoDao();
    
                while (true) {
                    System.out.print("메뉴 선택 : [1]방 정보 조회 [2]지역별 검색 [5]Quit : ");
                    int sel = sc.nextInt();
                    List<RoomInfoVo> list = new ArrayList<>();
                    switch (sel) {
                        case 1: // 전체 방 정보 조회
                            List<RoomInfoVo> allList = dao.roomSelect();
                            dao.roomSelectPrn(allList);
                            continue;
    
                        case 2: // 지역별 검색
                            System.out.println("지역별 검색을 시작합니다.");
                            list = dao.adressSearchSelect();
                            dao.roomSelectPrn(list);
    
                            System.out.println("거래검색을 하시겠습니까? [네,아니요]");
                            String str = sc.next();
                            if(str.equals("네")) {
    
                                list = dao.tradeSelect(list);
                                dao.roomSelectPrn(list);
                            }
                            System.out.println("평 수을 검색하시겠습니까? [네,아니요]");
                            String str2 = sc.next();
                            if(str2.equals("네")) {
    
                                List<RoomInfoVo> list3 = dao.areaTradeSelect(list);
                                dao.roomSelectPrn(list3);
                            }
                            System.out.println("층 수을 검색하시겠습니까? [네,아니요]");
                            String str3 = sc.next();
                            if(str3.equals("네")) {
                                List<RoomInfoVo> list4 = dao.floorTradeSelect(list);
                                dao.roomSelectPrn(list4);
                            }
                            System.out.println("다시 검색하시겠습니까?");
                            String str4 = sc.next();
                            if(str4.equals("네")) continue;
                            else break;
                        case 5:
                            System.out.println("프로그램을 종료합니다.");
                            break;
                        default:
                            System.out.println("메뉴를 잘못 선택했습니다.");
                            continue;
                    }
                    break;
                }
    ```
    
- MyPageMAIN
    
    작성자 : 서용욱
    
    ```java
    package 마이페이지.MAIN;
    
    import 마이페이지.DAO.My_PageDAO;
    import 마이페이지.VO.RoominfoVo;
    
    import java.util.ArrayList;
    import java.util.List;
    import java.util.Scanner;
    
    public class My_PageMain {
        //우리 아이디
        public static String myid = "user2@example.com";
    
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            My_PageDAO dao = new My_PageDAO();
            while(true){
                System.out.print("원하시는 기능을 선택해주세요. [1] 회원정보 수정 [2] 찜 목록보기 [3] 올룸 목록보기 [4] 나가기 : ");
                int num = sc.nextInt();
                switch (num) {
                    case 1:
                        // 개인정보 수정
                        dao.Userupdate();
                        break;
                    case 2:
                        // 찜목록
                        List<RoominfoVo> list = dao.Love_list();
                        //찜 리스트 출력
                        dao.loveprn(list);
                        System.out.println("[1]찜 삭제하기 [2]나가기");
                        int num1 = sc.nextInt();
                        switch (num1)
                        {
                            case 1:
                                // 찜 삭제
                                dao.Love_delete();
                                break;
                            case 2:
                                System.exit(0);
                            default:
                                System.out.println("다시 입력해주세요.");
                                continue;
                        }
                        break;
                    case 3:
                        //올룸 목록
                        List<RoominfoVo> list2 = dao.olume_list();
                        // 올룸 리스트 출력
                        dao.loveprn(list2);
                        System.out.println("[1]올룸 수정하기 [2]올룸 삭제하기 [3]나가기");
                        int num2 = sc.nextInt();
                        switch (num2)
                        {
                            case 1:
                                //올룸 수정하기
                                dao.RoomUpdate();
                                break;
                            case 2:
                                //올룸 삭제하기
                                dao.RoomDelete();
                                break;
                            case 3:
                                System.exit(0);
                            default:
                                System.out.println("다시 입력해주세요.");
                                continue;
                        }
                        break;
    
                    case 4:
                        System.exit(0);
                    default:
                        System.out.println("다시 입력해주세요.");
                        continue;
                }
                break;
            }
        }
    
    }
    
    ```
    
- AdminInfoMAIN
    
    작성자: 김동환
    
    ```java
    package admin_info;
    
    import Roominfo.Vo.RoominfoVo;
    import admin_info.dao.Admin_infoDao;
    import admin_info.vo.Admin_infoVo;
    import log.VO.Log_Vo;
    import log.dao.Log_Dao;
    
    import java.util.List;
    import java.util.Scanner;
    
    public class Admin_infoMain {
        public static void main(String[] args) {
            Scanner sc=new Scanner(System.in);
            Admin_infoDao dao=new Admin_infoDao();
            Log_Dao dao1=new Log_Dao();
               dao.Adminlogin();
                System.out.println("접속할 페이지 [1] 관리자 정보 [2] 회원정보 [3] 방정보 [4] 종료");
                int mana=sc.nextInt();
                switch (mana){
                    case 1:
                        System.out.println("[1] 관리자 조회 [2]관리자 추가 [3] 관리자 삭제 [4] 종료");
                        int ger=sc.nextInt();
                        if (ger==1){
                            List<Admin_infoVo> list =dao.admin_infoSelect();
                            dao.AdminSelectPrn(list);
                            break;
                        }
                        else if(ger==2){
                            dao.AdminInsert();
                            break;
                        }
                        else if(ger==3){
                            dao.AdminDelete();
                            break;
                        }
                        else if(ger==4){
                            break;
                        }
                        else {
                            System.out.println("번호를 잘못 입력 하였습니다.");
                        }
                    case 2:
                        System.out.println("[1] 회원 정보 조회 [2] 회원 정보 삭제 [3] 종료");
                        int mem=sc.nextInt();
                        if(mem==1){
                            List<Log_Vo> list=dao1.logsel();
                            dao1.logSelectPrn(list);
                            break;
                        }
                        else if(mem==2){
                            dao.LogDelete();
                            break;
                        }
                        else if (mem==3) {
                            break;
                        }
                        else {
                            System.out.println("번호를 잘못 입력 하였습니다.");
                        }
                    case 3:
                        System.out.println("[1] 방 조회 [2] 방 정보 수정 [3] 방 삭제 [3] 종료");
                        int ro = sc.nextInt();
                        if(ro==1){
                            List<RoominfoVo> list=dao.RoomSelect();
                            dao.RoomSelcetPrn(list);
                            break;
                        } else if(ro==2){
                            dao.RoomUpdate();
                            break;
                        } else if (ro==3) {
                            dao.RoomDelete();
                            break;
                        }
                }
        }
    }
    ```
    

# 8. Thymeleaf

## CONTROLLER

- AdminController
    
    ```java
    package com.kh.Olumgollum_Project.Controller;
    
    import com.kh.Olumgollum_Project.AdminInfo.AdminInfoDao;
    import com.kh.Olumgollum_Project.AdminInfo.AdminInfoVo;
    import com.kh.Olumgollum_Project.LoginInfo.LoginInfoDao;
    import com.kh.Olumgollum_Project.LoginInfo.LoginInfoVo;
    import com.kh.Olumgollum_Project.LoginInfo.LoginVo;
    import com.kh.Olumgollum_Project.MyPage.MyPageDao;
    import com.kh.Olumgollum_Project.RoomInfo.RoomInfoVo;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.ModelAttribute;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestMapping;
    
    import java.sql.SQLException;
    import java.util.List;
    @Controller
    @RequestMapping("/Admin")
    public class AdminController {
        @GetMapping("/UserList")
        public String UserSelectAll(Model model)
        {
            LoginInfoDao dao = new LoginInfoDao();
            List<LoginInfoVo> LIV = dao.logsel();
            model.addAttribute("UserList",LIV);
            return "AdminPageHTML/UserList";
        }
        @GetMapping("/UserDelete")
        public String UserDelete(Model model)
        {
            model.addAttribute("id",new LoginVo());
            return "AdminPageHTML/UserDelete";
        }
        @PostMapping("/UserDelete")
        public String UserDBdelete(@ModelAttribute("id") LoginVo LV, Model model)
        {
            MyPageDao dao = new MyPageDao();
            dao.UserDeletefunc(LV.getUserid());
            return "AdminPageHTML/UserDeleteRst";
        }
        @GetMapping("/RoomList")
        public String RoomSelectAll(Model model) throws SQLException {
            MyPageDao dao = new MyPageDao();
            List<RoomInfoVo> RIV = dao.RoomSelect();
            model.addAttribute("Rooms",RIV);
            return "AdminPageHTML/RoomList";
        }
        @GetMapping("/RoomDelete")
        public String RoomDelete(Model model)
        {
            model.addAttribute("RoomVo", new RoomInfoVo());
            return "AdminPageHTML/RoomDelete";
        }
        @PostMapping("/RoomDelete")
        public String RoomDBdelete(@ModelAttribute("RoomNum") RoomInfoVo RIV, Model model)
        {
            MyPageDao dao = new MyPageDao();
            dao.RoomDel(RIV.getHouse_num());
            return "AdminPageHTML/RoomDeleteRst";
        }
        @GetMapping("/AdminList")
        public String AdminList(Model model)
        {
            AdminInfoDao dao = new AdminInfoDao();
            List<AdminInfoVo> AIV = dao.admin_infoSelect();
            model.addAttribute("admin",AIV);
            return "AdminPageHTML/AdminList";
        }
        @GetMapping("/AdminInsert")
        public String AdminInsert(Model model)
        {
            model.addAttribute("AdminUp",new AdminInfoVo());
            return "AdminPageHTML/AdminInsert";
        }
        @PostMapping("/AdminInsert")
        public String AdminDBinsert(@ModelAttribute("AdminUp") AdminInfoVo AIV)
        {
    
            MyPageDao dao = new MyPageDao();
            AdminInfoDao AID = new AdminInfoDao();
            //유저 DB에 있으면 삭제한다.
            dao.UserDeletefunc(AIV.getADM_ID());
            //관리자 DB에 추가한다.
            AID.AdminInsertfunc(AIV);
            return "AdminPageHTML/AdminInsertRst";
        }
        @GetMapping("/AdminDelete")
        public String AdminDelete(Model model)
        {
            model.addAttribute("AdminDel",new AdminInfoVo());
            return "AdminPageHTML/AdminDelete";
        }
        @PostMapping("/AdminDelete")
        public String AdminDBDelete(@ModelAttribute("AdminDel") AdminInfoVo AIV)
        {
            AdminInfoDao dao = new AdminInfoDao();
            dao.AdminDeletefunc(AIV);
            return "AdminPageHTML/AdminDeleteRst";
        }
    }
    
    ```
    
- LoginComtroller
    
    ```java
    package com.kh.Olumgollum_Project.Controller;
    
    import com.kh.Olumgollum_Project.LoginInfo.LoginInfoDao;
    import com.kh.Olumgollum_Project.LoginInfo.LoginInfoVo;
    import com.kh.Olumgollum_Project.LoginInfo.LoginVo;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.ModelAttribute;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestMapping;
    
    import javax.servlet.http.HttpSession;
    import java.sql.SQLException;
    import java.util.List;
    
    @Controller
    @RequestMapping("/Login")
    public class LoginController {
        //회원가입
        @GetMapping("/UserInsert")
        public String UserInsert(Model model){
            model.addAttribute("UserInsert",new LoginInfoVo());
            return "CommonHTML/UserInsert";
        }
        @PostMapping("/UserInsert")
        public String UserInsert(@ModelAttribute("UserInsert") LoginInfoVo LIV)
        {
            LoginInfoDao dao = new LoginInfoDao();
            dao.UserInsert(LIV);
            return "CommonHTML/UserInsertRst";
    
        }
        // 사용자  로그인
        @GetMapping("/LoginCheck")
        public String LoginPage(Model model) {
            model.addAttribute("ID_PASSWORD", new LoginVo());
            return "CommonHTML/LoginPage";
        }
        //========================================여기까지는 일반과 관리자가 동일=======================================
        // 일반 사용자 인지 관리자 사용자인지 확인
        @PostMapping("/LoginCheck")
        public String SetLogin(@ModelAttribute("ID_PASSWORD") LoginVo User, HttpSession session) throws InterruptedException, SQLException, SQLException {
            //서버 공간에 정보를 저장합니다.
            LoginInfoDao LID = new LoginInfoDao();
            // 관리자 사용자 ID,PW DB에서 확인하는 부분
            boolean AdminCheck = LID.AdminLoginfunc(User);
            if(AdminCheck){
                //로그인 조건일시 서버에 id,pw 저장
                session.setAttribute("id", User.getUserid());
                session.setAttribute("grant","Admin");
                return "AdminHTML/AdminMainPage";
            }
            //관리자 사용자가 아닌 경우
            else {
                // 일반 사용자 DB에서 확인하는 부분
                boolean LoginCheck = LID.UserLoginfunc(User);
                //로그인 성공!
                if (LoginCheck) {
                    //로그인 조건일시 서버에 id,pw 저장
                    session.setAttribute("id", User.getUserid());
                    session.setAttribute("grant","User");
                    return "UserHTML/UserMainPage";
                }
                //로그인에 실패했을 때 실패 문구가 뜨는 로그인페이지로 이동.
                else return "redirect:/Login/FailedCheck"; //redirect는 controller에 해당 GetMapping으로 이동!
            }
        }
    
        // 로그인이 실패했을 경우 계속 실패했다는 문구입력(기능됩니다.ㅋㅋ)
        @GetMapping("/FailedCheck")
        public String FailedPage(Model model) {
            model.addAttribute("ID_PASSWORD", new LoginVo());
            return "CommonHTML/FailedLoginPage";
        }
    
        @PostMapping("/FailedCheck")
        public String FailedLogin(@ModelAttribute("ID_PASSWORD") LoginVo User, HttpSession session) throws InterruptedException, SQLException {
            //서버 공간에 정보를 저장합니다.
            LoginInfoDao LID = new LoginInfoDao();
            // 관리자 사용자 ID,PW DB에서 확인하는 부분
            boolean AdminCheck = LID.AdminLoginfunc(User);
            if(AdminCheck){
                //로그인 조건일시 서버에 id,pw 저장
                session.setAttribute("id", User.getUserid());
                session.setAttribute("grant","Admin");
                return "AdminHTML/AdminMainPage";
            }
            //관리자 사용자가 아닌 경우
            else {
                // 일반 사용자의 로그인 확인하는 부분
                boolean LoginCheck = LID.UserLoginfunc(User);
                //로그인 성공!
                if (LoginCheck){
                    //로그인 조건일시 서버에 id,pw 저장
                    session.setAttribute("id", User.getUserid());
                    session.setAttribute("grant","User");
                    return "UserHTML/UserMainPage";
                }
                //로그인에 실패했을 때 실패 문구가 뜨는 로그인페이지로 이동.
                else return "redirect:/Login/FailedCheck";
            }
        }
    }
    
    ```
    
- MyPageController
    
    ```java
    package com.kh.Olumgollum_Project.Controller;
    
    import com.kh.Olumgollum_Project.AdminInfo.AdminInfoDao;
    import com.kh.Olumgollum_Project.AdminInfo.AdminInfoVo;
    import com.kh.Olumgollum_Project.LoginInfo.InfoUpdateVo;
    import com.kh.Olumgollum_Project.LoginInfo.LoginInfoVo;
    import com.kh.Olumgollum_Project.LoginInfo.LoginVo;
    import com.kh.Olumgollum_Project.LoveRoom.LoveRoomVo;
    import com.kh.Olumgollum_Project.MyPage.MyPageDao;
    import com.kh.Olumgollum_Project.RoomInfo.RoomInfoVo;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.ModelAttribute;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestMapping;
    
    import javax.servlet.http.HttpSession;
    import java.sql.SQLException;
    import java.util.List;
    
    @Controller
    @RequestMapping("/MyPage")
    public class MyPageController {
        //회원정보 수정
        @GetMapping("/InfoUpdate")
        public String InfoUpdate(Model model) {
            model.addAttribute("UpdateElements", new InfoUpdateVo());
            return "MyPageHTML/InfoUpdate";
        }
        @PostMapping("/InfoUpdate")
        public String Update(@ModelAttribute("UpdateElements") InfoUpdateVo IUV,HttpSession session,Model model)
        {
    
            MyPageDao dao = new MyPageDao();
            String ID = (String) session.getAttribute("id");
            //나의 정보 불러오기
            LoginInfoVo LIV = dao.ExtractInfo(ID);
            //수정된값 덮어 씌우기
            LoginInfoVo UpdateLIV = dao.Overwrite(LIV,IUV);
            //덮어 씌운값 DB에 수정하기
            dao.InfoUpdate(UpdateLIV, ID);
            model.addAttribute("MyInfos",UpdateLIV);
            return "UserHTML/UserInfoSelect";
        }
        //관리자 계정 회원정보 수정
        @GetMapping("/AdminInfoUpdate")
        public String AdminInfoUpdate(Model model) {
            model.addAttribute("UpdateElements", new AdminInfoVo());
            return "MyPageHTML/AdminInfoUpdate";
        }
        @PostMapping("/AdminInfoUpdate")
        public String AdminUpdate(@ModelAttribute("UpdateElements") AdminInfoVo AIV,HttpSession session,Model model)
        {
            MyPageDao dao = new MyPageDao();
            String ID = (String) session.getAttribute("id");
            //나의 정보 불러오기
            AdminInfoVo aiv = dao.AdminExtractInfo(ID);
            //수정된값 덮어 씌우기
            AdminInfoVo UpdateAIV = dao.AdminOverwrite(aiv,AIV);
            //덮어 씌운값 DB에 수정하기
            dao.AdminInfoUpdate(UpdateAIV, ID);
            model.addAttribute("MyInfos",UpdateAIV);
            //돌아가기를 위한 권한 확인
            return "AdminHTML/AdminInfoSelect";
        }
    //    찜한방 리스트
        @GetMapping("/LoveRoomList")
        public String LoveRoomList(Model model, HttpSession session) {
            MyPageDao dao = new MyPageDao();
            String ID = (String) session.getAttribute("id");
            List<RoomInfoVo> RIV = dao.LoveList(ID);
            model.addAttribute("LoveRooms",RIV);
            String grant = (String) session.getAttribute("grant");
            if (grant.equals("Admin"))
            {
                return "MyPageHTML/AdminLoveRoomList";
            }
            else
                return "MyPageHTML/UserLoveRoomList";
        }
        // 찜한방 삭제(찜 취소)
        @GetMapping("/LoveRoomDelete")
        public String LoveRoomDelete(Model model) {
            model.addAttribute("Lovedel", new LoveRoomVo());
            return "MyPageHTML/LoveRoomDelete";
        }
        @PostMapping("/LoveRoomDelete")
        public String LoveRoomDBdelete(@ModelAttribute() LoveRoomVo LRV,HttpSession session)
        {
            MyPageDao dao = new MyPageDao();
            String ID = (String) session.getAttribute("id");
            LRV.setUser_id(ID);
            dao.LoveDelete(LRV);
            String grant = (String) session.getAttribute("grant");
            if (grant.equals("Admin"))
            {
                return "MyPageHTML/AdminLoveRoomDeleteRst";
            }
            else
                return "MyPageHTML/UserLoveRoomDeleteRst";
        }
        // 올린방 리스트
        @GetMapping("/OulmList")
        public String OulmList(Model model, HttpSession session) {
            MyPageDao dao = new MyPageDao();
            String ID = (String) session.getAttribute("id");
            List<RoomInfoVo> RIV = dao.OlumList(ID);
            model.addAttribute("OulmList", RIV);
            String grant = (String) session.getAttribute("grant");
            if (grant.equals("Admin"))
            {
                return "MyPageHTML/AdminOlumList";
            }
            else
                return "MyPageHTML/UserOlumList";
        }
        //올린방 수정
        @GetMapping("/OulmUpdate")
        public String OulmUpdate(Model model) {
            model.addAttribute("UpdateElements", new RoomInfoVo());
            return "MyPageHTML/OlumUpdate";
        }
        @PostMapping("/OulmUpdate")
        public String OulmBDupdate(@ModelAttribute() RoomInfoVo RIV,HttpSession session)
        {
            MyPageDao dao = new MyPageDao();
            //올룸 수정
            dao.OlumUpdate(RIV);
            //권한에 따른 이동
            String grant = (String) session.getAttribute("grant");
            if (grant.equals("Admin"))
            {
                return "MyPageHTML/AdminOulmUpdateRst";
            }
            else
                return "MyPageHTML/UserOulmUpdateRst";
        }
        //올린방 삭제
        @GetMapping("/OulmDelete")
        public String OulmDelete(Model model) {
            model.addAttribute("DeleteNum", new RoomInfoVo());
            return "MyPageHTML/OulmDelete";
        }
        @PostMapping("/OulmDelete")
        public String OulmDBdelete(@ModelAttribute() RoomInfoVo RIV,HttpSession session)
        {
            String ID = (String) session.getAttribute("id");
            MyPageDao dao = new MyPageDao();
            //관련된 찜 목록 삭제
            dao.LoveOlumDelete(RIV.getHouse_num());
            //올룸삭제
            dao.RoomDel(RIV.getHouse_num());
            //권한에 따른 이동
            String grant = (String) session.getAttribute("grant");
            if (grant.equals("Admin"))
            {
                return "MyPageHTML/AdminOulmDeleteRst";
            }
            else
                return "MyPageHTML/UserOulmDeleteRst";
        }
        //회원탈퇴
        @GetMapping("/UserDelete")
        public String UserDelete(Model model) {
            model.addAttribute("id", new LoginVo());
            return "MyPageHTML/AccountDelete";
        }
        @PostMapping("/UserDelete")
        public String UserDBdelete(@ModelAttribute() LoginVo RIV,HttpSession session)
        {
            String ID = (String) session.getAttribute("id");
            MyPageDao dao = new MyPageDao();
            AdminInfoDao AID = new AdminInfoDao();
            //찜 목록 삭제
            dao.LoveAllDelete(ID);
            //권한에 따른 삭제
            String grant = (String) session.getAttribute("grant");
            if (grant.equals("Admin"))
            {
                //관리자 계정 삭제
                AID.AdminDelete(ID);
                return "MyPageHTML/AdminDeleteRst";
            }
            else
            {
                //User 계정 삭제
                dao.UserDeletefunc(ID);
                return "MyPageHTML/UserDeleteRst";
            }
        }
    
    }
    
    ```
    
- PageController
    
    ```java
    package com.kh.Olumgollum_Project.Controller;
    
    import com.kh.Olumgollum_Project.LoginInfo.LoginInfoDao;
    import com.kh.Olumgollum_Project.LoginInfo.LoginVo;
    import com.kh.Olumgollum_Project.RoomInfo.RoomInfoDao;
    import com.kh.Olumgollum_Project.RoomInfo.RoomInfoVo;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.ModelAttribute;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.stereotype.Controller;
    
    import javax.servlet.http.HttpSession;
    
    @Controller
    @RequestMapping("/Page")
    public class PageController {
        //OlumGollum 첫화면
        @GetMapping("/FirstPage")
        public String FirstPage() {
            return "CommonHTML/FirstPage";
        }
    
        //일반 User 메인 페이지
        @GetMapping("/UserMainPage")
        public String MainPage() {
            return "UserHTML/UserMainPage";
        }
    
        //관리자 계정의 메인페이지
        @GetMapping("/AdminMainPage")
        public String AdminMainPage() {
    
            return "AdminHTML/AdminMainPage";
        }
        @GetMapping("/AdminMyPage")
        public String AdminMyPageMain(Model model){
    
            return "AdminHTML/AdminMyPage";
        }
        @GetMapping("/UserMyPage")
        public String UserMyPageMain(Model model){
    
            return "UserHTML/UserMyPage";
        }
        @GetMapping("/AdminManage")
        public String AdminManage(Model model){
    
            return "AdminHTML/AdminManagePage";
        }
    
    }
    ```
    
- RoomSearchController
    
    ```java
    package com.kh.Olumgollum_Project.Controller;
    import com.kh.Olumgollum_Project.RoomInfo.RoomInfoDao;
    import com.kh.Olumgollum_Project.RoomInfo.RoomInfoVo;
    import com.kh.Olumgollum_Project.RoomSearch.RoomSearchDao;
    import com.kh.Olumgollum_Project.RoomSearch.RoomSearchVo;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.ModelAttribute;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestMapping;
    
    import javax.servlet.http.HttpSession;
    import java.sql.SQLException;
    import java.util.List;
    
    @Controller
    @RequestMapping("/RoomSearch")
    public class RoomSearchController {
        @GetMapping("/Search")
        public String AdminRoomSearch(Model model) {
            //입력을 안하는 요소값은 null을 가격은 맥시멈 값을 부여
            RoomSearchVo RSV = new RoomSearchVo(null, null, 0, 0, 0, 0, null);
            model.addAttribute("SearchElement", RSV);
            return "CommonHTML/RoomSearch";
        }
    
        @PostMapping("/Search")
        public String RoomSelect(@ModelAttribute("SearchElement") RoomSearchVo RSV, HttpSession session, Model model) throws SQLException {
            RoomSearchDao dao = new RoomSearchDao();
            //주소가 포함된 것을 뽑아오는 부분
            List<RoomInfoVo> Rooms = dao.RoomSelect1(RSV);
            model.addAttribute("Rooms", Rooms);
            // 권한 확인
            String grant = (String) session.getAttribute("grant");
            if (grant.equals("Admin")) {
                return "AdminHTML/AdminRoomList";
            } else return "UserHTML/UserRoomList";
        }
    }
    
    ```
    
- RoomUpController
    
    ```java
    package com.kh.Olumgollum_Project.Controller;
    
    import com.kh.Olumgollum_Project.LoginInfo.LoginInfoDao;
    import com.kh.Olumgollum_Project.LoginInfo.LoginVo;
    import com.kh.Olumgollum_Project.RoomInfo.RoomInfoDao;
    import com.kh.Olumgollum_Project.RoomInfo.RoomInfoVo;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.ModelAttribute;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestMapping;
    
    import javax.servlet.http.HttpSession;
    
    @Controller
    @RequestMapping("/RoomUp")
    public class RoomUpController {
        //======================================방 올리기 페이지============================================
        @GetMapping("/RoomInsert")
        public String OlumPage(Model model) {
            // 데이터를 입력 받기 위해 빈 통을 모델에 추가
            model.addAttribute("RoomInfoVos", new RoomInfoVo());
            return "CommonHTML/RoomInfoOlumUp";
    
        }
        //일반 사용자의 방 등록후 돌아가기
        @PostMapping("/RoomInsert")
        public String OlumDBinsert(@ModelAttribute("RoomInfoVos") RoomInfoVo RIV, HttpSession session) {
            RoomInfoDao dao = new RoomInfoDao();
            LoginInfoDao LID = new LoginInfoDao();
            //로그인 할때 서버에 저장하고 있던 아이디 불러오기
            String ID = (String) session.getAttribute("id");
            // 방 정보에 아이디 추가
            RIV.setUser_id(ID);
            // 방 DB에 추가
            dao.RoomInsert(RIV);
            // User인지 Admin인지 확인(성공후 돌아갈 메인 페이지가 다릅니다.)
            // 서버에 저장하고 있던 권한 불러오기
            String grant =(String) session.getAttribute("grant");
            //확인을 위해 함수에 넘겨주기위한 LoginVo 형식에 ID,PW 값 입력
            //관리자 계정
            if(grant.equals("Admin")){
                //Admin의 성공화면으로 이동.
                return "AdminHTML/AdminRoomInfoRst";
            }
            //유저계정
            else {
                // 유저의 성공화면으로 이동.
                return  "UserHTML/UserRoomInfoRst";
            }
        }
    }
    
    ```
    

## COMMON

- Common
    
    ```java
    package com.kh.Olumgollum_Project.COMMON;
    
    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.ResultSet;
    import java.sql.Statement;
    public class Common{
        final static String ORACLE_URL = "jdbc:oracle:thin:@localhost:1521:xe";
        final static String ORACLE_ID = "OlumGollum";
        final static String ORACLE_PW = "1234";
        final static String ORACLE_DRV = "oracle.jdbc.driver.OracleDriver";
        public static Connection getConnection() {
            Connection conn = null;
            try {
                //드라이브
                Class.forName(ORACLE_DRV);
                // URL, ID , PASSWORD를 보고 CONNECT
                conn = DriverManager.getConnection(ORACLE_URL, ORACLE_ID, ORACLE_PW);
                System.out.println("Connection 성공");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return conn;
        }
        public static void close(Connection conn) {
            try {
                if(conn != null && !conn.isClosed()) {
                    conn.close();
                    System.out.println("Connection 해제 성공");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //STATEMENT에 대한 CLOSE문
        public static void close(Statement stmt) {
            try {
                if(stmt != null && !stmt.isClosed()) {
                    stmt.close();
    //                System.out.println("Statement 해제 성공");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //RESULTESET에 대한 CLOSE문
        public static void close(ResultSet rset) {
            try {
                if(rset != null && !rset.isClosed()) {
                    rset.close();
                    System.out.println("Result set 해제 성공");
                }
    
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    ```
    

## DAO

- AdminDao
    
    ```java
    package com.kh.Olumgollum_Project.AdminInfo;
    
    import com.kh.Olumgollum_Project.COMMON.Common;
    import com.kh.Olumgollum_Project.RoomInfo.RoomInfoVo;
    
    import javax.swing.*;
    import java.sql.*;
    import java.util.ArrayList;
    import java.util.Collections;
    import java.util.List;
    import java.util.Scanner;
    
    public class AdminInfoDao {
        Connection conn = null;
        Statement stmt = null; // create Statement 방식
        PreparedStatement pStmt = null; // Prepared Statment 방식
        ResultSet rs = null; // database 로 부터 결과를 받는 변수
        Scanner sc = new Scanner(System.in);
        public static final String black = "\u001B[30m";
        public static final String red = "\u001B[31m";
        public static final String green = "\u001B[32m";
        public static final String yellow = "\u001B[33m";
        public static final String blue = "\u001B[34m";
        public static final String purple = "\u001B[35m";
        public static final String cyan = "\u001B[36;m";
        public static final String white = "\u001B[37m";
        public static final String exit = "\u001B[0m";
    
        public List<String> Adminlogin() throws InterruptedException {
            List<String> adm_id_pw = new ArrayList<>();
            String AdId = null;
            String Adpwd = null;
            while (true) {
                System.out.println(blue + "========================================================================" + exit);
                System.out.print(red +
                        "⠀⠀⠀⠀⠀⠀⣀⠄⠒⠉⠉⠉⠉⠉⠉⠑⠂⢄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⠀ ⠀⠀⣀⠄⠒⠉⠉⠉⠉⠉⠉⠑⠂⢤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⢠⠞⢁⠀⡀⠀⠀⠀⠀⣀⣠⣀⡀⢠⠺⠧⢀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⠀⠀⢀⠞⢁⠀⡀⠀⠀⠀⠀⣄⣠⣀⡀⢠⠺⠷⢄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⢠⠃⡸⢚⠛⠇⠀⠀⠀⢰⠁⡀⠈⢣⡜⠀⠀⠀⠀⠈⠁⠐⠢⠤⢄⡀⠀⠀⠀ ⠀⠀⢠⠃⡸⢚⠛⠇⠀⠀⠀⢰⠋⡀⠈⢳⡜⠀⠀⠀⠀⠈⠁⠐⠂⠤⢄⡀⠀⠀\n" +
                        "⠀⠀⢀⠃⠀⣇⢀⣠⣧⣤⣦⣤⣜⣤⣀⡠⢮⠃⠀⠀         ⠀⠀⠈⢱⠀⠀⠀⠇⠀⣇⢀⣠⣧⣤⣦⣤⣼⣦⣀⡠⢮⠂⠀⠀       ⠀⠀⠈⢱\n" +
                        "⠀⠀⠸⠀⠀⢀⣿⣿⣿⣿⣿⣿⣿⣿⣆⠀⡔⠀   [1]     ⠀⠀⠀⠆⠀⠀⠸⠀⠀⢀⣿⣿⣿⣿⣿⣿⣿⣿⣆⠀⡔⠀    [2]    ⠀⠀⠀⠆\n" +
                        "⠀⠀⡄⠀⠀⠸⣿⣿⣿⣿⣿⣿⣿⣿⣿⢠   관리자 로그인 ⠰⠀⠀⠀⡄⠀⠀⠸⣿⣿⣿⣿⣿⣿⣿⣿⣿⢢⠁⠀⠀    종료    ⠀⠰⠀\n" +
                        "⠀⠀⡇⠀⠀⠀⠙⢿⣿⣿⣿⣿⣿⠟⠁⠈⠉⠓⠒⠦⠤⣀⡀⠀⠀  ⠀⠀⠆⠀⠀⠀⡇⠀⠀⠀⠙⠿⣿⣿⣿⣿⣿⠟⠁⠈⠉⠓⠒⠦⠤⣀⡀⠀⠀   ⠀⠀⠆⠀\n" +
                        "⠀⢀⠃⠀⠀⠀⠀⠀⠈⠛⠛⠋⠁⠀⠀⠀⠀⠀⠀⣠⠞⠓⠛⠿⣷⡶⢦⡤⠰⠀⠀⠀⢀⠇⠀⠀⠀⠀⠀⠈⠛⠛⠋⠁⠀⠀⠀⠀⠀⠀⢠⠖⠓⠛⠿⣷⡴⢦⡤⠰⠀⠀\n" +
                        "⡰⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠹⣦⣤⡄⠀⠀⠘⡄⠀⠈⠀⠀⡠⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⣦⣤⡄⠀⠀⠘⡄⠀⠉⠀⠀\n"+exit);
                System.out.println(blue + "========================================================================" + exit);
                System.out.print(purple + "   번호 입력 :  ");
                int lo = sc.nextInt();
                System.out.println(blue + "========================================================================" + exit);
                if (lo == 1) {
                    System.out.println(green+"  참고"+exit+" : 테스트를 위한 예시 아이디 : dyddnr33@naver.com 패스워드 : admin1691");
                    System.out.println(blue + "========================================================================" + exit);
                    System.out.print("  아이디 입력 : ");
                    AdId = sc.next();
                    System.out.println(blue + "========================================================================" + exit);
                    System.out.print("  비밀번호 입력 : ");
                    String password = sc.next();
                    System.out.println(blue + "========================================================================" + exit);
                    System.out.println(yellow + "                     로그인 중입니다. 잠시만 기다려주세요..." + exit);
                    System.out.println(blue + "========================================================================" + exit);
                    Thread.sleep(2000);
                    String sql = "SELECT ADM_PW FROM ADMIN_INFO WHERE ADM_ID= ? ";
                    try {
                        conn = Common.getConnection();
                        pStmt = conn.prepareStatement(sql);
                        pStmt.setString(1, AdId);
                        rs = pStmt.executeQuery(); // rs 객체에 쿼리 결과를 할당
    
                        if (rs.next()) {
                            Adpwd = rs.getString("ADM_PW");
                            if (Adpwd.equals(password)) {
                                adm_id_pw.add(AdId);
                                adm_id_pw.add(Adpwd);
                                adm_id_pw.add("Admin");
                                System.out.println("\n" +
                                        "⠀⠀⠀⠀⠀⠀⠀⢀⣴⣾⣿⡿⠛⠛⠛⢛⣽⣿⣿⣿⣦⣄⠀⠀⠀⠀⠀⠀\n" +
                                        "⠀⠀⠀⠀⠀⢀⣴⣿⣿⣿⣿⣿⣦⠀⢼⣾⣿⣿⠏⢿⣿⣿⡆⠀⠀⠀⠀⠀\n" +
                                        "⠀⠀⠀⠀⢠⣾⣿⣿⡏⠙⢿⣿⡿⠛⠀⠻⠿⠋⠁⠴⡽⣻⡅⠀⠀⠀⠀⠀\n" +
                                        "⠀⠀⠀⢰⣿⢿⣿⠏⠇⠀⠀⠀⠀⠀⠀⠀⢀⣠⣤⣄⣈⣈⣧⣀⠀⠀⠀⠀\n" +
                                        "⠀⠀⢠⣿⡿⠧⠂⢡⣤⣤⣤⣴⣶⡆⠠⢾⣿⢿⠛⢛⣋⣫⠤⠬⠟⠂⠀⠀\n" +
                                        "⠀⠀⢸⣿⡗⣮⣶⠶⡟⠛⣛⣛⣙⡱⠤⠞⠚⠚⠉⠉⠀⠀⠀⠀⠀⠀⠸⡄\n" +
                                        "⢀⡾⢉⣿⣷⠤⠔⠒⠒⠉⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇\n" +
                                        "⣸⠌⠸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢣\n" +
                                        "⣿⠺⢟⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣾\n" +
                                        "⠸⡑⢠⣵⣀⠀⠀⠀⠀⠀⠀로그인 성공   ⠀⠀⠀⠘⣿\n" +
                                        "⠀⠈⣿⡿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                                        "⡰⠚⠉⣿⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                                        "⡇⠀⢰⣿⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                                        "⠈⠛⠋⠀⠛⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                                        "⠀⠀⢀⠤⢤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⣤⣤⠀⠐⠂\n" +
                                        "⠀⢀⣏⣀⣠⡇⠀⠀⠀⠀⣀⣀⢠⣤⣤⣴⣶⣾⣿⣿⣿⣿⣿⣿⡿⠀⠀⠀\n");
                                System.out.println(blue + "========================================================================" + exit);
                                return adm_id_pw;
                            } else {
                                System.out.println(red + "    비밀번호가 틀렸습니다." + exit);
                            }
                        } else {
                            System.out.println(red + "    아이디를 잘못 입력했습니다." + exit);
                        }
    
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        Common.close(rs);
                        Common.close(pStmt);
                        Common.close(conn);
                    }
                } else {
                    System.out.println(red + "                           프로그램을 종료합니다." + exit);
                    Thread.sleep(1000);
                    System.exit(0);
                }
    
            }
        }
    
        public List<AdminInfoVo> admin_infoSelect() {
            List<AdminInfoVo> list = new ArrayList<>();
            try {
                conn = Common.getConnection();
                stmt = conn.createStatement();
                String sql = "SELECT * FROM ADMIN_INFO";
                rs = stmt.executeQuery(sql);
    
                while (rs.next()) {
                    String AdmId = rs.getString("ADM_ID");
                    String AdmPw = rs.getString("ADM_PW");
                    list.add(new AdminInfoVo(AdmId, AdmPw));
                }
                Common.close(rs);
                Common.close(stmt);
                Common.close(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
    
            return list;
        }
    
        public void AdminInsert() {
            System.out.println("아이디 입력");
            String ID = sc.next();
            System.out.println("비밀번호 입력");
            String pwd = sc.next();
            String sql = "INSERT INTO ADMIN_INFO(ADM_ID,ADM_PW) VALUES (?,?)";
    
            try {
                conn = Common.getConnection();
                pStmt = conn.prepareStatement(sql);
                pStmt.setString(1, ID);
                pStmt.setString(2, pwd);
                int rst = pStmt.executeUpdate();
                System.out.println("rst : " + rst);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Common.close(pStmt);
            Common.close(conn);
        }
    
        public void AdminDelete(){
            System.out.println("\n" +
                    "⠀⠀⠀⠀⠀⠀⠀⠀⣠⠴⠒⠚⠉⠉⠉⠉⠉⠛⠒⠲⢄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⠀⠀⠀⣴⠚⠁⠀⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀⠀⣩⣗⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⠀⢀⡞⢡⣤⣧⣾⠀⠀⠀⠀⠀⣸⠗⠛⠲⣇⢠⡃⠀⠈⠑⠒⠂⠤⢀⢀⠀⠀⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⢀⠾⠀⡞⠡⠄⠈⡇⠀⠀⠀⠀⡁⠀⠆⠀⢸⡾⠀⠀⠀⠀   ⠀⠀⠀⠈⠁⠂⠒⠤⢀⣀\n" +
                    "⠀⠀⠀⣸⠂⠀⢇⣀⣠⣾⣷⣾⣿⣶⣶⣿⡦⠤⠴⢫⡇⠀⠀⠀삭제하고자하는⠀⠀⠀⠘\n" +
                    "⠀⠀⠀⡇⠀⠀⢀⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⠀⡜⠀⠀   아이디 입력⠀⠀⠀⢀⠇\n" +
                    "⠀⠀⢠⡇⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢦⠇⠀⠀             ⠀⠀⢸⠀\n" +
                    "⠀⠀⢸⠀⠀⠀⠀⠻⣿⣿⣿⣿⣿⣿⣿⣿⡿⠟⠘⠦⠤⢤⣀⠀⠀⠀         ⠀⠀⠃⠀\n" +
                    "⠀⠀⢸⠀⠀⠀⠀⠀⠈⠻⢿⣿⣿⣿⡿⠛⠁⠀⠀⠀⠀⠀⠈⠉⢙⣗⣲⣤⣤⣀⡀⠀⠀⠀⡸⠀⠀\n" +
                    "⠀⢀⡟⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⠋⠉⠉⠙⠻⠿⣜⠃⠵⢲⣃⠀⠀\n" +
                    "⣰⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢛⣷⣶⣤⠀⠀⠀⠘⡆⠀⠀⠀⠀⠀\n");
            String del=sc.next();
            String sql="DELETE FROM ADMIN_INFO WHERE ADM_ID=?";
            try{
                conn=Common.getConnection();
                pStmt=conn.prepareStatement(sql);
                pStmt.setString(1,del);
                int rst = pStmt.executeUpdate();
                System.out.println("rst : "+ rst);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                Common.close(pStmt);
                Common.close(conn);
            }
    
        }
    
        public void LogDelete(){
            System.out.println("\n" +
                    "⠀⠀⠀⠀⠀⠀⠀⠀⣠⠴⠒⠚⠉⠉⠉⠉⠉⠛⠒⠲⢄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⠀⠀⠀⣴⠚⠁⠀⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀⠀⣩⣗⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⠀⢀⡞⢡⣤⣧⣾⠀⠀⠀⠀⠀⣸⠗⠛⠲⣇⢠⡃⠀⠈⠑⠒⠂⠤⢀⢀⠀⠀⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⢀⠾⠀⡞⠡⠄⠈⡇⠀⠀⠀⠀⡁⠀⠆⠀⢸⡾⠀⠀⠀⠀   ⠀⠀⠀⠈⠁⠂⠒⠤⢀⣀\n" +
                    "⠀⠀⠀⣸⠂⠀⢇⣀⣠⣾⣷⣾⣿⣶⣶⣿⡦⠤⠴⢫⡇⠀⠀⠀삭제하고자하는⠀⠀⠀⠘\n" +
                    "⠀⠀⠀⡇⠀⠀⢀⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⠀⡜⠀⠀   아이디 입력⠀⠀⠀⢀⠇\n" +
                    "⠀⠀⢠⡇⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢦⠇⠀⠀             ⠀⠀⢸⠀\n" +
                    "⠀⠀⢸⠀⠀⠀⠀⠻⣿⣿⣿⣿⣿⣿⣿⣿⡿⠟⠘⠦⠤⢤⣀⠀⠀⠀         ⠀⠀⠃⠀\n" +
                    "⠀⠀⢸⠀⠀⠀⠀⠀⠈⠻⢿⣿⣿⣿⡿⠛⠁⠀⠀⠀⠀⠀⠈⠉⢙⣗⣲⣤⣤⣀⡀⠀⠀⠀⡸⠀⠀\n" +
                    "⠀⢀⡟⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⠋⠉⠉⠙⠻⠿⣜⠃⠵⢲⣃⠀⠀\n" +
                    "⣰⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢛⣷⣶⣤⠀⠀⠀⠘⡆⠀⠀⠀⠀⠀\n");
            String del=sc.next();
            String sql="DELETE FROM INFO WHERE USER_MAIL=?";
            try{
                conn= Common.getConnection();
                pStmt=conn.prepareStatement(sql);
                pStmt.setString(1,del);
                int rst = pStmt.executeUpdate();
                System.out.println("rst : "+ rst);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                Common.close(pStmt);
                Common.close(conn);
            }
        }
    
        public List<RoomInfoVo> RoomSelect() {
            List<RoomInfoVo> list = new ArrayList<>();
            try {
                conn = Common.getConnection();
                stmt = conn.createStatement();
                String sql = "SELECT * FROM RoomInfo";
                rs = stmt.executeQuery(sql);
    
                while (rs.next()) {
                    int house_num = rs.getInt("HOUSE_NUM");
                    String user_id = rs.getString("user_id");
                    String photo_url = rs.getString("photo_url");
                    String trade_method = rs.getString("trade_method");
                    int deposit = rs.getInt("deposit");
                    int monthly = rs.getInt("monthly");
                    int jeonsegeum = rs.getInt("jeonsegeum");
                    int Sale_price = rs.getInt("Sale_price");
                    String area = rs.getString("area");
                    Date accept_date = rs.getDate("accept_date");
                    String address = rs.getString("address");
                    String floor1 = rs.getString("floor1");
                    int phonenumber = rs.getInt("phonenumber");
                    Date regit_date = rs.getDate("regit_date");
                    String remark = rs.getString("remark");
                    list.add(new RoomInfoVo(house_num, user_id, photo_url, trade_method, deposit, monthly, jeonsegeum, Sale_price, area, accept_date, address, floor1, phonenumber, regit_date, remark));
                }
                Common.close(rs);
                Common.close(stmt);
                Common.close(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return list;
        }
    
        public void RoomUpdate(){
            System.out.println(blue + "========================================================================" + exit);
            System.out.println(blue +
                    "|   —̳͟͞͞ \uD83D\uDC97     _____ __ __    _____ _____ _____ _____      \uD83C\uDF38        |\n" +
                    "|               |     |  |  |  |  _  |  _  |   __|   __|               |\n" +
                    "|         \uD83C\uDF38    | | | |_   _|  |   __|     |  |  |   __|   —̳͟͞͞ \uD83D\uDC97    |\n" +
                    "|               |_|_|_| |_|    |__|  |__|__|_____|_____|               |\n" +
                    "|         —̳͟͞͞ \uD83D\uDC97                                 —̳͟͞͞ \uD83D\uDC97            |");
            System.out.println(blue + "|======================================================================|" + exit);
            System.out.println(blue + "| \uD83C\uDF26\uFE0F     ------------------              ------------------            |" + exit);
            System.out.println(blue + "|        |" + exit + purple+"[1]   방 사진     "+exit + blue + "|     \uD83C\uDF38     |" + exit + purple+"[2] 거래방식(가격) "+exit + blue + "|            |" + exit);
            System.out.println(blue + "|        ------------------              ------------------            |" + exit);
            System.out.println(blue + "|        ------------------              ------------------            |" + exit);
            System.out.println(blue + "| \uD83C\uDF38     |" + exit + purple+"[3]   전화번호    "+exit + blue + "|   \uD83C\uDF26\uFE0F       |" + exit + purple+"[4]     비고      "+exit + blue + "| \uD83C\uDF38        |" + exit);
            System.out.println(blue + "|        ------------------              ------------------            |" + exit);
            System.out.println(blue + "|        ------------------                                            |" + exit);
            System.out.println(blue + "|        |" + exit + purple+"[5]    나가기     "+exit + blue + "|         |￣￣￣￣￣￣￣|     " + red + "   ☁\uFE0F   ⭐  " + blue + "    |");
            System.out.println(blue + "|        ------------------         |  선택하쇼   |     " + green + "       \uD83C\uDF38\uD83C\uDF38 " + blue + "    |");
            System.out.println(blue + "|                   ☁\uFE0F              |＿＿＿＿＿＿＿|  " + green + "  ☁\uFE0F     \uD83C\uDF38\uD83C\uDF38\uD83C\uDF38    " + blue + "|");
            System.out.println(blue + "|                                   (\\__/) || " + green + "             \uD83C\uDF38\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38   " + blue + "|");
            System.out.println(blue + "|                                   (•ㅅ•).||  " + green + "                \uD83C\uDF38      " + blue + "|");
            System.out.println(blue + "|       ☁\uFE0F          \uD83C\uDF26\uFE0F              / . . . .づ  " + green + "                      " + blue + "|");
            System.out.println(blue + "========================================================================" + exit);
            System.out.print(blue + "|" + exit + yellow + "    선택하쇼   " + exit  + blue + "|     " + exit);
            int up = sc.nextInt();
            System.out.println(blue + "|======================================================================|" + exit);
            String sql = null;
            int honum;
            switch (up) {
                case 1:
                    System.out.println("바꾸고자 하는 매물번호");
                    honum=sc.nextInt();
                    System.out.println("수정 사진 등록");
                    String url = sc.next();
                    sql = "UPDATE ROOMINFO SET photo_url = ? WHERE house_num=?";
                    try{
                        conn=Common.getConnection();
                        pStmt = conn.prepareStatement(sql);
                        pStmt.setString(1,url);
                        pStmt.setInt(2,honum);
                        int rst = pStmt.executeUpdate();
                        System.out.println("rst : " + rst);
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        Common.close(pStmt);
                        Common.close(conn);
                    }
                    break;
                case 2:
                    System.out.println("바꾸고자 하는 매물번호");
                    honum=sc.nextInt();
                    System.out.println(blue + "========================================================================" + exit);
                    System.out.println(blue + "|" + exit + "                       " + purple + "원하시는 기능을 골라주세요~!" + exit + "                        " + blue + "|" + exit);
                    System.out.println(blue + "|======================================================================|" + exit);
                    System.out.println(blue + "|                                                                      |" + exit);
                    System.out.println(blue + "|        ------------------              ------------------            |" + exit);
                    System.out.println(blue + "|       |" + exit + "   [1]매매   " + blue + "|            |" + exit + "   [3] 월세  " + blue + "|           |" + exit);
                    System.out.println(blue + "|        ------------------              ------------------            |" + exit);
                    System.out.println(blue + "|                                                                      |" + exit);
                    System.out.println(blue + "|                                                                      |" + exit);
                    System.out.println(blue + "|        ------------------              ------------------            |" + exit);
                    System.out.println(blue + "|       |" + exit + "   [2] 전세    " + blue + "|             |" + exit + "    [4]종료     " + blue + "|            |" + exit);
                    System.out.println(blue + "|        ------------------              ------------------            |" + exit);
                    System.out.println(blue + "|                                                                      |" + exit);
                    System.out.println(blue + "========================================================================" + exit);
                    int tr=sc.nextInt();
                    if(tr==4){
                        break;
                    }
                    sql = "UPDATE ROOMINFO SET deposit=NULL,monthly=NULL,jeonsegeum=NULL,Sale_price=NULL WHERE house_num=?";
                    try{
                        conn=Common.getConnection();
                        pStmt = conn.prepareStatement(sql);
                        pStmt.setInt(1,honum);
                        int rst = pStmt.executeUpdate();
                        System.out.println("rst : " + rst);
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        Common.close(pStmt);
                        Common.close(conn);
                    }
    
                    switch (tr){
                        case 1:
                            System.out.println("\n" +
                                    "⠀⠀⠀⠀⠀⠀⠀⠀⣠⠴⠒⠚⠉⠉⠉⠉⠉⠛⠒⠲⢄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                                    "⠀⠀⠀⠀⠀⠀⣴⠚⠁⠀⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀⠀⣩⣗⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                                    "⠀⠀⠀⠀⢀⡞⢡⣤⣧⣾⠀⠀⠀⠀⠀⣸⠗⠛⠲⣇⢠⡃⠀⠈⠑⠒⠂⠤⢀⢀⠀⠀⠀⠀⠀⠀⠀\n" +
                                    "⠀⠀⠀⢀⠾⠀⡞⠡⠄⠈⡇⠀⠀⠀⠀⡁⠀⠆⠀⢸⡾⠀⠀⠀⠀   ⠀⠀⠀⠈⠁⠂⠒⠤⢀⣀\n" +
                                    "⠀⠀⠀⣸⠂⠀⢇⣀⣠⣾⣷⣾⣿⣶⣶⣿⡦⠤⠴⢫⡇⠀⠀             ⠀⠀⠘\n" +
                                    "⠀⠀⠀⡇⠀⠀⢀⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⠀⡜⠀⠀  매매 가격 입력⠀⠀⢀⠇\n" +
                                    "⠀⠀⢠⡇⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢦⠇⠀⠀             ⠀⠀⢸⠀\n" +
                                    "⠀⠀⢸⠀⠀⠀⠀⠻⣿⣿⣿⣿⣿⣿⣿⣿⡿⠟⠘⠦⠤⢤⣀⠀⠀⠀         ⠀⠀⠃⠀\n" +
                                    "⠀⠀⢸⠀⠀⠀⠀⠀⠈⠻⢿⣿⣿⣿⡿⠛⠁⠀⠀⠀⠀⠀⠈⠉⢙⣗⣲⣤⣤⣀⡀⠀⠀⠀⡸⠀⠀\n" +
                                    "⠀⢀⡟⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⠋⠉⠉⠙⠻⠿⣜⠃⠵⢲⣃⠀⠀\n" +
                                    "⣰⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢛⣷⣶⣤⠀⠀⠀⠘⡆⠀⠀⠀⠀⠀\n");
                            int sal=sc.nextInt();
                            sql = "UPDATE ROOMINFO SET trade_method=?,Sale_price=? WHERE house_num=?";
                            try{
                                conn=Common.getConnection();
                                pStmt = conn.prepareStatement(sql);
                                pStmt.setString(1,"매매");
                                pStmt.setInt(2,sal);
                                pStmt.setInt(3,honum);
                                int rst = pStmt.executeUpdate();
                                System.out.println("rst : " + rst);
                            }catch (Exception e){
                                e.printStackTrace();
                            }finally {
                                Common.close(pStmt);
                                Common.close(conn);
                            }
                            break;
                        case 2:
                            System.out.println("\n" +
                                    "⠀⠀⠀⠀⠀⠀⠀⠀⣠⠴⠒⠚⠉⠉⠉⠉⠉⠛⠒⠲⢄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                                    "⠀⠀⠀⠀⠀⠀⣴⠚⠁⠀⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀⠀⣩⣗⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                                    "⠀⠀⠀⠀⢀⡞⢡⣤⣧⣾⠀⠀⠀⠀⠀⣸⠗⠛⠲⣇⢠⡃⠀⠈⠑⠒⠂⠤⢀⢀⠀⠀⠀⠀⠀⠀⠀\n" +
                                    "⠀⠀⠀⢀⠾⠀⡞⠡⠄⠈⡇⠀⠀⠀⠀⡁⠀⠆⠀⢸⡾⠀⠀⠀⠀   ⠀⠀⠀⠈⠁⠂⠒⠤⢀⣀\n" +
                                    "⠀⠀⠀⣸⠂⠀⢇⣀⣠⣾⣷⣾⣿⣶⣶⣿⡦⠤⠴⢫⡇⠀⠀             ⠀⠀⠘\n" +
                                    "⠀⠀⠀⡇⠀⠀⢀⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⠀⡜⠀⠀  전세 가격 입력⠀⠀⢀⠇\n" +
                                    "⠀⠀⢠⡇⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢦⠇⠀⠀             ⠀⠀⢸⠀\n" +
                                    "⠀⠀⢸⠀⠀⠀⠀⠻⣿⣿⣿⣿⣿⣿⣿⣿⡿⠟⠘⠦⠤⢤⣀⠀⠀⠀         ⠀⠀⠃⠀\n" +
                                    "⠀⠀⢸⠀⠀⠀⠀⠀⠈⠻⢿⣿⣿⣿⡿⠛⠁⠀⠀⠀⠀⠀⠈⠉⢙⣗⣲⣤⣤⣀⡀⠀⠀⠀⡸⠀⠀\n" +
                                    "⠀⢀⡟⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⠋⠉⠉⠙⠻⠿⣜⠃⠵⢲⣃⠀⠀\n" +
                                    "⣰⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢛⣷⣶⣤⠀⠀⠀⠘⡆⠀⠀⠀⠀⠀\n");
                            int jeo=sc.nextInt();
                            sql = "UPDATE ROOMINFO SET trade_method=?,jeonsegeum=? WHERE house_num=?";
                            try{
                                conn=Common.getConnection();
                                pStmt = conn.prepareStatement(sql);
                                pStmt.setString(1,"전세");
                                pStmt.setInt(2,jeo);
                                pStmt.setInt(3,honum);
                                int rst = pStmt.executeUpdate();
                                System.out.println("rst : " + rst);
                            }catch (Exception e){
                                e.printStackTrace();
                            }finally {
                                Common.close(pStmt);
                                Common.close(conn);
                            }
                            break;
                        case 3:
                            System.out.println("\n" +
                                    "⠀⠀⠀⠀⠀⠀⠀⠀⣠⠴⠒⠚⠉⠉⠉⠉⠉⠛⠒⠲⢄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                                    "⠀⠀⠀⠀⠀⠀⣴⠚⠁⠀⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀⠀⣩⣗⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                                    "⠀⠀⠀⠀⢀⡞⢡⣤⣧⣾⠀⠀⠀⠀⠀⣸⠗⠛⠲⣇⢠⡃⠀⠈⠑⠒⠂⠤⢀⢀⠀⠀⠀⠀⠀⠀⠀\n" +
                                    "⠀⠀⠀⢀⠾⠀⡞⠡⠄⠈⡇⠀⠀⠀⠀⡁⠀⠆⠀⢸⡾⠀⠀⠀⠀   ⠀⠀⠀⠈⠁⠂⠒⠤⢀⣀\n" +
                                    "⠀⠀⠀⣸⠂⠀⢇⣀⣠⣾⣷⣾⣿⣶⣶⣿⡦⠤⠴⢫⡇⠀⠀             ⠀⠀⠘\n" +
                                    "⠀⠀⠀⡇⠀⠀⢀⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⠀⡜⠀월세 보증금 가격 입력⠀⠇\n" +
                                    "⠀⠀⢠⡇⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢦⠇⠀⠀             ⠀⠀⢸⠀\n" +
                                    "⠀⠀⢸⠀⠀⠀⠀⠻⣿⣿⣿⣿⣿⣿⣿⣿⡿⠟⠘⠦⠤⢤⣀⠀⠀⠀         ⠀⠀⠃⠀\n" +
                                    "⠀⠀⢸⠀⠀⠀⠀⠀⠈⠻⢿⣿⣿⣿⡿⠛⠁⠀⠀⠀⠀⠀⠈⠉⢙⣗⣲⣤⣤⣀⡀⠀⠀⠀⡸⠀⠀\n" +
                                    "⠀⢀⡟⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⠋⠉⠉⠙⠻⠿⣜⠃⠵⢲⣃⠀⠀\n" +
                                    "⣰⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢛⣷⣶⣤⠀⠀⠀⠘⡆⠀⠀⠀⠀⠀\n");
                            int dep=sc.nextInt();
                            System.out.println("\n" +
                                    "⠀⠀⠀⠀⠀⠀⠀⠀⣠⠴⠒⠚⠉⠉⠉⠉⠉⠛⠒⠲⢄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                                    "⠀⠀⠀⠀⠀⠀⣴⠚⠁⠀⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀⠀⣩⣗⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                                    "⠀⠀⠀⠀⢀⡞⢡⣤⣧⣾⠀⠀⠀⠀⠀⣸⠗⠛⠲⣇⢠⡃⠀⠈⠑⠒⠂⠤⢀⢀⠀⠀⠀⠀⠀⠀⠀\n" +
                                    "⠀⠀⠀⢀⠾⠀⡞⠡⠄⠈⡇⠀⠀⠀⠀⡁⠀⠆⠀⢸⡾⠀⠀⠀⠀   ⠀⠀⠀⠈⠁⠂⠒⠤⢀⣀\n" +
                                    "⠀⠀⠀⣸⠂⠀⢇⣀⣠⣾⣷⣾⣿⣶⣶⣿⡦⠤⠴⢫⡇⠀⠀             ⠀⠀⠘\n" +
                                    "⠀⠀⠀⡇⠀⠀⢀⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⠀⡜⠀⠀  월세 가격 입력⠀⠀⢀⠇\n" +
                                    "⠀⠀⢠⡇⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢦⠇⠀⠀             ⠀⠀⢸⠀\n" +
                                    "⠀⠀⢸⠀⠀⠀⠀⠻⣿⣿⣿⣿⣿⣿⣿⣿⡿⠟⠘⠦⠤⢤⣀⠀⠀⠀         ⠀⠀⠃⠀\n" +
                                    "⠀⠀⢸⠀⠀⠀⠀⠀⠈⠻⢿⣿⣿⣿⡿⠛⠁⠀⠀⠀⠀⠀⠈⠉⢙⣗⣲⣤⣤⣀⡀⠀⠀⠀⡸⠀⠀\n" +
                                    "⠀⢀⡟⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⠋⠉⠉⠙⠻⠿⣜⠃⠵⢲⣃⠀⠀\n" +
                                    "⣰⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢛⣷⣶⣤⠀⠀⠀⠘⡆⠀⠀⠀⠀⠀\n");
                            int mon=sc.nextInt();
                            sql = "UPDATE ROOMINFO SET trade_method=?,deposit=?,monthly=? WHERE house_num=?";
                            try{
                                conn=Common.getConnection();
                                pStmt = conn.prepareStatement(sql);
                                pStmt.setString(1,"월세");
                                pStmt.setInt(2,dep);
                                pStmt.setInt(3,mon);
                                pStmt.setInt(4,honum);
                                int rst = pStmt.executeUpdate();
                                System.out.println("rst : " + rst);
                            }catch (Exception e){
                                e.printStackTrace();
                            }finally {
                                Common.close(pStmt);
                                Common.close(conn);
                            }
                            break;
                    }
                    break;
                case 3:
                    System.out.println("\n" +
                            "⠀⠀⠀⠀⠀⠀⠀⠀⣠⠴⠒⠚⠉⠉⠉⠉⠉⠛⠒⠲⢄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                            "⠀⠀⠀⠀⠀⠀⣴⠚⠁⠀⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀⠀⣩⣗⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                            "⠀⠀⠀⠀⢀⡞⢡⣤⣧⣾⠀⠀⠀⠀⠀⣸⠗⠛⠲⣇⢠⡃⠀⠈⠑⠒⠂⠤⢀⢀⠀⠀⠀⠀⠀⠀⠀\n" +
                            "⠀⠀⠀⢀⠾⠀⡞⠡⠄⠈⡇⠀⠀⠀⠀⡁⠀⠆⠀⢸⡾⠀⠀⠀⠀   ⠀⠀⠀⠈⠁⠂⠒⠤⢀⣀\n" +
                            "⠀⠀⠀⣸⠂⠀⢇⣀⣠⣾⣷⣾⣿⣶⣶⣿⡦⠤⠴⢫⡇⠀⠀바꾸고자 하는 ⠀⠀⠘\n" +
                            "⠀⠀⠀⡇⠀⠀⢀⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⠀⡜⠀⠀  매물 번호 입력⠀⠀⢀⠇\n" +
                            "⠀⠀⢠⡇⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢦⠇⠀⠀             ⠀⠀⢸⠀\n" +
                            "⠀⠀⢸⠀⠀⠀⠀⠻⣿⣿⣿⣿⣿⣿⣿⣿⡿⠟⠘⠦⠤⢤⣀⠀⠀⠀         ⠀⠀⠃⠀\n" +
                            "⠀⠀⢸⠀⠀⠀⠀⠀⠈⠻⢿⣿⣿⣿⡿⠛⠁⠀⠀⠀⠀⠀⠈⠉⢙⣗⣲⣤⣤⣀⡀⠀⠀⠀⡸⠀⠀\n" +
                            "⠀⢀⡟⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⠋⠉⠉⠙⠻⠿⣜⠃⠵⢲⣃⠀⠀\n" +
                            "⣰⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢛⣷⣶⣤⠀⠀⠀⠘⡆⠀⠀⠀⠀⠀\n");
                    honum=sc.nextInt();
                    System.out.println("수정 하고자 하는 전화번호");
                    int phnum=sc.nextInt();
                    sql = "UPDATE ROOMINFO SET phonenumber = ? WHERE house_num=?";
                    try{
                        conn=Common.getConnection();
                        pStmt = conn.prepareStatement(sql);
                        pStmt.setInt(1,phnum);
                        pStmt.setInt(2,honum);
                        int rst = pStmt.executeUpdate();
                        System.out.println("rst : " + rst);
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        Common.close(pStmt);
                        Common.close(conn);
                    }
                    break;
                case 4:
                    System.out.println("\n" +
                            "⠀⠀⠀⠀⠀⠀⠀⠀⣠⠴⠒⠚⠉⠉⠉⠉⠉⠛⠒⠲⢄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                            "⠀⠀⠀⠀⠀⠀⣴⠚⠁⠀⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀⠀⣩⣗⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                            "⠀⠀⠀⠀⢀⡞⢡⣤⣧⣾⠀⠀⠀⠀⠀⣸⠗⠛⠲⣇⢠⡃⠀⠈⠑⠒⠂⠤⢀⢀⠀⠀⠀⠀⠀⠀⠀\n" +
                            "⠀⠀⠀⢀⠾⠀⡞⠡⠄⠈⡇⠀⠀⠀⠀⡁⠀⠆⠀⢸⡾⠀⠀⠀⠀   ⠀⠀⠀⠈⠁⠂⠒⠤⢀⣀\n" +
                            "⠀⠀⠀⣸⠂⠀⢇⣀⣠⣾⣷⣾⣿⣶⣶⣿⡦⠤⠴⢫⡇⠀⠀바꾸고자 하는 ⠀⠀⠘\n" +
                            "⠀⠀⠀⡇⠀⠀⢀⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⠀⡜⠀⠀  매물 번호 입력⠀⠀⢀⠇\n" +
                            "⠀⠀⢠⡇⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢦⠇⠀⠀             ⠀⠀⢸⠀\n" +
                            "⠀⠀⢸⠀⠀⠀⠀⠻⣿⣿⣿⣿⣿⣿⣿⣿⡿⠟⠘⠦⠤⢤⣀⠀⠀⠀         ⠀⠀⠃⠀\n" +
                            "⠀⠀⢸⠀⠀⠀⠀⠀⠈⠻⢿⣿⣿⣿⡿⠛⠁⠀⠀⠀⠀⠀⠈⠉⢙⣗⣲⣤⣤⣀⡀⠀⠀⠀⡸⠀⠀\n" +
                            "⠀⢀⡟⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⠋⠉⠉⠙⠻⠿⣜⠃⠵⢲⣃⠀⠀\n" +
                            "⣰⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢛⣷⣶⣤⠀⠀⠀⠘⡆⠀⠀⠀⠀⠀\n");
                    honum=sc.nextInt();
                    sc.nextLine();
                    System.out.println("수정 할 비고");
                    String rem=sc.nextLine();
                    sql = "UPDATE ROOMINFO SET remark = ? WHERE house_num=?";
                    try{
                        conn=Common.getConnection();
                        pStmt = conn.prepareStatement(sql);
                        pStmt.setString(1,rem);
                        pStmt.setInt(2,honum);
                        int rst = pStmt.executeUpdate();
                        System.out.println("rst : " + rst);
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        Common.close(pStmt);
                        Common.close(conn);
                    }
                    break;
                case 5:
                    break;
            }
        }
    
        public void RoomDelete(){
            System.out.println("\n" +
                    "⠀⠀⠀⠀⠀⠀⠀⠀⣠⠴⠒⠚⠉⠉⠉⠉⠉⠛⠒⠲⢄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⠀⠀⠀⣴⠚⠁⠀⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀⠀⣩⣗⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⠀⢀⡞⢡⣤⣧⣾⠀⠀⠀⠀⠀⣸⠗⠛⠲⣇⢠⡃⠀⠈⠑⠒⠂⠤⢀⢀⠀⠀⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⢀⠾⠀⡞⠡⠄⠈⡇⠀⠀⠀⠀⡁⠀⠆⠀⢸⡾⠀⠀⠀⠀   ⠀⠀⠀⠈⠁⠂⠒⠤⢀⣀\n" +
                    "⠀⠀⠀⣸⠂⠀⢇⣀⣠⣾⣷⣾⣿⣶⣶⣿⡦⠤⠴⢫⡇⠀⠀삭제 하고자 하는⠀⠀⠘\n" +
                    "⠀⠀⠀⡇⠀⠀⢀⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⠀⡜⠀⠀  매물 번호 입력⠀⠀⢀⠇\n" +
                    "⠀⠀⢠⡇⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢦⠇⠀⠀             ⠀⠀⢸⠀\n" +
                    "⠀⠀⢸⠀⠀⠀⠀⠻⣿⣿⣿⣿⣿⣿⣿⣿⡿⠟⠘⠦⠤⢤⣀⠀⠀⠀         ⠀⠀⠃⠀\n" +
                    "⠀⠀⢸⠀⠀⠀⠀⠀⠈⠻⢿⣿⣿⣿⡿⠛⠁⠀⠀⠀⠀⠀⠈⠉⢙⣗⣲⣤⣤⣀⡀⠀⠀⠀⡸⠀⠀\n" +
                    "⠀⢀⡟⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⠋⠉⠉⠙⠻⠿⣜⠃⠵⢲⣃⠀⠀\n" +
                    "⣰⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢛⣷⣶⣤⠀⠀⠀⠘⡆⠀⠀⠀⠀⠀\n");
            int del=sc.nextInt();
            String sql="DELETE FROM RoomInfo WHERE house_num=?";
            try{
                conn= Common.getConnection();
                pStmt=conn.prepareStatement(sql);
                pStmt.setInt(1,del);
                int rst = pStmt.executeUpdate();
                System.out.println("rst : "+ rst);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                Common.close(pStmt);
                Common.close(conn);
            }
        }
    
        public void AdminSelectPrn(List<AdminInfoVo> list) {
            for (AdminInfoVo e : list) {
                System.out.print(e.getADM_ID() + " ");
                System.out.println(e.getADM_PW());
                System.out.println(blue + "========================================================================" + exit);
            }
        }
    
        public void RoomSelcetPrn(List<RoomInfoVo> list) {
            for (RoomInfoVo e : list) {
                System.out.print(e.getHouse_num() + " ");
                System.out.print(e.getUser_id() + " ");
                System.out.print(e.getPhoto_url() + " ");
                System.out.print(e.getTrade_method() + " ");
                System.out.print(e.getDeposit() + " ");
                System.out.print(e.getMonthly() + " ");
                System.out.print(e.getJeonsegeum() + " ");
                System.out.print(e.getSale_price() + " ");
                System.out.print(e.getArea() + " ");
                System.out.print(e.getAccept_date() + " ");
                System.out.print(e.getAddress() + " ");
                System.out.print(e.getFloor1() + " ");
                System.out.print(e.getPhonenumber() + " ");
                System.out.print(e.getRegit_date() + " ");
                System.out.println(e.getRemark());
                System.out.println(blue + "========================================================================" + exit);
            }
    
        }
        public void AdminInsertfunc(AdminInfoVo AIV) {
            String sql = "INSERT INTO ADMIN_INFO(ADM_ID,ADM_PW) VALUES (?,?)";
            try {
                conn = Common.getConnection();
                pStmt = conn.prepareStatement(sql);
                pStmt.setString(1, AIV.getADM_ID());
                pStmt.setString(2, AIV.getADM_PW());
                pStmt.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Common.close(pStmt);
            Common.close(conn);
        }
        public void AdminDeletefunc(AdminInfoVo AIV){
            String sql="DELETE FROM ADMIN_INFO WHERE ADM_ID=? AND ADM_PW=?";
            try{
                conn = Common.getConnection();
                pStmt = conn.prepareStatement(sql);
                pStmt.setString(1,AIV.getADM_ID());
                pStmt.setString(2,AIV.getADM_PW());
                pStmt.executeUpdate();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                Common.close(pStmt);
                Common.close(conn);
            }
    
        }
        public void AdminDelete(String ID){
            String sql="DELETE FROM ADMIN_INFO WHERE ADM_ID=?";
            try{
                conn=Common.getConnection();
                pStmt=conn.prepareStatement(sql);
                pStmt.setString(1,ID);
                pStmt.executeUpdate();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                Common.close(pStmt);
                Common.close(conn);
            }
    
        }
    }
    
    ```
    
- LoginDao
    
    ```java
    package com.kh.Olumgollum_Project.LoginInfo;
    
    import com.kh.Olumgollum_Project.COMMON.Common;
    
    import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.Statement;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.Scanner;
    
    public class LoginInfoDao {
        Scanner sc = new Scanner(System.in);
    
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        String ID = null;
    
        public static final String black = "\u001B[30m";
        public static final String red = "\u001B[31m";
        public static final String green = "\u001B[32m";
        public static final String yellow = "\u001B[33m";
        public static final String blue = "\u001B[34m";
        public static final String purple = "\u001B[35m";
        public static final String cyan = "\u001B[36;m";
        public static final String white = "\u001B[37m";
        public static final String exit = "\u001B[0m";
    
        public List<LoginInfoVo> logsel() {
            List<LoginInfoVo> list = new ArrayList<>();
            try {
                conn = Common.getConnection();
                stmt = conn.createStatement();
                String sql = "SELECT * FROM INFO";
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    String user_mail = rs.getString("USER_MAIL");
                    String password = rs.getString("PASSWORD");
                    String name = rs.getString("NAME");
                    int first_id_cardnum = rs.getInt("FIRST_ID_CARDNUM");
                    int phone_number = rs.getInt("PHONE_NUMBER");
                    String address = rs.getString("ADDRESS");
                    int gender = rs.getInt("GENDER");
                    String nickname = rs.getString("NICKNAME");
                    list.add(new LoginInfoVo(user_mail, password, name, first_id_cardnum, phone_number, address, gender, nickname));
                }
                Common.close(rs);
                Common.close(stmt);
                Common.close(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
    
            return list;
        }
    
        public List<String> login(LoginVo User_id_pw) throws InterruptedException {
            List<String> id_pw = new ArrayList<>();
            String sql = "SELECT PASSWORD FROM INFO WHERE USER_MAIL= ? ";
            String pwd;
            try {
                conn = Common.getConnection();
                pStmt = conn.prepareStatement(sql);
                pStmt.setString(1, ID);
                rs = pStmt.executeQuery(); // rs 객체에 쿼리 결과를 할당
    
                if (rs.next()) {
                    pwd = rs.getString("PASSWORD");
                    if (pwd.equals(User_id_pw.getUserid())) {
                        System.out.println(green + "                         로그인에 성공하셨습니다!" + exit);
                        id_pw.add(ID);
                        id_pw.add(pwd);
                        id_pw.add("User");
                        return id_pw;
                    } else {
                        System.out.println("  비밀번호가 틀렸습니다.");
                    }
                } else {
                    System.out.println("  아이디를 잘못 입력했습니다.");
                }
    
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Common.close(rs);
                Common.close(pStmt);
                Common.close(conn);
            }
            return id_pw;
        }
    
        public List<String> logInsert() {
            List<String> id_pw = new ArrayList<>();
            System.out.print(blue + "|" + exit + "          " + green + "아이디 입력" + exit + "         " + blue + "|     " + exit);
            String user_mail = sc.next();
            id_pw.add(user_mail);
            System.out.println(blue + "========================================================================" + exit);
            System.out.print(blue + "|" + exit + "         " + green + "패스워드 입력" + exit + "         " + blue + "|     " + exit);
            String password = sc.next();
            id_pw.add(password);
            System.out.println(blue + "========================================================================" + exit);
            System.out.print(blue + "|" + exit + "          " + green + "이름 입력" + exit + "           " + blue + "|     " + exit);
            String name = sc.next();
            System.out.println(blue + "========================================================================" + exit);
            System.out.print(blue + "|" + exit + "      " + green + "주민번호 앞자리 입력" + exit + "      " + blue + "|     " + exit);
            int first_id_cardnum = sc.nextInt();
            System.out.println(blue + "========================================================================" + exit);
            System.out.print(blue + "|" + exit + "      " + green + "전화번호 입력(-빼고)" + exit + "     " + blue + "|     " + exit);
            int phone_number = sc.nextInt();
            sc.nextLine();
            System.out.println(blue + "========================================================================" + exit);
            System.out.print(blue + "|" + exit + "          " + green + "주소 입력" + exit + "           " + blue + "|     " + exit);
            String address = sc.nextLine();
            System.out.println(blue + "========================================================================" + exit);
            System.out.print(blue + "|" + exit + "     " + green + "성별입력([1]남[2]여)" + exit + "     " + blue + "|     " + exit);
            int gender = sc.nextInt();
            sc.nextLine();
            System.out.println(blue + "========================================================================" + exit);
            System.out.print(blue + "|" + exit + "         " + green + "닉네임 입력" + exit + "          " + blue + "|     " + exit);
            String nickname = sc.next();
            System.out.println(blue + "========================================================================" + exit);
            String sql = "INSERT INTO INFO(USER_MAIL, PASSWORD, NAME, FIRST_ID_CARDNUM, PHONE_NUMBER, ADDRESS, GENDER, NICKNAME) VALUES (?,?,?,?,?,?,?,?)";
            try {
                conn = Common.getConnection();
                pStmt = conn.prepareStatement(sql);
                pStmt.setString(1, user_mail);
                pStmt.setString(2, password);
                pStmt.setString(3, name);
                pStmt.setInt(4, first_id_cardnum);
                pStmt.setInt(5, phone_number);
                pStmt.setString(6, address);
                pStmt.setInt(7, gender);
                pStmt.setString(8, nickname);
                int rst = pStmt.executeUpdate();
    //            System.out.println("rst : " + rst);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Common.close(pStmt);
            Common.close(conn);
            id_pw.add("User");
            return id_pw;
        }
    
        public void logSelectPrn(List<LoginInfoVo> list) {
            for (LoginInfoVo e : list) {
                System.out.print(e.getUser_mail() + " ");
                System.out.print(e.getPassword() + " ");
                System.out.print(e.getName() + " ");
                System.out.print(e.getFirst_id_cardnum() + " ");
                System.out.print(e.getPhone_number() + " ");
                System.out.print(e.getAddress() + " ");
                System.out.print(e.getGender() + " ");
                System.out.println(e.getNickname());
                System.out.println(blue + "========================================================================" + exit);
            }
            System.out.println();
        }
        // Thymeleaf 로그인 동작을 위한 메소드 제작.
        public boolean AdminLoginfunc(LoginVo vo){
            boolean check = false;
    
            try {
                String query = "SELECT * FROM ADMIN_INFO WHERE ADM_ID = ? AND ADM_PW = ?";
                conn = Common.getConnection();
                pStmt = conn.prepareStatement(query);
                pStmt.setString(1, vo.getUserid());
                pStmt.setString(2, vo.getPassword());
                rs = pStmt.executeQuery();
                if (rs.next()) {
                    check =  true;
                }
                Common.close(rs);
                Common.close(pStmt);
                Common.close(conn);
            }catch (Exception e){
                e.printStackTrace();
            }
            return check;
        }
        public boolean UserLoginfunc(LoginVo vo){
            boolean check = false;
    
            try {
                String query = "SELECT * FROM INFO WHERE USER_MAIL = ? AND PASSWORD = ?";
                conn = Common.getConnection();
                pStmt = conn.prepareStatement(query);
                pStmt.setString(1, vo.getUserid());
                pStmt.setString(2, vo.getPassword());
                rs = pStmt.executeQuery();
                if (rs.next()) {
                    check =  true;
                }
                Common.close(rs);
                Common.close(pStmt);
                Common.close(conn);
            }catch (Exception e){
                e.printStackTrace();
            }
            return check;
        }
        public void UserInsert(LoginInfoVo LIV) {
            List<String> id_pw = new ArrayList<>();
            String sql = "INSERT INTO INFO(USER_MAIL, PASSWORD, NAME, FIRST_ID_CARDNUM, PHONE_NUMBER, ADDRESS, GENDER, NICKNAME) VALUES (?,?,?,?,?,?,?,?)";
            try {
                conn = Common.getConnection();
                pStmt = conn.prepareStatement(sql);
                pStmt.setString(1, LIV.getUser_mail());
                pStmt.setString(2, LIV.getPassword());
                pStmt.setString(3, LIV.getName());
                pStmt.setInt(4, LIV.getFirst_id_cardnum());
                pStmt.setInt(5, LIV.getPhone_number());
                pStmt.setString(6, LIV.getAddress());
                pStmt.setInt(7, LIV.getGender());
                pStmt.setString(8, LIV.getNickname());
                pStmt.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Common.close(pStmt);
            Common.close(conn);
        }
    }
    
    ```
    
- MyPageDao
    
    ```java
    package com.kh.Olumgollum_Project.MyPage;
    
    import java.sql.*;
    
    import com.kh.Olumgollum_Project.AdminInfo.AdminInfoVo;
    import com.kh.Olumgollum_Project.COMMON.Common;
    import com.kh.Olumgollum_Project.LoginInfo.InfoUpdateVo;
    import com.kh.Olumgollum_Project.LoginInfo.LoginInfoVo;
    import com.kh.Olumgollum_Project.LoveRoom.LoveRoomVo;
    import com.kh.Olumgollum_Project.RoomInfo.RoomInfoVo;
    
    import java.sql.Date;
    import java.util.*;
    
    public class MyPageDao {
        public static final String black = "\u001B[30m";
        public static final String red = "\u001B[31m";
        public static final String green = "\u001B[32m";
        public static final String yellow = "\u001B[33m";
        public static final String blue = "\u001B[34m";
        public static final String purple = "\u001B[35m";
        public static final String cyan = "\u001B[36;m";
        public static final String white = "\u001B[37m";
        public static final String exit = "\u001B[0m";
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement pSmt = null;
        ResultSet rs = null;
        Scanner sc = new Scanner(System.in);
    
        // 삭제할 방 번호 저장
        // 개인정보 수정 비밀번호 전화번호 주소 닉네임
        public void Userupdate(List<String> id_pw) {
            System.out.print(blue +
                    "|======================================================================|\n" +
    
                    "|       \uD83D\uDD2E      _____  _____  ____   _____  _____  _____        \uD83D\uDD2E     |\n" +
                    "|            \uD83D\uDD2E|  |  ||  _  ||    \\ |  _  ||_   _||   __|              |\n" +
                    "|              |  |  ||   __||  |  ||     |  | |  |   __|      \uD83D\uDD2E      |\n" +
                    "|  \uD83D\uDD2E          |_____||__|   |____/ |__|__|  |_|  |_____|              |\n" +
                    "|======================================================================|\n" +
                    "| \uD83C\uDF26\uFE0F     ------------------              ------------------            |\n" +
                    "|        |[1]   비밀번호    |     \uD83C\uDF38      |[2]   전화번호    |            |\n" +
                    "|        ------------------              ------------------            |\n" +
                    "|        ------------------              ------------------            |\n" +
                    "| \uD83C\uDF38     |[3]     주소     |   \uD83C\uDF26\uFE0F        |[4]   닉네임      | \uD83C\uDF38        |\n" +
                    "|        ------------------              ------------------            |\n" +
                    "|        ------------------                                            |\n" +
                    "|        |[5]    나가기    |         |￣￣￣￣￣￣￣|        ☁\uFE0F   ⭐      |\n" +
                    "|        ------------------         | 뭐 바꿀래?  |            \uD83C\uDF38\uD83C\uDF38     |\n" +
                    "|                   ☁\uFE0F              |＿＿＿＿＿＿＿|    ☁\uFE0F     \uD83C\uDF38\uD83C\uDF38\uD83C\uDF38   |\n" +
                    "|                                   (\\__/) ||              \uD83C\uDF38\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38   |\n" +
                    "|                                   (•ㅅ•).||                  \uD83C\uDF38      |\n" +
                    "|       ☁\uFE0F          \uD83C\uDF26\uFE0F              / . . . .づ                        |\n" +
                    "========================================================================\n" + exit +
                    yellow + "     뭐 바꿀래? : " + exit);
            int up = sc.nextInt();
            System.out.println(blue + "========================================================================" + exit);
    
            String sql = null;
            switch (up) {
                case 1:
                    System.out.print("   수정하고자 하는 비밀번호 입력 : ");
                    String pwd = sc.next();
                    sql = "UPDATE INFO SET PASSWORD = ? WHERE USER_MAIL=?";
                    try {
                        conn = Common.getConnection();
                        pSmt = conn.prepareStatement(sql);
                        pSmt.setString(1, pwd);
                        pSmt.setString(2, id_pw.get(0));
                        pSmt.executeUpdate();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        Common.close(pSmt);
                        Common.close(conn);
                    }
                    break;
                case 2:
                    System.out.print("   수정하고자 하는 전화번호 입력 :");
                    String pn = sc.next();
                    sql = "UPDATE INFO SET PHONE_NUMBER = ? WHERE USER_MAIL=?";
                    try {
                        conn = Common.getConnection();
                        pSmt = conn.prepareStatement(sql);
                        pSmt.setString(1, pn);
                        pSmt.setString(2, id_pw.get(0));
                        pSmt.executeUpdate();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        Common.close(pSmt);
                        Common.close(conn);
                    }
                    break;
                case 3:
                    System.out.println("   수정하고자 하는 주소 입력 :");
                    String ad = sc.nextLine();
                    sql = "UPDATE INFO SET ADDRESS = ? WHERE USER_MAIL=?";
                    try {
                        conn = Common.getConnection();
                        pSmt = conn.prepareStatement(sql);
                        pSmt.setString(1, ad);
                        pSmt.setString(2, id_pw.get(0));
                        pSmt.executeUpdate();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        Common.close(pSmt);
                        Common.close(conn);
                    }
                    break;
                case 4:
                    System.out.println("   수정하고자 하는 닉네임 입력 :");
                    String ni = sc.next();
                    sql = "UPDATE INFO SET NICKNAME = ? WHERE USER_MAIL=?";
                    try {
                        conn = Common.getConnection();
                        pSmt = conn.prepareStatement(sql);
                        pSmt.setString(1, ni);
                        pSmt.setString(2, id_pw.get(0));
                        pSmt.executeUpdate();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        Common.close(pSmt);
                        Common.close(conn);
                    }
                    break;
                case 5:
                    System.exit(0);
            }
        }
    
        //===========================================================================================================================================================================================================
        public List<RoomInfoVo> Love_list(List<String> id_pw) { // 찜한방 목록화
            List<RoomInfoVo> love = new ArrayList<>();
            try {
                conn = Common.getConnection();
                stmt = conn.createStatement();
                String sql = "SELECT rm.house_num,rm.user_id,rm.photo_url,rm.trade_method,rm.deposit,rm.MONTHLY,rm.jeonsegeum,rm.address, rm.area, rm.sale_price " +
                        "FROM ROOMINFO rm JOIN LOVE_ROOM lr " +
                        "ON rm.house_num = lr.shouse_num WHERE lr.user_id = '" + id_pw.get(0) + "'";
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    //내가 찜한 방에 대한 정보들
                    love.add(new RoomInfoVo(rs.getInt("house_num"), rs.getString("user_id"), rs.getString("photo_url"), rs.getString("trade_method"),
                            rs.getInt("deposit"), rs.getInt("MONTHLY"), rs.getInt("jeonsegeum"),
                            rs.getInt("sale_price"), rs.getString("area"), null, rs.getString("address"), null,
                            0, null, null));
                }
                Common.close(rs);
                Common.close(stmt);
                Common.close(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return love;
        }
    
        //==================================================================================================================================================================================================================
        //db에서 찜삭제함수
        public void Love_delete(List<String> id_pw) { // 찜한방 목록 Delete
            try {
                // love 내가 찜한 방의 정보들
                conn = Common.getConnection();
                stmt = conn.createStatement();
                System.out.print("   삭제 할 방 번호를 입력해주세요 : ");
                int num = sc.nextInt();
                // 찜한 방 목록에서 확인 후 삭제
                String sql = "DELETE FROM LOVE_ROOM WHERE USER_ID = '" + id_pw.get(0) + "'" + "AND SHOUSE_NUM = '" + num + "'";
                rs = stmt.executeQuery(sql);
                // 방목록에서 원하는방 삭제
                Common.close(rs);
                Common.close(stmt);
                Common.close(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    
    //==========================================================================================================================================================================
    
        public List<RoomInfoVo> olume_list(List<String> id_pw) { // 올룸 목록
    
            List<RoomInfoVo> olume = new ArrayList<>();
            try {
                conn = Common.getConnection();
                stmt = conn.createStatement();
                String sql = "SELECT address, deposit, area, trade_method, photo_url, MONTHLY, jeonsegeum, sale_price " +
                        "FROM ROOMINFO WHERE USER_ID = '" + id_pw.get(0) + "'";
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    olume.add(new RoomInfoVo(0, null, rs.getString("photo_url"), rs.getString("trade_method"),
                            rs.getInt("deposit"), rs.getInt("monthly"), rs.getInt("jeonsegeum"),
                            rs.getInt("sale_price"), rs.getString("area"), null, rs.getString("address"), null,
                            0, null, null));
                }
                Common.close(rs);
                Common.close(stmt);
                Common.close(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return olume;
        }
    
        //===========================================================================================================================================================================
        // 개인정보 수정 비밀번호 전화번호 주소 닉네임
        public void print(List<RoomInfoVo> list) {
            for (RoomInfoVo e : list)
                System.out.println(e.getUser_id());
        }
    
        //============================================================================================================================
        //올룸 수정 함수
        // 업데이트
        public void RoomUpdate() {
            System.out.print("   수정하고 싶은 정보 [1] 방 사진 [2] 거래방식(가격) [3] 전화번호 [4] 비고 [5] 종료");
            int up = sc.nextInt();
            String sql = null;
            int honum;
            switch (up) {
                case 1:
                    System.out.print("   바꾸고자 하는 매물번호 : ");
                    honum = sc.nextInt();
                    System.out.println("수정 사진 등록");
                    String url = sc.next();
                    sql = "UPDATE ROOMINFO SET photo_url = ? WHERE house_num=?";
                    try {
                        conn = Common.getConnection();
                        pSmt = conn.prepareStatement(sql);
                        pSmt.setString(1, url);
                        pSmt.setInt(2, honum);
                        pSmt.executeUpdate();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        Common.close(pSmt);
                        Common.close(conn);
                    }
                    break;
                case 2:
                    System.out.print("   바꾸고자 하는 매물번호  :");
                    honum = sc.nextInt();
                    System.out.println("바꾸고 싶은 거래방식 선택 [1] 매매 [2] 전세 [3] 월세 [4] 종료");
                    int tr = sc.nextInt();
                    if (tr == 4) {
                        break;
                    }
                    sql = "UPDATE ROOMINFO SET deposit = NULL, monthly = NULL, jeonsegeum = NULL , Sale_price = NULL WHERE house_num =?";
                    try {
                        conn = Common.getConnection();
                        pSmt = conn.prepareStatement(sql);
                        pSmt.setInt(1, honum);
                        pSmt.executeUpdate();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        Common.close(pSmt);
                        Common.close(conn);
                    }
                    switch (tr) {
                        case 1:
                            System.out.print("  매매 가격을 알려주세요");
                            int sal = sc.nextInt();
                            sql = "UPDATE ROOMINFO SET trade_method = ?,Sale_price=? WHERE house_num=?";
                            try {
                                conn = Common.getConnection();
                                pSmt = conn.prepareStatement(sql);
                                pSmt.setString(1, "매매");
                                pSmt.setInt(2, sal);
                                pSmt.setInt(3, honum);
                                pSmt.executeUpdate();
                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                Common.close(pSmt);
                                Common.close(conn);
                            }
                            break;
                        case 2:
                            System.out.print("  전세 가격을 알려주세요");
                            int jeo = sc.nextInt();
                            sql = "UPDATE ROOMINFO SET trade_method=?,jeonsegeum=? WHERE house_num=?";
                            try {
                                conn = Common.getConnection();
                                pSmt = conn.prepareStatement(sql);
                                pSmt.setString(1, "전세");
                                pSmt.setInt(2, jeo);
                                pSmt.setInt(3, honum);
                                pSmt.executeUpdate();
                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                Common.close(pSmt);
                                Common.close(conn);
                            }
                            break;
                        case 3:
                            System.out.print("   보증금 가격 : ");
                            int dep = sc.nextInt();
                            System.out.print("   월세 가격 : ");
                            int mon = sc.nextInt();
                            sql = "UPDATE ROOMINFO SET trade_method=?,deposit=?,monthly=? WHERE house_num=?";
                            try {
                                conn = Common.getConnection();
                                pSmt = conn.prepareStatement(sql);
                                pSmt.setString(1, "월세");
                                pSmt.setInt(2, dep);
                                pSmt.setInt(3, mon);
                                pSmt.setInt(4, honum);
                                pSmt.executeUpdate();
                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                Common.close(pSmt);
                                Common.close(conn);
                            }
                            break;
                    }
                    break;
                case 3:
                    System.out.print("   바꾸고자 하는 매물번호  :");
                    honum = sc.nextInt();
                    System.out.print("   수정 하고자 하는 전화번호  :");
                    int phnum = sc.nextInt();
                    sql = "UPDATE ROOMINFO SET phonenumber = ? WHERE house_num=?";
                    try {
                        conn = Common.getConnection();
                        pSmt = conn.prepareStatement(sql);
                        pSmt.setInt(1, phnum);
                        pSmt.setInt(2, honum);
                        pSmt.executeUpdate();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        Common.close(pSmt);
                        Common.close(conn);
                    }
                    break;
                case 4:
                    System.out.print("   바꾸고자 하는 매물번호  :");
                    honum = sc.nextInt();
                    sc.nextLine();
                    System.out.print("   수정 할 비고  :");
                    String rem = sc.nextLine();
                    sql = "UPDATE ROOMINFO SET remark = ? WHERE house_num=?";
                    try {
                        conn = Common.getConnection();
                        pSmt = conn.prepareStatement(sql);
                        pSmt.setString(1, rem);
                        pSmt.setInt(2, honum);
                        pSmt.executeUpdate();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        Common.close(pSmt);
                        Common.close(conn);
                    }
                    break;
                case 5:
                    System.exit(0);
            }
        }
    
        //올룸 삭제 함수
        // 방삭제
        public void RoomDelete() {
            System.out.print("   삭제 할 매물번호를 입력하세요 : ");
            int num1 = sc.nextInt();
            String sql = "DELETE FROM LOVE_ROOM WHERE SHOUSE_NUM = ?";
            try {
                conn = Common.getConnection();
                pSmt = conn.prepareStatement(sql);
                pSmt.setInt(1, num1);
                pSmt.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Common.close(pSmt);
                Common.close(conn);
            }
        }
    
        //======================================================== 회원 탈퇴=======================================================
        public void UserDelete(List<String> id_pw) {
            System.out.println(red +
                    "=====================================\n" +
                    "   ·▄▄▄▄  ▄▄▄ .▄▄▌  ▄▄▄ .▄▄▄▄▄▄▄▄ .\n" +
                    "   ██▪ ██ ▀▄.▀·██•  ▀▄.▀·•██  ▀▄.▀·\n" +
                    "   ▐█· ▐█▌▐▀▀▪▄██▪  ▐▀▀▪▄ ▐█.▪▐▀▀▪▄\n" +
                    "   ██. ██ ▐█▄▄▌▐█▌▐▌▐█▄▄▌ ▐█▌·▐█▄▄▌\n" +
                    "   ▀▀▀▀▀•  ▀▀▀ .▀▀▀  ▀▀▀  ▀▀▀  ▀▀▀ \n" +
                    "=====================================");
            System.out.println(blue + " [1]\uD83D\uDE2D탈퇴 [2] 나가기  :");
            int ud = sc.nextInt();
            String sql = "DELETE FROM INFO WHERE USER_MAIL =  ?";
            switch (ud) {
                case 1:
                    try {
                        conn = Common.getConnection();
                        pSmt = conn.prepareStatement(sql);
                        pSmt.setString(1, id_pw.get(0));
                        pSmt.executeUpdate();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        Common.close(pSmt);
                        Common.close(conn);
                    }
                    break;
                case 2:
                    break;
            }
        }
    
        //=========================================================================================================================================================
        public void loveprn(List<RoomInfoVo> LP) {
            System.out.println(blue + "=======================================================================" + exit);
            System.out.println(blue +
                    "   —̳͟͞͞ \uD83D\uDC97    ██╗         ██╗    ███████╗    ████████╗        \uD83C\uDF38       \n" +
                    "               ██║         ██║    ██╔════╝    ╚══██╔══╝               \n" +
                    "               ██║         ██║    ███████╗       ██║    —̳͟͞͞ \uD83D\uDC97             \n" +
                    "      \uD83C\uDF38       ██║         ██║    ╚════██║       ██║                  \n" +
                    "               ███████╗    ██║    ███████║       ██║               —̳͟͞͞ \uD83D\uDC97  \n" +
                    "    —̳͟͞͞ \uD83D\uDC97   ╚══════╝    ╚═╝    ╚══════╝       ╚═╝  \uD83C\uDF38                \n");
            System.out.println(blue + "=======================================================================" + exit);
            for (RoomInfoVo e : LP) {
                System.out.println(blue + "|  ⭐------------------⭐                                              " + exit);
                System.out.println(blue + "|   ▏                    ▏  \uD83C\uDFE0" + e.getAddress() + "                           " + exit);
                System.out.println(blue + "|   ▏" + (e.getPhoto_url()) + "               ▏          \uD83D\uDCB8" + e.getJeonsegeum() + "                                  " + exit);
                System.out.println(blue + "|   ▏                    ▏          \uD83D\uDCB8" + e.getSale_price() + "                                " + exit);
                System.out.println(blue + "|   ▏                    ▏          \uD83D\uDCB8" + e.getMonthly() + "                                " + exit);
                System.out.println(blue + "|  ⭐------------------⭐       \uD83D\uDFEB" + e.getArea() + "                                   " + exit);
                System.out.println(blue + "――――――――――――――――――――――――――――――――――――――――――――" + exit);
            }
        }
    
        //===============================================================================================================================================
        public void mprn() {
            System.out.println(blue + "========================================================================" + exit);
            System.out.println(blue +
                    "|   —̳͟͞͞ \uD83D\uDC97     _____ __ __    _____ _____ _____ _____      \uD83C\uDF38        |\n" +
                    "|               |     |  |  |  |  _  |  _  |   __|   __|               |\n" +
                    "|         \uD83C\uDF38    | | | |_   _|  |   __|     |  |  |   __|   —̳͟͞͞ \uD83D\uDC97    |\n" +
                    "|               |_|_|_| |_|    |__|  |__|__|_____|_____|               |\n" +
                    "|         —̳͟͞͞ \uD83D\uDC97                                 —̳͟͞͞ \uD83D\uDC97            |");
            System.out.println(blue + "|======================================================================|" + exit);
            System.out.println(blue + "| \uD83C\uDF26\uFE0F     ------------------              ------------------            |" + exit);
            System.out.println(blue + "|       |" + exit + "[1]   회원 수정    " + blue + "|     \uD83C\uDF38      |" + exit + "[2]    찜 목록    " + blue + "|           |" + exit);
            System.out.println(blue + "|        ------------------              ------------------            |" + exit);
            System.out.println(blue + "|        ------------------              ------------------            |" + exit);
            System.out.println(blue + "| \uD83C\uDF38    |" + exit + "[3]   올룸 목록    " + blue + "|   \uD83C\uDF26\uFE0F        |" + exit + "[4]   회원 탈퇴   " + blue + "| \uD83C\uDF38        |" + exit);
            System.out.println(blue + "|        ------------------              ------------------            |" + exit);
            System.out.println(blue + "|        ------------------                                            |" + exit);
            System.out.println(blue + "|       |" + exit + "[5]    나가기      " + blue + "         |￣￣￣￣￣￣￣|     " + red + "   ☁\uFE0F   ⭐  " + blue + "    |");
            System.out.println(blue + "|        ------------------         |  선택하쇼   |     " + green + "       \uD83C\uDF38\uD83C\uDF38 " + blue + "    |");
            System.out.println(blue + "|                   ☁\uFE0F              |＿＿＿＿＿＿＿|  " + green + "  ☁\uFE0F     \uD83C\uDF38\uD83C\uDF38\uD83C\uDF38    " + blue + "|");
            System.out.println(blue + "|                                   (\\__/) || " + green + "             \uD83C\uDF38\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38   " + blue + "|");
            System.out.println(blue + "|                                   (•ㅅ•).||  " + green + "                \uD83C\uDF38      " + blue + "|");
            System.out.println(blue + "|       ☁\uFE0F          \uD83C\uDF26\uFE0F              / . . . .づ  " + green + "                      " + blue + "|");
            System.out.println(blue + "========================================================================" + exit);
            System.out.print(blue + "|" + exit + "     " + yellow + "|  선택하쇼   |" + exit + "     " + blue + "|     " + exit);
        }
    
        public LoginInfoVo ExtractInfo(String ID) {
            LoginInfoVo LIV = new LoginInfoVo();
            String sql = "SELECT * FROM INFO WHERE USER_MAIL = ?";
            try {
                conn = Common.getConnection();
                pSmt = conn.prepareStatement(sql);
                pSmt.setString(1, ID);
                rs = pSmt.executeQuery();
                while (rs.next()) {
                    String user_mail = rs.getString("USER_MAIL");
                    String password = rs.getString("PASSWORD");
                    String name = rs.getString("NAME");
                    int first_id_cardnum = rs.getInt("FIRST_ID_CARDNUM");
                    int phone_number = rs.getInt("PHONE_NUMBER");
                    String address = rs.getString("ADDRESS");
                    int gender = rs.getInt("GENDER");
                    String nickname = rs.getString("NICKNAME");
                    LIV.setUser_mail(user_mail);
                    LIV.setPassword(password);
                    LIV.setName(name);
                    LIV.setFirst_id_cardnum(first_id_cardnum);
                    LIV.setPhone_number(phone_number);
                    LIV.setAddress(address);
                    LIV.setGender(gender);
                    LIV.setNickname(nickname);
                }
                Common.close(rs);
                Common.close(pSmt);
                Common.close(conn);
    
            } catch (Exception e) {
                e.printStackTrace();
            }
            return LIV;
        }
        public AdminInfoVo AdminExtractInfo(String ID) {
            AdminInfoVo AIV = new AdminInfoVo();
            String sql = "SELECT * FROM ADMIN_INFO WHERE ADM_ID = ?";
            try {
                conn = Common.getConnection();
                pSmt = conn.prepareStatement(sql);
                pSmt.setString(1, ID);
                rs = pSmt.executeQuery();
                while (rs.next()) {
                    String adm_id = rs.getString("ADM_ID");
                    String adm_pw = rs.getString("ADM_PW");
                    AIV.setADM_ID(adm_id);
                    AIV.setADM_PW(adm_pw);
                }
                Common.close(rs);
                Common.close(pSmt);
                Common.close(conn);
    
            } catch (Exception e) {
                e.printStackTrace();
            }
            return AIV;
        }
    
        //덮어 씌우는 함수
        public LoginInfoVo Overwrite(LoginInfoVo LIV, InfoUpdateVo IUV) {
            if (IUV.getUSER_MAIL() != null) {
                LIV.setUser_mail(IUV.getUSER_MAIL());
            } if (IUV.getPASSWORD() != null) {
                LIV.setPassword(IUV.getPASSWORD());
            } if (IUV.getNAME() != null) {
                LIV.setName(IUV.getNAME());
            } if (IUV.getPHONE_NUMBER() != 0) {
                LIV.setPhone_number(IUV.getPHONE_NUMBER());
            } if (IUV.getADDRESS() != null) {
                LIV.setAddress(IUV.getADDRESS());
            } if (IUV.getNICKNAME() != null) {
                LIV.setNickname(IUV.getNICKNAME());
            }
            return LIV;
        }
        public AdminInfoVo AdminOverwrite(AdminInfoVo aiv, AdminInfoVo AIV) {
            if (AIV.getADM_ID() != null) {
                aiv.setADM_ID(AIV.getADM_ID());
            }
            if (AIV.getADM_PW() != null) {
                aiv.setADM_PW(AIV.getADM_PW());
            }
            return aiv;
        }
    
        public void InfoUpdate(LoginInfoVo LIV, String ID) {
            String sql = "UPDATE INFO SET USER_MAIL = ?, PASSWORD = ?, NAME = ?," +
                    "FIRST_ID_CARDNUM = ?,PHONE_NUMBER = ?, ADDRESS = ?, GENDER = ?, NICKNAME = ? " +
                    "WHERE USER_MAIL = ?";
            try {
                conn = Common.getConnection();
                pSmt = conn.prepareStatement(sql);
                pSmt.setString(1, LIV.getUser_mail());
                pSmt.setString(2, LIV.getPassword());
                pSmt.setString(3, LIV.getName());
                pSmt.setInt(4, LIV.getFirst_id_cardnum());
                pSmt.setInt(5, LIV.getPhone_number());
                pSmt.setString(6, LIV.getAddress());
                pSmt.setInt(7, LIV.getGender());
                pSmt.setString(8, LIV.getNickname());
                pSmt.setString(9, ID);
                pSmt.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Common.close(pSmt);
                Common.close(conn);
            }
        }
        public void AdminInfoUpdate(AdminInfoVo AIV, String ID) {
            String sql = "UPDATE ADMIN_INFO SET ADM_ID = ?, ADM_PW = ? WHERE ADM_ID = ?";
            try {
                conn = Common.getConnection();
                pSmt = conn.prepareStatement(sql);
                pSmt.setString(1, AIV.getADM_ID());
                pSmt.setString(2, AIV.getADM_PW());
                pSmt.setString(3, ID);
                pSmt.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Common.close(pSmt);
                Common.close(conn);
            }
        }
    
        public void UserDeletefunc(String ID) {
            String sql = "DELETE FROM INFO WHERE USER_MAIL =  ?";
            try {
                conn = Common.getConnection();
                pSmt = conn.prepareStatement(sql);
                pSmt.setString(1, ID);
                pSmt.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Common.close(pSmt);
                Common.close(conn);
            }
    
        }
    
        public List<RoomInfoVo> RoomSelect() throws SQLException {
            // 일단 전부 뽑아온다.
            List<RoomInfoVo> list = new ArrayList<>();
            try {
                String sql = "SELECT * FROM ROOMINFO";
                conn = Common.getConnection();
                pSmt = conn.prepareStatement(sql);
                rs = pSmt.executeQuery();
                while (rs.next()) {
                    int house_num = rs.getInt("HOUSE_NUM");
                    String user_id = rs.getString("USER_ID");
                    String photo_url = rs.getString("PHOTO_URL");
                    String trade_method = rs.getString("TRADE_METHOD");
                    int deposit = rs.getInt("DEPOSIT");
                    int monthly = rs.getInt("MONTHLY");
                    int jeonsegeum = rs.getInt("JEONSEGEUM");
                    int Sale_price = rs.getInt("SALE_PRICE");
                    String area = rs.getString("AREA");
                    java.sql.Date account_date = rs.getDate("ACCEPT_DATE");
                    String address = rs.getString("ADDRESS");
                    String floor1 = rs.getString("FLOOR1");
                    int phonenumber = rs.getInt("PHONENUMBER");
                    Date regit_date = rs.getDate("REGIT_DATE");
                    String remark = rs.getString("REMARK");
                    list.add(new RoomInfoVo(house_num, user_id, photo_url, trade_method, deposit, monthly, jeonsegeum,
                            Sale_price, area, account_date, address, floor1, phonenumber, regit_date, remark));
                }
            } catch (Exception e) {
            } finally {
                Common.close(rs);
                Common.close(pSmt);
                Common.close(conn);
            }
            return list;
        }
    
        public void RoomDel(int RoomNum) {
            String sql = "DELETE FROM RoomInfo WHERE HOUSE_NUM = ?";
            try {
                conn = Common.getConnection();
                pSmt = conn.prepareStatement(sql);
                pSmt.setInt(1, RoomNum);
                pSmt.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Common.close(pSmt);
                Common.close(conn);
            }
        }
    
        public List<RoomInfoVo> LoveList(String id) { // 찜한방 목록화
            List<RoomInfoVo> love = new ArrayList<>();
            try {
                conn = Common.getConnection();
                stmt = conn.createStatement();
                String sql = "SELECT rm.house_num,rm.user_id,rm.photo_url,rm.trade_method,rm.deposit,rm.MONTHLY,rm.jeonsegeum,rm.address, rm.area, rm.sale_price " +
                        "FROM ROOMINFO rm JOIN LOVE_ROOM lr " +
                        "ON rm.house_num = lr.shouse_num WHERE lr.user_id = '" + id + "'";
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    //내가 찜한 방에 대한 정보들
                    love.add(new RoomInfoVo(rs.getInt("house_num"), rs.getString("user_id"), rs.getString("photo_url"), rs.getString("trade_method"),
                            rs.getInt("deposit"), rs.getInt("MONTHLY"), rs.getInt("jeonsegeum"),
                            rs.getInt("sale_price"), rs.getString("area"), null, rs.getString("address"), null,
                            0, null, null));
                }
                Common.close(rs);
                Common.close(stmt);
                Common.close(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return love;
        }
    
        public void LoveDelete(LoveRoomVo LRV) { // 찜한방 목록 Delete
            try {
                // love 내가 찜한 방의 정보들
                conn = Common.getConnection();
                stmt = conn.createStatement();
                // 찜한 방 목록에서 확인 후 삭제
                String sql = "DELETE FROM LOVE_ROOM WHERE USER_ID = '" + LRV.getUser_id() + "'" + "AND SHOUSE_NUM = '" + LRV.getShouse_num() + "'";
                rs = stmt.executeQuery(sql);
                // 방목록에서 원하는방 삭제
                Common.close(rs);
                Common.close(stmt);
                Common.close(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    
        public List<RoomInfoVo> OlumList(String id) { // 올룸 목록
    
            List<RoomInfoVo> olume = new ArrayList<>();
            try {
                conn = Common.getConnection();
                stmt = conn.createStatement();
                String sql = "SELECT * FROM ROOMINFO WHERE USER_ID = '" + id + "'";
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    int house_num = rs.getInt("HOUSE_NUM");
                    String user_id = rs.getString("USER_ID");
                    String photo_url = rs.getString("PHOTO_URL");
                    String trade_method = rs.getString("TRADE_METHOD");
                    int deposit = rs.getInt("DEPOSIT");
                    int monthly = rs.getInt("MONTHLY");
                    int jeonsegeum = rs.getInt("JEONSEGEUM");
                    int sale_price = rs.getInt("SALE_PRICE");
                    String area = rs.getString("AREA");
                    Date accept_date =  rs.getDate("ACCEPT_DATE");
                    String address =  rs.getString("ADDRESS");
                    String floor1 = rs.getString("FLOOR1");
                    int phonenumber =rs.getInt("PHONENUMBER");
                    Date regit_date =rs.getDate("REGIT_DATE");
                    String remark = rs.getString("REMARK");
                    olume.add(new RoomInfoVo(house_num,user_id ,photo_url,trade_method, deposit,monthly,jeonsegeum,sale_price
                            ,area,accept_date,address,floor1, phonenumber,regit_date,remark));
                }
                Common.close(rs);
                Common.close(stmt);
                Common.close(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return olume;
        }
    
        public void OlumUpdate(RoomInfoVo RIV) {
    
            try {
                String query = "UPDATE ROOMINFO SET photo_url = ?, TRADE_METHOD = ?, DEPOSIT = ?, MONTHLY = ?, JEONSEGEUM = ?, SALE_PRICE = ?,AREA = ?, ADDRESS = ?,FlOOR1 =?,PHONENUMBER = ?, REMARK = ? WHERE HOUSE_NUM = ?";
                conn = Common.getConnection();
                pSmt = conn.prepareStatement(query);
                pSmt.setString(1, RIV.getPhoto_url());
                pSmt.setString(2, RIV.getTrade_method());
                pSmt.setInt(3, RIV.getDeposit());
                pSmt.setInt(4, RIV.getMonthly());
                pSmt.setInt(5, RIV.getJeonsegeum());
                pSmt.setInt(6, RIV.getSale_price());
                pSmt.setString(7, RIV.getArea());
                pSmt.setString(8, RIV.getAddress());
                pSmt.setString(9, RIV.getFloor1());
                pSmt.setInt(10, RIV.getPhonenumber());
                pSmt.setString(11, RIV.getRemark());
                pSmt.setInt(12, RIV.getHouse_num());
                pSmt.executeUpdate();
            } catch (Exception e) {
            } finally {
                Common.close(rs);
                Common.close(pSmt);
                Common.close(conn);
            }
        }
        public void LoveAllDelete(String ID) { // 찜한방 목록 Delete
            try {
                // love 내가 찜한 방의 정보들
                conn = Common.getConnection();
                stmt = conn.createStatement();
                // 찜한 방 목록에서 확인 후 삭제
                String sql = "DELETE FROM LOVE_ROOM WHERE USER_ID = '" + ID + "'";
                rs = stmt.executeQuery(sql);
                // 방목록에서 원하는방 삭제
                Common.close(rs);
                Common.close(stmt);
                Common.close(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        public void RoomAllDel(String ID) {
            String sql = "DELETE FROM RoomInfo WHERE user_id = ?";
            try {
                conn = Common.getConnection();
                pSmt = conn.prepareStatement(sql);
                pSmt.setString(1, ID);
                pSmt.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Common.close(pSmt);
                Common.close(conn);
            }
        }
        public void MyPageAllDel(String ID) {
            String sql = "DELETE FROM MY_PAGE WHERE USER_MAIL = ?";
            try {
                conn = Common.getConnection();
                pSmt = conn.prepareStatement(sql);
                pSmt.setString(1, ID);
                pSmt.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Common.close(pSmt);
                Common.close(conn);
            }
        }
        public void LoveOlumDelete(int num) { // 찜한방 목록 Delete
            try {
                // love 내가 찜한 방의 정보들
                conn = Common.getConnection();
                stmt = conn.createStatement();
                // 찜한 방 목록에서 확인 후 삭제
                String sql = "DELETE FROM LOVE_ROOM WHERE Shouse_num = '" + num + "'";
                rs = stmt.executeQuery(sql);
                // 방목록에서 원하는방 삭제
                Common.close(rs);
                Common.close(stmt);
                Common.close(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        public void MyPageOlumDelete(String ID) { // 찜한방 목록 Delete
            try {
                // love 내가 찜한 방의 정보들
                conn = Common.getConnection();
                stmt = conn.createStatement();
                // 찜한 방 목록에서 확인 후 삭제
                String sql = "DELETE FROM MY_PAGE WHERE USER_MAIL = '" + ID + "'";
                rs = stmt.executeQuery(sql);
                // 방목록에서 원하는방 삭제
                Common.close(rs);
                Common.close(stmt);
                Common.close(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    
    }
    
    ```
    
- RoomSearchDao
    
    ```java
    package com.kh.Olumgollum_Project.RoomSearch;
    
    import com.kh.Olumgollum_Project.COMMON.Common;
    import com.kh.Olumgollum_Project.RoomInfo.RoomInfoVo;
    
    import java.sql.*;
    import java.util.ArrayList;
    import java.util.Iterator;
    import java.util.List;
    import java.util.Scanner;
    
    // ROOMINFO 테이블 검색 후 리스트 리턴 함수
    public class RoomSearchDao {
        public static final String black = "\u001B[30m";
        public static final String red = "\u001B[31m";
        public static final String green = "\u001B[32m";
        public static final String yellow = "\u001B[33m";
        public static final String blue = "\u001B[34m";
        public static final String purple = "\u001B[35m";
        public static final String cyan = "\u001B[36;m";
        public static final String white = "\u001B[37m";
        public static final String exit = "\u001B[0m";
        Connection conn = null;
        Statement stmt = null; // create Statement 방식
        PreparedStatement pStmt = null; // Prepared Statement 방식
        ResultSet rs = null; // database로 부터 결과를 받는 변수
        Scanner sc = new Scanner(System.in);
    
        // ROOMINFO 테이블 검색 후 리스트 리턴 함수
        public List<RoomInfoVo> roomSelect() {
            List<RoomInfoVo> list = new ArrayList<>();
            System.out.println(blue + "========================================================================" + exit);
            System.out.println(purple + "                         모든 검색을 시작합니다." + exit);
            System.out.println(blue + "========================================================================" + exit);
            try {
                conn = Common.getConnection();
                stmt = conn.createStatement();
                String sql = "SELECT * FROM ROOMINFO";
                rs = stmt.executeQuery(sql);
    
                while (rs.next()) {
                    int house_num = rs.getInt("HOUSE_NUM");
                    String user_id = rs.getString("USER_ID");
                    String photo_url = rs.getString("PHOTO_URL");
                    String trade_method = rs.getString("TRADE_METHOD");
                    int deposit = rs.getInt("DEPOSIT");
                    int monthly = rs.getInt("MONTHLY");
                    int jeonsegeum = rs.getInt("JEONSEGEUM");
                    int Sale_price = rs.getInt("SALE_PRICE");
                    String area = rs.getString("AREA");
                    String address = rs.getString("ADDRESS");
                    String floor1 = rs.getString("FLOOR1");
                    int phonenumber = rs.getInt("PHONENUMBER");
                    String remark = rs.getString("REMARK");
    
                    list.add(new RoomInfoVo(house_num, user_id, photo_url, trade_method, deposit, monthly, jeonsegeum,
                            Sale_price, area, rs.getDate("ACCEPT_DATE"), address, floor1, phonenumber, rs.getDate("REGIT_DATE"), remark));
                }
                Common.close(rs);
                Common.close(stmt);
                Common.close(conn);
    
            } catch (Exception e) {
                e.printStackTrace();
            }
            return list;
        }
    
        // 지역별 검색 함수
        public List<RoomInfoVo> adressSearchSelect() {
            List<RoomInfoVo> list = new ArrayList<>();
            System.out.print(green + "    조회 할 지역을 입력하세요 : " + exit);
            String add = sc.next();
            String sql = "SELECT * FROM ROOMINFO WHERE address like ?";
    
            try {
                conn = Common.getConnection();
                pStmt = conn.prepareStatement(sql);
                pStmt.setString(1, "%" + add + "%");
                ResultSet rs = pStmt.executeQuery();
                while (rs.next()) {
                    int house_num = rs.getInt("HOUSE_NUM");
                    String user_id = rs.getString("USER_ID");
                    String photo_url = rs.getString("PHOTO_URL");
                    String trade_method = rs.getString("TRADE_METHOD");
                    int deposit = rs.getInt("DEPOSIT");
                    int monthly = rs.getInt("MONTHLY");
                    int jeonsegeum = rs.getInt("JEONSEGEUM");
                    int Sale_price = rs.getInt("SALE_PRICE");
                    String area = rs.getString("AREA");
                    String address = rs.getString("ADDRESS");
                    String floor1 = rs.getString("FLOOR1");
                    int phonenumber = rs.getInt("PHONENUMBER");
                    String remark = rs.getString("REMARK");
    
                    list.add(new RoomInfoVo(house_num, user_id, photo_url, trade_method, deposit, monthly, jeonsegeum,
                            Sale_price, area, rs.getDate("ACCEPT_DATE"), address, floor1, phonenumber, rs.getDate("REGIT_DATE"), remark));
                }
                Common.close(pStmt);
                Common.close(conn);
    
            } catch (Exception e) {
                e.printStackTrace();
            }
            return list;
        }
    
        //거래방식 검색함수
        public List<RoomInfoVo> tradeSelect(List<RoomInfoVo> list) {
            List<RoomInfoVo> tradeList = new ArrayList<>();
            try {
                while (true) {
                    System.out.print(green + "    거래방식을 입력하세요 : " + exit);
                    String trade = sc.next();
                    for (RoomInfoVo e : list) {
                        if (e.getTrade_method().equals(trade)) {
                            tradeList.add(e);
                        }
                    }
                    if (tradeList.size() == 0) {
                        System.out.println(green + "    다시 입력해주세요." + exit);
                        continue;
                    }
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return tradeList;
        }
    
        //평수 검색 함수
        public List<RoomInfoVo> areaTradeSelect(List<RoomInfoVo> tradeList) {
            List<RoomInfoVo> areaTradeList = new ArrayList<>();
            try {
                while (true) {
                    System.out.print(green + "    평수를 입력하세요 : " + exit);
                    int area = sc.nextInt();
                    for (RoomInfoVo e : tradeList) {
                        if (Integer.parseInt(e.getArea()) >= area) {
                            areaTradeList.add(e);
                        }
                    }
                    if (areaTradeList.size() <= 0) {
                        System.out.println(green + "    다시 입력해주세요." + exit);
                        continue;
                    }
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return areaTradeList;
        }
    
        //층 수 검색 함수
        public List<RoomInfoVo> floorTradeSelect(List<RoomInfoVo> tradeList) {
            List<RoomInfoVo> floorTradeList = new ArrayList<>();
            try {
                while (true) {
                    System.out.print(green + "    층을 입력하세요 : " + exit);
                    String flo = sc.next();
                    for (RoomInfoVo e : tradeList) {
                        int index = flo.indexOf("층");
                        if (index != -1) {
                            String extract = flo.substring(0, index);
                            int index2 = e.getFloor1().indexOf("층");
                            if (index2 != -1) {
                                String extract2 = e.getFloor1().substring(0, index2);
                                if (flo.equals("루프탑") || flo.equals("반지층") || Integer.parseInt(extract) <= Integer.parseInt(extract2)) {
                                    floorTradeList.add(e);
                                }
                            }
                        }
                    }
                    if (floorTradeList.size() <= 0) {
                        System.out.println(green + "    다시 입력해주세요." + exit);
                        continue;
                    }
                    break;
                }
            } catch (Exception ee) {
                ee.printStackTrace();
            }
            return floorTradeList;
        }
    
        // 리스트 출력 함수
        public void roomSelectPrn(List<RoomInfoVo> list) {
            for (RoomInfoVo e : list) {
                System.out.println(blue + "========================================================================" + exit);
                System.out.println(blue + "|                                                                      |" + exit);
                System.out.println(blue + "|                                                                      |" + exit);
                System.out.println(blue + "|                                                                      |" + exit);
                System.out.println(blue + "        사진 URL : " + exit + e.getPhoto_url() + exit);
                System.out.println(blue + "|                                                                      |" + exit);
                System.out.println(blue + "|                                                                      |" + exit);
                System.out.println(blue + "|                                                                      |" + exit);
                System.out.println(blue + "========================================================================" + exit);
                System.out.println(blue + "|       매물번호     : " + e.getHouse_num());
                System.out.println(blue + "|       거래방식     : " + e.getTrade_method());
                System.out.println(blue + "|       보증금      : " + e.getDeposit());
                System.out.println(blue + "|       월세        : " + e.getMonthly());
                System.out.println(blue + "|       전세금      : " + e.getJeonsegeum());
                System.out.println(blue + "|       매매가      : " + e.getSale_price());
                System.out.println(blue + "|       평수        : " + e.getArea());
                System.out.println(blue + "|       사용승인날짜 : " + e.getAccept_date());
                System.out.println(blue + "|       주소        : " + e.getAddress());
                System.out.println(blue + "|       층          : " + e.getFloor1());
                System.out.println(blue + "|       전화번호     : " + e.getPhonenumber());
                System.out.println(blue + "|       등록날짜     : " + e.getRegit_date());
                System.out.println(blue + "|       비고        : " + e.getRemark());
                System.out.println(blue + "========================================================================" + exit);
    
            }
        }
    
        //한번에 입력받은 값에 해당하는 Room데이터를 DB에서 가져오는 함수
        public List<RoomInfoVo> RoomSelect(RoomSearchVo RSV) throws SQLException {
            List<RoomInfoVo> list = new ArrayList<>();
            // 일단 전부 뽑아온다.
            String sql = "SELECT * FROM ROOMINFO WHERE address like ?";
            try {
                conn = Common.getConnection();
                pStmt = conn.prepareStatement(sql);
                pStmt.setString(1, "%" + RSV.getAddress() + "%");
                ResultSet rs = pStmt.executeQuery();
                while (rs.next()) {
                    int house_num = rs.getInt("HOUSE_NUM");
                    String user_id = rs.getString("USER_ID");
                    String photo_url = rs.getString("PHOTO_URL");
                    String trade_method = rs.getString("TRADE_METHOD");
                    int deposit = rs.getInt("DEPOSIT");
                    int monthly = rs.getInt("MONTHLY");
                    int jeonsegeum = rs.getInt("JEONSEGEUM");
                    int Sale_price = rs.getInt("SALE_PRICE");
                    String area = rs.getString("AREA");
                    String address = rs.getString("ADDRESS");
                    String floor1 = rs.getString("FLOOR1");
                    int phonenumber = rs.getInt("PHONENUMBER");
                    String remark = rs.getString("REMARK");
    
                    list.add(new RoomInfoVo(house_num, user_id, photo_url, trade_method, deposit, monthly, jeonsegeum,
                            Sale_price, area, rs.getDate("ACCEPT_DATE"), address, floor1, phonenumber, rs.getDate("REGIT_DATE"), remark));
                }
                Common.close(rs);
                Common.close(pStmt);
                Common.close(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return list;
        }
        public List<RoomInfoVo> RoomSelect1(RoomSearchVo vo){
            List<RoomInfoVo> list=new ArrayList<>();
            String sql=null;
            conn = Common.getConnection();
            try{
                switch (vo.getTrade_method()){
                    case "월세":
                        sql = "SELECT * FROM ROOMINFO WHERE address like ? and deposit<? and monthly<?";
                        pStmt = conn.prepareStatement(sql);
                        pStmt.setString(1, "%" + vo.getAddress() + "%");
                        pStmt.setInt(2,vo.getDeposit());
                        pStmt.setInt(3,vo.getMonthly());
                        rs = pStmt.executeQuery();
                        break;
                    case "전세":
                        sql = "SELECT * FROM ROOMINFO WHERE address like ? and jeonsegeum<? ";
                        pStmt = conn.prepareStatement(sql);
                        pStmt.setString(1, "%" + vo.getAddress() + "%");
                        pStmt.setInt(2,vo.getJeonsegeum());
                        rs = pStmt.executeQuery();
                        break;
                    case "매매":
                        sql = "SELECT * FROM ROOMINFO WHERE address like ? and Sale_price<? ";
                        pStmt = conn.prepareStatement(sql);
                        pStmt.setString(1, "%" + vo.getAddress() + "%");
                        pStmt.setInt(2,vo.getSale_price());
                        rs = pStmt.executeQuery();
                        break;
                }
                while (rs.next()) {
                    int house_num = rs.getInt("HOUSE_NUM");
                    String user_id = rs.getString("USER_ID");
                    String photo_url = rs.getString("PHOTO_URL");
                    String trade_method = rs.getString("TRADE_METHOD");
                    int deposit = rs.getInt("DEPOSIT");
                    int monthly = rs.getInt("MONTHLY");
                    int jeonsegeum = rs.getInt("JEONSEGEUM");
                    int Sale_price = rs.getInt("SALE_PRICE");
                    String area = rs.getString("AREA");
                    String address = rs.getString("ADDRESS");
                    String floor1 = rs.getString("FLOOR1");
                    int phonenumber = rs.getInt("PHONENUMBER");
                    String remark = rs.getString("REMARK");
    
                    list.add(new RoomInfoVo(house_num, user_id, photo_url, trade_method, deposit, monthly, jeonsegeum,
                            Sale_price, area, rs.getDate("ACCEPT_DATE"), address, floor1, phonenumber, rs.getDate("REGIT_DATE"), remark));
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                Common.close(pStmt);
                Common.close(conn);
            }
            return list;
        }
    
    }
    ```
    
- RoomInfoDao
    
    ```java
    package com.kh.Olumgollum_Project.RoomInfo;
    
    import com.kh.Olumgollum_Project.COMMON.Common;
    
    import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.Statement;
    import java.text.ParseException;
    import java.util.Scanner;
    public class RoomInfoDao {
        Connection conn = null;
        Statement stmt = null; // create Statement 방식
        PreparedStatement psmt = null; // Prepared Statement 방식
        ResultSet rs = null; // database 부터 결과를 받는 변수
        Scanner sc = new Scanner(System.in);
    
        public static final String black    = "\u001B[30m" ;
        public static final String red      = "\u001B[31m" ;
        public static final String green    = "\u001B[32m" ;
        public static final String yellow   = "\u001B[33m" ;
        public static final String blue     = "\u001B[34m" ;
        public static final String purple   = "\u001B[35m" ;
        public static final String cyan     = "\u001B[36;m" ;
        public static final String white     = "\u001B[37m" ;
        public static final String exit     = "\u001B[0m" ;
    
        public RoomInfoVo RoomInputFunc() throws ParseException {
            //초기값 설정
            RoomInfoVo rvo = new RoomInfoVo(11, "can3487@naver.com", null, null, 0, 0, 0, 0, null, null, null, null, 0, null, null);
            //날짜 입력받기 위한 SimpleDateFormat 생성.
            // RoomOlum_Page 화면
            System.out.println(blue + "========================================================================" + exit);
            System.out.println(blue+"|"+exit+"                 "+red+"/$$$$$$  /$$       /$$   /$$ /$$      /$$"+exit+"             "+blue+"|"+exit+"\n" +
                    blue+"|"+exit+"                "+red+"/$$__  $$| $$      | $$  | $$| $$$    /$$$"+exit+"             "+blue+"|"+exit+"\n" +
                    blue+"|"+exit+"               "+red+"| $$  \\ $$| $$      | $$  | $$| $$$$  /$$$$"+exit+"             "+blue+"|"+exit+"\n" +
                    blue+"|"+exit+"               "+red+"| $$  | $$| $$      | $$  | $$| $$ $$/$$ $$"+exit+"             "+blue+"|"+exit+"\n" +
                    blue+"|"+exit+"               "+red+"| $$  | $$| $$      | $$  | $$| $$  $$$| $$"+exit+"             "+blue+"|"+exit+"\n" +
                    blue+"|"+exit+"               "+red+"| $$  | $$| $$      | $$  | $$| $$\\  $ | $$"+exit+"             "+blue+"|"+exit+"\n" +
                    blue+"|"+exit+"               "+red+"|  $$$$$$/| $$$$$$$$|  $$$$$$/| $$ \\/  | $$"+exit+"             "+blue+"|"+exit+"\n" +
                    blue+"|"+exit+"                "+red+"\\______/ |________/ \\______/ |__/     |__/"+exit+"             "+blue+"|"+exit);
            System.out.println(blue + "========================================================================" + exit);
            System.out.println(blue+"|"+exit+"                             RoomOlum_Page                             "+blue+"|"+exit);
            System.out.println(blue+"/-------------------\\                                                   |"+exit);
            System.out.println(blue+"|                   |                                                   |"+exit);
            System.out.println(blue+"|                   |                                                   |"+exit);
            System.out.println(blue+"|"+exit+"       사  진       |                                                   "+blue+"|"+exit);
            System.out.println(blue+"|                   |                                                   |"+exit);
            System.out.println(blue+"|                   |                                                   |"+exit);
            System.out.println(blue+"\\-------------------/                                                   |"+exit);
            System.out.print(blue+"|"+exit+" url: ");
            rvo.setPhoto_url(sc.nextLine());
            System.out.println(blue+"|                                                                       |"+exit);
            while (true) {
                System.out.print(blue+"|"+exit+" 거래 방식(매매,전세,월세): ");
                rvo.setTrade_method(sc.nextLine());
                System.out.println(blue+"|                                                                       |"+exit);
                switch (rvo.getTrade_method()) {
                    case "매매":
                        System.out.print(blue+"|"+exit+" 매매가(만원): ");
                        rvo.setSale_price(sc.nextInt());
                        sc.nextLine();
                        System.out.println(blue+"|                                                                       |"+exit);
                        break;
                    case "전세":
                        System.out.print(blue+"|"+exit+" 전세금(만원): ");
                        rvo.setJeonsegeum(sc.nextInt());
                        sc.nextLine();
                        System.out.println(blue+"|                                                                       |"+exit);
                        break;
                    case "월세":
                        System.out.print(blue+"|"+exit+" 보증금(만원): ");
                        rvo.setDeposit(sc.nextInt());
                        sc.nextLine();
                        System.out.println(blue+"|                                                                       |"+exit);
                        System.out.print(blue+"|"+exit+" 월세(만원): ");
                        rvo.setMonthly(sc.nextInt());
                        sc.nextLine();
                        System.out.println(blue+"|                                                                       |"+exit);
                        break;
                    default:
                        System.out.print("다시 입력해주세요.");
                        continue;
                }
                break;
            }
            System.out.print(blue+"|"+exit+" 평수: ");
            rvo.setArea(sc.nextLine());
            System.out.println(blue+"|                                                                       |"+exit);
            System.out.print(blue+"|"+exit+" 사용 승인일(YYYY-MM-DD): ");
            rvo.setAccept_date(java.sql.Date.valueOf(sc.nextLine()));
            System.out.println(blue+"|                                                                       |"+exit);
            System.out.print(blue+"|"+exit+" 주소: ");
            rvo.setAddress(sc.nextLine());
            System.out.println(blue+"|                                                                       |"+exit);
            System.out.print(blue+"|"+exit+" 층수: ");
            rvo.setFloor1(sc.nextLine());
            System.out.println(blue+"|                                                                       |"+exit);
            System.out.print(blue+"|"+exit+" 집주인 전화번호(-빼고): ");
            rvo.setPhonenumber(sc.nextInt());
            sc.nextLine();
            System.out.println(blue+"|                                                                       |"+exit);
            System.out.print(blue+"|"+exit+" 등록일(YYYY-MM-DD): ");
            rvo.setRegit_date(java.sql.Date.valueOf(sc.nextLine()));
            System.out.println(blue+"|                                                                       |"+exit);
            System.out.print(blue+"|"+exit+" 비고(옵션): ");
            rvo.setRemark(sc.nextLine());
            System.out.println(blue+"|                                                                       |"+exit);
            System.out.println(blue + "========================================================================" + exit);
    
            return rvo;
        }
    
        public void RoomInputPrF(RoomInfoVo rvo) {
            System.out.println(blue + "========================================================================" + exit);
            System.out.print(rvo.getHouse_num() + " ");
            System.out.print(rvo.getUser_id()+ " ");
            System.out.print(rvo.getPhoto_url() + " ");
            System.out.print(rvo.getTrade_method() + " ");
            System.out.print(rvo.getDeposit() + " ");
            System.out.print(rvo.getMonthly() + " ");
            System.out.print(rvo.getJeonsegeum() + " ");
            System.out.print(rvo.getSale_price()+ " ");
            System.out.print(rvo.getArea() + " ");
            System.out.print(rvo.getAccept_date() + " ");
            System.out.print(rvo.getAddress() + " ");
            System.out.print(rvo.getFloor1() + " ");
            System.out.print(rvo.getPhonenumber() + " ");
            System.out.print(rvo.getRegit_date() + " ");
            System.out.println(rvo.getRemark() + " ");
            System.out.println(blue + "========================================================================" + exit);
        }
        public void RoomInsert(RoomInfoVo rvo) {
            String query = "INSERT INTO RoomInfo VALUES(SEQ_ROOM.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            try {
                conn = Common.getConnection();
                psmt = conn.prepareStatement(query);
                psmt.setString(1, rvo.getUser_id());
                psmt.setString(2, rvo.getPhoto_url());
                psmt.setString(3, rvo.getTrade_method());
                psmt.setInt(4, rvo.getDeposit());
                psmt.setInt(5, rvo.getMonthly());
                psmt.setInt(6, rvo.getJeonsegeum());
                psmt.setInt(7, rvo.getSale_price());
                psmt.setString(8, rvo.getArea());
                psmt.setDate(9, rvo.getAccept_date());
                psmt.setString(10, rvo.getAddress());
                psmt.setString(11, rvo.getFloor1());
                psmt.setInt(12, rvo.getPhonenumber());
                psmt.setDate(13, rvo.getRegit_date());
                psmt.setString(14, rvo.getRemark());
                psmt.executeUpdate();
                System.out.println(purple+"                          DB에 저장되었습니다!"+exit);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Common.close(psmt);
            Common.close(conn);
        }
    }
    
    ```
    

## VO

- AdminVo
    
    ```java
    package com.kh.Olumgollum_Project.AdminInfo;
    
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;
    
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public class AdminInfoVo {
        private String ADM_ID;
        private String ADM_PW;
    
    }
    
    ```
    
- InfoUpdateVo
    
    ```java
    package com.kh.Olumgollum_Project.LoginInfo;
    
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;
    
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public class InfoUpdateVo {
        private String USER_MAIL;
        private String PASSWORD;
        private String NAME;
        private int PHONE_NUMBER;
        private  String ADDRESS;
        private String NICKNAME;
    }
    
    ```
    
- LoginInfoVo
    
    ```java
    package com.kh.Olumgollum_Project.LoginInfo;
    
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;
    
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public class LoginInfoVo {
        private String user_mail; // 유저 이메일
        private String password; //비밀번호
        private String name; // 이름
        private int first_id_cardnum; //생년월일
        private int phone_number; // 전화번호
        private String address; // float 급여 정보이고 소수점 이하가 존재
        private int gender; // 성과급
        private String nickname; //닉네임
    }
    
    ```
    
- LoginVo
    
    ```java
    package com.kh.Olumgollum_Project.LoginInfo;
    
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;
    
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public class LoginVo {
        private String Userid;
        private String Password;
    
    }
    
    ```
    
- MyPageVo
    
    ```java
    package com.kh.Olumgollum_Project.MyPage;
    
    public class MyPageVo{
        private String USER_MAIL;
        private boolean LOVE_ROOM;
        private String profile_photo;
    
        public MyPageVo(String USER_MAIL, boolean LOVE_ROOM, String profile_photo) {
            this.USER_MAIL = USER_MAIL;
            this.LOVE_ROOM = LOVE_ROOM;
            this.profile_photo = profile_photo;
        }
    
        public String getUSER_MAIL() {
            return USER_MAIL;
        }
    
        public boolean isLOVE_ROOM() {
            return LOVE_ROOM;
        }
    
        public String getProfile_photo() {
            return profile_photo;
        }
    
        public void setUSER_MAIL(String USER_MAIL) {
            this.USER_MAIL = USER_MAIL;
        }
    
        public void setLOVE_ROOM(boolean LOVE_ROOM) {
            this.LOVE_ROOM = LOVE_ROOM;
        }
    
        public void setProfile_photo(String profile_photo) {
            this.profile_photo = profile_photo;
        }
    }
    
    ```
    
- RoomSearchVo
    
    ```java
    package com.kh.Olumgollum_Project.RoomSearch;
    
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;
    
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public class RoomSearchVo {
        private String address;
        private String trade_method;
        private int deposit;
        private int monthly;
        private int jeonsegeum;
        private int Sale_price;
        private String remark;
    }
    
    ```
    
- RoomInfoVo
    
    ```java
    package com.kh.Olumgollum_Project.RoomInfo;
    
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;
    
    import java.sql.Date;
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    
    public class RoomInfoVo {
        private int house_num;
        private String user_id;
        private String photo_url;
        private String trade_method;
        private int deposit;
        private int monthly;
        private int jeonsegeum;
        private int Sale_price;
        private String area;
        private Date accept_date;
        private String address;
        private String floor1;
        private int phonenumber;
        private Date regit_date;
        private String remark;
    }
    
    ```
    

## HTML

- AdminHTML
    - AdminInfoSelect
        
        ```html
        <!DOCTYPE html>
        <html xmlns:th="http:/www.thymeleaf.org">
        <head>
            <meta charset="UTF-8">
            <title>Title</title>
            <link rel="stylesheet" th:href="@{/css/UserInfoPage.css}">
        </head>
        <body class="container">
        <h2>수정된 결과</h2>
        <table >
            <thead>
            <tr>
                <th>아이디</th>
                <th>패스워드</th>
            </tr>
            </thead>
            <tbody>
            <tr th:each="MyInfo: ${MyInfos}">
                <td th:text="${MyInfo.ADM_ID}"></td>
                <td th:text="${MyInfo.ADM_PW}"></td>
            </tr>
            </tbody>
        </table>
        <div>
            <!--Controller에 해당 부분으로 이동!!-->
            <a th:href="@{/Page/AdminMyPage}">
                <button>이전화면으로 돌아가기</button>
            </a>
        </div>
        </body>
        </html>
        ```
        
    - AdminMainPage
        
        ```html
        <!DOCTYPE html>
        <html xmlns:th="http://www.thymeleaf.org">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>AdminOlumgollumMainPage</title>
            <link rel="stylesheet" th:href="@{/css/AdminOlumGollumMain.css}">
        </head>
        <body>
        <h1 class="title">Admin_Olumgollum</h1>
        <div class="button-container">
            <div class="button-row">
        <!--        Controller에 해당 부분으로 이동!-->
                <a th:href="@{/RoomUp/RoomInsert}">
                    <button>방 올룸</button>
                </a>
                <a th:href="@{/RoomSearch/Search}">
                    <button>방 골룸</button>
                </a>
            </div>
            <div class="button-row">
                <a th:href="@{/Page/AdminManage}">
                    <button>관리자 페이지</button>
                </a>
                <a th:href="@{/Page/AdminMyPage}">
                    <button>마이페이지</button>
                </a>
            </div>
        
        </div>
        </body>
        </html>
        ```
        
    - AdminMangePage
        
        ```html
        <!DOCTYPE html>
        <html xmlns:th="http://www.thymeleaf.org">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>AdminOlumgollumMainPage</title>
            <link rel="stylesheet" th:href="@{/css/AdminOlumGollumMain.css}">
        </head>
        <body>
        <h1 class="title">Admin_Olumgollum</h1>
        <div class="button-container">
            <div class="button-row">
                <!--        Controller에 해당 부분으로 이동!-->
                <a th:href="@{/Admin/UserList}">
                    <button>전체회원목록</button>
                </a>
                <a th:href="@{/Admin/UserDelete}">
                    <button>회원삭제</button>
                </a>
            </div>
            <div class="button-row">
                <a th:href="@{/Admin/RoomList}">
                    <button>전체매물목록</button>
                </a>
                <a th:href="@{/Admin/RoomDelete}">
                    <button>매물삭제</button>
                </a>
                <a th:href="@{/Page/AdminMainPage}">
                    <button>이전 페이지로</button>
                </a>
            </div>
            <div class="button-row">
                <a th:href="@{/Admin/AdminList}">
                    <button>관리자목록</button>
                </a>
                <a th:href="@{/Admin/AdminInsert}">
                    <button>관리자 계정 추가</button>
                </a>
                <a th:href="@{/Admin/AdminDelete}">
                    <button>관리자 계정 삭제</button>
                </a>
            </div>
        </div>
        </body>
        </html>
        ```
        
    - AdminMyPage
        
        ```html
        <!DOCTYPE html>
        <html xmlns:th="http://www.thymeleaf.org">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>AdminOlumgollumMainPage</title>
            <link rel="stylesheet" th:href="@{/css/AdminOlumGollumMain.css}">
        </head>
        <body>
        <h1 class="title">Admin_Olumgollum</h1>
        <div class="button-container">
            <div class="button-row">
                <!--        Controller에 해당 부분으로 이동!-->
                <a th:href="@{/Admin/UserList}">
                    <button>전체회원목록</button>
                </a>
                <a th:href="@{/Admin/UserDelete}">
                    <button>회원삭제</button>
                </a>
            </div>
            <div class="button-row">
                <a th:href="@{/Admin/RoomList}">
                    <button>전체매물목록</button>
                </a>
                <a th:href="@{/Admin/RoomDelete}">
                    <button>매물삭제</button>
                </a>
                <a th:href="@{/Page/AdminMainPage}">
                    <button>이전 페이지로</button>
                </a>
            </div>
            <div class="button-row">
                <a th:href="@{/Admin/AdminList}">
                    <button>관리자목록</button>
                </a>
                <a th:href="@{/Admin/AdminInsert}">
                    <button>관리자 계정 추가</button>
                </a>
                <a th:href="@{/Admin/AdminDelete}">
                    <button>관리자 계정 삭제</button>
                </a>
            </div>
        </div>
        </body>
        </html>
        ```
        
    - AdminRoomInfoRst
        
        ```html
        <!DOCTYPE html>
        <html xmlns:th="http://www.thymeleaf.org">
        <head>
            <meta charset="UTF-8">
            <title>RommInfo Insert 성공!</title>
            <link rel="stylesheet" th:href="@{/css/AdminOlumGollumMain.css}">
        </head>
        <body class="container">
        <div class="form-container">
            <h1>방 등록에 성공했습니다!!!!!!!!</h1>
        </div>
        <div>
            <!--Controller에 해당 부분으로 이동!!-->
            <a th:href="@{/Page/AdminMainPage}">
                <button>메인 화면으로 돌아가기</button>
            </a>
        </div>
        </body>
        </html>
        ```
        
    - AdminRoomList
        
        ```html
        <!DOCTYPE html>
        <html xmlns:th="http:/www.thymeleaf.org">
        <head>
            <meta charset="UTF-8">
            <title>Title</title>
            <link rel="stylesheet" th:href="@{/css/RoomInfoPage.css}">
        </head>
        <body class="container">
        <h2>RoomGollum 결과</h2>
        <table>
            <thead>
            <tr>
                <th>매물번호</th>
                <th>집주인아이디</th>
                <th><img src="https://i.ibb.co/yR7yBTv/2.jpg"></th>
                <th>보증금</th>
                <th>월세</th>
                <th>전세</th>
                <th>매매</th>
                <th>면적(평수)</th>
                <th>사용 승인일</th>
                <th>주소</th>
                <th>층수</th>
                <th>전화번호</th>
                <th>매물 등록일</th>
                <th>옵션</th>
            </tr>
            </thead>
            <tbody>
            <tr th:each="RoomInfo:${OulmList}">
                <td th:text="${RoomInfo.house_num}"></td>
                <td th:text="${RoomInfo.user_id}"></td>
                <td th:text="${RoomInfo.photo_url}"></td>
                <td th:text="${RoomInfo.trade_method}"></td>
                <td th:text="${RoomInfo.deposit}"></td>
                <td th:text="${RoomInfo.monthly}"></td>
                <td th:text="${RoomInfo.jeonsegeum}"></td>
                <td th:text="${RoomInfo.Sale_price}"></td>
                <td th:text="${RoomInfo.area}"></td>
                <td th:text="${RoomInfo.accept_date}"></td>
                <td th:text="${RoomInfo.address}"></td>
                <td th:text="${RoomInfo.floor1}"></td>
                <td th:text="${RoomInfo.phonenumber}"></td>
                <td th:text="${RoomInfo.regit_date}"></td>
                <td th:text="${RoomInfo.remark}"></td>
            </tr>
            </tbody>
        </table>
        <div>
            <!--Controller에 해당 부분으로 이동!!-->
            <a th:href="@{/Page/AdminMainPage}">
                <button>메인 화면으로 돌아가기</button>
            </a>
        </div>
        </body>
        </html>
        ```
        
- AdminPageHTML
    - AdminDelete
        
        ```html
        <!DOCTYPE html>
        <html xmlns:th="http://www.thymeleaf.org">
        <head>
            <meta charset="UTF-8">
            <title>관리자 삭제</title>
            <link rel="stylesheet" th:href="@{/css/TitlePontLow.css}">
        </head>
        <body class="container">
        <div class="form-container">
            <h1 class="title">정말 삭제하시겠습니까?</h1>
            <!--    Controller에 해당부분으로 POSTING!-->
            <form th:action="@{/Admin/AdminDelete}" th:object="${AdminDel}" method="post">
                <div>
                    <label for="ADM_ID">삭제할 관리자 아이디</label>
                    <input type="text" id="ADM_ID" th:field="*{ADM_ID}" />
                </div>
                <div>
                    <label for="ADM_PW">삭제할 관리자 패스워드</label>
                    <input type="text" id="ADM_PW" th:field="*{ADM_PW}" />
                </div>
                <div>
                    <button class="page-button">관리자 계정 삭제</button>
                </div>
            </form>
        </div>
        </body>
        </html>
        ```
        
    - AdminDeleteRst
        
        ```html
        <!DOCTYPE html>
        <html xmlns:th="http://www.thymeleaf.org">
        <head>
            <meta charset="UTF-8">
            <title>관리자 계정 삭제 성공!</title>
            <link rel="stylesheet" th:href="@{/css/AdminOlumGollumMain.css}">
        </head>
        <body class="container">
        <div class="form-container">
            <h1>관리자 계정이 삭제되었습니다!!!!!!!!</h1>
        </div>
        <div>
            <!--    Controller에 해당 부분으로 이동!!-->
            <a th:href="@{/Page/AdminManage}">
                <button>돌아가기</button>
            </a>
        </div>
        </body>
        </html>
        ```
        
    - AdminInsert
        
        ```html
        <!DOCTYPE html>
        <html xmlns:th="http://www.thymeleaf.org">
        <head>
            <meta charset="UTF-8">
            <title>New Employee</title>
            <link rel="stylesheet" th:href="@{/css/TitlePontLow.css}">
        </head>
        <body class="container">
        <div class="form-container">
            <h1 class="title">관리자 계정으로 변경</h1>
            <!--    Controller에 해당부분으로 POSTING!-->
            <form th:action="@{/Admin/AdminInsert}" th:object="${AdminUp}" method="post">
                <div>
                    <label for="ADM_ID">변경할 유저 아이디</label>
                    <input type="text" id="ADM_ID" th:field="*{ADM_ID}" />
                </div>
                <div>
                    <label for="ADM_PW">변경할 유저 패스워드</label>
                    <input type="text" id="ADM_PW" th:field="*{ADM_PW}" />
                </div>
                <div>
                    <button class="page-button">관리자로 변경</button>
                </div>
            </form>
        </div>
        </body>
        </html>
        ```
        
    - AdminInsertRst
        
        ```html
        <!DOCTYPE html>
        <html xmlns:th="http://www.thymeleaf.org">
        <head>
            <meta charset="UTF-8">
            <title>관리자 계정 추가 성공!</title>
            <link rel="stylesheet" th:href="@{/css/AdminOlumGollumMain.css}">
        </head>
        <body class="container">
        <div class="form-container">
            <h1>관리자 계정이 추가되었습니다!!!!!!!!</h1>
        </div>
        <div>
            <!--    Controller에 해당 부분으로 이동!!-->
            <a th:href="@{/Page/AdminManage}">
                <button>돌아가기</button>
            </a>
        </div>
        </body>
        </html>
        ```
        
    - AdminList
        
        ```html
        <!DOCTYPE html>
        <html xmlns:th="http:/www.thymeleaf.org">
        <head>
            <meta charset="UTF-8">
            <title>관리자List</title>
            <link rel="stylesheet" th:href="@{/css/RoomInfoPage.css}">
        </head>
        <body class="container">
        <h2>관리자 리스트</h2>
        <table >
            <thead>
            <tr>
                <th>아이디</th>
                <th>패스워드</th>
            </tr>
            </thead>
            <tbody>
            <tr th:each="AdminInfo : ${admin}">
                <td th:text="${AdminInfo.ADM_ID}"></td>
                <td th:text="${AdminInfo.ADM_PW}"></td>
            </tr>
            </tbody>
        </table>
        <div>
            <!--Controller에 해당 부분으로 이동!!-->
            <a th:href="@{/Page/AdminManage}">
                <button>이전화면으로 돌아가기</button>
            </a>
        </div>
        </body>
        </html>
        ```
        
    - RoomDelete
        
        ```html
        <!DOCTYPE html>
        <html xmlns:th="http://www.thymeleaf.org">
        <head>
            <meta charset="UTF-8">
            <title>New Employee</title>
            <link rel="stylesheet" th:href="@{/css/TitlePontLow.css}">
        </head>
        <body class="container">
        <div class="form-container">
            <h1 class="title">정말 삭제하시겠습니까?</h1>
            <!--    Controller에 해당부분으로 POSTING!-->
            <form th:action="@{/Admin/RoomDelete}" th:object="${RoomVo}" method="post">
                <div>
                    <label for="house_num">삭제할 매물번호</label>
                    <input type="text" id="house_num" th:field="*{house_num}" />
                </div>
                <div>
                    <button class="page-button">매물 삭제하기</button>
                </div>
            </form>
        </div>
        </body>
        </html>
        ```
        
    - RoomDeleteRst
        
        ```html
        <!DOCTYPE html>
        <html xmlns:th="http://www.thymeleaf.org">
        <head>
            <meta charset="UTF-8">
            <title>RommInfo Delete 성공!</title>
            <link rel="stylesheet" th:href="@{/css/AdminOlumGollumMain.css}">
        </head>
        <body class="container">
        <div class="form-container">
            <h1>방 매물이 삭제되었습니다!!!!!!!!</h1>
        </div>
        <div>
            <!--    Controller에 해당 부분으로 이동!!-->
            <a th:href="@{/Page/AdminManage}">
                <button>돌아가기</button>
            </a>
        </div>
        </body>
        </html>
        ```
        
    - RoomList
        
        ```html
        <!DOCTYPE html>
        <html xmlns:th="http:/www.thymeleaf.org">
        <head>
            <meta charset="UTF-8">
            <title>Title</title>
            <link rel="stylesheet" th:href="@{/css/RoomInfoPage.css}">
        </head>
        <body class="container">
        <h2>전체매물목록</h2>
        <table>
            <thead>
            <tr>
                <th>매물번호</th>
                <th>등록자 아이디</th>
                <th>사진URL</th>
                <th>거래방식</th>
                <th>보증금(만원)</th>
                <th>월세(만원)</th>
                <th>전세금(만원)</th>
                <th>매매가(만원)</th>
                <th>평수</th>
                <th>사용 승인일</th>
                <th>매물주소</th>
                <th>층수</th>
                <th>집주인 전화번호</th>
                <th>매물 등록일</th>
                <th>비고(옵션)</th>
            </tr>
            </thead>
            <tbody>
            <tr th:each="RoomInfo:${Rooms}">
                <td th:text="${RoomInfo.house_num}"></td>
                <td th:text="${RoomInfo.user_id}"></td>
                <td th:text="${RoomInfo.photo_url}"></td>
                <td th:text="${RoomInfo.trade_method}"></td>
                <td th:text="${RoomInfo.deposit}"></td>
                <td th:text="${RoomInfo.monthly}"></td>
                <td th:text="${RoomInfo.jeonsegeum}"></td>
                <td th:text="${RoomInfo.Sale_price}"></td>
                <td th:text="${RoomInfo.area}"></td>
                <td th:text="${RoomInfo.accept_date}"></td>
                <td th:text="${RoomInfo.address}"></td>
                <td th:text="${RoomInfo.floor1}"></td>
                <td th:text="${RoomInfo.phonenumber}"></td>
                <td th:text="${RoomInfo.regit_date}"></td>
                <td th:text="${RoomInfo.remark}"></td>
            </tr>
            </tbody>
        </table>
        <div>
            <!--Controller에 해당 부분으로 이동!!-->
            <a th:href="@{/Page/AdminManage}">
                <button>이전화면으로 돌아가기</button>
            </a>
        </div>
        </body>
        </html>
        ```
        
    - UserDelete
        
        ```html
        <!DOCTYPE html>
        <html xmlns:th="http://www.thymeleaf.org">
        <head>
            <meta charset="UTF-8">
            <title>New Employee</title>
            <link rel="stylesheet" th:href="@{/css/TitlePontLow.css}">
        </head>
        <body class="container">
        <div class="form-container">
            <h1 class="title">정말 삭제하시겠습니까?</h1>
            <!--    Controller에 해당부분으로 POSTING!-->
            <form th:action="@{/Admin/UserDelete}" th:object="${id}" method="post">
                <div>
                    <label for="Userid">삭제할 유저 아이디</label>
                    <input type="text" id="Userid" th:field="*{Userid}" />
                </div>
                <div>
                    <button class="page-button">유저 삭제하기</button>
                </div>
            </form>
        </div>
        </body>
        </html>
        ```
        
    - UserDeleteRst
        
        ```html
        <!DOCTYPE html>
        <html xmlns:th="http://www.thymeleaf.org">
        <head>
            <meta charset="UTF-8">
            <title>RommInfo Insert 성공!</title>
            <link rel="stylesheet" th:href="@{/css/UserOlumGollumMain.css}">
        </head>
        <body class="container">
        <div class="form-container">
            <h1>유저 계정이 삭제되었습니다!!!!!!!!</h1>
        </div>
        <div>
            <!--    Controller에 해당 부분으로 이동!!-->
            <a th:href="@{/Page/AdminManage}">
                <button>돌아가기</button>
            </a>
        </div>
        </body>
        </html>
        ```
        
    - UserList
        
        ```html
        <!DOCTYPE html>
        <html xmlns:th="http:/www.thymeleaf.org">
        <head>
            <meta charset="UTF-8">
            <title>Title</title>
            <link rel="stylesheet" th:href="@{/css/RoomInfoPage.css}">
        </head>
        <body class="container">
        <h2>전체 회원 목록</h2>
        <table >
            <thead>
            <tr>
                <th>아이디</th>
                <th>패스워드</th>
                <th>이름</th>
                <th>주민번호 앞자리</th>
                <th>전화번호(-빼고)</th>
                <th>주소</th>
                <th>성별</th>
                <th>닉네임</th>
            </tr>
            </thead>
            <tbody>
            <tr th:each="UserInfo : ${UserList}">
                <td th:text="${UserInfo.user_mail}"></td>
                <td th:text="${UserInfo.password}"></td>
                <td th:text="${UserInfo.name}"></td>
                <td th:text="${UserInfo.first_id_cardnum}"></td>
                <td th:text="${UserInfo.phone_number}"></td>
                <td th:text="${UserInfo.address}"></td>
                <td th:text="${UserInfo.gender}"></td>
                <td th:text="${UserInfo.nickname}"></td>
            </tr>
            </tbody>
        </table>
        <div>
            <!--Controller에 해당 부분으로 이동!!-->
            <a th:href="@{/Page/AdminManage}">
                <button>이전화면으로 돌아가기</button>
            </a>
        </div>
        </body>
        </html>
        ```
        
- CommonHTML
    - FailedLoginPage
        
        ```html
        <!DOCTYPE html>
        <html xmlns:th="http://www.thymeleaf.org" lang="en">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>로그인</title>
            <!-- CSS 파일 링크를 수정해야 합니다. -->
            <link rel="stylesheet" th:href="@{/css/LoginPage.css}">
        </head>
        <body>
        <div class="login-container">
            <h2>로그인</h2>
            <!--    Controller에 해당부분으로 POSTING!-->
            <form th:action="@{/Login/FailedCheck}" th:object="${ID_PASSWORD}" method="post">
                <!-- 사용자명 입력 필드 -->
                <label>
                    <input type="text" placeholder="사용자명"  name="Userid" th:field = "*{Userid}">
                </label>
                <!-- 비밀번호 입력 필드 -->
                <label>
                    <input type="password" placeholder="비밀번호"  name="Password" th:field = "*{Password}">
                </label>
                <button type="submit">로그인</button>
            </form>
            <p>잘못입력했습니다. 다시 입력해 주세요.</p>
        </div>
        </body>
        </html>
        ```
        
    - FirstPage
        
        ```html
        <!DOCTYPE html>
        <html xmlns:th="http://www.thymeleaf.org" lang="en">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>선택 화면</title>
            <link rel="stylesheet" th:href="@{/css/UserOlumGollumMain.css}">
        </head>
        <body class="container">
        <h1 class="title">Olumgollum</h1>
        <div class="button-container">
            <div class="button-row">
        <!--        controller에 해당 부분으로 이동-->
                <a th:href="@{/Login/LoginCheck}">
                    <button>로그인하기</button>
                </a>
                <a th:href="@{/Login/UserInsert}">
                    <button>회원가입</button>
                </a>
            </div>
            <div class="button-row">
        <!--        만드는 방법 있으면 만드는 걸로-->
                <a>
                    <button onclick="closeWindow()">나가기</button>
                    <script>
                        function closeWindow() {
                            window.close();
                        }
                    </script>
                </a>
            </div>
        </div>
        
        </body>
        </html>
        ```
        
    - LoginPage
        
        ```html
        <!DOCTYPE html>
        <html xmlns:th="http://www.thymeleaf.org" lang="en">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>로그인</title>
            <!-- CSS 파일 링크를 수정해야 합니다. -->
            <link rel="stylesheet" th:href="@{/css/LoginPage.css}">
        </head>
        <body>
        <div class="login-container">
            <h2>로그인</h2>
        <!--    Controller에 해당부분으로 POSTING!-->
            <form th:action="@{/Login/LoginCheck}" th:object="${ID_PASSWORD}" method="post">
                <!-- 사용자명 입력 필드 -->
                <label>
                    <input type="text" placeholder="사용자명"  name="Userid" th:field = "*{Userid}">
                </label>
                <!-- 비밀번호 입력 필드 -->
                <label>
                    <input type="password" placeholder="비밀번호" name="Password" th:field = "*{Password}" >
                </label>
                <button type="submit">로그인</button>
            </form>
        
        </div>
        </body>
        </html>
        ```
        
    - RoomInfoOlumUp
        
        ```html
        <!DOCTYPE html>
        <html xmlns:th="http://www.thymeleaf.org">
        <head>
            <meta charset="UTF-8">
            <title>New Employee</title>
            <link rel="stylesheet" th:href="@{/css/RoomInfoPage.css}">
        </head>
        <body class="container">
        <div class="form-container">
            <h1 class="title">Olum</h1>
            <!--    Controller에 해당부분으로 POSTING!-->
            <form th:action="@{/RoomUp/RoomInsert}" th:object="${RoomInfoVos}" method="post">
                <div>
                    <label for="photo_url">사진 url</label>
                    <input type="text" id="photo_url" th:field="*{photo_url}" />
                </div>
                <div>
                    <label for="trade_method">거래방식(매매,전세,월세)</label>
                    <input type="text" id="trade_method" th:field="*{trade_method}" />
                </div>
                <div>
                    <label for="deposit">보증금(만원)</label>
                    <input type="text" id="deposit" th:field="*{deposit}" />
                </div>
                <div>
                    <label for="monthly">월세(만원)</label>
                    <input type="text" id="monthly" th:field="*{monthly}" />
                </div>
                <div>
                    <label for="jeonsegeum">전세금(만원)</label>
                    <input type="text" id="jeonsegeum" th:field="*{jeonsegeum}" />
                </div>
                <div>
                    <label for="Sale_price">매매가(만원)</label>
                    <input type="text" id="Sale_price" th:field="*{Sale_price}" />
                </div>
                <div>
                    <label for="area">평수</label>
                    <input type="text" id="area" th:field="*{area}" />
                </div>
                <div>
                    <label for="accept_date">사용 승인일(YYYY-MM-DD)</label>
                    <input type="date" id="accept_date" th:field="*{accept_date}" />
                </div>
                <div>
                    <label for="address">주소</label>
                    <input type="text" id="address" th:field="*{address}" />
                </div>
                <div>
                    <label for="floor1">층수</label>
                    <input type="text" id="floor1" th:field="*{floor1}" />
                </div>
                <div>
                    <label for="phonenumber">집주인 전화번호(-빼고)</label>
                    <input type="text" id="phonenumber" th:field="*{phonenumber}" />
                </div>
                <div>
                    <label for="regit_date">등록일(YYYY-MM-DD)</label>
                    <input type="date" id="regit_date" th:field="*{regit_date}" />
                </div>
                <div>
                    <label for="remark">비고(옵션)</label>
                    <input type="text" id="remark" th:field="*{remark}" />
                </div>
                <div>
                    <button class="page-button">방 등록</button>
                </div>
            </form>
        </div>
        </body>
        </html>
        ```
        
    - RoomSearch
        
        ```html
        <!DOCTYPE html>
        <html xmlns:th="http://www.thymeleaf.org">
        <head>
            <meta charset="UTF-8">
            <title>방 검색하는 페이지</title>
            <link rel="stylesheet" th:href="@{/css/RoomInfoPage.css}">
        </head>
        <body class="container">
        <div class="form-container">
            <h1 class="title">Gollum</h1>
            <!--    Controller에 해당부분으로 POSTING!-->
            <form th:action="@{/RoomSearch/Search}" th:object="${SearchElement}" method="post">
                <div>
                    <label for="address">지역검색</label>
                    <input type="text" id="address" th:field="*{address}"/>
                </div>
                <div>
                    <label for="trade_method">거래 방식</label>
                    <input type="text" id="trade_method" th:field="*{trade_method}"/>
                </div>
                <div>
                    <label for="deposit">보증금</label>
                    <input type="text" id="deposit" th:field="*{deposit}"/>
                </div>
                <div>
                    <label for="monthly">월세</label>
                    <input type="text" id="monthly" th:field="*{monthly}"/>
                </div>
                <div>
                    <label for="jeonsegeum">전세금</label>
                    <input type="text" id="jeonsegeum" th:field="*{jeonsegeum}"/>
                </div>
                <div>
                    <label for="Sale_price">매매가</label>
                    <input type="text" id="Sale_price" th:field="*{Sale_price}"/>
                </div>
                <div>
                    <!--    Controller에 해당 부분으로 이동!!-->
                    <button class="page-button">방 검색하기</button>
                </div>
            </form>
        </div>
        </body>
        </html>
        ```
        
    - UserInsert
        
        ```html
        <!DOCTYPE html>
        <html xmlns:th="http://www.thymeleaf.org">
        <head>
          <meta charset="UTF-8">
          <title>회원가입!</title>
          <link rel="stylesheet" th:href="@{/css/UserInfoPage.css}">
        </head>
        <body class="container">
        <div class="form-container">
          <h1 class="title">회원가입!</h1>
          <!--    Controller에 해당부분으로 POSTING!-->
          <form th:action="@{/Login/UserInsert}" th:object="${UserInsert}" method="post">
            <div>
              <label for="user_mail">아이디</label>
              <input type="text" id="user_mail" th:field="*{user_mail}" />
            </div>
            <div>
              <label for="password">패스워드</label>
              <input type="text" id="password" th:field="*{password}" />
            </div>
            <div>
              <label for="name">이름</label>
              <input type="text" id="name" th:field="*{name}" />
            </div>
            <div>
              <label for="first_id_cardnum">주민번호 앞자리</label>
              <input type="text" id="first_id_cardnum" th:field="*{first_id_cardnum}" />
            </div>
            <div>
              <label for="phone_number">전화번호</label>
              <input type="text" id="phone_number" th:field="*{phone_number}" />
            </div>
            <div>
              <label for="address">주소</label>
              <input type="text" id="address" th:field="*{address}" />
            </div>
            <div>
              <label for="gender">성별(남자[1]여자[2])</label>
              <input type="text" id="gender" th:field="*{gender}" />
            </div>
            <div>
              <label for="nickname">닉네임</label>
              <input type="text" id="nickname" th:field="*{nickname}" />
            </div>
            <div>
              <button class="page-button">등록하기</button>
            </div>
          </form>
        </div>
        </body>
        </html>
        ```
        
    - UserInsetRst
        
        ```html
        <!DOCTYPE html>
        <html xmlns:th="http://www.thymeleaf.org">
        <head>
            <meta charset="UTF-8">
            <title>회원가입 성공!</title>
            <link rel="stylesheet" th:href="@{/css/UserOlumGollumMain.css}">
        </head>
        <body class="container">
        <div class="form-container">
            <h1>환영합니다!!!!!!!!</h1>
        </div>
        <div>
            <!--    Controller에 해당 부분으로 이동!!-->
            <a th:href="@{/Page/FirstPage}">
                <button>이전화면으로 돌아가기</button>
            </a>
        </div>
        </body>
        </html>
        ```
        
- MyPageHTML
    - AcccountDelete
        
        ```html
        <!DOCTYPE html>
        <html xmlns:th="http://www.thymeleaf.org">
        <head>
            <meta charset="UTF-8">
            <title>New Employee</title>
            <link rel="stylesheet" th:href="@{/css/TitlePontLow.css}">
        </head>
        <body class="container">
        <div class="form-container">
            <h1 class="title">정말 삭제하시겠습니까?</h1>
            <!--    Controller에 해당부분으로 POSTING!-->
            <form th:action="@{/MyPage/UserDelete}" th:object="${id}" method="post">
                <div>
                    <button class="page-button">회원탈퇴하기</button>
                </div>
            </form>
        </div>
        </body>
        </html>
        ```
        
    - AdminDeleteRst
        
        ```html
        <!DOCTYPE html>
        <html xmlns:th="http://www.thymeleaf.org">
        <head>
            <meta charset="UTF-8">
            <title>회원탈퇴 성공!</title>
            <link rel="stylesheet" th:href="@{/css/AdminOlumGollumMain.css}">
        </head>
        <body class="container">
        <div class="form-container">
            <h1>게정을 탈퇴했습니다!!!!!!!!</h1>
        </div>
        <div>
            <!--    Controller에 해당 부분으로 이동!!-->
            <a th:href="@{/Page/FirstPage}">
                <button>돌아가기</button>
            </a>
        </div>
        </body>
        </html>
        ```
        
    - AdminInfoUpdate
        
        ```html
        <!DOCTYPE html>
        <html xmlns:th="http://www.thymeleaf.org">
        <head>
            <meta charset="UTF-8">
            <title>New Employee</title>
            <link rel="stylesheet" th:href="@{/css/UserInfoPage.css}">
        </head>
        <body class="container">
        <div class="form-container">
            <h1 class="title">회원정보변경</h1>
            <!--    Controller에 해당부분으로 POSTING!-->
            <form th:action="@{/MyPage/AdminInfoUpdate}" th:object="${UpdateElements}" method="post">
                <div>
                    <label for="ADM_ID">아이디</label>
                    <input type="text" id="ADM_ID" th:field="*{ADM_ID}" />
                </div>
                <div>
                    <label for="ADM_PW">패스워드</label>
                    <input type="text" id="ADM_PW" th:field="*{ADM_PW}" />
                <div>
                    <button class="page-button">수정하기</button>
                </div>
            </form>
        </div>
        </body>
        </html>
        ```
        
    - AdminLoveRoomDeleteRst
        
        ```html
        <!DOCTYPE html>
        <html xmlns:th="http://www.thymeleaf.org">
        <head>
            <meta charset="UTF-8">
            <title>찜 삭제!</title>
            <link rel="stylesheet" th:href="@{/css/TitlePontLow.css}">
        </head>
        <body class="container">
        <div class="form-container">
            <h1>해당 찜이 삭제되었습니다!!!!!!!!</h1>
        </div>
        <div>
            <!--    Controller에 해당 부분으로 이동!!-->
            <a th:href="@{/Page/AdminMyPage}">
                <button>돌아가기</button>
            </a>
        </div>
        </body>
        </html>
        ```
        
    - AdminLoveRoomList
        
        ```html
        <!DOCTYPE html>
        <html xmlns:th="http:/www.thymeleaf.org">
        <head>
            <meta charset="UTF-8">
            <title>Title</title>
            <link rel="stylesheet" th:href="@{/css/RoomInfoPage.css}">
        </head>
        <body class="container">
        <h2>찜 한방 목록</h2>
        <table>
            <thead>
            <tr>
                <th>매물번호</th>
                <th>등록자 아이디</th>
                <th>사진URL</th>
                <th>거래방식</th>
                <th>보증금(만원)</th>
                <th>월세(만원)</th>
                <th>전세금(만원)</th>
                <th>매매가(만원)</th>
                <th>평수</th>
                <th>사용 승인일</th>
                <th>매물주소</th>
                <th>층수</th>
                <th>집주인 전화번호</th>
                <th>매물 등록일</th>
                <th>비고(옵션)</th>
            </tr>
            </thead>
            <tbody>
            <tr th:each="Oulm:${LoveRooms}">
                <td th:text="${Oulm.house_num}"></td>
                <td th:text="${Oulm.user_id}"></td>
                <td th:text="${Oulm.photo_url}"></td>
                <td th:text="${Oulm.trade_method}"></td>
                <td th:text="${Oulm.deposit}"></td>
                <td th:text="${Oulm.monthly}"></td>
                <td th:text="${Oulm.jeonsegeum}"></td>
                <td th:text="${Oulm.Sale_price}"></td>
                <td th:text="${Oulm.area}"></td>
                <td th:text="${Oulm.accept_date}"></td>
                <td th:text="${Oulm.address}"></td>
                <td th:text="${Oulm.floor1}"></td>
                <td th:text="${Oulm.phonenumber}"></td>
                <td th:text="${Oulm.regit_date}"></td>
                <td th:text="${Oulm.remark}"></td>
            </tr>
            </tbody>
        </table>
        <div>
            <!--Controller에 해당 부분으로 이동!!-->
            <a th:href="@{/Page/AdminMyPage}">
                <button>이전 화면으로 돌아가기</button>
            </a>
        </div>
        </body>
        </html>
        ```
        
    - AdminOlumList
        
        ```html
        <!DOCTYPE html>
        <html xmlns:th="http:/www.thymeleaf.org">
        <head>
            <meta charset="UTF-8">
            <title>Title</title>
            <link rel="stylesheet" th:href="@{/css/RoomInfoPage.css}">
        </head>
        <body class="container">
        <h2>OLUM 목록</h2>
        <table>
            <thead>
            <tr>
                <th>매물번호</th>
                <th>집주인아이디</th>
                <th>사진URL</th>
                <th>거래방식</th>
                <th>보증금</th>
                <th>월세</th>
                <th>전세금</th>
                <th>매매가</th>
                <th>면적(평수)</th>
                <th>사용 승인일</th>
                <th>주소</th>
                <th>층수</th>
                <th>전화번호</th>
                <th>매물 등록일</th>
                <th>옵션</th>
            </tr>
            </thead>
            <tbody>
            <tr th:each="RoomInfo:${OulmList}">
                <td th:text="${RoomInfo.house_num}"></td>
                <td th:text="${RoomInfo.user_id}"></td>
                <td th:text="${RoomInfo.photo_url}"></td>
                <td th:text="${RoomInfo.trade_method}"></td>
                <td th:text="${RoomInfo.deposit}"></td>
                <td th:text="${RoomInfo.monthly}"></td>
                <td th:text="${RoomInfo.jeonsegeum}"></td>
                <td th:text="${RoomInfo.Sale_price}"></td>
                <td th:text="${RoomInfo.area}"></td>
                <td th:text="${RoomInfo.accept_date}"></td>
                <td th:text="${RoomInfo.address}"></td>
                <td th:text="${RoomInfo.floor1}"></td>
                <td th:text="${RoomInfo.phonenumber}"></td>
                <td th:text="${RoomInfo.regit_date}"></td>
                <td th:text="${RoomInfo.remark}"></td>
            </tr>
            </tbody>
        </table>
        <div>
            <!--Controller에 해당 부분으로 이동!!-->
            <a th:href="@{/Page/AdminMyPage}">
                <button>이전화면으로 돌아가기</button>
            </a>
        </div>
        </body>
        </html>
        ```
        
    - RoomOlumDeleteRst
        
        ```html
        <!DOCTYPE html>
        <html xmlns:th="http://www.thymeleaf.org">
        <head>
            <meta charset="UTF-8">
            <title>Olum Delete 성공!</title>
            <link rel="stylesheet" th:href="@{/css/AdminOlumGollumMain.css}">
        </head>
        <body class="container">
        <div class="form-container">
            <h1>방 매물이 삭제되었습니다!!!!!!!!</h1>
        </div>
        <div>
            <!--    Controller에 해당 부분으로 이동!!-->
            <a th:href="@{/Page/AdminMyPage}">
                <button>돌아가기</button>
            </a>
        </div>
        </body>
        </html>
        ```
        
    - RoomOlumUpdateRst
        
        ```html
        <!DOCTYPE html>
        <html xmlns:th="http://www.thymeleaf.org">
        <head>
            <meta charset="UTF-8">
            <title>올린 방 수정 성공!</title>
            <link rel="stylesheet" th:href="@{/css/UserOlumGollumMain.css}">
        </head>
        <body class="container">
        <div class="form-container">
            <h1>방 수정에 성공했습니다!!!!!!!!</h1>
        </div>
        <div>
            <!--    Controller에 해당 부분으로 이동!!-->
            <a th:href="@{/Page/AdminMyPage}">
                <button>이전화면으로 돌아가기</button>
            </a>
        </div>
        </body>
        </html>
        ```
        
    - InfoUpdate
        
        ```html
        <!DOCTYPE html>
        <html xmlns:th="http://www.thymeleaf.org">
        <head>
            <meta charset="UTF-8">
            <title>New Employee</title>
            <link rel="stylesheet" th:href="@{/css/UserInfoPage.css}">
        </head>
        <body class="container">
        <div class="form-container">
            <h1 class="title">회원정보변경</h1>
            <!--    Controller에 해당부분으로 POSTING!-->
            <form th:action="@{/MyPage/InfoUpdate}" th:object="${UpdateElements}" method="post">
                <div>
                    <label for="USER_MAIL">아이디</label>
                    <input type="text" id="USER_MAIL" th:field="*{USER_MAIL}" />
                </div>
                <div>
                    <label for="PASSWORD">패스워드</label>
                    <input type="text" id="PASSWORD" th:field="*{PASSWORD}" />
                </div>
                <div>
                    <label for="NAME">이름</label>
                    <input type="text" id="NAME" th:field="*{NAME}" />
                </div>
                <div>
                    <label for="PHONE_NUMBER">전화번호</label>
                    <input type="text" id="PHONE_NUMBER" th:field="*{PHONE_NUMBER}" />
                </div>
                <div>
                    <label for="ADDRESS">주소</label>
                    <input type="text" id="ADDRESS" th:field="*{ADDRESS}" />
                </div>
                <div>
                    <label for="NICKNAME">닉네임</label>
                    <input type="text" id="NICKNAME" th:field="*{NICKNAME}" />
                </div>
                <div>
                    <button class="page-button">수정하기</button>
                </div>
            </form>
        </div>
        </body>
        </html>
        ```
        
    - LoveRoomDelete
        
        ```html
        <!DOCTYPE html>
        <html xmlns:th="http://www.thymeleaf.org">
        <head>
            <meta charset="UTF-8">
            <title>찜 취소하기</title>
            <link rel="stylesheet" th:href="@{/css/TitlePontLow.css}">
        </head>
        <body class="container">
        <div class="form-container">
            <h1 class="title">정말 삭제하시겠습니까?</h1>
            <!--    Controller에 해당부분으로 POSTING!-->
            <form th:action="@{/MyPage/LoveRoomDelete}" th:object="${Lovedel}" method="post">
                <div>
                    <label for="Shouse_num">삭제할 매물번호</label>
                    <input type="text" id="Shouse_num" th:field="*{Shouse_num}" />
                </div>
                <div>
                    <button class="page-button">찜 삭제하기</button>
                </div>
            </form>
        </div>
        </body>
        </html>
        ```
        
    - OlumUpdate
        
        ```html
        <!DOCTYPE html>
        <html xmlns:th="http://www.thymeleaf.org">
        <head>
            <meta charset="UTF-8">
            <title>New Employee</title>
            <link rel="stylesheet" th:href="@{/css/RoomInfoPage.css}">
        </head>
        <body class="container">
        <div class="form-container">
            <h1 class="title">올린 방 수정!(전부 입력바랍니다.)</h1>
            <!--    Controller에 해당부분으로 POSTING!-->
            <form th:action="@{/MyPage/OulmUpdate}" th:object="${UpdateElements}" method="post">
                <div>
                    <label for="house_num">수정할 매물번호</label>
                    <input type="text" id="house_num" th:field="*{house_num}" />
                </div>
                <div>
                    <label for="photo_url">사진 url</label>
                    <input type="text" id="photo_url" th:field="*{photo_url}" />
                </div>
                <div>
                    <label for="trade_method">거래방식(매매,전세,월세)</label>
                    <input type="text" id="trade_method" th:field="*{trade_method}" />
                </div>
                <div>
                    <label for="deposit">보증금(만원)</label>
                    <input type="text" id="deposit" th:field="*{deposit}" />
                </div>
                <div>
                    <label for="monthly">월세(만원)</label>
                    <input type="text" id="monthly" th:field="*{monthly}" />
                </div>
                <div>
                    <label for="jeonsegeum">전세금(만원)</label>
                    <input type="text" id="jeonsegeum" th:field="*{jeonsegeum}" />
                </div>
                <div>
                    <label for="Sale_price">매매가(만원)</label>
                    <input type="text" id="Sale_price" th:field="*{Sale_price}" />
                </div>
                <div>
                    <label for="area">평수</label>
                    <input type="text" id="area" th:field="*{area}" />
                </div>
                <div>
                    <label for="address">주소</label>
                    <input type="text" id="address" th:field="*{address}" />
                </div>
                <div>
                    <label for="floor1">층수</label>
                    <input type="text" id="floor1" th:field="*{floor1}" />
                </div>
                <div>
                    <label for="phonenumber">집주인 전화번호(-빼고)</label>
                    <input type="text" id="phonenumber" th:field="*{phonenumber}" />
                </div>
                <div>
                    <label for="remark">비고(옵션)</label>
                    <input type="text" id="remark" th:field="*{remark}" />
                </div>
                <div>
                    <button class="page-button">방 수정</button>
                </div>
            </form>
        </div>
        </body>
        </html>
        ```
        
    - OlumDelete
        
        ```html
        <!DOCTYPE html>
        <html xmlns:th="http://www.thymeleaf.org">
        <head>
            <meta charset="UTF-8">
            <title>Olum Delete</title>
            <link rel="stylesheet" th:href="@{/css/TitlePontLow.css}">
        </head>
        <body class="container">
        <div class="form-container">
            <h1 class="title">정말 삭제하시겠습니까?</h1>
            <!--    Controller에 해당부분으로 POSTING!-->
            <form th:action="@{/MyPage/OulmDelete}" th:object="${DeleteNum}" method="post">
                <div>
                    <label for="house_num">삭제할 매물번호</label>
                    <input type="text" id="house_num" th:field="*{house_num}" />
                </div>
                <div>
                    <button class="page-button">방 삭제하기</button>
                </div>
            </form>
        </div>
        </body>
        </html>
        ```
        
    - UserDeleteRst
        
        ```html
        <!DOCTYPE html>
        <html xmlns:th="http://www.thymeleaf.org">
        <head>
            <meta charset="UTF-8">
            <title>회원탈퇴 성공!</title>
            <link rel="stylesheet" th:href="@{/css/AdminOlumGollumMain.css}">
        </head>
        <body class="container">
        <div class="form-container">
            <h1>게정을 탈퇴했습니다!!!!!!!!</h1>
        </div>
        <div>
            <!--    Controller에 해당 부분으로 이동!!-->
            <a th:href="@{/Page/FirstPage}">
                <button>돌아가기</button>
            </a>
        </div>
        </body>
        </html>
        ```
        
    - UserLoveRoomDeleteRst
        
        ```html
        <!DOCTYPE html>
        <html xmlns:th="http://www.thymeleaf.org">
        <head>
            <meta charset="UTF-8">
            <title>찜 삭제!</title>
            <link rel="stylesheet" th:href="@{/css/TitlePontLow.css}">
        </head>
        <body class="container">
        <div class="form-container">
            <h1>해당 찜이 삭제되었습니다!!!!!!!!</h1>
        </div>
        <div>
            <!--    Controller에 해당 부분으로 이동!!-->
            <a th:href="@{/Page/UserMyPage}">
                <button>돌아가기</button>
            </a>
        </div>
        </body>
        </html>
        ```
        
    - UserLoveRoomList
        
        ```html
        <!DOCTYPE html>
        <html lang="en">
        <head>
            <meta charset="UTF-8">
            <title>Title</title>
        </head>
        <body>
        
        </body>
        </html><!DOCTYPE html>
        <html xmlns:th="http:/www.thymeleaf.org">
        <head>
            <meta charset="UTF-8">
            <title>Title</title>
            <link rel="stylesheet" th:href="@{/css/RoomInfoPage.css}">
        </head>
        <body class="container">
        <h2>찜한 방 목록</h2>
        <table>
            <thead>
            <tr>
                <th>매물번호</th>
                <th>등록자 아이디</th>
                <th>사진URL</th>
                <th>거래방식</th>
                <th>보증금(만원)</th>
                <th>월세(만원)</th>
                <th>전세금(만원)</th>
                <th>매매가(만원)</th>
                <th>평수</th>
                <th>사용 승인일</th>
                <th>매물주소</th>
                <th>층수</th>
                <th>집주인 전화번호</th>
                <th>매물 등록일</th>
                <th>비고(옵션)</th>
            </tr>
            </thead>
            <tbody>
            <tr th:each="RoomInfo:${LoveRooms}">
                <td th:text="${RoomInfo.house_num}"></td>
                <td th:text="${RoomInfo.user_id}"></td>
                <td th:text="${RoomInfo.photo_url}"></td>
                <td th:text="${RoomInfo.trade_method}"></td>
                <td th:text="${RoomInfo.deposit}"></td>
                <td th:text="${RoomInfo.monthly}"></td>
                <td th:text="${RoomInfo.jeonsegeum}"></td>
                <td th:text="${RoomInfo.Sale_price}"></td>
                <td th:text="${RoomInfo.area}"></td>
                <td th:text="${RoomInfo.accept_date}"></td>
                <td th:text="${RoomInfo.address}"></td>
                <td th:text="${RoomInfo.floor1}"></td>
                <td th:text="${RoomInfo.phonenumber}"></td>
                <td th:text="${RoomInfo.regit_date}"></td>
                <td th:text="${RoomInfo.remark}"></td>
            </tr>
            </tbody>
        </table>
        <div>
            <!--Controller에 해당 부분으로 이동!!-->
            <a th:href="@{/Page/UserMyPage}">
                <button>이전 화면으로 돌아가기</button>
            </a>
        </div>
        </body>
        </html>
        ```
        
    - UserOlumList
        
        ```html
        <!DOCTYPE html>
        <html xmlns:th="http:/www.thymeleaf.org">
        <head>
            <meta charset="UTF-8">
            <title>Title</title>
            <link rel="stylesheet" th:href="@{/css/RoomInfoPage.css}">
        </head>
        <body class="container">
        <h2>OLUM목록</h2>
        <table>
            <thead>
            <tr>
                <th>매물번호</th>
                <th>등록자 아이디</th>
                <th>사진URL</th>
                <th>거래방식</th>
                <th>보증금(만원)</th>
                <th>월세(만원)</th>
                <th>전세금(만원)</th>
                <th>매매가(만원)</th>
                <th>평수</th>
                <th>사용 승인일</th>
                <th>매물주소</th>
                <th>층수</th>
                <th>집주인 전화번호</th>
                <th>매물 등록일</th>
                <th>비고(옵션)</th>
            </tr>
            </thead>
            <tbody>
            <tr th:each="Oulm:${OulmList}">
                <td th:text="${Oulm.house_num}"></td>
                <td th:text="${Oulm.user_id}"></td>
                <td th:text="${Oulm.photo_url}"></td>
                <td th:text="${Oulm.trade_method}"></td>
                <td th:text="${Oulm.deposit}"></td>
                <td th:text="${Oulm.monthly}"></td>
                <td th:text="${Oulm.jeonsegeum}"></td>
                <td th:text="${Oulm.Sale_price}"></td>
                <td th:text="${Oulm.area}"></td>
                <td th:text="${Oulm.accept_date}"></td>
                <td th:text="${Oulm.address}"></td>
                <td th:text="${Oulm.floor1}"></td>
                <td th:text="${Oulm.phonenumber}"></td>
                <td th:text="${Oulm.regit_date}"></td>
                <td th:text="${Oulm.remark}"></td>
            </tr>
            </tbody>
        </table>
        <div>
            <!--Controller에 해당 부분으로 이동!!-->
            <a th:href="@{/Page/UserMyPage}">
                <button>이전화면으로 돌아가기</button>
            </a>
        </div>
        </body>
        </html>
        ```
        
    - UserOlumDeleteRst
        
        ```html
        <!DOCTYPE html>
        <html xmlns:th="http://www.thymeleaf.org">
        <head>
            <meta charset="UTF-8">
            <title>Olum Delete 성공!</title>
            <link rel="stylesheet" th:href="@{/css/AdminOlumGollumMain.css}">
        </head>
        <body class="container">
        <div class="form-container">
            <h1>방 매물이 삭제되었습니다!!!!!!!!</h1>
        </div>
        <div>
            <!--    Controller에 해당 부분으로 이동!!-->
            <a th:href="@{/Page/UserMyPage}">
                <button>돌아가기</button>
            </a>
        </div>
        </body>
        </html>
        ```
        
    - UserOlumUpdateRst
        
        ```html
        <!DOCTYPE html>
        <html xmlns:th="http://www.thymeleaf.org">
        <head>
            <meta charset="UTF-8">
            <title>올린 방 수정 성공!</title>
            <link rel="stylesheet" th:href="@{/css/UserOlumGollumMain.css}">
        </head>
        <body class="container">
        <div class="form-container">
            <h1>방 수정에 성공했습니다!!!!!!!!</h1>
        </div>
        <div>
            <!--    Controller에 해당 부분으로 이동!!-->
            <a th:href="@{/Page/UserMyPage}">
                <button>이전화면으로 돌아가기</button>
            </a>
        </div>
        </body>
        </html>
        ```
        
- UserHTML
    - UserInfoSelect
        
        ```html
        <!DOCTYPE html>
        <html xmlns:th="http:/www.thymeleaf.org">
        <head>
            <meta charset="UTF-8">
            <title>Title</title>
            <link rel="stylesheet" th:href="@{/css/UserInfoPage.css}">
        </head>
        <body class="container">
        <h2>수정된 결과</h2>
        <table >
            <thead>
            <tr>
                <th>아이디</th>
                <th>패스워드</th>
                <th>이름</th>
                <th>주민번호 앞자리</th>
                <th>전화번호(-빼고)</th>
                <th>주소</th>
                <th>성별</th>
                <th>닉네임</th>
            </tr>
            </thead>
            <tbody>
            <tr th:each="MyInfo : ${MyInfos}">
                <td th:text="${MyInfo.user_mail}"></td>
                <td th:text="${MyInfo.password}"></td>
                <td th:text="${MyInfo.name}"></td>
                <td th:text="${MyInfo.first_id_cardnum}"></td>
                <td th:text="${MyInfo.phone_number}"></td>
                <td th:text="${MyInfo.address}"></td>
                <td th:text="${MyInfo.gender}"></td>
                <td th:text="${MyInfo.nickname}"></td>
            </tr>
            </tbody>
        </table>
        <div>
            <!--Controller에 해당 부분으로 이동!!-->
            <a th:href="@{/Page/UserMyPage}">
                <button>이전화면으로 돌아가기</button>
            </a>
        </div>
        </body>
        </html>
        ```
        
    - UserMainPage
        
        ```html
        <!DOCTYPE html>
        <html xmlns:th="http://www.thymeleaf.org">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>OlumgollumMainPage</title>
            <link rel="stylesheet" th:href="@{/css/UserOlumGollumMain.css}">
        </head>
        <body class="container">
        <h1 class="title">Olumgollum</h1>
        <div class="button-container">
            <div class="button-row">
        <!--        Controller에 해당부분으로 이동!!-->
                <a th:href="@{/RoomUp/RoomInsert}">
                    <button>방 올룸</button>
                </a>
                <a th:href="@{/RoomSearch/Search}">
                    <button>방 골룸</button>
                </a>
            </div>
            <div class="button-row">
                <a th:href="@{/Page/UserMyPage}">
                    <button>마이페이지</button>
                </a>
            </div>
        </div>
        </body>
        </html>
        ```
        
    - UserMyPage
        
        ```html
        <!DOCTYPE html>
        <html xmlns:th="http://www.thymeleaf.org">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>UserMyPage</title>
            <link rel="stylesheet" th:href="@{/css/UserOlumGollumMain.css}">
        </head>
        <body>
        <h1 class="title">마이페이지</h1>
        <div class="button-container">
            <div class="button-row">
                <!--        Controller에 해당 부분으로 이동!-->
                <a th:href="@{/MyPage/InfoUpdate}">
                    <button>회원정보변경</button>
                </a>
                <a th:href="@{/MyPage/LoveRoomList}">
                    <button>찜한방목록</button>
                </a>
                <a th:href="@{/MyPage/LoveRoomDelete}">
                    <button>찜삭제</button>
                </a>
            </div>
            <div class="button-row">
                <!--        Controller에 해당 부분으로 이동!-->
                <a th:href="@{/MyPage/OulmList}">
                    <button>올룸목록</button>
                </a>
                <a th:href="@{/MyPage/OulmUpdate}">
                    <button>올룸수정</button>
                </a>
                <a th:href="@{/MyPage/OulmDelete}">
                    <button>올룸삭제</button>
                </a>
            </div>
            <div class="button-row">
                <a th:href="@{/Page/UserMainPage}">
                    <button>돌아가기</button>
                </a>
                <a th:href="@{/MyPage/UserDelete}">
                    <button>회원탈퇴</button>
                </a>
                <a th:href="@{/Page/FirstPage}">
                    <button>로그아웃</button>
                </a>
            </div>
        
        </div>
        </body>
        </html>
        ```
        
    - UserRoomInfoRst
        
        ```html
        <!DOCTYPE html>
        <html xmlns:th="http://www.thymeleaf.org">
        <head>
            <meta charset="UTF-8">
            <title>RommInfo Insert 성공!</title>
            <link rel="stylesheet" th:href="@{/css/UserOlumGollumMain.css}">
        </head>
        <body class="container">
        <div class="form-container">
            <h1>방 등록에 성공했습니다!!!!!!!!</h1>
        </div>
        <div>
        <!--    Controller에 해당 부분으로 이동!!-->
            <a th:href="@{/Page/UserMainPage}">
                <button>메인 화면으로 돌아가기</button>
            </a>
        </div>
        </body>
        </html>
        ```
        
    - UserRoomList
        
        ```html
        <!DOCTYPE html>
        <html xmlns:th="http:/www.thymeleaf.org">
        <head>
            <meta charset="UTF-8">
            <title>Title</title>
            <link rel="stylesheet" th:href="@{/css/RoomInfoPage.css}">
        </head>
        <body class="container">
        <h2>회원목록</h2>
        <table>
            <thead>
            <tr>
                <th>매물번호</th>
                <th>등록자 아이디</th>
                <th></th>
                <th>거래방식</th>
                <th>보증금(만원)</th>
                <th>월세(만원)</th>
                <th>전세금(만원)</th>
                <th>매매가(만원)</th>
                <th>평수</th>
                <th>사용 승인일</th>
                <th>매물주소</th>
                <th>층수</th>
                <th>집주인 전화번호</th>
                <th>매물 등록일</th>
                <th>비고(옵션)</th>
            </tr>
            </thead>
            <tbody>
        
            <tr th:each="RoomInfo:${Rooms}">
                <td th:text="${RoomInfo.house_num}"></td>
                <td th:text="${RoomInfo.user_id}"></td>
                <td ><img src="https://i.ibb.co/yR7yBTv/2.jpg"></td>
                <td th:text="${RoomInfo.trade_method}"></td>
                <td th:text="${RoomInfo.deposit}"></td>
                <td th:text="${RoomInfo.monthly}"></td>
                <td th:text="${RoomInfo.jeonsegeum}"></td>
                <td th:text="${RoomInfo.Sale_price}"></td>
                <td th:text="${RoomInfo.area}"></td>
                <td th:text="${RoomInfo.accept_date}"></td>
                <td th:text="${RoomInfo.address}"></td>
                <td th:text="${RoomInfo.floor1}"></td>
                <td th:text="${RoomInfo.phonenumber}"></td>
                <td th:text="${RoomInfo.regit_date}"></td>
                <td th:text="${RoomInfo.remark}"></td>
            </tr>
            </tbody>
        </table>
        <div>
            <!--Controller에 해당 부분으로 이동!!-->
            <a th:href="@{/Page/UserMainPage}">
                <button>메인 화면으로 돌아가기</button>
            </a>
        </div>
        </body>
        </html>
        ```
        

## CSS

- AdminInsert
    
    ```css
    table {
        border-collapse: collapse;
    }
    
    th, td {
        border:1px solid #ccc;
    }
    td {
        background-color: beige;
    }
    
    thead {
        background-color: cornflowerblue;
        color: white;
    }
    .title {
      font-size: 50px;
      font-weight: bold;
      text-shadow: 2px 2px 4px rgba(11, 54, 103, 0.5);
      color: rgb(151, 115, 221);
      text-align: center; /* 가운데 정렬 */
      margin-top: 70px; /* 위로 70px 이동 */
    }
    
    .form-container {
        max-width: 400px;
        margin: 0 auto;
        padding: 20px;
        background-color: #f7f7f7;
        border: 1px solid #ddd;
        border-radius: 5px;
    }
    
    label {
        display: block;
        margin-bottom: 5px;
    }
    
    input[type="text"],
    input[type="date"] {
        width: 90%;
        padding: 10px;
        margin-bottom: 15px;
        border: 1px solid #ccc;
        border-radius: 3px;
    }
      .page-button {
        padding: 10px 20px;
        background-color: #4CAF50;
        color: #fff;
        border: none;
        border-radius: 3px;
        cursor: pointer;
      }
    
      .page-button:hover {
        background-color: #45a049;
      }
      button {
        padding: 20px 40px;
        margin: 0 70px; /* 나가기 버튼의 크기만큼 띄우기 */
        font-size: 20px;
        background-color: #4CAF50;
        color: #fff;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        transition: background-color 0.3s;
      }
    .container {
        background-image: url('../images/골룸.jpg');
        background-size: cover; /* 이미지가 화면에 맞게 확대/축소되도록 설정 */
        background-repeat: no-repeat; /* 이미지 반복 없음 */
        background-attachment: fixed; /* 스크롤 시 배경 이미지가 고정됨 */
    }
    
    ```
    
- AdminOlumGollumMain
    
    ```css
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      height: calc(100vh - 20px); /* 전체 높이에서 20px 뺌 */
      background-image: url('/images/골룸.jpg');
      background-size: cover;
      background-position: center;
    }
    
    .title {
      font-size: 128px;
      font-weight: bold;
      text-shadow: 2px 2px 4px rgba(11, 54, 103, 0.5);
      color: rgb(151, 115, 221); /* 제목 색상을 빨강으로 변경 */
      margin-bottom: 50px; /* 제목의 글자 크기만큼 위로 올리기 */
    }
    
    .button-container {
      margin-top: 60px;
      display: flex;
      flex-direction: column;
    }
    
    .button-row {
      display: flex;
      justify-content: center;
      margin-bottom: 60px;
    }
    
    button {
      padding: 20px 40px;
      margin: 0 70px; /* 나가기 버튼의 크기만큼 띄우기 */
      font-size: 20px;
      background-color: #4CAF50;
      color: #fff;
      border: none;
      border-radius: 4px;
      cursor: pointer;
      transition: background-color 0.3s;
    }
    
    button:hover {
      background-color: #45a049;
    }
    ```
    
- LoginPage
    
    ```css
    body {
      font-family: Arial, sans-serif;
      background-color: #f2f2f2;
      margin: 0;
      padding: 0;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
    }
    
    .login-container {
      background-color: #fff;
      border-radius: 8px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
      padding: 60px; /* 패딩을 늘려 컨테이너를 더 크게 만듭니다. */
      width: 500px; /* 더 큰 너비로 조정합니다. */
      text-align: center;
    }
    
    .login-container h2 {
      margin-bottom: 30px; /* 제목과 입력 필드 사이의 간격을 더욱 넓게 만듭니다. */
      color: #333;
    }
    
    .login-container input[type="text"],
    .login-container input[type="password"],
    .login-container button {
      width: calc(100% - 20px); /* 내부 요소들의 너비를 조정합니다. */
      padding: 15px; /* 입력 필드와 버튼의 패딩을 늘립니다. */
      margin-bottom: 30px; /* 내부 요소들 간의 간격을 늘립니다. */
      border-radius: 4px;
      box-sizing: border-box;
    }
    
    .login-container input[type="text"],
    .login-container input[type="password"] {
      border: 1px solid #ccc;
    }
    
    .login-container button {
      background-color: #4CAF50;
      color: #fff;
      border: none;
      cursor: pointer;
      transition: background-color 0.3s;
    }
    
    .login-container button:hover {
      background-color: #45a049;
    }
    
    .login-container p {
      margin-top: 30px; /* 가입하기 링크와의 간격을 늘립니다. */
      font-size: 14px;
      color: #666;
    }
    ```
    
- RoomInfoPage
    
    ```css
    table {
        border-collapse: collapse;
    }
    
    th, td {
        border:1px solid #ccc;
    }
    td {
        background-color: beige;
    }
    
    thead {
        background-color: cornflowerblue;
        color: white;
    }
    .title {
      font-size: 120px;
      font-weight: bold;
      text-shadow: 2px 2px 4px rgba(11, 54, 103, 0.5);
      color: rgb(151, 115, 221);
      text-align: center; /* 가운데 정렬 */
      margin-top: 70px; /* 위로 70px 이동 */
    }
    
    .form-container {
        max-width: 400px;
        margin: 0 auto;
        padding: 20px;
        background-color: #f7f7f7;
        border: 1px solid #ddd;
        border-radius: 5px;
    }
    
    label {
        display: block;
        margin-bottom: 5px;
    }
    
    input[type="text"],
    input[type="date"] {
        width: 90%;
        padding: 10px;
        margin-bottom: 15px;
        border: 1px solid #ccc;
        border-radius: 3px;
    }
      .page-button {
        padding: 10px 20px;
        background-color: #4CAF50;
        color: #fff;
        border: none;
        border-radius: 3px;
        cursor: pointer;
      }
    
      .page-button:hover {
        background-color: #45a049;
      }
      button {
        padding: 20px 40px;
        margin: 0 70px; /* 나가기 버튼의 크기만큼 띄우기 */
        font-size: 20px;
        background-color: #4CAF50;
        color: #fff;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        transition: background-color 0.3s;
      }
    .container {
        background-image: url('../images/골룸.jpg');
        background-size: cover; /* 이미지가 화면에 맞게 확대/축소되도록 설정 */
        background-repeat: no-repeat; /* 이미지 반복 없음 */
        background-attachment: fixed; /* 스크롤 시 배경 이미지가 고정됨 */
    }
    
    ```
    
- TitlePointLow
    
    ```css
    table {
        border-collapse: collapse;
    }
    
    th, td {
        border:1px solid #ccc;
    }
    td {
        background-color: beige;
    }
    
    thead {
        background-color: cornflowerblue;
        color: white;
    }
    .title {
      font-size: 50px;
      font-weight: bold;
      text-shadow: 2px 2px 4px rgba(11, 54, 103, 0.5);
      color: rgb(151, 115, 221);
      text-align: center; /* 가운데 정렬 */
      margin-top: 70px; /* 위로 70px 이동 */
    }
    
    .form-container {
        max-width: 400px;
        margin: 0 auto;
        padding: 20px;
        background-color: #f7f7f7;
        border: 1px solid #ddd;
        border-radius: 5px;
    }
    
    label {
        display: block;
        margin-bottom: 5px;
    }
    
    input[type="text"],
    input[type="date"] {
        width: 90%;
        padding: 10px;
        margin-bottom: 15px;
        border: 1px solid #ccc;
        border-radius: 3px;
    }
      .page-button {
        padding: 10px 20px;
        background-color: #4CAF50;
        color: #fff;
        border: none;
        border-radius: 3px;
        cursor: pointer;
      }
    
      .page-button:hover {
        background-color: #45a049;
      }
      button {
        padding: 20px 40px;
        margin: 0 70px; /* 나가기 버튼의 크기만큼 띄우기 */
        font-size: 20px;
        background-color: #4CAF50;
        color: #fff;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        transition: background-color 0.3s;
      }
    .container {
        background-image: url('../images/골룸.jpg');
        background-size: cover; /* 이미지가 화면에 맞게 확대/축소되도록 설정 */
        background-repeat: no-repeat; /* 이미지 반복 없음 */
        background-attachment: fixed; /* 스크롤 시 배경 이미지가 고정됨 */
    }
    
    ```
    
- UserInfoPage
    
    ```css
    table {
        border-collapse: collapse;
    }
    
    th, td {
        border:1px solid #ccc;
    }
    td {
        background-color: beige;
    }
    
    thead {
        background-color: cornflowerblue;
        color: white;
    }
    .title {
      font-size: 120px;
      font-weight: bold;
      text-shadow: 2px 2px 4px rgba(11, 54, 103, 0.5);
      color: rgb(151, 115, 221);
      text-align: center; /* 가운데 정렬 */
      margin-top: 70px; /* 위로 70px 이동 */
    }
    
    .form-container {
        max-width: 400px;
        margin: 0 auto;
        padding: 20px;
        background-color: #f7f7f7;
        border: 1px solid #ddd;
        border-radius: 5px;
    }
    
    label {
        display: block;
        margin-bottom: 5px;
    }
    
    input[type="text"],
    input[type="date"] {
        width: 90%;
        padding: 10px;
        margin-bottom: 15px;
        border: 1px solid #ccc;
        border-radius: 3px;
    }
      .page-button {
        padding: 10px 20px;
        background-color: #4CAF50;
        color: #fff;
        border: none;
        border-radius: 3px;
        cursor: pointer;
      }
    
      .page-button:hover {
        background-color: #45a049;
      }
      button {
        padding: 20px 40px;
        margin: 0 70px; /* 나가기 버튼의 크기만큼 띄우기 */
        font-size: 20px;
        background-color: #4CAF50;
        color: #fff;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        transition: background-color 0.3s;
      }
    
    .container {
        background-image: url('../images/골룸.jpg');
        background-size: cover; /* 이미지가 화면에 맞게 확대/축소되도록 설정 */
        background-repeat: no-repeat; /* 이미지 반복 없음 */
        background-attachment: fixed; /* 스크롤 시 배경 이미지가 고정됨 */
    }
    
    ```
    
- UserOlumGollumMain
    
    ```css
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      height: calc(100vh - 20px); /* 전체 높이에서 20px 뺌 */
      background-image: url('/images/골룸.jpg');
      background-size: cover;
      background-position: center;
    }
    
    .title {
      font-size: 128px;
      font-weight: bold;
      text-shadow: 2px 2px 4px rgba(11, 54, 103, 0.5);
      color: rgb(128, 0, 128); /* 제목 색상을 보라색으로 변경 */
      margin-bottom: 50px; /* 제목의 글자 크기만큼 위로 올리기 */
    }
    
    .button-container {
      margin-top: 60px;
      display: flex;
      flex-direction: column;
    }
    
    .button-row {
      display: flex;
      justify-content: center;
      margin-bottom: 60px;
    }
    
    button {
      padding: 20px 40px;
      margin: 0 70px; /* 나가기 버튼의 크기만큼 띄우기 */
      font-size: 20px;
      background-color: #4CAF50;
      color: #fff;
      border: none;
      border-radius: 4px;
      cursor: pointer;
      transition: background-color 0.3s;
    }
    
    button:hover {
      background-color: #45a049;
    }
    ```
    

# 9. 프로젝트 진행

https://docs.google.com/spreadsheets/d/1PHxGOc3YHsZqQTuzGfzHkA2f34ExoHbKAJ8qmyVLs-0/edit?usp=sharing


# 10. 참고자료

## GitHub url

## Team
|<img src="https://avatars.githubusercontent.com/u/67867076?v=4" width="150" height="150"/>|<img src="https://avatars.githubusercontent.com/u/161570998?v=4" width="150" height="150"/>|<img src="https://avatars.githubusercontent.com/u/161570977?v=4" width="150" height="150"/>|<img src="https://avatars.githubusercontent.com/u/49334905?v=4" width="150" height="150"/>|
|:-:|:-:|:-:|:-:|
|Neo.경섭<br/>[@Leegyeongseob](https://github.com/Leegyeongseob)|[@tjdyddnr1](https://github.com/tjdyddnr1)|kimfjd<br/>[@kimfjd](https://github.com/kimfjd)|[@tpgh1554](https://github.com/tpgh1554)|

## Git Bash 참고 사이트

https://shortcuts.tistory.com/8

## ERD 참고 사이트

https://www.erdcloud.com/

# 11. 개발환경



 <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white">
      <img src="https://img.shields.io/badge/Oracle-F80000?style=for-the-badge&logo=Oracle&logoColor=white">
      <img src="https://img.shields.io/badge/Thymeleaf-%23005C0F.svg?style=for-the-badge&logo=Thymeleaf&logoColor=white">
      <img src="https://img.shields.io/badge/HTML-239120?style=for-the-badge&logo=html5&logoColor=white">
  <img src="https://img.shields.io/badge/CSS-239120?&style=for-the-badge&logo=css3&logoColor=white">


