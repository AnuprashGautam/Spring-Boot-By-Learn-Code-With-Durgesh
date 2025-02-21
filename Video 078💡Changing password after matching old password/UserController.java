public class UserController {
    // Change password handler
	@PostMapping("/change-password")
	public String changePassword(Principal principal,
								@RequestParam("oldPassword") String oldPassword,
								@RequestParam("newPassword") String newPassword,
								HttpSession session) 
	{
		System.out.println("OLD_PASSWORD: "+oldPassword);		
		System.out.println("NEW_PASSWORD: "+newPassword);	
		
		String userName = principal.getName();
		User user = this.userRepository.getUserByUserName(userName);
		
		System.out.println(user.getPassword());
		
		if(this.bCryptPasswordEncoder.matches(oldPassword, user.getPassword())) 
		{
			// Changing the password and returning the success message.
			user.setPassword(this.bCryptPasswordEncoder.encode(newPassword));
			
			this.userRepository.save(user);
			
			session.setAttribute("message", new Message("Password updated successfully.", "alert-success"));
		}else 
		{
			// Just returning the failure message.

			session.setAttribute("message", new Message("Password didn't matched with the old password. Something went wrong!!!", "alert-danger"));
		}
		
		return "redirect: user/settings";
	}
}
