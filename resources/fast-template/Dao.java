package ${package};

import com.netease.edu.steam.common.page.PageParam;
import com.netease.edu.steam.common.page.ResultPagination;
import ${package}.${modelName};
/**
* Created by ${user} on ${.now}.
*/
public interface ${modelName}Dao {

    /**
    * 新增 ${modelName?uncap_first}
    *
    * @param ${modelName?uncap_first}
    * @return
    */
    ${modelName} add${modelName}(${modelName} ${modelName?uncap_first});

    /**
    * 修改 ${modelName?uncap_first}
    *
    * @param ${modelName?uncap_first}
    * @return
    */
    boolean update${modelName}(${modelName} ${modelName?uncap_first});

    /**
    * 删除 ${modelName?uncap_first}
    *
    * @param id
    * @return
    */
    boolean delete${modelName}(Long id);

    /**
    * 获取 ${modelName?uncap_first}
    *
    * @param id
    * @return
    */
    ${modelName} get${modelName}(Long id);

    /**
    * 分页查询
    *
    * @param pageParam          分页参数
    * @return
    */
    ResultPagination<${modelName}> queryForPage(PageParam pageParam);
}