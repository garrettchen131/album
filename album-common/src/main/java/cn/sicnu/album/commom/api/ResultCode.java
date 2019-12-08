package cn.sicnu.album.commom.api;

public enum  ResultCode implements IResultCode {

    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    VALIDATE_FAILED(404, "参数验证失败"),
    UNAUTHORIZED(401, "暂未登录或者token已经过期"),
    FORBIDDEN(403, "没有相关权限");


    private int code;
    private String message;

    private ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
