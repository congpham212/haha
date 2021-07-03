package com.evotek.nagakawa_be.repository;


import com.evotek.nagakawa_be.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {



    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

    boolean existsById(Long id);

    List<User> findAllByStatus(Boolean status);

//    List<User> findByBirthdayContaining(String key);
//
//    List<User> findByEmailContaining(String key);
//
//    List<User> findByFullNameContaining(String key);
//
//    List<User> findByAddressContaining(String key);
//
//    List<User> findByDepartment_NameContaining(String key);
//
//    List<User> findByDescriptionContaining(String key);


//    @Query("SELECT u FROM User u where " +
//            "lower(u.username) like lower(concat('%', :username, '%')) and " +
//            "lower(u.fullName) like lower(concat('%', :fullname, '%')) and " +
//            "lower(u.address) like lower(concat('%', :address, '%')) and " +
//            "concat(u.gender,'') like lower(:gender) and " +
//            "concat(u.department.id,'') like lower(:departmentId) and " +
//            "u.birthday >= (:birthStart) and " +
//            "u.birthday <= (:birthEnd)"
//    )
//    Page<User> searchAdvanced(@Param("username")String username, @Param("fullname") String fullname,
//                              @Param("address")String address, @Param("gender")String gender,
//                              @Param("departmentId")String departmentId, @Param("birthStart")Date birthStart,
//                              @Param("birthEnd")Date birthEnd, Pageable pageable);
//
//
//
//    @Query("SELECT u FROM User u where " +
//            "lower(u.username) like lower(concat('%', :username, '%')) and " +
//            "lower(u.fullName) like lower(concat('%', :fullname, '%')) and " +
//            "lower(u.address) like lower(concat('%', :address, '%')) and " +
//            "concat(u.gender,'') like lower(:gender) and " +
//            "concat(u.department.id,'') like lower(:departmentId)"
//    )
//    Page<User> searchAdvanced(@Param("username")String username, @Param("fullname") String fullname,
//                              @Param("address")String address, @Param("gender")String gender,
//                              @Param("departmentId")String departmentId, Pageable pageable);


//    @Query("SELECT u FROM User u where " +
//            "lower(u.id) like lower(concat('%', :username, '%')) and " +
//            "lower(u.username) like lower(concat('%', :username, '%')) and " +
//            "lower(u.fullName) like lower(concat('%', :fullName, '%')) and " +
//            "lower(u.address) like lower(concat('%', :address, '%')) and " +
//            "(u.gender)  = (:gender) and " +
//            "u.department.id = ( :departmentId) and " +
//            "u.birthday >= (:birthStart) and " +
//            "u.birthday <= (:birthEnd) "
//    )
//    Page<User> searchA(@Param("keyword")String key, Pageable pageable);


}
