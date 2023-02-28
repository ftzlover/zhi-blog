package com.zhi.blog.strategy.context;

import com.zhi.blog.strategy.DeleteType;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Collection;
import java.util.Map;



/**
 * @author ftz-lover
 * @version 1.0
 * @date 2023/2/28 19:06
 */
@Service
public class DeleteStrategyContext {

    @Resource
    private  Map<String, DeleteType> deleteTypeMap;

    public  void operate(String commenttype, Collection<Long> ids){
        deleteTypeMap.get(commenttype).operate(ids);
    }

}
