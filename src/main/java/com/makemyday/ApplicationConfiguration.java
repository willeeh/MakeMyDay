package com.makemyday;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.config.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class ApplicationConfiguration extends Configuration
{
    @Valid
        @NotNull
        @JsonProperty
        private DatabaseConfiguration database = new DatabaseConfiguration();

        @NotEmpty
        @JsonProperty
        private String template;

        @NotEmpty
        @JsonProperty
        private String defaultName = "Stranger";

        public DatabaseConfiguration getDatabase()
        {
            return database;
        }

        public String getTemplate()
        {
            return template;
        }

        public String getDefaultName()
        {
            return defaultName;
        }
}
