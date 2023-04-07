package com.berat.constant;

public class EndPoint {
    public static final String API = "/api";
    public static final String VERSION = "/v1";

    public static final String ACCOUNT = API+VERSION+"/account";

    public static final String CREATE = "/create";
    public static final String GETALL = "/getall";
    public static final String GETONE = "/getone";
    public static final String DELETE = "/delete";
    public static final String EXIST = "/exist";
    public static final String DEACTIVATE = "/deactivate";
    public static final String DEPOSIT = "/deposit";
    public static final String WITHDRAWAL = "/withdrawal";

    public static final String BYACCOUNTID = "/{accountId}";
    public static final String BYCUSTOMERID = "/{customerId}";

}
