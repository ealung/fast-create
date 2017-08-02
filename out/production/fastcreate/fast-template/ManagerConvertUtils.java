package ${package};

import com.netease.edu.steam.common.page.ResultPagination;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by ${user} on ${.now}
 */
public class ${modelName}ManagerConvertUtils{
    /**
     * 转换${modelName}Bo为${modelName}Meta
     * @param ${modelName?uncap_first}Bo  Bo
     * @return ${modelName}DTO
     */
    public static ${modelName}DTO convertToDto(${modelName}BO ${modelName?uncap_first}Bo){
        ${modelName}DTO ${modelName?uncap_first}Dto=new ${modelName}DTO();
        <#list fileds as attr>
        ${modelName?uncap_first}Dto.set${attr.name?cap_first}(${modelName?uncap_first}Bo.get${attr.name?cap_first}());
        </#list>
        return ${modelName?uncap_first}Dto;
    }
    /**
     * 转换${modelName}Dto为${modelName}Bo
     * @param ${modelName?uncap_first}Dto Dto
     * @return ${modelName}BO
     */
    public static ${modelName}BO convertToBo(${modelName}DTO ${modelName?uncap_first}Dto){
        ${modelName}BO ${modelName?uncap_first}Bo=new ${modelName}BO();
        <#list fileds as attr>
        ${modelName?uncap_first}Bo.set${attr.name?cap_first}(${modelName?uncap_first}Dto.get${attr.name?cap_first}());
        </#list>
        return ${modelName?uncap_first}Bo;
    }

    /**
     * 转换list
     */
    public static List<${modelName}BO> convertListToBo(List<${modelName}DTO> list) {
        List<${modelName}BO> listBo = new ArrayList<>();
        list.forEach(${modelName?uncap_first}Dto -> {
            listBo.add(convertToBo(${modelName?uncap_first}Dto));
        });
        return listBo;
    }
    /**
     * 转换ResultPagination
     */
    public static ResultPagination<${modelName}BO> convertPageToBo(ResultPagination<${modelName}DTO> resultPagination) {
        ResultPagination<${modelName}BO> resultPaginationBo = new ResultPagination<>();
        if (CollectionUtils.isEmpty(resultPagination.getList())) {
            return resultPaginationBo;
        }

        List<${modelName}DTO> listT = resultPagination.getList();
        List<${modelName}BO> listV = convertListToBo(listT);
        // 分页参数
        BeanUtils.copyProperties(resultPagination, resultPaginationBo);
        resultPaginationBo.setList(listV);
        return resultPaginationBo;
    }

    /**
     * 转换list
     */
    public static List<${modelName}DTO> convertListToDto(List<${modelName}BO> list) {
        List<${modelName}DTO> listDto = new ArrayList<>();
        list.forEach(${modelName?uncap_first}Bo -> {
            listDto.add(convertToDto(${modelName?uncap_first}Bo));
        });
        return listDto;
    }
    /**
     * 转换ResultPagination
     */
    public static ResultPagination<${modelName}DTO> convertPageToDto(ResultPagination<${modelName}BO> resultPagination) {
        ResultPagination<${modelName}DTO> resultPaginationDto = new ResultPagination<>();
        if (CollectionUtils.isEmpty(resultPagination.getList())) {
            return resultPaginationDto;
        }

        List<${modelName}BO> listT = resultPagination.getList();
        List<${modelName}DTO> listV = convertListToDto(listT);
        // 分页参数
        BeanUtils.copyProperties(resultPagination, resultPaginationDto);
        resultPaginationDto.setList(listV);
        return resultPaginationDto;
    }
}
