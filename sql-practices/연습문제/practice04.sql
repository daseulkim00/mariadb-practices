-- 문제1.
-- 현재 평균 연봉보다 많은 월급을 받는 직원은 몇 명이나 있습니까?
select count(*)
 from  salaries 
  where to_date = '9999-01-01'
    and salary > ( select avg(salary) 
                    from salaries
                   where to_date = '9999-01-01');

-- 문제2.
-- 현재, 각 부서별로 최고의 급여를 받는 사원의 사번, 이름, 부서 연봉을 조회하세요.
-- 단 조회결과는 연봉의 내림차순으로 정렬되어 나타나야 합니다. 

select a.emp_no, a.first_name, b.dept_name, d.salary   
 from employees a, 
      departments b,
      dept_emp c,
      salaries d,
      (select a.dept_no , max(c.salary) as max_salary
		 from departments a , dept_emp b, salaries c
		where a.dept_no = b.dept_no
		 and b.emp_no = c.emp_no
		 and b.to_date = '9999-01-01'
		 and c.to_date = '9999-01-01'
		group by a.dept_no) e
where a.emp_no = c.emp_no
  and a.emp_no = d.emp_no
  and b.dept_no = c.dept_no
  and c.emp_no = d.emp_no
  and d.salary = e.max_salary
  and c.dept_no = e.dept_no          -- 이거 빼서 sales가 2명이 나와버림ㅠ 
  and c.to_date = '9999-01-01'
  and d.to_date = '9999-01-01'
order by d.salary desc;
  
  
-- 부서별로 최고의 급여 출력
select a.dept_no , max(c.salary)
 from departments a , dept_emp b, salaries c
 where a.dept_no = b.dept_no
   and b.emp_no = c.emp_no
   and b.to_date = '9999-01-01'
   and c.to_date = '9999-01-01'
group by a.dept_no;


-- 문제3.
-- 현재, 자신의 부서 평균 급여보다 연봉(salary)이 많은 사원의 사번, 이름과 연봉을 조회하세요 

select a.emp_no, a.first_name, b.salary           -- 왜안댐
 from employees a, salaries b , dept_emp c, 
             (select b.dept_no, avg(a.salary) as avg_salary  -- 부서의 평균 급여
             from salaries a, dept_emp b, employees c
            where a.emp_no = b.emp_no
                 and b.emp_no = c.emp_no
             and a.to_date = '9999-01-01'
             and b.to_date = '9999-01-01'
            group by b.dept_no) d
where a.emp_no = b.emp_no
 and a.emp_no = c.emp_no
 and c.dept_no = d.dept_no
 and b.salary > d.avg_salary
 and b.to_date = '9999-01-01'
 and c.to_date = '9999-01-01';
 
 
 select e.emp_no as '사번', concat(e.first_name, ' ', e.last_name) as '이름' , s.salary as '연봉'
  from employees e
  join dept_emp de 
    on e.emp_no = de.emp_no
  join (select dept_no, avg(salary) as dept_avg_sal
          from salaries s 
          join dept_emp de 
            on s.emp_no = de.emp_no
         where s.to_date = '9999-01-01'
           and de.to_date = '9999-01-01'
      group by dept_no) avg_sal 
    on de.dept_no = avg_sal.dept_no
  join salaries s 
    on e.emp_no = s.emp_no 
   and salary >= avg_sal.dept_avg_sal
   and de.to_date = '9999-01-01' 
   and s.to_date = '9999-01-01'
   order by s.salary;  
 
-- 문제4.
-- 현재, 사원들의 사번, 이름, 매니저 이름, 부서 이름으로 출력해 보세요.

select a.emp_no ,  concat(a.first_name, ' ', a.last_name),  concat(.first_name, ' ', a.last_name)
 from employees a, dept_manager b, dept_emp c ,departments d
where a.emp_no = b.emp_no
  and b.emp_no = c.emp_no
  and b.dept_no = d.dept_no
  and c.dept_no = d.dept_no
  and b.to_date = '9999-01-01'
  and c.to_date = '9999-01-01';



-- 문제5.
-- 현재, 평균연봉이 가장 높은 부서의 사원들의 사번, 이름, 직책, 연봉을 조회하고 연봉 순으로 출력하세요.

-- 1. 평균연봉이 가장 높은 부서 구하기
select a.dept_no
 from dept_emp a, salaries b
where a.emp_no = b.emp_no
group by a.dept_no
order by avg(b.salary) desc
limit 0,1;

-- 2. 부서의 사원들의  사번, 이름,직책연봉을 조회 연봉순 출력
select d.dept_no as '부서번호', a.emp_no as '사번', concat(a.first_name, ' ', a.last_name) as'이름', b.title as '직책', c.salary as '연봉'
 from employees a, titles b, salaries c, dept_emp d
where a.emp_no = b.emp_no
  and a.emp_no = c.emp_no
  and a.emp_no = d.emp_no
  and b.to_date = '9999-01-01'
  and c.to_date = '9999-01-01'
  and d.dept_no = ( select a.dept_no
					 from dept_emp a, salaries b
					where a.emp_no = b.emp_no
					group by a.dept_no
					order by avg(b.salary) desc
					limit 0,1)
order by c.salary desc;


-- 문제6.
-- 평균 연봉이 가장 높은 부서는? 

-- 평균연봉높은 부서 번호

select de.dept_no
 from salaries s, dept_emp de
where s.emp_no = de.emp_no
 group by de.dept_no
  order by avg(s.salary) desc
limit 0,1;


-- 답
select d.dept_name as '부서'
 from dept_emp de, departments d
where de.dept_no = d.dept_no
  and de.dept_no = (select de.dept_no
					 from salaries s, dept_emp de
					where s.emp_no = de.emp_no
					group by de.dept_no
					order by avg(s.salary) desc
					limit 0,1)
and de.to_date = '9999-01-01'
limit 0,1;


-- 문제7.
-- 평균 연봉이 가장 높은 직책?

select t.title as '직책' 
 from salaries s, titles t
where s.emp_no = t.emp_no
  and s.to_date = '9999-01-01'
  and t.to_date = '9999-01-01'
 group by t.title
  order by avg(s.salary) desc
  limit 0,1;
  

-- 문제8.
-- 현재 자신의 매니저보다 높은 연봉을 받고 있는 직원은?
-- 부서이름, 사원이름, 연봉, 매니저 이름, 메니저 연봉 순으로 출력합니다.

select*
 from departments d, employees e, salaries s, 







