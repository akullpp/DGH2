package de.akull.dgh.configuration;

import dagger.Module;
import dagger.Provides;
import de.akull.dgh.database.NameDAO;
import de.akull.dgh.resources.NameResource;

import javax.inject.Named;


// Required by Dagger for DI
@Module(injects = NameResource.class)
public class HelloModule {
    private final HelloConfiguration configuration;
    private final NameDAO nameDAO;

    public HelloModule(HelloConfiguration configuration, NameDAO namedDAO) {
        this.configuration = configuration;
        this.nameDAO = namedDAO;
    }

    @Provides
    @Named("template")
    String provideTemplate() {
        return configuration.getTemplate();
    }

    @Provides
    @Named("defaultName")
    String provideDefaultName() {
        return configuration.getDefaultName();
    }

    @Provides
    @Named("nameDAO")
    NameDAO provideNameDAO() {
        return nameDAO;
    }
}
