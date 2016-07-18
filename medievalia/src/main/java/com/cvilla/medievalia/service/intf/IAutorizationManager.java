package com.cvilla.medievalia.service.intf;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.cvilla.medievalia.domain.User;

@Component
public interface IAutorizationManager extends Serializable {

	public boolean isAutorized(int action, User user);
}
