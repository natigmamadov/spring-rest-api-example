package az.spring.demo.springrestdemonew.enums;

public enum ErrorCodeEnum {
    EMPLOYEE_NOT_FOUND(1001, "can not find any employee with given id"),
    VALIDATION_ERROR(1002, "parameter is not valid"),
    UNKNOWN_ERROR(1003, "parameter is unknown"),
    ACCESS_DENIED(1000, "access is denied");

    private final int code;
    private final String message;

    ErrorCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;

    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
