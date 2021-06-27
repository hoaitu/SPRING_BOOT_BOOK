package com.ht.service;

import java.util.ArrayList;
import java.util.List;

//import org.o7planning.sbsecurity.dao.AppUserDAO;
//import org.o7planning.sbsecurity.entity.AppUser;
//import org.o7planning.sbsecurity.dao.AppRoleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ht.dao.AppRoleDAO;
import com.ht.dao.AppUserDAO;
import com.ht.entities.UsersRoles;
import com.ht.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private AppUserDAO appUserDAO;

	@Autowired
	private AppRoleDAO appRoleDAO;

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
//		com.ht.entities.User user = this.userRepository.findByUserName(username);

		com.ht.entities.User user = this.appUserDAO.findUserAccount(username);

		if (user == null) {
			System.out.println("User not found! " + username);
			throw new UsernameNotFoundException("User " + username + " was not found in the database");
		}

		System.out.println("Found User: " + user);

		// [ROLE_USER, ROLE_ADMIN,..]
		List<String> roleNames = this.appRoleDAO.getRoleNames(user.getIdUser());

		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		if (roleNames != null) {
			for (String role : roleNames) {
				// ROLE_USER, ROLE_ADMIN,..
				GrantedAuthority authority = new SimpleGrantedAuthority(role);
				grantList.add(authority);
			}
		}

		UserDetails userDetails = (UserDetails) new User(user.getUsername(), //
				user.getPassword(), grantList);

		return userDetails;

//		

//		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//		for (UsersRoles usersRoles : user.getUsersRoleses()) {
//			authorities.add(new SimpleGrantedAuthority(usersRoles.getRole().getName()));
//			authorities.add((GrantedAuthority) authorities);
//
//		}
//
////		
//
//		boolean enabled = true;
//		boolean accountNonExpired = true;
//		boolean credentialsNonExpired = true;
//		boolean accountNonLocked = true;
////		return new User(username, user.getPassword(), enabled, accountNonExpired, credentialsNonExpired,
////				accountNonLocked, user.getAuthorities());
//
//		return new org.springframework.security.core.userdetails.User(username, user.getPassword(), enabled,
//				accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);

	}

//	14/06/2021

}
