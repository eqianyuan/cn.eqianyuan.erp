package cn.eqianyuan.erp.common.response;

import org.apache.commons.lang.StringUtils;

/**
 * 统一服务响应
 * Created by jason on 2016/1/11.
 */
public class ServerResponse {
    private static String DEFAULT_MSG = "操作成功";
    private static String DEFAULT_CODE = "200";

    private String code;
    private String message;
    private Object data;

    public ServerResponse() {
        this(DEFAULT_CODE, DEFAULT_MSG, null);
    }

    public ServerResponse(Object data){
        this(DEFAULT_CODE, DEFAULT_MSG, data);
    }

    public ServerResponse(String code, String message) {
        this(code, message, null);
    }

    public ServerResponse(String code, String message, Object data) {
        this.code = code;
        if (StringUtils.isNotEmpty(message)) {
            this.message = message;
        }
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 构造响应。 使用方式：
     * <p/>
     * <pre>
     *  ApiResponse.ApiResponseBuilder builder = new ApiResponse.ApiResponseBuilder();
     *  ApiResponse apiResponse =  builder.code(200).message("coupons total").data(new Total("0")).build();
     * </pre>
     */
    public static class ResponseBuilder {
        ServerResponse serverResponse;

        public ResponseBuilder() {
            serverResponse = new ServerResponse();
        }

        /**
         * 设置错误码。默认200
         *
         * @param code 错误码
         * @return ApiResponseBuilder
         */
        public ResponseBuilder code(String code) {
            serverResponse.code = code;
            return this;
        }

        /**
         * 设置消息。默认[操作成功]
         *
         * @param message 错误消息
         * @return ApiResponseBuilder
         */
        public ResponseBuilder message(String message) {
            serverResponse.message = message;
            return this;
        }

        /**
         * 设置响应的具体内容
         *
         * @param data 响应的具体内容
         * @return 内容
         */
        public ResponseBuilder data(Object data) {
            serverResponse.data = data;
            return this;
        }

        /**
         * 构造响应
         *
         * @return 响应
         */
        public ServerResponse build() {
            //参数校验, 并且设置默认值
            if (StringUtils.isEmpty(serverResponse.code)) {
                this.serverResponse.code = DEFAULT_CODE;
            }
            if (StringUtils.isEmpty(serverResponse.message)) {
                this.serverResponse.message = DEFAULT_MSG;
            }

            return serverResponse;
        }
    }
}
