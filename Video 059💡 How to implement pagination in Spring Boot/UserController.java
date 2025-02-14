// Show contacts handler
	// Contacts per page represented by "n".
	// Current page = 0 represented by "page".
	@GetMapping("/show-contacts/{page}")
	public String showContacts(@PathVariable("page") Integer page,Model model, Principal principal) {
		
		model.addAttribute("title","Show User Contacts");
		
		String userName= principal.getName();
		User user = this.userRepository.getUserByUserName(userName);
		
		// Fetching all the contacts of the logged in user.
		// This pageable object has two informations: current page - page,  contact per page - 8.
		Pageable pageable = PageRequest.of(page, 8);
		Page<Contact> contacts = this.contactRepository.findContactsByUser(user.getId(),pageable);
		
		model.addAttribute("contacts",contacts);
		model.addAttribute("currentPage",page);
		model.addAttribute("totalPages",contacts.getTotalPages());
		
		return "normal/show_contacts";
	}