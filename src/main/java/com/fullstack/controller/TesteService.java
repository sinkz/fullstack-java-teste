package com.fullstack.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;


@Path("/message")
public class TesteService
{
    @GET
    public String getMsg()
    {
         return "Hello World !! - Jersey 2";
    }
}
