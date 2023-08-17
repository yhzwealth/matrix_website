package com.chuang.bootplus.po.project;

import com.chuang.bootplus.entity.User;
import com.chuang.bootplus.vo.user.UserVO;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

/**
 * @ author xinyi
 * @create 2022-03-09 21:34
 */
@Setter
@Getter
public class UserMap implements Comparable{
    UserVO user;
    Integer count;

    @Override
    public int compareTo(@NotNull Object o) {
        if(o instanceof UserMap){
            UserMap userMap = (UserMap) o;
            return -Integer.compare(this.count,userMap.count);
        }
        throw new RuntimeException("传入的数据类型不一致！");
    }
}
