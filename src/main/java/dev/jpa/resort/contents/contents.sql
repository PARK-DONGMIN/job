-- dev.jpa.resort.contents.contents.sql
DROP TABLE contents CASCADE CONSTRAINTS; -- 자식 무시하고 삭제 가능
DROP TABLE contents;

CREATE TABLE contents(
        contentsno                            NUMBER(10)         NOT NULL,
        employeeno                            NUMBER(10)     NOT NULL , -- FK
        cateno                                NUMBER(10)         NOT NULL , -- FK
        title                                 VARCHAR2(200)         NOT NULL,
        content                               CLOB                  NOT NULL,
        recom                                 NUMBER(7)         DEFAULT 0         NOT NULL,
        cnt                                   NUMBER(7)         DEFAULT 0         NOT NULL,
        replycnt                              NUMBER(7)         DEFAULT 0         NOT NULL,
        password                              VARCHAR2(100)         NOT NULL,
        word                                  VARCHAR2(200)         NULL ,
        rdate                                 VARCHAR2(19)               NOT NULL,
        file1                                 VARCHAR(100)          NULL,  -- 원본 파일명 image
        file1saved                            VARCHAR(100)          NULL,  -- 저장된 파일명, image
        thumb1                                VARCHAR(100)          NULL,   -- preview image
        size1                                 NUMBER(10)      DEFAULT 0 NULL,  -- 파일 사이즈
        price                                 NUMBER(10)      DEFAULT 0 NULL,  
        dc                                    NUMBER(10)      DEFAULT 0 NULL,  
        saleprice                            NUMBER(10)      DEFAULT 0 NULL,  
        point                                 NUMBER(10)      DEFAULT 0 NULL,  
        salecnt                               NUMBER(10)      DEFAULT 0 NULL,
        map                                   VARCHAR2(1000)            NULL,
        youtube                               VARCHAR2(1000)            NULL,
        mp4                                  VARCHAR2(100)            NULL,
        visible                                CHAR(1)         DEFAULT 'Y' NOT NULL,
        emotion                              NUMBER(1) DEFAULT 1 NOT NULL,  -- 0: 부정, 1: 긍정
        summary                              VARCHAR(1000)            NULL,   -- 요약       
        PRIMARY KEY (contentsno),
        FOREIGN KEY (employeeno) REFERENCES employee (employeeno),
        FOREIGN KEY (cateno) REFERENCES cate (cateno)
);

COMMENT ON TABLE contents is '컨텐츠 - 순례길';
COMMENT ON COLUMN contents.contentsno is '컨텐츠 번호';
COMMENT ON COLUMN contents.employeeno is '관리자 번호';
COMMENT ON COLUMN contents.cateno is '카테고리 번호';
COMMENT ON COLUMN contents.title is '제목';
COMMENT ON COLUMN contents.content is '내용';
COMMENT ON COLUMN contents.recom is '추천수';
COMMENT ON COLUMN contents.cnt is '조회수';
COMMENT ON COLUMN contents.replycnt is '댓글수';
COMMENT ON COLUMN contents.password is '패스워드';
COMMENT ON COLUMN contents.word is '검색어';
COMMENT ON COLUMN contents.rdate is '등록일';
COMMENT ON COLUMN contents.file1 is '메인 이미지';
COMMENT ON COLUMN contents.file1saved is '실제 저장된 메인 이미지';
COMMENT ON COLUMN contents.thumb1 is '메인 이미지 Preview';
COMMENT ON COLUMN contents.size1 is '메인 이미지 크기';
COMMENT ON COLUMN contents.price is '정가';
COMMENT ON COLUMN contents.dc is '할인률';
COMMENT ON COLUMN contents.saleprice is '판매가';
COMMENT ON COLUMN contents.point is '포인트';
COMMENT ON COLUMN contents.salecnt is '재고 수량';
COMMENT ON COLUMN contents.map is '지도';
COMMENT ON COLUMN contents.youtube is 'Youtube 영상';
COMMENT ON COLUMN contents.mp4 is '영상';
COMMENT ON COLUMN contents.visible is '출력 모드';
COMMENT ON COLUMN contents.visible is '출력 모드';
COMMENT ON COLUMN contents.emotion is '감정분석';

