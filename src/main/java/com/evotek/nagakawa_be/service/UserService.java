package com.evotek.nagakawa_be.service;


import com.evotek.nagakawa_be.model.User;
import com.evotek.nagakawa_be.repository.UserRepository;
import com.evotek.nagakawa_be.repository.UserRepositoryCustom;
import com.evotek.nagakawa_be.utils.Validator;
import com.evotek.nagakawa_be.utils.key.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserService implements GeneralService<User> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRepositoryCustom userRepositoryCustom;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findAllActive() {
        return userRepository.findAllByStatus(true);
    }


//    public List<User> findAll(int page) {
//
//        TypedQuery<User> query ;
//        query.setFirstResult(page* Constants.PAGE_SIZE);
//        query.setMaxResults(Constants.PAGE_SIZE);
//        return query.getResultList();
//    }


    public List<User> findByPage(int page) {
        if (page < 1) {
            page = 1;
        }
        Pageable paging = PageRequest.of(page - 1, Constants.PAGE_SIZE);

        Page<User> pagedResult = userRepository.findAll(paging);
        System.err.println("size: " + pagedResult.getContent().size());

        return pagedResult.getContent();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void update(Long id, User user) {
//        User user1 = userRepository.existsByUsername(user.getUsername());
        if (!userRepository.existsByEmail(user.getEmail())) {
            System.out.println("Không tìm thấy user có id: " + id + " để update");
            return;
        }
        user.setId(id);
//        userRepository.delete(user1);
        userRepository.saveAndFlush(user);
        System.out.println("Update Successful!");
    }

    @Override
    public void remove(Long id) {
        User user = userRepository.findById(id).get();
        if (user.equals(null)) {
            System.out.println("Không tìm thấy user có id: " + id + " để remove");
            return;
        }
        userRepository.delete(user);
        System.out.println("Remove Successful!");
    }

    public int totalPage(String keyword) {
        int result = userRepositoryCustom.searchBasicCount(keyword);
//        Long count = userRepository.count();
//        if (count != null) {
//            result = count.intValue();
//        }
        return result;
    }

    public int totalPage(String username, String fullname, String address, Long deptId, Boolean gender,
                         Date birthStart, Date birthEnd) {
        int result = userRepositoryCustom.searchAdvanceCount(username, fullname, address, deptId,
                gender, birthStart, birthEnd);
//        Long count = userRepository.count();
//        if (count != null) {
//            result = count.intValue();
//        }
        return result;
    }

    public List<User> getUserWithBirthDayContainKey(String key) {
        key = key.trim();
        List<User> userList = userRepository.findAll();
        List<User> result = new ArrayList<>();
        if (Validator.isNotNull(userList)) {
            for (User user : userList) {
                if (String.valueOf(user.getBirthday()).contains(key)) {
                    result.add(user);
                }
            }
        }
        return result;
    }


//    public List<User> findByUsernameContaining(String key) {
//        return userRepository.findByUsernameContaining(key);
//    }
//
//    public List<User> findByFullNameContaining(String key) {
//        return userRepository.findByFullNameContaining(key);
//    }
//
//    public List<User> findByAddressContaining(String key) {
//        return userRepository.findByAddressContaining(key);
//    }
//
//    public List<User> findByDepartment_NameContaining(String key) {
//        return userRepository.findByDepartment_NameContaining(key);
//    }
//
//    public List<User> findByDescriptionContaining(String key) {
//        return userRepository.findByDescriptionContaining(key);
//    }

//    public List<User> findByBasicSearchKey(String key) {
//        Set<User> userSet = new HashSet<>();
//        userSet.addAll(findByUsernameContaining(key));
//        userSet.addAll(findByFullNameContaining(key));
//        userSet.addAll(findByAddressContaining(key));
//        userSet.addAll(findByDepartment_NameContaining(key));
//        userSet.addAll(findByDescriptionContaining(key));
//        List<User> userList = new ArrayList<>();
//        userList.addAll(userSet);
//        return userList;
//    }
//
//    public List<User> searchBasic(String keyword) {
//        if (keyword == "" || keyword == null) {
//            return findAllActive();
//        } else {
//            Set<User> userSet = new HashSet<>();
//            if (Validator.isDigit(keyword)) {
//                if (userRepository.existsById(Long.parseLong(keyword))) {
//                    User user = findById(Long.parseLong(keyword));
//                    System.out.println("not null");
//                    userSet.add(user);
//                }
//                userSet.addAll(getUserWithBirthDayContainKey(keyword));
//            }
//            userSet.addAll(findByBasicSearchKey(keyword));
//
//            List<User> userList = new ArrayList<>();
//            userList.addAll(userSet);
//            return userList;
//        }
//    }

    public List<User> searchBasic(String key, int page){
        System.out.println("Service------------"+ key + "  ---  " + page);
        return userRepositoryCustom.searchBasic(key, page);
    }

    public List<User> searchAdvanced(String username, String fullname, String address, Long deptId, Boolean gender,
                             Date birthStart, Date birthEnd, int page){
        return userRepositoryCustom.search(username, fullname, address, deptId, gender, birthStart, birthEnd, page);
    }

//    public List<User> searchBasic(String keyword, int page) {
//        if (keyword == "" || keyword == null) {
//            return findByPage(page);
//        } else {
//            Set<User> userSet = new HashSet<>();
//            if (Validator.isDigit(keyword)) {
//                if (userRepository.existsById(Long.parseLong(keyword))) {
//                    User user = findById(Long.parseLong(keyword));
//                    System.out.println("not null");
//                    userSet.add(user);
//                }
//                userSet.addAll(getUserWithBirthDayContainKey(keyword));
//            }
//            userSet.addAll(findByBasicSearchKey(keyword));
//
//            List<User> userList = new ArrayList<>();
//            userList.addAll(userSet);
//            return userList;
//        }
//    }

//    public List<User> searchAdvanced(String username, String fullname, String address, Long deptId,
//                                     Date birthStart, Date birthEnd, Boolean gender, int page) {
//        if(page < 1){
//            page = 1;
//        }
//        Pageable paging = PageRequest.of(page - 1, Constants.PAGE_SIZE);
//
//        Page<User> pagedResult = userRepository.searchAdvanced(username, fullname, address, gender,
//                deptId, birthStart, birthEnd, paging);
//
//        System.err.println("size: " + pagedResult.getContent().size());
//
//        return pagedResult.getContent();
//
//    }


//    public List<User> getList(int page){
//        return userRepository.findBy();
//    }
}
