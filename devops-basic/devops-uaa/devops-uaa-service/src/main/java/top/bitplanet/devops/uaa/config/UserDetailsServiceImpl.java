//package top.bitplanet.devops.uaa.config;
//
//import top.bitplanet.devops.uaa.entity.User;
//import top.bitplanet.devops.uaa.mapper.UserMapper;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//
//import javax.annotation.Resource;
//import java.util.ArrayList;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Resource()
//    private UserMapper userMapper;
//
//    @Override
//    public UserDetails loadUserByUsername(String username)
//            throws UsernameNotFoundException {
//        User userDO = userMapper.selectByUsername(username);
//        return new org.springframework.security.core.userdetails.User(
//                username, userDO.getPassword(), true, true, true, true,
//                new ArrayList<>());
//    }
//
//
//    public static void main(String[] args) {
//        String encode = new BCryptPasswordEncoder().encode("123456");
//        System.out.println(encode);
//    }
//}