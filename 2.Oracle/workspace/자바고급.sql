create table mymember(
    mem_id varchar2(15) not null,
    mem_name varchar2(30) not null,
    mem_tel varchar2(14) not null,
    mem_addr varchar2(90) not null,
    constraint pk_mymember primary key (mem_id)
);



    -- VO객체의 멤버변수를 자동으로 만들기
    -- sql명령어를 이용하면 컬럼명을 뽑아서 VO에 들어갈 멤버 변수를 자동으로 만들 수 있다.
    SELECT 'private ' || 
    -- NUMBER일 때는 int, 그 외는 모두 String으로 한다. - 날짜도 String으로 하는 게 좋다!
    -- data_type을 가져오는데 그게 number면 int, 아니면 String || 컬럼명
    decode(lower(data_type), 'number', 'int ', 'String ') || lower(column_name) || ';'
       from cols    --(오라클에서 자동으로 만들어주는 것들 중에 cols는 컬럼에 대한 것들)
     where lower(table_name) = 'member';  -- 내가 만들고 싶은 테이블 명 넣어주면 된다.
      
      -- 이거 실행하면 변수들이 자동으로 만들어짐 > 그거 복사해서 java에 붙여넣기 하면 변수 만들기 끝