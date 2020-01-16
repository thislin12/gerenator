${packageName}

import lombok.Data;

/**
* ${remark}
* @author ${author}
* @date ${date}
*/
@Data
@TableName(value = "${tableName}")
@ApiModel(value="${className}", description="${remark}")
public class ${className} {

${propertyList}

}