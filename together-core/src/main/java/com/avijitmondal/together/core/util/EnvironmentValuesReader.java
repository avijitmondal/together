package com.avijitmondal.together.core.util;

import com.avijitmondal.together.core.data.Constants;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

public class EnvironmentValuesReader implements EnvironmentAware {
    private String togetherDatabaseUrl;
    private String togetherConversationUrl;
    private String togetherUserUrl;

    private EnvironmentValuesReader() {
    }

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

    private static class EnvironmentValuesReaderHelper {
        private static final EnvironmentValuesReader INSTANCE = new EnvironmentValuesReader();
    }
}
