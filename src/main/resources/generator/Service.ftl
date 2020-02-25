${packageName}

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${entityPath}.${entityClassName};

/**
* ${remark}
* @author ${author}
* @date ${date}
*/
public interface ${className} {

	/**
	* 添加${remark}
	* @param ${entityName}		${remark}
	* @return boolean		成功或者失败
	*/
	boolean add${entityClassName}(${entityClassName} ${entityName});

	/**
	* 分页查询${remark}
	* @param id		${remark}id
	* @return boolean
	*/
	boolean delete${entityClassName}(Integer id);

	/**
	* 修改${remark}
	* @param ${entityName}		${remark}
	* @return boolean		成功或者失败
	*/
	boolean update${entityClassName}(${entityClassName} ${entityName});

	/**
	 * 分页查询${entityName}
	 * @param page		分页参数
	 * @return IPage
	 */
	IPage ${entityName}s(Page page);
}
