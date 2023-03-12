package cn.sd.vrd.mapper;

import cn.sd.vrd.vo.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {

    @Insert("insert into category values(null,#{name})")
    void insert(Category category);

    @Select("select * from category")
    List<Category> select();

    @Delete("delete from category where id=#{id}")
    void delete(Integer id);
}
