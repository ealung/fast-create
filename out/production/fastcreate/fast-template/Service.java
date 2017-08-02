package ${package};

import com.netease.edu.steam.common.page.PageParam;
import com.netease.edu.steam.common.page.ResultPagination;
import ${package}.${modelName}DTO;
/**
* Created by ${user} on ${.now}.
*/
public interface ${modelName}Service {

    /**
    * 新增 ${modelName?uncap_first}
    *
    * @param ${modelName?uncap_first}Dto
    * @return
    */
    ${modelName}DTO add${modelName}(${modelName}DTO ${modelName?uncap_first}Dto);

    /**
    * 修改 ${modelName?uncap_first}
    *
    * @param ${modelName?uncap_first}Dto
    * @return
    */
    boolean update${modelName}(${modelName}DTO ${modelName?uncap_first}Dto);

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
    ${modelName}DTO get${modelName}(Long id);

    /**
    * 分页查询
    *
    * @param pageParam          分页参数
    * @return
    */
    ResultPagination<${modelName}DTO> queryForPage(PageParam pageParam);
}