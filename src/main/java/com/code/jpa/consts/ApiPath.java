package com.code.jpa.consts;

public interface ApiPath {
    String API = "/api/v1";

    //ping
    String PING = API + "/ping";

    //user
    String EMPLOYEE_GET_ALL = API + "/employee/get-all";
    String EMPLOYEE_GET_ONE = API + "/employee/get-employee";
    String EMPLOYEE_CREATE = API + "/employee/create";
    String EMPLOYEE_UPDATE = API + "/employee/update";
    String EMPLOYEE_DELETE = API + "/employee/delete";

}
