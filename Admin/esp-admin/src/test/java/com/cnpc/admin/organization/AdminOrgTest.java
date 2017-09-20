package com.cnpc.admin.organization;

import com.cnpc.admin.entity.PageData;
import com.cnpc.admin.organization.service.IAdminOrgService;
import net.minidev.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.StatusResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminOrgTest {


    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac; // 注入WebApplicationContext

    @Before // 在测试开始前初始化工作
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void list() throws Exception {
        JSONObject param = new JSONObject() ;
        param.put("name", "张三");
        String jsonstr = param.toString() ;
        RequestBuilder request = MockMvcRequestBuilders.post("http://localhost:8888/sys/dictionary/list.do")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonstr);
        MvcResult mvcResult = mockMvc.perform(request).andReturn() ;
        System.out.println(mvcResult.getResponse().getContentAsString());

    }
    @Test
    public void findOnlyCode() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("http://localhost:8888/sys/dictionary/findOnlyCode.do?code=code");
        MvcResult mvcResult = mockMvc.perform(request).andReturn() ;
        System.out.println(mvcResult.getResponse().getContentAsString());

    }

}
