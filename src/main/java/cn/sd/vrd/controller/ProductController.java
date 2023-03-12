package cn.sd.vrd.controller;

import cn.sd.vrd.mapper.ProductMapper;
import cn.sd.vrd.vo.Product;
import cn.sd.vrd.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product/")
public class ProductController {

    @Autowired(required = false)
    private ProductMapper mapper;
    /*读取配置文件中的值赋值给dirPath变量*/
    @Value("${dirPath}")
    private String dirPath;

    @RequestMapping("/insert")
    public int insert(Product product, HttpSession session, MultipartFile picFile) throws IOException {
        System.out.println("product = " + product + ", session = " + session);
        //得到登录的用户对象
        User u = (User)session.getAttribute("u");
        if(u==null){//未登录
            return 2;
        }
        //第一件事，把图片存入本地磁盘   按照年月日建文件夹，存图片
        String fileName = picFile.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID()+suffix;
        //String dirPath = "d:/upload";//文件夹路径
        SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");
        String datePath = sdf.format(new Date());//new Date()当前系统时间，得到日期路径
        File dirFile = new File(dirPath + datePath);
        if(!dirFile.exists()){
            dirFile.mkdirs();//若不存在，则创建件夹路径+日期路径
        }
        String filePath = dirPath+datePath+fileName;//图片的完整路径
        picFile.transferTo(new File(filePath));
        //第二件事，把作品相关信息封装到Product对象，存入数据库
        //目前缺url和created
        product.setUrl(datePath+fileName);
        product.setCreated(new Date());
        mapper.insert(product);
        return  1;//发布成功
    }
    @RequestMapping("select")
    public List<Product> select(){
        return mapper.select();
    }
    @RequestMapping("delete")
    public void delete(Integer id){
        //根据id查询作品图片路径
        String url = mapper.selectUrlById(id);
        //删除文件
        new File(dirPath+url).delete();
        //根据id删除数据库中相关作品信息
        mapper.delete(id);
    }
    @RequestMapping("select/view")
    public List<Product> selectView(){
        return mapper.selectView();
    }
    @RequestMapping("select/like")
    public List<Product> selectLike(){
        return mapper.selectLike();
    }
    @RequestMapping("selectById")
    public Product selectById(Integer id){
        //selectById方法只要被执行，就代表该作品被浏览一次
        //浏览量加1
        mapper.view(id);
        return mapper.selectById(id);
    }
    @RequestMapping("like")
    public int like(Integer id,HttpSession session){
        //从session中查是否存在like+id这样的字符串
        String info = (String)session.getAttribute("like"+id);
        if(info==null){//代表没有点过赞
            mapper.like(id);
            session.setAttribute("like"+id,"like");
            return 1;//点赞成功
        }
        return 2;//已经点过赞
    }

    @RequestMapping("selectByCid")
    public List<Product> selectByCid(Integer cid){
        return mapper.selectByCid(cid);
    }
    @RequestMapping("selectByWd")
    public List<Product> selectByWd(String wd){
        return mapper.selectByWd(wd);
    }

}
