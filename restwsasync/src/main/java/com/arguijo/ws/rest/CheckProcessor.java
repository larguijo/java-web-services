package com.arguijo.ws.rest;

import com.arguijo.ws.rest.model.CheckList;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;

@Path("/checkprocessingservice")
public interface CheckProcessor {
	@POST
	@Path("/checks")
	public void processChecks(@Suspended AsyncResponse response, CheckList checkList);
}
