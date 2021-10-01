-- Basic Query

-- 테이블 삭제
drop table pet;

-- 테이블 만들기
create table pet(
	name varchar(20),
    owner varchar(20),
    species varchar(20),
    gender char(1),
    birth DATE,
    death DATE
);
-- schema 확인
desc pet;

-- 조회
select name, owner, species, gender, birth, death from pet;

-- 데이터 넣기 생성, create

insert
	into pet
    value('예삐','김다슬','dog','w','2018-12-25','2018-12-25');
    
-- 데이터 삭제(delete)
delete
 from pet
where name ='성탄이';
    
-- insert
