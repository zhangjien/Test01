package cn.sd.vrd.controller;

import cn.sd.vrd.mapper.CategoryMapper;
import cn.sd.vrd.vo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category/")
public class CategoryController {

    @Autowired(required = false)
    private CategoryMapper mapper;

    @RequestMapping("select")
    public List<Category> select(){
        return mapper.select();
    }

    @RequestMapping("delete")
    public void delete(Integer id){
        mapper.delete(id);
    }

    @RequestMapping("insert")
    public void insert(Category category){
        mapper.insert(category);
    }
}
