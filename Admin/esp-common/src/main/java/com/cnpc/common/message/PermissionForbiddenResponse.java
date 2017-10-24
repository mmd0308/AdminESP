package com.cnpc.common.message;

import com.cnpc.common.constant.RestCodeConstants;

/**
 * @author billjiang 475572229@qq.com
 * @create 17-9-21
 */
public class PermissionForbiddenResponse extends BaseResponse {
    public PermissionForbiddenResponse(String message) {
        super(RestCodeConstants.PERMISSION_FORBIDDEN_CODE, message);
    }
}
