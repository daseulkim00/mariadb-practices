--  함수: 문자열 함수

-- upper
select upper('busan'),upper('busan'), upper('Douzone');
select upper(first_name) from employees;

select upper(first_name), gender, hire_date
from employees
where last_name ='ACTON';
-- 

-- lower
select lower('busan'),lower('busan'), lower('Douzone');
select lower(first_name) from employees;


-- substring(문자열, index, length)
select substring('Hello World',3,2);
select substring('I wanna go home', 2,4);
-- 예제) 1989년에 입사한 사원들의 이름, 입사일 출력
select first_name, hire_date
 from employees
 where substring(hire_date,1,4) ='1989';
 
 
 -- lpad(왼쪽 정렬),rpad(오른쪽 정렬)
 select lpad('Hello',10,'-'); 
 select rpad('world',10,'-');
 
 -- 예제) 직원들의 월급을 오른쪽 정렬(빈공간은 *)
 select lpad (salary,10 , '*') from salaries;

 
 -- trim, ltrim, rtrim
 select concat('----',ltrim('    hello    '), '---'),
        concat('----',rtrim('    hello    '), '---'),
        concat('----',trim(both 'x'  from  'xxxxxhelloxxxxx'), '---');
 

 
 