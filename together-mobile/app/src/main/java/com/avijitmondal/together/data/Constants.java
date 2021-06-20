package com.avijitmondal.together.data;

public abstract class Constants {
    public static final String REGEX_PORT = "^(80|443|[8-9][0-9]{3})$";
    public static final String REGEX_PORT_80 = "^80$";
    public static final String REGEX_PORT_443 = "^443$";
    public static final String ZERO_TO_255_REGEX = "([01]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])";
    public static final String IPV4_REGEX = "^" + ZERO_TO_255_REGEX + "\\." + ZERO_TO_255_REGEX + "\\." + ZERO_TO_255_REGEX + "\\." + ZERO_TO_255_REGEX + "$";
}
