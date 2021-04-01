package com.hg.model;

import lombok.Data;

import java.util.Date;

@Data
public class HGLogBean {

    private Date logTime;
    private String userName;
    private String databaseName;
    private Integer processId;
    private String connectionFrom;
    private String sessionId;
    private Integer sessionLineNum;
    private String commandTag;
    private Date session_startTime;
    private String virtual_transactionId;
    private Integer transactionId;
    private String errorSeverity;
    private String sqlStateCode;
    private String message;
    private String detail;
    private String hint;
    private String internalQuery;
    private Integer internalQueryPos;
    private String context;
    private String query;
    private Integer queryPos;
    private String location;
    private String applicationName;
}
