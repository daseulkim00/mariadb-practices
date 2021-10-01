-- inner join
-- 예제1: employees 테이블과 title 테이블을 join하여 현재 직원의 이름과 직책을 모두 출력
select a.first_name, b.title
 from employees a, titles b
 where a.emp_no = b.emp_no       -- join 조건(n -1)
  and b.to_date = '9999-01-01';  -- row 선택 조건
  
  
-- 예제2: employees 테이블과 titles 테이블을 join 하여 직원의 이름과 직책을 출력하되 여성 엔지니어만 출력하세요
select a.first_name, b.title
 from employees a, titles b
 where a.emp_no = b.emp_no       -- join 조건(n -1)
  and b.to_date = '9999-01-01'   -- row 선택 조건1
  and a.gender = 'f'             -- row 선택 조건2
  and b.title ='engineer';       -- row 선택 조건3
  
  
  --
-- ANSI/ISO SQL1999 JOIN 표준 문법
--

-- 1) natural join  거의 안쓴다고함
-- 두 테이블에 공통 컬럼이 있으면 별다른 조인 조건없이 암묵적으로 join이 된다.

		select a.first_name, b.title
			from employees a 
 natural join titles b
			-- on a.emp_no = b.emp_no 생략
  where b.to_date = '9999-01-01'; -- row 선택조건
 
 -- 2) join ~ using
         select a.first_name, b.title
			from employees a join titles b using (emp_no)
     where b.to_date = '9999-01-01'; -- row 선택조건
     
-- 3) join ~ on    문제풀때 이거써~
	select a.first_name, b.title
     from employees a join titles b on a .emp_no = b.emp_no
where b.to_date = '9999-01-01'; -- row 선택조건





