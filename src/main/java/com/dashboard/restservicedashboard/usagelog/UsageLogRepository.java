package com.dashboard.restservicedashboard.usagelog;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface UsageLogRepository extends MongoRepository<UsageLog, String> {

}