DROP SEQUENCE contents_seq;

CREATE SEQUENCE contents_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;                      -- 다시 1부터 생성되는 것을 방지

-- 부모키를 확인후 등록하는 경우
INSERT INTO contents(contentsno, employeeno, cateno, title, content, recom, cnt, replycnt, password, 
                     word, rdate, file1, file1saved, thumb1, size1, emotion, summary)
VALUES(contents_seq.nextval, 1, 5, '제주도', '제주도 휴양 스토리', 0, 0, 0, '123',
       '제주도,바다,휴양', sysdate, 'space.jpg', 'space_1.jpg', 'space_t.jpg', 1000, 1, '즐거운 추억 만들기');
            
INSERT INTO contents(contentsno, employeeno, cateno, title, content, recom, cnt, replycnt, password, 
                     word, rdate, file1, file1saved, thumb1, size1, emotion, summary)
VALUES(contents_seq.nextval, 1, 5, '부산', '드론쇼', 0, 0, 0, '123',
       '부산,드론,해수욕장', sysdate, 'space.jpg', 'space_1.jpg', 'space_t.jpg', 1000, 1, '즐거운 추억 만들기');
       
INSERT INTO contents(contentsno, employeeno, cateno, title, content, recom, cnt, replycnt, password, 
                     word, rdate, file1, file1saved, thumb1, size1, emotion, summary)
VALUES(contents_seq.nextval, 1, 5, '정선', '패러글라이딩', 0, 0, 0, '123',
       '정선,패러글라이딩,체험', sysdate, 'space.jpg', 'space_1.jpg', 'space_t.jpg', 1000, 1, '즐거운 추억 만들기');       
       
COMMIT;

-- 전체 목록
SELECT contentsno, employeeno, cateno, title, content, recom, cnt, replycnt, password, word, rdate,
           file1, file1saved, thumb1, size1, map, youtube mp4, visible, emotion, summary
FROM contents
ORDER BY contentsno DESC;

-- 5번 cateno 만 출력
SELECT contentsno, employeeno, cateno, title, content, recom, cnt, replycnt, password, word, rdate,
        LOWER(file1) as file1, file1saved, thumb1, size1, map, youtube, visible, price, dc, saleprice, point, salecnt
FROM contents
WHERE cateno=5
ORDER BY contentsno DESC;

 SELECT contentsno, employeeno, cateno, title, content, recom, cnt, replycnt, password, word, rdate, 
 LOWER(file1) as file1, file1saved, thumb1, size1, map, youtube, visible 
 FROM contents
 WHERE cateno=12 
 ORDER BY contentsno ASC
      
      

-- 2번 cateno 만 출력
SELECT contentsno, employeeno, cateno, title, content, recom, cnt, replycnt, password, word, rdate,
        LOWER(file1) as file1, file1saved, thumb1, size1, map, youtube, visible
FROM contents
WHERE cateno=2
ORDER BY contentsno ASC;

-- 5번 cateno 만 출력
SELECT contentsno, employeeno, cateno, title, content, recom, cnt, replycnt, password, word, rdate,
        LOWER(file1) as file1, file1saved, thumb1, size1, map, youtube, visible
FROM contents
WHERE cateno=5
ORDER BY contentsno ASC;

commit;

-- 모든 레코드 삭제
DELETE FROM contents;
commit;

-- 삭제
DELETE FROM contents
WHERE contentsno = 25;
commit;

DELETE FROM contents
WHERE cateno=12 AND contentsno <= 41;

commit;


-- ----------------------------------------------------------------------------------------------------
-- 검색, cateno별 검색 목록
-- ----------------------------------------------------------------------------------------------------
-- 모든글
SELECT contentsno, memberno, cateno, title, content, recom, cnt, replycnt, word, rdate,
       file1, file1saved, thumb1, size1, map, youtube, visible
