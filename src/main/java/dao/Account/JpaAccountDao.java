package dao.Account;

import dao.JpaDao;
import entity.Account;

import javax.ejb.Stateless;

@Stateless
public class JpaAccountDao extends JpaDao<Account, Long> implements AccountDao {
}
