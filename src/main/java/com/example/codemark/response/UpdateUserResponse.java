package com.example.codemark.response;

import com.example.codemark.dto.ResponseTemplate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "UpdateUserResponseWrapper",namespace = "http://com.testTask.userService")
@XmlAccessorType(XmlAccessType.FIELD)
public class UpdateUserResponse {

    @XmlElement(name = "returnUpdateResponse",namespace = "http://com.testTask.userService")
    public ResponseTemplate returnUpdateResponse;

    public ResponseTemplate getReturnUpdateResponse() {
        return returnUpdateResponse;
    }

    public void setReturnUpdateResponse(ResponseTemplate returnUpdateResponse) {
        this.returnUpdateResponse = returnUpdateResponse;
    }
}
