package org.tikim.boot.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.tikim.boot.domain.UserCode;
import org.tikim.boot.domain.UserTest;

@Mapper
public interface UserMapper {
    int join(UserTest user);
    void insertKakaoId(UserCode userCode);
    UserCode getKakaoId(Long id);
    UserTest getUser(int id);
}
