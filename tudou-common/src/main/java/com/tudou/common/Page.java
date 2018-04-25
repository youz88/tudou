package com.tudou.common;

import com.github.liuanxin.page.model.PageBounds;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
public class Page implements Serializable {
    private static final long serialVersionUID = -7561416267594208961L;

    /** 前台传递过来的分页参数名 */
    public static final String GLOBAL_PAGE = "page";
    /** 前台传递过来的每页条数名 */
    public static final String GLOBAL_LIMIT = "limit";

    /** 分页默认页 */
    private static final int DEFAULT_PAGE_NO = 1;
    /** 分页默认的每页条数 */
    private static final int DEFAULT_LIMIT = 15;
    /** 最大分页条数 */
    private static final int MAX_LIMIT = 200;

    @ApiParam("当前页数. 不传或传入 0, 或传入负数, 或传入非数字)则默认是 " + DEFAULT_PAGE_NO)
    private int page;
    @ApiParam("每页条数. 不传或传入 0, 或传入负数, 或传入非数字, 或大于 " + MAX_LIMIT + ")则默认是 " + DEFAULT_LIMIT)
    private int limit;

    public Page(String page, String limit) {
        setPage(NumberUtils.toInt(page));
        setLimit(NumberUtils.toInt(limit));
    }

    public void setPage(int page) {
        if (page <= 0) {
            page = DEFAULT_PAGE_NO;
        }
        this.page = page;
    }
    public void setLimit(int limit) {
        if (limit <= 0 || limit > MAX_LIMIT) {
            limit = DEFAULT_LIMIT;
        }
        this.limit = limit;
    }
    public void useDefaultLimit() {
        this.limit = DEFAULT_LIMIT;
    }

    /**
     * 返回 service 端用的 分页对象
     * <pre>
     * app 与 pc 的分页不同, app 不需要进行 select count 查询.
     *
     * 对于 app 用户而言, 只需要一直向上或者向下刷新, app 只管将后台给的数据渲染, 不需要构建 1 2 3 ... 9 10 这些页面按钮
     * PC 前端 根据 后端 返回的总条数 构建出 1 2 3 ... 9 10 这些页面按钮
     * </pre>
     */
    public PageBounds bounds() {
        return new PageBounds(page, limit);
    }
}
