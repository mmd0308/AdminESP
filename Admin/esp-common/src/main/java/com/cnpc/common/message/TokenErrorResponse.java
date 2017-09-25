package com.cnpc.common.message;

import com.cnpc.common.constant.RestCodeConstants;

/**
 * @author billjiang 475572229@qq.com
 * @create 17-9-21
 */
public class TokenErrorResponse extends BaseResponse {
    public TokenErrorResponse(String message) {
        super(RestCodeConstants.TOKEN_ERROR_CODE, message);
    }
}
