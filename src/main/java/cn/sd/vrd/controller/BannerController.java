package cn.sd.vrd.controller;

import cn.sd.vrd.mapper.BannerMapper;
import cn.sd.vrd.vo.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
/*将Controller类中每个方法拦截相同的路径部分提取出来*/
@RequestMapping("/banner/")
@RestController
public class BannerController {

    @Autowired
    private BannerMapper mapper;
    /*读取配置文件中的值赋值给dirPath变量*/
    @Value("${dirPath}")
    private String dirPath;

    @RequestMapping("select")
    public List<Banner> select(){
        return mapper.select();
    }

    @RequestMapping("delete")
    public void delete(Integer id){
        mapper.delete(id);
    }

    @RequestMapping("insert")
    public void insert(MultipartFile picFile) throws IOException {
        String filename = picFile.getOriginalFilename();//获取文件的原始名称
        String suffix = filename.substring(filename.lastIndexOf("."));//获取文件后缀
        filename = UUID.randomUUID()+suffix;//得到文件的新名字
       // String dirPath = "d:/upload/";//存文件的文件夹
        File dirFile = new File(dirPath);
        if(!dirFile.exists()){//判断文件夹是否存在
            dirFile.mkdirs();
        }
        String filePath = dirPath+"/"+filename;//得到文件的完整路径
        picFile.transferTo(new File(filePath));//将文件保存到指定的路径
        //将轮播图的路径封装在Banner对象
        Banner banner= new Banner();
        banner.setUrl("/"+filename);
        mapper.insert(banner);
    }
}
