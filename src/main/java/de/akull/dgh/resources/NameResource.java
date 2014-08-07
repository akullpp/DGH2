package de.akull.dgh.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import de.akull.dgh.database.NameDAO;
import de.akull.dgh.models.Name;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;


// Webservice
@Path("/hello")
@Produces(MediaType.APPLICATION_JSON)
public class NameResource {
    @Inject
    @Named("nameDAO")
    NameDAO dao;

    @Inject
    @Named("template")
    String template;

    @Inject
    @Named("defaultName")
    String defaultName;

    @Timed
    @GET
    public String greet(@QueryParam("name") Optional<String> name) throws InterruptedException {
        return String.format(template, name.or(defaultName));
    }

    @Timed
    @POST
    @Path("/add")
    public Name add(String name) {
        return find(dao.insert(name));
    }

    @Timed
    @GET
    @Path("/{id}")
    public Name find(@PathParam("id") Integer id) {
        return dao.findById(id);
    }

    @Timed
    @GET
    @Path("/all")
    public List<Name> all(@PathParam("id") Integer id) {
        return dao.all();
    }
}