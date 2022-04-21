package com.mindtree.ambulanceserviceapplication.property;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class HomeController {

	@GetMapping("/home")
	public String home() {
		return "Ambulance service APIs are up and runing";
	}

}
