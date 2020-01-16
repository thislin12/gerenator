${packageName}

/**
* ${remark}
* @author ${author}
* @date ${date}
*/
@Service
public class ${className} implements ${entityClassName}Service{

    private ${entityClassName}Dao ${entityName}Dao;

    @Autowired
    public void set${entityClassName}Dao(${entityClassName}Dao ${entityName}Dao) {
        this.${entityName}Dao = ${entityName}Dao;
    }

    @Override
    public boolean add${entityClassName}(${entityClassName} ${entityName}){
        return ${entityName}Dao.insert(${entityName}) > 0;
    }

    @Override
    public boolean update${entityClassName}(${entityClassName} ${entityName}){
        return ${entityName}Dao.updateById(${entityName}) > 0;
    }

}
