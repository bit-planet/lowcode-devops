package top.bitplanet.devops.support.core.framework.page;

import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author Le
 * @version 1.0.0
 * @since 2021/12/13 14:46
 */
@Data
public class TYPageResp<T> {

    /**
     * 查询数据列表
     */
    public List<T> records = Collections.emptyList();

    /**
     * 总数
     */
    public long total = 0;
    /**
     * 每页显示条数，默认 10
     */
    public long size = 10;

    /**
     * 当前页
     */
    public long current = 1;


    public long getPages() {
        if (getSize() == 0) {
            return 0L;
        }
        long pages = getTotal() / getSize();
        if (getTotal() % getSize() != 0) {
            pages++;
        }
        return pages;
    }

    /**
     * 是否存在下一页
     *
     * @return true / false
     */
    public boolean hasNext() {
        return this.current < this.getPages();
    }


    public TYPageResp(long current , long size) {
        this.current = current;
        this.size = size;
    }

    public TYPageResp() {
    }
}
