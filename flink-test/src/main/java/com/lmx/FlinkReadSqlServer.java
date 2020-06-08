package com.lmx;

import org.apache.flink.api.common.typeinfo.BasicTypeInfo;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.io.jdbc.JDBCInputFormat;
import org.apache.flink.api.java.typeutils.RowTypeInfo;
import org.apache.flink.api.java.utils.ParameterTool;

/**
 * @author lmx
 * @date 2020-05-27 17:12
 * flink读取mysql数据demo
 */
public class FlinkReadSqlServer {

    public static void main(String[] args) throws Exception {
        // 0,读取 country.properties 配置
        ParameterTool pt = ParameterTool.fromPropertiesFile("flink-test/src/main/resources/application.properties");

        //1.获取flink运行环境，flink支持流式处理和批处理，当前需求需要使用流式处理，因此运行环境指明流式处理环境
        ExecutionEnvironment environment = ExecutionEnvironment.getExecutionEnvironment();

        TypeInformation[] typeInformations = new TypeInformation[]{
                BasicTypeInfo.INT_TYPE_INFO,
                BasicTypeInfo.STRING_TYPE_INFO
        };
        RowTypeInfo rowTypeInfo = new RowTypeInfo(typeInformations);

        JDBCInputFormat jdbcInputFormat = JDBCInputFormat.buildJDBCInputFormat()
                //数据库连接信息
                .setDrivername("com.mysql.jdbc.Driver")
//                .setDBUrl("jdbc:mysql://localhost:3306/testdb?useSSL=false&useUnicode=true&characterEncoding=UTF-8")
                .setDBUrl("jdbc:sqlserver://172.16.6.164:1433;databaseName=win60_dcs")
                .setUsername("sa")
                .setPassword("@Welcome164")
                .setQuery("SELECT CS_ID,DRUG_CHEMICAL_NAME FROM ods.ODS_MDM_MATERIAL_CLINICAL_SERVICE_DRUG")//查询sql
                .setRowTypeInfo(rowTypeInfo)
                .finish();


        DataSet s1 = environment.createInput(jdbcInputFormat);
//        System.out.println("数据行：" + s1.count());
        s1.print();
    }

}
