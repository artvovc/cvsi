create procedure PROC_DROP_FOREIGN_KEY(in tableName varchar(64), in constraintName varchar(64))
  begin
    if exists(
        select * from information_schema.table_constraints
        where
          table_schema    = DATABASE()     and
          table_name      = tableName      and
          constraint_name = constraintName and
          constraint_type = 'FOREIGN KEY')
    then
      set @query = CONCAT('ALTER TABLE ', tableName, ' DROP FOREIGN KEY ', constraintName, ';');
      prepare stmt from @query;
      execute stmt;
      deallocate prepare stmt;
    end if;
  end;