FROM contents
ORDER BY contentsno ASC;

-- 카테고리별 목록
SELECT contentsno, employeeno, cateno, title, content, recom, cnt, replycnt, word, rdate,
       file1, file1saved, thumb1, size1, map, youtube, visible
FROM contents
WHERE cateno=1
ORDER BY contentsno ASC;

-- 1) 검색
-- ① cateno별 검색 목록
-- word 컬럼의 존재 이유: 검색 정확도를 높이기 위하여 중요 단어를 명시
-- 글에 'swiss'라는 단어만 등장하면 한글로 '스위스'는 검색 안됨.
-- 이런 문제를 방지하기위해 'swiss,스위스,스의스,수의스,유럽' 검색어가 들어간 word 컬럼을 추가함.
SELECT contentsno, memberno, cateno, title, content, recom, cnt, replycnt, word, rdate,
           file1, file1saved, thumb1, size1, map, youtube
FROM contents
WHERE cateno=8 AND word LIKE '%부대찌게%'
ORDER BY contentsno DESC;

-- title, content, word column search
SELECT contentsno, memberno, cateno, title, content, recom, cnt, replycnt, word, rdate,
           file1, file1saved, thumb1, size1, map, youtube
FROM contents
WHERE cateno=8 AND (title LIKE '%부대찌게%' OR content LIKE '%부대찌게%' OR word LIKE '%부대찌게%')
ORDER BY contentsno DESC;

-- ② 검색 레코드 갯수
-- 전체 레코드 갯수, 집계 함수
SELECT COUNT(*)
FROM contents
WHERE cateno=8;

  COUNT(*)  <- 컬럼명
----------
         5
         
SELECT COUNT(*) as cnt -- 함수 사용시는 컬럼 별명을 선언하는 것을 권장
FROM contents
WHERE cateno=8;

       CNT <- 컬럼명
----------
         5

-- cateno 별 검색된 레코드 갯수
SELECT COUNT(*) as cnt
FROM contents
WHERE cateno=8 AND word LIKE '%부대찌게%';

SELECT COUNT(*) as cnt
FROM contents
WHERE cateno=8 AND (title LIKE '%부대찌게%' OR content LIKE '%부대찌게%' OR word LIKE '%부대찌게%');

-- SUBSTR(컬럼명, 시작 index(1부터 시작), 길이), 부분 문자열 추출
SELECT contentsno, SUBSTR(title, 1, 4) as title
FROM contents
WHERE cateno=8 AND (content LIKE '%부대%');

-- SQL은 대소문자를 구분하지 않으나 WHERE문에 명시하는 값은 대소문자를 구분하여 검색
SELECT contentsno, title, word
FROM contents
WHERE cateno=8 AND (word LIKE '%FOOD%');

SELECT contentsno, title, word
FROM contents
WHERE cateno=8 AND (word LIKE '%food%'); 

SELECT contentsno, title, word
FROM contents
WHERE cateno=8 AND (LOWER(word) LIKE '%food%'); -- 대소문자를 일치 시켜서 검색

SELECT contentsno, title, word
FROM contents
WHERE cateno=8 AND (UPPER(word) LIKE '%' || UPPER('FOOD') || '%'); -- 대소문자를 일치 시켜서 검색 ★

SELECT contentsno, title, word
FROM contents
WHERE cateno=8 AND (LOWER(word) LIKE '%' || LOWER('Food') || '%'); -- 대소문자를 일치 시켜서 검색

SELECT contentsno || '. ' || title || ' 태그: ' || word as title -- 컬럼의 결합, ||
FROM contents
WHERE cateno=8 AND (LOWER(word) LIKE '%' || LOWER('Food') || '%'); -- 대소문자를 일치 시켜서 검색


SELECT UPPER('한글') FROM dual; -- dual: 오라클에서 SQL 형식을 맞추기위한 시스템 테이블

-- ----------------------------------------------------------------------------
-- 조회수 증가
-- ----------------------------------------------------------------------------
UPDATE contents SET cnt = cnt + 1 WHERE contentsno = 1;

