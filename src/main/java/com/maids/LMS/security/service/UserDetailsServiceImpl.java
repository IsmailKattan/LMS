package com.maids.LMS.security.service;


import com.maids.LMS.repository.PatronRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final PatronRepository patronRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // find the user by username
        return patronRepository.findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User Not Found with -> username or email : " + username)
                );
    }
}
