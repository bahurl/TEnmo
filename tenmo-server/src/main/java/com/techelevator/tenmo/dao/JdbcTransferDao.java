package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.List;

@Component
public class JdbcTransferDao implements TransferDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcTransferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

//    @Override
//    public List<Transfer> listAllTransfers() {
//
//    }

    @Override
    @Transactional
    public Long sendMoney(BigDecimal amount, Long sendId, Long receiveId) {

//        String sql = "BEGIN TRANSACTION;" +
//                "UPDATE account SET balance = balance - ? WHERE account_number = ?;" +
//                "UPDATE account SET balance = balance + ? WHERE account_number = ?;" +
//                "INSERT INTO transfer (tranfer_type_id, transfer_status_id, account_from, account_to, amount) VALUES (2, 2, ?, ?, ?);" +
//                "COMMIT;";
//
//        try {
//            jdbcTemplate.update(sql, amount, sendId, receiveId, sendId, receiveId, amount);
//        } catch(DataAccessException e) {
//            System.out.println(e.getMessage());
//        }

        try {
            String firstAccountsql = "UPDATE account SET balance = balance - ? WHERE account_number = ?;";

            jdbcTemplate.update(firstAccountsql, amount, sendId);

            String secondAccountSql = "UPDATE account SET balance = balance + ? WHERE account_number = ?;";

            jdbcTemplate.update(secondAccountSql, amount, receiveId);

            String transferSql = "INSERT INTO transfer (tranfer_type_id, transfer_status_id, account_from, account_to, amount) VALUES (2, 2, ?, ?, ?) RETURNING transfer_id;";

            return jdbcTemplate.queryForObject(transferSql, Long.class, sendId, receiveId, amount);

        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }





}
