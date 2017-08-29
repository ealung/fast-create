package ${package};

import com.netease.edu.steam.common.page.ResultPagination;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by ${user} on ${.now}
 */

public class ${modelName}ControllerConvertUtils{
    /**
     * 转换${modelName}Vo为${modelName}Bo
     * @param ${modelName?uncap_first}Vo  Vo
     * @return ${modelName}BO
     */
    public static ${modelName}BO convertToBo(${modelName}VO ${modelName?uncap_first}Vo){
        ${modelName}BO ${modelName?uncap_first}Bo=new ${modelName}BO();
        <#list fields as attr>
        ${modelName?uncap_first}Bo.set${attr.name?cap_first}(${modelName?uncap_first}Vo.get${attr.name?cap_first}());
        </#list>
        return ${modelName?uncap_first}Bo;
    }
    /**
     * 转换${modelName}Dto为${modelName}Bo
     * @param ${modelName?uncap_first}Bo  Bo
     * @return ${modelName}VO
     */
    public static ${modelName}VO convertToVo(${modelName}BO ${modelName?uncap_first}Bo){
        ${modelName}VO ${modelName?uncap_first}Vo=new ${modelName}VO();
        <#list fields as attr>
        ${modelName?uncap_first}Vo.set${attr.name?cap_first}(${modelName?uncap_first}Bo.get${attr.name?cap_first}());
        </#list>
        return ${modelName?uncap_first}Vo;
    }

    /**
     * 转换list
     */
    public static List<${modelName}VO> convertListToVo(List<${modelName}BO> list) {
        List<${modelName}VO> listVo = new ArrayList<>();
        list.forEach(${modelName?uncap_first}Bo -> {
            listVo.add(convertToVo(${modelName?uncap_first}Bo));
        });
        return listVo;
    }
    /**
     * 转换ResultPagination
     */
    public static ResultPagination<${modelName}VO> convertPageToVo(ResultPagination<${modelName}BO> resultPagination) {
        ResultPagination<${modelName}VO> resultPaginationVo = new ResultPagination<>();
        if (CollectionUtils.isEmpty(resultPagination.getList())) {
            return resultPaginationVo;
        }

        List<${modelName}BO> listT = resultPagination.getList();
        List<${modelName}VO> listV = convertListToVo(listT);
        // 分页参数
        BeanUtils.copyProperties(resultPagination, resultPaginationVo);
        resultPaginationVo.setList(listV);
        return resultPaginationVo;
    }

    /**
     * 转换list
     */
    public static List<${modelName}BO> convertListToBo(List<${modelName}VO> list) {
        List<${modelName}BO> listBo = new ArrayList<>();
        list.forEach(${modelName?uncap_first}Vo -> {
            listBo.add(convertToBo(${modelName?uncap_first}Vo));
        });
        return listBo;
    }
    /**
     * 转换ResultPagination
     */
    public static ResultPagination<${modelName}BO> convertPageToBo(ResultPagination<${modelName}VO> resultPagination) {
        ResultPagination<${modelName}BO> resultPaginationBO = new ResultPagination<>();
        if (CollectionUtils.isEmpty(resultPagination.getList())) {
            return resultPaginationBO;
        }

        List<${modelName}VO> listT = resultPagination.getList();
        List<${modelName}BO> listV = convertListToBo(listT);
        // 分页参数
        BeanUtils.copyProperties(resultPagination, resultPaginationBO);
        resultPaginationBO.setList(listV);
        return resultPaginationBO;
    }
}
