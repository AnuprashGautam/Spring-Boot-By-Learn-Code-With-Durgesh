// Handling the update contact form data and updating the respective contact.
@PostMapping("/process-update")
public String updateHandler(@ModelAttribute Contact contact,@RequestParam("profileImage") MultipartFile file,Model model,HttpSession session,Principal principle)
{
	
	try {
		
		//fetch old contact details
		Contact oldcontactDetails=this.contactRepository.findById(contact.getcId()).get();
		
		
		// Checking if the user want to update the photo or not.
		if(!file.isEmpty())
		{
			//delete file
			File deleteFile=new ClassPathResource("static/img").getFile();  // For path to that folder.
			File file1=new File(deleteFile,oldcontactDetails.getImage());
			file1.delete();
			
			//update image
			File saveFile=new ClassPathResource("static/img").getFile();
			
			Path path=Paths.get(saveFile.getAbsolutePath()+File.separator+file.getOriginalFilename());
			
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			contact.setImage(file.getOriginalFilename());
		}
		else
		{
			contact.setImage(oldcontactDetails.getImage());
		}
		
		User user=this.userRepository.getUserByUserName(principle.getName());
		
		contact.setUser(user);
		
		this.contactRepository.save(contact);
		
		session.setAttribute("message", new Message("Contact updated successfully.","alert-success"));
		
	}catch(Exception e)
	{
		e.printStackTrace();
		session.setAttribute("message", new Message("Something went wrong.","alert-danger"));
		
	}
	
	
	System.out.println("Conatct NAME="+contact.getName());
	System.out.println("Contact ID="+contact.getcId());
	return "redirect:/user/"+contact.getcId()+"/contact";
}