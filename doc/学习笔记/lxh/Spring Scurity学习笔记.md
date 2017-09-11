##Spring Security 学习笔记

###简介

#####Spring Security 是基于Spring AOP和Servlet过滤器的安全框架，可用于在web请求和方法调用时的身份确认和授权。

###配置
#####引入namespace
```
<beans:beans xmlns="http://www.springframework.org/schema/security"
xmlns:beans="http://www.springframework.org/schema/beans"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="http://www.springframework.org/schema/beans
		http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
		http://www.springframework.org/schema/security
		http://www.springframework.org/schema/security/spring-security.xsd">
</beans:beans>
```

#####web.xml配置

```
<filter>
<filter-name>springSecurityFilterChain</filter-name>
<filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
</filter>

<filter-mapping>
<filter-name>springSecurityFilterChain</filter-name>
<url-pattern>/*</url-pattern>
</filter-mapping>
```
该配置定义需要交给Spring Security处理的请求，需要注意的是该 filter 一定要定义在其它如 SpringMVC 等拦截请求之前，这里将拦截所有的请求。

#####网页访问权限

使用http元素确定相关权限控制规则
```
<http auto-config='true'>
<intercept-url pattern="/**" access="ROLE_USER" />
</http>
```
`intercept-url` 定义了一个权限控制的规则, 

可以使用多个`intercept-url`元素为不同URL的集合定义不同的访问需求， 它们会被归入一个有序队列中，每次取出最先匹配的一个元素使用。

也可以给`intercept-url`添加一个method属性 来限制匹配一个特定的HTTP method。对于一个模式同时定义在定义了method和未定义method的情况， 指定method的匹配会无视顺序优先被使用。 

- `pattern` 属性表示将对哪些 url 进行权限控制，其也可以是一个正则表达式，如上表示将对所有的 URL 进行权限控制；

- `access` 属性表示在请求对应的 URL 时需要什么权限，是一个以逗号分隔的角色列表，请求的用户只需拥有其中的一个角色就能成功访问对应的 URL。“ROLE_USER” 表示请求的用户应当具有 ROLEUSER 角色。“ROLE” 前缀是一个提示 Spring 使用基于角色的检查的标记。

#####权限认证

有了权限控制的规则了后，接下来需要定义一个AuthenticationManager 用于认证。
```
<!-- 配置一个认证管理器 -->  
<authentication-manager>  
	<authentication-provider> 
        <!-- 从JDBC数据源中获取验证信息，也可使用自定义UserDetailService  -->  
        <jdbc-user-service authorities-by-username-query="select username, role from user where username = ?" users-by-username-query="select username, password, valid from user where username = ?" data-source-ref="dataSource"/>
        <!-- 密码编码 -->
        <password-encoder hash="bcrypt" />
    </authentication-provider>  
</authentication-manager>  
```


AuthenticationManager会依次使用每一个 AuthenticationProvider 进行认证，如果有一个 AuthenticationProvider 认证后的结果不为 null，则表示该 AuthenticationProvider 已经认证成功，之后的 AuthenticationProvider 将不再继续认证。然后直接以该 AuthenticationProvider 的认证结果作为 ProviderManager 的认证结果。

![认证原理](http://img.blog.csdn.net/20150430142806243?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvc2hlaHVuMQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center)

#####表单和基本登录
```
<http auto-config='true'>
    <intercept-url pattern="/login.jsp*" access="IS_AUTHENTICATED_ANONYMOUSLY"/>
    <intercept-url pattern="/**" access="ROLE_USER" />
    <form-login login-page='/login.jsp'/>
</http>
```
`IS_AUTHENTICATED_ANONYMOUSLY`定义了login.jsp可被匿名访问，否则该页面会被/**拦截。

`form-login`有如下几个常用属性：

- `login-page` : 指定登录页面
- `username-parameter` : 前端username对应的input元素的name
- `password-parameter` : 前端password对应的input元素的name
- `default-target-url` : 登陆成功后页面
- `authentication-failure-url` ： 失败页面，默认情况是返回登录页面

#####注销
需在http 元素下定义 logout 元素，若已指定`auto-config='true'`会自动配置，可以指定如下属性

-`invalidate-session` ：表示是否要在退出登录后让当前 session 失效，默认为 true。

-`delete-cookies` ：指定退出登录后需要删除的 cookie 名称，多个 cookie 之间以逗号分隔。

-`logout-success-url`：指定成功退出登录后要重定向的 URL。需要注意的是对应的 URL 应当是不需要登录就可以访问的。

-`success-handler-ref`：指定用来处理成功退出登录的 LogoutSuccessHandler 的引用。


