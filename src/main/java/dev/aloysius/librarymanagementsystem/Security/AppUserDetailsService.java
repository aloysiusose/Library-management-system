package dev.aloysius.librarymanagementsystem.Security;

import dev.aloysius.librarymanagementsystem.Domains.Patrons;
import dev.aloysius.librarymanagementsystem.Repositories.PatronsRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class AppUserDetailsService implements UserDetailsService {
    private final PatronsRepository patronsRepository;

    public AppUserDetailsService(PatronsRepository patronsRepository) {
        this.patronsRepository = patronsRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return patronsRepository.findByEmail(username)
                .map(AppUserDetails::new)
                .orElseThrow(()-> new UsernameNotFoundException("User with email: %s not found !!!".formatted(username)));
    }

    public static class AppUserDetails extends Patrons implements UserDetails{
        public AppUserDetails(Patrons users) {
            super(users);
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return List.of(()-> this.getRoles().name());
        }

        @Override
        public String getPassword() {
            return this.getUserPassword();
        }

        @Override
        public String getUsername() {
            return this.getEmail();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}
