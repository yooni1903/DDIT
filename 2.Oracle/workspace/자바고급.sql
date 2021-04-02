create table mymember(
    mem_id varchar2(15) not null,
    mem_name varchar2(30) not null,
    mem_tel varchar2(14) not null,
    mem_addr varchar2(90) not null,
    constraint pk_mymember primary key (mem_id)
);



    -- VO��ü�� ��������� �ڵ����� �����
    -- sql��ɾ �̿��ϸ� �÷����� �̾Ƽ� VO�� �� ��� ������ �ڵ����� ���� �� �ִ�.
    SELECT 'private ' || 
    -- NUMBER�� ���� int, �� �ܴ� ��� String���� �Ѵ�. - ��¥�� String���� �ϴ� �� ����!
    -- data_type�� �������µ� �װ� number�� int, �ƴϸ� String || �÷���
    decode(lower(data_type), 'number', 'int ', 'String ') || lower(column_name) || ';'
       from cols    --(����Ŭ���� �ڵ����� ������ִ� �͵� �߿� cols�� �÷��� ���� �͵�)
     where lower(table_name) = 'member';  -- ���� ����� ���� ���̺� �� �־��ָ� �ȴ�.
      
      -- �̰� �����ϸ� �������� �ڵ����� ������� > �װ� �����ؼ� java�� �ٿ��ֱ� �ϸ� ���� ����� ��