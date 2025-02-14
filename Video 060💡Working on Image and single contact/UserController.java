// Showing particular contact details.
@GetMapping("/{cId}/contact")
public String  showContactDetail(@PathVariable("cId") Integer cId, Model model) {
    
    System.out.println("Showing the details of the contact with id:"+cId);
    
    Optional<Contact> contactOptional = this.contactRepository.findById(cId);
    Contact contact= contactOptional.get();
    
    model.addAttribute("title",  contact.getName()+"- Contact Details");
    model.addAttribute("contact",contact);
    
    return "normal/contact_detail";
}