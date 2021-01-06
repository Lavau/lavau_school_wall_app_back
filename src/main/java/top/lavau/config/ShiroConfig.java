package top.lavau.config;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import top.lavau.realm.MyRealm;

import java.util.HashMap;
import java.util.Map;

/**
 * description shiro config
 *
 * @author Leet
 * @date 2020-12-01 17:28
 **/
@EnableAutoConfiguration
public class ShiroConfig {

    @Bean
    public CredentialsMatcher credentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("MD5");
        matcher.setHashIterations(1024);
        return matcher;
    }

    @Bean
    public Realm realm(CredentialsMatcher credentialsMatcher) {
        MyRealm realm = new MyRealm();
        realm.setCredentialsMatcher(credentialsMatcher);
        return new MyRealm();
    }

    @Bean
    public SecurityManager securityManager(Realm realm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 设置默认登录的 url（若登录失败，则转到此）
        shiroFilterFactoryBean.setLoginUrl("/app/noLogin/error");
        // 设置登录认证成功后默认转到的 url
//        shiroFilterFactoryBean.setSuccessUrl("/admin/index");
        // 设置权限认证失败时转到的 url
        shiroFilterFactoryBean.setUnauthorizedUrl("/app/noLogin/noAccess");

        /*
         * anon：匿名用户可访问
         * authc：认证用户可访问
         * user：使用rememberMe可访问
         * perms：对应权限可访问
         * roles[角色名]：对应角色权限可访问
         */
        //设置访问各 url 的权限
        Map<String, String> filterChain = new HashMap<>(5);
        filterChain.put("/app/login/**", "authc");
        filterChain.put("/app/noLogin/**", "anon");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChain);

        return shiroFilterFactoryBean;
    }
}
