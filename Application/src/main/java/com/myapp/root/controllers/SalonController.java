

import com.myapp.root.model.Salon


public class SalonController(){
    @Autowired
    private SalonRepository salonRepository;

    @PostMapping("salon")
    Salon newSalon(@RequestBody Salon newSalon) {
        return salonRepository.save(newSalon);
    }

    @GetMapping("/salons")
    List<Salon> getAllSalons() {
        return salonRepository.findAll();
    }

    @GetMapping("/salon/{id}")
    Salon getSalonById(@PathVariable Long id) {
        return salonRepository.findById(id)
                .orElseThrow(() -> new SalonNotFoundException(id));
    }

    @PutMapping("/salon/{id}")
    Salon updateSalon(@RequestBody Salon newSalon, @PathVariable Long id) {
        return salonRepository.findById(id)
                .map(salon -> {
                    salon.setSalonname(newSalon.getSalonname());
                    salon.setName(newSalon.getName());
                    salon.setEmail(newSalon.getEmail());
                    return salonRepository.save(salon);
                }).orElseThrow(() -> new SalonNotFoundException(id));
    }

    @DeleteMapping("/salon/{id}")
    String deleteSalon(@PathVariable Long id){
        if(!salonRepository.existsById(id)){
            throw new SalonNotFoundException(id);
        }
        salonRepository.deleteById(id);
        return  "Salon with id "+id+" has been deleted success.";
    }



}