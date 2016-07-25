package com.winify.cvsi.db.dao.impl;

import com.winify.cvsi.db.dao.UserDao;
import com.winify.cvsi.db.model.User;
import com.winify.cvsi.db.model.enums.RoleEnum;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Repository
@Transactional
public class UserDaoImpl extends AbstractDao<User, Long> implements UserDao {
    public UserDaoImpl() {
        super(User.class);
    }

    public User findByEmail(String email) {
        User user = this.getCurrentSession()
                .createQuery("SELECT u FROM User u WHERE u.email = :pEmail", clazz)
                .setParameter("pEmail", email)
                .getSingleResult();
        return user;
    }

    public Set<RoleEnum> getRoles(Long userId) {
        Set<RoleEnum> roles = new HashSet<>();
        List<String> rolesList = this.getCurrentSession()
                .createNativeQuery("SELECT ur.role_enum_set " +
                                   "FROM user_role AS ur " +
                                   "WHERE ur.user_id = :userId")
                .setParameter("userId", userId)
                .getResultList();
        this.convertRolesFromListToSet(rolesList, roles);
        return roles;
    }

    private void convertRolesFromListToSet(List<String> rolesList, Set<RoleEnum> roles) {
        for (String role : rolesList) {
            for (RoleEnum roleEnum : RoleEnum.values()) {
                if (Objects.equals(roleEnum.toString(), role)) {
                    roles.add(roleEnum);
                }
            }
        }
    }
}
