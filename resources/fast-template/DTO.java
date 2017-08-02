package ${package};

import java.io.Serializable;
import java.util.Date;
/**
* Created by ${user} on ${.now}.
*/
public class ${modelName}DTO implements Serializable {
<#list fileds as attr>
    private ${attr.type} ${attr.name};
</#list>

<#list fileds as attr>
    /**
     * set${attr.name?cap_first}
     */
    public void set${attr.name?cap_first}(${attr.type} ${attr.name}){
        this.${attr.name}=${attr.name};
    }
    /**
     * get${attr.name?cap_first}
     */
    public ${attr.type} get${attr.name?cap_first}(){
         return ${attr.name};
    }
</#list>
}
