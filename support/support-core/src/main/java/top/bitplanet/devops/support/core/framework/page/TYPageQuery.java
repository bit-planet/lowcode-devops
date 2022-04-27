package top.bitplanet.devops.support.core.framework.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 *
 * </p>
 *
 * @author Le
 * @version 1.0.0
 * @since 2021/12/22 9:50
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TYPageQuery<Q> {
    /**
     * 每页显示条数，默认 10
     */
    private long size = 10;

    /**
     * 当前页
     */
    private long current = 1;


    private Q query;

    // {size:10,current:2,query:{username:'张三'}}


}