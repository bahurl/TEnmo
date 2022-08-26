package com.techelevator.tenmo.dao;

import java.math.BigDecimal;

public interface TransferDao {
    Long sendMoney(BigDecimal amount, Long sendId, Long receiveId);
}
