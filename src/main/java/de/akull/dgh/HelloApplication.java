package de.akull.dgh;

import dagger.ObjectGraph;
import de.akull.dgh.configuration.HelloConfiguration;
import de.akull.dgh.configuration.HelloModule;
import de.akull.dgh.database.NameDAO;
import de.akull.dgh.resources.NameResource;
import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

import java.util.Arrays;


// Main
public class HelloApplication extends Application<HelloConfiguration> {

    // Arguments are located in the build.gradle
    public static void main(String[] args) throws Exception {
        new HelloApplication().run(args);
    }

    // Fills the temporary in-memory-database with some data.
    public static void initializeDatabase(DBI dbi) {
        try (Handle h = dbi.open()) {
            h.execute("create table test (id int primary key auto_increment, name varchar(100))");

            String[] names = {"Aaa", "Bbb", "Ccc", "Ddd"};

            Arrays.stream(names).forEach(name -> h.insert("insert into test (name) values (?)", name));
        }
    }

    @Override
    public void initialize(Bootstrap<HelloConfiguration> bootstrap) {
    }

    @Override
    public void run(HelloConfiguration configuration, Environment environment) throws ClassNotFoundException {
        final DBI dbi = new DBIFactory().build(environment, configuration.getDataSourceFactory(), "h2");
        final NameDAO nameDAO = dbi.onDemand(NameDAO.class);
        final ObjectGraph objectGraph = ObjectGraph.create(new HelloModule(configuration, nameDAO));

        initializeDatabase(dbi);
        /*
        * API:
        * GET -> String    /hello
        * GET -> String    /hello?name={name}
        * GET -> JSON      /hello/all
        * GET -> JSON      /hello/{id}
        * POST -> JSON     /hello/add
        */
        environment.jersey().register(objectGraph.get(NameResource.class));
    }
}