${packageName}

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ${entityPath}.${entityClassName};
import ${servicePath}.${entityClassName}Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
* ${remark}
* @author ${author}
* @date ${date}
*/
@RestController
@RequestMapping("${tableName}")
public class ${className} {

    private ${entityClassName}Service ${entityName}Service;

    @Autowired
    public void set${entityClassName}Service(${entityClassName}Service ${entityName}Service) {
    this.${entityName}Service = ${entityName}Service;
    }

    /**
    * 添加${remark}
    * @param ${entityName}		${remark}
    * @return ResponseEntity
    */
    @PostMapping()
    public ResponseEntity add${entityClassName}(${entityClassName} ${entityName}){
        return ResponseEntity.ok(${entityName}Service.add${entityClassName}(${entityName}));
    }

    /**
     * 删除${remark}
     * @param id		id
     * @return ResponseEntity
     */
    @DeleteMapping
    public ResponseEntity delete${entityClassName}(Integer id){
        return ResponseEntity.ok(${entityName}Service.delete${entityClassName}(id));
    }

    /**
    * 修改${remark}
    * @param ${entityName}		${remark}
    * @return ResponseEntity
    */
    @PutMapping()
    public ResponseEntity update${entityClassName}(${entityClassName} ${entityName}){
        return ResponseEntity.ok(${entityName}Service.update${entityClassName}(${entityName}));
    }

    /**
     * 分页查询${remark}
     * @param page		分页参数
     * @return ResponseEntity
     */
    @GetMapping("page")
    public ResponseEntity ${entityName}s(Page page){
        return ResponseEntity.ok(${entityName}Service.${entityName}s(page));
    }

}
