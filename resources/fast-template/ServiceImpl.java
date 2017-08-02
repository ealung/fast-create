package ${package};

import com.netease.edu.steam.common.page.PageParam;
import com.netease.edu.steam.common.page.ResultPagination;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import ${package}.${modelName}DTO;
/**
* Created by ${user} on ${.now}.
*/
@Service("${modelName?uncap_first}Service")
public class ${modelName}ServiceImpl implements ${modelName}Service{
    @Resource
    private ${modelName}Dao ${modelName?uncap_first}Dao;
    /**
    * 新增 ${modelName?uncap_first}
    *
    * @param ${modelName?uncap_first}Dto
    * @return
    */
    public ${modelName}DTO add${modelName}(${modelName}DTO ${modelName?uncap_first}Dto){
        ${modelName} ${modelName?uncap_first}=${modelName}ServiceConvertUtils.convertToMeta(${modelName?uncap_first}Dto);
        ${modelName?uncap_first}=${modelName?uncap_first}Dao.add${modelName}(${modelName?uncap_first});
        ${modelName?uncap_first}Dto.setId(${modelName?uncap_first}.getId());
        return ${modelName?uncap_first}Dto;
    }

    /**
    * 修改 ${modelName?uncap_first}
    *
    * @param ${modelName?uncap_first}Dto
    * @return
    */
    public boolean update${modelName}(${modelName}DTO ${modelName?uncap_first}Dto){
        ${modelName} ${modelName?uncap_first}=${modelName}ServiceConvertUtils.convertToMeta(${modelName?uncap_first}Dto);
        return ${modelName?uncap_first}Dao.update${modelName}(${modelName?uncap_first});
    }

    /**
    * 删除 ${modelName?uncap_first}
    *
    * @param id
    * @return
    */
    public boolean delete${modelName}(Long id){
        return ${modelName?uncap_first}Dao.delete${modelName}(id);
    }

    /**
    * 获取 ${modelName?uncap_first}
    *
    * @param id
    * @return
    */
    public ${modelName}DTO get${modelName}(Long id){
        ${modelName} ${modelName?uncap_first} = ${modelName?uncap_first}Dao.get${modelName}(id);
        return ${modelName}ServiceConvertUtils.convertToDto(${modelName?uncap_first});
    }

    /**
    * 分页查询
    *
    * @param pageParam          分页参数
    * @return
    */
    public ResultPagination<${modelName}DTO> queryForPage(PageParam pageParam){
        ResultPagination<${modelName}> resultPagination= ${modelName?uncap_first}Dao.queryForPage(pageParam);
        return ${modelName}ServiceConvertUtils.convertPageToDto(resultPagination);
    }
}