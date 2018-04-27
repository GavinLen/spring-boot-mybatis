package xyz.ieden.mb.config;

import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author ThinkPad
 * @date Created by 2018/4/16 18:01
 */
@Configuration
@MapperScan("xyz.ieden.mb.mapper")
public class MyBatisConfig {

    @Autowired
    private DataSource druidDataSource;

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean() {
        SqlSessionFactoryBean sqlSession = new SqlSessionFactoryBean();
        sqlSession.setDataSource(druidDataSource);

        //扫描entity包 使用别名
        sqlSession.setTypeAliasesPackage("xyz.ieden.mb.entity");

        //分页插件
        PageInterceptor interceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("helperDialect", "mysql");
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        interceptor.setProperties(properties);
        sqlSession.setPlugins(new Interceptor[]{interceptor});


        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        //使用jdbc的getGeneratedKeys获取数据库自增主键值
        configuration.setUseGeneratedKeys(true);
        //使用列别名替换列名 select user as User
        configuration.setUseColumnLabel(true);
        //-自动使用驼峰命名属性映射字段   userId    user_id
        configuration.setMapUnderscoreToCamelCase(true);
        sqlSession.setConfiguration(configuration);
        sqlSession.setFailFast(true);

        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            sqlSession.setMapperLocations(resolver.getResources("classpath:mybatis/mapper/*Mapper*.xml"));
            return sqlSession.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(druidDataSource);
    }


}
