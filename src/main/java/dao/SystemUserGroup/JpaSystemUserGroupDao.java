package dao.SystemUserGroup;

import dao.JpaDao;
import entity.SystemUserGroup;

import javax.ejb.Stateless;

@Stateless
public class JpaSystemUserGroupDao extends JpaDao<SystemUserGroup, Long> implements SystemUserGroupDao {
}
