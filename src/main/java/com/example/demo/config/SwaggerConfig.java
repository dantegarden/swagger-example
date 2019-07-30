package com.example.demo.config;

import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig implements InitializingBean {

    @Autowired
    Environment environment;

    private boolean isProfileActive = false;


    // 配置docket以配置Swagger具体参数
    @Bean
    public Docket testDocket() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .enable(isProfileActive)
                .groupName("测试")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.demo.controller"))
                .paths(PathSelectors.ant("/test/**"))
                .build();
    }

    @Bean
    public Docket userDocket() {
        Parameter token = new ParameterBuilder()
                .name("token")
                .required(true)
                .description("用户登录令牌")
                .parameterType("header")
                .modelRef(new ModelRef("String")).build();
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .enable(isProfileActive)
                .groupName("用户")
                .globalOperationParameters(ImmutableList.of(token) )
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.demo.controller"))
                .paths(PathSelectors.ant("/user/**"))
                .build();
    }

    @Bean
    public Docket addressDocket() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .enable(isProfileActive)
                .groupName("地址")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.demo.controller"))
                .paths(PathSelectors.ant("/address/**"))
                .build();
    }

    /***配置swagger api基本信息**/
    private ApiInfo apiInfo() {
        Contact contact = new Contact("lij", "https://github.com/dantegarden", "760143617@qq.com");
        // public ApiInfo(String title, String description, String version, String termsOfServiceUrl, Contact contact, String ", String licenseUrl, Collection<VendorExtension> vendorExtensions) {
        return new ApiInfo("Swagger使用示例", // 标题
                "演示如何配置Swagger", // 描述
                "v1.0", // 版本
                "公司官网链接", // 组织链接
                contact, // 联系人信息
                "Apach 2.0 许可", // 许可
                "许可链接", // 许可连接
                new ArrayList<>()); // 扩展
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Profiles profiles = Profiles.of("dev", "test");
        isProfileActive = environment.acceptsProfiles(profiles);
    }
}
