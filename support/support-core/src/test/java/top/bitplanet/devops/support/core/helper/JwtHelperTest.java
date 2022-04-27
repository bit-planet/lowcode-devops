package top.bitplanet.devops.support.core.helper;

import top.bitplanet.devops.support.core.exception.TokenValidateException;

class JwtHelperTest {


    @org.junit.jupiter.api.Test
    void jwt() throws InterruptedException {
        // 生成
        String withRsa256 = JwtHelper.createWithRsa256(1);
        System.out.println(withRsa256);
        // 解析
        //String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6Imxvb2x5IiwiYWRtaW4iOnRydWV9.LP_XmjYe2e5Q2Fxd1qIOA0Mopl7MWyNhXsDFYq-7lTgXevIe6W1n7zerlz8Yft3b9mTqhrVvfn_9Y7ifx8ohjtPq4DCGxGnCSXJiCWJJCd5KR4yk7WMTUjMRYx_9QLS1G1_yJ5HN56iK0lYLLUfwPQvrvVaUcjip7OggqQ8SRis";
        JwtHelper.decodeWithRsa256(withRsa256);
        // 验证签名是否有效
        boolean verify = JwtHelper.verify(withRsa256);
        System.out.println("验证结果==>" + verify );
        // 完整的校验（时间有效性验证）
        Thread.sleep(100);
        try {
            JwtHelper.validate(withRsa256);
        } catch (TokenValidateException e) {
            e.printStackTrace();
        }
    }
}