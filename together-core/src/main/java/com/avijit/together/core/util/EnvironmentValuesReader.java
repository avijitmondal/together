package com.avijit.together.core.util;

import com.avijit.together.core.data.Constants;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class EnvironmentValuesReader implements EnvironmentAware {
    private String togetherDatabaseUrl;
    private String togetherConversationUrl;
    private String togetherUserUrl;

    private EnvironmentValuesReader() { }

    public static EnvironmentValuesReader getInstance() {
        return EnvironmentValuesReaderHelper.INSTANCE;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.togetherDatabaseUrl = environment.getProperty(Constants.TOGETHER_DATABASE_URL);
        this.togetherConversationUrl = environment.getProperty(Constants.TOGETHER_CONVERSATION_URL);
        this.togetherUserUrl = environment.getProperty(Constants.TOGETHER_USER_URL);
    }

    public String getTogetherDatabaseUrl() {
        return togetherDatabaseUrl;
    }

    public String getTogetherConversationUrl() {
        return togetherConversationUrl;
    }

    public String getTogetherUserUrl() {
        return togetherUserUrl;
    }

    private static class EnvironmentValuesReaderHelper{
        private static final EnvironmentValuesReader INSTANCE = new EnvironmentValuesReader();
    }
}
