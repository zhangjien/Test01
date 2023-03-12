package cn.sd.vrd.mapper;

import cn.sd.vrd.vo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from user where username=#{username}")
    User selectByUsername(String username);

}
