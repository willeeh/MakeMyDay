package com.makemyday.health;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.yammer.metrics.core.HealthCheck;

public class TemplateHealthCheck extends HealthCheck
{
    private final String template;

    @Inject
    public TemplateHealthCheck( @Named("template") String template )
    {
        super("template");
        this.template = template;
    }

    @Override
    protected HealthCheck.Result check() throws Exception
    {
        final String saying = String.format(template, "TEST");

        if (!saying.contains("TEST"))
        {
            return HealthCheck.Result.unhealthy("template doesn't include a name");
        }

        return HealthCheck.Result.healthy();
    }

}
