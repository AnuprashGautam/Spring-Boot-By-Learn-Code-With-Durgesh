// Processing add contact form.
@PostMapping("/process-contact")
public String processContact(@ModelAttribute Contact contact,
							Principal principal,
							@RequestParam("profileImage") MultipartFile file,
							HttpSession session) 
{
	
	boolean b=false;  // For storing the status.
	try {
		String name=principal.getName();
		User user = this.userRepository.getUserByUserName(name);
		
		// Processing and uploading file(Image).
		if(!file.isEmpty())
		{
			contact.setImage(file.getOriginalFilename());
			
			File saveFile = new ClassPathResource("/static/img").getFile(); // The path where profileImage is going to be saved.
			
			Path path = Paths.get(saveFile.getAbsolutePath()+File.separator+file.getOriginalFilename());
			
			Files.copy(file.getInputStream(),path , StandardCopyOption.REPLACE_EXISTING);
			
			b=true;
			System.out.println("Image uploaded successfully.");
		}
		
		
		contact.setUser(user);					// Setting user in the contact.
		user.getContacts().add(contact);		// Adding contact in the user's contact attribute.
		
		
		
		this.userRepository.save(user);
		
		// For add contact form.
		if(b) {
			session.setAttribute("message",new Message("Contact added Successfully!!!","alert-success"));
		}
		
		// For the back-end console.
		System.out.println("Contact added successfully.");
	} catch (Exception e) {
		e.printStackTrace();
		session.setAttribute("message",new Message("Something went wrong!!!","alert-danger"));
	}
	
	return "normal/add_contact_form";
}