//search handler
@GetMapping("/search/{query}")
public ResponseEntity<?> search(Principal principal,
                                @PathVariable("query") String query)
{
    System.out.println(query);
    User user = this.userRepository.getUserByUserName(principal.getName());
    
    List<Contact> contacts= this.contactRepository.findByNameContainingAndUser(query, user);
    
    return ResponseEntity.ok(contacts);
}