package com.resources.utils.validators;

@SuppressWarnings("unused")
public class Regex {

    public static final String EMAIL =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final String ALPHA_NUMERIC =
            "^[A-za-z0-9-\\+]+$";

    public static final String NUMERIC =
            "^[0-9]+$";

    public static final String DECIMAL =
            "^[0-9]+(\\.[0-9][0-9]?)?$";

    public static final String NOT_EMPTY =
            "^(?=\\s*\\S).*$";

    public static final String INTERNAL_NUMBER =
            "^[a-zA-Z0-9[:space:]]*$";

    public static final String RFC =
            "^([A-Z|a-z|&amp;]{3}\\d{2}((0[1-9]|1[012])(0[1-9]|1\\d|2[0-8])|(0[13456789]|1[012])(29|30)|(0[13578]|1[02])31)|([02468][048]|[13579][26])0229)(\\w{2})([A|a|0-9]{1})$|^([A-Z|a-z]{4}\\d{2}((0[1-9]|1[012])(0[1-9]|1\\d|2[0-8])|(0[13456789]|1[012])(29|30)|(0[13578]|1[02])31)|([02468][048]|[13579][26])0229)((\\w{2})([A|a|0-9]{1})){0,3}$";


    public static final String SPECIAL_ALPHA_NUMERIC =
            "^[A-za-z0-9-/_()]+$";
}
