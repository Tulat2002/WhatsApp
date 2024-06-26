package com.devanktu.whatsappspring.repository;

import com.devanktu.whatsappspring.model.Chat;
import com.devanktu.whatsappspring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer> {

    @Query("select c from Chat c join c.users u where u.id=:userId")
    List<Chat> findChatByUserid(@Param("userId")Integer userId);

    @Query("select c from Chat c where c.isGroup=false And :user Member of c.users And :reqUser Member of c.users")
    Chat findSingleChatByUserIds(@Param("user")User user, @Param("reqUser")User reqUser);

}
