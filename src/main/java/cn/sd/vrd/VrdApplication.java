package cn.sd.vrd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
//组件扫描：添加了此注解才能从当前类所在包以及子包中找到过滤器
@ServletComponentScan
@SpringBootApplication
public class VrdApplication {

    public static void main(String[] args) {
        SpringApplication.run(VrdApplication.class, args);
    }

}
