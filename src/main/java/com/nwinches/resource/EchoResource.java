package com.nwinches.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("echo/{echo}")
public class EchoResource {

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String getEcho(@PathParam("echo") String echo) {
    return echo;
  }
}
