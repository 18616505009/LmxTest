package com.winning.ldaptest.test;

import com.sun.jndi.ldap.LdapCtx;

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
        String filter = "";
        //TODO
        String username = "winning\\l.mx";//xxx为申请的对接账户
        //TODO
        String password = "Lmx123";

        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");//设置连接LDAP的实现工厂
        //TODO
        env.put(Context.PROVIDER_URL, "ldap://172.16.0.11:389");// 指定LDAP服务器的主机名和端口号
        env.put(Context.SECURITY_AUTHENTICATION, "simple");//给环境提供认证方法,有SIMPLE、SSL/TLS和SASL
        env.put(Context.SECURITY_PRINCIPAL, username);//指定进入的目录识别名DN
        env.put(Context.SECURITY_CREDENTIALS, password); //进入的目录密码
        env.put("filter", filter);
        DirContext ctx = null;

        try {
            // 得到初始目录环境的一个引用
            ctx = new InitialDirContext(env);

            //根据名称查找(OK)
            System.out.println("null?-->" + (null == ctx.lookup("cn=l.mx(李孟骁),ou=卫宁健康,dc=winning,dc=com,dc=cn")));

            NamingEnumeration namingEnumeration = (NamingEnumeration) ctx.listBindings("ou=卫宁健康,dc=winning,dc=com,dc=cn");

            while (namingEnumeration.hasMore()) {
                Binding bd = (Binding) namingEnumeration.next();

                LdapCtx ldapCtx = (LdapCtx) bd.getObject();
                System.out.println(bd.getName() + ": " + ldapCtx.getNameInNamespace());

            }

            /*Attributes attributes = new BasicAttributes();
            attributes.put("BaseDN", "ou=卫宁健康,dc=winning,dc=com,dc=cn");
            attributes.put("mail", "l.mx@winning.com.cn");
//            NamingEnumeration namingEnumeration = ctx.search("tlw@winning.com.cn", attributes, new String[]{"dn", "cn", "ou", "uid"});
//            ctx.search("l.mx@winning.com.cn", attributes);

            ctx.search("fangliang@winning.com.cn", attributes);
            ctx.search(new LdapName("l.mx@winning.com.cn"), attributes, new String[]{"dn", "cn", "ou", "uid"});*/


            /*while (namingEnumeration.hasMore()) {
                Binding bd = (Binding) namingEnumeration.next();
                System.out.println(bd.getName() + ": " + bd.getObject());
            }*/


            /*NamingEnumeration bindings = ctx.listBindings("OU=卫宁健康,DC=winning,DC=com,DC=cn");//列举 内部人员

            while (bindings.hasMore()) {
                Binding bd = (Binding) bindings.next();
                System.out.println(bd.getName() + ": " + bd.getObject());
            }*/

        } catch (javax.naming.AuthenticationException e) {
            System.out.println("认证失败");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("认证出错：");
            e.printStackTrace();
        } finally {
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
//        ldapLogin(null, null);

    }

}
