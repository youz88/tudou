package com.tudou.common;

import com.github.liuanxin.page.model.PageList;
import com.google.common.collect.Lists;
import com.tudou.common.util.A;
import com.tudou.common.util.U;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PageInfo<T> implements Serializable {
    private static final long serialVersionUID = 767143231878083335L;

    @ApiModelProperty("总条数. 根据此值和 page limit 构建分页按钮")
    private int total;
    @ApiModelProperty("当前页的数据")
    private List<T> list;

    @SuppressWarnings("unchecked")
    public static <T> PageInfo<T> returnList(List<T> list) {
        if (A.isEmpty(list)) {
            return emptyReturn();
        } else if (list instanceof PageList) {
            return new PageInfo(((PageList) list).getTotal(), Lists.newArrayList(list));
        } else {
            return new PageInfo(list.size(), list);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> PageInfo<T> emptyReturn() {
        return new PageInfo(0, Collections.emptyList());
    }

    @SuppressWarnings("unchecked")
    public static <S,T> PageInfo<T> convert(PageInfo<S> pageInfo) {
        if (U.isBlank(pageInfo)) {
            return emptyReturn();
        } else {
            // 只要总条数
            PageInfo info = new PageInfo();
            info.setTotal(pageInfo.getTotal());
            return info;
        }
    }
}
