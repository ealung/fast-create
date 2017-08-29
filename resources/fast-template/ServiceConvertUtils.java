package ${package};

import com.netease.edu.steam.common.page.ResultPagination;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by ${user} on ${.now}
 */
public class ${modelName}ServiceConvertUtils{
    /**
     * 转换${modelName}Dto为${modelName}Meta
     * @param ${modelName?uncap_first}Dto  dto
     * @return ${modelName}
     */
    public static ${modelName} convertToMeta(${modelName}DTO ${modelName?uncap_first}Dto){
        ${modelName} ${modelName?uncap_first}=new ${modelName}();
        <#list fields as attr>
        ${modelName?uncap_first}.set${attr.name?cap_first}(${modelName?uncap_first}Dto.get${attr.name?cap_first}());
        </#list>
        return ${modelName?uncap_first};
    }
    /**
     * 转换${modelName}Meta为${modelName}Dto
     * @param ${modelName?uncap_first}     meta
     * @return ${modelName}DTO
     */
    public static ${modelName}DTO convertToDto(${modelName} ${modelName?uncap_first}){
        ${modelName}DTO ${modelName?uncap_first}Dto=new ${modelName}DTO();
        <#list fields as attr>
        ${modelName?uncap_first}Dto.set${attr.name?cap_first}(${modelName?uncap_first}.get${attr.name?cap_first}());
        </#list>
        return ${modelName?uncap_first}Dto;
    }
    
    /**
     * 转换list
     */
    public static List<${modelName}DTO> convertListToDto(List<${modelName}> list) {
        List<${modelName}DTO> listDto = new ArrayList<>();
        list.forEach(${modelName?uncap_first} -> {
            listDto.add(convertToDto(${modelName?uncap_first}));
        });
        return listDto;
        }
    /**
     * 转换ResultPagination
     */
    public static ResultPagination<${modelName}DTO> convertPageToDto(ResultPagination<${modelName}> resultPagination) {
        ResultPagination<${modelName}DTO> resultPaginationDto = new ResultPagination<>();
        if (CollectionUtils.isEmpty(resultPagination.getList())) {
            return resultPaginationDto;
        }

        List<${modelName}> listT = resultPagination.getList();
        List<${modelName}DTO> listV = convertListToDto(listT);
        // 分页参数
        BeanUtils.copyProperties(resultPagination, resultPaginationDto);
        resultPaginationDto.setList(listV);
        return resultPaginationDto;
    }

    /**
     * 转换list
     */
    public static List<${modelName}> convertListToMeta(List<${modelName}DTO> list) {
        List<${modelName}> listMeta = new ArrayList<>();
        list.forEach(${modelName?uncap_first}Dto -> {
            listMeta.add(convertToMeta(${modelName?uncap_first}Dto));
        });
        return listMeta;
    }
    /**
     * 转换ResultPagination
     */
    public static ResultPagination<${modelName}> convertPageToMeta(ResultPagination<${modelName}DTO> resultPagination) {
        ResultPagination<${modelName}> resultPaginationMeta = new ResultPagination<>();
        if (CollectionUtils.isEmpty(resultPagination.getList())) {
            return resultPaginationMeta;
        }

        List<${modelName}DTO> listT = resultPagination.getList();
        List<${modelName}> listV = convertListToMeta(listT);
        // 分页参数
        BeanUtils.copyProperties(resultPagination, resultPaginationMeta);
        resultPaginationMeta.setList(listV);
        return resultPaginationMeta;
    }
}
