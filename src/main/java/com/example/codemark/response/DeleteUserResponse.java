package com.example.codemark.response;

import com.example.codemark.dto.ResponseTemplate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "DeleteUserResponseWrapper",namespace = "http://com.testTask.userService")
@XmlAccessorType(XmlAccessType.FIELD)
public class DeleteUserResponse {

    @XmlElement(name = "returnDeleteResponse",namespace = "http://com.testTask.userService")
    public ResponseTemplate returnDeleteResponse;

    public ResponseTemplate getReturnDeleteResponse() {
        return returnDeleteResponse;
    }

    public void setReturnDeleteResponse(ResponseTemplate returnDeleteResponse) {
        this.returnDeleteResponse = returnDeleteResponse;
    }
}
