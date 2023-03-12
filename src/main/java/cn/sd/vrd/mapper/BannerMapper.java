package cn.sd.vrd.mapper;

import cn.sd.vrd.vo.Banner;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BannerMapper {

    @Insert("insert into banner values(null,#{url})")
    void insert(Banner banner);

    @Select("select * from banner")
    List<Banner> select();
    @Delete("delete from banner where id=#{id}")
    void delete(Integer id);
}
