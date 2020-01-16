${packageName}

/**
* ${remark}
* @author ${author}
* @date ${date}
*/
@RestController
@RequestMapping("${tableName}")
@Api(value="${remark}接口", tags={"${remark}接口"})
public class ${className} {

    private ${entityClassName}Service ${entityName}Service;

    @Autowired
    public void set${entityClassName}Service(${entityClassName}Service ${entityName}Service) {
    this.${entityName}Service = ${entityName}Service;
    }

    /**
    * 添加${remark}
    * @param ${entityName}		${remark}
    * @return boolean		成功或者失败
    */
    @PostMapping()
    public Object add${entityClassName}(${entityClassName} ${entityName}){
        return ${entityName}Service.add${entityClassName}(${entityName});
    }

    /**
    * 修改${remark}
    * @param ${entityName}		${remark}
    * @return boolean		成功或者失败
    */
    @PutMapping()
    public boolean update${entityClassName}(${entityClassName} ${entityName}){
        return ${entityName}Service.update${entityClassName}(${entityName});
    }

}
