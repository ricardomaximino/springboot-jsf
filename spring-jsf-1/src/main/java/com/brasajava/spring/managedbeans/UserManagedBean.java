package com.brasajava.spring.managedbeans;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.brasajava.beans.User;

@Component
@Scope("request")
public class UserManagedBean extends User {
	private static final Logger log = LoggerFactory.getLogger(UserManagedBean.class);

	@PostConstruct
	public void init() {
		log.info("UserManagedBean Starts");
	}

	@PreDestroy
	public void destroy() {
		log.info("UserManagedBean Ends");
	}

	public void setDateBirthday(Date date) {
		if (date != null) {
			Instant instant = Instant.ofEpochMilli(date.getTime());
			this.setBirthday(LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate());
		}
	}

	public Date getDateBirthday() {
		if (this.getBirthday() != null) {
			Instant instant = this.getBirthday().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
			return Date.from(instant);
		}
		return null;
	}

}
