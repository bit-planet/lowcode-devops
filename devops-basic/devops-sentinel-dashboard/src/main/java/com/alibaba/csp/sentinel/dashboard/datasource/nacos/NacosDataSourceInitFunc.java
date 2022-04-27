package com.alibaba.csp.sentinel.dashboard.datasource.nacos;

import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.init.InitOrder;
import org.springframework.stereotype.Component;

@InitOrder(-1)
@Component
public class NacosDataSourceInitFunc implements InitFunc {

    @Override
    public void init() throws Exception {
        NacosDataSourceDemo.loadRules();
    }
}