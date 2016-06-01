select concat('create table ',table_name,'_bak',date_format(now(),'%Y%m%d'),' as select * from ',table_name,';') from information_schema.tables where table_schema = 'hehedb';
select concat('drop table ',table_name,';') from information_schema.tables where table_schema = 'hehedb';
