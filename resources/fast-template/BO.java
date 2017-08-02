package ${package};

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
/**
* Created by ${user} on ${.now}.
*/
@Data
public class ${modelName}BO implements Serializable {

<#list fileds as attr>
    private ${attr.type} ${attr.name};
</#list>

}
