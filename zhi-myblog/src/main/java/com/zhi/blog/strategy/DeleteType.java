package com.zhi.blog.strategy;

import java.util.Collection;

/**
 * @author ftz-lover
 * @version 1.0
 * @date 2023/2/27 21:04
 * 要删除的类型 评论type类型 1.文章 2.友链 3.说说
 */
public interface DeleteType {

    /**
     * 根据Type不同，对应操作不同
     */
    void operate(Collection<Long> ids);

}
