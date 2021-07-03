package com.evotek.nagakawa_be.repository;


import com.evotek.nagakawa_be.model.User;
import com.evotek.nagakawa_be.utils.CharPool;
import com.evotek.nagakawa_be.utils.QueryUtil;
import com.evotek.nagakawa_be.utils.Validator;
import com.evotek.nagakawa_be.utils.key.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class UserRepositoryCustom {

    public static final Logger _log = LogManager.getLogger(UserRepositoryCustom.class);

    @Autowired
    EntityManager entityManager;

//    public List<User> search(String username){
//        StringBuilder sql = new StringBuilder("Select * from users u ");
//
//        if(username != ""){
//            sql.append("where u.username = :username");
//        }
//
//        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
//        List<Predicate> predicates = new ArrayList<>();
//        if(username != null || username != ''){
//
//        }
//        Query query = entityManager.createNativeQuery(sql.toString(), User.class);
//        query.setParameter("username", username);
//        return query.getResultList();
//
//    }

//    Search Basic
    public List<User> searchBasic(String keyword, int page){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);

        criteriaQuery.select(root);

        List<Predicate> predicates = getUserPridicates(builder, root, keyword);

        if(Validator.isNotNull(predicates)){
            criteriaQuery.where(builder.or(predicates.toArray(new Predicate[predicates.size()])));
        }

        if(page < 1){
            page = 1;
        }
        int firstResult = (page-1)* Constants.PAGE_SIZE;

        TypedQuery<User> query = entityManager.createQuery(criteriaQuery);

        query.setFirstResult(firstResult);
        query.setMaxResults(Constants.PAGE_SIZE);

        List<User> userList = new ArrayList<>();
        userList = query.getResultList();

        System.out.println("Cus------------"+ keyword + "  ---  " + page + "  ---  " + userList);

        return userList;

    }

    public int searchBasicCount(String keyword){

        int result = 0;

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<User> root = criteriaQuery.from(User.class);
//        root.fetch("department", JoinType.LEFT);

        criteriaQuery.select(builder.count(root));

        List<Predicate> predicates = getUserPridicates(builder, root, keyword);

        if(Validator.isNotNull(predicates)){
            criteriaQuery.where(builder.or(predicates.toArray(new Predicate[predicates.size()])));
        }


        TypedQuery<Long> query = entityManager.createQuery(criteriaQuery);


        Long count = query.getSingleResult();
        if(count != null){
            result = count.intValue();
        }
        return result;
    }

    private List<Predicate> getUserPridicates(CriteriaBuilder builder, Root<User> root, String keyword) {
        List<Predicate> predicates = new ArrayList<>();
        try {
            if (Validator.isNotNull(keyword)){
                if(Validator.isDigit(keyword)){
                    predicates.add(builder.equal(root.get("id"), Long.parseLong(keyword)));
                }
//                predicates.add(builder.like(builder.lower(root.get("birthday")),
//                        QueryUtil.getFullStringParam(keyword, true), CharPool.BACK_SLASH));
                predicates.add(builder.like(builder.lower(root.get("email")),
                        QueryUtil.getFullStringParam(keyword, true), CharPool.BACK_SLASH));
                predicates.add(builder.like(builder.lower(root.get("fullName")),
                        QueryUtil.getFullStringParam(keyword, true), CharPool.BACK_SLASH));
                predicates.add(builder.like(builder.lower(root.get("address")),
                        QueryUtil.getFullStringParam(keyword, true), CharPool.BACK_SLASH));
                predicates.add(builder.like(builder.lower(root.get("department").get("name")),
                        QueryUtil.getFullStringParam(keyword, true), CharPool.BACK_SLASH));
                predicates.add(builder.like(builder.lower(root.get("description")),
                        QueryUtil.getFullStringParam(keyword, true), CharPool.BACK_SLASH));
            }
        }catch (Exception e){
            e.printStackTrace();
            _log.error(e.getMessage());
        }

        return predicates;
    }


    //    Search Advance
    public List<User> search(String username, String fullname, String address, Long deptId, Boolean gender,
                             Date birthStart, Date birthEnd, int page){

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);

//        root.fetch("role", JoinType.LEFT);
        List<Predicate> predicates = getUserPridicates(builder, root, username, fullname, address, deptId, gender,
                birthStart, birthEnd);

        CriteriaQuery<User> select = criteriaQuery.select(root);

        if(Validator.isNotNull(predicates)){
            criteriaQuery.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
        }

        if(page < 1){
            page = 1;
        }
        int firstResult = (page-1)* Constants.PAGE_SIZE;

        TypedQuery<User> query = entityManager.createQuery(criteriaQuery);
        query.setFirstResult(firstResult);
        query.setMaxResults(Constants.PAGE_SIZE);

        List<User> userList = new ArrayList<>();
        userList = query.getResultList();

//        Query<User> query = (Query<User>) entityManager.createQuery(criteriaQuery);

        return userList;

    }

    public int searchAdvanceCount(String username, String fullname, String address, Long deptId,
                                  Boolean gender, Date birthStart, Date birthEnd) {
        int value = 0;

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<User> root = criteriaQuery.from(User.class);

        criteriaQuery.select(builder.count(root));

        List<Predicate> predicates = getUserPridicates(builder, root, username, fullname, address, deptId,
                gender, birthStart, birthEnd);

        if(Validator.isNotNull(predicates)){
            criteriaQuery.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
        }

        TypedQuery<Long> query = entityManager.createQuery(criteriaQuery);

        Long count = query.getSingleResult();
        System.out.println("longggggggggg: "+ count);
        if(count != null){
            value = count.intValue();
        }
        System.out.println("Valueeeeeeeeeeeee: " + value);

        return value;
    }


    private List<Predicate> getUserPridicates(CriteriaBuilder builder, Root<User> root,
                                              String email, String fullname, String address, Long deptId,
                                              Boolean gender, Date birthStart, Date birthEnd) {
        List<Predicate> predicates = new ArrayList<>();
        System.out.println("hahah");
        try {
            if(Validator.isNotNull(email)){
                System.out.println("username: " + email);
                predicates.add(builder.like(builder.lower(root.get("email")),
                        QueryUtil.getFullStringParam(email, true), CharPool.BACK_SLASH));
            }
            if(Validator.isNotNull(fullname)){
                System.out.println("fullname: " + fullname);
                predicates.add(builder.like(builder.lower(root.get("fullName")),
                        QueryUtil.getFullStringParam(fullname, true), CharPool.BACK_SLASH));
            }
            if(Validator.isNotNull(address)){
                System.out.println("address: " + address);
                predicates.add(builder.like(builder.lower(root.get("address")),
                        QueryUtil.getFullStringParam(address, true), CharPool.BACK_SLASH));
            }
            if (Validator.isNotNull(deptId)) {
                System.out.println("deptId: " + deptId);
                predicates.add(builder.equal(root.get("department").get("id"), deptId));
            }
            if (Validator.isNotNull(gender)) {
                System.out.println("gender: " + gender);
                predicates.add(builder.equal(root.get("gender"), gender));
            }if (Validator.isNotNull(birthStart)) {
                System.out.println("birthStart: " + birthStart);
                predicates.add(builder.greaterThanOrEqualTo(root.get("birthday"), birthStart));
            }
            if (Validator.isNotNull(birthEnd)) {
                System.out.println("birthEnd: " + birthEnd);
                predicates.add(builder.lessThanOrEqualTo(root.get("birthday"), birthEnd));
            }
        }catch (Exception e){
            e.printStackTrace();
            _log.error(e.getMessage());
        }

        return predicates;
    }

}
