CREATE TABLE ISSUE(
  ISSUENO NUMBER(10) NOT NULL,
  TITLE VARCHAR2(100) NOT NULL,
  CONTENT CLOB NOT NULL,
  CNT NUMBER(7) DEFAULT 0 NOT NULL,
  RDATE DATE NOT NULL,
  EMPLOYEENO NUMBER(5)     NOT NULL,
  CATENO NUMBER(10) NOT NULL,
  PRIMARY KEY(ISSUENO),
  FOREIGN KEY(employeeno) REFERENCES employee(employeeno),
  FOREIGN KEY(cateno) REFERENCES cate(cateno)
);


SELECT issueno, title, content, cnt, rdate FROM issue;

INSERT INTO issue(issueno, title, content, cnt, rdate) 
VALUES (ISSUE_SEQ.nextval, 'cloud 서버 일시 중지', '서버 교체', 0, sysdate);

-- "": X
-- INSERT INTO issue(issueno, title, content, cnt, rdate) 
-- VALUES (ISSUE_SEQ.nextval, "서버 이전", "cloud 서버 설정", 0, sysdate);

INSERT INTO issue(issueno, title, content, cnt, rdate) 
VALUES (ISSUE_SEQ.nextval, '서버 이전3', 'cloud 서버 설정3', 0, TO_CHAR(sysdate-1, 'YYYY-MM-DD HH24:MI:SS'));

-- % 와일드 카드 사용
SELECT issueno, title, content, cnt, rdate FROM issue 
WHERE UPPER(title) LIKE '%' || UPPER('Cloud') || '%';

commit;

SELECT SUBSTR(rdate, 1, 10) FROM issue;

-- 13) 날짜 구간 검색
SELECT issueno, title, content, cnt, rdate 
FROM issue 
WHERE (SUBSTR(rdate, 1, 10) >= '2025-09-25') AND (SUBSTR(rdate, 1, 10) <= '2025-09-25');

SELECT issueno, title, content, cnt, rdate 
FROM issue 
ORDER BY issueno DESC;

SELECT issueno, title, content, cnt, rdate 
FROM issue 
WHERE (SUBSTR(rdate, 1, 10) >= '2025-09-25') AND (SUBSTR(rdate, 1, 10) <= '2025-09-26')
ORDER BY rdate DESC;

UPDATE issue SET title='서비스 오픈', content='사은품 증정' WHERE issueno=54;

UPDATE issue SET cnt = cnt + 1 WHERE issueno=3;

DELETE FROM issue;

COMMIT;
