package top.bitplanet.devops.support.webmvc;

import top.bitplanet.devops.support.core.helper.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 *  springMVC 统一异常处理类
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
	/**
	 * 参数校验异常
	 * @param req
	 * @param e
	 * @return
	 */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
	public R bizExceptionHandler(HttpServletRequest req, MethodArgumentNotValidException e){
    	log.error("参数校验异常！原因是：{}",e.getMessage());
    	Map<String,String> map = new HashMap<>();
		for (FieldError fieldError : e.getFieldErrors()) {
			map.put(fieldError.getField(),fieldError.getDefaultMessage());
		}
    	return R.fail("参数校验异常",map);
    }


}

