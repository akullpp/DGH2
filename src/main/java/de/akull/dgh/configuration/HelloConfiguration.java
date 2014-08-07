package de.akull.dgh.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


// Gets deserialized from resources/config.yaml
public class HelloConfiguration extends Configuration {
    @JsonProperty
    @NotEmpty
    private String template;

    @JsonProperty
    @NotEmpty
    private String defaultName;

    @JsonProperty
    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();

    public String getTemplate() {
        return template;
    }

    public String getDefaultName() {
        return defaultName;
    }

    public DataSourceFactory getDataSourceFactory() {
        return database;
    }
}