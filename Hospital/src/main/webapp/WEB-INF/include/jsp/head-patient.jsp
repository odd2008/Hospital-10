<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="utb" uri="123123" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="navbar navbar-default" id="navbar">
			<div class="navbar-container" id="navbar-container">
				<div class="navbar-header pull-left">
					<a href="#" class="navbar-brand">
						<small>
							<i class="icon-leaf"></i>
							XXX医院管理系统（病人）
						</small>
					</a>
				</div>

				<div class="navbar-header pull-right" role="navigation">
					<ul class="nav ace-nav">
						<li class="light-blue">
							<a data-toggle="dropdown" href="#" class="dropdown-toggle">
								<img class="nav-user-photo" src="/Hospital/assets/avatars/user.jpg" alt="Jason's Photo" />
								<span class="user-info">
									<small>欢迎光临,</small>
									${currusername }
								</span>
								<i class="icon-caret-down"></i>
							</a>

							<ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
								<%-- <li>
									<a href="<%=path%>/user/userPatientPasswordSet">
										<i class="icon-cog"></i>
										密码设置
									</a>
								</li>

								<li>
									<a href="<%=path%>/user/userPatientInfoSet">
										<i class="icon-user"></i>
										个人资料修改
									</a>
								</li> --%>
								${utb:gethtml(role,"HEAD") }
								<li class="divider"></li>

								<li>
									<a href="<%=path%>/user/userLogout">
										<i class="icon-off"></i>
										退出
									</a>
								</li>
							</ul>
						</li>
					</ul>
				</div>
			</div>
		</div>
		