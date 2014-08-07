package de.akull.dgh.database;

import de.akull.dgh.models.Name;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;


// Database access layer
@RegisterMapper(NameMapper.class)
public interface NameDAO {
    @SqlUpdate("insert into test (name) values (:name)")
    @GetGeneratedKeys
    int insert(@Bind("name") String name);

    @SqlQuery("select * from test where id = :id")
    Name findById(@Bind("id") int id);

    @SqlQuery("select * from test")
    List<Name> all();
}
