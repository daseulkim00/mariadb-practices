-- 함수: 날짜함수

-- CURDATE(), CURRENT_DATE
select curdate(), current_date();


-- CURDATE(), CURRENT_TIME
select curtime(), current_time();

-- now() vs sysdate()  sysdate는 쿼리가 시작된 시간을 나타낸다.
select now(), sysdate();
select now(), sleep(2), now();      
select now(), sleep(2), sysdate();  -- 2초 쉬어간다. 

-- date_format(date,format)
select date_format( now(), '%Y년 / %월 / %d일 / %h시 / %i분 / %s초');

-- period_diff
-- YYMM, YYYYMM
-- 예) 근무 개월 수를 출력
select first_name,
	period_diff(date_format(curdate(),'%y%m'),date_format(hire_date, '%y%m')) as month
    from employees
    order by month desc;
    
    -- 이름과 근무 개월 수 출력
  select first_name as '이름' ,
  period_diff( date_format(curdate(), '%Y%m'), date_format(hire_date, '%Y%m')) as month   -- 소문자와 대문자의 차이?
  from employees
  order by month desc;
    
-- date_add(=adddate), data_sub(=subdate)
-- 날짜를 date에 type(day, month, year) 형식의 표현식(expr) 더하거나 뺀다.
-- 예제) 각 사원들의 근무년수가 5년이 되는 날은 언제 인가요?
select first_name,
	   hire_date,
       date_add(hire_date, interval 5 year)
 from employees;
 

 -- cast
 -- select '12345' +10, cast('12345' as int) +10;
 
 select date_format(cast('2021-10-01' as date),'%y-%m-%d');
 select cast(1-2 as unsigned);
 

 -- mysql type
 -- varchar, char text, CLOB(Character Lager OBject)
 -- signed(unsigned), int(integer), medium int, big int , int(11)
 -- float,double
 -- time, date, datetime
 -- LOB: CLOB, BLOB 
 
 
 
 





