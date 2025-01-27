package com.db_project.db_project.router;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class DataSourceRouter extends AbstractRoutingDataSource {


    @Override
    protected Object determineCurrentLookupKey() {
        // @Transactionl(readOnly = true) 이면 True 이다.
        boolean readOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();
        return readOnly ? "read" : "write";
    }
}
