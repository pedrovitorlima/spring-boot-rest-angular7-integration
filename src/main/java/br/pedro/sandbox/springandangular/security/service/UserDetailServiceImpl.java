package br.pedro.sandbox.springandangular.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.pedro.sandbox.springandangular.security.domain.User;
import br.pedro.sandbox.springandangular.security.repository.UserRepository;

@Service("userDetailsServiceImpl")
public class UserDetailServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		User user = userRepository.findByLogin(username);
		
		if (user == null) {
			return null;
		}
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_ADMIN");
		org.springframework.security.core.userdetails.User userSpr = 
				new org.springframework.security.core.userdetails.User(
					user.getLogin(), 
					encoder.encode(user.getPassword()), 
					grantedAuthorities); 
		
		return userSpr;
		
	}
}