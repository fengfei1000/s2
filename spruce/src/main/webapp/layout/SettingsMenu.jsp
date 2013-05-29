<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="u" uri="/WEB-INF/ui.tld"%>
<%
    Map<String, String> ms = new HashMap<String, String>();
    ms.put(item, " class=\"active\" ");
%>


<ul class="nav nav-list">
	<li><h4>Profile</h4></li>
	<li ${ms.get("Profile")}><a href="/settings/profile">Profile</a></li>
	<li ${ms.get("Camera")}><a href="/settings/camera">Camera</a></li>
	<li ${ms.get("Password")}><a href="/settings/password">Password</a></li>
	<li ${ms.get("Notifications")}><a href="/settings/notification">Notifications</a></li>
	<li ${ms.get("SocialMedia")}><a href="/settings/notification">Social
			Media</a></li>
	<li ${ms.get("Account")}><a href="/settings/account">Account</a></li>
	<li><h4>Album</h4></li>
	<li ${ms.get("Settings")}><a href="#">Settings</a></li>
	<li ${ms.get("Themes")}><a href="#">Themes</a></li>
	<li><h4>Friends</h4></li>
	<li ${ms.get("Following")}><a href="#">Following</a></li>
	<li ${ms.get("Followed")}><a href="#">Followed </a></li>
</ul>