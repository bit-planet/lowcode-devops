package top.bitplanet.devops.support.webmvc;

import com.alibaba.cloud.sentinel.rest.SentinelClientHttpResponse;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.transport.heartbeat.client.SimpleHttpResponse;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpResponse;

/**
 * <p>
 *  todo 待测试？？？
 * </p>
 *
 * @author Le
 * @since 2022/1/10
 */
public class ExceptionUtil {
    public static ClientHttpResponse handleException(HttpRequest request, byte[] body, ClientHttpRequestExecution execution, BlockException exception) {
        ClientHttpResponse simpleHttpResponse = new SentinelClientHttpResponse("sentinel restTemplate拦截处理");
        return simpleHttpResponse;
    }
}