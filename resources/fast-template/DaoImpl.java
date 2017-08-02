package ${package};

import ${package}.${modelName};
import com.netease.edu.steam.common.page.PageParam;
import com.netease.edu.steam.common.page.ResultPagination;
import com.netease.edu.scratch.itemcenter.util.DDBUtils;
import com.netease.print.daojar.dao.autowired.PolicyObjectDaoSqlBaseOfAutowired;
import com.netease.print.daojar.ext.edu.util.PrintExtEduDaoUtils;
import com.netease.print.daojar.meta.base.DBCondition;
import com.netease.print.daojar.meta.base.DDBParam;
import com.netease.print.daojar.meta.enums.OpSymbolType;
import com.netease.print.daojar.util.PrintDaoUtil;
import org.springframework.stereotype.Repository;

import java.util.Date;
/**
* Created by ${user} on ${.now}.
*/
@Repository("${modelName?uncap_first}Dao")
public class ${modelName}DaoImpl extends PolicyObjectDaoSqlBaseOfAutowired<${modelName}> implements ${modelName}Dao {

    /**
    * 新增 ${modelName?uncap_first}
    *
    * @param ${modelName?uncap_first}
    * @return
    */
    @Override
    public  ${modelName} add${modelName}(${modelName} ${modelName?uncap_first}){
        ${modelName?uncap_first}.setId(0L);
        ${modelName?uncap_first}.setDbCreateTime(new Date());
        ${modelName?uncap_first}.setDbUpdateTime(new Date());
        return addObject(${modelName?uncap_first});
    }

    /**
    * 修改 ${modelName?uncap_first}
    *
    * @param ${modelName?uncap_first}
    * @return
    */
    @Override
    public boolean update${modelName}(${modelName} ${modelName?uncap_first}){

        return updateObjectByKey(${modelName?uncap_first});
    }

    /**
    * 删除 ${modelName?uncap_first}
    *
    * @param id
    * @return
    */
    @Override
    public boolean delete${modelName}(Long id){
        DBCondition dbCondition = new DBCondition();
        // set
        dbCondition.addSetByValue("is_deleted", DataType.DELETE.getValue());
        // where
        dbCondition.addWhereByValue("id", id);
        dbCondition.addWhereByValue("is_deleted", DataType.DEFAUlT.getValue());
        return PrintDaoUtil.updateObjectsWithPrepare(this, dbCondition);
    }

    /**
    * 获取 ${modelName?uncap_first}
    *
    * @param id
    * @return
    */
    @Override
    public ${modelName} get${modelName}(Long id){

        DBCondition dbCondition = new DBCondition();
        // where
        dbCondition.addWhereByValue("id", id);
        dbCondition.addWhereByValue("is_deleted", 0);
        return PrintDaoUtil.queryObjectWithPrepare(this, dbCondition);
    }

    /**
    * 分页查询
    *
    * @param pageParam          分页参数
    * @return
    */
    @Override
    public ResultPagination<${modelName}> queryForPage(PageParam pageParam){

        DDBParam dbParam = PrintExtEduDaoUtils.genDDBParam(pageParam);
        dbParam.setOrderColumn("update_time");
        dbParam.setAsc(false);
        DBCondition dbCondition = new DBCondition();
        dbCondition.addWhereByValue("is_deleted", 0);
        return PrintExtEduDaoUtils.queryObjectsWithPrepare(this, dbCondition, dbParam);

    }
}