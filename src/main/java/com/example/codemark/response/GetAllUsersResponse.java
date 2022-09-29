package com.example.codemark.response;

import com.example.codemark.entity.User;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlType(name = "GetAllUsersResponseWrapper",namespace = "http://com.testTask.userService")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetAllUsersResponse {

    @XmlElement(name = "returnUsers",namespace = "http://com.testTask.userService")
    public List<User> returnUsers;

    public List<User> getReturnUsers() {
        return returnUsers;
    }

    public void setReturnUsers(List<User> returnUsers) {
        this.returnUsers = returnUsers;
    }
}
