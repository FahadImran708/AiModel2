package org.ellaa.ai.Repository;
import org.ellaa.ai.Entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MessageRepo extends JpaRepository<MessageEntity, Long> {


}