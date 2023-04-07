package com.berat.constant;

public class EndPoint {
    public static final String API = "/api";
    public static final String VERSION = "/v1";

    public static final String TRANSACTION = API+VERSION+"/transaction";

    public static final String CREATE = "/create";
    public static final String EXIST = "/exist";
    public static final String GETONE = "/getone";
    public static final String GETALL = "/getall";
    public static final String TRANSFER = "/transfer";
    public static final String PAYMENT = "/payment";

    public static final String BYACCOUNTID = "/{accountId}";
    public static final String BYAUTHID = "/{authId}";
    public static final String BYTRANSACTIONID = "/{transactionId}";


}
