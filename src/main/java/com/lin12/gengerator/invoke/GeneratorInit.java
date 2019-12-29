package com.lin12.gengerator.invoke;

import com.lin12.gengerator.entity.ColumnInfo;
import com.lin12.gengerator.entity.TableInfo;
import com.lin12.gengerator.utils.ConnectionUtil;
import com.lin12.gengerator.utils.StringUtils;
import com.lin12.gengerator.utils.TypeUtil;
import com.lin12.gengerator.utils.YmlUtils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author lin12
 * @date 2019/12/22
 */
public class GeneratorInit {

    /**
     * 获取数据库连接 查询表信息 并返回对应 bean
     * @return List<TableInfo>
     */
    public List<TableInfo> getTableInfoAll()  {
        Connection connection = ConnectionUtil.getConnection();
        assert connection != null;
        DatabaseMetaData metaData = null;
        try {
            metaData = connection.getMetaData();
            List<TableInfo> tableInfos = YmlUtils.readTableInfo();
            for (TableInfo tableInfo : tableInfos) {
                if(tableInfo.getClassName() == null){
                    tableInfo.setClassName(StringUtils.becomeClassName(tableInfo.getTableName()));
                }
                ResultSet resultSet = metaData.getColumns(connection.getCatalog(), "%", tableInfo.getTableName(), "%");
                //查询主键列
                ResultSet primaryResultSet = metaData.getPrimaryKeys(null, "%", tableInfo.getTableName());
                String primaryKeyColumnName = null;
                while(primaryResultSet.next()){
                    primaryKeyColumnName = primaryResultSet.getString("COLUMN_NAME");
                }
                //遍历每一个列
                while(resultSet.next()) {
                    boolean isPrimaryKey = false;
                    String columnName = resultSet.getString("COLUMN_NAME");
                    String columnType = resultSet.getString("TYPE_NAME");
                    String columnAnnotation = resultSet.getString("REMARKS");
                    if(columnName.equals(primaryKeyColumnName)){
                        isPrimaryKey = true;
                    }
                    columnName = StringUtils.nomenclatureOfHump(columnName);
                    columnType = TypeUtil.getColumnType(columnType);
                    ColumnInfo columnInfo = new ColumnInfo(columnName, columnType, columnAnnotation, isPrimaryKey);
                    List<ColumnInfo> columnInfos = tableInfo.getColumnInfos();
                    columnInfos.add(columnInfo);
                }
                resultSet.close();
            }
            connection.close();
            return tableInfos;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
