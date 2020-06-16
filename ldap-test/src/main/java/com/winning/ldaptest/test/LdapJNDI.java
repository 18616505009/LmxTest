package com.winning.ldaptest.test;

import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;

/**
 * @author lmx
 * @date 2020-06-15 16:03
 */
public class LdapJNDI {

    public void JNDILookup() {
        //TODO
        String filter = "xxxx";
        //TODO
        String username = "域账号用户名";//xxx为申请的对接账户
        //TODO
        String password = "域账号密码";

        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");//设置连接LDAP的实现工厂
        //TODO
        env.put(Context.PROVIDER_URL, "ldap://地址:端口");// 指定LDAP服务器的主机名和端口号
        env.put(Context.SECURITY_AUTHENTICATION, "simple");//给环境提供认证方法,有SIMPLE、SSL/TLS和SASL
        env.put(Context.SECURITY_PRINCIPAL, username);//指定进入的目录识别名DN
        env.put(Context.SECURITY_CREDENTIALS, password); //进入的目录密码
        env.put("filter",filter);
        DirContext ctx = null;

        try {
            // 得到初始目录环境的一个引用
            ctx = new InitialDirContext(env);

            //TODO
            NamingEnumeration bindings = ctx.listBindings("ou=xxx,dc=xxx,dc=xxx,dc=xx");//列举 内部人员

            while (bindings.hasMore()) {
                Binding bd = (Binding)bindings.next();
                System.out.println(bd.getName() + ": " + bd.getObject());
            }

        } catch (javax.naming.AuthenticationException e) {
            System.out.println("认证失败");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("认证出错：");
            e.printStackTrace();
        }finally {
            if (ctx != null) {
                try {
                    ctx.close();
                } catch (NamingException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void main(String[] args) {
        LdapJNDI ldapJNDI = new LdapJNDI();
        ldapJNDI.JNDILookup();

    }

}
