package com.dashboard.restservicedashboard;

import com.dashboard.commondashboard.Account;
import com.dashboard.commondashboard.AccountRepository;
import com.dashboard.restservicedashboard.configuration.AppProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService  {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    private AppProperties appProp;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if(appProp.getAlmAuthentication()) {
            return User.withUsername(username)
                    .password("fake")
                    .roles("ADMIN").build();
        }
        else {
            Account account = accountRepository.findByUsername(username);
            //System.out.println(account.getUsername()+":"+account.getPassword());
            if (account != null) {
                return User.withUsername(account.getUsername())
                        .password(account.getPassword())
                        .roles(account.getRole()).build();
            } else {
                System.out.println("could not find the user '" + username + "'");
                throw new UsernameNotFoundException("could not find the user '" + username + "'");

            }
        }
    }
}