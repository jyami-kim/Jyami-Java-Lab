package com.jyami.springbootgettingstartedmaven.dataRedis.account;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by jyami on 2020/07/21
 */
public interface AccountRepository extends CrudRepository<Account, String> {
}