commit;

-- ----------------------------------------------------------------------------
-- 조회
-- ----------------------------------------------------------------------------
SELECT contentsno, employeeno, cateno, title, content, recom, cnt, replycnt, password, word, rdate,
           file1, file1saved, thumb1, size1, map, youtube, visible
FROM contents
WHERE contentsno = 1;

-- ----------------------------------------------------------------------------
-- 다음 지도, MAP, 먼저 레코드가 등록되어 있어야함.
-- map                                   VARCHAR2(1000)         NULL ,
-- ----------------------------------------------------------------------------
-- MAP 등록/수정
UPDATE contents SET map='카페산 지도 스크립트' WHERE contentsno=1;

-- MAP 삭제
UPDATE contents SET map='' WHERE contentsno=1;

commit;

-- ----------------------------------------------------------------------------
-- Youtube, 먼저 레코드가 등록되어 있어야함.
-- youtube                                   VARCHAR2(1000)         NULL ,
-- ----------------------------------------------------------------------------
-- youtube 등록/수정
UPDATE contents SET youtube='Youtube 스크립트' WHERE contentsno=1;

-- youtube 삭제
UPDATE contents SET youtube='' WHERE contentsno=1;

commit;

-- 패스워드 검사, id="password_check", 0 or 1
SELECT COUNT(*) as cnt FROM contents WHERE contentsno=3 AND password='123';

SELECT COUNT(*) as cnt 
FROM contents
WHERE contentsno=30 AND password='fS/kjO+fuEKk06Zl7VYMhg==';

       CNT
----------
         1

-- 텍스트 수정: 예외 컬럼: 추천수, 조회수, 댓글 수
UPDATE contents
SET title='기차를 타고', content='계획없이 여행 출발',  word='나,기차,생각' 
WHERE contentsno = 2;

-- ERROR, " 사용 에러
UPDATE contents
SET title='기차를 타고', content="계획없이 '여행' 출발",  word='나,기차,생각'
WHERE contentsno = 1;

-- ERROR, \' 에러
UPDATE contents
SET title='기차를 타고', content='계획없이 \'여행\' 출발',  word='나,기차,생각'
WHERE contentsno = 1;

-- SUCCESS, '' 한번 ' 출력됨.
UPDATE contents
SET title='기차를 타고', content='계획없이 ''여행'' 출발',  word='나,기차,생각'
WHERE contentsno = 1;

-- SUCCESS
UPDATE contents SET title='기차를 타고', content='계획없이 "여행" 출발',  word='나,기차,생각' WHERE contentsno = 1;

commit;

-- 파일 수정
UPDATE contents SET file1='train.jpg', file1saved='train.jpg', thumb1='train_t.jpg', size1=5000 WHERE contentsno = 1;

commit;

-- 삭제
DELETE FROM contents
WHERE contentsno = 42;

commit;

DELETE FROM contents
WHERE contentsno >= 7;

commit;

-- 추천
UPDATE contents
SET recom = recom + 1
WHERE contentsno = 1;

-- cateno FK 특정 그룹에 속한 레코드 갯수 산출
SELECT COUNT(*) as cnt 
FROM contents 
WHERE cateno=1;

-- memberno FK 특정 관리자에 속한 레코드 갯수 산출
SELECT COUNT(*) as cnt 
FROM contents 
WHERE memberno=1;

-- cateno FK 특정 그룹에 속한 레코드 모두 삭제
DELETE FROM contents
WHERE cateno=1;

-- memberno FK 특정 관리자에 속한 레코드 모두 삭제
DELETE FROM contents
WHERE memberno=1;

commit;

-- 다수의 카테고리에 속한 레코드 갯수 산출: IN
SELECT COUNT(*) as cnt
FROM contents
WHERE cateno IN(1,2,3);

-- 다수의 카테고리에 속한 레코드 모두 삭제: IN
SELECT contentsno, memberno, cateno, title
FROM contents
WHERE cateno IN(1,2,3);

