package com.devcom.service;

import java.util.Optional;

import com.devcom.dto.ResponseDTO;
import com.devcom.entity.Response;

public interface ResponseService {
	public Response addResponse(ResponseDTO responsedto);

	public Response editResponse(int respId, ResponseDTO responsedto);
	
	public String removeResponse(int respId);

	public Optional<Response> getResponse(int respid);
	
}
