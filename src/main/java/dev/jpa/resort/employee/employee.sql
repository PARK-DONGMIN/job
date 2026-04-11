-- SQL을 직접 적용하는 경우 테스트
/**********************************/
/* Table Name: 직원 */
/**********************************/
DROP TABLE EMPLOYEE CASCADE CONSTRAINTS;

CREATE TABLE EMPLOYEE(
    EMPLOYEENO NUMBER(5)     NOT NULL,
    ID         VARCHAR(20)   NOT NULL UNIQUE, -- 아이디, 중복 안됨, 레코드를 구분 
    PASSWORD     VARCHAR(100)   NOT NULL, -- 패스워드, 영숫자 조합
    MNAME      VARCHAR(20)   NOT NULL, -- 성명, 한글 10자 저장 가능
    RDATE      VARCHAR(19)          NOT NULL, -- 가입일, 2025-11-05 10:57:01    
    GRADE      NUMBER(2)     NOT NULL, -- 등급(1~10: 관리자, 정지 관리자: 20, 탈퇴 관리자: 99)    
    PRIMARY KEY (EMPLOYEENO)
);

COMMENT ON TABLE EMPLOYEE is '직원';
COMMENT ON COLUMN EMPLOYEE.EMPLOYEENO is '직원 번호';
COMMENT ON COLUMN EMPLOYEE.ID is '아이디';
COMMENT ON COLUMN EMPLOYEE.PASSWORD is '패스워드';
COMMENT ON COLUMN EMPLOYEE.MNAME is '성명';
COMMENT ON COLUMN EMPLOYEE.RDATE is '가입일';
COMMENT ON COLUMN EMPLOYEE.GRADE is '등급';

DROP SEQUENCE EMPLOYEE_SEQ;

CREATE SEQUENCE EMPLOYEE_SEQ
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999999
  CACHE 2                     -- 2번은 메모리에서만 계산
  NOCYCLE;                   -- 다시 1부터 생성되는 것을 방지


1. 중복 아이디 검사
SELECT COUNT(*) as cnt FROM employee WHERE id='user1';
       CNT
----------
         1
         
SELECT COUNT(*) as cnt FROM employee WHERE id='user100';
       CNT
----------
         0


DELETE FROM employee;

commit;

-- 등록
INSERT INTO employee(EMPLOYEENO, ID, PASSWORD, MNAME, RDATE, GRADE)
VALUES(employee_seq.nextval, 'user1', '1234', '왕눈이', sysdate, 1);

commit;

SELECT employeeno, id, password, mname, rdate, grade FROM employee ORDER BY employeeno ASC;


2. 패스워드 변경
UPDATE employee SET password='1111' WHERE id='user1';

SELECT employeeno, id, password, mname, rdate, grade FROM employee ORDER BY employeeno ASC;

commit;


3. 패스워드 검사, 로그인
SELECT COUNT(*) as cnt FROM employee WHERE id='user1' AND password='1234';


4. 수정
UPDATE employee SET mname='가길동', id='user4', grade=2 WHERE employeeno=1;

commit;


5. 삭제
DELETE FROM employee WHERE employeeno=1;

commit;

SELECT employeeno, id, password, mname, rdate, grade FROM employee ORDER BY employeeno ASC;


6. id를 이용한 회원 정보 조회
SELECT employeeno, id, password, mname, rdate, grade 
FROM employee 
WHERE id='user1'
ORDER BY employeeno ASC;






  
  