CONTENTSNO    ADMINNO     CATENO TITLE                                                                                                                                                                                                                                                                                                       
---------- ---------- ---------- ------------------------
         3             1                   1           인터스텔라                                                                                                                                                                                                                                                                                                  
         4             1                   2           드라마                                                                                                                                                                                                                                                                                                      
         5             1                   3           컨저링                                                                                                                                                                                                                                                                                                      
         6             1                   1           마션       
         
SELECT contentsno, memberno, cateno, title
FROM contents
WHERE cateno IN('1','2','3');

CONTENTSNO    ADMINNO     CATENO TITLE                                                                                                                                                                                                                                                                                                       
---------- ---------- ---------- ------------------------
         3             1                   1           인터스텔라                                                                                                                                                                                                                                                                                                  
         4             1                   2           드라마                                                                                                                                                                                                                                                                                                      
         5             1                   3           컨저링                                                                                                                                                                                                                                                                                                      
         6             1                   1           마션       

-- ----------------------------------------------------------------------------------------------------
-- cate + contents INNER JOIN
-- ----------------------------------------------------------------------------------------------------
-- 모든글
SELECT c.name,
       t.contentsno, t.memberno, t.cateno, t.title, t.content, t.recom, t.cnt, t.replycnt, t.word, t.rdate,
       t.file1, t.file1saved, t.thumb1, t.size1, t.map, t.youtube
FROM cate c, contents t
WHERE c.cateno = t.cateno
ORDER BY t.contentsno DESC;

-- contents, member INNER JOIN
SELECT t.contentsno, t.memberno, t.cateno, t.title, t.content, t.recom, t.cnt, t.replycnt, t.word, t.rdate,
       t.file1, t.file1saved, t.thumb1, t.size1, t.map, t.youtube,
       a.mname
FROM member a, contents t
WHERE a.memberno = t.memberno
ORDER BY t.contentsno DESC;

SELECT t.contentsno, t.memberno, t.cateno, t.title, t.content, t.recom, t.cnt, t.replycnt, t.word, t.rdate,
       t.file1, t.file1saved, t.thumb1, t.size1, t.map, t.youtube,
       a.mname
FROM member a INNER JOIN contents t ON a.memberno = t.memberno
ORDER BY t.contentsno DESC;

-----------------------------------------------------------
-- FK cateno 컬럼에 대응하는 필수 SQL
-----------------------------------------------------------
-- 특정 카테고리에 속한 레코드 갯수를 리턴
SELECT COUNT(*) as cnt 
FROM contents 
WHERE cateno=1;
  
-- 특정 카테고리에 속한 모든 레코드 삭제
DELETE FROM contents
WHERE cateno=1;

-----------------------------------------------------------
-- FK memberno 컬럼에 대응하는 필수 SQL
-----------------------------------------------------------
-- 특정 회원에 속한 레코드 갯수를 리턴
SELECT COUNT(*) as cnt 
FROM contents 
WHERE memberno=1;
  
-- 특정 회원에 속한 모든 레코드 삭제
DELETE FROM contents
WHERE memberno=1;

-----------------------------------------------------------
-- 댓글 관련 SQL
-----------------------------------------------------------
1) 댓글수 증가
UPDATE contents
SET replycnt = replycnt + 1
WHERE contentsno = 3;

2) 댓글수 감소
UPDATE contents
SET replycnt = replycnt - 1
WHERE contentsno = 3;   

commit;

-- 글만 등록
INSERT INTO contents(contentsno, employeeno, cateno, title, content, password, word, rdate, visible)
VALUES(contents_seq.nextval, 1, 12, '컨저링 2', '실화에 기반한 퇴마', '123', '', sysdate, 'Y');
       
COMMIT;

-- 전체 목록
SELECT contentsno, employeeno, cateno, title, content, recom, cnt, replycnt, password, word, rdate,
           file1, file1saved, thumb1, size1, map, youtube mp4, visible
FROM contents
WHERE cateno=12
ORDER BY contentsno DESC;


