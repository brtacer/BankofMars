package com.berat.constant;

public class EndPoint {
    public static final String API = "/api";
    public static final String VERSION = "/v1";

    public static final String AUTH = API+VERSION+"/auth";

    public static final String REGISTER = "/register";
    public static final String LOGIN = "/login";

    public static final String CREATE = "/create";
    public static final String APPROVE = "/approve";
    public static final String UPDATE = "/update";
    public static final String DELETE = "/delete";
    public static final String BLOCK = "/block";
    public static final String PASSWORD = "/password";

    public static final String BYAUTHID = "/{authId}";
    public static final String BYCUSTOMERID = "/{customerId}";

}
