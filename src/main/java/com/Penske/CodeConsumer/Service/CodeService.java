package com.Penske.CodeConsumer.Service;

import dto.CodeVersionDto;
//import com.Penske.CodeConsumer.Dto.CodeVersionDto;
import com.Penske.CodeConsumer.Model.CodeEntity;
import com.Penske.CodeConsumer.Util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CodeService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @KafkaListener(topics = AppConstants.topic_name, groupId = "1")
    public String updateData(CodeVersionDto codeVersionDto) {
        try {
            System.out.println(codeVersionDto);
            Query query = new Query(Criteria.where("code").is(codeVersionDto.getCode()).and("version").is(codeVersionDto.getVersion()));
            Update update = new Update().set("isActive", "TRUE");
            mongoTemplate.updateFirst(query, update, CodeEntity.class);
            return "Status has been updated...";
        } catch (Exception e) {
            System.out.println("Error updating status: " + e.getMessage());
            throw e;
        }

    }
}

