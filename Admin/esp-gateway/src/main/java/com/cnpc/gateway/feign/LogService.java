package com.cnpc.gateway.feign;

import com.cnpc.common.vo.LogInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author billjiang 475572229@qq.com
 * @create 17-9-21
 */
@FeignClient(value = "admin")
@RequestMapping(value = "/log")
public interface LogService {
    @PostMapping(value = "/save")
    void saveLog(LogInfo info);
}
