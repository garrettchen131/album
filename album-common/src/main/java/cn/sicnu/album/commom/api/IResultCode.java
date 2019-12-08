package cn.sicnu.album.commom.api;

public interface IResultCode {
    /**
     * 获取错误码
     * @return
     */
    int getCode();

    /**
     * 获取错误信息
     * @return
     */
    String getMessage();
}
