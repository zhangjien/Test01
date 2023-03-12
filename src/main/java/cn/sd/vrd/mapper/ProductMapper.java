package cn.sd.vrd.mapper;

import cn.sd.vrd.vo.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {

    @Insert("insert into product values(null,#{title},#{author},#{intro}," +
            "#{url},0,0,#{created},#{categoryId})")
    void insert(Product product);
    /*当查询的字段名和封装数据对象的属性名不一致时，必须单独指定*/
    @Select("select id,title,author,intro,url,view_count,like_count from product")
    @Result(column = "view_count",property = "viewCount")
    @Result(column = "like_count",property = "likeCount")
    List<Product> select();
    @Delete("delete from product where id=#{id}")
    void delete(Integer id);
    @Select("select id,title,intro,url from product order by view_count desc limit 0,4")
    List<Product> selectView();
    @Select("select id,title,intro,url from product order by like_count desc limit 0,4")
    List<Product> selectLike();
    @Select("select * from product where id=#{id}")
    @Result(column = "view_count",property = "viewCount")
    @Result(column = "like_count",property = "likeCount")
    Product selectById(Integer id);
    @Update("update product set like_count=like_count+1 where id = #{id}")
    void like(Integer id);
    @Update("update product set view_count=view_count+1 where id = #{id}")
    void view(Integer id);

    @Select("select id,title,author,intro,url,view_count,like_count " +
            "from product where category_id=#{cid}")
    @Result(column = "view_count",property = "viewCount")
    @Result(column = "like_count",property = "likeCount")
    List<Product> selectByCid(Integer cid);
                                            //#{xxx} 如果xxx是字符串，在sql中会自动添加两个单引号，作为纯参数
                                            //${xxx} 如果xxx是字符串，在sql中会不会添加两个单引号，作为sql语言的一部分
    @Select("select id,title,author,intro,url,view_count,like_count " +
            "from product where title like '%${wd}%'")
    @Result(column = "view_count",property = "viewCount")
    @Result(column = "like_count",property = "likeCount")
    List<Product> selectByWd(String wd);
    @Select("select url from product where id=#{id}")
    String selectUrlById(Integer id);
}
