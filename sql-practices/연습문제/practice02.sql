-- 문제 1. 
-- 최고임금(salary)과  최저임금을 “최고임금, “최저임금”프로젝션 타이틀로 함께 출력해 보세요.
-- 두 임금의 차이는 얼마인가요? 함께 “최고임금 – 최저임금”이란 타이틀로 출력해 보세요.
select max(salary) as '최고임금', min(salary) as '최저임금' , max(salary) - min(salary) as '최고임금 - 최저임금'
 from salaries;

-- 문제2.
-- 마지막으로 신입사원이 들어온 날은 언제 입니까? 다음 형식으로 출력해주세요.
-- 예) 2014년 07월 10일

select date_format(max(hire_date), '%Y년 %m월 %d일')
 from employees;

-- 문제3.
-- 가장 오래 근속한 직원의 입사일은 언제인가요? 다음 형식으로 출력해주세요.
-- 예) 2014년 07월 10일





-- 문제4.
-- 현재 이 회사의 평균 연봉은 얼마입니까?

select avg(salary)
from salaries
where to_date ='9999-01-01';

-- 문제5.
-- 현재 이 회사의 최고/최저 연봉은 얼마입니까?

select max(salary), min(salary)
 from salaries
 where to_date = '9999-01-01';

-- 문제6.
-- 최고 어린 사원의 나이와 최 연장자의 나이는?

SELECT FLOOR((CAST(REPLACE(CURRENT_DATE,'-','') AS UNSIGNED) - CAST(REPLACE(max(birth_date),'-','') AS UNSIGNED)) / 10000 ) as '어린 사원',
     FLOOR((CAST(REPLACE(CURRENT_DATE,'-','') AS UNSIGNED) - CAST(REPLACE(min(birth_date),'-','') AS UNSIGNED)) / 10000 ) as '연장자'
from employees;

select floor((cast(replace(current_date,'-','') as unsigned) - cast(replace(max(birth_date),'-','') as unsigned)) / 10000) as '어린 사원',
	   floor((cast(replace(current_date,'-','')as unsigned) - cast(replace(min(birth_date),'-','')as unsigned))/ 10000) as '연장자'
from employees;

-- replace('문자열','기존문자열','변경문자열') 
-- 날짜에서 - 를 제거하고 cast를 이용해 int로 만들어 주고   - 한 다음 /10000 계산한 결과 floor로 소수점 버려준다

 select floor(datediff(now(), min(birth_date)) / 365) as '연장자' , floor(datediff(now(),max(birth_date))/ 365) as '어린사원'
 from employees;




