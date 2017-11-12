/*****************************************************************************
 * FILE NAME   : ViewResolver.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Oct 18, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.server.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author avijit
 *
 */
@Controller
public class ViewResolver {
	/**
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@GetMapping("/")
    public String listUploadedFiles(Model model) throws IOException {

        return "upload";
    }
}
