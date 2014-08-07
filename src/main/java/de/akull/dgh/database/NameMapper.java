package de.akull.dgh.database;

import de.akull.dgh.models.Name;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


// Required by JDBI
public class NameMapper implements ResultSetMapper<Name> {
    public Name map(int index, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new Name(resultSet.getInt("id"), resultSet.getString("Name"));
    }
}
