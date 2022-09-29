package com.example.codemark.response;

import com.example.codemark.dto.UserWithRoles;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "GetUserByLoginResponseWrapper",namespace = "http://com.testTask.userService")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetUserByLoginResponse {

    @XmlElement(name = "returnUserWithRoles",namespace = "http://com.testTask.userService")
    public UserWithRoles returnUserWithRoles;

    public UserWithRoles getReturnUserWithRoles() {
        return returnUserWithRoles;
    }

    public void setReturnUserWithRoles(UserWithRoles returnUserWithRoles) {
        this.returnUserWithRoles = returnUserWithRoles;
    }
}
