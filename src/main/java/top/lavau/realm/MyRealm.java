package top.lavau.realm;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;
import top.lavau.entity.User;
import top.lavau.service.UserService;
import top.lavau.util.PasswordUtil;

import javax.annotation.Resource;

/**
 * description shiro realm 实现
 *
 * @author Leet
 * @date 2020-12-01 17:37
 **/
@Slf4j
public class MyRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        User user = userService.getUserByStuIdAndPassword(usernamePasswordToken.getUsername(),
                PasswordUtil.encrypt(String.valueOf(usernamePasswordToken.getPassword())));
        if (user == null) {
            throw new AccountException();
        }
        ByteSource saltOfCredential = ByteSource.Util.bytes(user.getStuId());
        return new SimpleAuthenticationInfo(user, String.valueOf(usernamePasswordToken.getPassword()),
                saltOfCredential, getName());
    }
}
