package com.ht.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

//import org.o7planning.sbsecurity.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ht.entities.UsersRoles;

@Repository
@Transactional
public class AppRoleDAO {

	@Autowired
	private EntityManager entityManager;

	public List<String> getRoleNames(Long userId) {
		String sql = "Select ur.role.name from " + UsersRoles.class.getName() + " ur " //
				+ " where ur.users.idUser = :idUser ";

		Query query = this.entityManager.createQuery(sql, String.class);
		query.setParameter("idUser", userId);
		return query.getResultList();
	}

}