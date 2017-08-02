package ${package};


import com.netease.edu.scratch.util.view.ResponseView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.netease.edu.steam.common.page.PageParam;
import javax.annotation.Resource;
import com.netease.edu.steam.common.page.ResultPagination;
/**
 * Created by zhangchanglu on 2017/6/16
 * email hzzhangchanglu@corp.netease.com
 */
@RestController
@RequestMapping("/${modelName?uncap_first}")
@Slf4j
public class ${modelName}Controller {
    @Resource
    private ${modelName}Manager ${modelName?uncap_first}Manager;

    @RequestMapping("/list")
    public ResponseEntity list(@RequestParam(defaultValue = "1") Integer pageIndex, @RequestParam(defaultValue = "8") Integer pageSize) {
        PageParam pageParam = PageParam.genInstanceByPage(pageIndex, pageSize);
        ResultPagination<${modelName}BO> resultPagination = ${modelName?uncap_first}Manager.queryForPage(pageParam);
        return ResponseView.success(${modelName}ControllerConvertUtils.convertPageToVo(resultPagination));
    }

    @RequestMapping("/add")
    public ResponseEntity add(${modelName}VO ${modelName?uncap_first}Vo) {
        ${modelName}BO ${modelName?uncap_first}Bo=${modelName}ControllerConvertUtils.convertToBo(${modelName?uncap_first}Vo);
        ${modelName?uncap_first}Bo=${modelName?uncap_first}Manager.add${modelName}(${modelName?uncap_first}Bo);
        ${modelName?uncap_first}Vo= ${modelName}ControllerConvertUtils.convertToVo(${modelName?uncap_first}Bo);
        return ResponseView.success(${modelName?uncap_first}Vo);
    }

    @RequestMapping("/get")
    public ResponseEntity get(Long id) {
        ${modelName}BO ${modelName?uncap_first}Bo= ${modelName?uncap_first}Manager.get${modelName}(id);
        ${modelName}VO ${modelName?uncap_first}Vo= ${modelName}ControllerConvertUtils.convertToVo(${modelName?uncap_first}Bo);
        return ResponseView.success(${modelName?uncap_first}Vo);
    }
    @RequestMapping("/delete")
    public ResponseEntity delete(Long id) {
        Boolean isok= ${modelName?uncap_first}Manager.delete${modelName}(id);
        return ResponseView.success("删除成功");
    }
    @RequestMapping("/update")
    public ResponseEntity update(${modelName}VO ${modelName?uncap_first}Vo) {
        ${modelName}BO ${modelName?uncap_first}Bo=${modelName}ControllerConvertUtils.convertToBo(${modelName?uncap_first}Vo);
        ${modelName?uncap_first}Manager.update${modelName}(${modelName?uncap_first}Bo);
        ${modelName}VO ${modelName}Vo= ${modelName}ControllerConvertUtils.convertToVo(${modelName?uncap_first}Bo);
        return ResponseView.success(${modelName}Vo);
    }
}
