package com.example.codemark.response;

import com.example.codemark.dto.ResponseTemplate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "AddUserResponseWrapper",namespace = "http://com.testTask.userService")
@XmlAccessorType(XmlAccessType.FIELD)
public class AddUserResponse {

    @XmlElement(name = "returnAddResponse",namespace = "http://com.testTask.userService")
    public ResponseTemplate returnAddResponse;

    public ResponseTemplate getReturnAddResponse() {
        return returnAddResponse;
    }

    public void setReturnAddResponse(ResponseTemplate returnAddResponse) {
        this.returnAddResponse = returnAddResponse;
    }
}
