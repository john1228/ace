package com.ace.security;


import com.ace.entity.Account;
import com.ace.entity.Staff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ace.entity.User;
import com.ace.service.UserService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminUserDetailsService implements UserDetailsService {
    Logger logger = LoggerFactory.getLogger(AdminUserDetailsService.class);
    @Resource
    private UserService userService;
    @Resource
    private RedisTemplate<String, Staff> redisTemplate;


    public UserDetails loadUserByToken(String token)
            throws UsernameNotFoundException {
        User user = null;
        try {
            logger.info("TOKEN检验");
        } catch (Exception e) {
            throw new UsernameNotFoundException("user select fail");
        }

        if (user == null) {
            throw new UsernameNotFoundException("no user found");
        } else {
            try {
                String role = "ADMIN";
                Account account = new Account();
                account.setAccountId("001");
                account.setAccountName("001-NAME");
                List<Staff> staffList = new ArrayList<>();
                for (int i = 1; i <= 10; i++) {
                    Staff staff = new Staff();
                    staff.setId(Long.valueOf(i));
                    staff.setAccountId("001");
                    staff.setAccountName("001-NAME");
                    staff.setProjectId("001-P-" + i);
                    staff.setProjectName("001-PN-" + i);
                    staff.setOrgId("001-O-" + i);
                    staff.setOrgName("001-ON" + i);
                    staff.setEmpId("001-E-" + i);
                    staff.setEmpName("001-EM-" + i);
                    staffList.add(staff);
                    redisTemplate.opsForList().leftPush(account.getAccountId(), staff);
                }
                account.setStaffList(staffList);
                return new AdminUserDetails(user, role, account);
            } catch (Exception e) {
                throw new UsernameNotFoundException("user role select fail");
            }
        }
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
