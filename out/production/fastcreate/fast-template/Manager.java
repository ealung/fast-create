package ${package};


import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.netease.edu.steam.common.page.ResultPagination;
import com.netease.edu.steam.common.page.PageParam;
/**
 * Created by zhangchanglu on 2017/6/16
 * email zclsoft@163.com
 */
@Component
public class ${modelName}Manager {

    @Resource
    private ${modelName}Service ${modelName?uncap_first}Service;
    /**
     * 新增 ${modelName?uncap_first}
     *
     * @param ${modelName?uncap_first}Bo
     * @return
     */
    public ${modelName}BO add${modelName}(${modelName}BO ${modelName?uncap_first}Bo){
        ${modelName}DTO ${modelName?uncap_first}Dto=${modelName}ManagerConvertUtils.convertToDto(${modelName?uncap_first}Bo);
        ${modelName?uncap_first}Dto= ${modelName?uncap_first}Service.add${modelName}(${modelName?uncap_first}Dto);
        ${modelName?uncap_first}Bo.setId(${modelName?uncap_first}Dto.getId());
        return ${modelName?uncap_first}Bo;
    }

    /**
     * 修改 ${modelName?uncap_first}
     *
     * @param ${modelName?uncap_first}Bo
     * @return
     */
    public boolean update${modelName}(${modelName}BO ${modelName?uncap_first}Bo){
        ${modelName}DTO ${modelName?uncap_first}Dto=${modelName}ManagerConvertUtils.convertToDto(${modelName?uncap_first}Bo);
        return ${modelName?uncap_first}Service.update${modelName}(${modelName?uncap_first}Dto);
    }

    /**
     * 删除 ${modelName?uncap_first}
     *
     * @param id
     * @return
     */
    public boolean delete${modelName}(Long id){
        return ${modelName?uncap_first}Service.delete${modelName}(id);
    }

    /**
     * 获取 ${modelName?uncap_first}
     *
     * @param id
     * @return
     */
    public ${modelName}BO get${modelName}(Long id){
        ${modelName}DTO ${modelName?uncap_first}Dto = ${modelName?uncap_first}Service.get${modelName}(id);
        return ${modelName}ManagerConvertUtils.convertToBo(${modelName?uncap_first}Dto);
    }

    /**
     * 分页查询
     *
     * @param pageParam          分页参数
     * @return
     */
    public ResultPagination<${modelName}BO> queryForPage(PageParam pageParam){
        ResultPagination<${modelName}DTO> resultPagination= ${modelName?uncap_first}Service.queryForPage(pageParam);
        return ${modelName}ManagerConvertUtils.convertPageToBo(resultPagination);
    }
}
