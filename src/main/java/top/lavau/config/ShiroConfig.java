//package top.lavau.config;
//
//import org.apache.shiro.authc.credential.CredentialsMatcher;
//import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
//import org.apache.shiro.mgt.SecurityManager;
//import org.apache.shiro.realm.Realm;
//import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
//import org.apache.shiro.web.servlet.ShiroFilter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import top.lavau.realm.MyRealm;
//
///**
// * description shiro config
// *
// * @author Leet
// * @date 2020-12-01 17:28
// **/
//@Configuration
//public class ShiroConfig {
//
//    @Bean
//    public CredentialsMatcher credentialsMatcher() {
//        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
//        matcher.setHashAlgorithmName("MD5");
//        matcher.setHashIterations(1024);
//        return matcher;
//    }
//
//    @Bean
//    public Realm realm(CredentialsMatcher credentialsMatcher) {
//        MyRealm realm = new MyRealm();
//        realm.setCredentialsMatcher(credentialsMatcher);
//        return new MyRealm();
//    }
//
//    @Bean
//    public SecurityManager securityManager(Realm realm) {
//        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        securityManager.setRealm(realm);
//        return securityManager;
//    }
//
//    @Bean
//    public ShiroFilter shiroFilter() {
//        ShiroFilter shiroFilter = new ShiroFilter();
//
//        return shiroFilter;
//    }
//}
