${packageName}

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ${entityPath}.${entityClassName};
import ${daoPath}.${entityClassName}Dao;
import ${servicePath}.${entityClassName}Service;

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
    public boolean delete${entityClassName}(Integer id) {
        return ${entityName}Dao.deleteById(id) > 0;
    }

    @Override
    public boolean update${entityClassName}(${entityClassName} ${entityName}){
        return ${entityName}Dao.updateById(${entityName}) > 0;
    }

    @Override
    public IPage ${entityName}s(Page page) {
        return ${entityName}Dao.selectPage(page, null);
    }

}
