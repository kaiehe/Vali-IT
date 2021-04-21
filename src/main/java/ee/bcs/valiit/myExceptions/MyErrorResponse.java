package ee.bcs.valiit.myExceptions;

public class MyErrorResponse {
    private String message;
    private Integer code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public MyErrorResponse(String message, Integer code) {
        this.message = message;
        this.code = code;
    }
}
