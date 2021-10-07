drop table member;
create table member(								-- 테이블 생성
	no int(11) not null auto_increment,
	email varchar(200) not null,
	password varchar(64) not null,
	name varchar(100) not null,
	deparment varchar(100),
	primary key(no)
);
desc member;

alter table member add juminbunho char(13) not null after email;   -- 테이블 추가
desc member;

alter table member drop juminbunho;								   -- 테이블 삭제
desc member;

alter table member add join_date datetime not null;  -- member 테이블에 회원 가입날짜  DATETIME 타입의 join_date 이름의 칼럼을 추가 하세요.반드시 입력되어야 하는 컬럼입니다.
desc member;

alter table member change deparment department varchar(100) not null;  -- 수정
desc member;

alter table member add self_intro text;
desc member;


-- insert
insert
  into member 
values(null, 'kickscar@gmail.com', password('1234'), '안대혁', '개발팀', now(), null);
select * from member;

insert
  into member(no, email, password, department, name, join_date) 
values(null, 'kickscar3@gmail.com', password('1234'), '개발팀3', '안대혁3', now());
select * from member;

insert
	into member(no, email, password, department, name, join_date) 
values(null,'ektmf8619@naver.com', password('1004'),'인사팀', '김다슬',now());
select * from member;

insert
	into member(no, email, password, department, name, join_date)
values(null, 'fdsljdnflskdnkl@sknfd', password('123456789'),'메롱팀','메롱메롱',now());
select * from member;

-- update
 update member
    set email='kicks@gmail.com', password=password('5678')
  where no = 3;
select * from member;
  
-- delete
delete
  from member
 where no = 4; 
select * from member;
select * from member;
-- transaction
select @@AUTOCOMMIT;
set autocommit=0;  -- 0: fales commit을 해야 변경사항? 저장되고 commit 전에 실수로 지우거나 하면 rollback 해서 되돌릴 수 있다. // 1: true true로 해놓으면 삭제한거 되돌릴 수 없다.
-- commit;
-- rollback;

insert
  into member(no, email, password, department, name, join_date) 
values(null, 'kickscar5@gmail.com', password('1234'), '개발팀5', '안대혁5', now());
select * from member;