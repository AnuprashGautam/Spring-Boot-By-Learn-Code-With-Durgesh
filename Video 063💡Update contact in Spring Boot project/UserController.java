package com.smart.controller;

	// Open update form handler
	@PostMapping("/update-contact/{cId}")
	public String updateForm(@PathVariable("cId") Integer cId ,Model model, Principal principal) {
		
		Optional<Contact> contactOptional = this.contactRepository.findById(cId);
		Contact contact= contactOptional.get();
		
		//Sending the api key to the template so that the respective service can be used.
		model.addAttribute("api_key",apiKey);
		model.addAttribute("contact",contact);
		model.addAttribute("title","Update Contact");
		
		return "normal/update_form";
	}






